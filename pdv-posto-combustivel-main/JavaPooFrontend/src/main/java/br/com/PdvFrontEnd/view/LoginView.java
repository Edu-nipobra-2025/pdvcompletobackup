package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.util.SessionManager;
import br.com.PdvFrontEnd.service.AcessoService;
import br.com.PdvFrontEnd.model.Acesso;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80);
    private static final Color ACCENT_COLOR = new Color(230, 126, 34);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185);

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private SessionManager sessionManager;
    private AcessoService acessoService;

    public LoginView() {
        this.sessionManager = SessionManager.getInstance();
        this.acessoService = new AcessoService();
        initComponents();
    }

    private void initComponents() {
        setTitle("Login - Sistema PDV");
        setSize(400, 300);
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
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(TEXT_COLOR);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

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
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        // Botão Login
        JButton btnLogin = createStyledButton("Entrar");
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(300, 40));
        btnLogin.addActionListener(e -> handleLogin());
        mainPanel.add(btnLogin);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Botão Cadastrar
        JButton btnRegister = createStyledButton("Cadastrar");
        btnRegister.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegister.setMaximumSize(new Dimension(300, 40));
        btnRegister.setBackground(ACCENT_COLOR);
        btnRegister.addActionListener(e -> {
            new RegisterView().setVisible(true);
            dispose();
        });
        mainPanel.add(btnRegister);

        add(mainPanel, BorderLayout.CENTER);

        // Enter para fazer login
        txtPassword.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha todos os campos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sessionManager.checkCredentials(username, password)) {
            sessionManager.login(username, "1");

            // Registra o acesso no banco de dados apenas se o usuário ainda não existir
            try {
                if (!acessoService.usuarioJaExiste(username)) {
                    Acesso acesso = new Acesso(username, password);
                    acessoService.addAcesso(acesso);
                }
            } catch (Exception e) {
                // Se houver erro ao salvar o acesso, apenas loga, mas não impede o login
                System.err.println("Erro ao registrar acesso no banco: " + e.getMessage());
            }

            JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            // Abre a tela principal
            MainApp.showMainApp();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
        }
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

