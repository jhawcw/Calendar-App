import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;

public class CalendarCell extends JPanel {
    private String eventType; // compulsory
    private String eventTitle; // compulsory
    private String eventDate; // compulsory
    private String eventID; //unique compulsory
    private String eventDuration; // optional , to implement full day
    private String eventStatus; // pending/completed/inprogress/KIV compulsory
    private Color eventColor; //optional
    private Settings settings;
    private CalendarTopUI calendarTopUI;
    private CalendarPanel calendarPanel;
    private int month;
    private int year;

    public CalendarCell() {
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
    }

    public CalendarCell(String calendarDate,int calendarMonth,int calendarYear,Settings settings,CalendarTopUI calendarTopUI,CalendarPanel calendarPanel) {
        this.calendarTopUI=calendarTopUI;
        this.calendarPanel=calendarPanel;
        this.month = calendarMonth;
        this.year = calendarYear;
        JLabel datelabel = new JLabel(calendarDate,SwingConstants.CENTER);
        datelabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource() == datelabel){
                    try {
                        Settings entrySettings = new Settings(new Font("Consolas", Font.PLAIN, 20));
                        Frame eventEntryWindow = new Frame(300, 200, "Event Entry");
                        EventEntryPanel eventEntryPanel = new EventEntryPanel(entrySettings, calendarTopUI, calendarPanel, eventEntryWindow, calendarDate, CalendarCell.this);
                        eventEntryWindow.add(eventEntryPanel);
                        eventEntryWindow.pack();
                        eventEntryWindow.setLocationRelativeTo(null);
                        eventEntryWindow.setVisible(true);
                    }catch(Exception E){
                        JOptionPane.showMessageDialog(null,"No event for selected date");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                datelabel.setForeground(Color.RED);
                datelabel.setFont(new Font("Consolas",Font.BOLD,35));
                datelabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                datelabel.setForeground(settings.getForegroundColor());
                datelabel.setFont(settings.getFont());
                datelabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
//        datelabel.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                if(e.getSource() == datelabel) {
//                    datelabel.setForeground(Color.RED);
//                }
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                if(e.getSource() == datelabel) {
//                    datelabel.setForeground(Color.BLACK);
//                }
//            }
//        });
        this.setLayout(new GridLayout(2,1));
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        this.settings = settings;
        datelabel.setForeground(settings.getForegroundColor());
        datelabel.setFont(settings.getFont());
        this.add(datelabel);

    }

    public CalendarCell(String calendarDate,int calendarMonth,int calendarYear,Settings settings,Color color,CalendarTopUI calendarTopUI,CalendarPanel calendarPanel) {
        this.calendarTopUI=calendarTopUI;
        this.calendarPanel=calendarPanel;
        this.month = calendarMonth;
        this.year = calendarYear;
        JLabel datelabel = new JLabel(calendarDate,SwingConstants.CENTER);
        datelabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource() == datelabel){
                    try {
                        Settings entrySettings = new Settings(new Font("Consolas", Font.PLAIN, 20));
                        Frame eventEntryWindow = new Frame(300, 200, "Event Entry");
                        EventEntryPanel eventEntryPanel = new EventEntryPanel(entrySettings, calendarTopUI, calendarPanel, eventEntryWindow, calendarDate, CalendarCell.this);
                        eventEntryWindow.add(eventEntryPanel);
                        eventEntryWindow.pack();
                        eventEntryWindow.setLocationRelativeTo(null);
                        eventEntryWindow.setVisible(true);
                    }catch (Exception E){
                        JOptionPane.showMessageDialog(null,"No event for selected date");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                datelabel.setForeground(Color.RED);
                datelabel.setFont(new Font("Consolas",Font.BOLD,35));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                datelabel.setForeground(settings.getForegroundColor());
                datelabel.setFont(settings.getFont());
            }
        });
        this.setLayout(new GridLayout(2,1));
        this.setBorder(BorderFactory.createLineBorder(color,3));
        this.settings = settings;
        datelabel.setForeground(settings.getForegroundColor());
        datelabel.setFont(settings.getFont());
        this.add(datelabel);
    }

    public CalendarCell(Settings settings,Color color) {
        this.setBorder(BorderFactory.createLineBorder(color,3));
        this.settings = settings;
        this.setForeground(settings.getForegroundColor());
        this.setFont(settings.getFont());

    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}


//    public CalendarCell() {
//        this.setEditable(false);
//        this.setBorder(BorderFactory.createLineBorder(Color.gray));
//    }
//    public CalendarCell(String calendarDate) {
//        this.setText(calendarDate);
//        this.setBorder(BorderFactory.createLineBorder(Color.gray));
//        this.setEditable(false);
//    }
//
//    public CalendarCell(String calendarDate,Settings settings) {
//        this.setText(calendarDate);
//        this.setBorder(BorderFactory.createLineBorder(Color.gray));
//        this.setEditable(false);
//        this.settings = settings;
//        this.setForeground(settings.getForegroundColor());
//        this.setFont(settings.getFont());
//
//    }
//
//    public CalendarCell(String calendarDate,Settings settings,Color color) {
//        this.setText(calendarDate);
//        this.setBorder(BorderFactory.createLineBorder(color,3));
//        this.setEditable(false);
//        this.settings = settings;
//        this.setForeground(settings.getForegroundColor());
//        this.setFont(settings.getFont());
//
//    }
//
//    public CalendarCell(Settings settings,Color color) {
//        this.setBorder(BorderFactory.createLineBorder(color,3));
//        this.setEditable(false);
//        this.settings = settings;
//        this.setForeground(settings.getForegroundColor());
//        this.setFont(settings.getFont());
//
//    }