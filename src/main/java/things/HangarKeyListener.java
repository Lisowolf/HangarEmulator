package things;

import things.enums.Keyboards;
import things.utils.KeyCodeConverter;

import javax.microedition.lcdui.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HangarKeyListener implements KeyListener {
    private boolean keyIsRepeated = false;

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        var displayable = HangarPanel.getDisplayable();
        if (displayable instanceof Canvas) {
            int convertedKeyCode = KeyCodeConverter.awtToDefault(e.getKeyCode());
            if (HangarSettings.getKeyboard() == Keyboards.Nokia) {
                convertedKeyCode = KeyCodeConverter.defaultToNokia(convertedKeyCode);
            }
            var canvas = (Canvas)displayable;

            if (!keyIsRepeated) {
                canvas.keyPressed(convertedKeyCode);
                keyIsRepeated = true;
            }
            else {
                canvas.keyRepeated(convertedKeyCode);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        var displayable = HangarPanel.getDisplayable();
        if (displayable instanceof Canvas) {
            int convertedKeyCode = KeyCodeConverter.awtToDefault(e.getKeyCode());
            if (HangarSettings.getKeyboard() == Keyboards.Nokia) {
                convertedKeyCode = KeyCodeConverter.defaultToNokia(convertedKeyCode);
            }

            var canvas = (Canvas)displayable;
            canvas.keyReleased(convertedKeyCode);
            keyIsRepeated = false;
        }
    }
}