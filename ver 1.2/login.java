import java.io.*;
class login
{
    Encrypt en = new Encrypt();
    Decrypt de = new Decrypt();
    void syslogin() throws Exception
    {
        masthead obj = new masthead();
         /**
         * Reading the file admininfo.txt for secured entry.
         */
        FileReader file = new FileReader("admininfo.txt");
        BufferedReader fileInput = new BufferedReader(file);
        String username="", password="";
        String text;
        int invalidCount; invalidCount = 0; //After three chances of logging in, the program terminates.
        while((text=fileInput.readLine())!=null){
           text = de.decode(text); 
           username = text.substring(0,text.indexOf(';'));
           password = text.substring(text.indexOf(';')+1,text.length());
        }
        file.close();
        /** 
         * Original username and password stored for checking.
         * Now, it'll be checked with the input.
         */
        form:
        for(;;)
        {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            obj.print();
            System.out.println("Hello Admin");
            System.out.print("USERNAME: ");
            String user = br.readLine();
            System.out.print("PASSWORD: ");
            String pass = br.readLine();
            System.out.println("\n\n...Logging you in...");
            Thread.sleep(2000);
            if(username.equals(user)&&password.equals(pass))
            {
                System.out.println("Login Successful.");
                System.out.println("Redirecting...");
                Thread.sleep(2000);
                login ob =new login();
                ob.menu();
            }
            else
            {
                if(invalidCount==2)
                {
                    System.out.println("Maximum number of attempts exceeded. Exiting...");
                    Thread.sleep(2000);
                    System.exit(0);
                }
                System.out.println("Invalid username or password...");
                System.out.println("Please try again");
                invalidCount++;
                Thread.sleep(2000);
                continue form;
            }
        }
    }
    void menu() throws Exception
    {
        masthead obj = new masthead();
        obj.print();
        System.out.println("\t \t \t Hello Administrator.\n");
        System.out.println("Press 1 to access administrative interface.");
        System.out.println("Press 2 to change your password.");
        System.out.println("Press 3 to exit.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int option=0;
        try{
        option = Integer.parseInt(br.readLine());
        }
        catch(Exception e)
        {
            System.out.println("\tInvalid input.");
            Thread.sleep(1000);
            System.out.println("\tTry again.");
            Thread.sleep(1000);
            menu();
        }
        if(option==1)
        {
            admininterface ob = new admininterface();
            ob.menu();
        }
        else if(option==2)
        {
            /**
             * Username extracted.
             */
            login ob1 = new login();
            FileReader file = new FileReader("admininfo.txt");
            BufferedReader fileInput = new BufferedReader(file);
            String username="", password="";
            String text;
            while((text=fileInput.readLine())!=null){
                text = de.decode(text);
                username = text.substring(0,text.indexOf(';'));
                password = text.substring(text.indexOf(';')+1,text.length());
             }
            file.close();
            String newpassword = "";
            System.out.println("Enter your new password: ");
            newpassword = br.readLine();
            if(newpassword.equals(password))
            {
                System.out.println("\tError. New password is same as the old password. Try again. \n\tTerminating...");
                Thread.sleep(2000);
                System.out.println("\tRedirecting.."); 
                Thread.sleep(3000);
                ob1.menu();
            }
            /**
             * New file over-written with new password.
             */
            FileWriter fw = new FileWriter("admininfo.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);
            outFile.println(en.encode(username+";"+newpassword));
            outFile.close();
            System.out.println("\tPassword successfuly changed..");
            System.out.println("\tRedirecting..");
            Thread.sleep(1000);
            ob1.menu();
        }
        else if(option==3)
        {
            System.out.println("\tExiting...");
            Thread.sleep(1000);
            System.exit(0);
        }
        else
        {
            System.out.println("\tInvalid choice.");
            System.out.println("\tTry again.");
            Thread.sleep(1000);
            menu();
        }
    }
}