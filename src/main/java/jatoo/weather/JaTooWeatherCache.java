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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The cache.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 1.0, October 19, 2016
 */
@SuppressWarnings("unchecked")
public class JaTooWeatherCache {

  /** The logger. */
  private static final Log LOGGER = LogFactory.getLog(JaTooWeatherCache.class);

  /** The file where the cache will be saved. */
  private static final File CACHE_FILE = new File(System.getProperty("user.home"), ".jatoo/weather/cache.obj");
  static {
    CACHE_FILE.getParentFile().mkdirs();
  }

  /** The cache. */
  private static final Map<String, JaTooWeatherCacheObject> CACHE = new HashMap<>();

  /** The singleton instance. */
  private static final JaTooWeatherCache INSTANCE = new JaTooWeatherCache();

  /**
   * The getter for the singleton instance.
   * 
   * @return the singleton instance
   */
  public static JaTooWeatherCache getInstance() {
    return INSTANCE;
  }

  /** Private constructor for singleton classes. */
  private JaTooWeatherCache() {
    load();
  }

  public boolean load() {

    try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(CACHE_FILE))) {
      CACHE.putAll((Map<String, JaTooWeatherCacheObject>) stream.readObject());
    }

    catch (IOException | ClassNotFoundException e) {
      LOGGER.warn("failed to load the cache", e);
      return false;
    }

    return true;
  }

  public boolean save() {

    try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(CACHE_FILE))) {
      stream.writeObject(CACHE);
    }

    catch (IOException e) {
      LOGGER.error("failed to save the cache", e);
      return false;
    }

    return true;
  }

  public void add(final JaTooWeatherService service, final String city, final JaTooWeather weather) {
    CACHE.put(createKey(service, city), new JaTooWeatherCacheObject(weather));
  }

  public JaTooWeather get(final JaTooWeatherService service, final String city) {

    JaTooWeatherCacheObject weather = CACHE.get(createKey(service, city));

    if (weather == null) {
      return null;
    }

    return new JaTooWeather(service, weather);
  }

  private String createKey(final JaTooWeatherService service, final String city) {
    return service.getClass().getName() + "." + city;
  }

}
