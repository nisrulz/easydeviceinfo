package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.List;

public class EasySensorMod {

  private final SensorManager sensorManager;
  private Sensor sensor;

  public EasySensorMod(final Context context) {
    sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
  }

  public List<Sensor> getAllSensors() {
    return sensorManager.getSensorList(Sensor.TYPE_ALL);
  }
}
