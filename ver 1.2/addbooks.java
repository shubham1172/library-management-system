import java.io.*;
/**
 * The following class is used to add books to the bookmaster.
 */
class addbooks
{
    Encrypt en = new Encrypt();
    Decrypt de = new Decrypt();
    void add() throws Exception
    {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            FileReader file = new FileReader("bookmaster.txt");
            BufferedReader fileInput = new BufferedReader(file);
            String text = "";
            String space = "                              "; //used to adjust spaces while storing.
            String zero = "00000"; //used for adjusting length of SL number.
            admininterface ob = new admininterface(); //used to redirect back to the menu.
            while((text = fileInput.readLine()) != null)        
                {
                    text = de.decode(text);
                    int len = text.length()/100;
                    String[][] arr = new String[len][5];
                    int option;
                    for(option = 0; option < len; option++) {
                        arr[option][0] = text.substring(option * 100, option * 100 + 5);
                        arr[option][1] = text.substring(option * 100 + 5, option * 100 + 35);
                        arr[option][2] = text.substring(option * 100 + 35, option * 100 + 65);
                        arr[option][3] = text.substring(option * 100 + 65, option * 100 + 90);
                        arr[option][4] = text.substring(option * 100 + 90, option * 100 + 100);
                    }
                    System.out.println("Id num          Book Name                      Author                   Publisher           Status  ");
                    for(option = 0; option < len; option++) {
                    for(int j = 0; j < 5; ++j) {
                                System.out.print(arr[option][j]);
                    }
                    System.out.println();
                    }
                    int lastSL = Integer.parseInt(arr[len-1][0].trim());
                    String book, author, publisher;
                    System.out.println("Stock mode:");
                    System.out.println("Adding books..");
                    int iC=0; //invalidCount
                    addbook:
                    for(;;)
                    {
                        System.out.println("Enter the book name:");
                        System.out.println("(length limit -> 30)");
                        book = br.readLine();
                        book = book.trim();
                        if(book.length()>30)
                        {
                            System.out.println("Out of bounds exception.");
                            System.out.println("Please enter the field data within the length limit.");
                            System.out.println("Press 1 to retry.");
                            System.out.println("Press 2 to cancel.");
                            int choice = Integer.parseInt(br.readLine());
                            if(choice==1)
                            {
                            continue addbook;
                            }
                            else
                            {
                                System.out.println("Cancelling..");
                                Thread.sleep(2000);
                                ob.menu();
                            }
                        }
                        else if(book.equals(""))
                        {
                            if(iC==2)
                            {
                                System.out.println("Too many invalid attempts.");
                                Thread.sleep(1000);
                                System.out.println("Redirecting you back.");
                                Thread.sleep(2000);
                                ob.menu();
                            }
                            iC++;
                            System.out.println("Blank Entry.");
                            Thread.sleep(1000);
                            System.out.println("Retry.");
                            Thread.sleep(1000);
                            continue addbook;
                        }
                        else 
                        {
                            book = space.substring(0,30-book.length()) + book;
                            break addbook;
                        }
                    }
                    iC=0;
                    addauthor:
                    for(;;)
                    {
                        System.out.println("Enter the author name:");
                        System.out.println("(length limit -> 30)");
                        author = br.readLine();
                        author = author.trim();
                        if(author.length()>30)
                        {
                            System.out.println("Out of bounds exception.");
                            System.out.println("Please enter the field data within the length limit.");
                            System.out.println("Press 1 to retry.");
                            System.out.println("Press 2 to cancel.");
                            int choice = Integer.parseInt(br.readLine());
                            if(choice==1)
                            {
                            continue addauthor;
                            }
                            else
                            {
                                System.out.println("Cancelling..");
                                Thread.sleep(2000);
                                ob.menu();
                            }
                        }
                        else if(author.equals(""))
                        {
                            if(iC==2)
                            {
                                System.out.println("Too many invalid attempts.");
                                Thread.sleep(1000);
                                System.out.println("Redirecting you back.");
                                Thread.sleep(2000);
                                ob.menu();
                            }
                            iC++;
                            System.out.println("Blank Entry.");
                            Thread.sleep(1000);
                            System.out.println("Retry.");
                            Thread.sleep(1000);
                            continue addauthor;
                        }
                        else 
                        {
                            author = space.substring(0,30-author.length()) + author;
                            break addauthor;
                        }
                    }
                    iC=0;
                    addpublisher:
                    for(;;)
                    {
                        System.out.println("Enter the publisher name:");
                        System.out.println("(length limit -> 25)");
                        publisher = br.readLine();
                        publisher = publisher.trim();
                        if(publisher.length()>25)
                        {
                            System.out.println("Out of bounds exception.");
                            System.out.println("Please enter the field data within the length limit.");
                            System.out.println("Press 1 to retry.");
                            System.out.println("Press 2 to cancel.");
                            int choice = Integer.parseInt(br.readLine());
                            if(choice==1)
                            {
                            continue addpublisher;
                            }
                            else
                            {
                                System.out.println("Cancelling..");
                                Thread.sleep(2000);
                                ob.menu();
                            }
                        }
                        else if(publisher.equals(""))
                        {
                            if(iC==2)
                            {
                                System.out.println("Too many invalid attempts.");
                                Thread.sleep(1000);
                                System.out.println("Redirecting you back.");
                                Thread.sleep(2000);
                                ob.menu();
                            }
                            iC++;
                            System.out.println("Blank Entry.");
                            Thread.sleep(1000);
                            System.out.println("Retry.");
                            Thread.sleep(1000);
                            continue addpublisher;
                        }
                        else 
                        {
                            publisher = space.substring(0,25-publisher.length()) + publisher;
                            break addpublisher;
                        }
                    }
                    boolean flag = false;
                    for(int counter = 0; counter < len; counter++)
                    {
                        if((arr[counter][1]).equals(book))
                        {
                            if(arr[counter][2].equals(author)&&arr[counter][3].equals(publisher))
                            {
                                if(arr[counter][4].equals("   removed"))
                                {
                                    System.out.println("The following book was removed earlier and is now available.");
                                    Thread.sleep(1000);
                                    System.out.println("(Entry no. " + Integer.parseInt(arr[counter][0].trim()) + " modified)");
                                    Thread.sleep(1000);
                                    arr[counter][4] = " available";
                                    flag = true;
                                }
                                else
                                {
                                    System.out.println("The following book already exists in the database.");
                                    Thread.sleep(2000);
                                    System.out.println("Exiting.");
                                    Thread.sleep(1000);
                                    ob.menu();
                                }
                            }
                        }
                    }
                    if(flag==false)
                    {
                    //adding the input values to the array for parsing into a new file.
                    String[][] arrr = new String[len+1][5];
                    arrr[len][0] = Integer.toString(lastSL+1);
                    arrr[len][0] = zero.substring(0,5-arrr[len][0].length()) + arrr[len][0];
                    arrr[len][1] = book;
                    arrr[len][2] = author;
                    arrr[len][3] = publisher;
                    arrr[len][4] = " available";
                    for(int counter = 0; counter <5; counter++)
                    {
                        text = text + arrr[len][counter];
                    }
                    System.out.println("The following book: " + arrr[len][1].trim() + " was successfully added and is now available.");
                    }
                    else
                    {
                        text = "";
                        for(int i=0; i<len; i++)
                        {
                            for(int j=0; j<5; j++)
                            {
                                text = text + arr[i][j];
                            }
                        }
                    }
                   //new text file written for overwriting.
                    /**
                    * New file over-written with new entries.
                    */
                    FileWriter fw = new FileWriter("bookmaster.txt");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter outFile = new PrintWriter(bw);
                    text = en.encode(text);
                    outFile.println(text);
                    outFile.close();    
                    Thread.sleep(1000);
                    System.out.println("Press 1 to add more books.");
                    System.out.println("Press 2 to exit.");
                    int choice = 0;
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
                        this.add();
                    }
                    else if(choice==2)
                    {
                        System.out.println("Exiting... Returning back to main menu.");
                        Thread.sleep(2000);
                        ob.menu();
                    }
                    else
                    {
                        System.out.println("Invalid choice");
                        System.out.println("Returning back to main menu.");
                        Thread.sleep(2000);
                        ob.menu();
                    }
                }
            }
        }