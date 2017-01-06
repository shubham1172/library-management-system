import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
import java.io.*;
/**
 * This class stores the log in a file 'log.txt'.
 */
class Logger
{
    static String log="";
    static String months[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    static final int ADD = 1,EDIT = 2, RETURN =3, DELETE = 4;
    //FOR ADD,EDIT,RETURN,DELETE.
    public static void append(int id, int action) throws Exception
    {
        GregorianCalendar cal = new GregorianCalendar();
        String idnum = String.format("%04d",id);
        String time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        String date = months[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.DATE) + ", " + cal.get(Calendar.YEAR);
        String actionS = (action>2)?((action==3)?"RETURN":"DELETE"):((action==1)?"ADD":"EDIT");
        log = idnum + ";" + getBook(id) + ";"+ actionS.toUpperCase() + ";-;" + time + ";" + date + ";";
        add();
    }
    //FOR BOOK ISSUE. Boolean ISSUE just differentiates the two constructors.
    public static void append(int id,boolean ISSUE,int StuID) throws Exception
    {
        GregorianCalendar cal = new GregorianCalendar();
        String idnum = String.format("%04d",id);
        String time = cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        String date = months[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.DATE) + ", " + cal.get(Calendar.YEAR); 
        log = idnum + ";" + getBook(id) + ";ISSUE;" + StuID + ";" + time + ";" + date + ";";
        add();
    }
    private static String getBook(int id) throws Exception
    {
        FileReader fr = new FileReader(Path.getAdd()+"resources\\bookmaster.txt");
        BufferedReader br = new BufferedReader(fr);
        String text="";
        while((text=br.readLine())!=null)
        {
            String data[] = new String[text.split(";").length];
            data = text.split(";");
            return data[4*(id-1)];
        }
        fr.close();
        return "NotFound";
    }   
    private static void add() throws Exception
    {
        check();
        FileWriter fw = new FileWriter(Path.getAdd()+"resources\\log.txt",true);
        char buffer[] = new char[log.length()];
        log.getChars(0,log.length(),buffer,0);
        fw.write(buffer);
        fw.close();
    }
    private static void check() throws Exception
    {
        String text="", writtable="";
        Boolean flag = false;
        int count=0;
        try{
        FileReader fr = new FileReader(Path.getAdd()+"resources\\log.txt");
        BufferedReader br = new BufferedReader(fr);
        while((text=br.readLine())!=null){
            count = text.split(";").length;
            if(count==20)
            {
                writtable = text.substring(text.indexOf(';')+1);
                flag=true;
            }
        }
        fr.close();
        }
        catch(Exception e){}
        if(flag){
            FileWriter fw = new FileWriter(Path.getAdd()+"resources\\log.txt");
            char buffer[] = new char[writtable.length()];
            writtable.getChars(0,writtable.length(),buffer,0);
            fw.write(buffer);
            fw.close();
        }
    }
}