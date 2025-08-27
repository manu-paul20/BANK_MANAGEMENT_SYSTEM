import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Confirmation extends JFrame {
    JButton nextButton = new JButton("Go to Login");
    Confirmation(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(600,200);
        setSize(500,500);
        setLayout(null);


        ImageIcon confirmImage = new ImageIcon(ClassLoader.getSystemResource("images/GreenTick.png"));
        JLabel image = new JLabel(confirmImage);
        image.setBounds(100,0,300,300);
        add(image);

        JLabel confirmationMessage = new JLabel("Account Created Successfully");
        confirmationMessage.setBounds(80,300,500,50);
        confirmationMessage.setFont(new Font("arial black",Font.ITALIC,20));
        add(confirmationMessage);

        nextButton.setBounds(200,350,100,50);
        nextButton.setBackground(Color.GREEN);
        nextButton.setForeground(Color.black);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });
        add(nextButton);

    }
}
