import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ChangePassword extends JFrame {
    ChangePassword(String userId, String pass){

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(400,100);
        setSize(800,300);
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel Heading  = new JLabel("Change Password");
        Heading.setBounds(330,5,200,50);
        Heading.setFont(new Font("arial black",Font.ITALIC,20));
        add(Heading);

        Font DEFAULT_FONT = new Font("arial black",Font.PLAIN,20);
        Color LABEL_DEFAULT_COLOR = Color.red;
        int INPUT_WIDTH = 200;
        int INPUT_HEIGHT = 25;

        JLabel old_pass_text = new JLabel("Old password : ");
        old_pass_text.setBounds(10,60,200,50);
        old_pass_text.setFont(DEFAULT_FONT);
        old_pass_text.setForeground(LABEL_DEFAULT_COLOR);
        add(old_pass_text);

        JTextField old_pass = new JTextField();
        old_pass.setBounds(190,74,INPUT_WIDTH,INPUT_HEIGHT);
        old_pass.setFont(DEFAULT_FONT);
        add(old_pass);

        JLabel new_pass_text = new JLabel("New password : ");
        new_pass_text.setBounds(10,120,200,50);
        new_pass_text.setFont(DEFAULT_FONT);
        new_pass_text.setForeground(LABEL_DEFAULT_COLOR);
        add(new_pass_text);

        JTextField new_pass = new JTextField();
        new_pass.setBounds(200,134,INPUT_WIDTH,INPUT_HEIGHT);
        new_pass.setFont(DEFAULT_FONT);
        add(new_pass);

        JButton confirm = new JButton("Confirm");
        confirm.setBounds(320,210,200,30);
        confirm.setBackground(Color.BLUE);
        confirm.setForeground(Color.white);
        confirm.addActionListener(e->{
            if(old_pass.getText().equals(pass)){
                changePass(userId, new_pass.getText());
            }else{
                JOptionPane.showMessageDialog(null,"Please check old password");
            }
        });
        add(confirm);

    }

    void changePass(String userId,String newPass){
        DB_CONNECTION connection = new DB_CONNECTION();
        String query = "UPDATE users SET password = '"+newPass+"' WHERE user_id = '"+userId+"'";
        try{
            connection.statement.executeUpdate(query);
            connection.connection.close();
            new Confirmation("Password Changes Successfully");
            setVisible(false);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
