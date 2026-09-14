import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CampusMapFrame extends JFrame {

    private final JLabel locationTitle;
    private final JLabel locationDetails;

    public CampusMapFrame() {
        setTitle("Interactive Campus Map");
        setSize(1050, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 42, 78));
        header.setBorder(new EmptyBorder(22, 30, 22, 30));

        JLabel title = new JLabel("Interactive Campus Map");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Select a location to view details");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(new Color(215, 230, 250));

        JPanel headerText = new JPanel();
        headerText.setOpaque(false);
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.add(title);
        headerText.add(Box.createVerticalStrut(5));
        headerText.add(subtitle);

        header.add(headerText, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(20, 20));
        content.setBackground(new Color(244, 247, 252));
        content.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel mapPanel = new JPanel(null);
        mapPanel.setBackground(new Color(224, 239, 220));
        mapPanel.setBorder(BorderFactory.createLineBorder(
                new Color(170, 190, 165), 2
        ));

        JLabel road1 = createRoad("Main Road", 20, 270, 690, 35);
        JLabel road2 = createRoad("Campus Road", 320, 20, 35, 520);

        mapPanel.add(road1);
        mapPanel.add(road2);

        addLocationButton(
                mapPanel,
                "Main Gate",
                55,
                40,
                new Color(80, 120, 80),
                "Main Gate",
                "Entry point, security office and visitor parking"
        );

        addLocationButton(
                mapPanel,
                "CSE Block",
                440,
                65,
                new Color(37, 128, 218),
                "CSE Block",
                "Classrooms, computer laboratories and faculty rooms"
        );

        addLocationButton(
                mapPanel,
                "Library",
                80,
                150,
                new Color(115, 85, 180),
                "Central Library",
                "Reading hall, digital library and book issue counter"
        );

        addLocationButton(
                mapPanel,
                "Cafeteria",
                500,
                180,
                new Color(220, 145, 45),
                "College Cafeteria",
                "Food counter, drinking water and seating area"
        );

        addLocationButton(
                mapPanel,
                "Seminar Hall",
                80,
                370,
                new Color(200, 85, 85),
                "Seminar Hall",
                "Auditorium, projector and presentation facilities"
        );

        addLocationButton(
                mapPanel,
                "Medical Room",
                500,
                370,
                new Color(210, 70, 100),
                "Medical Room",
                "First aid, nurse assistance and emergency support"
        );

        addLocationButton(
                mapPanel,
                "College Ground",
                390,
                480,
                new Color(50, 145, 90),
                "College Ground",
                "Sports area and emergency assembly point"
        );

        content.add(mapPanel, BorderLayout.CENTER);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 223, 235)),
                new EmptyBorder(25, 25, 25, 25)
        ));
        detailsPanel.setPreferredSize(new Dimension(270, 0));
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));

        JLabel detailsHeading = new JLabel("Location Details");
        detailsHeading.setFont(new Font("Arial", Font.BOLD, 20));
        detailsHeading.setForeground(new Color(15, 42, 78));
        detailsHeading.setAlignmentX(Component.LEFT_ALIGNMENT);

        locationTitle = new JLabel("Select a place");
        locationTitle.setFont(new Font("Arial", Font.BOLD, 22));
        locationTitle.setForeground(new Color(37, 128, 218));
        locationTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        locationDetails = new JLabel(
                "<html>Click any location on the map to view its information.</html>"
        );
        locationDetails.setFont(new Font("Arial", Font.PLAIN, 15));
        locationDetails.setForeground(new Color(75, 95, 120));
        locationDetails.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton routeButton = new JButton("Plan Route");
        routeButton.setFont(new Font("Arial", Font.BOLD, 14));
        routeButton.setForeground(Color.WHITE);
        routeButton.setBackground(new Color(37, 128, 218));
        routeButton.setFocusPainted(false);
        routeButton.setBorderPainted(false);
        routeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        routeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Shortest route planning will be connected next.",
                    "Route Planner",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        detailsPanel.add(detailsHeading);
        detailsPanel.add(Box.createVerticalStrut(30));
        detailsPanel.add(locationTitle);
        detailsPanel.add(Box.createVerticalStrut(15));
        detailsPanel.add(locationDetails);
        detailsPanel.add(Box.createVerticalGlue());
        detailsPanel.add(routeButton);

        content.add(detailsPanel, BorderLayout.EAST);

        add(content, BorderLayout.CENTER);
    }

    private JLabel createRoad(
            String text,
            int x,
            int y,
            int width,
            int height
    ) {
        JLabel road = new JLabel(text, SwingConstants.CENTER);
        road.setBounds(x, y, width, height);
        road.setOpaque(true);
        road.setBackground(new Color(205, 205, 205));
        road.setForeground(new Color(90, 90, 90));
        road.setFont(new Font("Arial", Font.BOLD, 12));
        return road;
    }

    private void addLocationButton(
            JPanel panel,
            String buttonText,
            int x,
            int y,
            Color color,
            String title,
            String details
    ) {
        JButton button = new JButton(buttonText);
        button.setBounds(x, y, 150, 65);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addActionListener(e -> {
            locationTitle.setText(title);
            locationDetails.setText(
                    "<html><b>Information:</b><br><br>"
                            + details
                            + "<br><br><b>Status:</b> Available</html>"
            );
        });

        panel.add(button);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CampusMapFrame().setVisible(true);
        });
    }
}