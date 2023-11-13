import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GC_GUIv1.0.2");
        ImageIcon icon = new ImageIcon("resources/appIcon.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.getContentPane().add(new MainMenuGUI());
        frame.pack();
        frame.setVisible(true);
    }
}