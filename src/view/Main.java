import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Produto;

import view.RoundBorder;
import view.TelaCadastroProduto;
import view.TelaProdutos;

public class Main {
    private static boolean sidebarVisible = true;
    private static Point initialClick;

    public static void main(String[] args) {
        Produto produto = new Produto();
        produto.setNome("Caneta");
        produto.setPreco(22.0);
        produto.setQuantidade(25);




        // Configurar a janela principal
        JFrame frame = new JFrame();
        frame.setSize(1366, 768);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Instanciar TelaProdutos
        TelaProdutos telaProdutos = new TelaProdutos();

        // Configurar o painel lateral com BorderLayout para logo e botões
        JPanel sidebar2 = new JPanel();
        sidebar2.setLayout(new BorderLayout());
        sidebar2.setBackground(Color.DARK_GRAY);
        sidebar2.setPreferredSize(new Dimension(250, 768));

        // Carregar e redimensionar a logomarca
        ImageIcon logoIcon = new ImageIcon("resources/icons/Stokke_logo.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(logoImage);

        // Adicionar logomarca ao painel lateral
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(resizedLogoIcon);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sidebar2.add(logoLabel, BorderLayout.NORTH);

        // Configurar o painel de botões no painel lateral
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adicionar botões
        JButton cadastroButton = RoundBorder.createStyledButton("Configurações", "src/resources/icons/cadastro.png");
        JButton listarButton = RoundBorder.createStyledButton("Listar Produtos", "src/resources/icons/listar.png");
        JButton relatorioButton = RoundBorder.createStyledButton("Gerar Relatório", "src/resources/icons/relatorio.png");
        JButton sairButton = RoundBorder.createStyledButton("Sair", "src/resources/icons/sair.png");

        // Ação para o botão de cadastro
        cadastroButton.addActionListener(e -> {
            //TelaCadastroProduto.createFormCadastro(); // Mudar para o painel de cadastro
        });

        listarButton.addActionListener(e -> {
            telaProdutos.cardLayout.show(telaProdutos.contentPanel, "list"); // Mudar para o painel de listagem
        });

        relatorioButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Funcionalidade de relatório ainda não implementada.");
        });

        sairButton.addActionListener(e -> System.exit(0));



        // Adicionar botões ao painel lateral
        buttonPanel.add(cadastroButton, gbc);
        gbc.gridy = 1;
        buttonPanel.add(listarButton, gbc);
        gbc.gridy = 2;
        buttonPanel.add(relatorioButton, gbc);
        gbc.gridy = 3;
        buttonPanel.add(sairButton, gbc);

        sidebar2.add(buttonPanel, BorderLayout.CENTER);

        // Configurar o painel principal
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(telaProdutos, BorderLayout.CENTER); // Adicionar a tela de produtos ao painel de conteúdo

        // Configurar o botão de abrir/fechar painel lateral
        JButton toggleSidebarButton = new JButton(">");
        toggleSidebarButton.setPreferredSize(new Dimension(50, 30));
        toggleSidebarButton.setBackground(Color.DARK_GRAY);
        toggleSidebarButton.setForeground(Color.WHITE);
        toggleSidebarButton.setFocusPainted(false);

        toggleSidebarButton.addActionListener(e -> {
            if (sidebarVisible) {
                frame.remove(sidebar2);
                toggleSidebarButton.setText(">");
                sidebarVisible = false;
            } else {
                frame.add(sidebar2, BorderLayout.WEST);
                toggleSidebarButton.setText("<");
                sidebarVisible = true;
            }
            frame.revalidate();
            frame.repaint();
        });

        // Configurar o painel da barra de título personalizada
        JPanel titleBar = new JPanel();
        titleBar.setBackground(Color.DARK_GRAY);
        titleBar.setPreferredSize(new Dimension(1366, 30));
        titleBar.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Stokke v1.0");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        titleBar.add(titleLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("X");
        closeButton.setBackground(Color.DARK_GRAY);
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(30, 30));
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.addActionListener(e -> System.exit(0));
        titleBar.add(closeButton, BorderLayout.EAST);
        titleBar.add(toggleSidebarButton, BorderLayout.WEST);

        titleBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });

        titleBar.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (initialClick != null) {
                    int x = e.getXOnScreen() - initialClick.x;
                    int y = e.getYOnScreen() - initialClick.y;
                    frame.setLocation(x, y);
                }
            }
        });

        frame.add(titleBar, BorderLayout.NORTH);
        frame.add(contentPanel, BorderLayout.CENTER); // Adicionar painel de conteúdo ao centro
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }
}
