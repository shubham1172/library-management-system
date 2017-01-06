import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 * This class handles the button actions for the database.
 */
class ActionHandler implements ActionListener{
    static final int ADD=1,EDIT=2,ISSUE=3,RETURN=4,DELETE=5;
    int code;
    public ActionHandler(int code){
        this.code=code;
    }
    public void actionPerformed(ActionEvent ae)
    {
        init();
    }
    private void init()
    {
        switch(code)
        {
            case 1:
            add_func();
            break;
            case 2:
            edit_func();
            break;
            case 3:
            issue_func();
            break;
            case 4:
            return_func();
            break;
            case 5:
            delete_func();
            break;
        }
    }
    private void add_func()
    {
        AddDialog aD = new AddDialog(adminPage.getFrame());
    }
    private void edit_func()
    {
        EditDialog eD = new EditDialog(adminPage.getFrame());
    }
    private void issue_func()
    {
        IssueDialog iD = new IssueDialog(adminPage.getFrame());
    }
    private void return_func()
    {
        ReturnDialog rD = new ReturnDialog(adminPage.getFrame());
    }
    private void delete_func()
    {
        DeleteDialog dD = new DeleteDialog(adminPage.getFrame());
    }
}