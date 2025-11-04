package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.model.Pessoa;
import br.com.PdvFrontEnd.service.PessoaService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PessoaList extends JFrame {
    private final PessoaService pessoaService;
    private JTable table;

    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color TABLE_HEADER_COLOR = new Color(52, 73, 94); // Azul escuro para cabeçalho da tabela
    private static final Color TABLE_SELECTION_COLOR = new Color(142, 68, 173); // Roxo para seleção da tabela
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover

    public PessoaList(PessoaService service) {
        this.pessoaService = service;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gerenciamento de Pessoas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("GERENCIAMENTO DE PESSOAS", SwingConstants.CENTER);
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

        btnAdicionar.addActionListener(this::adicionarPessoa);
        btnEditar.addActionListener(this::editarPessoa);
        btnRemover.addActionListener(this::removerPessoa);
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

    private void adicionarPessoa(ActionEvent e) {
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField dataField = new JTextField();
        String[] tipos = {"FISICA", "JURIDICA"};
        JComboBox<String> tipoBox = new JComboBox<>(tipos);
        String[] roles = {"FRENTISTA", "USUÁRIO", "ADMIN"};
        JComboBox<String> roleBox = new JComboBox<>(roles);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.add(new JLabel("Nome:"));
        form.add(nomeField);
        form.add(new JLabel("CPF:"));
        form.add(cpfField);
        form.add(new JLabel("Data de Nascimento (YYYY-MM-DD):"));
        form.add(dataField);
        form.add(new JLabel("Tipo:"));
        form.add(tipoBox);
        form.add(new JLabel("Função:"));
        form.add(roleBox);

        int result = JOptionPane.showConfirmDialog(this, form, "Nova Pessoa", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String dataNasc = dataField.getText();
            String tipo = (String) tipoBox.getSelectedItem();
            String role = (String) roleBox.getSelectedItem();

            if (!nome.isEmpty() && !cpf.isEmpty() && !dataNasc.isEmpty() && tipo != null) {
                pessoaService.addPessoa(new Pessoa(nome, cpf, dataNasc, tipo, role));
                atualizarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void editarPessoa(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Pegar a pessoa da lista usando o índice da linha
            List<Pessoa> pessoas = pessoaService.listPessoas();
            if (selectedRow < pessoas.size()) {
                Pessoa pessoa = pessoas.get(selectedRow);

                JTextField nomeField = new JTextField(pessoa.getNome());
                JTextField cpfField = new JTextField(pessoa.getCpf());
                JTextField dataField = new JTextField(pessoa.getDataNascimento());
                String[] tipos = {"FISICA", "JURIDICA"};
                JComboBox<String> tipoBox = new JComboBox<>(tipos);
                tipoBox.setSelectedItem(pessoa.getTipo());
                String[] roles = {"FRENTISTA", "USUÁRIO", "ADMIN"};
                JComboBox<String> roleBox = new JComboBox<>(roles);
                roleBox.setSelectedItem(pessoa.getRole());

                JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
                form.add(new JLabel("Nome:"));
                form.add(nomeField);
                form.add(new JLabel("CPF:"));
                form.add(cpfField);
                form.add(new JLabel("Data de Nascimento (YYYY-MM-DD):"));
                form.add(dataField);
                form.add(new JLabel("Tipo:"));
                form.add(tipoBox);
                form.add(new JLabel("Função:"));
                form.add(roleBox);

                int result = JOptionPane.showConfirmDialog(this, form, "Editar Pessoa", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String nome = nomeField.getText();
                    String cpf = cpfField.getText();
                    String dataNasc = dataField.getText();
                    String tipo = (String) tipoBox.getSelectedItem();
                    String role = (String) roleBox.getSelectedItem();

                    if (!nome.isEmpty() && !cpf.isEmpty() && !dataNasc.isEmpty() && tipo != null) {
                        pessoa.setNome(nome);
                        pessoa.setCpf(cpf);
                        pessoa.setDataNascimento(dataNasc);
                        pessoa.setTipo(tipo);
                        pessoa.setRole(role);
                        pessoaService.updatePessoa(pessoa.getId(), pessoa);
                        atualizarTabela();
                    } else {
                        JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para editar!");
        }
    }

    private void removerPessoa(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Pegar a pessoa da lista usando o índice da linha
            List<Pessoa> pessoas = pessoaService.listPessoas();
            if (selectedRow < pessoas.size()) {
                Pessoa pessoa = pessoas.get(selectedRow);
                if (pessoa.getId() != null) {
                    pessoaService.removePessoa(pessoa.getId());
                    atualizarTabela();
                } else {
                    JOptionPane.showMessageDialog(this, "Pessoa não possui ID válido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma pessoa para remover!");
        }
    }

    void atualizarTabela() {
        String[] colunas = {"Nome", "CPF", "Data de Nascimento", "Tipo", "Função"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Pessoa p : pessoaService.listPessoas()) {
            model.addRow(new Object[]{
                    p.getNome(),
                    p.getCpf(),
                    p.getDataNascimento(),
                    p.getTipo(),
                    p.getRole()
            });
        }

        table.setModel(model);
    }
}
