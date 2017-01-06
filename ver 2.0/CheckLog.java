import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
class CheckLog extends JDialog implements ActionListener
{
    String[] coloumnNames = {"ID","BOOK","ACTION","StudentID","TIME","DATE"};
    Object[][] data;
    JTable table;
    JScrollPane tabSP;
    JButton close;
    JLabel background,north;
    JPanel[][] gridpanel;
    public CheckLog(JFrame parent)
    {
        super(parent,"Check LOG:", false);
        setLayout(new BorderLayout());
        setSize(1000,600);
        setLocationRelativeTo(null);
        setVisible(true);
        init();
    }
    private void init()
    {
        try{
            fetchData();
        }
        catch(Exception e)
        {}
        DefaultTableModel model = new DefaultTableModel(data,coloumnNames){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table = new JTable(model);
        table.setRowSelectionAllowed(false);
        table.setOpaque(false);
        table.setAutoCreateRowSorter(true);
        //Alignment
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
        table.setShowGrid(false);
        tabSP = new JScrollPane(table);
        tabSP.setOpaque(false);
        tabSP.getViewport().setOpaque(false);
        setupItems();
        setupComponents();
    }
    private void fetchData() throws Exception{
        String val[] = new String[0];
        BufferedReader br = new BufferedReader(new FileReader(Path.getAdd()+"resources\\log.txt"));
        String text = "";
        while((text=br.readLine())!=null)
        {
            String values[] = new String[text.split(";").length];
            values = text.split(";");
            val = values;
        }
        data = new Object[val.length/6][6];
        int k=0;
        for(int i=0; i<val.length/6;i++)
        {
            for(int j=0;j<6;j++)
            {
                data[i][j] = val[k];
                k++;
            }
        }
    }
    private void setupItems()
    {
        close = new JButton("CLOSE");
        close.setMinimumSize(new Dimension(100,25));
        close.addActionListener(this);
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\dialogBG.jpg"));
        background.setLayout(new BorderLayout());
        north = new JLabel("                ");
        north.setLayout(new GridLayout(5,1));
        setupGridPanels();
    }
    private void setupGridPanels()
    {
        gridpanel = new JPanel[5][1];
        for(int i=0;i<5;i++)
        {
            for(int j=0;j<1;j++){
                gridpanel[i][j] = new JPanel();
                gridpanel[i][j].setOpaque(false);
                north.add(gridpanel[i][j]);}
        }
    }
    private void setupComponents()
    {
        gridpanel[2][0].add(close);
        background.add(tabSP,"Center");
        background.add(north,"East");
        add(background,"Center");
    }
    public void actionPerformed(ActionEvent ae)
    {
        setVisible(false);
        dispose();
    }
}