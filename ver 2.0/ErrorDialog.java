import java.awt.*;
import java.awt.event.*;
import java.awt.Font.*;
import javax.swing.*;
class ErrorDialog extends JDialog implements ActionListener {
    JPanel center;
    JPanel[][] gridpanel;
    ErrorDialog(Frame parent, String message1,String message2){
        super(parent,"A 1172 Product.",false);
        setLayout(new BorderLayout());
        setSize(800,500);
        setLocationRelativeTo(null);
        JLabel lab1 = new JLabel(message1);
        JLabel lab2 = new JLabel(message2);
        Font font = new Font("Lucida Fax",Font.BOLD,16);
        lab1.setFont(font);lab2.setFont(font);
        center = new JPanel();
        center.setLayout(new GridLayout(8,1));
        setupGridPanels();
        gridpanel[3][0].add(lab1);
        gridpanel[4][0].add(lab2);
        add(center,"Center");
        JButton ok = new JButton("OK");
        add(ok,"South");
        ok.addActionListener(this);
        JLabel oops = new JLabel(new ImageIcon(Path.getAdd()+"resources\\oops.jpg"));
        add(oops,"East");
    }
    void setupGridPanels()
    {
        gridpanel = new JPanel[8][1];
        int i=0,j=0;
        for(i=0; i<8; i++)
            for(j=0; j<1; j++)
            {
                gridpanel[i][j] = new JPanel();
                gridpanel[i][j].setOpaque(false);
                center.add(gridpanel[i][j]);
            }

    }
    public void actionPerformed(ActionEvent ae){
        dispose();
    }
}