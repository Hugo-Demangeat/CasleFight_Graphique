package com.btssio66.hdemangeat.caslefight_graphique.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CombatDAO {

    private static String lastErrorMessage = null;

    public static boolean enregistrerResultat(Personnage gagnant, Personnage perdant, double degatsGagnant, double degatsPerdant) {
        boolean successGagnant = upsertStatistique(gagnant.getNom(), 1, 0, degatsGagnant);
        boolean successPerdant = upsertStatistique(perdant.getNom(), 0, 1, degatsPerdant);
        return successGagnant && successPerdant;
    }

    private static boolean upsertStatistique(String personnage, int victoiresDelta, int defaitesDelta, double degatsDelta) {
        String updateSql = "UPDATE resultats_combats "
                         + "SET nb_victoires = nb_victoires + ?, "
                         + "nb_defaites = nb_defaites + ?, "
                         + "total_degats_infliges = total_degats_infliges + ? "
                         + "WHERE personnage = ?";
        String insertSql = "INSERT INTO resultats_combats (personnage, nb_victoires, nb_defaites, total_degats_infliges) "
                         + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            updateStmt.setInt(1, victoiresDelta);
            updateStmt.setInt(2, defaitesDelta);
            updateStmt.setDouble(3, degatsDelta);
            updateStmt.setString(4, personnage);

            int updated = updateStmt.executeUpdate();
            if (updated > 0) {
                lastErrorMessage = null;
                return true;
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                insertStmt.setString(1, personnage);
                insertStmt.setInt(2, victoiresDelta);
                insertStmt.setInt(3, defaitesDelta);
                insertStmt.setDouble(4, degatsDelta);
                int inserted = insertStmt.executeUpdate();
                if (inserted > 0) {
                    lastErrorMessage = null;
                    return true;
                }
            }

            lastErrorMessage = "Aucune ligne insérée.";
            return false;

        } catch (SQLException e) {
            lastErrorMessage = e.getMessage();
            System.err.println("Erreur lors de l'enregistrement du résultat : " + lastErrorMessage);
            e.printStackTrace();
            return false;
        }
    }

    public static String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public static List<StatistiquePersonnage> getStatistiques() {
        List<StatistiquePersonnage> statistiques = new ArrayList<>();
        String sql = "SELECT personnage, nb_victoires, nb_defaites, total_degats_infliges "
                   + "FROM resultats_combats "
                   + "ORDER BY nb_victoires DESC, total_degats_infliges DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nom = rs.getString("personnage");
                int victoires = rs.getInt("nb_victoires");
                int defaites = rs.getInt("nb_defaites");
                double totalDegats = rs.getDouble("total_degats_infliges");
                String ratioVD = victoires + "-" + defaites;
                statistiques.add(new StatistiquePersonnage(nom, victoires, defaites, totalDegats, ratioVD));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des statistiques : " + e.getMessage());
            // Retourner une liste vide
        }

        return statistiques;
    }
}
