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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The object representation of the weather.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 2.0, October 18, 2016
 */
public class JaTooWeather {

  public static enum TEMPERATURE_UNIT {
    CELSIUS,
    FAHRENHEIT,
    KELVIN
  }

  public static enum HUMIDITY_UNIT {
    PERCENT
  }

  public static enum PRESSURE_UNIT {
    HPA
  }

  public static enum WIND_UNIT {
    METER_PER_SEC,
    MILES_PER_HOUR
  }

  public static enum WIND_DIRECTION_UNIT {
    DEGREES_METEOROLOGICAL
  }

  public static enum CLOUDS_UNIT {
    PERCENT
  }

  public static enum RAIN_UNIT {

  }

  public static enum SNOW_UNIT {

  }

  public long timestamp;

  public String city;
  public String description;

  public Number temperature;
  public TEMPERATURE_UNIT temperatureUnit;

  public Number humidity;
  public HUMIDITY_UNIT humidityUnit;

  public Number pressure;
  public PRESSURE_UNIT pressureUnit;

  public Number wind;
  public WIND_UNIT windUnit;

  public Number windDirection;
  public WIND_DIRECTION_UNIT windDirectionUnit;

  public Number clouds;
  public CLOUDS_UNIT cloudsUnit;

  public Number rain;
  public RAIN_UNIT rainUnit;

  public Number snow;
  public SNOW_UNIT snowUnit;

  public Long sunrise;
  public Long sunset;

  private final JaTooWeatherService service;

  private final String missingValueText;
  private final String unitSeparatorText;
  private final String valueSeparatorText;

  public JaTooWeather(final JaTooWeatherService service) {

    this.service = service;

    this.missingValueText = service.getText("jatoo.weather.missingValue");
    this.unitSeparatorText = service.getText("jatoo.weather.unitSeparator");
    this.valueSeparatorText = service.getText("jatoo.weather.valueSeparator");
  }

  public JaTooWeather(final JaTooWeatherService service, final JaTooWeatherCacheObject weather) {

    this.service = service;

    this.missingValueText = service.getText("jatoo.weather.missingValue");
    this.unitSeparatorText = service.getText("jatoo.weather.unitSeparator");
    this.valueSeparatorText = service.getText("jatoo.weather.valueSeparator");

    this.timestamp = weather.getTimestamp();

    this.city = weather.getCity();
    this.description = weather.getDescription();

    this.temperature = weather.getTemperature();
    this.temperatureUnit = weather.getTemperatureUnit();

    this.humidity = weather.getHumidity();
    this.humidityUnit = weather.getHumidityUnit();

    this.pressure = weather.getPressure();
    this.pressureUnit = weather.getPressureUnit();

    this.wind = weather.getWind();
    this.windUnit = weather.getWindUnit();

    this.windDirection = weather.getWindDirection();
    this.windDirectionUnit = weather.getWindDirectionUnit();

    this.clouds = weather.getClouds();
    this.cloudsUnit = weather.getCloudsUnit();

    this.rain = weather.getRain();
    this.rainUnit = weather.getRainUnit();

    this.snow = weather.getSnow();
    this.snowUnit = weather.getSnowUnit();

    this.sunrise = weather.getSunrise();
    this.sunset = weather.getSunset();
  }

  //
  // ---
  //

  public final String getDescriptionText() {
    if (description == null) {
      return missingValueText;
    }
    return description;
  }

  public final String getTemperatureText() {
    if (temperature == null) {
      return missingValueText;
    }
    return temperature + unitSeparatorText + service.getText("jatoo.weather.temperature.unit." + temperatureUnit.name());
  }

  public final String getTemperatureTextFull() {
    return service.getText("jatoo.weather.temperature.text") + valueSeparatorText + getTemperatureText();
  }

  public final String getHumidityText() {
    if (humidity == null) {
      return missingValueText;
    }
    return humidity + unitSeparatorText + service.getText("jatoo.weather.humidity.unit." + humidityUnit.name());
  }

  public final String getHumidityTextFull() {
    return service.getText("jatoo.weather.humidity.text") + valueSeparatorText + getHumidityText();
  }

  public final String getPressureText() {
    if (pressure == null) {
      return missingValueText;
    }
    return pressure + unitSeparatorText + service.getText("jatoo.weather.pressure.unit." + pressureUnit.name());
  }

  public final String getPressureTextFull() {
    return service.getText("jatoo.weather.pressure.text") + valueSeparatorText + getPressureText();
  }

  public final String getWindText() {
    if (wind == null) {
      return missingValueText;
    }
    return wind + unitSeparatorText + service.getText("jatoo.weather.wind.unit." + windUnit.name());
  }

  public final String getWindTextFull() {
    return service.getText("jatoo.weather.wind.text") + valueSeparatorText + getWindText();
  }

  public final String getWindDirectionText() {
    if (windDirection == null) {
      return missingValueText;
    }
    return windDirection + unitSeparatorText + service.getText("jatoo.weather.windDirection.unit." + windDirectionUnit.name());
  }

