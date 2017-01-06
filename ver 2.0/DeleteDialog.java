import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class DeleteDialog extends JDialog
{
    JLabel background, prompt;
    JButton delete, cancel;
    JPanel[][] gridpanel;
    public DeleteDialog(JFrame parent)
    {
        super(parent,"DELETE BOOK:",false);
        setLayout(new BorderLayout());
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
    }
    private void init()
    {
        setupLabels();
        setupButtons();
        setupGridPanels();
        setupComponents();
    }
    private void setupLabels()
    {
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\dialogBGG.jpg"));
        background.setLayout(new GridLayout(6,1));
        prompt = new JLabel("Are you sure?");
        prompt.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,18));
        prompt.setForeground(Color.RED);
    }
    private void setupButtons()
    {
        delete = new JButton("DELETE");
        delete.setSize(100,25);
        delete.addActionListener(new ButtonHandler(0));
        cancel = new JButton("CANCEL");
        cancel.setSize(100,25);
        cancel.addActionListener(new ButtonHandler(1));
    }
    private void setupGridPanels()
    {
        gridpanel = new JPanel[6][1];
        for(int i=0; i<6; i++)
            for(int j=0; j<1; j++)
            {
                gridpanel[i][j] = new JPanel();
                gridpanel[i][j].setOpaque(false);
                background.add(gridpanel[i][j]);
            }
    }
    private void setupComponents()
    {
        gridpanel[2][0].add(prompt);
        gridpanel[3][0].add(delete);
        gridpanel[3][0].add(cancel);
        add(background);
    }
    class ButtonHandler implements ActionListener
    {
        int code;
        public ButtonHandler(int code)
        {
            this.code = code;
        }
        public void actionPerformed(ActionEvent ae)
        {
            switch(code)
            {
                case 1:
                setVisible(false);
                dispose();
                break;
                case 0:
                try
                {
                    BookWriter bw = new BookWriter();
                    bw.delete(adminPage.getRows());
                    int[] rows = adminPage.getRows();
                    for(int i=0; i<rows.length; i++)
                    {
                        Logger.append(rows[i],Logger.DELETE);
                    }
                }catch(Exception e)
                {}
                adminPage.update();
                break;
            }
        }
    }
}