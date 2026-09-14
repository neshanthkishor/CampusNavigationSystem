import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class EmergencyRouteFrame extends JFrame {

    private JComboBox<String> locationBox;
    private JLabel safePointLabel;
    private JLabel routeLabel;
    private JTextArea instructionArea;

    private final Map<String, String> safePoints = new HashMap<>();
    private final Map<String, String> routes = new HashMap<>();

    public EmergencyRouteFrame() {
        setTitle("Emergency Safe Route");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        safePoints.put("Main Gate", "Security Office");
        safePoints.put("CSE Block", "Main Gate Security Office");
        safePoints.put("Central Library", "Main Gate Security Office");
        safePoints.put("Cafeteria", "College Ground Assembly Point");
        safePoints.put("Seminar Hall", "College Ground Assembly Point");
        safePoints.put("Medical Room", "Medical Room");
        safePoints.put("College Ground", "College Ground Assembly Point");

        routes.put("Main Gate", "Move straight to the Security Office");
        routes.put("CSE Block", "Exit the CSE Block and move towards the Main Gate");
        routes.put("Central Library", "Follow the main pathway towards the Main Gate");
        routes.put("Cafeteria", "Move towards the College Ground using the eastern pathway");
        routes.put("Seminar Hall", "Exit through the nearest door and move to the College Ground");
        routes.put("Medical Room", "Stay near the Medical Room and follow staff instructions");
        routes.put("College Ground", "Remain at the assembly point and wait for instructions");

        createUI();
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 248, 252));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(153, 27, 27));
        header.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("Emergency Safe Route");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JLabel subtitle = new JLabel("Find the nearest safe point during an emergency");
        subtitle.setForeground(new Color(254, 226, 226));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        headingPanel.add(title);
        headingPanel.add(Box.createVerticalStrut(5));
        headingPanel.add(subtitle);

        header.add(headingPanel, BorderLayout.WEST);
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(245, 248, 252));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JLabel locationTitle = new JLabel("Select your current location");
        locationTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        locationTitle.setForeground(new Color(31, 41, 55));
        locationTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(locationTitle);
        contentPanel.add(Box.createVerticalStrut(12));

        locationBox = new JComboBox<>(new String[]{
                "Main Gate",
                "CSE Block",
                "Central Library",
                "Cafeteria",
                "Seminar Hall",
                "Medical Room",
                "College Ground"
        });

        locationBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        locationBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        locationBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(locationBox);
        contentPanel.add(Box.createVerticalStrut(20));

        JButton findButton = new JButton("Find Safe Route");
        findButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        findButton.setForeground(Color.WHITE);
        findButton.setBackground(new Color(185, 28, 28));
        findButton.setFocusPainted(false);
        findButton.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        findButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(findButton);
        contentPanel.add(Box.createVerticalStrut(30));

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(254, 202, 202), 2),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 260));

        JLabel safePointTitle = new JLabel("Nearest Safe Point");
        safePointTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        safePointTitle.setForeground(new Color(127, 29, 29));

        safePointLabel = new JLabel("Select a location to find the safe point");
        safePointLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        safePointLabel.setForeground(new Color(22, 101, 52));

        JLabel routeTitle = new JLabel("Suggested Route");
        routeTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        routeTitle.setForeground(new Color(127, 29, 29));

        routeLabel = new JLabel("Route will appear here");
        routeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        routeLabel.setForeground(new Color(31, 41, 55));

        JLabel instructionTitle = new JLabel("Emergency Instruction");
        instructionTitle.setFont(new Font("Segoe UI", Font.BOLD, 15));
        instructionTitle.setForeground(new Color(127, 29, 29));

        instructionArea = new JTextArea(
                "Stay calm. Do not use elevators. Follow security staff instructions."
        );
        instructionArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        instructionArea.setForeground(new Color(55, 65, 81));
        instructionArea.setBackground(Color.WHITE);
        instructionArea.setLineWrap(true);
        instructionArea.setWrapStyleWord(true);
        instructionArea.setEditable(false);
        instructionArea.setRows(2);

        resultPanel.add(safePointTitle);
        resultPanel.add(Box.createVerticalStrut(5));
        resultPanel.add(safePointLabel);
        resultPanel.add(Box.createVerticalStrut(15));
        resultPanel.add(routeTitle);
        resultPanel.add(Box.createVerticalStrut(5));
        resultPanel.add(routeLabel);
        resultPanel.add(Box.createVerticalStrut(15));
        resultPanel.add(instructionTitle);
        resultPanel.add(Box.createVerticalStrut(5));
        resultPanel.add(instructionArea);

        contentPanel.add(resultPanel);
        contentPanel.add(Box.createVerticalStrut(25));

        JLabel warningLabel = new JLabel(
                "Emergency number: Contact campus security immediately."
        );
        warningLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        warningLabel.setForeground(new Color(153, 27, 27));
        warningLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contentPanel.add(warningLabel);

        findButton.addActionListener(e -> findSafeRoute());

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void findSafeRoute() {
        String currentLocation = (String) locationBox.getSelectedItem();

        String safePoint = safePoints.get(currentLocation);
        String route = routes.get(currentLocation);

        safePointLabel.setText(safePoint);
        routeLabel.setText(route);

        instructionArea.setText(
                "Stay calm and move carefully. Do not run or push others. " +
                "Follow the instructions given by campus security and move to the safe point."
        );
    }
}