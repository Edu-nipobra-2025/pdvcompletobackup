package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Pessoa;
import br.com.PdvFrontEnd.service.PessoaService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class PessoaForm extends JFrame {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    private JTextField txtNome;
    private JTextField txtCtps;
    private JTextField txtDataNascimento;
    private JComboBox<String> comboTipo;
    private PessoaService pessoaService;
    private PessoaList pessoaList;
    private Pessoa pessoaEmEdicao;

    public PessoaForm(PessoaService service, PessoaList list) {
        this(service, list, null);
    }

    public PessoaForm(PessoaService service, PessoaList list, Pessoa pessoa) {
        this.pessoaService = service;
        this.pessoaList = list;
        this.pessoaEmEdicao = pessoa;

        setTitle(pessoa == null ? "Cadastro de Pessoa" : "Editar Pessoa");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 400);
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

        mainPanel.add(new JLabel("CTPS:"));
        txtCtps = new JTextField();
        txtCtps.setBackground(Color.WHITE);
        txtCtps.setForeground(SECONDARY_COLOR);
        txtCtps.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCtps.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtCtps);

        mainPanel.add(new JLabel("Data de Nascimento:"));
        txtDataNascimento = new JTextField();
        txtDataNascimento.setBackground(Color.WHITE);
        txtDataNascimento.setForeground(SECONDARY_COLOR);
        txtDataNascimento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataNascimento.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtDataNascimento);

        mainPanel.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(new String[]{"Cliente", "Fornecedor"});
        comboTipo.setBackground(Color.WHITE);
        comboTipo.setForeground(SECONDARY_COLOR);
        comboTipo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboTipo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(comboTipo);

        // Se estiver editando, preencher os campos
        if (pessoaEmEdicao != null) {
            txtNome.setText(pessoaEmEdicao.getNome());
            txtCtps.setText(pessoaEmEdicao.getCpf());
            txtDataNascimento.setText(pessoaEmEdicao.getDataNascimento());
            comboTipo.setSelectedItem(pessoaEmEdicao.getTipo());
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar");
        JButton btnCancelar = createStyledButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPessoa());
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

    private void salvarPessoa() {
        if (pessoaEmEdicao != null) {
            // Modo edição
            Pessoa pessoaAtualizada = new Pessoa(
                    null,
                    txtNome.getText(),
                    txtCtps.getText(),
                    txtDataNascimento.getText(),
                    (String) comboTipo.getSelectedItem()
            );
            pessoaService.updatePessoa(pessoaEmEdicao.getId(), pessoaAtualizada);
        } else {
            // Modo criação
            Pessoa pessoa = new Pessoa(
                    null,
                    txtNome.getText(),
                    txtCtps.getText(),
                    txtDataNascimento.getText(),
                    (String) comboTipo.getSelectedItem()
            );
            pessoaService.addPessoa(pessoa);
        }
        pessoaList.atualizarTabela();
        dispose();
    }
}