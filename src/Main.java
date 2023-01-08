import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Main {


    public static void main(String[] args) {
        Frame loginWindow = new Frame(300,200,"User Login");
        User user = new User();
        LoginPanel loginPanel = new LoginPanel(user,new Settings(new Font("Consolas",Font.PLAIN,20),Color.BLACK,Color.BLACK));
        loginWindow.setLayout(new BorderLayout());
        loginWindow.add(loginPanel,BorderLayout.CENTER);
        loginWindow.setVisible(true);
        loginWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginPanel.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == loginPanel.getLoginButton() && user.getLoginID().equals(loginPanel.getLoginField().getText()) && user.getPassword().equals(loginPanel.getPasswordField().getText())){
                    loginWindow.setVisible(false);

                    Settings calendarPanelSettings = new Settings(new Font("Consolas",Font.PLAIN,20),Color.BLACK,Color.BLACK);
                    Settings calendarSecondaryCellsSettings = new Settings(Color.LIGHT_GRAY,new Font("Consolas",Font.PLAIN,20));
                    Frame window = new Frame("2023 Diary");
                    CalendarTopUI calendarTopUI = new CalendarTopUI(calendarPanelSettings);
                    CalendarPanel calendarPanel = new CalendarPanel(calendarPanelSettings,calendarTopUI,calendarSecondaryCellsSettings);
                    CalendarBottomUI calendarBottomUI = new CalendarBottomUI(calendarPanelSettings,window,loginWindow);

                    // updating calendarPanel with Jcombobox
                    calendarTopUI.getMonthDropdownList().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==calendarTopUI.getMonthDropdownList()){
                                calendarTopUI.setMonth(calendarTopUI.monthNumber(calendarTopUI.getMonthDropdownList()));
                                calendarPanel.updateCalendarPanel();
                            }
                        }
                    });
                    calendarTopUI.getYearDropdownList().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==calendarTopUI.getYearDropdownList()){
                                calendarTopUI.setYear(Integer.parseInt((String) Objects.requireNonNull(calendarTopUI.getYearDropdownList().getSelectedItem())));
                                calendarPanel.updateCalendarPanel();
                            }
                        }
                    });

                    // updating calendarPanel with Jbutton
                    calendarTopUI.getLeftCalendarButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                if (e.getSource() == calendarTopUI.getLeftCalendarButton()) {
                                    if (calendarTopUI.getMonthDropdownList().getSelectedIndex() > 0) {
                                        calendarTopUI.getMonthDropdownList().setSelectedIndex(calendarTopUI.getMonthDropdownList().getSelectedIndex() - 1);
                                        calendarPanel.updateCalendarPanel();
                                    } else {
                                        calendarTopUI.getYearDropdownList().setSelectedIndex(calendarTopUI.getYearDropdownList().getSelectedIndex() - 1);
                                        calendarTopUI.getMonthDropdownList().setSelectedIndex(11);
                                        calendarPanel.updateCalendarPanel();
                                    }
                                }
                            }catch (Exception une){
                                calendarTopUI.getYearDropdownList().setSelectedIndex(0);
                                JOptionPane.showMessageDialog(null, "Stop pressing, its the end of the list, no one keeps track of something 200 years ago!");
                            }
                        }
                    });
                    calendarTopUI.getRightCalendarButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                if (e.getSource() == calendarTopUI.getRightCalendarButton()) {
                                    if (calendarTopUI.getMonthDropdownList().getSelectedIndex() < 11) {
                                        calendarTopUI.getMonthDropdownList().setSelectedIndex(calendarTopUI.getMonthDropdownList().getSelectedIndex() + 1);
                                        calendarPanel.updateCalendarPanel();
                                    } else {
                                        calendarTopUI.getYearDropdownList().setSelectedIndex(calendarTopUI.getYearDropdownList().getSelectedIndex() + 1);
                                        calendarTopUI.getMonthDropdownList().setSelectedIndex(0);
                                        calendarPanel.updateCalendarPanel();
                                    }
                                }
                            }catch (Exception une){
                                calendarTopUI.getYearDropdownList().setSelectedIndex(399);
                                JOptionPane.showMessageDialog(null, "Stop pressing, its the end of the list, no one keeps track of something 200 years ahead!");
                            }
                        }
                    });

                    // implementing add function of addEventButton
                    calendarTopUI.getAddEventButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(e.getSource() == calendarTopUI.getAddEventButton()){
                                Settings entrySettings = new Settings(new Font("Consolas",Font.PLAIN,20));
                                Frame eventEntryWindow = new Frame(300,200,"Event Entry");
                                EventEntryPanel eventEntryPanel = new EventEntryPanel(entrySettings,calendarTopUI,eventEntryWindow,calendarPanel);
                                eventEntryWindow.add(eventEntryPanel);
                                eventEntryWindow.pack();
                                eventEntryWindow.setLocationRelativeTo(null);
                                eventEntryWindow.setVisible(true);
                            }
                        }
                    });

                    // Adding of all components
                    window.add(calendarBottomUI,BorderLayout.SOUTH);
                    window.add(calendarPanel, BorderLayout.CENTER);
                    window.add(calendarTopUI, BorderLayout.NORTH);
                    window.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Wrong ID/Password!");
                }
            }
        });




    }


}
