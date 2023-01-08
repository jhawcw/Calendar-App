import javax.swing.*;

public class Button extends JButton {
    private Settings settings;
    private ImageIcon imageIcon;

    public Button(Settings settings, ImageIcon imageIcon, String displayText) {
        this.settings = settings;
        this.imageIcon = imageIcon;
        this.setIcon(this.imageIcon);
        this.setText(displayText);
    }
    //Method overloading
    public Button(Settings settings, ImageIcon imageIcon) {
        this.settings = settings;
        this.imageIcon = imageIcon;
        this.setIcon(this.imageIcon);
    }
}
