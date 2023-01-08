import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalendarBottomUI extends JPanel {
    private Settings settings;
    private Button logoutButton;
    private ImageIcon logoutIcon;

    public CalendarBottomUI(Settings settings,Frame window,Frame loginWindow) {
        this.settings = settings;
        this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        logoutIcon = new ImageIcon("logout.png");
        logoutButton = new Button(settings,logoutIcon,"Logout");
        this.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==logoutButton){
                    loginWindow.setVisible(true);
                    loginWindow.revalidate();
                    loginWindow.repaint();
                    window.dispose();
                }
            }
        });
    }

    public Button getLogoutButton() {
        return logoutButton;
    }
}
