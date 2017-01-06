import java.io.*;
class user
{
    void login() throws Exception
    {
        masthead obj = new masthead();
        obj.print();
        System.out.println("Hello student");
        /**
         * No security line.
         * A public resource for browsing books and checking their status.
         */
        System.out.println("Press 1 to browse the books.");
        System.out.println("Press 2 to exit.");
        int option=0;
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
            option = Integer.parseInt(br.readLine());
        }
        catch(Exception e)
        {
            System.out.println("Invalid input.");
            Thread.sleep(1000);
            System.out.println("Try again.");
            Thread.sleep(1000);
            login();
        }
        if(option==1)
        {
            //For Browsing books, the books are read in a different class and then sorted accordingly.
            browse ob = new browse();
            ob.display();
            BufferedReader var9 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n\nEnter any character to exit.");
            String var10 = var9.readLine();
            System.out.println("\tExiting..");
            Thread.sleep(1000);
            System.exit(0);
        }
        else if(option==2)
        {
            System.out.println("\tExiting...");
            Thread.sleep(1000);
            System.exit(0);
        }
        else
        {
            System.out.println("\tInvalid choice...\n\tTry again.");
            Thread.sleep(1000);
            login();
        }
    }
}