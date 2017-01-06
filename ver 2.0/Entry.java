import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This class is the starting point of our application, containing a main() method.
 */
class Entry {
    JFrame window;
    /** Initiate the application */
    public static void main(String[] args)
    {
        Entry app = new Entry();
        app.init();
    }
    public static void main()
    {
        String args[] = new String[0];
        main(args);
    }
    /** Declaring components */
    JButton administrator,student;
    JLabel north,south,background;
    MenuBar menubar;
    Menu options;
    MenuItem exit;
    JPanel[][] gridpanel;
    /** Initialising */
    private void init()
    {
        /** Configuring window */
        window = (new MyFrame("A 1172 Product.")).getFrame();
        window.setLayout(new BorderLayout());
        /** Configuring components */
        setupLabels();
        setupButtons();
        setupMenu();
        setupComponents();
    }
    /** Manages buttons and configures them */
    private void setupButtons()
    {
        administrator = new JButton("ADMINISTRATOR");
        administrator.addActionListener(new ButtonHandler(0));
        student = new JButton("STUDENT");
        student.addActionListener(new ButtonHandler(1));
        setupGridPanels();
    }
    /** Manages menus and configures them */
    private void setupMenu()
    {
        menubar = new MenuBar();
        options = new Menu("Options");
        exit = new MenuItem("Exit");
        exit.setEnabled(true);
        exit.addActionListener(new MenuHandler());
        options.add(exit);
        menubar.add(options);
        window.setMenuBar(menubar);
    }
    /** Manages labels and configures them */
    private void setupLabels()
    {
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\background.jpg"));
        background.setLayout(new GridLayout(8,3,0,50));
        north = new JLabel("WELCOME TO LIBRARY MANAGEMENT SYSTEM! Choose a module to continue...");
        south = new JLabel("Library Management System ver 2.0 is a library utility app created by shubhamsharma1172@gmail.com");
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
    /** Adds components to frame */
    private void setupComponents()
    {
        /** Adding components to window */
        gridpanel[3][1].setLayout(new GridLayout());
        gridpanel[3][1].add(administrator);
        gridpanel[4][1].setLayout(new GridLayout());
        gridpanel[4][1].add(student);
        window.add(background,"Center");
        window.add(north,"North");
        window.add(south,"South");
    }
    /** Handles button triggers
     *  int code is used to distinguish between the buttons.
     */
    class ButtonHandler implements ActionListener
    {
        int code;
        public ButtonHandler(int code)
        {
            this.code = code;
        }
        public void actionPerformed(ActionEvent ae)
        {
            if(code==0)
            {
                window.setVisible(false);
                loginForm ob = new loginForm();
            }
            if(code==1)
            {
                window.setVisible(false);
                Student st = new Student();
            }
        }
    }
    /** Handles the menu */
    class MenuHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            window.setVisible(false);
            System.exit(0);     
        }
    }
}