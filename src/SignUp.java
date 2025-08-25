import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {

    JButton submitButton = new JButton("Generate User ID");
    DB_CONNECTION connection = new DB_CONNECTION();
    JTextField nameInput = new JTextField();
    JTextField addressInput = new JTextField();
    JTextField passwordInput = new JTextField();
    JTextField userIDInput = new JTextField();
    JTextField mobileNumberInput = new JTextField();
    JTextField initialAmountInput = new JTextField();
    JRadioButton currentAccount = new JRadioButton("Current");
    JRadioButton savingsAccount = new JRadioButton("Savings");
    JLabel message = new JLabel("Please note user id for future use");
    SignUp(){
        setVisible(true);
        setLocation(400,40);
        setSize(800,700);
        setLayout(null);

        Font TEXT_FONT = new Font("",Font.BOLD,20);
        int INPUT_AREA_WIDTH = 200;
        int INPUT_AREA_HEIGHT = 25;

        JLabel heading = new JLabel("Sign Up");
        heading.setBounds(350,2,120,50);
        heading.setFont(new Font("",Font.BOLD,30));
        heading.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
        add(heading);

        JLabel nameText = new JLabel("Name");
        nameText.setBounds(10,90,150,20);
        nameText.setFont(new Font("",Font.BOLD,20));
        add(nameText);

       // name input
        nameInput.setBounds(70,90,INPUT_AREA_WIDTH,INPUT_AREA_HEIGHT);
        nameInput.setFont(TEXT_FONT);
        add(nameInput);

        JLabel addressText = new JLabel("Address");
        addressText.setBounds(10,170,150,20);
        addressText.setFont(TEXT_FONT);
        add(addressText);

       // address input
        addressInput.setBounds(100,170,INPUT_AREA_WIDTH,INPUT_AREA_HEIGHT);
        addressInput.setFont(TEXT_FONT);
        add(addressInput);

        JLabel passwordText = new JLabel("Password");
        passwordText.setBounds(10,250,100,20);
        passwordText.setFont(TEXT_FONT);
        add(passwordText);

        // password input
        passwordInput.setBounds(120,250,INPUT_AREA_WIDTH,INPUT_AREA_HEIGHT);
        passwordInput.setFont(TEXT_FONT);
        add(passwordInput);

        JLabel userIDText = new JLabel("User ID");
        userIDText.setBounds(10,330,100,20);
        userIDText.setFont(TEXT_FONT);
        add(userIDText);

        // user id input
        userIDInput.setBounds(90,330,INPUT_AREA_WIDTH,INPUT_AREA_HEIGHT);
        userIDInput.setFont(TEXT_FONT);
        userIDInput.setEnabled(true);
        userIDInput.setForeground(Color.red);
        userIDInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                userIDInput.setEnabled(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userIDInput.setEnabled(true);
            }
        });
        add(userIDInput);

        JLabel mobileNumberText = new JLabel("Mobile Number");
        mobileNumberText.setBounds(400,90,200,20);
        mobileNumberText.setFont(TEXT_FONT);
        add(mobileNumberText);

        // mobile number input
        mobileNumberInput.setBounds(550,90,INPUT_AREA_WIDTH,INPUT_AREA_HEIGHT);
        mobileNumberInput.setFont(TEXT_FONT);
        add(mobileNumberInput);

        JLabel initialAmountText = new JLabel("Initial Amount");
        initialAmountText.setBounds(400,170,200,20);
        initialAmountText.setFont(TEXT_FONT);
        add(initialAmountText);

        // initial amount input
        initialAmountInput.setBounds(550,170,INPUT_AREA_WIDTH,INPUT_AREA_HEIGHT);
        initialAmountInput.setFont(TEXT_FONT);
        add(initialAmountInput);

        JLabel accountTypeText = new JLabel("Account Type : ");
        accountTypeText.setBounds(10,410,200,30);
        accountTypeText.setFont(TEXT_FONT);
        add(accountTypeText);

        //account type

       //savings account
        savingsAccount.setBounds(180,420,100,20);
        add(savingsAccount);

      // current account
        currentAccount.setBounds(300,420,150,20);
        add(currentAccount);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(savingsAccount);
        buttonGroup.add(currentAccount);


        // submit button
        submitButton.setBounds(300,500,200,30);
        submitButton.setBackground(Color.BLUE);
        submitButton.setForeground(Color.white);
        submitButton.addActionListener(this);
        add(submitButton);

        message.setBounds(250,600,500,30);
        message.setFont(TEXT_FONT);
        message.setForeground(Color.red);
        message.setVisible(false);
        add(message);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submitButton){
            if(submitButton.getText() == "Generate User ID" && validateUserDetails() && !(isUserExists(mobileNumberInput.getText()))){
                //generating a user id until a random user id is generated
                String userId;
                do{
                    userId = generateUserId();
                }while (isUserExists(userId));
                userIDInput.setText(userId);
                message.setVisible(true);
                submitButton.setText("Submit");
            }
            else if(submitButton.getText() == "Submit"){

            }else{
                JOptionPane.showMessageDialog(
                        null,
                        "please check all details"
                );
            }


        }
    }

    // check if all the fields are filled or not
    public boolean validateUserDetails(){
        boolean isFilled =
                !nameInput.getText().isBlank() &&
                !passwordInput.getText().isBlank() &&
                !addressInput.getText().isBlank() &&
                !mobileNumberInput.getText().isBlank() &&
                !initialAmountInput.getText().isBlank() &&
                (savingsAccount.isSelected() || currentAccount.isSelected());
        return isFilled;
    }

    // checking if user already exist in the database or not
    public boolean isUserExists(String searchParameter){
        String query = "SELECT * FROM user_details WHERE mobile_number = '"+searchParameter+"'";
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

    //generating a randomized user id
    public String generateUserId(){
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        String nums = "1234567890";
        int n1 = (int)(Math.random()*27);
        int n2 = (int)(Math.random()*27);
        int n3 = (int)(Math.random()*11);
        int n4 = (int)(Math.random()*11);
        return ""+alphabets.charAt(n1)+alphabets.charAt(n2)+nums.charAt(n3)+nums.charAt(n4);
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
