package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Estoque;
import model.Produto;

public class TelaCadastroProduto extends JPanel {
    public static JPanel createFormCadastro() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Título
        JLabel titleLabel = new JLabel("Cadastro de Produto");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);

        // Nome do Produto
        gbc.gridwidth = 1;
        gbc.gridy++;
        JLabel nomeLabel = new JLabel("Nome do Produto:");
        formPanel.add(nomeLabel, gbc);

        gbc.gridx = 1;
        JTextField nomeField = new JTextField(20);
        formPanel.add(nomeField, gbc);

        // Quantidade
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel quantidadeLabel = new JLabel("Quantidade:");
        formPanel.add(quantidadeLabel, gbc);

        gbc.gridx = 1;
        JTextField quantidadeField = new JTextField(20);
        formPanel.add(quantidadeField, gbc);

        // Preço
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel precoLabel = new JLabel("Preço:");
        formPanel.add(precoLabel, gbc);

        gbc.gridx = 1;
        JTextField precoField = new JTextField(20);
        formPanel.add(precoField, gbc);

        // Upload de imagem
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel fotoLabel = new JLabel("Foto do Produto:");
        formPanel.add(fotoLabel, gbc);

        gbc.gridx = 1;
        JButton uploadButton = new JButton("Selecionar Imagem");
        formPanel.add(uploadButton, gbc);

        // Botão Cadastrar
        gbc.gridy++;
        gbc.gridwidth = 20;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton cadastrarButton = new JButton("Cadastrar Produto");
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        cadastrarButton.setBackground(Color.DARK_GRAY);
        cadastrarButton.setForeground(Color.WHITE);
        formPanel.add(cadastrarButton, gbc);

        return formPanel;
    }
  }

