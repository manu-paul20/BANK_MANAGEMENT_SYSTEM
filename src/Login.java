import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    JTextField uid_input = new JTextField();
    JTextField password_input = new JTextField();
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

        JLabel password_text = new JLabel("Enter User ID : ");
        password_text.setBounds(40,200,300,20);
        password_text.setFont(new Font("",Font.BOLD,20));
        add(password_text);

        //password input
        password_input.setBounds(190,200,300,30);
        password_input.setFont(new Font("",Font.BOLD,20));
        password_input.setSelectedTextColor(Color.BLUE);
        password_input.setForeground(Color.red);
        add(password_input);

        //login button
        login_button.setBounds(200,300,100,50);
        login_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login_button.setBackground(Color.BLUE);
        login_button.setForeground(Color.WHITE);
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
        signup_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signup_button.setBackground(Color.WHITE);
                signup_button.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
                if(uid_input.getText().isBlank() && password_input.getText().isBlank()){
                    signup_button.setBounds(500,300,150,50);
                    signup_button.setText("Fill all fields");
                    signup_button.setEnabled(false);

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
    public static void main(String[] args) {
     new Login();
    }
}
