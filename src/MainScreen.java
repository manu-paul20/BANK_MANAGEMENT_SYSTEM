import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame{
    JButton Account_Details_Button = new JButton("Account Details");
    JButton Balance_Enquiry_Button = new JButton("Balance Enquiry");
    JButton Change_Password_Button = new JButton("Change Password");
    JButton Transaction_Button = new JButton("Withdraw / Deposit");
    JButton Delete_Account_Button = new JButton("Delete Account");
    JButton Update_Account_Button = new JButton("Update Account Details");
    MainScreen(String User_Name,String User_ID,String Password){
        Font DEFAULT_BUTTON_FONT = new Font("arial black",Font.BOLD,12);
        int DEFAULT_BUTTON_HEIGHT = 40;
        int DEFAULT_BUTTON_WIDTH = 200;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(400,100);
        setSize(800,500);
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel Greeting_Message = new JLabel("Welcome, "+User_Name);
        Greeting_Message.setFont(new Font("Arial Black", Font.BOLD,30));
        Greeting_Message.setBounds(270,10,500,60);
        add(Greeting_Message);

        JLabel Message = new JLabel("Please select from below");
        Message.setFont(new Font("Arial Black",Font.ITALIC,20));
        Message.setBounds(260,80,300,20);
        Message.setForeground(Color.BLUE);
        add(Message);


        Account_Details_Button.setFont(DEFAULT_BUTTON_FONT);
        Account_Details_Button.setBounds(80,150,DEFAULT_BUTTON_WIDTH,DEFAULT_BUTTON_HEIGHT);
        Account_Details_Button.setBackground(Color.BLUE);
        Account_Details_Button.setForeground(Color.white);
        Account_Details_Button.addActionListener(e -> {new AccountDetails(User_Name,User_ID);});
        add(Account_Details_Button);

        Balance_Enquiry_Button.setFont(DEFAULT_BUTTON_FONT);
        Balance_Enquiry_Button.setBounds(80,210,DEFAULT_BUTTON_WIDTH,DEFAULT_BUTTON_HEIGHT);
        Balance_Enquiry_Button.setBackground(Color.BLUE);
        Balance_Enquiry_Button.setForeground(Color.white);
        Balance_Enquiry_Button.addActionListener(e->{new BalanceEnquiry(User_ID,Password);});
        add(Balance_Enquiry_Button);

        Change_Password_Button.setFont(DEFAULT_BUTTON_FONT);
        Change_Password_Button.setBounds(80,270,DEFAULT_BUTTON_WIDTH,DEFAULT_BUTTON_HEIGHT);
        Change_Password_Button.setBackground(Color.BLUE);
        Change_Password_Button.setForeground(Color.white);
        Change_Password_Button.addActionListener(e->{
            new ChangePassword(User_ID,Password);
            setVisible(false);
        });
        add(Change_Password_Button);

        Transaction_Button.setFont(DEFAULT_BUTTON_FONT);
        Transaction_Button.setBounds(500,150,DEFAULT_BUTTON_WIDTH,DEFAULT_BUTTON_HEIGHT);
        Transaction_Button.setBackground(Color.BLUE);
        Transaction_Button.setForeground(Color.white);
        Transaction_Button.addActionListener(e->{
            new Transaction(User_ID,Password);
            setVisible(false);
        });
        add(Transaction_Button);

        Update_Account_Button.setFont(DEFAULT_BUTTON_FONT);
        Update_Account_Button.setBounds(500,210,DEFAULT_BUTTON_WIDTH,DEFAULT_BUTTON_HEIGHT);
        Update_Account_Button.setBackground(Color.BLUE);
        Update_Account_Button.setForeground(Color.white);
        Update_Account_Button.addActionListener(e->{
            new UpdateAccount(User_ID,User_Name);
            setVisible(false);
        });
        add(Update_Account_Button);

        Delete_Account_Button.setFont(DEFAULT_BUTTON_FONT);
        Delete_Account_Button.setBounds(500,270,DEFAULT_BUTTON_WIDTH,DEFAULT_BUTTON_HEIGHT);
        Delete_Account_Button.setBackground(Color.BLUE);
        Delete_Account_Button.setForeground(Color.white);
        Delete_Account_Button.addActionListener(e->{
            new DeleteAccount(User_ID,Password);
            setVisible(false);
        });
        add(Delete_Account_Button);




    }

}
