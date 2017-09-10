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

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.support.annotation.RequiresPermission;

/**
 * The type Easy fingerprint mod.
 */
public class EasyFingerprintMod {

  private FingerprintManager fingerprintManager = null;

  /**
   * Instantiates a new Easy fingerprint mod.
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.USE_FINGERPRINT" />
   *
   * @param context
   *     the context
   */
  @TargetApi(23)
  @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
  public EasyFingerprintMod(final Context context) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
      fingerprintManager =
          (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
    }
  }

  /**
   * Is fingerprint sensor present boolean.
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.USE_FINGERPRINT" />
   *
   * @return the boolean
   */
  @SuppressLint("NewApi")
  @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
  public final boolean isFingerprintSensorPresent() {
    return fingerprintManager != null && fingerprintManager.isHardwareDetected();
  }

  /**
   * Are fingerprints enrolled boolean.
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.USE_FINGERPRINT" />
   *
   * @return the boolean
   */
  @SuppressLint("NewApi")
  @RequiresPermission(Manifest.permission.USE_FINGERPRINT)
  public final boolean areFingerprintsEnrolled() {
    return fingerprintManager != null && fingerprintManager.hasEnrolledFingerprints();
  }
}
