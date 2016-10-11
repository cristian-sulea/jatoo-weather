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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jatoo.resources.ResourcesTexts;

/**
 * The base of a weather service, implementing the core functionality and also a
 * caching system.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 1.1, October 11, 2016
 */
@SuppressWarnings("unchecked")
public abstract class JaTooWeatherService {

  /** the logger */
  private static final Log logger = LogFactory.getLog(JaTooWeatherService.class);

  /** the file where the cache will be saved */
  private static final File CACHE_FILE = new File(System.getProperty("user.home"), ".jatoo/weather/cache.obj");
  static {
    CACHE_FILE.getParentFile().mkdirs();
  }

  /** the expiration threshold for the cache objects */
  private static final long CACHE_EXPIRATION_THRESHOLD = 5 * 60 * 1000L;

  /** the cache */
  private static final Map<String, JaTooWeather> CACHE = new HashMap<>();
  static {
    try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(CACHE_FILE))) {
      CACHE.putAll((Map<String, JaTooWeather>) stream.readObject());
    } catch (IOException | ClassNotFoundException e) {
      logger.warn("failed to read the cache from the cache file", e);
    }
  }

  /** the texts resources */
  private final ResourcesTexts texts;

  private final String missingValueText;
  private final String unitSeparatorText;
  private final String valueSeparatorText;

  public JaTooWeatherService(final String language) {

    texts = new ResourcesTexts(getClass(), JaTooWeatherService.class, language);

    missingValueText = texts.getText("jatoo.weather.missingValue");
    unitSeparatorText = texts.getText("jatoo.weather.unitSeparator");
    valueSeparatorText = texts.getText("jatoo.weather.valueSeparator");
  }

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
        logger.error("failed to get the weather", t);
      }
    }

    else {

      synchronized (CACHE) {

        weather = CACHE.get(city);

        if (weather == null || (System.currentTimeMillis() - weather.timestamp) > CACHE_EXPIRATION_THRESHOLD) {

          try {

            weather = getWeatherImpl(city);
            weather.timestamp = System.currentTimeMillis();

            CACHE.put(city, weather);

            try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(CACHE_FILE))) {
              stream.writeObject(CACHE);
            } catch (IOException e) {
              logger.error("failed to write the cache to the cache file", e);
            }
          }

          catch (Throwable t) {
            weather = null;
            logger.error("failed to get the weather", t);
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

  public String getDescription(final JaTooWeather weather) {

    if (weather.description == null) {
      return missingValueText;
    }

    return weather.description;
  }

  public String getTemperature(final JaTooWeather weather) {

    if (weather.temperature == null) {
      return missingValueText;
    }

    return weather.temperature + unitSeparatorText + texts.getText("jatoo.weather.temperature.unit." + weather.temperatureUnit.name());
  }

  public String getTemperatureWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.temperature.text") + valueSeparatorText + getTemperature(weather);
  }

  public String getHumidity(final JaTooWeather weather) {

    if (weather.humidity == null) {
      return missingValueText;
    }

    return weather.humidity + unitSeparatorText + texts.getText("jatoo.weather.humidity.unit." + weather.humidityUnit.name());
  }

  public String getHumidityWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.humidity.text") + valueSeparatorText + getHumidity(weather);
  }

  public String getPressure(final JaTooWeather weather) {

    if (weather.pressure == null) {
      return missingValueText;
    }

    return weather.pressure + unitSeparatorText + texts.getText("jatoo.weather.pressure.unit." + weather.pressureUnit.name());
  }

  public String getPressureWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.pressure.text") + valueSeparatorText + getPressure(weather);
  }

  public String getWind(final JaTooWeather weather) {

    if (weather.wind == null) {
      return missingValueText;
    }

    return weather.wind + unitSeparatorText + texts.getText("jatoo.weather.wind.unit." + weather.windUnit.name());
  }

  public String getWindWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.wind.text") + valueSeparatorText + getWind(weather);
  }

  public String getWindDirection(final JaTooWeather weather) {

    if (weather.windDirection == null) {
      return missingValueText;
    }

    return weather.windDirection + unitSeparatorText + texts.getText("jatoo.weather.windDirection.unit." + weather.windDirectionUnit.name());
  }

  public String getWindDirectionWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.windDirection.text") + valueSeparatorText + getWindDirection(weather);
  }

  public String getClouds(final JaTooWeather weather) {

    if (weather.clouds == null) {
      return missingValueText;
    }

    return weather.clouds + unitSeparatorText + texts.getText("jatoo.weather.clouds.unit." + weather.cloudsUnit.name());
  }

  public String getCloudsWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.clouds.text") + valueSeparatorText + getClouds(weather);
  }

  public String getRain(final JaTooWeather weather) {

    if (weather.rain == null) {
      return missingValueText;
    }

    return weather.rain + "";
  }

  public String getRainWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.rain.text") + valueSeparatorText + getRain(weather);
  }

  public String getSnow(final JaTooWeather weather) {

    if (weather.snow == null) {
      return missingValueText;
    }

    return weather.snow + "";
  }

  public String getSnowWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.snow.text") + valueSeparatorText + getSnow(weather);
  }

  public String getSunrise(final JaTooWeather weather) {

    if (weather.sunrise == null) {
      return missingValueText;
    }

    return new SimpleDateFormat(texts.getText("jatoo.weather.sunrise.pattern")).format(new Date(weather.sunrise));
  }

  public String getSunriseWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.sunrise.text") + valueSeparatorText + getSunrise(weather);
  }

  public String getSunset(final JaTooWeather weather) {

    if (weather.sunset == null) {
      return missingValueText;
    }

    return new SimpleDateFormat(texts.getText("jatoo.weather.sunset.pattern")).format(new Date(weather.sunset));
  }

  public String getSunsetWithText(final JaTooWeather weather) {
    return texts.getText("jatoo.weather.sunset.text") + valueSeparatorText + getSunset(weather);
  }

}
