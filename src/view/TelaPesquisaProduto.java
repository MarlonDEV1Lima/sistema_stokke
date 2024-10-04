package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Criar a tela de pesquisa de produtos
public class TelaPesquisaProduto {

    public static JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem ao redor do painel

        // Título da página
        JLabel searchLabel = new JLabel("Pesquisar Produtos");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 24));
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        searchPanel.add(searchLabel, BorderLayout.NORTH);

        // Painel para barra de pesquisa
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JTextField searchField = new JTextField(20); // Tamanho fixo do campo de pesquisa
        JButton searchButton = new JButton("Pesquisar");
        inputPanel.add(searchField);
        inputPanel.add(searchButton);
        searchPanel.add(inputPanel, BorderLayout.CENTER);

        // Criar a tabela de produtos com DefaultTableModel
        String[] colunas = {"ID", "Nome", "Quantidade", "Preço", "Editar", "Deletar"};
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(tableModel);

        // Definindo a largura das colunas
        tabela.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150); // Nome
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100); // Quantidade
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100); // Preço

        // Adicionar os dados de exemplo
        tableModel.addRow(new Object[]{"1", "Produto A", "100", "R$ 50,00"});
        tableModel.addRow(new Object[]{"2", "Produto B", "200", "R$ 100,00"});
        tableModel.addRow(new Object[]{"3", "Produto C", "150", "R$ 75,00"});

        // Estilizar tabela
        tabela.setRowHeight(30);
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabela.setFont(new Font("Arial", Font.PLAIN, 14));
        tabela.setShowGrid(true);
        tabela.setGridColor(Color.LIGHT_GRAY);
        tabela.setSelectionBackground(Color.GRAY);

        // Renderizadores de botão
        tabela.getColumn("Editar").setCellRenderer(new ButtonRenderer("resources/icons/edit-icon.png"));
        tabela.getColumn("Deletar").setCellRenderer(new ButtonRenderer("resources/icons/delete-icon.png"));
        tabela.getColumn("Editar").setCellEditor(new ButtonEditor(new JCheckBox(), "resources/icons/edit-icon.png"));
        tabela.getColumn("Deletar").setCellEditor(new ButtonEditor(new JCheckBox(), "resources/icons/delete-icon.png"));

        // Criar um painel para a tabela
        JPanel tablePanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(800, 400)); // Largura desejada da tabela
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        tablePanel.add(scrollPane);

        // Adicionando o painel da tabela ao painel de pesquisa
        searchPanel.add(tablePanel, BorderLayout.SOUTH);

        return searchPanel;
    }

    // Classe para renderizar botões na tabela
    static class ButtonRenderer extends JButton implements TableCellRenderer {
        private ImageIcon icon;

        public ButtonRenderer(String iconPath) {
            icon = resizeIcon(new ImageIcon(iconPath), 20, 20); // Redimensiona a imagem
            setOpaque(true);
        }

        private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon(icon);
            setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
            setBorderPainted(false);
            return this;
        }
    }

    // Classe para editar botões na tabela
    static class ButtonEditor extends DefaultCellEditor {
        private JButton button;
        private boolean isPushed;
        private ImageIcon icon;

        public ButtonEditor(JCheckBox checkBox, String iconPath) {
            super(checkBox);
            button = new JButton();
            icon = resizeIcon(new ImageIcon(iconPath), 30, 30); // Redimensiona a imagem
            button.setIcon(icon);
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        }


        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row) {
            isPushed = true;
            button.setIcon(icon);
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
                // Ação do botão (pode ser editado ou deletado)
            }
            isPushed = false;
            return null;
        }
    }






}