  public final String getWindDirectionTextFull() {
    return service.getText("jatoo.weather.windDirection.text") + valueSeparatorText + getWindDirectionText();
  }

  public final String getCloudsText() {
    if (clouds == null) {
      return missingValueText;
    }
    return clouds + unitSeparatorText + service.getText("jatoo.weather.clouds.unit." + cloudsUnit.name());
  }

  public final String getCloudsTextFull() {
    return service.getText("jatoo.weather.clouds.text") + valueSeparatorText + getCloudsText();
  }

  public final String getRainText() {
    if (rain == null) {
      return missingValueText;
    }
    return String.valueOf(rain);
  }

  public final String getRainTextFull() {
    return service.getText("jatoo.weather.rain.text") + valueSeparatorText + getRainText();
  }

  public final String getSnowText() {
    if (snow == null) {
      return missingValueText;
    }
    return String.valueOf(snow);
  }

  public final String getSnowTextFull() {
    return service.getText("jatoo.weather.snow.text") + valueSeparatorText + getSnowText();
  }

  public final String getSunriseText() {
    if (sunrise == null) {
      return missingValueText;
    }
    return new SimpleDateFormat(service.getText("jatoo.weather.sunrise.pattern")).format(new Date(sunrise));
  }

  public final String getSunriseTextFull() {
    return service.getText("jatoo.weather.sunrise.text") + valueSeparatorText + getSunriseText();
  }

  public final String getSunsetText() {
    if (sunset == null) {
      return missingValueText;
    }
    return new SimpleDateFormat(service.getText("jatoo.weather.sunset.pattern")).format(new Date(sunset));
  }

  public final String getSunsetTextFull() {
    return service.getText("jatoo.weather.sunset.text") + valueSeparatorText + getSunsetText();
  }

  //
  // ---
  //

  public long getTimestamp() {
    return this.timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Number getTemperature() {
    return this.temperature;
  }

  public void setTemperature(Number temperature) {
    this.temperature = temperature;
  }

  public TEMPERATURE_UNIT getTemperatureUnit() {
    return this.temperatureUnit;
  }

  public void setTemperatureUnit(TEMPERATURE_UNIT temperatureUnit) {
    this.temperatureUnit = temperatureUnit;
  }

  public Number getHumidity() {
    return this.humidity;
  }

  public void setHumidity(Number humidity) {
    this.humidity = humidity;
  }

  public HUMIDITY_UNIT getHumidityUnit() {
    return this.humidityUnit;
  }

  public void setHumidityUnit(HUMIDITY_UNIT humidityUnit) {
    this.humidityUnit = humidityUnit;
  }

  public Number getPressure() {
    return this.pressure;
  }

  public void setPressure(Number pressure) {
    this.pressure = pressure;
  }

  public PRESSURE_UNIT getPressureUnit() {
    return this.pressureUnit;
  }

  public void setPressureUnit(PRESSURE_UNIT pressureUnit) {
    this.pressureUnit = pressureUnit;
  }

  public Number getWind() {
    return this.wind;
  }

  public void setWind(Number wind) {
    this.wind = wind;
  }

  public WIND_UNIT getWindUnit() {
    return this.windUnit;
  }

  public void setWindUnit(WIND_UNIT windUnit) {
    this.windUnit = windUnit;
  }

  public Number getWindDirection() {
    return this.windDirection;
  }

  public void setWindDirection(Number windDirection) {
    this.windDirection = windDirection;
  }

  public WIND_DIRECTION_UNIT getWindDirectionUnit() {
    return this.windDirectionUnit;
  }

  public void setWindDirectionUnit(WIND_DIRECTION_UNIT windDirectionUnit) {
    this.windDirectionUnit = windDirectionUnit;
  }

  public Number getClouds() {
    return this.clouds;
  }

  public void setClouds(Number clouds) {
    this.clouds = clouds;
  }

  public CLOUDS_UNIT getCloudsUnit() {
    return this.cloudsUnit;
  }

  public void setCloudsUnit(CLOUDS_UNIT cloudsUnit) {
    this.cloudsUnit = cloudsUnit;
  }

  public Number getRain() {
    return this.rain;
  }

  public void setRain(Number rain) {
    this.rain = rain;
  }

  public RAIN_UNIT getRainUnit() {
    return this.rainUnit;
  }

  public void setRainUnit(RAIN_UNIT rainUnit) {
    this.rainUnit = rainUnit;
  }

  public Number getSnow() {
    return this.snow;
  }

  public void setSnow(Number snow) {
    this.snow = snow;
  }

  public SNOW_UNIT getSnowUnit() {
    return this.snowUnit;
  }

  public void setSnowUnit(SNOW_UNIT snowUnit) {
    this.snowUnit = snowUnit;
  }

  public Long getSunrise() {
    return this.sunrise;
  }

  public void setSunrise(Long sunrise) {
    this.sunrise = sunrise;
  }

  public Long getSunset() {
    return this.sunset;
  }

  public void setSunset(Long sunset) {
    this.sunset = sunset;
  }

}
