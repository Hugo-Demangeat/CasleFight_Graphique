package com.btssio66.hdemangeat.caslefight_graphique.controller;

import com.btssio66.hdemangeat.caslefight_graphique.model.CombatDAO;
import com.btssio66.hdemangeat.caslefight_graphique.model.StatistiquePersonnage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScoresController implements Initializable {

    @FXML private TableView<StatistiquePersonnage> scoresTableView;
    @FXML private TableColumn<StatistiquePersonnage, String> nomColumn;
    @FXML private TableColumn<StatistiquePersonnage, Integer> victoiresColumn;
    @FXML private TableColumn<StatistiquePersonnage, Integer> defaitesColumn;
    @FXML private TableColumn<StatistiquePersonnage, Integer> totalPVColumn;
    @FXML private TableColumn<StatistiquePersonnage, String> ratioColumn;
    @FXML private Label totalCombatsLabel;

    private ObservableList<StatistiquePersonnage> statistiquesData;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        victoiresColumn.setCellValueFactory(new PropertyValueFactory<>("victoires"));
        defaitesColumn.setCellValueFactory(new PropertyValueFactory<>("defaites"));
        totalPVColumn.setCellValueFactory(new PropertyValueFactory<>("totalPointsVie"));

        // Ratio calculé
        ratioColumn.setCellValueFactory(cellData -> {
            StatistiquePersonnage stat = cellData.getValue();
            int victoires = stat.getVictoires();
            int defaites = stat.getDefaites();
            String ratio;
            if (defaites == 0) {
                ratio = victoires > 0 ? victoires + " - 0" : "Aucun combat";
            } else {
                ratio = String.format("%d - %d (%.2f%%)", victoires, defaites,(victoires * 100.0 / (victoires + defaites)));
            }
            return new javafx.beans.property.SimpleStringProperty(ratio);
        });

        loadStatistiques();
    }

    private void loadStatistiques() {
        List<StatistiquePersonnage> statistiques = CombatDAO.getStatistiques();
        statistiquesData = FXCollections.observableArrayList(statistiques);
        scoresTableView.setItems(statistiquesData);

        int totalCombats = statistiques.stream()
                .mapToInt(stat -> stat.getVictoires() + stat.getDefaites())
                .sum() / 2;
        totalCombatsLabel.setText("Total de combats : " + totalCombats);
    }

    @FXML
    protected void refreshAction() {
        loadStatistiques();
    }

    @FXML
    protected void closeAction() {
        Stage stage = (Stage) scoresTableView.getScene().getWindow();
        stage.close();
    }
}
