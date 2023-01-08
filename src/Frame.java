import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(int length, int height,String title) {
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.setSize(length,height);
        this.setLocationRelativeTo(null);
    }

    public Frame(String title) {
        this.setTitle(title);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,800);
        this.setLocationRelativeTo(null);
    }
}
