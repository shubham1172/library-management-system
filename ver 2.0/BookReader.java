import java.io.*;
class BookReader
{
    static String document;
    public BookReader()
    {
        try{
        init();
        }
        catch(Exception e){}
    }
    private void init() throws Exception
    {
        FileReader fr = new FileReader(Path.getAdd()+"resources\\bookmaster.txt");
        BufferedReader br = new BufferedReader(fr);
        String text="";
        while((text=br.readLine())!=null)
        {
            document = text;
        }
    }
    public boolean checkMatch(String book,String author,String publisher)
    {
        String query = book+";"+author+";"+publisher;
        return (document.toLowerCase().indexOf(query.toLowerCase())>-1);
    }
    public boolean checkMatchEdit(String book,String author,String publisher)
    {
        String query = book+";"+author+";"+publisher;
        return (document.indexOf(query)>-1);
    }
    public int getID(String book,String author,String publisher)
    {
        String query = book+";"+author+";"+publisher;
        int index = document.toLowerCase().indexOf(query.toLowerCase());
        query = document.substring(0,index);
        return ((query.split(";")).length/4+1);
    }
}