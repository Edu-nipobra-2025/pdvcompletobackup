package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Preco;
import br.com.PdvFrontEnd.service.PrecoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrecoForm extends JFrame {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    private JTextField txtValor;
    private JTextField txtDataAlteracao;
    private JTextField txtHoraAlteracao;
    private PrecoService precoService;
    private PrecoList precoList;
    private Preco precoEmEdicao;

    public PrecoForm(PrecoService service, PrecoList list) {
        this(service, list, null);
    }

    public PrecoForm(PrecoService service, PrecoList list, Preco preco) {
        this.precoService = service;
        this.precoList = list;
        this.precoEmEdicao = preco;

        setTitle(preco == null ? "Cadastro de Preço" : "Editar Preço");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Valor:"));


        txtValor = new JTextField();
        txtValor.setBackground(Color.WHITE);
        txtValor.setForeground(SECONDARY_COLOR);
        txtValor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtValor.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtValor);

        mainPanel.add(new JLabel("Data de Alteração (dd/MM/yyyy):"));
        txtDataAlteracao = new JTextField();
        txtDataAlteracao.setBackground(Color.WHITE);
        txtDataAlteracao.setForeground(SECONDARY_COLOR);
        txtDataAlteracao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataAlteracao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtDataAlteracao);

        mainPanel.add(new JLabel("Hora de Alteração (HH:mm:ss):"));
        txtHoraAlteracao = new JTextField();
        txtHoraAlteracao.setBackground(Color.WHITE);
        txtHoraAlteracao.setForeground(SECONDARY_COLOR);
        txtHoraAlteracao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtHoraAlteracao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtHoraAlteracao);

        // Se estiver editando, preencher os campos
        if (precoEmEdicao != null) {
            txtValor.setText(precoEmEdicao.getValor().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            txtDataAlteracao.setText(dateFormat.format(precoEmEdicao.getDataAlteracao()));
            txtHoraAlteracao.setText(timeFormat.format(precoEmEdicao.getHoraAlteracao()));
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar");
        JButton btnCancelar = createStyledButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarPreco());
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

    private void salvarPreco() {
        try {
            BigDecimal valor = new BigDecimal(txtValor.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date dataAlteracao = dateFormat.parse(txtDataAlteracao.getText());
            Date horaAlteracao = timeFormat.parse(txtHoraAlteracao.getText());

            if (precoEmEdicao != null) {
                // Modo edição
                Preco precoAtualizado = new Preco(valor, dataAlteracao, horaAlteracao);
                precoService.updatePreco(precoEmEdicao.getId(), precoAtualizado);
            } else {
                // Modo criação
                Preco preco = new Preco(valor, dataAlteracao, horaAlteracao);
                precoService.addPreco(preco);
            }
            precoList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
