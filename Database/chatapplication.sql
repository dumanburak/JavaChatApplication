-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 30 Haz 2022, 16:25:59
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 7.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `chatapplication`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `admin`
--

CREATE TABLE `admin` (
  `No` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `admin`
--

INSERT INTO `admin` (`No`, `Name`, `Username`, `Password`) VALUES
(1, 'Burak', 'brkdmn', '2777dae8914d39f1f491a246cc56a3977083bfa99e8b1bb1f82b2045750543a7'),
(2, 'Elif', 'elfkvk', '4e4ce939c5de8c235101eb750078131e771333b9bd72be60e5c03261c3cf648'),
(3, 'Bestami', 'bstmgny', 'c25c2be5275e54f3198af3b9f47e8d1273ba371f36d90dcaca2c674b205e7c8a');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `badwordlog`
--

CREATE TABLE `badwordlog` (
  `No` int(11) NOT NULL,
  `Sender` varchar(50) NOT NULL,
  `Chat` varchar(500) NOT NULL,
  `Time` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `badwordlog`
--

INSERT INTO `badwordlog` (`No`, `Sender`, `Chat`, `Time`) VALUES
(1, 'elif', 'i want to kill you', ' 11:4:30 '),
(2, 'burak', 'i have a bomb', ' 11:33:14 '),
(3, 'can', 'i want to kill you', ' 15:32:17 ');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `banned_users`
--

CREATE TABLE `banned_users` (
  `No` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `MyID` varchar(50) NOT NULL,
  `eMail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `banned_users`
--

INSERT INTO `banned_users` (`No`, `Username`, `MyID`, `eMail`) VALUES
(1, 'elif', 'elif', 'elifkvk1905@gmail.com'),
(2, 'burak', 'burak', 'brkdmn190399@gmail.com'),
(3, 'biran', 'can', 'sharpe1907@gmail.com');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `forbidden_words`
--

CREATE TABLE `forbidden_words` (
  `No` int(11) NOT NULL,
  `Word` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `forbidden_words`
--

INSERT INTO `forbidden_words` (`No`, `Word`) VALUES
(1, 'kill'),
(2, 'die'),
(3, 'terror'),
(4, 'terrorist'),
(5, 'slain'),
(6, 'murder'),
(7, 'bomb'),
(8, 'heroin'),
(9, 'drug'),
(10, 'weed'),
(11, 'gun'),
(12, 'weapon'),
(13, 'strike'),
(14, 'drug'),
(15, 'suicide');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `myaccounts`
--

CREATE TABLE `myaccounts` (
  `Username` text NOT NULL,
  `Password` text NOT NULL,
  `MyID` text NOT NULL,
  `eMail` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `mychat`
--

CREATE TABLE `mychat` (
  `Sender` text NOT NULL,
  `Chat` text NOT NULL,
  `Time` text NOT NULL,
  `BadWords` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`No`);

--
-- Tablo için indeksler `badwordlog`
--
ALTER TABLE `badwordlog`
  ADD PRIMARY KEY (`No`);

--
-- Tablo için indeksler `banned_users`
--
ALTER TABLE `banned_users`
  ADD PRIMARY KEY (`No`);

--
-- Tablo için indeksler `forbidden_words`
--
ALTER TABLE `forbidden_words`
  ADD PRIMARY KEY (`No`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `admin`
--
ALTER TABLE `admin`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `badwordlog`
--
ALTER TABLE `badwordlog`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `banned_users`
--
ALTER TABLE `banned_users`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `forbidden_words`
--
ALTER TABLE `forbidden_words`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
