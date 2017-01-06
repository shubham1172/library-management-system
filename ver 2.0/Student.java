import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Student
{
    JLabel north,south,center,background,east,west,centerNorth;
    JPanel[] eastPanel,westPanel;
    MenuBar menubar;
    Menu options;
    MenuItem home,exit;
    JButton homeB;
    JTable tab;
    JScrollPane tabSP;
    JFrame student;
    public Student()
    {
        student = (new MyFrame("Student's portal - A 1172 Product")).getFrame();
        student.setLayout(new BorderLayout());
        init();
    }
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
        north = new JLabel("Hello Student:");
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
        centerNorth = new JLabel("LIBRARY OPEN DATABASE for students:");
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
        homeB = new JButton("HOME");
        homeB.setSize(100,25);
        homeB.addActionListener(new ButtonHandler());
    }
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
    private void setupTable()
    {
        tab = (new MyTable(1)).getTable();
        tab.setShowGrid(false);
        tabSP = new JScrollPane(tab);
        tabSP.setOpaque(false);
        tabSP.getViewport().setOpaque(false);
        tab.setRowSelectionAllowed(false);
    }
    private void setupComponents()
    {
        eastPanel[6].add(homeB);
        center.add(centerNorth,"North");
        center.add(tabSP,"Center");
        student.setMenuBar(menubar);
        student.add(north,"North");
        student.add(south,"South");
        background.add(west,"West");
        background.add(east,"East");
        background.add(center,"Center");
        student.add(background,"Center");
        student.setVisible(true);
    }
    class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            student.setVisible(false);
            Entry ob = new Entry();
            ob.main();   
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
                //home
                student.setVisible(false);
                Entry ob = new Entry();
                ob.main();
                break;
                case 1:
                //exit
                student.setVisible(false);
                System.exit(0);
                break;
            }            
        }
    }
}