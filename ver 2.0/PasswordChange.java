import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 * Creates a dialog box for changing password
 */
public class PasswordChange extends JDialog
{
    JLabel oldL,new1L,new2L,center,message;
    JPasswordField old, new1, new2;
    JPanel gridpanel[][];
    JButton submit,cancel;
    PasswordChange(Frame parent){
        super(parent,"Change Password:", false);
        setLayout(new BorderLayout());
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
    }
    private void init()  {
        setupLabels();
        setupTextFields();
        setupButtons();
        setupGridPanels();
        setupComponents();
    }
    private void setupLabels()
    {
        center = new JLabel(new ImageIcon(Path.getAdd()+"resources\\changePass.jpg"));
        center.setLayout(new GridLayout(6,2));
        oldL = new JLabel("ENTER OLD PASSWORD:");
        oldL.setFont(new Font("Arial",Font.BOLD,14));
        oldL.setForeground(Color.WHITE);
        new1L = new JLabel("ENTER NEW PASSWORD:");
        new1L.setFont(new Font("Arial",Font.BOLD,14));
        new1L.setForeground(Color.WHITE);
        new2L = new JLabel("RE-ENTER NEW PASSWORD:");
        new2L.setFont(new Font("Arial",Font.BOLD,14));
        new2L.setForeground(Color.WHITE);
        message = new JLabel();
        message.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,16));
        message.setForeground(Color.RED);
    }
    private void setupTextFields()
    {
        old = new JPasswordField(15);
        old.setText("password");
        old.setForeground(Color.GRAY);
        old.addFocusListener(new FocusHandler(0));
        new1 = new JPasswordField(15);
        new1.setText("password");
        new1.setForeground(Color.GRAY);
        new1.addFocusListener(new FocusHandler(1));
        new2 = new JPasswordField(15);
        new2.setText("password");
        new2.setForeground(Color.GRAY);
        new2.addFocusListener(new FocusHandler(2));
    }
    private void setupButtons()
    {
        submit = new JButton("SUBMIT");
        submit.setPreferredSize(new Dimension(100,25));
        submit.addActionListener(new ButtonHandler(0));
        cancel = new JButton("CANCEL");
        cancel.addActionListener(new ButtonHandler(1));
    }
    private void setupGridPanels()
    {
        gridpanel = new JPanel[6][2];
        int i,j;
        for(i=0;i<6;i++)
            for(j=0;j<2;j++){
                gridpanel[i][j] = new JPanel();
                gridpanel[i][j].setOpaque(false);
                center.add(gridpanel[i][j]);
            }
    }
    private void setupComponents()
    {
        gridpanel[1][0].add(oldL);gridpanel[1][1].add(old);
        gridpanel[2][0].add(new1L);gridpanel[2][1].add(new1);
        gridpanel[3][0].add(new2L);gridpanel[3][1].add(new2);
        gridpanel[4][0].add(submit);
        gridpanel[4][0].add(cancel);
        gridpanel[5][0].add(message);
        add(center,"Center");
    }
    public void close(){
        setVisible(false);
        dispose();
    }
    class ButtonHandler implements ActionListener{
        int code;
        String[] info = {"",""};
        public ButtonHandler(int code)
        {
            this.code = code;

        }
        public void actionPerformed(ActionEvent ae)
        {   
            switch(code){
                case 0: //submit
            try{
                check();
            }
            catch(Exception e){}
            break;
                case 1: //exit
                setVisible(false);
                dispose();
            }
        }
        private boolean checkPass()
        {
            if(!(old.getText()).equals(info[1]))
            {
                message.setText("Invalid old password. Try again.");
                return false;
            }
            else if(!new1.getText().equals(new2.getText()))
            {
                message.setText("New passwords don't match. Try again.");
                return false;
            }
            else if(new1.getText().equals(old.getText()))
            {
                message.setText("New password and old password cannot be same. Try again.");
                return false;
            }
            else if(new1.getText().length()<5)
            {
                message.setText("New password too short!");
                return false;
            }
            return true;
        }
        private void check() throws Exception
        {
            try{
                FileReader fr = new FileReader(Path.getAdd()+"resources\\admininfo.txt");
                BufferedReader br = new BufferedReader(fr);
                String text="";
                while((text=br.readLine())!=null){
                    info = text.split(";");
                }
                fr.close();
            }
            catch(Exception e)
            {
                message.setText(e.toString());
            }
            if(checkPass())
                correct();
        }
        private void correct() throws Exception
        {
            String text = info[0]+";"+new1.getText();
            FileWriter fw = new FileWriter(Path.getAdd()+"resources\\admininfo.txt");
            char buffer[] = new char[text.length()];
            text.getChars(0,text.length(),buffer,0);
            fw.write(buffer);
            fw.close();
            message.setText("Password changed successfully!");
        }
    }
    class FocusHandler implements FocusListener{
        int code;
        FocusHandler(int code){
            this.code = code;
        }
        public void focusGained(FocusEvent fe)
        {
            switch(code)
            {
                case 0:
                if(old.getText().equals("password")){
                    old.setText("");
                    old.setForeground(Color.BLACK);
                }
                break;
                case 1:
                if(new1.getText().equals("password")){
                    new1.setText("");
                    new1.setForeground(Color.BLACK);
                }
                break;
                case 2:
                if(new2.getText().equals("password")){
                    new2.setText("");
                    new2.setForeground(Color.BLACK);
                }
                break;
            }
        }
        public void focusLost(FocusEvent fe)
        {
            switch(code)
            {
                case 0:
                if(old.getText().equals("")){
                    old.setText("password");
                    old.setForeground(Color.GRAY);
                }
                break;
                case 1:
                if(new1.getText().equals("")){
                    new1.setText("password");
                    new1.setForeground(Color.GRAY);
                }
                break;
                case 2:
                if(new2.getText().equals("")){
                    new2.setText("password");
                    new2.setForeground(Color.GRAY);
                }
                break;
            }
        }
    }
}