import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class LocationSearchFrame extends JFrame {

    private final JTextField searchField;
    private final JLabel resultTitle;
    private final JLabel resultDetails;
    private final JLabel resultFacilities;

    private final Map<String, String[]> locations = new LinkedHashMap<>();

    public LocationSearchFrame() {
        setTitle("Smart Find Location - Campus Navigation System");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        locations.put("cse block", new String[]{
                "CSE Block",
                "Ground Floor and First Floor",
                "Computer laboratories, classrooms and faculty rooms",
                "5 minutes"
        });

        locations.put("library", new String[]{
                "Central Library",
                "Ground Floor",
                "Reading hall, digital library and book issue counter",
                "7 minutes"
        });

        locations.put("cafeteria", new String[]{
                "College Cafeteria",
                "Ground Floor",
                "Food counter, drinking water and seating area",
                "4 minutes"
        });

        locations.put("seminar hall", new String[]{
                "Seminar Hall",
                "First Floor",
                "Auditorium, projector and presentation facilities",
                "6 minutes"
        });

        locations.put("main gate", new String[]{
                "Main Gate",
                "Ground Level",
                "Security office, visitor entry and parking area",
                "2 minutes"
        });

        locations.put("computer lab", new String[]{
                "Computer Laboratory",
                "First Floor",
                "Desktop computers, internet access and practical classes",
                "8 minutes"
        });

        locations.put("medical room", new String[]{
                "Medical Room",
                "Ground Floor",
                "First aid, nurse assistance and emergency support",
                "5 minutes"
        });

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 42, 78));
        header.setBorder(new EmptyBorder(22, 30, 22, 30));

        JLabel heading = new JLabel("Smart Find Location");
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 28));

        JLabel subtitle = new JLabel("Find important places inside your campus");
        subtitle.setForeground(new Color(210, 225, 245));
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        headingPanel.add(heading);
        headingPanel.add(Box.createVerticalStrut(6));
        headingPanel.add(subtitle);

        header.add(headingPanel, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(244, 247, 252));
        mainPanel.setBorder(new EmptyBorder(25, 30, 25, 30));

        JPanel searchPanel = new JPanel(new BorderLayout(10, 10));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 223, 235)),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel searchLabel = new JLabel("Search campus location");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchLabel.setForeground(new Color(15, 42, 78));

        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 205, 225)),
                new EmptyBorder(10, 12, 10, 12)
        ));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(37, 128, 218));
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setPreferredSize(new Dimension(120, 45));

        JPanel inputPanel = new JPanel(new BorderLayout(10, 10));
        inputPanel.setOpaque(false);
        inputPanel.add(searchField, BorderLayout.CENTER);
        inputPanel.add(searchButton, BorderLayout.EAST);

        searchPanel.add(searchLabel, BorderLayout.NORTH);
        searchPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel suggestionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        suggestionsPanel.setOpaque(false);

        String[] suggestions = {
                "CSE Block",
                "Library",
                "Cafeteria",
                "Seminar Hall",
                "Main Gate",
                "Medical Room"
        };

        for (String suggestion : suggestions) {
            JButton suggestionButton = new JButton(suggestion);
            suggestionButton.setFont(new Font("Arial", Font.PLAIN, 12));
            suggestionButton.setBackground(new Color(232, 241, 252));
            suggestionButton.setForeground(new Color(15, 75, 130));
            suggestionButton.setFocusPainted(false);
            suggestionButton.setBorderPainted(false);
            suggestionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            suggestionButton.addActionListener(e -> {
                searchField.setText(suggestion);
                searchLocation();
            });

            suggestionsPanel.add(suggestionButton);
        }

        searchPanel.add(suggestionsPanel, BorderLayout.SOUTH);

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 223, 235)),
                new EmptyBorder(25, 25, 25, 25)
        ));
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JLabel resultHeading = new JLabel("Location Details");
        resultHeading.setFont(new Font("Arial", Font.BOLD, 20));
        resultHeading.setForeground(new Color(15, 42, 78));
        resultHeading.setAlignmentX(Component.LEFT_ALIGNMENT);

        resultTitle = new JLabel("Search for a location");
        resultTitle.setFont(new Font("Arial", Font.BOLD, 24));
        resultTitle.setForeground(new Color(37, 128, 218));
        resultTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        resultDetails = new JLabel(
                "<html>Enter a campus location above to view its details.</html>"
        );
        resultDetails.setFont(new Font("Arial", Font.PLAIN, 16));
        resultDetails.setForeground(new Color(75, 95, 120));
        resultDetails.setAlignmentX(Component.LEFT_ALIGNMENT);

        resultFacilities = new JLabel("");
        resultFacilities.setFont(new Font("Arial", Font.PLAIN, 15));
        resultFacilities.setForeground(new Color(75, 95, 120));
        resultFacilities.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton routeButton = new JButton("Plan Route to This Location");
        routeButton.setFont(new Font("Arial", Font.BOLD, 14));
        routeButton.setForeground(Color.WHITE);
        routeButton.setBackground(new Color(37, 128, 218));
        routeButton.setFocusPainted(false);
        routeButton.setBorderPainted(false);
        routeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        routeButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        routeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "Route planning will be connected in the next step.",
                    "Route Feature",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        resultPanel.add(resultHeading);
        resultPanel.add(Box.createVerticalStrut(30));
        resultPanel.add(resultTitle);
        resultPanel.add(Box.createVerticalStrut(15));
        resultPanel.add(resultDetails);
        resultPanel.add(Box.createVerticalStrut(15));
        resultPanel.add(resultFacilities);
        resultPanel.add(Box.createVerticalStrut(30));
        resultPanel.add(routeButton);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchLocation());

        searchField.addActionListener(e -> searchLocation());
    }

    private void searchLocation() {
        String searchText = searchField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a location name.",
                    "Search Required",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String[] location = locations.get(searchText);

        if (location == null) {
            for (Map.Entry<String, String[]> entry : locations.entrySet()) {
                if (entry.getKey().contains(searchText)
                        || entry.getValue()[0].toLowerCase().contains(searchText)) {
                    location = entry.getValue();
                    break;
                }
            }
        }

        if (location == null) {
            resultTitle.setText("Location Not Found");
            resultDetails.setText(
                    "<html>No matching location was found.<br>Please try another campus place.</html>"
            );
            resultFacilities.setText("");
            return;
        }

        resultTitle.setText(location[0]);

        resultDetails.setText(
                "<html><b>Floor:</b> " + location[1]
                        + "<br><br><b>Nearby Facilities:</b> " + location[2]
                        + "</html>"
        );

        resultFacilities.setText(
                "<html><b>Estimated Walking Time:</b> " + location[3]
                        + "<br><br><b>Status:</b> Location available</html>"
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LocationSearchFrame().setVisible(true);
        });
    }
}