package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Contato;
import br.com.PdvFrontEnd.service.ContatoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class ContatoForm extends JFrame {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtEndereco;
    private ContatoService contatoService;
    private ContatoList contatoList;
    private Contato contatoEmEdicao;

    public ContatoForm(ContatoService service, ContatoList list) {
        this(service, list, null);
    }

    public ContatoForm(ContatoService service, ContatoList list, Contato contato) {
        this.contatoService = service;
        this.contatoList = list;
        this.contatoEmEdicao = contato;

        setTitle(contato == null ? "Cadastro de Contato" : "Editar Contato");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Telefone:"));


        txtTelefone = new JTextField();
        txtTelefone.setBackground(Color.WHITE);
        txtTelefone.setForeground(SECONDARY_COLOR);
        txtTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtTelefone.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtTelefone);

        mainPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        txtEmail.setBackground(Color.WHITE);
        txtEmail.setForeground(SECONDARY_COLOR);
        txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtEmail);

        mainPanel.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        txtEndereco.setBackground(Color.WHITE);
        txtEndereco.setForeground(SECONDARY_COLOR);
        txtEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtEndereco.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtEndereco);

        // Se estiver editando, preencher os campos
        if (contatoEmEdicao != null) {
            txtTelefone.setText(contatoEmEdicao.getTelefone());
            txtEmail.setText(contatoEmEdicao.getEmail());
            txtEndereco.setText(contatoEmEdicao.getEndereco());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar");
        JButton btnCancelar = createStyledButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarContato());
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

    private void salvarContato() {
        if (contatoEmEdicao != null) {
            // Modo edição
            Contato contatoAtualizado = new Contato(
                    null, // ID será definido pelo backend
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtEndereco.getText()
            );
            contatoService.updateContato(contatoEmEdicao.getId(), contatoAtualizado);
        } else {
            // Modo criação
            Contato contato = new Contato(
                    null, // ID será definido pelo backend
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    txtEndereco.getText()
            );
            contatoService.addContato(contato);
        }
        contatoList.atualizarTabela();
        dispose();
    }
}
