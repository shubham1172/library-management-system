import java.io.*;
class Enter
{
    static int iC=0; //invalidCount
    public static void main(String[] args) throws Exception
    {
        main();
    }
    public static void main() throws Exception
    {
        masthead obj = new masthead();
        obj.print();
        System.out.println("ver 1.2 \n\t\tshubhamsharma1172@gmail.com");
        System.out.println("Press 1 for administrator login.**");
        System.out.println("Press 2 for student login.");
        System.out.println("**protected");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));      
        int option=0;
        try {
            option = Integer.parseInt(br.readLine());
        }
        catch(Exception e)
        {
            System.out.println("\tInvalid input.");
            Thread.sleep(1000);
            System.exit(0);
        }
        if(option==1)
        {
            login ob = new login();
            ob.syslogin();
        }
        else if(option==2)
        {
            user ob1 = new user();
            ob1.login();
        }
        else
        {
            iC++;
            if(iC<3) {
            System.out.println("Invalid choice.");
            Thread.sleep(1000);
            System.out.println("Retry.");
            Thread.sleep(1000);
            main();
            }
            else
            {
                System.out.println("Maximum number of attempts exceeded. Exiting...");
                Thread.sleep(1000);
                System.exit(0);
            }
        }
    }
}