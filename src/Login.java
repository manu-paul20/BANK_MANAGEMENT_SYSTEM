import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JTextField uid_input = new JTextField();
    JPasswordField password_input = new JPasswordField();
    JButton login_button = new JButton("Login");
    JButton signup_button = new JButton("Sign Up");
    Login(){
       setVisible(true);
       setLocation(400,40);
       setSize(800,500);
       setBackground(Color.WHITE);
        setLayout(null);

       JLabel heading = new JLabel("Bank Management System");
       heading.setBounds(80,10,800,80);
       heading.setFont(new Font("",Font.BOLD,50));
       add(heading);

       JLabel uid_text = new JLabel("Enter User ID : ");
        uid_text.setBounds(40,150,300,20);
        uid_text.setFont(new Font("",Font.BOLD,20));
       add(uid_text);

      //user ID input
        uid_input.setBounds(190,148,300,30);
        uid_input.setFont(new Font("",Font.BOLD,20));
        uid_input.setSelectedTextColor(Color.BLUE);
        uid_input.setForeground(Color.red);
        add(uid_input);

        JLabel password_text = new JLabel("Enter password : ");
        password_text.setBounds(40,200,300,20);
        password_text.setFont(new Font("",Font.BOLD,20));
        add(password_text);

        //password input
        password_input.setBounds(220,200,300,30);
        password_input.setFont(new Font("",Font.BOLD,20));
        password_input.setSelectedTextColor(Color.BLUE);
        password_input.setForeground(Color.red);
        add(password_input);

        //login button
        login_button.setBounds(200,300,100,50);
        login_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login_button.setBackground(Color.BLUE);
        login_button.setForeground(Color.WHITE);
        login_button.addActionListener(this);
        login_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                login_button.setBackground(Color.BLUE);
                login_button.setForeground(Color.WHITE);
                login_button.setBounds(200,300,100,50);
                login_button.setText("Login");
                login_button.setEnabled(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                login_button.setBackground(Color.WHITE);
                login_button.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
                login_button.setForeground(Color.black);
                if(uid_input.getText().isBlank() && password_input.getText().isBlank()){
                    login_button.setBounds(200,300,150,50);
                    login_button.setText("Fill all fields");
                    login_button.setEnabled(false);
                }
            }
        });
        add(login_button);

        //sign up button
        signup_button.setBounds(500,300,100,50);
        signup_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signup_button.setBackground(Color.blue);
        signup_button.setForeground(Color.white);
        signup_button.addActionListener(this);
        signup_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signup_button.setBackground(Color.WHITE);
                signup_button.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
                if(uid_input.getText().isBlank() && password_input.getText().isBlank()){
                    signup_button.setBounds(500,300,150,50);
                    signup_button.setText("Fill all fields");
                    signup_button.setEnabled(false);
                    signup_button.setForeground(Color.black);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signup_button.setBackground(Color.BLUE);
                signup_button.setForeground(Color.WHITE);
                signup_button.setBounds(500,300,100,50);
                signup_button.setText("Sign Up");
                signup_button.setEnabled(true);
            }
        });
        add(signup_button);



    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==login_button){
            String user_id = uid_input.getText().toLowerCase();
            String password = password_input.getText();
            String result = searchUser(user_id,password);
            if(result.equals("NOT_FOUND")){
                JOptionPane.showMessageDialog(
                        null,
                        "User not found\nPlease check details",
                        "ERROR",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }else{
                String message = "Welcome "+result;
                JOptionPane.showMessageDialog(null,message);
            }
        }
    }

    public static void main(String[] args) {
     new Login();
    }
    public String searchUser(String user_id,String password){
        String query = "SELECT * FROM users WHERE user_id = '"+user_id+"'";
        DB_CONNECTION connection = new DB_CONNECTION();
        try{
            ResultSet resultSet = connection.statement.executeQuery(query);
            if(resultSet.next()){
                String user_pass = resultSet.getString("password");
                String user_name = resultSet.getString("user_name");
                resultSet.close();
                System.out.println("Connection closed");
                return (user_pass.equals(password)) ? user_name : "NOT_FOUND";
            }
            resultSet.close();
            connection.connection.close();
            System.out.println("Connection closed");
            return "NOT_FOUND";
        }catch (Exception e){
            System.out.println(e);
            return "ERROR";
        }

    }
}
