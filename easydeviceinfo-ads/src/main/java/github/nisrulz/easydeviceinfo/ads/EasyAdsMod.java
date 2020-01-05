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

import androidx.ads.identifier.AdvertisingIdClient;
import androidx.ads.identifier.AdvertisingIdInfo;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/**
 * The type Easy ads mod.
 */
public class EasyAdsMod {

    /**
     * The interface Ad identifier callback.
     */
    public interface AdIdentifierCallback {

        /**
         * On success.
         *
         * @param adIdentifier the ad identifier
         * @param adDonotTrack the ad donot track
         */
        void onSuccess(String adIdentifier, boolean adDonotTrack);
    }

    private Context context;
    private final Executor executor = Executors.newSingleThreadExecutor();

    /**
     * Instantiates a new Easy ads mod.
     *
     * @param context the context
     */
    public EasyAdsMod(final Context context) {
        this.context = context.getApplicationContext();
    }

    /**
     * Gets android ad id.
     *
     * @param callback the callback
     */
    public final void getAndroidAdId(final AdIdentifierCallback callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                retrieveAdId(callback);
            }
        });
    }

    private void retrieveAdId(AdIdentifierCallback callback) {
        if (AdvertisingIdClient.isAdvertisingIdProviderAvailable(context)) {
            ListenableFuture<AdvertisingIdInfo> advertisingIdInfoListenableFuture =
                    AdvertisingIdClient.getAdvertisingIdInfo(context);

            String androidAdId = EasyDeviceInfo.notFoundVal;
            boolean adDoNotTrack = false;

            try {
                AdvertisingIdInfo advertisingIdInfo = advertisingIdInfoListenableFuture.get();
                adDoNotTrack = advertisingIdInfo.isLimitAdTrackingEnabled();
                androidAdId = advertisingIdInfo.getId();
                if (androidAdId == null) {
                    androidAdId = EasyDeviceInfo.notFoundVal;
                }
                //Send Data to callback
                callback.onSuccess(androidAdId, adDoNotTrack);
            } catch (ExecutionException e) {
                Log.e(EasyDeviceInfo.nameOfLib, "The Advertising ID execution exception");
            } catch (InterruptedException e) {
                Log.e(EasyDeviceInfo.nameOfLib, "The Advertising ID access interrupted");
            }
        } else {
            Log.e(EasyDeviceInfo.nameOfLib, "The Advertising ID client library is unavailable. Use a different library to perform any required ads use cases.");
        }
    }

    public void clear() {
        this.context = null;
    }
}
