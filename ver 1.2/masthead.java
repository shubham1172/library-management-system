import java.text.SimpleDateFormat;
import java.util.Calendar;
class masthead
{
    String name;
    void print()
    {
        Calendar calender = Calendar.getInstance();
        SimpleDateFormat dateformatter = new SimpleDateFormat(" dd MMMMMMMMM',' yyyy ");
        System.out.println("\f");
        clearScreen();
        System.out.println("*****************************************************************************************************");
        System.out.println("*********************                 LIBRARY MANAGEMENT SYSTEM                **********************");
        System.out.println("*****************************************************************************************************");
        System.out.println("Date - "+ dateformatter.format(calender.getTime()));
        System.out.println("*****************************************************************************************************");
        System.out.println("");
    }
    void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
   }  

}