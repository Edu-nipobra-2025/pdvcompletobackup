package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Custo;
import br.com.PdvFrontEnd.service.CustoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.util.List;

public class CustoList extends JFrame {
    private final CustoService custoService;
    private JTable table;

    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80);
    private static final Color ACCENT_COLOR = new Color(230, 126, 34);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color TABLE_HEADER_COLOR = new Color(52, 73, 94);
    private static final Color TABLE_SELECTION_COLOR = new Color(142, 68, 173);
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185);

    public CustoList(CustoService service) {
        this.custoService = service;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Custos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 550);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("GERENCIAMENTO DE CUSTOS", SwingConstants.CENTER);
        header.setFont(new Font("Arial Black", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(TEXT_COLOR);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR, 3),
                new EmptyBorder(15, 0, 15, 0)
        ));
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY_COLOR, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));

        table = new JTable();
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(28);
        table.setGridColor(BACKGROUND_COLOR);
        table.setSelectionBackground(TABLE_SELECTION_COLOR);
        table.setSelectionForeground(TEXT_COLOR);
        table.getTableHeader().setBackground(TABLE_HEADER_COLOR);
        table.getTableHeader().setForeground(TEXT_COLOR);
        table.getTableHeader().setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(SECONDARY_COLOR);

        JButton btnAdicionar = criarBotao("Adicionar", PRIMARY_COLOR, TEXT_COLOR);
        JButton btnEditar = criarBotao("Editar", PRIMARY_COLOR, TEXT_COLOR);
        JButton btnRemover = criarBotao("Remover", PRIMARY_COLOR, TEXT_COLOR);
        JButton btnAtualizar = criarBotao("Atualizar", PRIMARY_COLOR, TEXT_COLOR);

        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnRemover);
        buttonPanel.add(btnAtualizar);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        btnAdicionar.addActionListener(e -> {
            CustoForm form = new CustoForm(custoService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> editarCusto(e));
        btnRemover.addActionListener(e -> removerCusto(e));
        btnAtualizar.addActionListener(e -> atualizarTabela());

        atualizarTabela();
    }

    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton btn = new JButton(texto);
        btn.setBackground(fundo);
        btn.setForeground(textoCor);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                new EmptyBorder(8, 16, 8, 16)
        ));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(BUTTON_HOVER_COLOR);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(fundo);
            }
        });
        return btn;
    }

    private void editarCusto(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<Custo> custos = custoService.getAllCustos();
            if (selectedRow < custos.size()) {
                Custo custo = custos.get(selectedRow);
                if (custo.getId() != null) {
                    CustoForm form = new CustoForm(custoService, this, custo);
                    form.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Custo não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um custo para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removerCusto(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<Custo> custos = custoService.getAllCustos();
            if (selectedRow < custos.size()) {
                Custo custo = custos.get(selectedRow);
                if (custo.getId() != null) {
                    custoService.removeCusto(custo.getId());
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Custo não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um custo para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"Imposto", "Frete", "Custo Variável", "Custo Fixo", "Margem de Lucro", "Data de Processamento"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Custo> custos = custoService.getAllCustos();
        for (Custo custo : custos) {
            model.addRow(new Object[]{
                    custo.getImposto(),
                    custo.getFrete(),
                    custo.getCustoVariavel(),
                    custo.getCustoFixo(),
                    custo.getMargemLucro(),
                    custo.getDataProcessamento()
            });
        }

        table.setModel(model);
    }
}
