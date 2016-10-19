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

import org.junit.Assert;
import org.junit.Test;

public class JaTooWeatherTest {

  @Test
  public void test1() throws Throwable {

    JaTooWeatherService service = new JaTooWeatherService() {
      protected JaTooWeather getWeatherImpl(String city) {
        return getJaTooWeather(this);
      }
    };

    JaTooWeather weather = service.getWeather("683506", false);

    Assert.assertEquals("broken clouds", weather.getDescriptionText());
    Assert.assertEquals("15.07 °C", weather.getTemperatureText());
    Assert.assertEquals("Temperature: 15.07 °C", weather.getTemperatureTextFull());
    Assert.assertEquals("79 %", weather.getHumidityText());
    Assert.assertEquals("Humidity: 79 %", weather.getHumidityTextFull());
    Assert.assertEquals("1026.69 hPa", weather.getPressureText());
    Assert.assertEquals("Pressure: 1026.69 hPa", weather.getPressureTextFull());
    Assert.assertEquals("2.21 meters/sec", weather.getWindText());
    Assert.assertEquals("Wind: 2.21 meters/sec", weather.getWindTextFull());
    Assert.assertEquals("57.5015 degrees (meteorological)", weather.getWindDirectionText());
    Assert.assertEquals("Wind Direction: 57.5015 degrees (meteorological)", weather.getWindDirectionTextFull());
    Assert.assertEquals("56 %", weather.getCloudsText());
    Assert.assertEquals("Clouds: 56 %", weather.getCloudsTextFull());
    Assert.assertEquals("-", weather.getRainText());
    Assert.assertEquals("Rain: -", weather.getRainTextFull());
    Assert.assertEquals("-", weather.getSnowText());
    Assert.assertEquals("Snow: -", weather.getSnowTextFull());
    Assert.assertEquals("07:25", weather.getSunriseText());
    Assert.assertEquals("Sunrise: 07:25", weather.getSunriseTextFull());
    Assert.assertEquals("18:38", weather.getSunsetText());
    Assert.assertEquals("Sunset: 18:38", weather.getSunsetTextFull());
  }

  @Test
  public void test2() throws Throwable {

    JaTooWeatherService service = new JaTooWeatherService("ro") {
      protected JaTooWeather getWeatherImpl(String city) {
        return getJaTooWeather(this);
      }
    };

    JaTooWeather weather = service.getWeather("683506", false);

    Assert.assertEquals("broken clouds", weather.getDescriptionText());
    Assert.assertEquals("Temperatură: 15.07 °C", weather.getTemperatureTextFull());
    Assert.assertEquals("Umiditate: 79 %", weather.getHumidityTextFull());
    Assert.assertEquals("Presiune atmosferică: 1026.69 hPa", weather.getPressureTextFull());
    Assert.assertEquals("Vânt: 2.21 metri/sec", weather.getWindTextFull());
    Assert.assertEquals("Direcția vântului: 57.5015 grade (meteorologice)", weather.getWindDirectionTextFull());
    Assert.assertEquals("Nori: 56 %", weather.getCloudsTextFull());
    Assert.assertEquals("Ploaie: -", weather.getRainTextFull());
    Assert.assertEquals("Zăpada: -", weather.getSnowTextFull());
    Assert.assertEquals("Răsărit: 07:25", weather.getSunriseTextFull());
    Assert.assertEquals("Apus: 18:38", weather.getSunsetTextFull());
  }

  private JaTooWeather getJaTooWeather(JaTooWeatherService service) {

    JaTooWeather weather = new JaTooWeather(service);

    weather.description = "broken clouds";

    weather.temperature = 15.07;
    weather.temperatureUnit = JaTooWeather.TEMPERATURE_UNIT.CELSIUS;

    weather.humidity = 79;
    weather.humidityUnit = JaTooWeather.HUMIDITY_UNIT.PERCENT;

    weather.pressure = 1026.69;
    weather.pressureUnit = JaTooWeather.PRESSURE_UNIT.HPA;

    weather.wind = 2.21;
    weather.windUnit = JaTooWeather.WIND_UNIT.METER_PER_SEC;

    weather.windDirection = 57.5015;
    weather.windDirectionUnit = JaTooWeather.WIND_DIRECTION_UNIT.DEGREES_METEOROLOGICAL;

    weather.clouds = 56;
    weather.cloudsUnit = JaTooWeather.CLOUDS_UNIT.PERCENT;

    weather.sunrise = 1476073518000L;
    weather.sunset = 1476113917000L;

    return weather;
  }

}
