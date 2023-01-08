import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

public class CalendarPanel extends JPanel {
    private final Settings settings; // using composition to inherit attributes of the Settings class
    private Settings secondaryCellsSettings;
    private CalendarTopUI calendarTopUI;
    private Calendar originalCal;
    private int currentMonth;
    private int currentYear;
    private int currentDate;
    private int nextMonth;
    private int nextyear;

    public CalendarPanel(Settings settings, CalendarTopUI calendarTopUI,Settings secondaryCellsSettings) {
        // Get the current month and year
        this.settings = settings;
        this.calendarTopUI = calendarTopUI;
        this.secondaryCellsSettings = secondaryCellsSettings;
        this.originalCal=calendarTopUI.getCal();
        currentMonth = originalCal.get(originalCal.MONTH);
        currentYear = originalCal.get(originalCal.YEAR);
        currentDate = originalCal.get(originalCal.DATE);

        updateCalendarPanel();
    }

    public void updateCalendarPanel(){
        this.removeAll();
        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        //this.setBackground(this.settings.getBackgroundColor());
        this.setLayout(new GridLayout(0,7));

        for (String day : days) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(settings.getFont());
            this.add(label);
        }

        calendarTopUI.getCal().set(calendarTopUI.getYear(), calendarTopUI.getMonth(), 1);

