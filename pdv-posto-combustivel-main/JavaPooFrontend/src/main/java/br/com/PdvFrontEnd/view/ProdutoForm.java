package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Produto;
import br.com.PdvFrontEnd.service.ProdutoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class ProdutoForm extends JFrame {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    private JTextField txtNome;
    private JTextField txtReferencia;
    private JTextField txtFornecedor;
    private JTextField txtCategoria;
    private JTextField txtMarca;
    private ProdutoService produtoService;
    private ProdutoList produtoList;
    private Produto produtoEmEdicao;

    public ProdutoForm(ProdutoService service, ProdutoList list) {
        this(service, list, null);
    }

    public ProdutoForm(ProdutoService service, ProdutoList list, Produto produto) {
        this.produtoService = service;
        this.produtoList = list;
        this.produtoEmEdicao = produto;

        setTitle(produto == null ? "Cadastro de Produto" : "Editar Produto");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Nome:"));


        txtNome = new JTextField();
        txtNome.setBackground(Color.WHITE);
        txtNome.setForeground(SECONDARY_COLOR);
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtNome);

        mainPanel.add(new JLabel("Referência:"));
        txtReferencia = new JTextField();
        txtReferencia.setBackground(Color.WHITE);
        txtReferencia.setForeground(SECONDARY_COLOR);
        txtReferencia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtReferencia.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtReferencia);

        mainPanel.add(new JLabel("Fornecedor:"));
        txtFornecedor = new JTextField();
        txtFornecedor.setBackground(Color.WHITE);
        txtFornecedor.setForeground(SECONDARY_COLOR);
        txtFornecedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtFornecedor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtFornecedor);

        mainPanel.add(new JLabel("Categoria:"));
        txtCategoria = new JTextField();
        txtCategoria.setBackground(Color.WHITE);
        txtCategoria.setForeground(SECONDARY_COLOR);
        txtCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCategoria.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtCategoria);

        mainPanel.add(new JLabel("Marca:"));
        txtMarca = new JTextField();
        txtMarca.setBackground(Color.WHITE);
        txtMarca.setForeground(SECONDARY_COLOR);
        txtMarca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMarca.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtMarca);

        // Se estiver editando, preencher os campos
        if (produtoEmEdicao != null) {
            txtNome.setText(produtoEmEdicao.getNome());
            txtReferencia.setText(produtoEmEdicao.getReferencia());
            txtFornecedor.setText(produtoEmEdicao.getFornecedor());
            txtCategoria.setText(produtoEmEdicao.getCategoria());
            txtMarca.setText(produtoEmEdicao.getMarca());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar");
        JButton btnCancelar = createStyledButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarProduto());
        btnCancelar.addActionListener(e -> dispose());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(8, 16, 8, 16)));
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

    private void salvarProduto() {
        if (produtoEmEdicao != null) {
            // Modo edição
            Produto produtoAtualizado = new Produto(
                    txtNome.getText(),
                    txtReferencia.getText(),
                    txtFornecedor.getText(),
                    txtCategoria.getText(),
                    txtMarca.getText()
            );
            produtoService.updateProduto(produtoEmEdicao.getId(), produtoAtualizado);
        } else {
            // Modo criação
            Produto produto = new Produto(
                    txtNome.getText(),
                    txtReferencia.getText(),
                    txtFornecedor.getText(),
                    txtCategoria.getText(),
                    txtMarca.getText()
            );
            produtoService.addProduto(produto);
        }
        produtoList.atualizarTabela();
        dispose();
    }
}
