import java.io.*;
/**
 * Implements updating bookmaster.txt
 */
class BookWriter {
    public static void add(String book,String author,String publisher) throws Exception
    {
        String document = book+";"+author+";"+publisher+";available;";
        FileWriter fw = new FileWriter(Path.getAdd()+"resources\\bookmaster.txt",true);
        char buffer[] = new char[document.length()];
        document.getChars(0,document.length(),buffer,0);
        fw.write(buffer);
        fw.close();
    }
    public static void edit(String book,String author,String publisher, int row) throws Exception
    {
        BookReader br = new BookReader();
        String document = br.document;
        String[] values = document.split(";");
        values[4*(row)] = book;
        values[4*(row)+1] = author;
        values[4*(row)+2] = publisher;
        document = "";
        for(int i=0; i< values.length; i++)
        {
            document = document + values[i] + ";";
        }
        FileWriter fw = new FileWriter(Path.getAdd()+"resources\\bookmaster.txt",false);
        char buffer[] = new char[document.length()];
        document.getChars(0,document.length(),buffer,0);
        fw.write(buffer);
        fw.close();
    }
    public static void issue(int[] rows) throws Exception
    {
        BookReader br = new BookReader();
        String document = br.document;
        String[] values = document.split(";");
        for(int i=0;i<rows.length;i++)
        {
            values[rows[i]*4+3] = "issued";
        }
        document = "";
        for(int i=0; i< values.length; i++)
        {
            document = document + values[i] + ";";
        }
        FileWriter fw = new FileWriter(Path.getAdd()+"resources\\bookmaster.txt",false);
        char buffer[] = new char[document.length()];
        document.getChars(0,document.length(),buffer,0);
        fw.write(buffer);
        fw.close();
    }
    public static void returnB(int[] rows) throws Exception
    {
        BookReader br = new BookReader();
        String document = br.document;
        String[] values = document.split(";");
        for(int i=0;i<rows.length;i++)
        {
            values[rows[i]*4+3] = "available";
        }
        document = "";
        for(int i=0; i< values.length; i++)
        {
            document = document + values[i] + ";";
        }
        FileWriter fw = new FileWriter(Path.getAdd()+"resources\\bookmaster.txt",false);
        char buffer[] = new char[document.length()];
        document.getChars(0,document.length(),buffer,0);
        fw.write(buffer);
        fw.close();
    }
    public static void delete(int[] rows) throws Exception
    {
        BookReader br = new BookReader();
        String document = br.document;
        String[] values = document.split(";");
        for(int i=0;i<rows.length;i++)
        {
            values[rows[i]*4+3] = "deleted";
        }
        document = "";
        for(int i=0; i< values.length; i++)
        {
            document = document + values[i] + ";";
        }
        FileWriter fw = new FileWriter(Path.getAdd()+"resources\\bookmaster.txt",false);
        char buffer[] = new char[document.length()];
        document.getChars(0,document.length(),buffer,0);
        fw.write(buffer);
        fw.close();
    }
    public static void addDeleted(int[] rows) throws Exception
    {
        BookReader br = new BookReader();
        String document = br.document;
        String values[] = document.split(";");
        for(int i=0;i<rows.length;i++)
        {
            values[rows[i]*4+3] = "available";
        }
         document = "";
        for(int i=0; i< values.length; i++)
        {
            document = document + values[i] + ";";
        }
        FileWriter fw = new FileWriter(Path.getAdd()+"resources\\bookmaster.txt",false);
        char buffer[] = new char[document.length()];
        document.getChars(0,document.length(),buffer,0);
        fw.write(buffer);
        fw.close();
    }
}