        //padding infront of the calendar
        int dayOfWeek = calendarTopUI.getCal().get(Calendar.DAY_OF_WEEK);
        int firstExtraDays = 0;
        int previousMonth = 0;
        int actPreviousMonth = 0;
        int previousYear = 0;
        if (calendarTopUI.getMonth() > 0 ) {
            previousMonth = calendarTopUI.getMonth() - 1;
            actPreviousMonth = calendarTopUI.getMonth();
            previousYear = calendarTopUI.getYear();
        } else {
            previousMonth = 11;
            actPreviousMonth = 12;
            previousYear = calendarTopUI.getYear()-1;
        }
        calendarTopUI.getCal().set(previousYear, previousMonth, 1);
        int previousMonthMaxDay = calendarTopUI.getCal().getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i < dayOfWeek; i++) {
            firstExtraDays ++;
        }
        // Display the relevant event from txt file onto the calendar
        for (int i = firstExtraDays; i >0; i--){

            JPanel test = new JPanel();
            test.setLayout(new FlowLayout(FlowLayout.LEFT));
            CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(previousMonthMaxDay-i+1),actPreviousMonth,previousYear,secondaryCellsSettings,calendarTopUI,this);
            String calendarEvent = "";
            JPanel eventHolder = new JPanel();
            eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
            try {
                BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
                String line;
                while((line = reader.readLine()) != null) {
                    String[] fileData = line.split(" ; ");
                    if(Integer.parseInt(fileData[4])==(previousMonthMaxDay-i+1) &&
                            Integer.parseInt(fileData[3])-1==previousMonth &&
                            Integer.parseInt(fileData[2])==calendarTopUI.getYear() ){
                        calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
                        eventHolder.add(new JLabel(calendarEvent));
                        calendarEvent = "";
                    }
                }
                reader.close();
            } catch (FileNotFoundException te) {
                te.printStackTrace();
            } catch (IOException te) {
                te.printStackTrace();
            }
            placeholderCalendarCell.add(eventHolder);
            this.add(placeholderCalendarCell);
        }


        // Fill the calendar with the current dates
        calendarTopUI.getCal().set(calendarTopUI.getYear(), calendarTopUI.getMonth(), 1);

        int numDays = calendarTopUI.getCal().getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 1; i <= numDays; i++) {
            // if today's date matches, highlight the cell
            if(currentDate==i && currentYear==calendarTopUI.getYear() && currentMonth==calendarTopUI.getMonth()){
                JPanel test = new JPanel();
                test.setLayout(new FlowLayout(FlowLayout.LEFT));
                CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(i),currentMonth+1,currentYear,settings,Color.RED,calendarTopUI,this);
                String calendarEvent = "";
                JPanel eventHolder = new JPanel();
                eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
                // if textfile contains an event of today , then add the event into the calendarcell and display it
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
                    String line;
                    while((line = reader.readLine()) != null) {
                        String[] fileData = line.split(" ; ");
                        if(Integer.parseInt(fileData[4])==currentDate &&
                                Integer.parseInt(fileData[3])-1==currentMonth &&
                                Integer.parseInt(fileData[2])==currentYear ){
                            calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
                            eventHolder.add(new JLabel(calendarEvent));
                            calendarEvent = "";
                        }
                    }
                    reader.close();
                } catch (FileNotFoundException te) {
                    te.printStackTrace();
                } catch (IOException te) {
                    te.printStackTrace();
                }
                placeholderCalendarCell.add(eventHolder);
                this.add(placeholderCalendarCell);
            }else{
                JPanel test = new JPanel();
                test.setLayout(new FlowLayout(FlowLayout.LEFT));
                CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(i),(calendarTopUI.getMonth()+1),calendarTopUI.getYear(),settings,calendarTopUI,this);
                String calendarEvent = "";
                JPanel eventHolder = new JPanel();
                eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
                // if textfile contains an event of today , then add the event into the calendarcell and display it
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
                    String line;
                    while((line = reader.readLine()) != null) {
                        String[] fileData = line.split(" ; ");
                        if(Integer.parseInt(fileData[4])==i &&
                                Integer.parseInt(fileData[3])-1==calendarTopUI.getMonth() &&
                                Integer.parseInt(fileData[2])==calendarTopUI.getYear() ){
                            calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
                            eventHolder.add(new JLabel(calendarEvent));
                            calendarEvent = "";
                        }
                    }
                    reader.close();
                } catch (FileNotFoundException te) {
                    te.printStackTrace();
                } catch (IOException te) {
                    te.printStackTrace();
                }
                placeholderCalendarCell.add(eventHolder);
                this.add(placeholderCalendarCell); // using composition again to access a diff class
                //this.add(new CalendarCell(String.valueOf(i),settings)); // using composition again to access a diff class
            }
        }


        //padding at the back of the calendar
        int totalCalDays = 42;
        this.nextMonth = 0;
        this.nextyear = 0;
        if(calendarTopUI.getMonth()+1 <12){
            this.nextMonth = calendarTopUI.getMonth()+2;
            this.nextyear = currentYear;
        }else{
            this.nextyear = calendarTopUI.getYear()+1;
            this.nextMonth = 1;
        }
        int extraDays = totalCalDays-(firstExtraDays + numDays);
        for (int i = 1; i <= extraDays; i++) {
            JPanel test = new JPanel();
            test.setLayout(new FlowLayout(FlowLayout.LEFT));
            CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(i),nextMonth,nextyear,secondaryCellsSettings,calendarTopUI,this);
            String calendarEvent = "";
            JPanel eventHolder = new JPanel();
            eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
            try {
                BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
                String line;
                while((line = reader.readLine()) != null) {
                    String[] fileData = line.split(" ; ");
                    if(Integer.parseInt(fileData[4])==i &&
                            Integer.parseInt(fileData[3]) == nextMonth &&
                            Integer.parseInt(fileData[2]) == nextyear){
                        calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
                        eventHolder.add(new JLabel(calendarEvent));
                        calendarEvent = "";
                    }
                }
                reader.close();
            } catch (FileNotFoundException te) {
                te.printStackTrace();
            } catch (IOException te) {
                te.printStackTrace();
            }
            placeholderCalendarCell.add(eventHolder);
            this.add(placeholderCalendarCell); // using composition again to access diff class
        }

        this.revalidate();
        this.repaint();
    }

    public Settings getSettings() {
        return settings;
    }

    public int getNextMonth() {
        return nextMonth;
    }

    public int getNextyear() {
        return nextyear;
    }
}


//old code

