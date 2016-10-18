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
 * The object representation of the weather (used also to cache the weather).
 * 
 * @author <a href="http://cristian.sulea.net" rel="author">Cristian Sulea</a>
 * @version 1.1, October 11, 2016
 */
@SuppressWarnings("serial")
public class JaTooWeather implements Serializable {

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

}
