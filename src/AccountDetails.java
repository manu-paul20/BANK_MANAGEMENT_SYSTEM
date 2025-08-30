import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDetails extends JFrame {

    DB_CONNECTION connection = new DB_CONNECTION();

    AccountDetails(String User_Name, String User_ID) {

        String[] User_Details = getUserDetails(User_ID);

        Font LABEL_DEFAULT_FONT = new Font("arial black", Font.BOLD, 20);
        Font VALUE_DEFAULT_FONT = new Font("arial black", Font.PLAIN, 20);
        Color LABEL_DEFAULT_COLOR = Color.BLUE;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(400, 100);
        setSize(800, 500);
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Account Details");
        heading.setBounds(340, 10, 200, 50);
        heading.setFont(new Font("arial black", Font.ITALIC, 20));
        add(heading);

        JLabel underScore = new JLabel("___________________________");
        underScore.setBounds(340, 15, 200, 50);
        add(underScore);

        JLabel User_ID_Label = new JLabel("User ID : ");
        User_ID_Label.setFont(LABEL_DEFAULT_FONT);
        User_ID_Label.setBounds(10, 70, 300, 50);
        User_ID_Label.setForeground(LABEL_DEFAULT_COLOR);
        add(User_ID_Label);

        JLabel User_Id_VAL = new JLabel(User_ID);
        User_Id_VAL.setFont(VALUE_DEFAULT_FONT);
        User_Id_VAL.setBounds(120, 70, 300, 50);
        add(User_Id_VAL);

        JLabel User_Name_Label = new JLabel("A/C Holder Name : ");
        User_Name_Label.setBounds(10, 120, 300, 50);
        User_Name_Label.setFont(LABEL_DEFAULT_FONT);
        User_Name_Label.setForeground(LABEL_DEFAULT_COLOR);
        add(User_Name_Label);

        JLabel User_Name_VAL = new JLabel(User_Name);
        User_Name_VAL.setFont(VALUE_DEFAULT_FONT);
        User_Name_VAL.setBounds(230, 120, 600, 50);
        add(User_Name_VAL);

        JLabel Address_Label = new JLabel("Address : ");
        Address_Label.setFont(LABEL_DEFAULT_FONT);
        Address_Label.setBounds(10, 170, 600, 50);
        Address_Label.setForeground(Color.BLUE);
        add(Address_Label);

        JLabel Address = new JLabel(User_Details[0]);
        Address.setFont(VALUE_DEFAULT_FONT);
        Address.setBounds(130, 170, 600, 50);
        add(Address);


        JLabel Mobile_Number_Label = new JLabel("Mobile_Number : ");
        Mobile_Number_Label.setFont(LABEL_DEFAULT_FONT);
        Mobile_Number_Label.setBounds(10, 220, 600, 50);
        Mobile_Number_Label.setForeground(Color.BLUE);
        add(Mobile_Number_Label);

        JLabel Mobile_Number = new JLabel(User_Details[1]);
        Mobile_Number.setFont(VALUE_DEFAULT_FONT);
        Mobile_Number.setBounds(210, 220, 600, 50);
        add(Mobile_Number);

        JLabel Account_Type_Label = new JLabel("Account Type : ");
        Account_Type_Label.setFont(LABEL_DEFAULT_FONT);
        Account_Type_Label.setBounds(10, 270, 600, 50);
        Account_Type_Label.setForeground(Color.BLUE);
        add(Account_Type_Label);

        JLabel Account_Type = new JLabel(User_Details[2]);
        Account_Type.setFont(VALUE_DEFAULT_FONT);
        Account_Type.setBounds(210, 270, 600, 50);
        add(Account_Type);


        JButton Close_Button = new JButton("Close");
        Close_Button.setBounds(350, 350, 100, 50);
        Close_Button.setFont(LABEL_DEFAULT_FONT);
        Close_Button.addActionListener(e -> {
            setVisible(false);
        });
        add(Close_Button);


    }

    String[] getUserDetails(String User_ID) {
        String[] UserDetails;
        String Address = "";
        String Mobile_Number = "";
        String Account_Type = "";

        try {
            ResultSet UserTable = connection.statement.executeQuery("SELECT * FROM user_details WHERE user_id = '" + User_ID + "'");
            if (UserTable.next()) {
                Address = UserTable.getString("address");
                Mobile_Number = UserTable.getString("mobile_number");
                Account_Type = UserTable.getString("account_type");
            }

            UserDetails = new String[]{Address, Mobile_Number, Account_Type};
            connection.connection.close();
            UserTable.close();
        } catch (SQLException e) {
            System.out.println(e);
            UserDetails = null;
        }
        return UserDetails;
    }
}