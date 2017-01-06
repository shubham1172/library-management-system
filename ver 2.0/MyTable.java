import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.table.*;
import javax.swing.event.*;
import java.io.*;
public class MyTable
{
    String[] coloumnNames = {"ID","BOOK","AUTHOR","PUBLISHER","STATUS"};
    Object[][] data;
    JTable table;
    public MyTable(int code){
        if(code==0)
        init();
        else
        initt();
    }
    private void init(){
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
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setOpaque(false);
        //SORTER
        table.setAutoCreateRowSorter(true);
        //ALIGNER
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        setupListener();
        fixbuttons();
    }
    private void initt()
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
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setOpaque(false);
        //SORTER
        table.setAutoCreateRowSorter(true);
        //ALIGNER
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
    }
    private void fetchData() throws Exception{
        String[] val = new String[0];
        FileReader fr = new FileReader(Path.getAdd()+"resources\\bookmaster.txt");
        BufferedReader br = new BufferedReader(fr);
        String text="";
        while((text=br.readLine())!=null)
        {
            String[] values = new String[text.split(";").length];
            values = text.split(";");
            val = values;
        }
        fr.close();
        data = new Object[val.length/4][5];
        int k=0,j;
        for(int i=0;i<val.length/4;i++)
        {
            j=0;
            data[i][j] = String.format("%04d",(i+1));
            for(j=1;j<5;j++){
                data[i][j] = val[k];
                k++;
            }
        }
    }
    private void fixbuttons()
    {
        adminPage.enable(adminPage.ADD);
        adminPage.disable(adminPage.EDIT);
        adminPage.disable(adminPage.ISSUE);
        adminPage.disable(adminPage.RETURN);
        adminPage.disable(adminPage.DELETE);
    }
    private void setupListener(){
        table.getSelectionModel().addListSelectionListener(
                new ListHandler());
    }
    public JTable getTable(){
        return table;
    }
    public class RowRenderer extends JLabel implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object color,
            boolean isSelected, boolean hasFocus, int row, int column) {
                setText(Integer.toString(row));
                return this;
            }
    }
    /**Manages list selection*/
    class ListHandler implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent le){
            int[] rows = table.getSelectedRows();
            //Set rules for button.
            if(rows.length>0)
                adminPage.disable(adminPage.ADD);
            else
                adminPage.enable(adminPage.ADD);
            if(rows.length!=1)
                adminPage.disable(adminPage.EDIT);
            else 
                adminPage.enable(adminPage.EDIT);
            if(rows.length>0)
            {
                Boolean addflag = true, issueflag = true, returnflag = true, deleteflag = true;
                //check for add compatibility for deleted books.
                for(int i=0; i<rows.length; i++)
                {
                    if(!(table.getValueAt(rows[i],4)).equals("deleted")){
                        addflag=false;
                    }
                }
                if(!addflag)
                    adminPage.disable(adminPage.ADD);
                else
                    adminPage.enable(adminPage.ADD);
                //check for issue compatibility
                for(int i=0; i<rows.length; i++)
                {
                    if(!(table.getValueAt(rows[i],4)).equals("available")){
                        issueflag=false;
                    }
                }
                if(!issueflag)
                    adminPage.disable(adminPage.ISSUE);
                else
                    adminPage.enable(adminPage.ISSUE);
                //check for return compatibility
                for(int i=0; i<rows.length; i++)
                {
                    if(!(table.getValueAt(rows[i],4)).equals("issued")){
                        returnflag = false;
                    }
                }
                if(!returnflag)
                    adminPage.disable(adminPage.RETURN);
                else
                    adminPage.enable(adminPage.RETURN);
                //check for delete compatibility
                for(int i=0; i<rows.length; i++)
                {
                    if(!(table.getValueAt(rows[i],4)).equals("available")){
                        deleteflag=false;
                    }
                }
                if(!deleteflag)
                    adminPage.disable(adminPage.DELETE);
                else
                    adminPage.enable(adminPage.DELETE);
            }
            else
            {
                adminPage.disable(adminPage.ISSUE);
                adminPage.disable(adminPage.RETURN);
                adminPage.disable(adminPage.DELETE);
            }
        }
    }
}