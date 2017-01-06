import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

class browse {
   void display() throws Exception {
      Decrypt de = new Decrypt(); 
      FileReader file = new FileReader("bookmaster.txt");
      BufferedReader fileInput = new BufferedReader(file);
      String text = "";
      boolean entries = false;
      //This is going to read bookmaster.txt and extract all records of book. each entry in it has the following specs: book id(5), book name(30), author(30), publisher(25), status(10). This is for students, so only the first 5 columns are displayed i.e. total length of one entry - 100
      while((text = fileInput.readLine()) != null) {
         text = de.decode(text);
         int len = text.length() / 100;
         String[][] br = new String[len][5];
         System.out.println("Current records:");
         int option;
         for(option = 0; option < len; ++option) {
            br[option][0] = text.substring(option * 100, option * 100 + 5);
            br[option][1] = text.substring(option * 100 + 5, option * 100 + 35);
            br[option][2] = text.substring(option * 100 + 35, option * 100 + 65);
            br[option][3] = text.substring(option * 100 + 65, option * 100 + 90);
            br[option][4] = text.substring(option * 100 + 90, option * 100 + 100);
         }

         System.out.println("Id num          Book Name                      Author                   Publisher           Status  ");

         for(option = 0; option < len; ++option) {
            for(int j = 0; j < 5; ++j) {
               System.out.print(br[option][j]);
            }

            System.out.println();
         }
      }
      file.close();
      }
}
