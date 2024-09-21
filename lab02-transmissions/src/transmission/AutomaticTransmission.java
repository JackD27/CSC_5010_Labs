package transmission;


/**
 * Represents an automatic transmission in a car. An automatic transmission has five gears and
 * shifts gears based on the speed of the car.
 */
public class AutomaticTransmission implements Transmission {

  private static final int MAX_GEAR = 6;
  private final int firThreshold;
  private final int secThreshold;
  private final int thirdThreshold;
  private final int fourThreshold;
  private final int fifthThreshold;
  private int speed;
  private int gear;

  /**
   * Constructs a new AutomaticTransmission with the given thresholds for each gear.
   *
   * @param firThreshold  the speed at which the transmission shifts from gear 1 to gear 2
   * @param secThreshold the speed at which the transmission shifts from gear 2 to gear 3
   * @param thirdThreshold  the speed at which the transmission shifts from gear 3 to gear 4
   * @param fourThreshold the speed at which the transmission shifts from gear 4 to gear 5
   * @param fifthThreshold  the speed at which the transmission shifts from gear 5 to gear 6
   * @throws IllegalArgumentException if any of the thresholds are negative or if the thresholds are
   *                                  not in increasing order
   */
  public AutomaticTransmission(int firThreshold,
                               int secThreshold,
                               int thirdThreshold,
                               int fourThreshold,
                               int fifthThreshold) {

    if (firThreshold == 0 || secThreshold == 0 || thirdThreshold == 0
            || fourThreshold == 0 || fifthThreshold == 0) {
      throw new IllegalArgumentException("Thresholds cannot be zero");
    }

    if (firThreshold < 0 || secThreshold < 0 || thirdThreshold < 0
            || fourThreshold < 0 || fifthThreshold < 0) {
      throw new IllegalArgumentException("Thresholds must be positive");
    }

    if (firThreshold >= secThreshold || secThreshold >= thirdThreshold
            || thirdThreshold >= fourThreshold
            || fourThreshold >= fifthThreshold) {
      throw new IllegalArgumentException("Thresholds must be in increasing order");
    }

    this.firThreshold = firThreshold;
    this.secThreshold = secThreshold;
    this.thirdThreshold = thirdThreshold;
    this.fourThreshold = fourThreshold;
    this.fifthThreshold = fifthThreshold;
    speed = 0;

  }

  @Override
  public void increaseSpeed() {
    speed++;
    changeGear();
  }

  /**
   * Increases the speed of this Transmission by the given number of times.
   *
   * @param times the number of times to increase the speed
   */
  public void increaseSpeed(int times) {
    for (int i = 0; i < times; i++) {
      increaseSpeed();
    }
  }

  @Override
  public void decreaseSpeed() throws IllegalStateException {
    if (speed == 0 || speed < 0) {
      throw new IllegalStateException("Speed cannot be negative");
    }
    speed--;
    changeGear();
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public int getGear() {
    return gear;
  }

  /**
   * Changes the gear of the transmission based on the speed of the car.
   */

  public void changeGear() {

    int[] thresholds = {firThreshold, secThreshold, thirdThreshold, fourThreshold, fifthThreshold};

    if (speed == 0) {
      gear = 0;
      return;
    }
    if (speed > 0 && speed < thresholds[0]) {
      gear = 1;
      return;
    }
    if (gear < MAX_GEAR && speed >= thresholds[gear - 1]) {
      gear++;
    }
    if (speed < thresholds[gear - 2]) {
      gear--;
    }
  }

  @Override
  public String toString() {
    return String.format("Transmission (speed = %d, gear = %d)", getSpeed(), getGear());
  }
}
