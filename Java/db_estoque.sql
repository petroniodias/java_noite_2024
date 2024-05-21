-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 22/05/2024 às 00:33
-- Versão do servidor: 10.4.28-MariaDB
-- Versão do PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_exemplo`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_clientes`
--

CREATE TABLE `tb_clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_clientes`
--

INSERT INTO `tb_clientes` (`id`, `nome`, `telefone`, `email`) VALUES
(1, 'Manuel Gumercindo', '21-98765-4321', 'mgumercindo@zemail.com');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_compras`
--

CREATE TABLE `tb_compras` (
  `id` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `id_fornecedor` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_compras`
--

INSERT INTO `tb_compras` (`id`, `id_produto`, `id_fornecedor`, `quantidade`, `preco`) VALUES
(1, 1, 1, 5, 25),
(2, 2, 1, 3, 25),
(3, 1, 2, 15, 22);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_fornecedores`
--

CREATE TABLE `tb_fornecedores` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contato` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_fornecedores`
--

INSERT INTO `tb_fornecedores` (`id`, `nome`, `telefone`, `email`, `contato`) VALUES
(1, 'Distribuidora Geral Ltda', '61-99988-7766', 'contato@distrgeral.com.br', 'João do Caminhão'),
(2, 'Candango Distribuidora Ltda', '61-98765-4321', 'contato@cangangodistr.com.br', 'José Candango');

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_produtos`
--

CREATE TABLE `tb_produtos` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `unidade` varchar(3) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_produtos`
--

INSERT INTO `tb_produtos` (`id`, `nome`, `unidade`, `quantidade`, `preco`) VALUES
(1, 'Caneta Azul', 'PCT', 5, 35),
(2, 'Caneta Vermelha', 'PCT', 3, 37),
(4, 'Caderno pautado', 'un', 15, 18),
(5, 'Caderno pautado 4 materias', 'un', 25, 28),
(6, 'Caneta Vermelha', 'PCT', 3, 37),
(7, 'Caderno pautado', 'un', 10, 28);

-- --------------------------------------------------------

--
-- Estrutura para tabela `tb_vendas`
--

CREATE TABLE `tb_vendas` (
  `id` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `preco` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `tb_vendas`
--

INSERT INTO `tb_vendas` (`id`, `id_produto`, `id_cliente`, `quantidade`, `preco`) VALUES
(1, 1, 1, 5, 35),
(2, 2, 2, 3, 40);

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `tb_clientes`
--
ALTER TABLE `tb_clientes`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tb_compras`
--
ALTER TABLE `tb_compras`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tb_fornecedores`
--
ALTER TABLE `tb_fornecedores`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tb_produtos`
--
ALTER TABLE `tb_produtos`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `tb_vendas`
--
ALTER TABLE `tb_vendas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `tb_clientes`
--
ALTER TABLE `tb_clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `tb_compras`
--
ALTER TABLE `tb_compras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `tb_fornecedores`
--
ALTER TABLE `tb_fornecedores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de tabela `tb_produtos`
--
ALTER TABLE `tb_produtos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de tabela `tb_vendas`
--
ALTER TABLE `tb_vendas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
