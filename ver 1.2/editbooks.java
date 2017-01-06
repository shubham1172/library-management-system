import java.io.*;
class editbooks
    {          
    /**
     * The following class is used to edit the books.
     * At once, one edit is permitted only.
     * Spaces are automatically adjusted according to the field space.
     */
     Encrypt en = new Encrypt();
     Decrypt de = new Decrypt();
     void edit() throws Exception
            {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            FileReader file = new FileReader("bookmaster.txt");
            BufferedReader fileInput = new BufferedReader(file);
            String text = "";   
            int xcount = 0; //Checks if any change has taken place in the edit mode.
            admininterface ob = new admininterface();
            while((text = fileInput.readLine()) != null)        
                    {
                    text = de.decode(text);
                    int len = text.length()/100;
                    String[][] arr = new String[len][5];
                    System.out.println("Current records:");
                    int option;
                    xcount = 0;
                    String space = "                              "; //used for entering spaces.
                    for(option = 0; option < len; option++) {
                        arr[option][0] = text.substring(option * 100, option * 100 + 5);
                        arr[option][1] = text.substring(option * 100 + 5, option * 100 + 35);
                        arr[option][2] = text.substring(option * 100 + 35, option * 100 + 65);
                        arr[option][3] = text.substring(option * 100 + 65, option * 100 + 90);
                        arr[option][4] = text.substring(option * 100 + 90, option * 100 + 100);
                    }
                    System.out.println("Id num          Book Name                      Author                   Publisher           Status  ");
                    for(option = 0; option < len; option++) 
                    {
                        for(int j = 0; j < 5; ++j) {
                                System.out.print(arr[option][j]);
                        }
                        System.out.println();
                    }
                    System.out.println("Enter the entry number:");
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
                        Thread.sleep(1000);
                        ob.menu();
                    }
                    if((ent-1)>=Integer.parseInt(arr[len-1][0].trim()))
                    {
                        System.out.println("\tNo such record exists.");
                        Thread.sleep(1000);
                        System.out.println("\tTry again...");
                        Thread.sleep(1000);
                        ob.menu();
                    }
                    ent = ent - 1;
                    //Entry editing is a step by step process.
                    String edit = "";
                    System.out.println("Edit mode:");
                    System.out.println("Note: If you want to keep the same value, enter \"X\".");                    
                    //Book Name.
                    Book:
                    for(;;)
                    {
                        System.out.println("Enter the new book name:");
                        edit = br.readLine();
                        if(edit.equals("X")||edit.equals("x"))
                        {
                            xcount++;
                            break Book;
                        }
                        else if((edit.trim()).equals(""))
                        {
                            System.out.println("\tBlank input.");
                            Thread.sleep(1000);
                            System.out.println("\tRetry...");
                            continue Book;
                        }
                        else if((edit.trim()).equals(arr[ent][1].trim()))
                        {
                            System.out.println("You have entered the same value... Retry.");
                            Thread.sleep(1000);
                            continue Book;
                        }
                        else
                        {
                            arr[ent][1] = space.substring(0,30-edit.length()) + edit;
                            break Book;
                        }
                    }
                    //Author Name.
                    Author:
                    for(;;)
                    {
                        System.out.println("Enter the new author name:");
                        edit = br.readLine();
                        if(edit.equals("X")||edit.equals("x"))
                        {
                            xcount++;
                            break Author;
                        }
                        else if((edit.trim()).equals(""))
                        {
                            System.out.println("\tBlank input.");
                            Thread.sleep(1000);
                            System.out.println("\tRetry...");
                            continue Author;
                        }
                        else if((edit.trim()).equals(arr[ent][2].trim()))
                        {
                            System.out.println("You have entered the same value... Retry.");
                            Thread.sleep(1000);
                            continue Author;
                        }
                        else
                        {
                            arr[ent][2] = space.substring(0,30-edit.length()) + edit;
                            break Author;
                        }       
                    }
                    //Publisher Name.
                    Publisher:
                    for(;;)
                    {
                        System.out.println("Enter the new publisher name:");
                        edit = br.readLine();
                        if(edit.equals("X")||edit.equals("x"))
                        {
                            xcount++;
                            break Publisher;
                        }
                        else if((edit.trim()).equals(""))
                        {
                            System.out.println("\tBlank input.");
                            Thread.sleep(1000);
                            System.out.println("\tRetry...");
                            continue Publisher;
                        }
                        else if((edit.trim()).equals(arr[ent][3].trim()))
                        {
                            System.out.println("You have entered the same value... Retry.");
                            Thread.sleep(1000);
                            continue Publisher;
                        }
                        else
                        {
                            arr[ent][3] = space.substring(0,25-edit.length()) + edit;
                            break Publisher;
                        }
                    }
                    if(xcount==3)
                    {
                        System.out.println("\tNo changes are made.");
                        Thread.sleep(1000);
                        System.out.println("\tExiting edit mode...");
                        Thread.sleep(1000);
                        break;
                    }
                    System.out.println("\tEdit Successful.");
                    System.out.println("\tEntry number " + (ent+1) + " changed.");
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
                    break;
                }
            }
    }