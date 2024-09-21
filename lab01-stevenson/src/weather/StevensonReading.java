package weather;


/**
 * A class to represent a StevensonReading. It implements the WeatherReading interface.
 * It provides methods to get the temperature, dew point, wind speed,
 * total rain, relative humidity, heat index, and wind chill.
 */
public class StevensonReading implements WeatherReading {

  private final double temperature;
  private final double dewPoint;
  private final double windSpeed;
  private final double rain;

  /**
   * Constructs a new StevensonReading with the given air temperature, dew point,
   * wind speed, and total rain.
   *
   * @param temperature the air temperature
   * @param dewPoint the dew point
   * @param windSpeed the wind speed
   * @param rain the total rain
   */

  public StevensonReading(double temperature, double dewPoint, double windSpeed, double rain) {
    if (dewPoint > temperature) {
      throw new IllegalArgumentException();
    }
    if (windSpeed < 0) {
      throw new IllegalArgumentException();
    }
    if (rain < 0) {
      throw new IllegalArgumentException();
    }

    this.temperature = temperature;
    this.dewPoint = dewPoint;
    this.windSpeed = windSpeed;
    this.rain = rain;
  }

  @Override
  public int getTemperature() {
    return (int) Math.round(temperature);
  }

  @Override
  public int getDewPoint() {
    return (int) Math.round(dewPoint);
  }

  @Override
  public int getWindSpeed() {
    return (int) Math.round(windSpeed);
  }

  @Override
  public int getTotalRain() {
    return (int) Math.round(rain);
  }

  @Override
  public int getRelativeHumidity() {
    double saturatedVaporPressure = 6.11 * Math.pow(10, (7.5 * temperature)
            / (237.3 + temperature));
    double vaporPressure = 6.11 * Math.pow(10, (7.5 * dewPoint) / (237.3 + dewPoint));
    double relativeHumidity = (vaporPressure / saturatedVaporPressure) * 100;

    if (relativeHumidity < 0 || relativeHumidity > 100) {
      throw new IllegalArgumentException();
    }
    return (int) Math.round(relativeHumidity);
  }


  /**
   * This method calculates the relative humidity. Returns in double.
   *
   * @return the relative humidity. Returns in double.
   */
  public double getRelativeHumidityDouble() {
    double saturatedVaporPressure = 6.11 * Math.pow(10, (7.5 * temperature)
            / (237.3 + temperature));
    double vaporPressure = 6.11 * Math.pow(10, (7.5 * dewPoint) / (237.3 + dewPoint));
    double relativeHumidity = (vaporPressure / saturatedVaporPressure) * 100;

    if (relativeHumidity < 0 || relativeHumidity > 100) {
      throw new IllegalArgumentException();
    }
    return relativeHumidity;
  }

  public double getTempInFahrenheit() {
    return (temperature * 9 / 5) + 32;
  }

  @Override
  public int getHeatIndex() {
    final double c1 = -8.78469475556;
    final double c2 = 1.61139411;
    final double c3 = 2.33854883889;
    final double c4 = -0.14611605;
    final double c5 = -0.012308094;
    final double c6 = -0.0164248277778;
    final double c7 = 0.002211732;
    final double c8 = 0.00072546;
    final double c9 = -0.000003582;

    double heatIndex;
    heatIndex = c1 + c2 * temperature
            + c3 * getRelativeHumidityDouble()
            + c4 * temperature * getRelativeHumidityDouble()
            + c5 * Math.pow(temperature, 2)
            + c6 * Math.pow(getRelativeHumidityDouble(), 2)
            + c7 * Math.pow(temperature, 2) * getRelativeHumidityDouble()
            + c8 * temperature * Math.pow(getRelativeHumidityDouble(), 2)
            + c9 * Math.pow(temperature, 2) * Math.pow(getRelativeHumidityDouble(), 2);
    return (int) Math.round(heatIndex);
  }


  /**
   * This method calculates the wind chill index. Returns in Celsius.
   *
   * @return the wind chill index. Returns in Celsius.
   */
  @Override
  public int getWindChill() {
    double windChill = 35.74 + 0.6215 * getTempInFahrenheit()
            - 35.75 * Math.pow(windSpeed, 0.16)
            + 0.4275 * getTempInFahrenheit() * Math.pow(windSpeed, 0.16);
    double windChillC = (windChill - 32) * 5 / 9;
    return (int) Math.round(windChillC);
  }

  @Override
  public String toString() {
    return String.format("Reading: T = %d, D = %d, v = %d, rain = %d",
            getTemperature(), getDewPoint(), getWindSpeed(), getTotalRain());
  }

  @Override
  public int hashCode() {
    return (int) (temperature + dewPoint + windSpeed + rain);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    StevensonReading other = (StevensonReading) obj;
    return this.temperature == other.temperature
            && this.dewPoint == other.dewPoint
            && this.windSpeed == other.windSpeed
            && this.rain == other.rain;
  }
}
