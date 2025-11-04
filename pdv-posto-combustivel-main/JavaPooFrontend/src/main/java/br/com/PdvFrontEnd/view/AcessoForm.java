package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Acesso;
import br.com.PdvFrontEnd.service.AcessoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class AcessoForm extends JFrame {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private AcessoService acessoService;
    private AcessoList acessoList;
    private Acesso acessoEmEdicao; // Para rastrear se estamos editando

    public AcessoForm(AcessoService service, AcessoList list) {
        this(service, list, null);
    }

    public AcessoForm(AcessoService service, AcessoList list, Acesso acesso) {
        this.acessoService = service;
        this.acessoList = list;
        this.acessoEmEdicao = acesso;

        setTitle(acesso == null ? "Cadastro de Acesso" : "Editar Acesso");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Usuário:"));


        txtUsuario = new JTextField();
        txtUsuario.setBackground(Color.WHITE);
        txtUsuario.setForeground(SECONDARY_COLOR);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtUsuario);

        mainPanel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        txtSenha.setBackground(Color.WHITE);
        txtSenha.setForeground(SECONDARY_COLOR);
        txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtSenha);

        // Se estiver editando, preencher os campos
        if (acessoEmEdicao != null) {
            txtUsuario.setText(acessoEmEdicao.getUsuario());
            txtSenha.setText(acessoEmEdicao.getSenha());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar");
        JButton btnCancelar = createStyledButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarAcesso());
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

    private void salvarAcesso() {
        if (acessoEmEdicao != null) {
            // Modo edição
            Acesso acessoAtualizado = new Acesso(
                    txtUsuario.getText(),
                    new String(txtSenha.getPassword())
            );
            acessoService.updateAcesso(acessoEmEdicao.getId(), acessoAtualizado);
        } else {
            // Modo criação
            Acesso acesso = new Acesso(
                    txtUsuario.getText(),
                    new String(txtSenha.getPassword())
            );
            acessoService.addAcesso(acesso);
        }
        acessoList.atualizarTabela();
        dispose();
    }
}
