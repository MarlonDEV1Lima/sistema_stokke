package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;



public class TelaProdutos extends JPanel {
    public static CardLayout cardLayout = null;
    public static JPanel contentPanel = null;
    private static Point initialClick;

    public TelaProdutos() {
        // Inicializando o CardLayout e o contentPanel
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        // Adicionando contentPanel ao layout principal
        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Chamar outros métodos para configurar a interface
        contentPanel.add(conteudo(), "form");
        contentPanel.add(createListPanel(), "list");

        add(createTopBar(), BorderLayout.NORTH);
    }

    // Método para criar a barra superior (top bar)
    public static JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBorder(new EmptyBorder(10, 10, 10, 10));
        topBar.setBackground(Color.LIGHT_GRAY);

        // Seção de pesquisa
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(Color.LIGHT_GRAY);
        JLabel searchLabel = new JLabel("Pesquisar:");
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Buscar");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Seção de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        JButton addButton = new JButton("Novo Produto");
        JButton editButton = new JButton("Editar");
        JButton deleteButton = new JButton("Excluir");

        // Ação de abrir formulário de cadastro
        addButton.addActionListener(e -> {
            // Criar a nova janela para cadastro de produto
            JFrame cadastroFrame = new JFrame("Cadastrar Produto");
            cadastroFrame.setSize(800, 400);
            cadastroFrame.setUndecorated(true);
            cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cadastroFrame.setLocationRelativeTo(null); // Centraliza em relação à tela



            // Criar e adicionar o painel de cadastro
            JPanel cadastroPanel = TelaProdutos.conteudo();
            


            ImageIcon cadImage = new ImageIcon("resources/icons/tela_cadastro_img.png");
            Image logoImage = cadImage.getImage().getScaledInstance(300, 280, Image.SCALE_SMOOTH);
            ImageIcon resizedImageIcon = new ImageIcon(logoImage);
            //imageLabel.setSize(300,200);


            cadastroFrame.add(cadastroPanel);
            JLabel logoLabel = new JLabel();
            logoLabel.setIcon(resizedImageIcon);
            logoLabel.setHorizontalAlignment(SwingConstants.HORIZONTAL);
            cadastroFrame.add(logoLabel, BorderLayout.WEST);

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
            closeButton.addActionListener(evt -> cadastroFrame.dispose());
            titleBar.add(closeButton, BorderLayout.EAST);


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
                        cadastroFrame.setLocation(x, y);
                    }
                }
            });

            cadastroFrame.add(titleBar, BorderLayout.NORTH);
            cadastroFrame.add(TelaCadastroProduto.createFormCadastro(), BorderLayout.CENTER); // Adicionar painel de conteúdo ao centro
            cadastroFrame.getContentPane().setBackground(Color.WHITE);
            cadastroFrame.setVisible(true);


        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        topBar.add(searchPanel, BorderLayout.WEST);
        topBar.add(buttonPanel, BorderLayout.EAST);

        return topBar;
    }

    // Painel de listagem de produtos
    public static JPanel createListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());

        // Lista de produtos (usando JTable para exibição)
        String[] columnNames = {"ID", "Nome", "Quantidade", "Preço"};
        Object[][] data = {
                {1, "Produto A", 10, "R$ 100,00"},
                {2, "Produto B", 5, "R$ 50,00"},
                {3, "Produto C", 20, "R$ 200,00"}
        };

        JTable productTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(productTable);

        listPanel.add(scrollPane, BorderLayout.CENTER);
        return listPanel;
    }

    // Painel de formulário de cadastro de produtos

    public static JPanel conteudo(){
        JPanel painel_principal = new JPanel(new BorderLayout());
        JPanel painelPrincipal = painel_principal;
        return painelPrincipal;
    }


}
