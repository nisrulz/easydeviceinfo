package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.List;

/**
 * The type Easy sensor mod.
 */
public class EasySensorMod {

  private final SensorManager sensorManager;

  /**
   * Instantiates a new Easy sensor mod.
   *
   * @param context
   *     the context
   */
  public EasySensorMod(final Context context) {
    sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
  }

  /**
   * Gets all sensors.
   *
   * @return the all sensors
   */
  public List<Sensor> getAllSensors() {
    return sensorManager.getSensorList(Sensor.TYPE_ALL);
  }
}
