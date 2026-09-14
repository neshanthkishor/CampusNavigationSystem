import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final Color navy = new Color(15, 23, 42);
    private final Color background = new Color(245, 248, 252);

    public DashboardFrame(String studentName) {
        setTitle("Campus Navigation System");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(studentName);
    }

    private void createUI(String studentName) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(background);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(navy);
        header.setBorder(
                BorderFactory.createEmptyBorder(25, 35, 25, 35)
        );

        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.setLayout(
                new BoxLayout(headingPanel, BoxLayout.Y_AXIS)
        );

        JLabel title = new JLabel("Campus Navigation System");
        title.setFont(new Font("Segoe UI", Font.BOLD, 27));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel(
                "Smart navigation for a smarter campus"
        );
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(203, 213, 225));

        headingPanel.add(title);
        headingPanel.add(Box.createVerticalStrut(5));
        headingPanel.add(subtitle);

        JLabel welcome = new JLabel("Welcome, " + studentName);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        welcome.setForeground(Color.WHITE);

        JButton profileButton = new JButton("My Profile");
        profileButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        profileButton.setForeground(Color.WHITE);
        profileButton.setBackground(new Color(37, 99, 235));
        profileButton.setFocusPainted(false);
        profileButton.setBorder(
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        );

        profileButton.addActionListener(e -> {
            new ProfileFrame(studentName).setVisible(true);
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(220, 38, 38));
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        );

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });

        header.add(headingPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 15, 0)
        );
        rightPanel.setOpaque(false);
        rightPanel.add(welcome);
        rightPanel.add(profileButton);
        rightPanel.add(logoutButton);

        header.add(rightPanel, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(
                new GridLayout(2, 3, 20, 20)
        );
        contentPanel.setBackground(background);
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(35, 40, 35, 40)
        );

        contentPanel.add(createCard(
                "Smart Find Location",
                "Search for campus buildings and facilities",
                "Find Location",
                new Color(37, 99, 235),
                () -> new LocationSearchFrame().setVisible(true)
        ));

        contentPanel.add(createCard(
                "Interactive Campus Map",
                "View important campus locations visually",
                "Open Map",
                new Color(124, 58, 237),
                () -> new CampusMapFrame().setVisible(true)
        ));

        contentPanel.add(createCard(
                "Shortest Route",
                "Find the shortest walking path between locations",
                "Plan Route",
                new Color(5, 150, 105),
                () -> new RoutePlannerFrame().setVisible(true)
        ));

        contentPanel.add(createCard(
                "Emergency Safe Route",
                "Find the nearest safe point during an emergency",
                "Emergency Route",
                new Color(220, 38, 38),
                () -> new EmergencyRouteFrame().setVisible(true)
        ));

        contentPanel.add(createCard(
                "Class Schedule",
                "View your daily class timetable",
                "View Schedule",
                new Color(234, 88, 12),
                () -> new ClassScheduleFrame().setVisible(true)
        ));

        contentPanel.add(createCard(
                "Report Campus Issue",
                "Report damaged signs, blocked paths or other issues",
                "Report Issue",
                new Color(8, 145, 178),
                () -> new ReportIssueFrame().setVisible(true)
        ));

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createCard(
            String titleText,
            String descriptionText,
            String buttonText,
            Color cardColor,
            Runnable action
    ) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                25, 25, 25, 25
                        )
                )
        );

        card.setLayout(
                new BoxLayout(card, BoxLayout.Y_AXIS)
        );

        JPanel colorBar = new JPanel();
        colorBar.setBackground(cardColor);
        colorBar.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 7)
        );
        colorBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel(titleText);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(15, 23, 42));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea description = new JTextArea(descriptionText);
        description.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        description.setForeground(new Color(71, 85, 105));
        description.setBackground(Color.WHITE);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        description.setRows(3);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton button = new JButton(buttonText);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(cardColor);
        button.setFocusPainted(false);
        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 18, 10, 18
                )
        );
        button.setAlignmentX(Component.LEFT_ALIGNMENT);

        button.addActionListener(e -> action.run());

        card.add(colorBar);
        card.add(Box.createVerticalStrut(20));
        card.add(title);
        card.add(Box.createVerticalStrut(12));
        card.add(description);
        card.add(Box.createVerticalGlue());
        card.add(Box.createVerticalStrut(15));
        card.add(button);

        return card;
    }
}