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

package github.nisrulz.easydeviceinfo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * The type Easy di bluetooth mod.
 */
public class EasyBluetoothMod {
  private final Context context;

  /**
   * Instantiates a new Easy di bluetooth mod.
   *
   * @param context the context
   */
  public EasyBluetoothMod(Context context) {
    this.context = context;
  }

  /**
   * Gets bluetooth mac.
   *
   * @return the bluetooth mac
   */
  @SuppressWarnings("MissingPermission") public String getBluetoothMAC() {
    String result = null;
    if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH)
        == PackageManager.PERMISSION_GRANTED) {
      BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
      result = bta.getAddress();
    }
    return CheckValidityUtil.checkValidData(result);
  }
}
