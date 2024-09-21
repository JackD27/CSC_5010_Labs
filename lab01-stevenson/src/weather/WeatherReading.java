package weather;

/**
 * A WeatherReading represents the data that is collected at a variety of
 * weather stations. Classes that implement this interface will vary depending
 * upon the type of weather station that does the reading.
 */
public interface WeatherReading {

  // TODO: Look at the relative humidity calculation
  // - the formula has a lot of restrictions that we do not enforce
  // - IllegalArgumentException for results outside of 0-100% humidity

  /**
   * Get the temperature (in Celsius) of this reading rounded to the nearest
   * integer.
   *
   * @return the temperature
   */
  int getTemperature();

  /**
   * Get the dew point (in Celsius) for this reading rounded to the nearest
   * integer.
   *
   * @return the dew point
   */
  int getDewPoint();

  /**
   * Get the wind speed (in mph) for this reading rounded to the nearest
   * integer.
   *
   * @return the wind speed
   */
  int getWindSpeed();

  /**
   * Get the total rain (in mm) of this reading.
   *
   * @return the total rain
   */
  int getTotalRain();

  /**
   * Get the relative humidity (in percent) of this weather reading rounded to the
   * nearest integer.
   *
   * @return the relative humidity
   */
  int getRelativeHumidity();

  /**
   * Get the heat index (in Celsius) for this weather reading rounded to the nearest integer.
   *
   * @return the heat index
   */
  int getHeatIndex();

  /**
   * Get the wind chill (in Celsius) rounded to the nearest integer.
   *
   * @return the wind chill
   */
  int getWindChill();

}
