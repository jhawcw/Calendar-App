import javax.swing.*;

public class DropdownList extends JComboBox {
    private Settings settings;

    public DropdownList(Settings settings,String[] stringArray) {
        this.settings = settings;
        this.setModel(new DefaultComboBoxModel(stringArray));
    }

    public DropdownList(String[] stringArray) {
        this.setModel(new DefaultComboBoxModel(stringArray));
    }
}
