import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceEnquiry extends  JFrame{

    DB_CONNECTION connection = new DB_CONNECTION();

    BalanceEnquiry(String User_ID, String password){
        Font LABEL_DEFAULT_FONT = new Font("arial black",Font.BOLD,20);
        Font VALUE_DEFAULT_FONT = new Font("arial black",Font.PLAIN,20);
        Color LABEL_DEFAULT_COLOR = Color.red;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(400, 100);
        setSize(800, 400);
        setBackground(Color.WHITE);
        setLayout(null);


        JLabel User_ID_Text = new JLabel("User ID : ");
        User_ID_Text.setBounds(10,10,200,50);
        User_ID_Text.setFont(LABEL_DEFAULT_FONT);
        User_ID_Text.setForeground(LABEL_DEFAULT_COLOR);
        add(User_ID_Text);

        JLabel User_ID_Val = new JLabel(User_ID);
        User_ID_Val.setFont(VALUE_DEFAULT_FONT);
        User_ID_Val.setBounds(120,10,200,50);
        add(User_ID_Val);

        JLabel Password_Text = new JLabel("Password : ");
        Password_Text.setBounds(10,70,200,50);
        Password_Text.setFont(LABEL_DEFAULT_FONT);
        Password_Text.setForeground(LABEL_DEFAULT_COLOR);
        add(Password_Text);


        JTextField Password_Val  = new JTextField();
        Password_Val.setBounds(150,85,300,25);
        Password_Val.setFont(VALUE_DEFAULT_FONT);
        add(Password_Val);

        JLabel Amount = new JLabel("Amount : 1000000");
        Amount.setForeground(LABEL_DEFAULT_COLOR);
        Amount.setBounds(100,110,800,80);
        Amount.setFont(new Font("arial black",Font.BOLD,50));
        Amount.setVisible(false);
        add(Amount);

        JButton Check_Amount_Button = new JButton("Check Amount");
        Check_Amount_Button.setBounds(255,250,300,50);
        Check_Amount_Button.setFont(LABEL_DEFAULT_FONT);
        Check_Amount_Button.setBackground(Color.blue);
        Check_Amount_Button.setForeground(Color.white);
        Check_Amount_Button.addActionListener(e->{
            if(Check_Amount_Button.getText().equals("Check Amount")){
                if (Password_Val.getText().equals(password)) {
                    Amount.setText("Amount : "+getCurrentBalance(User_ID));
                    Amount.setVisible(true);
                    Check_Amount_Button.setText("Close");
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Password");
                }
            }else{
                setVisible(false);
            }
        });
        add(Check_Amount_Button);
    }

    String getCurrentBalance(String User_ID){
        try{
            ResultSet rs = connection.statement.executeQuery("SELECT amount FROM user_details WHERE user_id = '"+User_ID+"'");
            if(rs.next()){
                String Amount = rs.getString("amount");
                rs.close();
                return Amount;
            }
            return "";
        } catch (SQLException e) {
            System.out.println(e);
            return "";
        }
    }
}
