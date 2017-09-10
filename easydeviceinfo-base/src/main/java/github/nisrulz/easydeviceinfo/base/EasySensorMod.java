/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
