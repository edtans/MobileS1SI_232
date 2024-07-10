-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Jul 2024 pada 03.42
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mobile_dtbs`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` varchar(11) NOT NULL,
  `nama` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`nim`, `nama`) VALUES
('17410100031', 'Dimas Ramadhan Efendi'),
('17410100107', 'Bayu Fajar Setiaji Nugroho'),
('18410100187', 'Muhammad Brilian Pamungkas'),
('18410100216', 'Aditya Ramadhan'),
('19410100006', 'Jhody Lesmana Ongko'),
('19410100018', 'Arif Hermawan'),
('19410100035', 'Sukman'),
('19410100087', 'Fariz Purnama Aji'),
('19410100097', 'Niol Kobak'),
('19410100121', 'Alifian Akmal Amanullah'),
('19410100128', 'Mikhe Mariana Oematan'),
('19410100130', 'Mas Adjie Hanafiah Hadi Putra'),
('20410100016', 'Muhammad Annafi Fakhruddin'),
('21410100002', 'Wahyu Cinde Yudha Chaniagara'),
('22410100034', 'Fandy Ardiansyah'),
('22410100041', 'Abimanyu Prayana'),
('22410100042', 'Valencia Avetta Christie'),
('22410100044', 'Gregory Alexandro Sani Fenat'),
('22410100055', 'Muhammad Ziyan Fachrun Syahhansyah'),
('22410100072', 'Rania Ayu Putri Tambunan');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
