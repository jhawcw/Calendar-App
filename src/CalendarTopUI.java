import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Objects;

public class CalendarTopUI extends JPanel implements ActionListener {
    private Calendar cal = Calendar.getInstance();
    private int month ;
    private int year ;
    private int minYear = cal.get(Calendar.YEAR)-200;
    private Settings settings;
    private DropdownList monthDropdownList;
    private DropdownList yearDropdownList;
    private Button leftCalendarButton;
    private ImageIcon leftCalendarIcon;
    private Button rightCalendarButton;
    private ImageIcon rightCalendarIcon;
    private ImageIcon addEventIcon;
    private Button addEventButton;
    private String[] monthString;
    private String[] yearString;



    public CalendarTopUI(Settings settings) {
        leftCalendarIcon = new ImageIcon("Left Arrow.png");
        leftCalendarButton = new Button(settings,leftCalendarIcon);
        rightCalendarIcon = new ImageIcon("Right Arrow.png");
        rightCalendarButton = new Button(settings,rightCalendarIcon);
        addEventIcon = new ImageIcon("add-icon.png");
        addEventButton = new Button(settings,addEventIcon,"Add Event");


        //this.setLayout(new FlowLayout(FlowLayout.LEADING));

        // updates-----
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        // updates-----

        String[] monthString = {"January", "February", "March",
                                "April", "May", "June", "July",
                                "August", "September", "October",
                                "November", "December"};
        monthDropdownList = new DropdownList(settings,monthString);
        monthDropdownList.addActionListener(this);
        monthDropdownList.setSelectedIndex(cal.get(Calendar.MONTH));

        this.month = monthNumber(monthDropdownList);
        String[] yearString =new String[400];
        for(int i = 0; i<400;i++){
            int runningYear = i+minYear;
            yearString[i]= String.valueOf(runningYear);
        }
        yearDropdownList = new DropdownList(settings,yearString);
        yearDropdownList.setSelectedIndex(200);
        this.year =  Integer.parseInt((String) Objects.requireNonNull(yearDropdownList.getSelectedItem()));

        gbc.gridx=0;
        gbc.gridy=0;
        this.add(leftCalendarButton,gbc);
        gbc.gridx=1;
        gbc.gridy=0;
        this.add(monthDropdownList,gbc);
        gbc.gridx=2;
        gbc.gridy=0;
        this.add(yearDropdownList,gbc);
        gbc.gridx=3;
        gbc.gridy=0;
        this.add(rightCalendarButton,gbc);
        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth = 2;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        this.add(addEventButton,gbc);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getMinYear() {
        return minYear;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Calendar getCal() {
        return cal;
    }

    public DropdownList getMonthDropdownList() {
        return monthDropdownList;
    }

    public DropdownList getYearDropdownList() {
        return yearDropdownList;
    }

    public Button getLeftCalendarButton() {
        return leftCalendarButton;
    }

    public Button getRightCalendarButton() {
        return rightCalendarButton;
    }

    public Button getAddEventButton() {
        return addEventButton;
    }

    public String[] getMonthString() {
        return monthString;
    }

    public String[] getYearString() {
        return yearString;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == monthDropdownList){
            this.month=monthNumber(monthDropdownList);
        }
    }

    public int monthNumber(DropdownList monthDropdownList){
        switch(monthDropdownList.getSelectedIndex()){
            case 0:
                return 0;
            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            case 4:
                return 4;

            case 5:
                return 5;

            case 6:
                return 6;

            case 7:
                return 7;

            case 8:
                return 8;

            case 9:
                return 9;

            case 10:
                return 10;

            default:
                return 11;
        }
    }
}
