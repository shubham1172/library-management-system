import java.io.*;
/**
 * The following class is used to delete the books.
 * In this process, the book status is changed to 'removed'.
 * If the book is issued, it cannot be removed. Availability is a must for deletion.
 */
class deletebooks
{
    Encrypt en = new Encrypt();
    Decrypt de = new Decrypt();
    void delete() throws Exception
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            FileReader file = new FileReader("bookmaster.txt");
            BufferedReader fileInput = new BufferedReader(file);
            String text = "";
            admininterface ob = new admininterface(); //used to redirect back to the menu.
            while((text = fileInput.readLine()) != null)        
                {
                    text = de.decode(text);
                    int len = text.length()/100;
                    String[][] arr = new String[len][5];
                    System.out.println("Current records:");
                    int option;
                    for(option = 0; option < len; option++) 
                    {
                        arr[option][0] = text.substring(option * 100, option * 100 + 5);
                        arr[option][1] = text.substring(option * 100 + 5, option * 100 + 35);
                        arr[option][2] = text.substring(option * 100 + 35, option * 100 + 65);
                        arr[option][3] = text.substring(option * 100 + 65, option * 100 + 90);
                        arr[option][4] = text.substring(option * 100 + 90, option * 100 + 100);
                    }
                    System.out.println("Id num          Book Name                      Author                   Publisher           Status  ");
                    for(option = 0; option < len; option++) 
                    {
                        for(int j = 0; j < 5; ++j) 
                        {
                                System.out.print(arr[option][j]);
                        }
                        System.out.println();
                    }
                    System.out.println("Enter the ID number:");
                    int ent = 0;
                    try
                    {
                        ent = Integer.parseInt(br.readLine());
                    }
                    catch(Exception e)
                    {
                        System.out.println("\tInvalid input.");
                        Thread.sleep(1000);
                        System.out.println("\tTry Again...");
                        ob.menu();
                    }
                    if((ent-1)>=Integer.parseInt(arr[len-1][0].trim()))
                    {
                        System.out.println("\tNo such record exists.");
                        Thread.sleep(1000);
                        System.out.println("\tTry again...");
                        ob.menu();
                    }
                    ent = ent - 1;
                    String status = arr[ent][4];
                    status = status.trim();
                    if(status.equals("available"))
                    {
                        System.out.println("The following book: " + arr[ent][1].trim() + " is available and can be deleted.");
                        System.out.println("Press 1 to confirm delete.");
                        System.out.println("Press 2 to cancel.");
                        int choice=0;
                        try
                        {
                            choice = Integer.parseInt(br.readLine());
                        }
                        catch(Exception e)
                        {
                            System.out.println("\tInvalid input.");
                            Thread.sleep(1000);
                            System.out.println("\tTry Again...");
                            ob.menu();
                        }
                        if(choice==1)
                        {
                            arr[ent][4] = "   removed";
                             //Writing the new file starts.
                             String file1 = "";
                             for(int count = 0; count < len; count++)
                             {
                                 file1 = file1 + arr[count][0] + arr[count][1] + arr[count][2] + arr[count][3] + arr[count][4];
                             }
                             /**
                             * New file over-written with new entries.
                             */
                            FileWriter fw = new FileWriter("bookmaster.txt");
                            BufferedWriter bw = new BufferedWriter(fw);
                            PrintWriter outFile = new PrintWriter(bw);
                            file1 = en.encode(file1);
                            outFile.println(file1);
                            outFile.close();    
                            System.out.println("The following book: " + arr[ent][1].trim() + " was successfully removed.");
                            Thread.sleep(4000);
                            ob.menu();                            
                        }
                        else if(choice==2)
                        {
                            System.out.println("Cancelling...");
                            Thread.sleep(2000);
                            ob.menu();
                        }
                        else
                        {
                            System.out.println("Invalid choice.");
                            System.out.println("Redirecting back to menu...");
                            Thread.sleep(2000);
                            ob.menu();
                        }
                    }
                    else if(status.equals("issued"))
                    {
                        System.out.println("The following book: " + arr[ent][1].trim() + " is already issued. \n The book should be available for deletion.");
                        System.out.println("Returning back to menu...");
                        Thread.sleep(4000);
                        ob.menu();
                    }
                    else
                    {
                        System.out.println("The following book: " + arr[ent][1].trim() + " is out of stock. \n This cannot be removed again.");
                        System.out.println("Returning back to menu...");
                        Thread.sleep(4000);
                        ob.menu();
                    }
                }
            }
    }