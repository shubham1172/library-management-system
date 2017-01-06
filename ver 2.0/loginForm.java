import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileReader;
import java.io.BufferedReader;
/**
 * This class provides a Login window and handles the login process.
 */
public class loginForm  {
    /** Declaring components */
    JFrame login;
    JTextField username;
    JPasswordField password;
    JLabel north,south,user,pass,background;
    JButton submit;
    JPanel[][] gridpanel;
    MenuBar menubar;
    Menu options;
    MenuItem home,exit;
    int invalidCount; //Counts the number of invalid login attempts by the user.
    /** Default constructor **/
    public loginForm()
    {
        login = (new MyFrame("Login - A 1172 Product.")).getFrame();
        login.setLayout(new BorderLayout());
        invalidCount = 0;
        init();
    }
    /** Initialises components **/
    private void init()
    {
        setupLabels();
        setupTextFields();
        setupGridPanels();
        setupMenu();
        submit = new JButton("SUBMIT");
        submit.addActionListener(new ButtonHandler());
        setupComponents();
    }
    /** Manages labels and configures them */
    private void setupLabels()
    {
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\background.jpg"));
        background.setLayout(new GridLayout(8,3));
        north = new JLabel("Login Page:");
        south = new JLabel("Library Management System ver 2.0 is a library utility app created by shubhamsharma1172@gmail.com");
        user = new JLabel("Username:");
        user.setForeground(Color.WHITE);
        pass = new JLabel("Password:");
        pass.setForeground(Color.WHITE);
    }
    /** Manages textFields and configures them */
    private void setupTextFields()
    {
        username = new JTextField(15);
        username.setText("xyz");
        username.setForeground(Color.GRAY);
        username.addFocusListener(new FocusHandler(0));
        password = new JPasswordField(15);
        password.setText("password");
        password.setForeground(Color.GRAY);
        password.addFocusListener(new FocusHandler(1));
    }
    /** Panels are distributed all over the grid to allow adding components to particular grid cells. 
    *   gridpanel[][] is distributed in the 8X3 network of grids and buttons are added accordingly.  
    */
   private void setupGridPanels()
    {
        gridpanel = new JPanel[8][3];
        int i=0,j=0;
        for(i=0; i<8; i++)
            for(j=0; j<3; j++)
            {
                gridpanel[i][j] = new JPanel();
                gridpanel[i][j].setOpaque(false);
                background.add(gridpanel[i][j]);
            }

    }
    /** Manages menus and configures them */
   private void setupMenu()
   {
        menubar = new MenuBar();
        options = new Menu("Options");
        home = new MenuItem("Home");
        home.setEnabled(true);
        home.addActionListener(new MenuHandler(0));
        exit = new MenuItem("Exit");
        exit.setEnabled(true);
        exit.addActionListener(new MenuHandler(1));
        options.add(home);
        options.add(exit);
        menubar.add(options);
    }
    /** Adds components to the frame */
   private void setupComponents()
    {
        gridpanel[3][1].add(user);
        gridpanel[3][1].add(username);
        gridpanel[4][1].add(pass);
        gridpanel[4][1].add(password);
        gridpanel[5][1].add(submit);
        login.setMenuBar(menubar);
        login.add(background,"Center");
        login.add(north,"North");
        login.add(south,"South");
    }
    /** Handles button trigger */
    class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            try {
                if(new CheckLogin(username.getText(),password.getText()).check()&&invalidCount<3)
                {
                    login.setVisible(false);
                    adminPage aP = new adminPage();
                }
                else
                {
                    invalidCount++;
                    if(invalidCount>2) //Maximum 3 attempts are allowed.
                    {
                        ErrorDialog eD = new ErrorDialog(login,"Too many invalid attempts.","Try again later!");
                        eD.setVisible(true);
                    }
                    else
                    {
                        ErrorDialog eD = new ErrorDialog(login,"Invalid username or password!","Please try again. " + (3-invalidCount) + " attempt(s) left.");
                        eD.setVisible(true);
                    }
                }
            }
            catch(Exception e)
            {}
        }
    }
    /** Handles focus of textFields 
     * int code is used to distinguish between the textFields.
     */
    class FocusHandler implements FocusListener
    {
        int code;
        public FocusHandler(int code)
        {
            this.code = code;
        }
        public void focusGained(FocusEvent fe)
        {
            if(username.getText().equals("xyz")&&code==0){
                username.setText("");
                username.setForeground(Color.BLACK);
            }
            if(password.getText().equals("password")&&code==1){
                password.setText("");
                password.setForeground(Color.BLACK);
            }
        }
        public void focusLost(FocusEvent fe)
        {
            if(username.getText().equals("")&&code==0){
                username.setText("xyz");
                username.setForeground(Color.GRAY);
            }
            if(password.getText().equals("")&&code==1){
                password.setText("password");
                password.setForeground(Color.GRAY);
            }
        }
    }
    /** Handles menu items */
    class MenuHandler implements ActionListener
    {
        int code;
        public MenuHandler(int code)
        {
            this.code = code;
        }
        public void actionPerformed(ActionEvent ae)
        {
            if(code==0)
            {
                login.setVisible(false);
                Entry ob = new Entry();
                ob.main();
            }
            if(code==1)
            {
               login.setVisible(false);
                System.exit(0);
            }
        }
    }
    class CheckLogin
    {
        String user, pass;
        private CheckLogin(String user, String pass)
        {   
            this.user = user;
            this.pass = pass;
        }
        boolean check() throws Exception
        {
            FileReader fr = new FileReader(Path.getAdd()+"resources\\admininfo.txt");
            BufferedReader file = new BufferedReader(fr);
            String info[] = new String[2];
            String text;
            while((text=file.readLine())!=null){
                info = text.split(";");
            }
            fr.close();
            if(info[0].equals(user)&&info[1].equals(pass))
                return true;
            return false;
        }
    }
}