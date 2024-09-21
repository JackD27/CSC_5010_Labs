package weather;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * A class to test the StevensonReading class.
 */

public class StevensonReadingTest {

  StevensonReading stevensonReading;

  @Before
  public void setUp() {
    stevensonReading = new StevensonReading(10, 5, 5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDewPointReading() {
    new StevensonReading(5, 10, 1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWindSpeedReading() {
    new StevensonReading(5, 3, -1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTotalRainReading() {
    new StevensonReading(5, 3, 1, -1);
  }

  @Test
  public void testSimpleGetters() {
    assertEquals(10, stevensonReading.getTemperature());
    assertEquals(5, stevensonReading.getDewPoint());
    assertEquals(5, stevensonReading.getWindSpeed());
    assertEquals(0, stevensonReading.getTotalRain());
  }

  @Test
  public void testGetTempInFahrenheit() {
    assertEquals(50, stevensonReading.getTempInFahrenheit(), 0.01);
  }

  @Test
  public void testGetRelativeHumidity() {
    assertEquals(71, stevensonReading.getRelativeHumidity());
    assertEquals(85, new StevensonReading(91.310, 87.060, 47.359, 84).getRelativeHumidity());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRelativeHumidityException() {
    StevensonReading stevensonReading2 = new StevensonReading(90, -250, 5, 0);
    stevensonReading2.getRelativeHumidity();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetRelativeHumidityException2() {
    StevensonReading stevensonReadingDouble = new StevensonReading(90, 250, 5, 0);
    stevensonReadingDouble.getRelativeHumidityDouble();
  }


  @Test
  public void testGetHeatIndex() {
    assertEquals(426, new StevensonReading(74.902741, 68.083233, 43.025681, 322).getHeatIndex());
    assertEquals(306, new StevensonReading(62.555305, 58.835772, 34.710117, 75).getHeatIndex());
  }

  @Test
  public void testGetWindChill() {
    assertEquals(4, new StevensonReading(2.779767, -12.015555, 1.163566, 62).getWindChill());
    assertEquals(5, new StevensonReading(10.040682, -2.124990, 39.051060, 74).getWindChill());
    assertEquals(0, new StevensonReading(6.489452, -11.963366, 47.943579, 37).getWindChill());
  }

  @Test
  public void testToString() {
    assertEquals("Reading: T = 10, D = 5, v = 5, rain = 0", stevensonReading.toString());
  }

  @Test
  public void testHashCode() {
    // This is how the hash code is calculated in the class.
    int sum = stevensonReading.getTemperature()
            + stevensonReading.getDewPoint()
            + stevensonReading.getWindSpeed()
            + stevensonReading.getTotalRain();

    assertEquals(sum, stevensonReading.hashCode());
  }

  @Test
  public void testEquals() {
    StevensonReading stevensonReading2 = new StevensonReading(10, 5, 5, 0);
    assertTrue("Same Values", stevensonReading.equals(stevensonReading2)); // Same Values
    assertTrue(stevensonReading.equals(stevensonReading));
    assertFalse(stevensonReading.equals(null));
    String s = "hello";
    assertFalse(stevensonReading.equals(s));
    StevensonReading stevensonReading3 = new StevensonReading(8, 3, 4, 1);
    assertFalse("Different values", stevensonReading.equals(stevensonReading3)); // Different Values

  }
}