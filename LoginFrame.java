import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField studentIdField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login - Campus Navigation System");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(new Color(245, 247, 251));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(15, 35, 65));
        leftPanel.setBorder(
                BorderFactory.createEmptyBorder(100, 55, 60, 55)
        );

        JLabel logo = new JLabel("CN");
        logo.setFont(new Font("Arial", Font.BOLD, 48));
        logo.setForeground(Color.WHITE);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("CAMPUS NAVIGATION");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("SYSTEM");
        subtitle.setFont(new Font("Arial", Font.BOLD, 24));
        subtitle.setForeground(new Color(90, 180, 255));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel(
                "<html>Find your way.<br>" +
                "Explore your campus.<br>" +
                "Navigate with confidence.</html>"
        );

        description.setFont(new Font("Arial", Font.PLAIN, 20));
        description.setForeground(new Color(220, 230, 245));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        leftPanel.add(logo);
        leftPanel.add(Box.createVerticalStrut(25));
        leftPanel.add(title);
        leftPanel.add(subtitle);
        leftPanel.add(Box.createVerticalStrut(35));
        leftPanel.add(description);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(
                BorderFactory.createEmptyBorder(110, 75, 70, 75)
        );

        JLabel heading = new JLabel("Welcome Back");
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(new Color(20, 35, 60));
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel message = new JLabel(
                "Sign in to continue to your campus dashboard"
        );
        message.setFont(new Font("Arial", Font.PLAIN, 15));
        message.setForeground(new Color(110, 120, 135));
        message.setAlignmentX(Component.LEFT_ALIGNMENT);

        studentIdField = createTextField();
        passwordField = createPasswordField();

        rightPanel.add(heading);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(message);
        rightPanel.add(Box.createVerticalStrut(40));

        rightPanel.add(createLabel("Student ID"));
        rightPanel.add(studentIdField);
        rightPanel.add(Box.createVerticalStrut(22));

        rightPanel.add(createLabel("Password"));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createVerticalStrut(12));

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setFont(new Font("Arial", Font.PLAIN, 13));
        showPassword.setBackground(Color.WHITE);
        showPassword.setForeground(new Color(90, 100, 115));
        showPassword.setAlignmentX(Component.LEFT_ALIGNMENT);

        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        });

        rightPanel.add(showPassword);
        rightPanel.add(Box.createVerticalStrut(30));

        JButton loginButton = new JButton("Sign In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(35, 125, 220));
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 48)
        );

        loginButton.addActionListener(e -> loginUser());

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );
        createAccountButton.setForeground(new Color(35, 125, 220));
        createAccountButton.setBackground(Color.WHITE);
        createAccountButton.setFocusPainted(false);
        createAccountButton.setBorderPainted(false);
        createAccountButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        createAccountButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Registration page will be added later."
            );
        });

        rightPanel.add(loginButton);
        rightPanel.add(Box.createVerticalStrut(18));
        rightPanel.add(createAccountButton);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(45, 55, 70));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 220, 235)
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 12, 8, 12
                        )
                )
        );

        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setEchoChar('•');
        field.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 42)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 220, 235)
                        ),
                        BorderFactory.createEmptyBorder(
                                8, 12, 8, 12
                        )
                )
        );

        return field;
    }

    private void loginUser() {
        String studentId = studentIdField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (studentId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Student ID and Password.",
                    "Missing Details",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (studentId.equals("CSE001")
                && password.equals("123456")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            DashboardFrame dashboardFrame =
                    new DashboardFrame("Bharath");

            dashboardFrame.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Student ID or Password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}