//    //padding infront of the calendar
//    int dayOfWeek = calendarTopUI.getCal().get(Calendar.DAY_OF_WEEK);
//    int firstExtraDays = 0;
//    int previousMonth = 0;
//        if (calendarTopUI.getMonth() > 0 ) {
//                previousMonth = calendarTopUI.getMonth() - 1;
//                } else {
//                previousMonth = 11;
//                }
//                calendarTopUI.getCal().set(calendarTopUI.getYear(), previousMonth, 1);
//                int previousMonthMaxDay = calendarTopUI.getCal().getActualMaximum(Calendar.DAY_OF_MONTH);
//                for (int i = 1; i < dayOfWeek; i++) {
//        firstExtraDays ++;
//        }
//        for (int i = firstExtraDays; i >0; i--){
//        this.add(new CalendarCell(String.valueOf(previousMonthMaxDay-i+1),secondaryCellsSettings));
//        }
//
//
//        // Fill the calendar with the appropriate dates
//        calendarTopUI.getCal().set(calendarTopUI.getYear(), calendarTopUI.getMonth(), 1);
//        int numDays = calendarTopUI.getCal().getActualMaximum(Calendar.DAY_OF_MONTH);
//        for (int i = 1; i <= numDays; i++) {
//        if(currentDate==i && currentYear==calendarTopUI.getYear() && currentMonth==calendarTopUI.getMonth()){
//        this.add(new CalendarCell(String.valueOf(i),settings,Color.RED));
//        }else{
//        this.add(new CalendarCell(String.valueOf(i),settings)); // using composition again to access a diff class
//        }
//        }
//
//        //padding at the back of the calendar
//        int totalCalDays = 42;
//        int extraDays = totalCalDays-(firstExtraDays + numDays);
//        for (int i = 1; i <= extraDays; i++) {
//        this.add(new CalendarCell(String.valueOf(i),secondaryCellsSettings)); // using composition again to access diff class
//        }

