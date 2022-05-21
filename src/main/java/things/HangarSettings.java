package things;

import things.enums.Keyboards;

import javax.swing.*;
import java.awt.*;

public class HangarSettings extends JFrame {
    private static Keyboards selectedKeyboard = Keyboards.Default;

    public HangarSettings() {
        this.setTitle("Settings");
        this.setSize(new Dimension(240, 128));
        AddKeyboardPanel();
    }

    public static Keyboards getKeyboard() {
        return selectedKeyboard;
    }

    private void AddKeyboardPanel() {
        var keyboardPanel = new JPanel();
        keyboardPanel.setBorder(BorderFactory.createTitledBorder("Keyboard"));

        var keyboardCombobox = new JComboBox<>(Keyboards.values());
        keyboardCombobox.setSelectedItem(selectedKeyboard);

        keyboardCombobox.addActionListener(event -> {
            var source = (JComboBox) event.getSource();
            selectedKeyboard = (Keyboards) source.getSelectedItem();

            var keyListeners = HangarPanel.getInstance().getKeyListeners();
            if (keyListeners.length > 0) {
                var hangarKeyListeners = (HangarKeyListener) keyListeners[0];
                hangarKeyListeners.getPressedKeys().clear();
            }
        });

        keyboardPanel.add(keyboardCombobox);
        this.add(keyboardPanel);
    }
}