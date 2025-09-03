import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DeleteAccount extends JFrame{
    DeleteAccount(String user_id,String pass){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(400,100);
        setSize(800,500);
        setBackground(Color.WHITE);
        setLayout(null);

        Font DEFAULT_FONT = new Font("arial black",Font.PLAIN,20);


       JLabel userId_text = new JLabel("User ID : ");
       userId_text.setBounds(10,20,200,50);
       userId_text.setFont(DEFAULT_FONT);
       add(userId_text);

       JLabel userId = new JLabel(user_id);
        userId.setBounds(120,20,200,50);
        userId.setFont(DEFAULT_FONT);
        userId.setForeground(Color.red);
        add(userId);

        JLabel reason_text = new JLabel("Reason for deletion (optional) : ");
        reason_text.setBounds(10,100,400,50);
        reason_text.setFont(DEFAULT_FONT);
        add(reason_text);

        JTextArea reason = new JTextArea();
        reason.setBounds(360,100,400,70);
        reason.setFont(DEFAULT_FONT);
        add(reason);

        JLabel pass_text = new JLabel("Password : ");
        pass_text.setBounds(10,200,200,50);
        pass_text.setFont(DEFAULT_FONT);
        add(pass_text);

        JTextField password = new JTextField();
        password.setBounds(150,215,200,30);
        password.setFont(DEFAULT_FONT);
        password.setForeground(Color.red);
        add(password);

        JButton confirm = new JButton("Confirm");
        confirm.setForeground(Color.white);
        confirm.setBackground(Color.BLUE);
        confirm.setBounds(310,330,200,50);
        confirm.addActionListener(e->{
            if(password.getText().isBlank()){
                JOptionPane.showMessageDialog(null,"Password field is required");
            }else{
                if(password.getText().equals(pass)){
                    int response = JOptionPane.showConfirmDialog(null, "Are you sure ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (response == 0) {
                        deleteUser(user_id);
                        setVisible(false);
                    } else {
                        new Confirmation("Account deletion cancelled");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong password");
                }
            }
        });
        add(confirm);

    }

    void deleteUser(String userId){
        DB_CONNECTION connection = new DB_CONNECTION();
        String query = "DELETE FROM users WHERE user_id = '"+userId+"'";
        try{
            connection.statement.executeUpdate(query);
            connection.connection.close();
            new Confirmation("Account deleted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
