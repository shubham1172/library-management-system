import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class IssueDialog extends JDialog
{
    JLabel background, idnum, message;
    JTextField id;
    JButton submit, close;
    JPanel[][] gridpanel;
    public IssueDialog(JFrame parent)
    {
        super(parent,"ISSUE BOOK:",false);
        setLayout(new BorderLayout());
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
    }
    private void init()
    {
        setupLabels();
        setupGridPanels();
        setupTextField();
        setupButtons();
        setupComponents();
    }
    private void setupLabels()
    {
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\dialogBGG.jpg"));
        background.setLayout(new GridLayout(6,2));
        idnum = new JLabel("Enter the student's ID number:");
        idnum.setForeground(Color.WHITE);
        idnum.setFont(new Font("Arial",Font.BOLD,14));
        message = new JLabel();
        message.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,14));
        message.setForeground(Color.RED);
    }
    private void setupGridPanels()
    {
        gridpanel = new JPanel[6][2];
        for(int i=0;i<6;i++)
            for(int j=0;j<2;j++)
            {
                gridpanel[i][j] = new JPanel();
                gridpanel[i][j].setOpaque(false);
                background.add(gridpanel[i][j]);
            }
    }
    private void setupTextField()
    {
        id = new JTextField(30);
        id.setForeground(Color.GRAY);
        id.setText("ID number");
        id.addFocusListener(new FocusHandler());
    }
    private void setupButtons()
    {
        submit = new JButton("SUBMIT");
        submit.setSize(100,25);
        submit.addActionListener(new ButtonHandler(0));
        close = new JButton("CLOSE");
        close.setSize(100,25);
        close.addActionListener(new ButtonHandler(1));
    }
    private void setupComponents()
    {
        gridpanel[2][0].add(idnum);
        gridpanel[2][1].add(id);
        gridpanel[3][0].add(submit);
        gridpanel[3][0].add(close);
        add(background,"Center");
    }
    class FocusHandler implements FocusListener
    {
        public void focusLost(FocusEvent fe)
        {
            if((id.getText()).equals(""))
            {
                id.setText("ID number");
                id.setForeground(Color.GRAY);
            }
        }
        public void focusGained(FocusEvent fe)
        {
            if((id.getText()).equals("ID number"))
            {
                id.setText("");
                id.setForeground(Color.BLACK);
            }
        }
    }
    class ButtonHandler implements ActionListener
    {
        int code;
        public ButtonHandler(int code){
            this.code = code;
        }
        public void actionPerformed(ActionEvent ae)
        {
            if(code==1)
            {
                setVisible(false);
                dispose();
            }
            else
            {
                if(!checkID())
                    message.setText("Invalid ID number. Try again.");
                else
                {
                    try
                    {
                        BookWriter bw = new BookWriter();
                        bw.issue(adminPage.getRows());
                        int[] rows = adminPage.getRows();
                        for(int i=0; i<rows.length; i++)
                        {
                            Logger.append(rows[i],true,Integer.parseInt(id.getText()));
                        }
                    }catch(Exception e)
                    {}
                    adminPage.update();
                }
            }
        }
        private boolean checkID()
        {
            String st = id.getText().trim();
            if (st.matches("[0-9]+")) 
                return true;
            return false;
        }
    }
}