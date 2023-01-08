import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

public class EventEntryPanel extends JPanel {
    private Settings settings;
    private JLabel eventType;
    private JLabel eventTitle;
    private JLabel eventStartDate;
    private JLabel eventEndDate;
    private JLabel allDay;
    private JLabel eventID; // to implement
    private JLabel eventDuration;
    private JLabel eventStatus;
    private JLabel eventColor;
    private JLabel startEvent;
    private JLabel endEvent;
    private JLabel eventDescription;
    private JComboBox eventTypeField;
    private JTextField eventTitleField;
    private Box eventDateField;
    private JTextField eventIDField; // to implement
    private JTextArea eventDescriptionField;
    private Box eventEndDateField;
    private CalendarTopUI calendarTopUI;
    private DropdownList monthList;
    private DropdownList yearList;
    private DropdownList dayList;
    private DropdownList startHourList;
    private DropdownList startMinList;
    private DropdownList endHourList;
    private DropdownList endMinList;
    private DropdownList endYearList;
    private DropdownList endMonthList;
    private DropdownList enddayList;
    private JCheckBox allDayCheckBox;
    private DropdownList statusList;
    private DropdownList colorList;
    private JScrollPane eventDescriptionFieldScrollPane;
    private Button saveToFileButton;
    private Button editEventButton;
    private Button removeEventButton;
    private ImageIcon addEventIcon;
    private ImageIcon editEventIcon;
    private ImageIcon removeEventIcon;
    private JTable pseudoTable;
    private JTable pseudo1Table;
    private DefaultTableModel pseudoModel;
    private DefaultTableModel pseudo1Model;
    private CalendarPanel calendarPanel;
    private String editRemoveDate;
    private int editedRow;


