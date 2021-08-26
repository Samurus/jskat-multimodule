/**
 * Copyright (C) 2020 Jan Schäfer (jansch@users.sourceforge.net)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jskat;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.jskat.data.DesktopSavePathResolver;
import org.jskat.data.JSkatOptions;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.LoggerFactory;

/**
 * Abstract class for all JSkat unit tests
 */
public abstract class AbstractJSkatTest {

    /**
     * Creates the logger
     */
    @BeforeAll
    public static void createLogger() {
        final JSkatOptions options = JSkatOptions.instance(new DesktopSavePathResolver());

        options.resetToDefault();
        final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.setLevel(Level.DEBUG );
    }
}
