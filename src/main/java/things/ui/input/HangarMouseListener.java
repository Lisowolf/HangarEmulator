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

package things.ui.input;

import things.ui.components.HangarPanel;
import things.utils.HangarPanelUtils;

import javax.microedition.lcdui.Canvas;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class HangarMouseListener extends MouseInputAdapter {
    private final HangarPanel hangarPanel;

    public HangarMouseListener(HangarPanel hangarPanel) {
        this.hangarPanel = hangarPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (hangarPanel.getDisplayable() instanceof Canvas canvas) {
            var point = HangarPanelUtils.panelPointToCanvas(hangarPanel, e.getX(), e.getY());
            canvas.pointerPressed(point.x, point.y);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (hangarPanel.getDisplayable() instanceof Canvas canvas) {
            var point = HangarPanelUtils.panelPointToCanvas(hangarPanel, e.getX(), e.getY());
            canvas.pointerReleased(point.x, point.y);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (hangarPanel.getDisplayable() instanceof Canvas canvas) {
            var point = HangarPanelUtils.panelPointToCanvas(hangarPanel, e.getX(), e.getY());
            canvas.pointerDragged(point.x, point.y);
        }
    }
}