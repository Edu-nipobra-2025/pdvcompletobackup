package br.com.PdvFrontEnd.view;

import br.com.PdvFrontEnd.service.*;
import br.com.PdvFrontEnd.util.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class MainApp {
    // Cores para a nova interface
    private static final Color PRIMARY_COLOR = new Color(52, 152, 219); // Azul
    private static final Color SECONDARY_COLOR = new Color(44, 62, 80); // Azul escuro quase preto
    private static final Color ACCENT_COLOR = new Color(230, 126, 34); // Laranja
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color BUTTON_HOVER_COLOR = new Color(41, 128, 185); // Azul mais escuro para hover
    private static final Color ACTIVE_COLOR = new Color(46, 204, 113); // Verde
    private static final Color INACTIVE_COLOR = new Color(231, 76, 60); // Vermelho

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // Inicia o sistema pelo login
            SessionManager sessionManager = SessionManager.getInstance();

            // Se não existir usuário cadastrado, abre a tela de cadastro
            if (!sessionManager.userExists()) {
                new RegisterView().setVisible(true);
            } else {
                new LoginView().setVisible(true);
            }
        });
    }

    public static void showMainApp() {
        EventQueue.invokeLater(() -> {
            JFrame mainFrame = new JFrame("Gerenciamento PDV");
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(800, 600);
            mainFrame.getContentPane().setBackground(SECONDARY_COLOR);
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setLayout(new BorderLayout(10, 10));

            // Painel de Botões à Esquerda
            JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));
            buttonPanel.setBackground(SECONDARY_COLOR);
            buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            // Adicionar botões de gerenciamento
            buttonPanel.add(createStyledButton("Gerenciar Pessoas", e -> new PessoaList(new PessoaService()).setVisible(true)));
            buttonPanel.add(createStyledButton("Gerenciar Preços", e -> new PrecoList(new PrecoService()).setVisible(true)));
            buttonPanel.add(createStyledButton("Gerenciar Produtos", e -> new ProdutoList(new ProdutoService()).setVisible(true)));
            buttonPanel.add(createStyledButton("Gerenciar Custos", e -> new CustoList(new CustoService()).setVisible(true)));
            buttonPanel.add(createStyledButton("Gerenciar Estoques", e -> new EstoqueList(new EstoqueService()).setVisible(true)));
            buttonPanel.add(createStyledButton("Gerenciar Acessos", e -> new AcessoList(new AcessoService()).setVisible(true)));
            buttonPanel.add(createStyledButton("Gerenciar Contatos", e -> new ContatoList(new ContatoService()).setVisible(true)));

            mainFrame.add(buttonPanel, BorderLayout.WEST);

            // Painel Central com as Bombas
            JPanel pumpsPanel = new JPanel(new GridLayout(1, 3, 20, 20));
            pumpsPanel.setBackground(SECONDARY_COLOR);
            pumpsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

            for (int i = 1; i <= 3; i++) {
                pumpsPanel.add(createPumpPanel("Bomba " + i));
            }

            mainFrame.add(pumpsPanel, BorderLayout.CENTER);

            // Legenda de Status no Canto Inferior Esquerdo
            JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
            legendPanel.setBackground(SECONDARY_COLOR);
            legendPanel.add(createStatusLabel("Ativo", ACTIVE_COLOR));
            legendPanel.add(createStatusLabel("Inativo", INACTIVE_COLOR));
            
            JPanel southPanel = new JPanel(new BorderLayout());
            southPanel.setBackground(SECONDARY_COLOR);
            southPanel.add(legendPanel, BorderLayout.WEST);

            mainFrame.add(southPanel, BorderLayout.SOUTH);

            mainFrame.setVisible(true);
        });
    }

    private static JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        button.setFocusPainted(false);
        button.addActionListener(actionListener);
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
    
    private static JPanel createPumpPanel(String name) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(SECONDARY_COLOR.darker());
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(ACCENT_COLOR, 2),
            name,
            javax.swing.border.TitledBorder.CENTER,
            javax.swing.border.TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 18),
            TEXT_COLOR
        ));

        // Status (inicialmente ativo)
        JPanel statusIndicator = new JPanel();
        statusIndicator.setBackground(ACTIVE_COLOR);
        statusIndicator.setPreferredSize(new Dimension(30, 30));
        
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        statusPanel.setOpaque(false);
        statusPanel.add(statusIndicator);

        // Botão Abastecer
        JButton refuelButton = createStyledButton("Abastecer", e -> showRefuelDialog());
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(refuelButton);

        panel.add(statusPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private static JLabel createStatusLabel(String text, Color color) {
        JLabel label = new JLabel(text);
        label.setForeground(TEXT_COLOR);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setIcon(new ColorIcon(color, 16, 16));
        label.setIconTextGap(5);
        return label;
    }

    private static void showRefuelDialog() {
        JDialog dialog = new JDialog((Frame) null, "Abastecimento", true);
        dialog.setSize(400, 500);
        dialog.getContentPane().setBackground(SECONDARY_COLOR);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(new BorderLayout(10, 10));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(SECONDARY_COLOR);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titleLabel = new JLabel("Selecione o Combustível");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Opções de Combustível
        JRadioButton gasolineButton = new JRadioButton("Gasolina (R$ 5,89/L)");
        gasolineButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gasolineButton.setForeground(TEXT_COLOR);
        gasolineButton.setOpaque(false);
        gasolineButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JRadioButton ethanolButton = new JRadioButton("Etanol (R$ 3,79/L)");
        ethanolButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        ethanolButton.setForeground(TEXT_COLOR);
        ethanolButton.setOpaque(false);
        ethanolButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup fuelGroup = new ButtonGroup();
        fuelGroup.add(gasolineButton);
        fuelGroup.add(ethanolButton);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setOpaque(false);
        radioPanel.add(gasolineButton);
        radioPanel.add(ethanolButton);
        contentPanel.add(radioPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Quantidade em Litros
        JLabel litersLabel = new JLabel("Quantidade (Litros):");
        litersLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        litersLabel.setForeground(TEXT_COLOR);
        litersLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JSpinner litersSpinner = new JSpinner(new SpinnerNumberModel(10.0, 0.1, 1000.0, 0.1));
        litersSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        litersSpinner.setMaximumSize(new Dimension(150, 30));
        litersSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(litersLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        contentPanel.add(litersSpinner);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Valor a Pagar
        JLabel priceLabel = new JLabel("Valor a Pagar: R$ 0,00");
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        priceLabel.setForeground(ACCENT_COLOR);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(priceLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Ação para atualizar o preço
        ActionListener priceUpdater = e -> {
            double liters = (double) litersSpinner.getValue();
            double pricePerLiter = 0;
            if (gasolineButton.isSelected()) {
                pricePerLiter = 5.89;
            } else if (ethanolButton.isSelected()) {
                pricePerLiter = 3.79;
            }
            double totalPrice = liters * pricePerLiter;
            priceLabel.setText("Valor a Pagar: R$ " + new DecimalFormat("#,##0.00").format(totalPrice));
        };

        gasolineButton.addActionListener(priceUpdater);
        ethanolButton.addActionListener(priceUpdater);
        litersSpinner.addChangeListener(e -> priceUpdater.actionPerformed(null));
        
        gasolineButton.setSelected(true);
        priceUpdater.actionPerformed(null);

        // Botão Finalizar Venda
        JButton finalizeButton = createStyledButton("Finalizar Venda", e -> {
            String fuelType = gasolineButton.isSelected() ? "Gasolina" : "Etanol";
            double liters = (double) litersSpinner.getValue();
            double pricePerLiter = gasolineButton.isSelected() ? 5.89 : 3.79;
            double basePrice = liters * pricePerLiter;
            double finalPrice = gasolineButton.isSelected() ? basePrice * 1.34 : basePrice;
            
            showReceiptDialog(fuelType, liters, pricePerLiter, basePrice, finalPrice);
            dialog.dispose();
        });
        finalizeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(SECONDARY_COLOR);
        bottomPanel.add(finalizeButton);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    private static void showReceiptDialog(String fuelType, double liters, double pricePerLiter, double basePrice, double finalPrice) {
        JDialog receiptDialog = new JDialog((Frame) null, "Nota Fiscal", true);
        receiptDialog.setSize(450, 450);
        receiptDialog.getContentPane().setBackground(Color.WHITE);
        receiptDialog.setLocationRelativeTo(null);
        receiptDialog.setLayout(new BorderLayout());

        JTextArea receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        receiptArea.setBorder(new EmptyBorder(15, 15, 15, 15));

        String cnpj = "12.345.678/0001-90";
        String emissionDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        DecimalFormat df = new DecimalFormat("#,##0.00");

        StringBuilder receiptText = new StringBuilder();
        receiptText.append("                 POSTO PDV         \n");
        receiptText.append("CNPJ do Emissor: ").append(cnpj).append("\n");
        receiptText.append("---------------------------------------------\n");
        receiptText.append("           CUPOM FISCAL ELETRÔNICO         \n");
        receiptText.append("---------------------------------------------\n");
        receiptText.append("CNPJ/CPF do Consumidor: 123.456.789-00\n");
        receiptText.append("Data de Emissão: ").append(emissionDate).append("\n");
        receiptText.append("---------------------------------------------\n");
        receiptText.append(String.format("%-13s %-5s %-3s %-10s %-10s\n", "PRODUTO", "QTD", "UN", "VL UNIT", "VL TOTAL"));
        receiptText.append(String.format("%-13s %-5.2f %-3s %-10s %-10s\n", 
            fuelType, liters, "L", df.format(pricePerLiter), df.format(basePrice)));
        receiptText.append("---------------------------------------------\n");
        receiptText.append(String.format("%-15s %28s\n", "Valor a Pagar:", "R$ " + df.format(basePrice)));
        if (fuelType.equals("Gasolina")) {
         receiptText.append(String.format("%-15s %28s\n", "Tributos (34%):", "R$ " + df.format(basePrice * 0.34)));
        }
        receiptText.append("---------------------------------------------\n");
        receiptText.append(String.format("%-15s %28s\n", "PREÇO FINAL:", "R$ " + df.format(finalPrice)));
        receiptText.append("---------------------------------------------\n");


        receiptArea.setText(receiptText.toString());
        receiptDialog.add(new JScrollPane(receiptArea), BorderLayout.CENTER);
        
        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> receiptDialog.dispose());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        receiptDialog.add(buttonPanel, BorderLayout.SOUTH);

        receiptDialog.setVisible(true);
    }
    
    // Classe auxiliar para criar ícones de cor
    private static class ColorIcon implements Icon {
        private final Color color;
        private final int width;
        private final int height;

        public ColorIcon(Color color, int width, int height) {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillOval(x, y, width, height);
        }

        @Override
        public int getIconWidth() {
            return width;
        }

        @Override
        public int getIconHeight() {
            return height;
        }
    }
     private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(TEXT_COLOR);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
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
}
