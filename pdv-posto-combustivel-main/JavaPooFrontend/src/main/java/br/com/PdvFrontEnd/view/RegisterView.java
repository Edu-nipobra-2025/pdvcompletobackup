package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.util.SessionManager;

import javax.swing.*;
import java.awt.*;

public class RegisterView extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80);
    private static final Color ACCENT_COLOR = new Color(230, 126, 34);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185);

    private JTextField txtNome;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private SessionManager sessionManager;

    public RegisterView() {
        this.sessionManager = SessionManager.getInstance();
        initComponents();
    }

    private void initComponents() {
        setTitle("Cadastro - Sistema PDV");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(SECONDARY_COLOR);
        setLayout(new BorderLayout(10, 10));

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(SECONDARY_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Título
        JLabel lblTitle = new JLabel("Cadastro");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Campo Nome
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setForeground(TEXT_COLOR);
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblNome);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtNome = new JTextField(20);
        txtNome.setMaximumSize(new Dimension(300, 35));
        txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNome.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtNome);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Username
        JLabel lblUsername = new JLabel("Usuário:");
        lblUsername.setForeground(TEXT_COLOR);
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtUsername = new JTextField(20);
        txtUsername.setMaximumSize(new Dimension(300, 35));
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtUsername);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Password
        JLabel lblPassword = new JLabel("Senha:");
        lblPassword.setForeground(TEXT_COLOR);
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtPassword = new JPasswordField(20);
        txtPassword.setMaximumSize(new Dimension(300, 35));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo Confirmar Password
        JLabel lblConfirmPassword = new JLabel("Confirmar Senha:");
        lblConfirmPassword.setForeground(TEXT_COLOR);
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        txtConfirmPassword = new JPasswordField(20);
        txtConfirmPassword.setMaximumSize(new Dimension(300, 35));
        txtConfirmPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtConfirmPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(txtConfirmPassword);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Botão Cadastrar
        JButton btnRegister = createStyledButton("Cadastrar");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setMaximumSize(new Dimension(300, 40));
        btnRegister.addActionListener(e -> handleRegister());
        mainPanel.add(btnRegister);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão Voltar ao Login
        JButton btnBackToLogin = createStyledButton("Já tenho cadastro");
        btnBackToLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBackToLogin.setMaximumSize(new Dimension(300, 40));
        btnBackToLogin.setBackground(ACCENT_COLOR);
        btnBackToLogin.addActionListener(e -> {
            if (sessionManager.userExists()) {
                new LoginView().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Você precisa se cadastrar primeiro!",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        mainPanel.add(btnBackToLogin);

        add(mainPanel, BorderLayout.CENTER);

        // Enter para cadastrar
        txtConfirmPassword.addActionListener(e -> handleRegister());
    }

    private void handleRegister() {
        String nome = txtNome.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        // Validações
        if (nome.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this,
                    "O usuário deve ter no mínimo 3 caracteres!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this,
                    "A senha deve ter no mínimo 4 caracteres!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "As senhas não conferem!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            txtConfirmPassword.setText("");
            return;
        }

        // Salva as credenciais
        sessionManager.saveCredentials(username, password);

        JOptionPane.showMessageDialog(this,
                "Cadastro realizado com sucesso!\n" +
                "Nome: " + nome + "\n" +
                "Usuário: " + username,
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);

        // Vai para a tela de login
        new LoginView().setVisible(true);
        dispose();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(BUTTON_HOVER_COLOR);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.getBackground().equals(ACCENT_COLOR)) {
                    button.setBackground(ACCENT_COLOR);
                } else {
                    button.setBackground(PRIMARY_COLOR);
                }
            }
        });

        return button;
    }
}

