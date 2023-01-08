import javax.swing.*;

public class DateOfCalendar extends JTextArea {
    private String textToDisplay;
    // events

    public void setTextToDisplay(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }

    public String getTextToDisplay() {
        return textToDisplay;
    }

    public DateOfCalendar(String textToDisplay) {
        this.textToDisplay = textToDisplay;
    }
}

