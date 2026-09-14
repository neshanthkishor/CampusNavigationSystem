import javax.swing.*;
import java.awt.*;

public class ReportIssueFrame extends JFrame {

    private JTextField nameField;
    private JComboBox<String> locationBox;
    private JComboBox<String> issueTypeBox;
    private JTextArea descriptionArea;

    public ReportIssueFrame() {
        setTitle("Report Campus Issue");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createUI();
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 248, 252));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel heading = new JLabel("Report Campus Issue");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 26));
        heading.setForeground(new Color(15, 23, 42));

        JLabel subtitle = new JLabel(
                "Help us improve the campus by reporting an issue"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(71, 85, 105));

        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.setLayout(
                new BoxLayout(headingPanel, BoxLayout.Y_AXIS)
        );

        headingPanel.add(heading);
        headingPanel.add(Box.createVerticalStrut(5));
        headingPanel.add(subtitle);

        mainPanel.add(headingPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        BorderFactory.createEmptyBorder(
                                20, 20, 20, 20
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Your Name:");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(250, 32));

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        String[] locations = {
                "Main Block",
                "Library",
                "Canteen",
                "Computer Science Block",
                "Mechanical Block",
                "Parking Area",
                "Playground",
                "Hostel",
                "Other"
        };

        locationBox = new JComboBox<>(locations);
        locationBox.setPreferredSize(new Dimension(250, 32));

        JLabel issueTypeLabel = new JLabel("Issue Type:");
        issueTypeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        String[] issueTypes = {
                "Damaged Sign",
                "Blocked Path",
                "Broken Light",
                "Water Leakage",
                "Cleanliness Issue",
                "Safety Issue",
                "Other"
        };

        issueTypeBox = new JComboBox<>(issueTypes);
        issueTypeBox.setPreferredSize(new Dimension(250, 32));

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        descriptionArea = new JTextArea(5, 25);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JScrollPane descriptionScrollPane =
                new JScrollPane(descriptionArea);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(locationLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(locationBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(issueTypeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        formPanel.add(issueTypeBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(descriptionLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        formPanel.add(descriptionScrollPane, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );
        buttonPanel.setOpaque(false);

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton submitButton = new JButton("Submit Issue");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(8, 145, 178));
        submitButton.setFocusPainted(false);

        clearButton.addActionListener(e -> clearForm());
        submitButton.addActionListener(e -> submitIssue());

        buttonPanel.add(clearButton);
        buttonPanel.add(submitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void submitIssue() {
        String name = nameField.getText().trim();
        String description = descriptionArea.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your name.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please describe the issue.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String location = locationBox.getSelectedItem().toString();
        String issueType = issueTypeBox.getSelectedItem().toString();

        JOptionPane.showMessageDialog(
                this,
                "Issue submitted successfully!\n\n"
                        + "Location: " + location
                        + "\nIssue Type: " + issueType,
                "Report Submitted",
                JOptionPane.INFORMATION_MESSAGE
        );

        clearForm();
    }

    private void clearForm() {
        nameField.setText("");
        locationBox.setSelectedIndex(0);
        issueTypeBox.setSelectedIndex(0);
        descriptionArea.setText("");
    }
}