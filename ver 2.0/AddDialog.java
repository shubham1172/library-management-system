import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Creates a dialog box for adding books.
 */
class AddDialog extends JDialog
{
    JLabel background,bookname,authorname,pubname,message;
    JTextField book,author,publisher;
    JButton update,close;
    JPanel gridpanels[][];
    /** Default constructor */
    public AddDialog(JFrame parent)
    {
        super(parent,"ADD BOOK:", false);
        setLayout(new BorderLayout());
        setSize(800,500);
        setLocationRelativeTo(null);
        setVisible(true);
        if((adminPage.getRows().length)>0)
        {
            addDelDialog aD = new addDelDialog(adminPage.getFrame());
        }
        else
        init();
    }
    private void init()
    {
        setupLabels();
        setupGrids();
        setupTextFields();
        setupButtons();
        setupComponents();
    }
    private void setupLabels()
    {
        background = new JLabel(new ImageIcon(Path.getAdd()+"resources\\dialogBGG.jpg"));
        background.setLayout(new GridLayout(6,2));
        bookname = new JLabel("BOOK NAME:");
        bookname.setForeground(Color.WHITE);
        authorname = new JLabel("AUTHOR:");
        authorname.setForeground(Color.WHITE);
        pubname = new JLabel("PUBLISHER:");
        pubname.setForeground(Color.WHITE);
        bookname.setFont(new Font("Arial",Font.BOLD,14));
        authorname.setFont(new Font("Arial",Font.BOLD,14));
        pubname.setFont(new Font("Arial",Font.BOLD,14));
        message = new JLabel();
        message.setFont(new Font("Arial",Font.BOLD+Font.ITALIC,16));
        message.setForeground(Color.RED);
    }
    private void setupGrids()
    {
        gridpanels = new JPanel[6][2];
        for(int i=0;i<6;i++)
            for(int j=0;j<2;j++)
            {
                gridpanels[i][j] = new JPanel();
                gridpanels[i][j].setOpaque(false);
                background.add(gridpanels[i][j]);
            }
    }
    private void setupTextFields()
    {
        book = new JTextField(30);
        book.setText("Name of the book");
        book.setForeground(Color.GRAY);
        author = new JTextField(30);
        author.setText("Author's name");
        author.setForeground(Color.GRAY);
        publisher = new JTextField(30);
        publisher.setText("Publisher's name");
        publisher.setForeground(Color.GRAY);
        book.addFocusListener(new FocusHandler(0));
        author.addFocusListener(new FocusHandler(1));
        publisher.addFocusListener(new FocusHandler(2));
    }
    private void setupButtons()
    {
        update = new JButton("UPDATE");
        update.setSize(100,25);
        update.addActionListener(new ButtonHandler(0));
        close = new JButton("CLOSE");
        close.setSize(100,25);
        close.addActionListener(new ButtonHandler(1));
    }
    private void setupComponents()
    {
        gridpanels[1][0].add(bookname);gridpanels[1][1].add(book);
        gridpanels[2][0].add(authorname);gridpanels[2][1].add(author);
        gridpanels[3][0].add(pubname);gridpanels[3][1].add(publisher);
        gridpanels[4][0].add(update);gridpanels[4][0].add(close);
        gridpanels[5][0].add(message);
        add(background,"Center");
    }
    class FocusHandler implements FocusListener{
        int code;
        public FocusHandler(int code){
            this.code = code;
        }
        public void focusLost(FocusEvent fe)
        {
            switch(code)
            {
                case 0:
                if((book.getText()).equals("")){
                    book.setText("Name of the book");
                    book.setForeground(Color.GRAY);
                }
                break;
                case 1:
                if((author.getText()).equals("")){
                    author.setText("Author's name");
                    author.setForeground(Color.GRAY);
                }
                break;
                case 2:
                if((publisher.getText()).equals("")){
                    publisher.setText("Publisher's name");
                    publisher.setForeground(Color.GRAY);
                }
            }
        }
        public void focusGained(FocusEvent fe)
        {
            switch(code)
            {
                case 0:
                if((book.getText()).equals("Name of the book")){
                    book.setText("");
                    book.setForeground(Color.BLACK);
                }
                break;
                case 1:
                if((author.getText()).equals("Author's name")){
                    author.setText("");
                    author.setForeground(Color.BLACK);
                }
                break;
                case 2:
                if((publisher.getText()).equals("Publisher's name")){
                    publisher.setText("");
                    publisher.setForeground(Color.BLACK);
                }
            }
        }
    }
    class ButtonHandler implements ActionListener{
        int code;
        public ButtonHandler(int code){
            this.code = code;
        }
        public void actionPerformed(ActionEvent ae)
        {
            switch(code)
            {
                case 1:
                //Close
                setVisible(false);
                dispose();
                break;
                case 0:
                //update
                String bookS = book.getText().trim(), authorS = author.getText().trim(), publisherS = publisher.getText().trim();
                BookReader br = new BookReader();
                //check rules
                if(br.checkMatch(bookS,authorS,publisherS))
                    message.setText("The following entry already exists: Book ID" + br.getID(bookS,authorS,publisherS));
                else if(bookS.indexOf(";")>-1||authorS.indexOf(";")>-1||publisherS.indexOf(";")>-1)
                    message.setText("Illegal character - ';' present in entry.");
                    else if(bookS.equals("")||authorS.equals("")||publisherS.equals("")||bookS.equals("Name of the book")||authorS.equals("Author's name")||publisherS.equals("Publisher's name"))
                    message.setText("Empty field not allowed.");
                else {
                    BookWriter bw = new BookWriter();
                    try{
                        bw.add(bookS,authorS,publisherS);
                        Logger.append(br.getID(bookS,authorS,publisherS),Logger.ADD);
                    }
                    catch(Exception e){}
                    message.setText("Updated successfuly!");
                    adminPage.update();
                }
                break;
            }
        }
    }
}