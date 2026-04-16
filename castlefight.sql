-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 16 avr. 2026 à 09:44
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `castlefight`
--

-- --------------------------------------------------------

--
-- Structure de la table `resultats_combats`
--

CREATE TABLE `resultats_combats` (
  `id` int(11) NOT NULL,
  `personnage` varchar(100) NOT NULL,
  `nb_victoires` int(11) NOT NULL DEFAULT 0,
  `nb_defaites` int(11) NOT NULL DEFAULT 0,
  `total_degats_infliges` double NOT NULL DEFAULT 0,
  `ratio_vd` double GENERATED ALWAYS AS (case when `nb_defaites` = 0 then `nb_victoires` else `nb_victoires` / `nb_defaites` end) STORED
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `resultats_combats`
--

INSERT INTO `resultats_combats` (`id`, `personnage`, `nb_victoires`, `nb_defaites`, `total_degats_infliges`) VALUES
(1, 'Bulbizarre', 2, 0, 215),
(2, 'Carapuce', 0, 7, 529),
(3, 'Pikachu', 3, 0, 319),
(4, 'Salamèche', 2, 0, 209);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `resultats_combats`
--
ALTER TABLE `resultats_combats`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `resultats_combats`
--
ALTER TABLE `resultats_combats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
