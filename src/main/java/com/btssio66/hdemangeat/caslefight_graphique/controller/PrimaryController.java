package com.btssio66.hdemangeat.caslefight_graphique.controller;

import com.btssio66.hdemangeat.caslefight_graphique.model.*;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML private ImageView ImagePikachu;
    @FXML private ImageView ImageBulbizarre;
    @FXML private ImageView ImageCarapuce;
    @FXML private ImageView ImageSalamèche;
    @FXML private ImageView ImagePokeball1;
    @FXML private ImageView ImagePokeball2;
    @FXML private ImageView ImageVersus;
    @FXML private ImageView ImageAttaque1;
    @FXML private ImageView ImageAttaque2;
    @FXML private TextArea TextArena;

    // ProgressBars pour les PV
    @FXML private ProgressBar BarreVieJoueur1;
    @FXML private ProgressBar BarreVieJoueur2;

    private Personnage joueur1 = null;
    private Personnage joueur2 = null;

    private int selectionIndex = 0;
    private boolean combatEnCours = false;
    private int currentAttacker; // 1 = joueur1, 2 = joueur2

    private final Random random = new Random();

    // Pokémon images
    private final Image imgSalamèche = new Image(getClass().getResourceAsStream("/images/Salamèche-carre.png"));
    private final Image imgCarapuce = new Image(getClass().getResourceAsStream("/images/Carapuce.png"));
    private final Image imgBulbizarre = new Image(getClass().getResourceAsStream("/images/Bulbizarre.png"));
    private final Image imgPikachu = new Image(getClass().getResourceAsStream("/images/Pikachu-carre.png"));
    private final Image imgVersus = new Image(getClass().getResourceAsStream("/images/Versus.png"));

    // Attaques
    private final Image imgDegat = new Image(getClass().getResourceAsStream("/images/dégat.png"));
    private final Image imgFouetLiane = new Image(getClass().getResourceAsStream("/images/fouet-liane.png"));
    private final Image imgFlaméche = new Image(getClass().getResourceAsStream("/images/Flaméche.png"));
    private final Image imgBoule_Elek = new Image(getClass().getResourceAsStream("/images/Boule_Elek.png"));
    private final Image imgBulleEau = new Image(getClass().getResourceAsStream("/images/bulle d'eau.png"));
    private final Image imgGagne = new Image(getClass().getResourceAsStream("/images/gagner.png"));
    private final Image imgPerdu = new Image(getClass().getResourceAsStream("/images/perdue.png"));

    public void initialize() {
        TextArena.setText("Bienvenue dans le monde Pokémon !\nChoisissez deux Pokémon pour commencer le combat.\n");
        ImageVersus.setImage(imgVersus);

        // Initialisation des ProgressBars
        BarreVieJoueur1.setProgress(1.0);
        BarreVieJoueur2.setProgress(1.0);
    }
    
    @FXML
    private void showScoresAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/btssio66/hdemangeat/caslefight_graphique/scores.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Statistiques des Combats");
            stage.setScene(new Scene(root, 600, 500));
            stage.setResizable(true);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    public void Clickedquitter(ActionEvent a) {
        Platform.exit();
    }

    @FXML
    public void selectPersoAction(MouseEvent event) {
        if (combatEnCours) {
            TextArena.appendText("⚠️ Impossible de changer de Pokémon pendant le combat !\n");
            return;
        }

        ImageView clicked = (ImageView) event.getSource();
        Personnage p = null;
        Image image = null;

        if (clicked == ImageSalamèche) { p = new Salamèche("Salamèche"); image = imgSalamèche; }
        else if (clicked == ImageCarapuce) { p = new Carapuce("Carapuce"); image = imgCarapuce; }
        else if (clicked == ImageBulbizarre) { p = new Bulbizarre("Bulbizarre"); image = imgBulbizarre; }
        else if (clicked == ImagePikachu) { p = new Pikachu("Pikachu"); image = imgPikachu; }

        if (p == null) return;

        if (selectionIndex % 2 == 0) {
            joueur1 = p;
            ImagePokeball1.setImage(image);
            ImagePokeball1.setOpacity(1.0);
            BarreVieJoueur1.setProgress(1.0);
            TextArena.appendText("Joueur 1 choisit " + p.getNom() + "\n");
        } else {
            joueur2 = p;
            ImagePokeball2.setImage(image);
            ImagePokeball2.setOpacity(1.0);
            BarreVieJoueur2.setProgress(1.0);
            TextArena.appendText("Joueur 2 choisit " + p.getNom() + "\n");
        }

        selectionIndex++;
    }

    @FXML
    public void Clickedfight(ActionEvent event) {
        if (joueur1 == null || joueur2 == null) {
            TextArena.appendText("⚠️ Sélectionnez deux Pokémon avant de lancer le combat.\n");
            return;
        }

        if (!combatEnCours) {
            combatEnCours = true;
            joueur1.setVie(100);
            joueur2.setVie(100);
            currentAttacker = random.nextBoolean() ? 1 : 2;

            BarreVieJoueur1.setProgress(1.0);
            BarreVieJoueur2.setProgress(1.0);

            ImageVersus.setImage(null);
            TextArena.appendText("\n⚔️ Combat entre " + joueur1.getNom() + " et " + joueur2.getNom() + " ! ⚔️\n");
            TextArena.appendText("---------------------\n");
            TextArena.appendText("C'est au tour de " + getAttacker().getNom() + " d'attaquer. Cliquez sur Fight.\n");
            return;
        }

        performOneAttack();
    }

    private Personnage getAttacker() { return currentAttacker == 1 ? joueur1 : joueur2; }
    private Personnage getDefender() { return currentAttacker == 1 ? joueur2 : joueur1; }
    private ImageView getAttackerImageView() { return currentAttacker == 1 ? ImageAttaque1 : ImageAttaque2; }
    private ImageView getDefenderImageViewForDegat() { return currentAttacker == 1 ? ImageAttaque2 : ImageAttaque1; }
    private ProgressBar getDefenderProgressBar() { return currentAttacker == 1 ? BarreVieJoueur2 : BarreVieJoueur1; }
    private ProgressBar getAttackerProgressBar() { return currentAttacker == 1 ? BarreVieJoueur1 : BarreVieJoueur2; }

    private void performOneAttack() {
        if (!combatEnCours) return;

        Personnage attaquant = getAttacker();
        Personnage defenseur = getDefender();

        int degats = attaquant.frapper();
        defenseur.setVie(Math.max(0, defenseur.getVie() - degats));

        getDefenderProgressBar().setProgress(defenseur.getVie() / 100.0);

        TextArena.appendText(attaquant.getNom() + " inflige " + degats + " dégâts !\n");
        TextArena.appendText(defenseur.getNom() + " : " + defenseur.getVie() + " PV\n\n");

        PauseTransition beforeAnim = new PauseTransition(Duration.millis(200));
        beforeAnim.setOnFinished(e -> {
            ImageView attImg = getAttackerImageView();
            ImageView degImg = getDefenderImageViewForDegat();
            attImg.setImage(getAttackImage(attaquant));
            degImg.setImage(imgDegat);

            screenShake();

            PauseTransition afterAnim = new PauseTransition(Duration.millis(350));
            afterAnim.setOnFinished(ev -> {
                attImg.setImage(null);
                degImg.setImage(null);

                if (defenseur.getVie() <= 0) {
                    showWinLoseAnimation();
                    return;
                }

                currentAttacker = (currentAttacker == 1) ? 2 : 1;
                TextArena.appendText("C'est au tour de " + getAttacker().getNom() + " d'attaquer. Cliquez sur Fight.\n");
            });
            afterAnim.play();
        });
        beforeAnim.play();
    }

    private Image getAttackImage(Personnage p) {
        if (p instanceof Pikachu) return imgBoule_Elek;
        if (p instanceof Salamèche) return imgFlaméche;
        if (p instanceof Carapuce) return imgBulleEau;
        if (p instanceof Bulbizarre) return imgFouetLiane;
        return null;
    }

    private void screenShake() {
        Node root = TextArena.getScene() == null ? null : TextArena.getScene().getRoot();
        if (root == null) return;

        TranslateTransition t1 = new TranslateTransition(Duration.millis(30), root); t1.setByX(8);
        TranslateTransition t2 = new TranslateTransition(Duration.millis(30), root); t2.setByX(-8);
        TranslateTransition t3 = new TranslateTransition(Duration.millis(20), root); t3.setByX(5);
        TranslateTransition t4 = new TranslateTransition(Duration.millis(20), root); t4.setByX(-5);
        TranslateTransition reset = new TranslateTransition(Duration.millis(10), root); reset.setToX(0);

        t1.play();
        t1.setOnFinished(e -> t2.play());
        t2.setOnFinished(e -> t3.play());
        t3.setOnFinished(e -> t4.play());
        t4.setOnFinished(e -> reset.play());
    }

    private void showWinLoseAnimation() {
        combatEnCours = false;

        Personnage gagnant = (joueur1.getVie() > 0) ? joueur1 : joueur2;
        Personnage perdant = (joueur1.getVie() > 0) ? joueur2 : joueur1;

        ImageView gagnantImg = (gagnant == joueur1) ? ImageAttaque1 : ImageAttaque2;
        ImageView perdantImg  = (gagnant == joueur1) ? ImageAttaque2 : ImageAttaque1;

        gagnantImg.setImage(imgGagne);
        perdantImg.setImage(imgPerdu);

        // 🔹 Enregistrement du résultat en base
        CombatDAO.enregistrerResultat(gagnant, perdant);

        TextArena.appendText("🏆 " + gagnant.getNom() + " gagne !\n");

        PauseTransition wait = new PauseTransition(Duration.seconds(2));
        wait.setOnFinished(e -> endCombat());
        wait.play();
    }


    private void endCombat() {
        ImagePokeball1.setImage(new Image(getClass().getResourceAsStream("/images/pokeball1.jpeg")));
        ImagePokeball2.setImage(new Image(getClass().getResourceAsStream("/images/pokeball4.jpeg")));
        ImagePokeball1.setOpacity(0.7);
        ImagePokeball2.setOpacity(0.7);

        joueur1 = null;
        joueur2 = null;
        selectionIndex = 0;

        BarreVieJoueur1.setProgress(1.0);
        BarreVieJoueur2.setProgress(1.0);

        ImageVersus.setImage(imgVersus);
        ImageAttaque1.setImage(null);
        ImageAttaque2.setImage(null);

        TextArena.appendText("\nCombat terminé. Sélectionnez de nouveaux Pokémon.\n");
    }
}
