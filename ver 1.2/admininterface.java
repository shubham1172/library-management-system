import java.io.*;
class admininterface 
{
        void menu() throws Exception 
        {
            masthead obj = new masthead();
            admininterface ob1 = new admininterface(); //used to redirect the choices back to the main menu.
            obj.print();
            System.out.println("Hello Admin");
            System.out.println("Welcome to administrative interface...");
            System.out.println("Press 1 to view book entries.");
            System.out.println("Press 2 to edit book entries.");
            System.out.println("Press 3 to add book entries.");
            System.out.println("Press 4 to delete book entries.");
            System.out.println("Press 5 to issue books.");
            System.out.println("Press 6 to return books.");
            System.out.println("Press 7 to go back.");
            System.out.println("Press 8 to exit.");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int choice = 0;
            try{
                choice  = Integer.parseInt(br.readLine());
            // choice is the user input
            }
            catch(Exception e)
            {
                System.out.println("Invalid input..");
                Thread.sleep(1000);
                System.out.println("Try again...");
                menu();
            }
            if(choice==1)
            {
            obj.print();
            browse ob = new browse();
            ob.display();
            BufferedReader var9 = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\n\nEnter any character to exit.");
            String var10 = var9.readLine();
            Thread.sleep(1000);
            ob1.menu();
            }
            if(choice==2)
            {
                obj.print();
                editbooks objj = new editbooks();
                objj.edit();
                Thread.sleep(2000);
                ob1.menu();
            }
            else if(choice==3)
            {
                obj.print();
                addbooks objj = new addbooks();
                objj.add();
            }
            else if(choice==4)
            {
                obj.print();
                deletebooks objj = new deletebooks();
                objj.delete();
            }
            else if(choice==5)
            {
                obj.print();
                issuebooks objj = new issuebooks();
                objj.issue();
            }
            else if(choice==6)
            {
                obj.print();
                returnbooks objj = new returnbooks();
                objj.returnX();
            }
            else if(choice==7)
            {
                login ob = new login();
                ob.menu();
            }
            else if(choice==8)
            {
                System.out.println("Thank you for using Library Management System.");
                System.out.println("Created by shubhamsharma1172@gmail.com");
                Thread.sleep(2000);
                System.out.println("Exiting...");
                Thread.sleep(1000);
                System.exit(0);
            }
            else
            {
                System.out.println("Invalid choice.");
                Thread.sleep(1000);
                menu();
            }
        }
} 
