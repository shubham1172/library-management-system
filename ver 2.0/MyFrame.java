import java.awt.*;
import javax.swing.*;
/**
 * This is a basic template for all the frames created in the system.
 */
public class MyFrame extends JFrame {
    JFrame win = new JFrame();    
    public MyFrame(String title)
    {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.getContentPane().setPreferredSize(new Dimension(1360,768));
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.win = frame;
    }
    public JFrame getFrame()
    {
        return win;
    }
}