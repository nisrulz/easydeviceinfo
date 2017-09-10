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

package github.nisrulz.easydeviceinfo.ads;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import java.io.IOException;

/**
 * The type Easy ads mod.
 */
public class EasyAdsMod {

  private final Context context;

  /**
   * Instantiates a new Easy ads mod.
   *
   * @param context
   *     the context
   */
  public EasyAdsMod(Context context) {
    this.context = context;
  }

  /**
   * Gets android ad id.
   *
   * @param callback
   *     the callback
   */
  public final void getAndroidAdId(final AdIdentifierCallback callback) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        retrieveAdId(callback);
      }
    }).start();
  }

  private void retrieveAdId(final AdIdentifierCallback callback) {
    AdvertisingIdClient.Info adInfo;
    try {
      adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
      String androidAdId = EasyDeviceInfo.notFoundVal;
      boolean adDoNotTrack = false;
      if (adInfo != null) {
        androidAdId = adInfo.getId();
        adDoNotTrack = adInfo.isLimitAdTrackingEnabled();
        if (androidAdId == null) {
          androidAdId = EasyDeviceInfo.notFoundVal;
        }
      }

      //Send Data to callback
      callback.onSuccess(androidAdId, adDoNotTrack);
    } catch (IOException | GooglePlayServicesNotAvailableException e) {
      // Unrecoverable error connecting to Google Play services (e.g.,
      // the old version of the service doesn't support getting AdvertisingId).
      Log.d(EasyDeviceInfo.nameOfLib, "Google Play Services Not Available Exception", e);
    } catch (GooglePlayServicesRepairableException e) {
      Log.d(EasyDeviceInfo.nameOfLib, "Google Play Services Repairable Exception", e);
    }
  }

  /**
   * The interface Ad identifier callback.
   */
  public interface AdIdentifierCallback {
    /**
     * On success.
     *
     * @param adIdentifier
     *     the ad identifier
     * @param adDonotTrack
     *     the ad donot track
     */
    void onSuccess(String adIdentifier, boolean adDonotTrack);
  }
}
