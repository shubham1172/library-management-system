import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * This class provides administrator window and functionalities.
 */
public class adminPage {
    /** Declaring components */
    static JFrame admin;
    static JButton addB,editB,issueB,returnB,deleteB,logoutB,clear;
    JLabel north,south,east,west,background,center,centerNorth;
    JPanel[] eastPanel,westPanel;
    MenuBar menubar;
    Menu options, tools;
    MenuItem logout, exit, changePass, checkLog;
    static JTable tab;
    JScrollPane tabSP;
    static final int ADD=1,EDIT=2,ISSUE=3,RETURN=4,DELETE=5;
    /** Default constructor */
    public adminPage()
    {
        admin = (new MyFrame("Hello Admin - A 1172 Product")).getFrame();
        admin.setLayout(new BorderLayout());
        init();
    }
    /** Initialises components */
    private void init()
    {
        setupLabels();
        setupGridPanels();
        setupButtons();
        setupMenu();
        setupTable();
        setupComponents();
    }
    private void setupLabels()
    {
        north = new JLabel("Hello Administrator:");
        south = new JLabel("Library Management System ver 2.0 is a library utility app created by shubhamsharma1172@gmail.com");
        east = new JLabel("                              ");
        east.setLayout(new GridLayout(9,1));
        west = new JLabel("                              ");
        west.setLayout(new GridLayout(9,1));
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\loginBG.jpg"));
        background.setLayout(new BorderLayout());
        center = new JLabel();
        center.setLayout(new BorderLayout());
        center.setBackground(new Color(0,0,0,0));
        centerNorth = new JLabel("LIBRARY DATABASE:");
        centerNorth.setFont(new Font("Courier New",Font.BOLD,20));
        centerNorth.setForeground(Color.WHITE);
    }
    private void setupGridPanels()
    {
        eastPanel = new JPanel[9];
        westPanel = new JPanel[9];
        int i;
        for(i=0;i<9;i++)
        {
            eastPanel[i] = new JPanel();
            eastPanel[i].setOpaque(false);
            east.add(eastPanel[i]);
            westPanel[i] = new JPanel();
            westPanel[i].setOpaque(false);
            west.add(westPanel[i]);
        }
    }
    private void setupButtons()
    {
        addB = new JButton("ADD");
        addB.addActionListener(new ActionHandler(1));
        addB.setPreferredSize(new Dimension(100,25));
        editB = new JButton("EDIT");
        editB.addActionListener(new ActionHandler(2));
        editB.setPreferredSize(new Dimension(100,25));
        issueB = new JButton("ISSUE");
        issueB.addActionListener(new ActionHandler(3));
        issueB.setPreferredSize(new Dimension(100,25));
        returnB = new JButton("RETURN");
        returnB.addActionListener(new ActionHandler(4));
        returnB.setPreferredSize(new Dimension(100,25));   
        deleteB = new JButton("DELETE");
        deleteB.addActionListener(new ActionHandler(5));
        deleteB.setPreferredSize(new Dimension(100,25));
        logoutB = new JButton("LOGOUT");
        logoutB.addActionListener(new ButtonHandler(1));
        logoutB.setPreferredSize(new Dimension(100,25));
        clear = new JButton("CLEAR");
        clear.setPreferredSize(new Dimension(100,50));
        clear.addActionListener(new ButtonHandler(2));
        westPanel[2].add(addB);
        westPanel[3].add(editB);
        westPanel[4].add(issueB);
        westPanel[5].add(returnB);
        westPanel[6].add(deleteB);
        eastPanel[2].add(clear);
        eastPanel[6].add(logoutB);
    }
    private void setupMenu()
    {
        menubar = new MenuBar();
        options = new Menu("Options");
        tools = new Menu("Tools");
        logout = new MenuItem("Logout");
        logout.setEnabled(true);
        logout.addActionListener(new MenuHandler(0));
        exit = new MenuItem("Exit");
        exit.setEnabled(true);
        exit.addActionListener(new MenuHandler(1));
        changePass = new MenuItem("Change password");
        changePass.setEnabled(true);
        changePass.addActionListener(new MenuHandler(2));
        checkLog = new MenuItem("Check log");
        checkLog.setEnabled(true);
        checkLog.addActionListener(new MenuHandler(3));
        options.add(logout);
        options.add(exit);
        tools.add(changePass);
        tools.add(checkLog);
        menubar.add(options);
        menubar.add(tools);
    }
    private void setupTable()
    {
        tab = (new MyTable(0)).getTable();
        tab.setShowGrid(false);
        tabSP = new JScrollPane(tab);
        tabSP.setOpaque(false);
        tabSP.getViewport().setOpaque(false);
    }
    private void setupComponents()
    {
        center.add(centerNorth,"North");
        center.add(tabSP,"Center");
        admin.setMenuBar(menubar);
        admin.add(north,"North");
        admin.add(south,"South");
        background.add(west,"West");
        background.add(east,"East");
        background.add(center,"Center");
        admin.add(background,"Center");
        admin.setVisible(true);
    }
    public static void disable(int code)
    {
        switch(code)
        {
            case 1:
            addB.setEnabled(false);
            break;
            case 2:
            editB.setEnabled(false);
            break;
            case 3:
            issueB.setEnabled(false);
            break;
            case 4:
            returnB.setEnabled(false);
            break;
            case 5:
            deleteB.setEnabled(false);
        }
    }
    public static void enable(int code)
    {
        switch(code)
        {
            case 1:
            addB.setEnabled(true);
            break;
            case 2:
            editB.setEnabled(true);
            break;
            case 3:
            issueB.setEnabled(true);
            break;
            case 4:
            returnB.setEnabled(true);
            break;
            case 5:
            deleteB.setEnabled(true);
        }
    }
    public static JFrame getFrame()
    {
        return admin;
    }
    public static void update()
    {
       admin.dispose();
       adminPage ap = new adminPage();
    }
    public static String getData(int row)
    {
        return tab.getValueAt(row,1)+";"+tab.getValueAt(row,2)+";"+tab.getValueAt(row,3);
    }
    public static int getRow()
    {
        return tab.getSelectedRow();
    }
    public static int[] getRows()
    {
        return tab.getSelectedRows();
    }
    class ButtonHandler implements ActionListener
    {
        int code;
        private ButtonHandler(int code)
        {
            this.code = code;
        }
        public void actionPerformed(ActionEvent ae)
        {
            switch(code)
            {
                case 1:
                //logout
                admin.setVisible(false);
                Entry ob = new Entry();
                ob.main();
                break;
                case 2:
                //clear
                tab.clearSelection();
                break;
            }
        }
    }
    class MenuHandler implements ActionListener
    {
        int code;
         MenuHandler(int code)
        {
            this.code = code;
        }
        public void actionPerformed(ActionEvent ae)
        {
            switch(code)
            {
                case 0:
                //logout
                admin.setVisible(false);
                Entry ob = new Entry();
                ob.main();
                break;
                case 1:
                //exit
                admin.setVisible(false);
                System.exit(0);
                break;
                case 2:
                //change password
                PasswordChange pc = new PasswordChange(admin);
                break;
                case 3:
                //check log
                CheckLog cl = new CheckLog(admin);
                break;
            }
        }
    }
}