package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Estoque;
import br.com.PdvFrontEnd.service.EstoqueService;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EstoqueForm extends JFrame {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    private JTextField txtQuantidade;
    private JTextField txtLocalTanque;
    private JTextField txtLocalEndereco;
    private JTextField txtLoteFabricacao;
    private JTextField txtDataValidade;
    private EstoqueService estoqueService;
    private EstoqueList estoqueList;
    private Estoque estoqueEmEdicao;

    public EstoqueForm(EstoqueService service, EstoqueList list) {
        this(service, list, null);
    }

    public EstoqueForm(EstoqueService service, EstoqueList list, Estoque estoque) {
        this.estoqueService = service;
        this.estoqueList = list;
        this.estoqueEmEdicao = estoque;

        setTitle(estoque == null ? "Cadastro de Estoque" : "Editar Estoque");
        getContentPane().setBackground(BACKGROUND_COLOR);
        setSize(500, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(new JLabel("Quantidade:"));


        txtQuantidade = new JTextField();
        txtQuantidade.setBackground(Color.WHITE);
        txtQuantidade.setForeground(SECONDARY_COLOR);
        txtQuantidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtQuantidade.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtQuantidade);

        mainPanel.add(new JLabel("Local Tanque:"));
        txtLocalTanque = new JTextField();
        txtLocalTanque.setBackground(Color.WHITE);
        txtLocalTanque.setForeground(SECONDARY_COLOR);
        txtLocalTanque.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLocalTanque.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtLocalTanque);

        mainPanel.add(new JLabel("Local Endereço:"));
        txtLocalEndereco = new JTextField();
        txtLocalEndereco.setBackground(Color.WHITE);
        txtLocalEndereco.setForeground(SECONDARY_COLOR);
        txtLocalEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLocalEndereco.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtLocalEndereco);

        mainPanel.add(new JLabel("Lote Fabricação:"));
        txtLoteFabricacao = new JTextField();
        txtLoteFabricacao.setBackground(Color.WHITE);
        txtLoteFabricacao.setForeground(SECONDARY_COLOR);
        txtLoteFabricacao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLoteFabricacao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtLoteFabricacao);

        mainPanel.add(new JLabel("Data de Validade (dd/MM/yyyy):"));
        txtDataValidade = new JTextField();
        txtDataValidade.setBackground(Color.WHITE);
        txtDataValidade.setForeground(SECONDARY_COLOR);
        txtDataValidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDataValidade.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        mainPanel.add(txtDataValidade);

        // Se estiver editando, preencher os campos
        if (estoqueEmEdicao != null) {
            txtQuantidade.setText(estoqueEmEdicao.getQuantidade().toString());
            txtLocalTanque.setText(estoqueEmEdicao.getLocalTanque());
            txtLocalEndereco.setText(estoqueEmEdicao.getLocalEndereco());
            txtLoteFabricacao.setText(estoqueEmEdicao.getLoteFabricacao());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            txtDataValidade.setText(dateFormat.format(estoqueEmEdicao.getDataValidade()));
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton btnSalvar = createStyledButton("Salvar");
        JButton btnCancelar = createStyledButton("Cancelar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        btnSalvar.addActionListener(e -> salvarEstoque());
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

    private void salvarEstoque() {
        try {
            BigDecimal quantidade = new BigDecimal(txtQuantidade.getText());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataValidade = dateFormat.parse(txtDataValidade.getText());

            if (estoqueEmEdicao != null) {
                // Modo edição
                Estoque estoqueAtualizado = new Estoque(
                        null, // ID será definido pelo backend
                        quantidade,
                        txtLocalTanque.getText(),
                        txtLocalEndereco.getText(),
                        txtLoteFabricacao.getText(),
                        dataValidade
                );
                estoqueService.updateEstoque(estoqueEmEdicao.getId(), estoqueAtualizado);
            } else {
                // Modo criação
                Estoque estoque = new Estoque(
                        null, // ID será definido pelo backend
                        quantidade,
                        txtLocalTanque.getText(),
                        txtLocalEndereco.getText(),
                        txtLoteFabricacao.getText(),
                        dataValidade
                );
                estoqueService.addEstoque(estoque);
            }
            estoqueList.atualizarTabela();
            dispose();
        } catch (NumberFormatException | ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro de formato de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
