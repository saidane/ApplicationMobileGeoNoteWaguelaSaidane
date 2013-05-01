-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Client: 127.0.0.1
-- Généré le : Dim 03 Mars 2013 à 23:08
-- Version du serveur: 5.5.20
-- Version de PHP: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `geonote`
--

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE IF NOT EXISTS `note` (
  `id_note` int(255) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(100) NOT NULL,
  `titre` varchar(50) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id_note`),
  UNIQUE KEY `id_note_UNIQUE` (`id_note`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `note`
--

INSERT INTO `note` (`id_note`, `adresse`, `titre`, `description`) VALUES
(1, '47 rue Antoine Durafour', 'Adresse de Mr WAGUELA', 'essai'),
(2, '17 Rue du Docteur Bouchut 69003 Lyon', 'Centre de la Part Dieu', 'Magasins');

-- --------------------------------------------------------

--
-- Structure de la table `parcours`
--

CREATE TABLE IF NOT EXISTS `parcours` (
  `id_parcours` int(255) NOT NULL AUTO_INCREMENT,
  `titre` varchar(50) NOT NULL,
  `commentaire` varchar(500) NOT NULL,
  PRIMARY KEY (`id_parcours`),
  UNIQUE KEY `id_parcours_UNIQUE` (`id_parcours`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `parcours`
--

INSERT INTO `parcours` (`id_parcours`, `titre`, `commentaire`) VALUES
(1, 'Promenade', 'a lyon'),
(2, 'Sortie', 'super');

-- --------------------------------------------------------

--
-- Structure de la table `parcours_has_note`
--

CREATE TABLE IF NOT EXISTS `parcours_has_note` (
  `parcours_id_parcours` int(255) NOT NULL,
  `note_id_note` int(255) NOT NULL,
  PRIMARY KEY (`parcours_id_parcours`,`note_id_note`),
  KEY `fk_parcours_has_note_note1_idx` (`note_id_note`),
  KEY `fk_parcours_has_note_parcours1_idx` (`parcours_id_parcours`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `parcours_has_note`
--

INSERT INTO `parcours_has_note` (`parcours_id_parcours`, `note_id_note`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int(255) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `id_utilisateur_UNIQUE` (`id_utilisateur`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `login`, `password`) VALUES
(1, 'Admin', 'Admin'),
(2, 'Demo', 'Demo');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `parcours_has_note`
--
ALTER TABLE `parcours_has_note`
  ADD CONSTRAINT `fk_parcours_has_note_note1` FOREIGN KEY (`note_id_note`) REFERENCES `note` (`id_note`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_parcours_has_note_parcours1` FOREIGN KEY (`parcours_id_parcours`) REFERENCES `parcours` (`id_parcours`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
