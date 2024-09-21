package transmission;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the AutomaticTransmission class.
 */

public class AutomaticTransmissionTest {

  private AutomaticTransmission transmission;

  @Before
  public void setUp() {
    transmission = new AutomaticTransmission(10, 20, 30, 40, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFirstThreshold() {
    new AutomaticTransmission(-1, 10, 1, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSecondThreshold() {
    new AutomaticTransmission(10, -1, 1, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testThirdThreshold() {
    new AutomaticTransmission(10, 15, -1, 1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFourthThreshold() {
    new AutomaticTransmission(10, 15, 20, -1, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFifthThreshold() {
    new AutomaticTransmission(10, 15, 20, 25, -1);
  }

  @Test
  public void testIncreaseSpeed() {
    AutomaticTransmission transmission2 = new AutomaticTransmission(2, 4, 6, 8, 10);
    transmission2.increaseSpeed();
    assertEquals(1, transmission2.getSpeed());
    assertEquals(1, transmission2.getGear());
    transmission2.increaseSpeed();
    assertEquals(2, transmission2.getSpeed());
    assertEquals(2, transmission2.getGear());
    transmission2.increaseSpeed();
    assertEquals(3, transmission2.getSpeed());
    assertEquals(2, transmission2.getGear());
    transmission2.increaseSpeed();
    assertEquals(4, transmission2.getSpeed());
    assertEquals(3, transmission2.getGear());
    transmission2.increaseSpeed(60);
    assertEquals(64, transmission2.getSpeed());
    assertEquals(6, transmission2.getGear());


  }

  @Test
  public void testDecreaseSpeed() {
    AutomaticTransmission transmission2 = new AutomaticTransmission(2, 4, 6, 8, 10);
    transmission2.increaseSpeed();
    transmission2.increaseSpeed();
    transmission2.increaseSpeed();
    transmission2.increaseSpeed();
    transmission2.decreaseSpeed();
    assertEquals(3, transmission2.getSpeed());
    assertEquals(2, transmission2.getGear());
    transmission2.decreaseSpeed();
    assertEquals(2, transmission2.getSpeed());
    assertEquals(2, transmission2.getGear());
    transmission2.decreaseSpeed();
    assertEquals(1, transmission2.getSpeed());
    assertEquals(1, transmission2.getGear());
  }

  @Test
  public void testSimpleGetters() {
    transmission.increaseSpeed();
    assertEquals(1, transmission.getSpeed());
    assertEquals(1, transmission.getGear());
  }

  @Test
  public void testToString() {
    assertEquals("Transmission (speed = 0, gear = 0)", transmission.toString());
    transmission.increaseSpeed();
    assertEquals("Transmission (speed = 1, gear = 1)", transmission.toString());
  }
}