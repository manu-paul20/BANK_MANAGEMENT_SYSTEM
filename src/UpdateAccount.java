import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateAccount extends JFrame{

    DB_CONNECTION connection = new DB_CONNECTION();

    UpdateAccount(String User_ID,String User_Name){

        String[] User_Details = getUserDetails(User_ID);

        ImageIcon edit = new ImageIcon(ClassLoader.getSystemResource("images/edit_icon.png"));
        Font DEFAULT_FONT = new Font("arial black",Font.PLAIN,20);
        Color LABEL_DEFAULT_COLOR = Color.red;
        int INPUT_WIDTH = 200;
        int INPUT_HEIGHT = 25;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocation(400,100);
        setSize(800,500);
        setBackground(Color.WHITE);
        setLayout(null);

        JLabel Heading  = new JLabel("Update Account");
        Heading.setBounds(340,5,200,50);
        Heading.setFont(new Font("arial black",Font.ITALIC,20));
        add(Heading);

        JLabel Name_Text = new JLabel("Name : ");
        Name_Text.setBounds(10,60,200,50);
        Name_Text.setFont(DEFAULT_FONT);
        Name_Text.setForeground(LABEL_DEFAULT_COLOR);
        add(Name_Text);

        JTextField Name = new JTextField(User_Name);
        Name.setBounds(100,74,INPUT_WIDTH,INPUT_HEIGHT);
        Name.setEnabled(false);
        Name.setFont(DEFAULT_FONT);
        add(Name);

        JLabel icon1 = new JLabel(edit);
        icon1.setBounds(270,35,100,100);
        icon1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Name.setEnabled(true);
            }
        });
        add(icon1);

        JLabel Address_Text = new JLabel("Address : ");
        Address_Text.setBounds(10,120,200,50);
        Address_Text.setFont(DEFAULT_FONT);
        Address_Text.setForeground(LABEL_DEFAULT_COLOR);
        add(Address_Text);

        JTextField Address = new JTextField(User_Details[0]);
        Address.setBounds(120,134,INPUT_WIDTH,INPUT_HEIGHT);
        Address.setEnabled(false);
        Address.setFont(DEFAULT_FONT);
        add(Address);

        JLabel icon2 = new JLabel(edit);
        icon2.setBounds(290,94,100,100);
        icon2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Address.setEnabled(true);
            }
        });
        add(icon2);

        JLabel Mobile_Number_Text = new JLabel("Mobile Number : ");
        Mobile_Number_Text.setBounds(10,180,200,50);
        Mobile_Number_Text.setFont(DEFAULT_FONT);
        Mobile_Number_Text.setForeground(LABEL_DEFAULT_COLOR);
        add(Mobile_Number_Text);

        JTextField Mobile_Number = new JTextField(User_Details[1]);
        Mobile_Number.setBounds(195,194,INPUT_WIDTH,INPUT_HEIGHT);
        Mobile_Number.setEnabled(false);
        Mobile_Number.setFont(DEFAULT_FONT);
        add(Mobile_Number);

        JLabel icon3 = new JLabel(edit);
        icon3.setBounds(365,154,100,100);
        icon3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Mobile_Number.setEnabled(true);
            }
        });
        add(icon3);

        JButton submit = new JButton("Submit");
        submit.addActionListener(e->{
            if(isUserExists("mobile_number",Mobile_Number.getText())){
                if(Mobile_Number.getText().equals(User_Details[1])){
                    updateUserDetails(User_ID,Name.getText(),Address.getText(),Mobile_Number.getText());
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,"Mobile number already exists");
                }
            }else{
                updateUserDetails(User_ID,Name.getText(),Address.getText(),Mobile_Number.getText());
                setVisible(false);
            }
            try{
                connection.connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        });
        submit.setBounds(320,300,200,30);
        submit.setBackground(Color.BLUE);
        submit.setForeground(Color.white);
        add(submit);


    }

    public boolean isUserExists(String searchParameterName,String searchParameterValue){
        String query = "SELECT * FROM user_details WHERE "+searchParameterName+" = '"+searchParameterValue+"'";
        ResultSet rs ;
        try{
            rs = connection.statement.executeQuery(query);
            if(rs.next()){
                rs.close();
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    String[] getUserDetails(String User_ID){
        String[] user_details;
        ResultSet rs;
        try{
            rs = connection.statement.executeQuery("SELECT address,mobile_number FROM user_details WHERE user_id='"+User_ID+"'");
            if(rs.next()){
                user_details = new String[] {rs.getString("address"),rs.getString("mobile_number")};
                rs.close();
            }else{
                user_details = null;
                rs.close();
            }
            return user_details;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    void updateUserDetails(String user_id,String user_name,String address,String mobile_number){
        try{
            connection.statement.executeUpdate(
                    "UPDATE user_details SET mobile_number = '"+mobile_number+"'," +
                            "address = '"+address+"'" +
                            " WHERE user_id = '"+user_id+"'"
            );
            connection.statement.executeUpdate(
                    "UPDATE users SET user_name = '"+user_name+"'" +
                            " WHERE user_id = '"+user_id+"'"
            );
            new Confirmation("Details updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new UpdateAccount("hr35","Manu");
    }
}
