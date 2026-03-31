package com.btssio66.hdemangeat.caslefight_graphique.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CombatDAO {

    public static boolean enregistrerResultat(Personnage gagnant, Personnage perdant) {
    String sql = "INSERT INTO resultats_combats (nom_gagnant, nom_perdant, vie_restante_gagnant, date_combat) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, gagnant.getNom());
        stmt.setString(2, perdant.getNom());
        stmt.setInt(3, gagnant.getVie());
        stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

        int rowsAffected = stmt.executeUpdate();
        System.out.println("SQL exécuté : " + stmt);
        System.out.println("Lignes affectées : " + rowsAffected);

        if (rowsAffected > 0) {
            System.out.println("Résultat enregistré : " + gagnant.getNom() + " a vaincu " + perdant.getNom() + " avec " + gagnant.getVie() + " PV restants");
            return true;
        }

    } catch (SQLException e) {
        System.err.println("Erreur SQL : " + e.getMessage());
        e.printStackTrace();
    }
    return false;
}


    // Récupère les statistiques pour chaque personnage
    public static List<StatistiquePersonnage> getStatistiques() {
        List<StatistiquePersonnage> statistiques = new ArrayList<>();
        String sql = "SELECT nom, "
                   + "SUM(victoires) as total_victoires, "
                   + "SUM(defaites) as total_defaites, "
                   + "SUM(points_vie) as total_points "
                   + "FROM ("
                   + " SELECT nom_gagnant as nom, COUNT(*) as victoires, 0 as defaites, SUM(vie_restante_gagnant) as points_vie FROM resultats_combats GROUP BY nom_gagnant "
                   + " UNION ALL "
                   + " SELECT nom_perdant as nom, 0 as victoires, COUNT(*) as defaites, 0 as points_vie FROM resultats_combats GROUP BY nom_perdant "
                   + ") AS stats "
                   + "GROUP BY nom "
                   + "ORDER BY total_victoires DESC, total_points DESC";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nom = rs.getString("nom");
                int victoires = rs.getInt("total_victoires");
                int defaites = rs.getInt("total_defaites");
                int totalPoints = rs.getInt("total_points");

                statistiques.add(new StatistiquePersonnage(nom, victoires, defaites, totalPoints));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des statistiques : " + e.getMessage());
            e.printStackTrace();
        }

        return statistiques;
    }
}
