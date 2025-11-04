package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.service.*;
import br.com.PdvFrontEnd.util.SessionManager;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class MainApp {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            // Inicia o sistema pelo login
            SessionManager sessionManager = SessionManager.getInstance();

            // Se não existir usuário cadastrado, abre a tela de cadastro
            if (!sessionManager.userExists()) {
                new RegisterView().setVisible(true);
            } else {
                new LoginView().setVisible(true);
            }
        });
    }

    public static void showMainApp() {
        java.awt.EventQueue.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Gerenciamento PDV");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(300, 400);
            mainFrame.getContentPane().setBackground(SECONDARY_COLOR); // Define o fundo do frame
            mainFrame.setLocationRelativeTo(null); // Centraliza a janela
            mainFrame.setLayout(new GridLayout(0, 1, 10, 10)); // Adiciona espaçamento entre os botões

               // Pessoa
            PessoaService pessoaService = new PessoaService();
            JButton btnPessoa = createStyledButton("Gerenciar Pessoas");
            btnPessoa.addActionListener(e -> new PessoaList(pessoaService).setVisible(true));
            mainFrame.add(btnPessoa);

            // Preco
            PrecoService precoService = new PrecoService();
            JButton btnPreco = createStyledButton("Gerenciar Preços");
            btnPreco.addActionListener(e -> new PrecoList(precoService).setVisible(true));
            mainFrame.add(btnPreco);

            // Produto
            ProdutoService produtoService = new ProdutoService();
            JButton btnProduto = createStyledButton("Gerenciar Produtos");
            btnProduto.addActionListener(e -> new ProdutoList(produtoService).setVisible(true));
            mainFrame.add(btnProduto);

            // Custo
            CustoService custoService = new CustoService();
            JButton btnCusto = createStyledButton("Gerenciar Custos");
            btnCusto.addActionListener(e -> new CustoList(custoService).setVisible(true));
            mainFrame.add(btnCusto);

            // Estoque
            EstoqueService estoqueService = new EstoqueService();
            JButton btnEstoque = createStyledButton("Gerenciar Estoques");
            btnEstoque.addActionListener(e -> new EstoqueList(estoqueService).setVisible(true));
            mainFrame.add(btnEstoque);

            // Acesso
            AcessoService acessoService = new AcessoService();
            JButton btnAcesso = createStyledButton("Gerenciar Acessos");
            btnAcesso.addActionListener(e -> new AcessoList(acessoService).setVisible(true));
            mainFrame.add(btnAcesso);

            // Contato
            ContatoService contatoService = new ContatoService();
            JButton btnContato = createStyledButton("Gerenciar Contatos");
            btnContato.addActionListener(e -> new ContatoList(contatoService).setVisible(true));
            mainFrame.add(btnContato);

            mainFrame.setVisible(true);
        });
    }

    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setFocusPainted(false);
        button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                AbstractButton b = (AbstractButton) c;
                ButtonModel model = b.getModel();

                if (model.isPressed()) {
                    g2.setColor(BUTTON_HOVER_COLOR.darker());
                } else if (model.isRollover()) {
                    g2.setColor(BUTTON_HOVER_COLOR);
                } else {
                    g2.setColor(b.getBackground());
                }
                g2.fillRect(0, 0, b.getWidth(), b.getHeight());
                g2.dispose();
                super.paint(g, c);
            }
        });
        return button;
    }
}

