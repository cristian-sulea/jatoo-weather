/*
 * Copyright (C) Cristian Sulea ( http://cristian.sulea.net )
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package jatoo.weather;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jatoo.resources.ResourcesTexts;

/**
 * The base of a weather service, implementing the core functionality and also a
 * caching system.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 2.0, October 19, 2016
 */
public abstract class JaTooWeatherService {

  /** The logger. */
  private static final Log LOGGER = LogFactory.getLog(JaTooWeatherService.class);

  /** The expiration threshold for the cache objects. */
  private static final long CACHE_EXPIRATION_THRESHOLD = TimeUnit.MINUTES.toMillis(5);

  /** The cache. */
  private static final JaTooWeatherCache CACHE = JaTooWeatherCache.getInstance();

  /** The texts resources. */
  private final ResourcesTexts texts;

  /**
   * The constructor.
   * 
   * @param language
   *          the language for which texts are desired
   */
  public JaTooWeatherService(final String language) {
    texts = new ResourcesTexts(getClass(), JaTooWeatherService.class, language);
  }

  /**
   * The constructor.
   */
  public JaTooWeatherService() {
    this(null);
  }

  public final JaTooWeather getWeather(final String city, final boolean useCache) {

    JaTooWeather weather;

    if (!useCache) {

      try {
        weather = getWeatherImpl(city);
        weather.timestamp = System.currentTimeMillis();
      }

      catch (Throwable t) {
        weather = null;
        LOGGER.error("failed to get the weather", t);
      }
    }

    else {

      synchronized (CACHE) {

        weather = CACHE.get(this, city);

        if (weather == null || (System.currentTimeMillis() - weather.timestamp) > CACHE_EXPIRATION_THRESHOLD) {

          try {

            weather = getWeatherImpl(city);
            weather.timestamp = System.currentTimeMillis();

            CACHE.add(this, city, weather);
            CACHE.save();
          }

          catch (Throwable t) {
            weather = null;
            LOGGER.error("failed to get the weather", t);
          }
        }
      }
    }

    return weather;
  }

  public final JaTooWeather getWeather(final String city) {
    return getWeather(city, true);
  }

  protected abstract JaTooWeather getWeatherImpl(String city) throws Throwable;

  /**
   * Gets the text ({@link String}) for the given key.
   * 
   * @param key
   *          the key for the desired text
   * 
   * @return the text for the given key, or the key if the text was not found
   * 
   * @see jatoo.resources.ResourcesTexts#getText(java.lang.String)
   */
  public String getText(String key) {
    return this.texts.getText(key);
  }

  /**
   * Format the given arguments using the text for the given key.
   * 
   * @param key
   *          the key for the desired text
   * @param arguments
   *          object(s) to format
   * 
   * @return the formatted text
   * 
   * @see jatoo.resources.ResourcesTexts#getText(java.lang.String,
   *      java.lang.Object[])
   */
  public String getText(String key, Object... arguments) {
    return this.texts.getText(key, arguments);
  }

}
