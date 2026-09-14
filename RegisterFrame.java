import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField nameField;
    private JTextField studentIdField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegisterFrame() {
        setTitle("Create Account - Campus Navigation System");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.setBackground(new Color(245, 247, 251));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(15, 35, 65));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(100, 55, 60, 55));

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
                "<html>Discover your campus.<br>Navigate smarter.<br>Enjoy your college life.</html>"
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
        rightPanel.setBorder(BorderFactory.createEmptyBorder(65, 75, 50, 75));

        JLabel heading = new JLabel("Create Account");
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(new Color(20, 35, 60));
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel message = new JLabel("Register to access the campus navigation system");
        message.setFont(new Font("Arial", Font.PLAIN, 15));
        message.setForeground(new Color(110, 120, 135));
        message.setAlignmentX(Component.LEFT_ALIGNMENT);

        nameField = createTextField();
        studentIdField = createTextField();
        passwordField = createPasswordField();
        confirmPasswordField = createPasswordField();

        rightPanel.add(heading);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(message);
        rightPanel.add(Box.createVerticalStrut(35));

        rightPanel.add(createLabel("Full Name"));
        rightPanel.add(nameField);
        rightPanel.add(Box.createVerticalStrut(18));

        rightPanel.add(createLabel("Student ID"));
        rightPanel.add(studentIdField);
        rightPanel.add(Box.createVerticalStrut(18));

        rightPanel.add(createLabel("Password"));
        rightPanel.add(passwordField);
        rightPanel.add(Box.createVerticalStrut(18));

        rightPanel.add(createLabel("Confirm Password"));
        rightPanel.add(confirmPasswordField);
        rightPanel.add(Box.createVerticalStrut(30));

        JButton registerButton = new JButton("Create Account");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(35, 125, 220));
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));

        registerButton.addActionListener(e -> registerUser());

        JButton backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setForeground(new Color(35, 125, 220));
        backButton.setBackground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        backButton.addActionListener(e -> dispose());

        rightPanel.add(registerButton);
        rightPanel.add(Box.createVerticalStrut(15));
        rightPanel.add(backButton);

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
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 220, 235)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private JPasswordField createPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 220, 235)),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return field;
    }

    private void registerUser() {
        String name = nameField.getText().trim();
        String studentId = studentIdField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (name.isEmpty() || studentId.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill all fields.",
                    "Missing Details",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 6 characters.",
                    "Weak Password",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean success = UserDAO.registerUser(studentId, password, name);

        if (success) {
            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
            dispose();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Student ID already exists.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}