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

public class JaTooWeatherTests {

  @Test
  public void test1() throws Throwable {

    JaTooWeatherService service = new JaTooWeatherService() {
      protected JaTooWeather getWeatherImpl(String city) {
        return getJaTooWeather();
      }
    };

    JaTooWeather weather = service.getWeather("683506");

    Assert.assertEquals("broken clouds", service.getDescription(weather));
    Assert.assertEquals("Temperature: 15.07 °C", service.getTemperatureWithText(weather));
    Assert.assertEquals("Humidity: 79 %", service.getHumidityWithText(weather));
    Assert.assertEquals("Pressure: 1026.69 hPa", service.getPressureWithText(weather));
    Assert.assertEquals("Wind: 2.21 meters/sec", service.getWindWithText(weather));
    Assert.assertEquals("Wind Direction: 57.5015 degrees (meteorological)", service.getWindDirectionWithText(weather));
    Assert.assertEquals("Clouds: 56 %", service.getCloudsWithText(weather));
    Assert.assertEquals("Rain: -", service.getRainWithText(weather));
    Assert.assertEquals("Snow: -", service.getSnowWithText(weather));
    Assert.assertEquals("Sunrise: 07:25", service.getSunriseWithText(weather));
    Assert.assertEquals("Sunset: 18:38", service.getSunsetWithText(weather));
  }

  @Test
  public void test2() throws Throwable {

    JaTooWeatherService service = new JaTooWeatherService("ro") {
      protected JaTooWeather getWeatherImpl(String city) {
        return getJaTooWeather();
      }
    };

    JaTooWeather weather = service.getWeather("683506");

    Assert.assertEquals("broken clouds", service.getDescription(weather));
    Assert.assertEquals("Temperatura: 15.07 °C", service.getTemperatureWithText(weather));
    Assert.assertEquals("Umiditate: 79 %", service.getHumidityWithText(weather));
    Assert.assertEquals("Presiune atmosferica: 1026.69 hPa", service.getPressureWithText(weather));
    Assert.assertEquals("Vant: 2.21 metri/sec", service.getWindWithText(weather));
    Assert.assertEquals("Directia vantului: 57.5015 grade (meteorologice)", service.getWindDirectionWithText(weather));
    Assert.assertEquals("Nori: 56 %", service.getCloudsWithText(weather));
    Assert.assertEquals("Ploaie: -", service.getRainWithText(weather));
    Assert.assertEquals("Zapada: -", service.getSnowWithText(weather));
    Assert.assertEquals("Rasarit: 07:25", service.getSunriseWithText(weather));
    Assert.assertEquals("Apus: 18:38", service.getSunsetWithText(weather));
  }

  private JaTooWeather getJaTooWeather() {

    JaTooWeather weather = new JaTooWeather();

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
