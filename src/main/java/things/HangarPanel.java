/*
 * Copyright 2022 Kirill Lomakin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package things;

import javax.microedition.lcdui.Displayable;
import javax.swing.*;
import java.awt.*;

public class HangarPanel extends JPanel {
    private static HangarPanel instance;
    private static Displayable displayable;
    private static boolean clearScreen;

    private HangarPanel() {
        setPreferredSize(HangarState.getResolution());
    }

    public static HangarPanel getInstance() {
        if (instance == null) {
            instance = new HangarPanel();
        }
        return instance;
    }

    public static Displayable getDisplayable() {
        return displayable;
    }

    public static void setDisplayable(Displayable displayable) {
        HangarPanel.displayable = displayable;
        if (getDisplayable() instanceof javax.microedition.lcdui.Canvas) {
            var canvas = (javax.microedition.lcdui.Canvas) displayable;
            canvas.showNotify();
        }
    }

    public void setScreenClearing(boolean clear) {
        clearScreen = clear;
    }

    public boolean getScreenClearing() {
        return clearScreen;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        if (displayable != null) {
            if (clearScreen) {
                super.paintComponent(graphics);
            }
            if (displayable instanceof javax.microedition.lcdui.Canvas canvas) {
                if (canvas.getWidth() != this.getWidth() || canvas.getHeight() != this.getHeight()) {
                    HangarState.setResolution(getSize());
                    canvas.sizeChanged(this.getWidth(), this.getHeight());
                }
                canvas.paint(new javax.microedition.lcdui.Graphics(graphics));
            }
        }
    }
}