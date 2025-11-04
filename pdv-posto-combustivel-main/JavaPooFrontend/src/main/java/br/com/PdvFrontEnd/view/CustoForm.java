package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Custo;
import br.com.PdvFrontEnd.service.CustoService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustoForm extends JFrame {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    private JTextField txtImposto;
    private JTextField txtFrete;
    private JTextField txtCustoVariavel;
    private JTextField txtCustoFixo;
    private JTextField txtMargemLucro;
    private JTextField txtDataProcessamento;
    private CustoService custoService;
    private CustoList custoList;
    private Custo custoEmEdicao;

    public CustoForm(CustoService service, CustoList list) {
        this(service, list, null);
    }

    public CustoForm(CustoService service, CustoList list, Custo custo) {
        this.custoService = service;
        this.custoList = list;
        this.custoEmEdicao = custo;

        setTitle(custo == null ? "Cadastro de Custo" : "Editar Custo");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Imposto:"));


        txtImposto = new JTextField();
        txtImposto.setBackground(Color.WHITE);
        txtImposto.setForeground(SECONDARY_COLOR);
        txtImposto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtImposto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtImposto);

        mainPanel.add(new JLabel("Frete:"));
        txtFrete = new JTextField();
        txtFrete.setBackground(Color.WHITE);
        txtFrete.setForeground(SECONDARY_COLOR);
        txtFrete.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtFrete.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtFrete);

        mainPanel.add(new JLabel("Custo Variável:"));
        txtCustoVariavel = new JTextField();
        txtCustoVariavel.setBackground(Color.WHITE);
        txtCustoVariavel.setForeground(SECONDARY_COLOR);
        txtCustoVariavel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCustoVariavel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtCustoVariavel);

        mainPanel.add(new JLabel("Custo Fixo:"));
        txtCustoFixo = new JTextField();
        txtCustoFixo.setBackground(Color.WHITE);
        txtCustoFixo.setForeground(SECONDARY_COLOR);
        txtCustoFixo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtCustoFixo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtCustoFixo);

        mainPanel.add(new JLabel("Margem de Lucro:"));
        txtMargemLucro = new JTextField();
        txtMargemLucro.setBackground(Color.WHITE);
        txtMargemLucro.setForeground(SECONDARY_COLOR);
        txtMargemLucro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMargemLucro.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtMargemLucro);

        mainPanel.add(new JLabel("Data de Processamento (dd/MM/yyyy):"));
        txtDataProcessamento = new JTextField();
        txtDataProcessamento.setBackground(Color.WHITE);
        txtDataProcessamento.setForeground(SECONDARY_COLOR);
        txtDataProcessamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataProcessamento.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtDataProcessamento);

        // Se estiver editando, preencher os campos
        if (custoEmEdicao != null) {
            txtImposto.setText(String.valueOf(custoEmEdicao.getImposto()));
            txtFrete.setText(String.valueOf(custoEmEdicao.getFrete()));
            txtCustoVariavel.setText(String.valueOf(custoEmEdicao.getCustoVariavel()));
            txtCustoFixo.setText(String.valueOf(custoEmEdicao.getCustoFixo()));
            txtMargemLucro.setText(String.valueOf(custoEmEdicao.getMargemLucro()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            txtDataProcessamento.setText(dateFormat.format(custoEmEdicao.getDataProcessamento()));
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar");
        JButton btnCancelar = createStyledButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarCusto());
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

    private void salvarCusto() {
        try {
            double imposto = Double.parseDouble(txtImposto.getText());
            double frete = Double.parseDouble(txtFrete.getText());
            double custoVariavel = Double.parseDouble(txtCustoVariavel.getText());
            double custoFixo = Double.parseDouble(txtCustoFixo.getText());
            double margemLucro = Double.parseDouble(txtMargemLucro.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataProcessamento = dateFormat.parse(txtDataProcessamento.getText());

            if (custoEmEdicao != null) {
                // Modo edição
                Custo custoAtualizado = new Custo(
                        null, // ID será definido pelo backend
                        imposto,
                        frete,
                        custoVariavel,
                        custoFixo,
                        margemLucro,
                        dataProcessamento
                );
                custoService.updateCusto(custoEmEdicao.getId(), custoAtualizado);
            } else {
                // Modo criação
                Custo custo = new Custo(
                        null, // ID será definido pelo backend
                        imposto,
                        frete,
                        custoVariavel,
                        custoFixo,
                        margemLucro,
                        dataProcessamento
                );
                custoService.addCusto(custo);
            }
            custoList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
