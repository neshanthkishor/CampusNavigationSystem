import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class RoutePlannerFrame extends JFrame {

    private final JComboBox<String> startBox;
    private final JComboBox<String> destinationBox;
    private final JLabel routeResult;
    private final JLabel timeResult;

    private final Map<String, Map<String, Integer>> graph = new HashMap<>();

    private final String[] locations = {
            "Main Gate",
            "CSE Block",
            "Library",
            "Cafeteria",
            "Seminar Hall",
            "Medical Room",
            "College Ground"
    };

    public RoutePlannerFrame() {
        setTitle("Shortest Route Finder");
        setSize(850, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        createGraph();

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 42, 78));
        header.setBorder(new EmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("Shortest Route Finder");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Find the fastest path between campus locations");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 15));
        subtitle.setForeground(new Color(215, 230, 250));

        JPanel headerText = new JPanel();
        headerText.setOpaque(false);
        headerText.setLayout(new BoxLayout(headerText, BoxLayout.Y_AXIS));
        headerText.add(title);
        headerText.add(Box.createVerticalStrut(6));
        headerText.add(subtitle);

        header.add(headerText, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(244, 247, 252));
        mainPanel.setBorder(new EmptyBorder(30, 35, 30, 35));

        JPanel selectionPanel = new JPanel();
        selectionPanel.setBackground(Color.WHITE);
        selectionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 223, 235)),
                new EmptyBorder(25, 25, 25, 25)
        ));
        selectionPanel.setLayout(new GridLayout(3, 2, 15, 20));

        JLabel startLabel = createLabel("Starting Location");
        JLabel destinationLabel = createLabel("Destination");

        startBox = new JComboBox<>(locations);
        destinationBox = new JComboBox<>(locations);

        styleComboBox(startBox);
        styleComboBox(destinationBox);

        JButton findButton = new JButton("Find Shortest Route");
        findButton.setFont(new Font("Arial", Font.BOLD, 14));
        findButton.setForeground(Color.WHITE);
        findButton.setBackground(new Color(37, 128, 218));
        findButton.setFocusPainted(false);
        findButton.setBorderPainted(false);
        findButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearButton.setForeground(new Color(37, 128, 218));
        clearButton.setBackground(new Color(232, 241, 252));
        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        selectionPanel.add(startLabel);
        selectionPanel.add(destinationLabel);
        selectionPanel.add(startBox);
        selectionPanel.add(destinationBox);
        selectionPanel.add(findButton);
        selectionPanel.add(clearButton);

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(215, 223, 235)),
                new EmptyBorder(25, 25, 25, 25)
        ));
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        JLabel resultHeading = new JLabel("Route Result");
        resultHeading.setFont(new Font("Arial", Font.BOLD, 21));
        resultHeading.setForeground(new Color(15, 42, 78));
        resultHeading.setAlignmentX(Component.LEFT_ALIGNMENT);

        routeResult = new JLabel(
                "<html>Select two locations and click Find Shortest Route.</html>"
        );
        routeResult.setFont(new Font("Arial", Font.PLAIN, 17));
        routeResult.setForeground(new Color(75, 95, 120));
        routeResult.setAlignmentX(Component.LEFT_ALIGNMENT);

        timeResult = new JLabel("");
        timeResult.setFont(new Font("Arial", Font.BOLD, 17));
        timeResult.setForeground(new Color(37, 128, 218));
        timeResult.setAlignmentX(Component.LEFT_ALIGNMENT);

        resultPanel.add(resultHeading);
        resultPanel.add(Box.createVerticalStrut(30));
        resultPanel.add(routeResult);
        resultPanel.add(Box.createVerticalStrut(20));
        resultPanel.add(timeResult);

        mainPanel.add(selectionPanel, BorderLayout.NORTH);
        mainPanel.add(resultPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        findButton.addActionListener(e -> findRoute());

        clearButton.addActionListener(e -> {
            startBox.setSelectedIndex(0);
            destinationBox.setSelectedIndex(0);
            routeResult.setText(
                    "<html>Select two locations and click Find Shortest Route.</html>"
            );
            timeResult.setText("");
        });
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setForeground(new Color(15, 42, 78));
        return label;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        comboBox.setBackground(Color.WHITE);
        comboBox.setPreferredSize(new Dimension(250, 40));
    }

    private void createGraph() {
        for (String location : locations) {
            graph.put(location, new HashMap<>());
        }

        addConnection("Main Gate", "CSE Block", 5);
        addConnection("Main Gate", "Library", 7);
        addConnection("CSE Block", "Cafeteria", 4);
        addConnection("CSE Block", "Seminar Hall", 6);
        addConnection("Cafeteria", "Medical Room", 3);
        addConnection("Cafeteria", "College Ground", 5);
        addConnection("Seminar Hall", "College Ground", 4);
        addConnection("Medical Room", "College Ground", 3);
        addConnection("Library", "Seminar Hall", 5);
    }

    private void addConnection(String first, String second, int minutes) {
        graph.get(first).put(second, minutes);
        graph.get(second).put(first, minutes);
    }

    private void findRoute() {
        String start = (String) startBox.getSelectedItem();
        String destination = (String) destinationBox.getSelectedItem();

        if (start.equals(destination)) {
            routeResult.setText(
                    "<html>You are already at <b>" + start + "</b>.</html>"
            );
            timeResult.setText("Walking time: 0 minutes");
            return;
        }

        List<String> route = calculateShortestPath(start, destination);

        if (route.isEmpty()) {
            routeResult.setText("<html>No route found.</html>");
            timeResult.setText("");
            return;
        }

        int totalTime = calculateRouteTime(route);

        StringBuilder routeText = new StringBuilder("<html>");
        routeText.append("<b>Route:</b><br><br>");

        for (int i = 0; i < route.size(); i++) {
            routeText.append(route.get(i));

            if (i < route.size() - 1) {
                routeText.append("  →  ");
            }
        }

        routeText.append("</html>");

        routeResult.setText(routeText.toString());
        timeResult.setText("Estimated walking time: " + totalTime + " minutes");
    }

    private List<String> calculateShortestPath(
            String start,
            String destination
    ) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(node -> node.distance)
        );

        for (String location : locations) {
            distance.put(location, Integer.MAX_VALUE);
        }

        distance.put(start, 0);
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.distance > distance.get(current.location)) {
                continue;
            }

            if (current.location.equals(destination)) {
                break;
            }

            for (Map.Entry<String, Integer> entry :
                    graph.get(current.location).entrySet()) {

                String nextLocation = entry.getKey();
                int newDistance = current.distance + entry.getValue();

                if (newDistance < distance.get(nextLocation)) {
                    distance.put(nextLocation, newDistance);
                    previous.put(nextLocation, current.location);
                    queue.add(new Node(nextLocation, newDistance));
                }
            }
        }

        if (distance.get(destination) == Integer.MAX_VALUE) {
            return new ArrayList<>();
        }

        LinkedList<String> route = new LinkedList<>();
        String current = destination;

        while (current != null) {
            route.addFirst(current);
            current = previous.get(current);
        }

        return route;
    }

    private int calculateRouteTime(List<String> route) {
        int totalTime = 0;

        for (int i = 0; i < route.size() - 1; i++) {
            totalTime += graph
                    .get(route.get(i))
                    .get(route.get(i + 1));
        }

        return totalTime;
    }

    private static class Node {
        String location;
        int distance;

        Node(String location, int distance) {
            this.location = location;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RoutePlannerFrame().setVisible(true);
        });
    }
}