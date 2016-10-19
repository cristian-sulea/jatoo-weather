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

import java.io.Serializable;

/**
 * The cached object representation of the weather.
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 1.0, October 19, 2016
 */
@SuppressWarnings("serial")
public class JaTooWeatherCacheObject implements Serializable {

  private final long timestamp;

  private final String city;
  private final String description;

  private final Number temperature;
  private final JaTooWeather.TEMPERATURE_UNIT temperatureUnit;

  private final Number humidity;
  private final JaTooWeather.HUMIDITY_UNIT humidityUnit;

  private final Number pressure;
  private final JaTooWeather.PRESSURE_UNIT pressureUnit;

  private final Number wind;
  private final JaTooWeather.WIND_UNIT windUnit;

  private final Number windDirection;
  private final JaTooWeather.WIND_DIRECTION_UNIT windDirectionUnit;

  private final Number clouds;
  private final JaTooWeather.CLOUDS_UNIT cloudsUnit;

  private final Number rain;
  private final JaTooWeather.RAIN_UNIT rainUnit;

  private final Number snow;
  private final JaTooWeather.SNOW_UNIT snowUnit;

  private final Long sunrise;
  private final Long sunset;

  public JaTooWeatherCacheObject(final JaTooWeather weather) {

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

  public long getTimestamp() {
    return this.timestamp;
  }

  public String getCity() {
    return this.city;
  }

  public String getDescription() {
    return this.description;
  }

  public Number getTemperature() {
    return this.temperature;
  }

  public JaTooWeather.TEMPERATURE_UNIT getTemperatureUnit() {
    return this.temperatureUnit;
  }

  public Number getHumidity() {
    return this.humidity;
  }

  public JaTooWeather.HUMIDITY_UNIT getHumidityUnit() {
    return this.humidityUnit;
  }

  public Number getPressure() {
    return this.pressure;
  }

  public JaTooWeather.PRESSURE_UNIT getPressureUnit() {
    return this.pressureUnit;
  }

  public Number getWind() {
    return this.wind;
  }

  public JaTooWeather.WIND_UNIT getWindUnit() {
    return this.windUnit;
  }

  public Number getWindDirection() {
    return this.windDirection;
  }

  public JaTooWeather.WIND_DIRECTION_UNIT getWindDirectionUnit() {
    return this.windDirectionUnit;
  }

  public Number getClouds() {
    return this.clouds;
  }

  public JaTooWeather.CLOUDS_UNIT getCloudsUnit() {
    return this.cloudsUnit;
  }

  public Number getRain() {
    return this.rain;
  }

  public JaTooWeather.RAIN_UNIT getRainUnit() {
    return this.rainUnit;
  }

  public Number getSnow() {
    return this.snow;
  }

  public JaTooWeather.SNOW_UNIT getSnowUnit() {
    return this.snowUnit;
  }

  public Long getSunrise() {
    return this.sunrise;
  }

  public Long getSunset() {
    return this.sunset;
  }

}
