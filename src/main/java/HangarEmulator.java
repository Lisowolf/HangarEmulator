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

import com.formdev.flatlaf.FlatDarkLaf;
import things.*;
import things.ui.HangarFrame;
import things.ui.components.HangarLabel;

import java.awt.*;
import java.io.File;
import java.util.Locale;

public class HangarEmulator {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        System.setProperty("sun.java2d.opengl", "true");
        System.setProperty("microedition.profiles", "MIDP-2.0");
        System.setProperty("microedition.platform", "HangarEmulator");
        System.setProperty("microedition.locale", Locale.getDefault().toLanguageTag());

        try {
            if (args.length > 0 && new File(args[0]).isFile()) {
                MIDletLoader.loadMIDlet(args[0]);
                MIDletLoader.startLoadedMIDlet();
            }
            else {
                var hangarFrame = HangarFrame.getInstance();
                var hangarLabel = new HangarLabel();

                hangarLabel.setPreferredSize(new Dimension(360, 360));
                hangarFrame.add(hangarLabel);
                hangarFrame.pack();
                hangarFrame.revalidate();
            }
            HangarState.setProgramFile(new File(HangarEmulator.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
            HangarFrame.getInstance().setVisible(true);
            HangarFrame.getInstance().setLocationRelativeTo(null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}