    public EventEntryPanel(Settings settings,CalendarTopUI calendarTopUI,Frame secondaryFrame,CalendarPanel calendarPanel) {
        this.settings = settings;
        this.calendarTopUI = calendarTopUI;
        this.calendarPanel = calendarPanel;
        this.setLayout(new GridLayout(8,2));
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        eventType = new JLabel("Event Type   ",SwingConstants. RIGHT);
        eventTitle = new JLabel("Event Title   ",SwingConstants. RIGHT);
        eventStartDate = new JLabel("Event Start Date   ",SwingConstants. RIGHT);
        eventEndDate = new JLabel("Event End Date   ",SwingConstants. RIGHT);
        allDay = new JLabel("All Day?   ",SwingConstants. RIGHT);
        eventID = new JLabel("Event ID   ",SwingConstants. RIGHT);
        eventDuration = new JLabel("Event Duration   ",SwingConstants. RIGHT);
        eventStatus = new JLabel("Completion Status   ",SwingConstants. RIGHT);
        eventColor = new JLabel("Color   ",SwingConstants. RIGHT);
        eventDescription = new JLabel("Event Description   ",SwingConstants. RIGHT);
        String[] eventListType = {"Task","Event"};
        eventTypeField = new JComboBox(eventListType);
        eventTitleField = new JTextField();
        String[] monthString = {"Month","January", "February", "March",
                "April", "May", "June", "July",
                "August", "September", "October",
                "November", "December"};
        monthList = new DropdownList(settings,monthString);
        endMonthList = new DropdownList(settings,monthString);
        String[] dayString = {  "1","2","3","4","5",
                                "6","7","8","9","10",
                                "11","12","13","14","15",
                                "16","17","18","19","20",
                                "21","22","23","24","25",
                                "26","27","28","29","30","31"};
        dayList = new DropdownList(settings,dayString);
        enddayList = new DropdownList(settings,dayString);
        String[] yearString =new String[401];
        for(int i = 0; i<401;i++){
            if(i!=0){
                int runningYear = i+calendarTopUI.getMinYear();
                yearString[i]= String.valueOf(runningYear);

            }else {
                yearString[i] = "Year";
            }
        }
        yearList = new DropdownList(settings,yearString);
        endYearList = new DropdownList(settings,yearString);
        eventEndDateField = Box.createHorizontalBox();
        allDayCheckBox = new JCheckBox();
        eventIDField = new JTextField();
        String[] hourString = new String[24];
        for(int i = 0; i<24;i++){
            if(i<10){
                hourString[i]= "0" + String.valueOf(i);
            }else {
                hourString[i] = String.valueOf(i);
            }
        }
        String[] minuteString = new String[4];
        for(int i = 0; i<4;i++){
            if(i==0){
                minuteString[i]="00";
            }else {
                minuteString[i] = String.valueOf(i * 15);
            }
        }
        startHourList = new DropdownList(hourString);
        startMinList = new DropdownList(minuteString);
        endHourList = new DropdownList(hourString);
        endMinList = new DropdownList(minuteString);
        startEvent = new JLabel("Start");
        endEvent = new JLabel("End");
        eventDateField = Box.createHorizontalBox();
        String[] statusString = {"Pending", "In Progress", "Completed", "KIV"};
        statusList = new DropdownList(statusString);
        String[] colorString = {"Red", "Blue", "Green", "Yellow", "White", "Magenta", "Cyan", "Pink", "Orange", "Gray"};
        colorList = new DropdownList(colorString);
        addEventIcon = new ImageIcon("add-icon.png");
        saveToFileButton = new Button(settings,addEventIcon,"Add Event");
        String[] columnNames = {"Event Type", "Event Title", "Event Start Year","Event Start Month","Event Start Date","Event Start Time", "Event End Year","Event End Month","Event End Date","Event End Time","All Day?","Event Description","Completion Status","Color", "Full start date","Start hour","Start min", "End hour", "End min"};
        pseudoModel = new DefaultTableModel();
        pseudoTable = new JTable();
        JScrollPane tableSP = new JScrollPane(pseudoTable);
        // rows for table
        String[] row = new String[columnNames.length];








        // Removing Month, so user will not be able to input Month as month, needs to be name of a Month in String
        monthList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                if(e.getSource()==monthList){
                    monthList.removeItem("Month");
                    monthList.setSelectedIndex(calendarTopUI.getMonth());
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endMonthList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endMonthList){

                }
            }
        });

        endMonthList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                if(e.getSource()==endMonthList){
                    endMonthList.removeItem("Month");
                    endMonthList.setSelectedIndex(calendarTopUI.getMonth());
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endMonthList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endMonthList){

                }
            }
        });





        //Removing Year option so user will not be able to select Year as an option as Integer is required.
        yearList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                yearList.removeItem("Year");
                yearList.setSelectedIndex(calendarTopUI.getYear()-calendarTopUI.getMinYear()-1);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endYearList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endYearList){
                }
            }
        });

        endYearList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                endYearList.removeItem("Year");
                endYearList.setSelectedIndex(calendarTopUI.getYear()-calendarTopUI.getMinYear()-1);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endYearList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endYearList){
                }
            }
        });
        monthList.setSelectedItem("Month");
        yearList.setSelectedItem("Year");
        endMonthList.setSelectedItem("Month");
        endYearList.setSelectedItem("Year");

        //adding actionlistener to all day checkbox
        allDayCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == allDayCheckBox){
                    if(allDayCheckBox.isSelected()){
                        startHourList.setEnabled(false);
                        startMinList.setEnabled(false);
                        endHourList.setEnabled(false);
                        endMinList.setEnabled(false);
                    }else{
                        startHourList.setEnabled(true);
                        startMinList.setEnabled(true);
                        endHourList.setEnabled(true);
                        endMinList.setEnabled(true);
                    }
                }
            }
        });

        //restrict 30 characters for Event title , assumption is that " " is not considered a special Char
        //with reference to the example
        eventTitleField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int allowedLength = eventTitleField.getText().length();
                char ch = e.getKeyChar();
                if (Character.isLetterOrDigit(ch) || e.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE
                        || e.getExtendedKeyCode()== KeyEvent.VK_DELETE
                        || e.getExtendedKeyCode() == KeyEvent.VK_SHIFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT
                        || e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
                    if (allowedLength < 30) {
                        eventTitleField.setEditable(true);
                        eventTitleField.getCaret().setVisible(true);
                    } else if (allowedLength == 30 && (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
                            || e.getExtendedKeyCode() == KeyEvent.VK_DELETE
                            || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                            || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT)) {
                        eventTitleField.setEditable(true);
                        eventTitleField.getCaret().setVisible(true);
                    } else {
                        eventTitleField.setEditable(false);
                        eventTitleField.getCaret().setVisible(true);
                    }
                } else{
                    eventTitleField.setEditable(false);
                    eventTitleField.getCaret().setVisible(true);
                }
            }
        });

        //Configuring event Description Jtextarea
        eventDescriptionField = new JTextArea(5,10);
        eventDescriptionField.setLineWrap(true);

        //Limiting to 200 characters
        eventDescriptionField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int allowedLength = eventDescriptionField.getText().length();
                char ch = e.getKeyChar();
                if (Character.isLetterOrDigit(ch) || e.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE
                        || e.getExtendedKeyCode()== KeyEvent.VK_DELETE
                        || e.getExtendedKeyCode() == KeyEvent.VK_SHIFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT
                        || e.getExtendedKeyCode() == KeyEvent.VK_SPACE
                        || e.getExtendedKeyCode() == KeyEvent.VK_ENTER
                        || e.getExtendedKeyCode() == KeyEvent.VK_COPY
                        || e.getExtendedKeyCode() == KeyEvent.VK_PASTE ) {
                    if (allowedLength < 200) {
                        eventDescriptionField.setEditable(true);
                        eventDescriptionField.getCaret().setVisible(true);
                    } else if (allowedLength == 200 && (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
                            || e.getExtendedKeyCode() == KeyEvent.VK_DELETE
                            || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                            || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT
                            || e.getExtendedKeyCode() == KeyEvent.VK_UP
                            || e.getExtendedKeyCode() == KeyEvent.VK_DOWN)) {
                        eventDescriptionField.setEditable(true);
                        eventDescriptionField.getCaret().setVisible(true);
                    } else {
                        eventDescriptionField.setEditable(false);
                        eventDescriptionField.getCaret().setVisible(true);
                    }
                } else{
                    eventDescriptionField.setEditable(false);
                    eventDescriptionField.getCaret().setVisible(true);
                }
            }
        });
        eventDescriptionFieldScrollPane = new JScrollPane(eventDescriptionField,
                                            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Table configurations
        pseudoTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        pseudoTable.setFillsViewportHeight(true);
        pseudoModel.setColumnIdentifiers(columnNames);
        pseudoTable.setModel(pseudoModel);
        TableRowSorter sorter = new TableRowSorter<>(pseudoModel);
        pseudoTable.setRowSorter(sorter);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
            String line;
            //int data = reader.read();
            while((line = reader.readLine()) != null) {
                pseudoModel.addRow(line.split(" ; "));
                //data = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException te) {
            te.printStackTrace();
        } catch (IOException te) {
            te.printStackTrace();
        }

        //add actionlistener to save to file button, ultimately saving all the details into a txt file
        saveToFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==saveToFileButton){

                    String monthValue = "0";
                    String endMonthValue = "0";

                    if(monthList.getSelectedItem().equals("January")){
                        monthValue = "1";
                    }else if (monthList.getSelectedItem().equals("February")){
                        monthValue = "2";
                    }else if (monthList.getSelectedItem().equals("March")){
                        monthValue = "3";
                    }else if (monthList.getSelectedItem().equals("April")){
                        monthValue = "4";
                    }else if (monthList.getSelectedItem().equals("May")){
                        monthValue = "5";
                    }else if (monthList.getSelectedItem().equals("June")){
                        monthValue = "6";
                    }else if (monthList.getSelectedItem().equals("July")){
                        monthValue = "7";
                    }else if (monthList.getSelectedItem().equals("August")){
                        monthValue = "8";
                    }else if (monthList.getSelectedItem().equals("September")){
                        monthValue = "9";
                    }else if (monthList.getSelectedItem().equals("October")){
                        monthValue = "10";
                    }else if (monthList.getSelectedItem().equals("November")){
                        monthValue = "11";
                    }else if (monthList.getSelectedItem().equals("December")){
                        monthValue = "12";
                    }

                    if(endMonthList.getSelectedItem().equals("January")){
                        endMonthValue = "1";
                    }else if (endMonthList.getSelectedItem().equals("February")){
                        endMonthValue = "2";
                    }else if (endMonthList.getSelectedItem().equals("March")){
                        endMonthValue = "3";
                    }else if (endMonthList.getSelectedItem().equals("April")){
                        endMonthValue = "4";
                    }else if (endMonthList.getSelectedItem().equals("May")){
                        endMonthValue = "5";
                    }else if (endMonthList.getSelectedItem().equals("June")){
                        endMonthValue = "6";
                    }else if (endMonthList.getSelectedItem().equals("July")){
                        endMonthValue = "7";
                    }else if (endMonthList.getSelectedItem().equals("August")){
                        endMonthValue = "8";
                    }else if (endMonthList.getSelectedItem().equals("September")){
                        endMonthValue = "9";
                    }else if (endMonthList.getSelectedItem().equals("October")){
                        endMonthValue = "10";
                    }else if (endMonthList.getSelectedItem().equals("November")){
                        endMonthValue = "11";
                    }else if (endMonthList.getSelectedItem().equals("December")){
                        endMonthValue = "12";
                    }


                    long startValue = Long.parseLong((String)yearList.getSelectedItem()+monthValue +(String)dayList.getSelectedItem() + (String) startHourList.getSelectedItem() + (String) startMinList.getSelectedItem());
                    long endValue =Long.parseLong((String)endYearList.getSelectedItem() + endMonthValue + (String)enddayList.getSelectedItem() + (String) endHourList.getSelectedItem() + (String) endMinList.getSelectedItem());

                    if(eventTitleField.getText().equals("")||eventDescriptionField.getText().equals("")
                            ||(String)yearList.getSelectedItem() == "Year"|| (String)endYearList.getSelectedItem() == "Year"
                            ||(String)monthList.getSelectedItem() == "Month"||(String)endMonthList.getSelectedItem() == "Month"){
                        JOptionPane.showMessageDialog(null,"All fields are compulsory");
                    }else if(startValue >= endValue){
                        JOptionPane.showMessageDialog(null,"You can't have an event that ends before it starts!");
                    } else {
                        row[0] = (String) eventTypeField.getSelectedItem();
                        row[1] = eventTitleField.getText();
                        row[2] = (String) yearList.getSelectedItem();
                        if(monthList.getSelectedItem().equals("January")){
                            row[3] = "1";
                        }else if (monthList.getSelectedItem().equals("February")){
                            row[3] = "2";
                        }else if (monthList.getSelectedItem().equals("March")){
                            row[3] = "3";
                        }else if (monthList.getSelectedItem().equals("April")){
                            row[3] = "4";
                        }else if (monthList.getSelectedItem().equals("May")){
                            row[3] = "5";
                        }else if (monthList.getSelectedItem().equals("June")){
                            row[3] = "6";
                        }else if (monthList.getSelectedItem().equals("July")){
                            row[3] = "7";
                        }else if (monthList.getSelectedItem().equals("August")){
                            row[3] = "8";
                        }else if (monthList.getSelectedItem().equals("September")){
                            row[3] = "9";
                        }else if (monthList.getSelectedItem().equals("October")){
                            row[3] = "10";
                        }else if (monthList.getSelectedItem().equals("November")){
                            row[3] = "11";
                        }else if (monthList.getSelectedItem().equals("December")){
                            row[3] = "12";
                        }
                        row[4] = (String) dayList.getSelectedItem();
                        row[6] = (String) endYearList.getSelectedItem();
                        if(endMonthList.getSelectedItem().equals("January")){
                            row[7] = "1";
                        }else if (endMonthList.getSelectedItem().equals("February")){
                            row[7] = "2";
                        }else if (endMonthList.getSelectedItem().equals("March")){
                            row[7] = "3";
                        }else if (endMonthList.getSelectedItem().equals("April")){
                            row[7] = "4";
                        }else if (endMonthList.getSelectedItem().equals("May")){
                            row[7] = "5";
                        }else if (endMonthList.getSelectedItem().equals("June")){
                            row[7] = "6";
                        }else if (endMonthList.getSelectedItem().equals("July")){
                            row[7] = "7";
                        }else if (endMonthList.getSelectedItem().equals("August")){
                            row[7] = "8";
                        }else if (endMonthList.getSelectedItem().equals("September")){
                            row[7] = "9";
                        }else if (endMonthList.getSelectedItem().equals("October")){
                            row[7] = "10";
                        }else if (endMonthList.getSelectedItem().equals("November")){
                            row[7] = "11";
                        }else if (endMonthList.getSelectedItem().equals("December")){
                            row[7] = "12";
                        }
                        row[8] = (String) enddayList.getSelectedItem();

                        if (allDayCheckBox.isSelected()) {
                            row[5] = "0000";
                            row[9] = "2359";
                            row[10] = "True";
                            row[14] = (String) yearList.getSelectedItem() + "/" +
                                    String.valueOf(1+monthList.getSelectedIndex()) + "/" +
                                    (String) dayList.getSelectedItem() + " 0000";
                            row[15] = "00";
                            row[16] = "00";
                            row[17] = "23";
                            row[18] = "59";
                        } else {
                            row[5] = (String) startHourList.getSelectedItem() +
                                    (String) startMinList.getSelectedItem();
                            row[9] = (String) endHourList.getSelectedItem() +
                                    (String) endMinList.getSelectedItem();
                            row[10] = "False";
                            row[14] = (String) yearList.getSelectedItem() + "/" +
                                    String.valueOf( 1+monthList.getSelectedIndex()) + "/" +
                                    (String) dayList.getSelectedItem() + " " + (String) startHourList.getSelectedItem() +
                                    (String) startMinList.getSelectedItem();
                            row[15] = (String) startHourList.getSelectedItem();
                            row[16] = (String) startMinList.getSelectedItem();
                            row[17] = (String) endHourList.getSelectedItem();
                            row[18] = (String) endMinList.getSelectedItem();
                        }
                        row[11] = eventDescriptionField.getText();
                        row[12] = (String) statusList.getSelectedItem();
                        row[13] = (String) colorList.getSelectedItem();


                        pseudoModel.addRow(row);
                        pseudoTable.getRowSorter().toggleSortOrder(14);
                        try {
                            FileWriter writer = new FileWriter("test_info.txt");
                            for (int trow = 0; trow < pseudoTable.getRowCount(); trow++) {
                                for (int tcol = 0; tcol < pseudoTable.getColumnCount(); tcol++) {
                                    if (tcol <= 17) {
                                        writer.append(pseudoTable.getValueAt(trow, tcol).toString() + " ; ");
                                    } else if (tcol == 18) {
                                        writer.append(pseudoTable.getValueAt(trow, tcol).toString());
                                        writer.append(System.lineSeparator());
                                    }
                                }
                            }
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        calendarPanel.updateCalendarPanel();
                        secondaryFrame.dispose();
                    }
                }
            }
        });

        eventEndDateField.add(endYearList);
        eventEndDateField.add(endMonthList);
        eventEndDateField.add(enddayList);
        eventEndDateField.add(Box.createRigidArea(new Dimension(9,10)));
        eventEndDateField.add(endEvent);
        eventEndDateField.add(Box.createRigidArea(new Dimension(9,10)));
        eventEndDateField.add(endHourList);
        eventEndDateField.add(endMinList);
        eventDateField.add(yearList);
        eventDateField.add(monthList);
        eventDateField.add(dayList);
        eventDateField.add(Box.createRigidArea(new Dimension(6,10)));
        eventDateField.add(startEvent);
        eventDateField.add(Box.createRigidArea(new Dimension(5,10)));
        eventDateField.add(startHourList);
        eventDateField.add(startMinList);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        this.add(eventType,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(eventTypeField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(eventTitle,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(eventTitleField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(eventStartDate,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(eventDateField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(eventEndDate,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(eventEndDateField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(allDay,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(allDayCheckBox,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(eventDescription,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(eventDescriptionFieldScrollPane,gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(eventStatus,gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        this.add(statusList,gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(eventColor,gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        this.add(colorList,gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        this.add(saveToFileButton,gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        gbc.gridheight = 9;
        gbc.fill=GridBagConstraints.VERTICAL;
        //this.add(tableSP,gbc);


    }


    //edit/remove panel
    public EventEntryPanel(Settings settings,CalendarTopUI calendarTopUI,CalendarPanel calendarPanel,Frame secondaryFrame,String editRemoveDate,CalendarCell calendarCell) {
        this.settings = settings;
        this.calendarTopUI = calendarTopUI;
        this.calendarPanel = calendarPanel;
        this.editRemoveDate = editRemoveDate;
        this.setLayout(new GridLayout(8,2));
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(new GridBagLayout());
        eventType = new JLabel("Event Type   ",SwingConstants. RIGHT);
        eventTitle = new JLabel("Event Title   ",SwingConstants. RIGHT);
        eventStartDate = new JLabel("Event Start Date   ",SwingConstants. RIGHT);
        eventEndDate = new JLabel("Event End Date   ",SwingConstants. RIGHT);
        allDay = new JLabel("All Day?   ",SwingConstants. RIGHT);
        eventID = new JLabel("Event ID   ",SwingConstants. RIGHT);
        eventDuration = new JLabel("Event Duration   ",SwingConstants. RIGHT);
        eventStatus = new JLabel("Completion Status   ",SwingConstants. RIGHT);
        eventColor = new JLabel("Color   ",SwingConstants. RIGHT);
        eventDescription = new JLabel("Event Description   ",SwingConstants. RIGHT);
        String[] eventListType = {"Task","Event"};
        eventTypeField = new JComboBox(eventListType);
        eventTitleField = new JTextField();
        String[] monthString = {"Month","January", "February", "March",
                "April", "May", "June", "July",
                "August", "September", "October",
                "November", "December"};
        monthList = new DropdownList(settings,monthString);
        endMonthList = new DropdownList(settings,monthString);
        String[] dayString = {  "1","2","3","4","5",
                "6","7","8","9","10",
                "11","12","13","14","15",
                "16","17","18","19","20",
                "21","22","23","24","25",
                "26","27","28","29","30","31"};
        dayList = new DropdownList(settings,dayString);
        enddayList = new DropdownList(settings,dayString);
        String[] yearString =new String[401];
        for(int i = 0; i<401;i++){
            if(i!=0){
                int runningYear = i+calendarTopUI.getMinYear();
                yearString[i]= String.valueOf(runningYear);

            }else {
                yearString[i] = "Year";
            }
        }
        yearList = new DropdownList(settings,yearString);
        endYearList = new DropdownList(settings,yearString);
        eventEndDateField = Box.createHorizontalBox();
        allDayCheckBox = new JCheckBox();
        eventIDField = new JTextField();
        String[] hourString = new String[24];
        for(int i = 0; i<24;i++){
            if(i<10){
                hourString[i]= "0" + String.valueOf(i);
            }else {
                hourString[i] = String.valueOf(i);
            }
        }
        String[] minuteString = new String[4];
        for(int i = 0; i<4;i++){
            if(i==0){
                minuteString[i]="00";
            }else {
                minuteString[i] = String.valueOf(i * 15);
            }
        }
        startHourList = new DropdownList(hourString);
        startMinList = new DropdownList(minuteString);
        endHourList = new DropdownList(hourString);
        endMinList = new DropdownList(minuteString);
        startEvent = new JLabel("Start");
        endEvent = new JLabel("End");
        eventDateField = Box.createHorizontalBox();
        String[] statusString = {"Pending", "In Progress", "Completed", "KIV"};
        statusList = new DropdownList(statusString);
        String[] colorString = {"Red", "Blue", "Green", "Yellow", "White", "Magenta", "Cyan", "Pink", "Orange", "Gray"};
        colorList = new DropdownList(colorString);
        editEventIcon = new ImageIcon("Trash-icon.png");
        editEventButton = new Button(settings,editEventIcon,"Edit Event");
        removeEventIcon = new ImageIcon("Close.png");
        removeEventButton = new Button(settings,removeEventIcon,"Remove Event");
        String[] columnNames = {"Event Type", "Event Title", "Event Start Year","Event Start Month","Event Start Date","Event Start Time", "Event End Year","Event End Month","Event End Date","Event End Time","All Day?","Event Description","Completion Status","Color", "Full start date","Start hour","Start min", "End hour", "End min"};
        pseudoModel = new DefaultTableModel();
        pseudoTable = new JTable();
        pseudo1Model = new DefaultTableModel();
        pseudo1Table = new JTable();
        JScrollPane tableSP = new JScrollPane(pseudoTable);
        JScrollPane tableSP1 = new JScrollPane(pseudo1Table);
        // rows for table
        String[] row = new String[columnNames.length];








        // Removing Month, so user will not be able to input Month as month, needs to be name of a Month in String
        monthList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                if(e.getSource()==monthList){
                    monthList.removeItem("Month");
                    monthList.setSelectedIndex(calendarTopUI.getMonth());
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endMonthList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endMonthList){

                }
            }
        });

        endMonthList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                if(e.getSource()==endMonthList){
                    endMonthList.removeItem("Month");
                    endMonthList.setSelectedIndex(calendarTopUI.getMonth());
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endMonthList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endMonthList){

                }
            }
        });





        //Removing Year option so user will not be able to select Year as an option as Integer is required.
        yearList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                yearList.removeItem("Year");
                yearList.setSelectedIndex(calendarTopUI.getYear()-calendarTopUI.getMinYear()-1);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endYearList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endYearList){
                }
            }
        });

        endYearList.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                endYearList.removeItem("Year");
                endYearList.setSelectedIndex(calendarTopUI.getYear()-calendarTopUI.getMinYear()-1);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {

            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {

            }
        });

        endYearList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==endYearList){
                }
            }
        });
        monthList.setSelectedItem("Month");
        yearList.setSelectedItem("Year");
        endMonthList.setSelectedItem("Month");
        endYearList.setSelectedItem("Year");

        //adding actionlistener to all day checkbox
        allDayCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == allDayCheckBox){
                    if(allDayCheckBox.isSelected()){
                        if(endMinList.getItemCount() == 5){
                            endMinList.removeItem("59");
                        }
                        startHourList.setEnabled(false);
                        startMinList.setEnabled(false);
                        endHourList.setEnabled(false);
                        endMinList.setEnabled(false);
                    }else{
                        if(endMinList.getItemCount() == 5){
                            endMinList.removeItem("59");
                        }
                        startHourList.setEnabled(true);
                        startMinList.setEnabled(true);
                        endHourList.setEnabled(true);
                        endMinList.setEnabled(true);
                    }
                }
            }
        });

        //restrict 30 characters for Event title , assumption is that " " is not considered a special Char
        //with reference to the example
        eventTitleField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int allowedLength = eventTitleField.getText().length();
                char ch = e.getKeyChar();
                if (Character.isLetterOrDigit(ch) || e.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE
                        || e.getExtendedKeyCode()== KeyEvent.VK_DELETE
                        || e.getExtendedKeyCode() == KeyEvent.VK_SHIFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT
                        || e.getExtendedKeyCode() == KeyEvent.VK_SPACE) {
                    if (allowedLength < 30) {
                        eventTitleField.setEditable(true);
                        eventTitleField.getCaret().setVisible(true);
                    } else if (allowedLength == 30 && (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
                            || e.getExtendedKeyCode() == KeyEvent.VK_DELETE
                            || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                            || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT)) {
                        eventTitleField.setEditable(true);
                        eventTitleField.getCaret().setVisible(true);
                    } else {
                        eventTitleField.setEditable(false);
                        eventTitleField.getCaret().setVisible(true);
                    }
                } else{
                    eventTitleField.setEditable(false);
                    eventTitleField.getCaret().setVisible(true);
                }
            }
        });

        //Configuring event Description Jtextarea
        eventDescriptionField = new JTextArea(5,10);
        eventDescriptionField.setLineWrap(true);

        //Limiting to 200 characters
        eventDescriptionField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int allowedLength = eventDescriptionField.getText().length();
                char ch = e.getKeyChar();
                if (Character.isLetterOrDigit(ch) || e.getExtendedKeyCode()== KeyEvent.VK_BACK_SPACE
                        || e.getExtendedKeyCode()== KeyEvent.VK_DELETE
                        || e.getExtendedKeyCode() == KeyEvent.VK_SHIFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                        || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT
                        || e.getExtendedKeyCode() == KeyEvent.VK_SPACE
                        || e.getExtendedKeyCode() == KeyEvent.VK_ENTER
                        || e.getExtendedKeyCode() == KeyEvent.VK_COPY
                        || e.getExtendedKeyCode() == KeyEvent.VK_PASTE ) {
                    if (allowedLength < 200) {
                        eventDescriptionField.setEditable(true);
                        eventDescriptionField.getCaret().setVisible(true);
                    } else if (allowedLength == 200 && (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE
                            || e.getExtendedKeyCode() == KeyEvent.VK_DELETE
                            || e.getExtendedKeyCode() == KeyEvent.VK_LEFT
                            || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT
                            || e.getExtendedKeyCode() == KeyEvent.VK_UP
                            || e.getExtendedKeyCode() == KeyEvent.VK_DOWN)) {
                        eventDescriptionField.setEditable(true);
                        eventDescriptionField.getCaret().setVisible(true);
                    } else {
                        eventDescriptionField.setEditable(false);
                        eventDescriptionField.getCaret().setVisible(true);
                    }
                } else{
                    eventDescriptionField.setEditable(false);
                    eventDescriptionField.getCaret().setVisible(true);
                }
            }
        });
        eventDescriptionFieldScrollPane = new JScrollPane(eventDescriptionField,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //pseudoTable configurations
        pseudoTable.setPreferredScrollableViewportSize(new Dimension(600, 300));
        pseudoTable.setFillsViewportHeight(true);
        pseudoModel.setColumnIdentifiers(columnNames);
        pseudoTable.setModel(pseudoModel);
        TableRowSorter sorter = new TableRowSorter<>(pseudoModel);
        pseudoTable.setRowSorter(sorter);

        //pseudo1Table configurations
        pseudo1Table.setPreferredScrollableViewportSize(new Dimension(600, 300));
        pseudo1Table.setFillsViewportHeight(true);
        pseudo1Model.setColumnIdentifiers(columnNames);
        pseudo1Table.setModel(pseudo1Model);
        TableRowSorter sorter1 = new TableRowSorter<>(pseudo1Model);
        pseudo1Table.setRowSorter(sorter1);


        //danger
        int counter=0;
        int[] interestedRow = new int[10];
        int numline = 0;
        int interestedEntryCounter=0;
        //danger

        //read and configure interestedRow length
        try {
            BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
            String line;


            while((line = reader.readLine()) != null) {
                numline ++;
            }
            interestedRow = new int[numline];
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Import data to Jtable using bufferedreader object
        try {
            BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
            String line;
            while((line = reader.readLine()) != null) {
                String[] fileData = line.split(" ; ");

                if(Integer.parseInt(fileData[4]) == Integer.parseInt(editRemoveDate) &&
                        (Integer.parseInt(fileData[3])) == calendarCell.getMonth() &&
                        Integer.parseInt(fileData[2]) ==  calendarCell.getYear()) {

                    pseudo1Model.addRow(line.split(" ; "));
                    interestedRow[interestedEntryCounter] = counter;
                    interestedEntryCounter++;
                }
                counter++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //for limitation is interestedEntryCounter.

        //Table selection function
        int[] finalInterestedRow = interestedRow;//cancer idk why
        editedRow = 0;
        pseudo1Table.changeSelection(0, 0, false, false);
        if (pseudo1Table.getSelectedRow() > -1) {
            // print first column value from selected row
            eventTypeField.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 0).toString());
            eventTitleField.setText(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 1).toString());
            yearList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 2).toString());
            monthList.setSelectedIndex(Integer.parseInt((String) pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 3)));
            dayList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 4).toString());
            if((pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 10).toString()).equals("True")){
                allDayCheckBox.setSelected(true);
                startHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 15).toString());
                startMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 16).toString());
                endHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 17).toString());
                if(endMinList.getItemCount() < 5){
                    endMinList.addItem("59");
                }
                endMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 18).toString());
                startHourList.setEnabled(false);
                startMinList.setEnabled(false);
                endHourList.setEnabled(false);
                endMinList.setEnabled(false);

            }else{
                allDayCheckBox.setSelected(false);
                if(endMinList.getItemCount() == 5){
                    endMinList.removeItem("59");
                }
                startHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 15).toString());
                startMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 16).toString());
                endHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 17).toString());
                endMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 18).toString());
            }
            endYearList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 6).toString());
            endMonthList.setSelectedIndex(Integer.parseInt((String)pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 7).toString()));
            enddayList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 8).toString());
            eventDescriptionField.setText(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 11).toString());
            statusList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 12).toString());
            colorList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 13).toString());
            editedRow = pseudo1Table.getSelectedRow();
        }
        pseudo1Table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {

                if (pseudo1Table.getSelectedRow() > -1) {
                    // print first column value from selected row
                    eventTypeField.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 0).toString());
                    eventTitleField.setText(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 1).toString());
                    yearList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 2).toString());
                    monthList.setSelectedIndex(Integer.parseInt((String) pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 3)));
                    dayList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 4).toString());
                    if((pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 10).toString()).equals("True")){
                        allDayCheckBox.setSelected(true);
                        startHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 15).toString());
                        startMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 16).toString());
                        endHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 17).toString());
                        if(endMinList.getItemCount() < 5){
                            endMinList.addItem("59");
                        }
                        endMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 18).toString());
                        startHourList.setEnabled(false);
                        startMinList.setEnabled(false);
                        endHourList.setEnabled(false);
                        endMinList.setEnabled(false);

                    }else{
                        allDayCheckBox.setSelected(false);
                        if(endMinList.getItemCount() == 5){
                            endMinList.removeItem("59");
                        }
                        startHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 15).toString());
                        startMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 16).toString());
                        endHourList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 17).toString());
                        endMinList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 18).toString());
                    }
                    endYearList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 6).toString());
                    endMonthList.setSelectedIndex(Integer.parseInt((String)pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 7).toString()));
                    enddayList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 8).toString());
                    eventDescriptionField.setText(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 11).toString());
                    statusList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 12).toString());
                    colorList.setSelectedItem(pseudo1Table.getValueAt(pseudo1Table.getSelectedRow(), 13).toString());
                    editedRow = pseudo1Table.getSelectedRow();
                }
            }
        });


        try {
            BufferedReader reader = new BufferedReader(new FileReader("test_info.txt"));
            String line;
            while((line = reader.readLine()) != null) {
                pseudoModel.addRow(line.split(" ; "));
            }
            reader.close();
        } catch (FileNotFoundException te) {
            te.printStackTrace();
        } catch (IOException te) {
            te.printStackTrace();
        }

        //add actionlistener to save to file button, ultimately saving all the details into a txt file
        editEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==editEventButton) {

                    String monthValue = "0";
                    String endMonthValue = "0";

                    if(monthList.getSelectedItem().equals("January")){
                        monthValue = "1";
                    }else if (monthList.getSelectedItem().equals("February")){
                        monthValue = "2";
                    }else if (monthList.getSelectedItem().equals("March")){
                        monthValue = "3";
                    }else if (monthList.getSelectedItem().equals("April")){
                        monthValue = "4";
                    }else if (monthList.getSelectedItem().equals("May")){
                        monthValue = "5";
                    }else if (monthList.getSelectedItem().equals("June")){
                        monthValue = "6";
                    }else if (monthList.getSelectedItem().equals("July")){
                        monthValue = "7";
                    }else if (monthList.getSelectedItem().equals("August")){
                        monthValue = "8";
                    }else if (monthList.getSelectedItem().equals("September")){
                        monthValue = "9";
                    }else if (monthList.getSelectedItem().equals("October")){
                        monthValue = "10";
                    }else if (monthList.getSelectedItem().equals("November")){
                        monthValue = "11";
                    }else if (monthList.getSelectedItem().equals("December")){
                        monthValue = "12";
                    }

                    if(endMonthList.getSelectedItem().equals("January")){
                        endMonthValue = "1";
                    }else if (endMonthList.getSelectedItem().equals("February")){
                        endMonthValue = "2";
                    }else if (endMonthList.getSelectedItem().equals("March")){
                        endMonthValue = "3";
                    }else if (endMonthList.getSelectedItem().equals("April")){
                        endMonthValue = "4";
                    }else if (endMonthList.getSelectedItem().equals("May")){
                        endMonthValue = "5";
                    }else if (endMonthList.getSelectedItem().equals("June")){
                        endMonthValue = "6";
                    }else if (endMonthList.getSelectedItem().equals("July")){
                        endMonthValue = "7";
                    }else if (endMonthList.getSelectedItem().equals("August")){
                        endMonthValue = "8";
                    }else if (endMonthList.getSelectedItem().equals("September")){
                        endMonthValue = "9";
                    }else if (endMonthList.getSelectedItem().equals("October")){
                        endMonthValue = "10";
                    }else if (endMonthList.getSelectedItem().equals("November")){
                        endMonthValue = "11";
                    }else if (endMonthList.getSelectedItem().equals("December")){
                        endMonthValue = "12";
                    }


                    long startValue = Long.parseLong((String)yearList.getSelectedItem()+monthValue +(String)dayList.getSelectedItem() + (String) startHourList.getSelectedItem() + (String) startMinList.getSelectedItem());
                    long endValue =Long.parseLong((String)endYearList.getSelectedItem() + endMonthValue + (String)enddayList.getSelectedItem() + (String) endHourList.getSelectedItem() + (String) endMinList.getSelectedItem());

                    if(eventTitleField.getText().equals("")||eventDescriptionField.getText().equals("")
                            ||(String)yearList.getSelectedItem() == "Year"|| (String)endYearList.getSelectedItem() == "Year"
                            ||(String)monthList.getSelectedItem() == "Month"||(String)endMonthList.getSelectedItem() == "Month"){
                        JOptionPane.showMessageDialog(null,"All fields are compulsory");
                    }else if(startValue >= endValue){
                        JOptionPane.showMessageDialog(null,"You can't have an event that ends before it starts!");
                    }
                     else {
                        //change here
                        row[0] = (String) eventTypeField.getSelectedItem();
                        row[1] = eventTitleField.getText();
                        row[2] = (String) yearList.getSelectedItem();
                        if(monthList.getSelectedItem().equals("January")){
                            row[3] = "1";
                        }else if (monthList.getSelectedItem().equals("February")){
                            row[3] = "2";
                        }else if (monthList.getSelectedItem().equals("March")){
                            row[3] = "3";
                        }else if (monthList.getSelectedItem().equals("April")){
                            row[3] = "4";
                        }else if (monthList.getSelectedItem().equals("May")){
                            row[3] = "5";
                        }else if (monthList.getSelectedItem().equals("June")){
                            row[3] = "6";
                        }else if (monthList.getSelectedItem().equals("July")){
                            row[3] = "7";
                        }else if (monthList.getSelectedItem().equals("August")){
                            row[3] = "8";
                        }else if (monthList.getSelectedItem().equals("September")){
                            row[3] = "9";
                        }else if (monthList.getSelectedItem().equals("October")){
                            row[3] = "10";
                        }else if (monthList.getSelectedItem().equals("November")){
                            row[3] = "11";
                        }else if (monthList.getSelectedItem().equals("December")){
                            row[3] = "12";
                        }
                        row[4] = (String) dayList.getSelectedItem();
                        row[6] = (String) endYearList.getSelectedItem();
                        if(endMonthList.getSelectedItem().equals("January")){
                            row[7] = "1";
                        }else if (endMonthList.getSelectedItem().equals("February")){
                            row[7] = "2";
                        }else if (endMonthList.getSelectedItem().equals("March")){
                            row[7] = "3";
                        }else if (endMonthList.getSelectedItem().equals("April")){
                            row[7] = "4";
                        }else if (endMonthList.getSelectedItem().equals("May")){
                            row[7] = "5";
                        }else if (endMonthList.getSelectedItem().equals("June")){
                            row[7] = "6";
                        }else if (endMonthList.getSelectedItem().equals("July")){
                            row[7] = "7";
                        }else if (endMonthList.getSelectedItem().equals("August")){
                            row[7] = "8";
                        }else if (endMonthList.getSelectedItem().equals("September")){
                            row[7] = "9";
                        }else if (endMonthList.getSelectedItem().equals("October")){
                            row[7] = "10";
                        }else if (endMonthList.getSelectedItem().equals("November")){
                            row[7] = "11";
                        }else if (endMonthList.getSelectedItem().equals("December")){
                            row[7] = "12";
                        }
                        row[8] = (String) enddayList.getSelectedItem();

                        if (allDayCheckBox.isSelected()) {
                            row[5] = "0000";
                            row[9] = "2359";
                            row[10] = "True";
                            row[14] = (String) yearList.getSelectedItem() + "/" +
                                    String.valueOf(1+monthList.getSelectedIndex()) + "/" +
                                    (String) dayList.getSelectedItem() + " 0000";
                            row[15] = "00";
                            row[16] = "00";
                            row[17] = "23";
                            row[18] = "59";
                        } else {
                            row[5] = (String) startHourList.getSelectedItem() +
                                    (String) startMinList.getSelectedItem();
                            row[9] = (String) endHourList.getSelectedItem() +
                                    (String) endMinList.getSelectedItem();
                            row[10] = "False";
                            row[14] = (String) yearList.getSelectedItem() + "/" +
                                    String.valueOf( 1+monthList.getSelectedIndex()) + "/" +
                                    (String) dayList.getSelectedItem() + " " + (String) startHourList.getSelectedItem() +
                                    (String) startMinList.getSelectedItem();
                            row[15] = (String) startHourList.getSelectedItem();
                            row[16] = (String) startMinList.getSelectedItem();
                            row[17] = (String) endHourList.getSelectedItem();
                            row[18] = (String) endMinList.getSelectedItem();
                        }
                        row[11] = eventDescriptionField.getText();
                        row[12] = (String) statusList.getSelectedItem();
                        row[13] = (String) colorList.getSelectedItem();


                        pseudoModel.addRow(row);
                        pseudoModel.removeRow(finalInterestedRow[editedRow]);
                        //change above

                        pseudoTable.getRowSorter().toggleSortOrder(14);
                        try {
                            FileWriter writer = new FileWriter("test_info.txt");
                            for (int trow = 0; trow < pseudoTable.getRowCount(); trow++) {
                                for (int tcol = 0; tcol < pseudoTable.getColumnCount(); tcol++) {
                                    if (tcol <= 17) {
                                        writer.append(pseudoTable.getValueAt(trow, tcol).toString() + " ; ");
                                    } else if (tcol == 18) {
                                        writer.append(pseudoTable.getValueAt(trow, tcol).toString());
                                        writer.append(System.lineSeparator());
                                    }
                                }
                            }
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        calendarPanel.updateCalendarPanel();
                        secondaryFrame.dispose();
                    }
                }
            }

        });

        removeEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == removeEventButton){
                    pseudoModel.removeRow(finalInterestedRow[editedRow]);
                    //change above

                    pseudoTable.getRowSorter().toggleSortOrder(14);
                    try {
                        FileWriter writer = new FileWriter("test_info.txt");
                        for (int trow = 0; trow < pseudoTable.getRowCount(); trow++) {
                            for (int tcol = 0; tcol < pseudoTable.getColumnCount(); tcol++) {
                                if (tcol <= 17) {
                                    writer.append(pseudoTable.getValueAt(trow, tcol).toString() + " ; ");
                                } else if (tcol == 18) {
                                    writer.append(pseudoTable.getValueAt(trow, tcol).toString());
                                    writer.append(System.lineSeparator());
                                }
                            }
                        }
                        writer.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    calendarPanel.updateCalendarPanel();
                    secondaryFrame.dispose();
                }
            }
        });

        eventEndDateField.add(endYearList);
        eventEndDateField.add(endMonthList);
        eventEndDateField.add(enddayList);
        eventEndDateField.add(Box.createRigidArea(new Dimension(9,10)));
        eventEndDateField.add(endEvent);
        eventEndDateField.add(Box.createRigidArea(new Dimension(9,10)));
        eventEndDateField.add(endHourList);
        eventEndDateField.add(endMinList);
        eventDateField.add(yearList);
        eventDateField.add(monthList);
        eventDateField.add(dayList);
        eventDateField.add(Box.createRigidArea(new Dimension(6,10)));
        eventDateField.add(startEvent);
        eventDateField.add(Box.createRigidArea(new Dimension(5,10)));
        eventDateField.add(startHourList);
        eventDateField.add(startMinList);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        this.add(eventType,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(eventTypeField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(eventTitle,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        this.add(eventTitleField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(eventStartDate,gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(eventDateField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(eventEndDate,gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        this.add(eventEndDateField,gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(allDay,gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        this.add(allDayCheckBox,gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(eventDescription,gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        this.add(eventDescriptionFieldScrollPane,gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(eventStatus,gbc);
        gbc.gridx = 1;
        gbc.gridy = 6;
        this.add(statusList,gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(eventColor,gbc);
        gbc.gridx = 1;
        gbc.gridy = 7;
        this.add(colorList,gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        this.add(editEventButton,gbc);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        this.add(removeEventButton,gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        gbc.gridheight = 9;
        gbc.fill=GridBagConstraints.VERTICAL;
        this.add(tableSP1,gbc);


    }
}

