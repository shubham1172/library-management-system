import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class addDelDialog extends JDialog implements ActionListener
{
    JLabel background,message;
    JButton ok;
    JPanel[][] gridpanel;
    public addDelDialog(JFrame parent)
    {
        super(parent,"ADD BOOK:",false);
        setLayout(new BorderLayout());
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
    }
    private void init()
    {
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\dialogBGG.jpg"));
        background.setLayout(new GridLayout(6,1));
        message = new JLabel("Click OK to confirm.");
        message.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,18));
        message.setForeground(Color.RED);
        gridpanel = new JPanel[6][2];
        for(int i=0;i<6;i++)
            for(int j=0;j<1;j++)
            {
                gridpanel[i][j] = new JPanel();
                gridpanel[i][j].setOpaque(false);
                background.add(gridpanel[i][j]);
            }
        ok = new JButton("OK");
        ok.setSize(100,25);
        ok.addActionListener(this);
        gridpanel[2][0].add(message);
        gridpanel[3][0].add(ok);
        add(background,"Center");
    }
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        dispose();
        try
        {
            BookWriter bw = new BookWriter();
            bw.addDeleted(adminPage.getRows());
            int[] rows = adminPage.getRows();
            for(int i=0; i<rows.length; i++)
            {
                Logger.append(rows[i],Logger.ADD);
            }
        }catch(Exception e)
        {}
        adminPage.update();
    }
}