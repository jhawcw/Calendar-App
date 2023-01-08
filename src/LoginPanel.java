import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    JButton loginButton;
    JTextField loginField;
    JPasswordField passwordField;
    public LoginPanel(User user,Settings settings) {
        JLabel loginID = new JLabel("LoginID:",SwingConstants.RIGHT);
        JLabel password = new JLabel("Password:",SwingConstants.RIGHT);
        loginButton = new JButton("Login");
        loginField = new JTextField(user.getLoginID());
        passwordField = new JPasswordField(user.getPassword());
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());

        gbc.gridx =0;
        gbc.gridy =0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(loginID,gbc);
        gbc.gridx =1;
        gbc.gridy =0;
        this.add(loginField,gbc);
        gbc.gridx =0;
        gbc.gridy =1;
        this.add(password,gbc);
        gbc.gridx =1;
        gbc.gridy =1;
        this.add(passwordField,gbc);
        gbc.gridx =0;
        gbc.gridy =2;
        gbc.gridwidth = 2;
        this.add(loginButton,gbc);

    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JTextField getLoginField() {
        return loginField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }
}
