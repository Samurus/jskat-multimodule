/**
 * This file is part of JSkat.
 *
 * JSkat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JSkat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JSkat.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jskat;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SplashScreen;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.log4j.PropertyConfigurator;
import org.jskat.control.JSkatEventBus;
import org.jskat.control.JSkatMaster;
import org.jskat.control.command.general.ShowWelcomeInformationCommand;
import org.jskat.data.DesktopSavePathResolver;
import org.jskat.data.JSkatOptions;
import org.jskat.data.JSkatOptions.Option;
import org.jskat.gui.img.JSkatGraphicRepository;
import org.jskat.gui.swing.JSkatViewImpl;
import org.jskat.gui.swing.LookAndFeelSetter;
import org.jskat.util.JSkatResourceBundle;
import org.jskat.util.version.VersionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for JSkat.
 */
public final class JSkat {

    private static final Logger LOG = LoggerFactory.getLogger(JSkat.class);

    private static final String VERSION = "0.15.0"; //$NON-NLS-1$

    /**
     * Main method
     *
     * @param args
     *            Command line arguments
     */
    public static void main(final String[] args) {

        PropertyConfigurator.configure(ClassLoader
                .getSystemResource("org/jskat/config/log4j.properties")); //$NON-NLS-1$
        LOG.debug("Welcome to JSkat!"); //$NON-NLS-1$

        initializeOptions();

        final SplashScreen splash = SplashScreen.getSplashScreen();
        Graphics2D g = null;
        if (splash == null) {
            LOG.error("SplashScreen not found. Please try to set the vm parameter: -splash:src/main/resources/org/jskat/gui/img/gui/splash.png");
        } else {
            g = splash.createGraphics();
        }

        JSkatViewImpl jskatView = null;
        for (int i = 0; i < 3; i++) {
            if (splash != null && g != null) {
                renderSplashFrame(g, i);
                splash.update();
            }
            switch (i) {
            case 0:
            	trySettingNimbusLookAndFeel();
                break;
            case 1:
				JSkatGraphicRepository.INSTANCE.toString();
                break;
            case 2:
                jskatView = new JSkatViewImpl();
                JSkatMaster.INSTANCE.setView(jskatView);
                break;
            }
        }

        if (splash != null && g != null) {
            splash.close();
        }

        jskatView.setVisible();

        if (JSkatOptions.instance().getBoolean(Option.SHOW_TIPS_AT_START_UP)) {
			JSkatEventBus.INSTANCE.post(new ShowWelcomeInformationCommand());
        }

        if (JSkatOptions.instance().getBoolean(
                Option.CHECK_FOR_NEW_VERSION_AT_START_UP)) {
            JSkatMaster.INSTANCE.checkJSkatVersion(getVersion(),
                    VersionChecker.getLatestVersion());
        }
    }

    /**
     * Gets the version of JSkat.
     *
     * @return Version of JSkat
     */
    public static String getVersion() {
        return VERSION;
    }

    private static void renderSplashFrame(Graphics2D g, int frame) {
		final JSkatResourceBundle strings = JSkatResourceBundle.INSTANCE;
        final String[] frameStrings = {
                strings.getString("splash_init_application"),
                strings.getString("splash_load_card_sets"),
                strings.getString("splash_look_for_ai_players") };
        g.setComposite(AlphaComposite.Clear);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(10, 280, 200, 40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
		g.drawString(frameStrings[frame] + "...", 10, 290);
    }

    private static void initializeOptions() {
        JSkatOptions.instance(new DesktopSavePathResolver());
    }

    private static void trySettingNimbusLookAndFeel() {
        for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(laf.getName())) { //$NON-NLS-1$
                LookAndFeelSetter.setLookAndFeel();
            }
        }
    }

    /**
     * Private constructor to prevent instatiations of this class.
     */
    private JSkat() {

    }
}
