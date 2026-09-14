import javax.swing.*;
import java.awt.*;

public class ProfileFrame extends JFrame {

    public ProfileFrame(String studentName) {
        setTitle("My Profile");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createUI(studentName);
    }

    private void createUI(String studentName) {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 248, 252));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel heading = new JLabel("My Profile");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(15, 23, 42));

        JLabel subtitle = new JLabel(
                "Student information"
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

        JPanel detailsPanel = new JPanel(
                new GridLayout(4, 2, 15, 20)
        );
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        BorderFactory.createEmptyBorder(
                                30, 25, 30, 25
                        )
                )
        );

        addDetail(
                detailsPanel,
                "Student Name",
                studentName
        );

        addDetail(
                detailsPanel,
                "Course",
                "B.E. Computer Science and Engineering"
        );

        addDetail(
                detailsPanel,
                "Department",
                "Computer Science and Engineering"
        );

        addDetail(
                detailsPanel,
                "System",
                "Campus Navigation System"
        );

        mainPanel.add(detailsPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        closeButton.setBackground(new Color(37, 99, 235));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);

        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT)
        );
        buttonPanel.setOpaque(false);
        buttonPanel.add(closeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addDetail(
            JPanel panel,
            String labelText,
            String valueText
    ) {
        JLabel label = new JLabel(labelText);
        label.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        label.setForeground(new Color(71, 85, 105));

        JLabel value = new JLabel(valueText);
        value.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );
        value.setForeground(new Color(15, 23, 42));

        panel.add(label);
        panel.add(value);
    }
}