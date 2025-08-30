import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Transaction extends JFrame{

    DB_CONNECTION connection = new DB_CONNECTION();

    Transaction(String User_ID,String Password){
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
        User_ID_Text.setBounds(10,100,200,50);
        User_ID_Text.setFont(LABEL_DEFAULT_FONT);
        User_ID_Text.setForeground(LABEL_DEFAULT_COLOR);
        add(User_ID_Text);

        JLabel User_ID_Val = new JLabel(User_ID);
        User_ID_Val.setFont(VALUE_DEFAULT_FONT);
        User_ID_Val.setBounds(120,100,200,50);
        add(User_ID_Val);

        JLabel Amount_Text = new JLabel("Amount : ");
        Amount_Text.setBounds(10,160,200,50);
        Amount_Text.setFont(LABEL_DEFAULT_FONT);
        Amount_Text.setForeground(LABEL_DEFAULT_COLOR);
        add(Amount_Text);


        JTextField Amount = new JTextField();
        Amount.setBounds(150,175,300,25);
        Amount.setFont(VALUE_DEFAULT_FONT);
        add(Amount);

        JLabel Password_Label = new JLabel("Password : ");
        Password_Label.setBounds(10,220,200,50);
        Password_Label.setFont(LABEL_DEFAULT_FONT);
        Password_Label.setForeground(LABEL_DEFAULT_COLOR);
        Password_Label.setVisible(false);
        add(Password_Label);


        JTextField Password_Val = new JTextField();
        Password_Val.setBounds(150,235,300,25);
        Password_Val.setFont(VALUE_DEFAULT_FONT);
        Password_Val.setVisible(false);
        add(Password_Val);

        JLabel Transaction_Type = new JLabel("Transaction Type : ");
        Transaction_Type.setFont(LABEL_DEFAULT_FONT);
        Transaction_Type.setForeground(LABEL_DEFAULT_COLOR);
        Transaction_Type.setBounds(10,40,300,50);
        add(Transaction_Type);

        JRadioButton Withdraw = new JRadioButton("Withdraw");
        Withdraw.setBounds(250,40,200,50);
        Withdraw.setFont(VALUE_DEFAULT_FONT);
        Withdraw.addActionListener(e->{
            Password_Label.setVisible(true);
            Password_Val.setVisible(true);
        });
        add(Withdraw);

        JRadioButton Deposit = new JRadioButton("Deposit");
        Deposit.setBounds(500,40,200,50);
        Deposit.setFont(VALUE_DEFAULT_FONT);
        Deposit.addActionListener(e->{
            Password_Label.setVisible(false);
            Password_Val.setVisible(false);
        });
        add(Deposit);

        ButtonGroup Transaction_Type_BG = new ButtonGroup();
        Transaction_Type_BG.add(Withdraw);
        Transaction_Type_BG.add(Deposit);

        JButton Submit_Button = new JButton("Submit");
        Submit_Button.setBackground(Color.blue);
        Submit_Button.setForeground(Color.white);
        Submit_Button.setBounds(350,270,100,50);
        Submit_Button.addActionListener(e->{
            if(Withdraw.isSelected() && Password_Val.getText().equals(Password)){
                withdraw(User_ID,Integer.parseInt(Amount.getText()));
            }else if(Deposit.isSelected()){
                deposit(User_ID,Integer.parseInt(Amount.getText()));
            }else{
                JOptionPane.showMessageDialog(null,"Incorrect Password");
            }
            try{
                connection.connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            setVisible(false);
            new Confirmation("Transaction completed successfully");
        });
        add(Submit_Button);

    }

    void withdraw(String User_ID,int Amount) {
        try{
            connection.statement.executeUpdate("UPDATE user_details SET amount = amount - "+Amount+" WHERE user_id = '"+User_ID+"'");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void deposit(String User_ID,int Amount){
        try{
            connection.statement.executeUpdate("UPDATE user_details SET amount = amount + "+Amount+" WHERE user_id = '"+User_ID+"'");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Transaction("hr35","aaa");
    }
}
