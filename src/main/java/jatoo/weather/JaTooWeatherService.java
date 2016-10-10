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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jatoo.resources.ResourcesTexts;

public abstract class JaTooWeatherService {

  /** the texts */
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

  public abstract JaTooWeather getWeather(String city) throws IOException;

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