//        String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
//        //this.setBackground(this.settings.getBackgroundColor());
//        this.setLayout(new GridLayout(0,7));
//
//        for (String day : days) {
//            JLabel label = new JLabel(day, SwingConstants.CENTER);
//            label.setFont(settings.getFont());
//            this.add(label);
//        }
//
//        calendarTopUI.getCal().set(calendarTopUI.getYear(), calendarTopUI.getMonth(), 1);
//
//        //padding infront of the calendar
//        int dayOfWeek = calendarTopUI.getCal().get(Calendar.DAY_OF_WEEK);
//        int firstExtraDays = 0;
//        int previousMonth = 0;
//        if (calendarTopUI.getMonth() > 0 ) {
//            previousMonth = calendarTopUI.getMonth() - 1;
//        } else {
//            previousMonth = 11;
//        }
//        calendarTopUI.getCal().set(calendarTopUI.getYear(), previousMonth, 1);
//        int previousMonthMaxDay = calendarTopUI.getCal().getActualMaximum(Calendar.DAY_OF_MONTH);
//        for (int i = 1; i < dayOfWeek; i++) {
//            firstExtraDays ++;
//        }
//        // Display the relevant event from txt file onto the calendar
//        for (int i = firstExtraDays; i >0; i--){
//
//            JPanel test = new JPanel();
//            test.setLayout(new FlowLayout(FlowLayout.LEFT));
//            CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(previousMonthMaxDay-i+1),secondaryCellsSettings);
//            String calendarEvent = "";
//            JPanel eventHolder = new JPanel();
//            eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
//            try {
//                BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
//                String line;
//                while((line = reader.readLine()) != null) {
//                    String[] fileData = line.split(" ; ");
//                    if(Integer.parseInt(fileData[4])==(previousMonthMaxDay-i+1) &&
//                            Integer.parseInt(fileData[3])-1==previousMonth &&
//                            Integer.parseInt(fileData[2])==calendarTopUI.getYear() ){
//                        calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
//                        eventHolder.add(new JLabel(calendarEvent));
//                        calendarEvent = "";
//                    }
//                }
//                reader.close();
//            } catch (FileNotFoundException te) {
//                te.printStackTrace();
//            } catch (IOException te) {
//                te.printStackTrace();
//            }
//            placeholderCalendarCell.add(eventHolder);
//            this.add(placeholderCalendarCell);
//        }
//
//
//        // Fill the calendar with the appropriate dates
//        calendarTopUI.getCal().set(calendarTopUI.getYear(), calendarTopUI.getMonth(), 1);
//
//        int numDays = calendarTopUI.getCal().getActualMaximum(Calendar.DAY_OF_MONTH);
//        for (int i = 1; i <= numDays; i++) {
//            // if today's date matches, highlight the cell
//            if(currentDate==i && currentYear==calendarTopUI.getYear() && currentMonth==calendarTopUI.getMonth()){
//                JPanel test = new JPanel();
//                test.setLayout(new FlowLayout(FlowLayout.LEFT));
//                CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(i),settings,Color.RED);
//                String calendarEvent = "";
//                JPanel eventHolder = new JPanel();
//                eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
//                // if textfile contains an event of today , then add the event into the calendarcell and display it
//                try {
//                    BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
//                    String line;
//                    while((line = reader.readLine()) != null) {
//                        String[] fileData = line.split(" ; ");
//                        if(Integer.parseInt(fileData[4])==currentDate &&
//                                Integer.parseInt(fileData[3])-1==currentMonth &&
//                                Integer.parseInt(fileData[2])==currentYear ){
//                            calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
//                            eventHolder.add(new JLabel(calendarEvent));
//                            calendarEvent = "";
//                        }
//                    }
//                    reader.close();
//                } catch (FileNotFoundException te) {
//                    te.printStackTrace();
//                } catch (IOException te) {
//                    te.printStackTrace();
//                }
//                placeholderCalendarCell.add(eventHolder);
//                this.add(placeholderCalendarCell);
//            }else{
//                JPanel test = new JPanel();
//                test.setLayout(new FlowLayout(FlowLayout.LEFT));
//                CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(i),settings);
//                String calendarEvent = "";
//                JPanel eventHolder = new JPanel();
//                eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
//                // if textfile contains an event of today , then add the event into the calendarcell and display it
//                try {
//                    BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
//                    String line;
//                    while((line = reader.readLine()) != null) {
//                        String[] fileData = line.split(" ; ");
//                        if(Integer.parseInt(fileData[4])==i &&
//                                Integer.parseInt(fileData[3])-1==calendarTopUI.getMonth() &&
//                                Integer.parseInt(fileData[2])==calendarTopUI.getYear() ){
//                            calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
//                            eventHolder.add(new JLabel(calendarEvent));
//                            calendarEvent = "";
//                        }
//                    }
//                    reader.close();
//                } catch (FileNotFoundException te) {
//                    te.printStackTrace();
//                } catch (IOException te) {
//                    te.printStackTrace();
//                }
//                placeholderCalendarCell.add(eventHolder);
//                this.add(placeholderCalendarCell); // using composition again to access a diff class
//            }
//        }
//
//
//        //padding at the back of the calendar
//        int totalCalDays = 42;
//        int nextMonth = 0;
//        int nextyear = 0;
//        if(calendarTopUI.getMonth()+1 <12){
//            nextMonth = calendarTopUI.getMonth()+1;
//            nextyear = currentYear;
//        }else{
//            nextyear = calendarTopUI.getYear()+1;
//            nextMonth = 1;
//        }
//        int extraDays = totalCalDays-(firstExtraDays + numDays);
//        for (int i = 1; i <= extraDays; i++) {
//            JPanel test = new JPanel();
//            test.setLayout(new FlowLayout(FlowLayout.LEFT));
//            CalendarCell placeholderCalendarCell = new CalendarCell(String.valueOf(i),secondaryCellsSettings);
//            String calendarEvent = "";
//            JPanel eventHolder = new JPanel();
//            eventHolder.setLayout(new BoxLayout(eventHolder,BoxLayout.PAGE_AXIS));
//            try {
//                BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
//                String line;
//                while((line = reader.readLine()) != null) {
//                    String[] fileData = line.split(" ; ");
//                    if(Integer.parseInt(fileData[4])==i &&
//                            Integer.parseInt(fileData[3]) == nextMonth &&
//                            Integer.parseInt(fileData[2]) == nextyear){
//                        calendarEvent = calendarEvent + "\n" + fileData[5] + "  " + fileData[1];
//                        eventHolder.add(new JLabel(calendarEvent));
//                        calendarEvent = "";
//                    }
//                }
//                reader.close();
//            } catch (FileNotFoundException te) {
//                te.printStackTrace();
//            } catch (IOException te) {
//                te.printStackTrace();
//            }
//            placeholderCalendarCell.add(eventHolder);
//            this.add(placeholderCalendarCell); // using composition again to access diff class
//        }