package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Preco;
import br.com.PdvFrontEnd.service.PrecoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.util.List;

public class PrecoList extends JFrame {
    private final PrecoService precoService;
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

    public PrecoList(PrecoService service) {
        this.precoService = service;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Preços");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("GERENCIAMENTO DE PREÇOS", SwingConstants.CENTER);
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
            PrecoForm form = new PrecoForm(precoService, this);
            form.setVisible(true);
        });

        btnEditar.addActionListener(e -> editarPreco(e));
        btnRemover.addActionListener(e -> removerPreco(e));
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

    private void editarPreco(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<Preco> precos = precoService.getAllPrecos();
            if (selectedRow < precos.size()) {
                Preco preco = precos.get(selectedRow);
                if (preco.getId() != null) {
                    PrecoForm form = new PrecoForm(precoService, this, preco);
                    form.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Preço não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um preço para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void removerPreco(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            List<Preco> precos = precoService.getAllPrecos();
            if (selectedRow < precos.size()) {
                Preco preco = precos.get(selectedRow);
                if (preco.getId() != null) {
                    precoService.removePreco(preco.getId());
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Preço não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um preço para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void atualizarTabela() {
        String[] colunas = {"Valor", "Data de Alteração", "Hora de Alteração"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Preco> precos = precoService.getAllPrecos();
        for (Preco preco : precos) {
            model.addRow(new Object[]{
                    preco.getValor(),
                    preco.getDataAlteracao(),
                    preco.getHoraAlteracao()
            });
        }

        table.setModel(model);
    }
}
