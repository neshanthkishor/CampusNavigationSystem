public class Main {

    public static void main(String[] args) {
        UserDAO.createTable();

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}