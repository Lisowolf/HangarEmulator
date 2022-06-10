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
import java.awt.image.BufferedImage;

public class HangarPanel extends JPanel {
    private static HangarPanel instance;
    private static Displayable displayable;
    private BufferedImage flushedBuffer;
    private Runnable callSerially;

    private HangarPanel() {
        setPreferredSize(HangarState.getResolution());
    }

    public static HangarPanel getInstance() {
        if (instance == null) {
            instance = new HangarPanel();
        }
        return instance;
    }

    public Displayable getDisplayable() {
        return displayable;
    }

    public void setDisplayable(Displayable displayable) {
        removeAll();
        HangarPanel.displayable = displayable;

        if (getDisplayable() instanceof javax.microedition.lcdui.Canvas canvas) {
            HangarFrame.getInstance().setHangarPanel();
            canvas.showNotify();
        }
    }

    public void setFlushedBuffer(BufferedImage buffer) {
        this.flushedBuffer = buffer;
    }

    public void setCallSerially(Runnable runnable) {
        this.callSerially = runnable;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        HangarState.syncWithFrameRate();
        HangarState.applyRenderingHints(graphics);

        if (flushedBuffer != null) {
            graphics.drawImage(flushedBuffer, 0, 0, null);
            flushedBuffer = null;
        }
        else if (displayable != null) {
            if (displayable.getWidth() != this.getWidth() || displayable.getHeight() != this.getHeight()) {
                HangarState.setResolution(getSize());
                displayable.sizeChanged(this.getWidth(), this.getHeight());
            }

            if (HangarState.getCanvasClearing()) {
                super.paintComponent(graphics);
            }

            if (displayable instanceof javax.microedition.lcdui.Canvas canvas) {
                canvas.paint(new javax.microedition.lcdui.Graphics(graphics));
            }
            else if (displayable instanceof javax.microedition.lcdui.List list) {
                list.paint(graphics);
            }
        }

        if (callSerially != null) {
            callSerially.run();
        }
    }
}