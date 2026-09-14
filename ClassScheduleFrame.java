import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClassScheduleFrame extends JFrame {

    public ClassScheduleFrame() {
        setTitle("Class Schedule");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createUI();
    }

    private void createUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 248, 252));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 23, 42));
        header.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("Class Schedule");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("View your weekly classroom timetable");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(203, 213, 225));

        JPanel headingPanel = new JPanel();
        headingPanel.setOpaque(false);
        headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.Y_AXIS));
        headingPanel.add(title);
        headingPanel.add(Box.createVerticalStrut(5));
        headingPanel.add(subtitle);

        header.add(headingPanel, BorderLayout.WEST);
        mainPanel.add(header, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(245, 248, 252));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 35, 30, 35));

        String[] columns = {
                "Day",
                "Time",
                "Subject",
                "Classroom",
                "Faculty",
                "Status"
        };

        Object[][] data = {
                {"Monday", "09:00 AM - 10:00 AM", "Data Structures", "CSE Block - Room 201", "Dr. Kumar", "Scheduled"},
                {"Monday", "10:15 AM - 11:15 AM", "Java Programming", "CSE Block - Room 202", "Ms. Priya", "Scheduled"},
                {"Tuesday", "09:00 AM - 10:00 AM", "Database Management", "CSE Block - Room 203", "Mr. Arun", "Scheduled"},
                {"Tuesday", "11:30 AM - 12:30 PM", "Computer Networks", "CSE Block - Room 204", "Dr. Meena", "Scheduled"},
                {"Wednesday", "10:15 AM - 11:15 AM", "Operating Systems", "CSE Block - Room 205", "Mr. Ravi", "Scheduled"},
                {"Thursday", "09:00 AM - 10:00 AM", "Machine Learning", "AI Lab", "Dr. Divya", "Scheduled"},
                {"Friday", "11:30 AM - 12:30 PM", "Software Engineering", "Seminar Hall", "Ms. Anitha", "Scheduled"}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(38);
        table.setGridColor(new Color(226, 232, 240));
        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(new Color(15, 23, 42));
        table.setAutoCreateRowSorter(true);

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        table.getTableHeader().setBackground(new Color(30, 64, 175));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setPreferredSize(new Dimension(100, 42));

        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(160);
        table.getColumnModel().getColumn(2).setPreferredWidth(160);
        table.getColumnModel().getColumn(3).setPreferredWidth(220);
        table.getColumnModel().getColumn(4).setPreferredWidth(130);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225))
        );

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(245, 248, 252));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel note = new JLabel(
                "Note: Please verify classroom changes with your department."
        );
        note.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        note.setForeground(new Color(71, 85, 105));

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(30, 64, 175));
        closeButton.setFocusPainted(false);
        closeButton.setBorder(
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
        );

        closeButton.addActionListener(e -> dispose());

        bottomPanel.add(note, BorderLayout.WEST);
        bottomPanel.add(closeButton, BorderLayout.EAST);

        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(contentPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
}