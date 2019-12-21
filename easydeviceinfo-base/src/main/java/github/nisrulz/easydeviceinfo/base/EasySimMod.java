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
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresPermission;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/**
 * EasySim Mod Class
 */
public class EasySimMod {

    private final Context context;

    private final TelephonyManager tm;

    /**
     * Instantiates a new Easy  sim mod.
     *
     * @param context the context
     */
    public EasySimMod(Context context) {
        this.context = context;
        this.tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * Gets active multi sim info.
     * <p>
     * You need to declare the below permission in the manifest file to use this properly
     * <p>
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @return the active multi sim info
     */
    @RequiresPermission(permission.READ_PHONE_STATE)
    public final List<SubscriptionInfo> getActiveMultiSimInfo() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && PermissionUtil.hasPermission(
                context, Manifest.permission.READ_PHONE_STATE)) {
            List<SubscriptionInfo> tempActiveSub =
                    SubscriptionManager.from(context).getActiveSubscriptionInfoList();
            if (tempActiveSub == null || tempActiveSub.isEmpty()) {
                return new ArrayList<>(0);
            } else {
                return tempActiveSub;
            }
        } else {
            if (EasyDeviceInfo.debuggable) {
                Log.w(EasyDeviceInfo.nameOfLib,
                        "Device is running on android version that does not support multi sim functionality!");
            }
        }
        return new ArrayList<>(0);
    }

    /**
     * Gets carrier.
     *
     * @return the carrier
     */
    public final String getCarrier() {
        String result = null;
        if ((tm != null) && (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA)) {
            result = this.tm.getNetworkOperatorName().toLowerCase(Locale.getDefault());
        }
        return CheckValidityUtil.checkValidData(
                CheckValidityUtil.handleIllegalCharacterInResult(result));
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public final String getCountry() {
        final String result;
        if ((tm != null) && (tm.getSimState() == TelephonyManager.SIM_STATE_READY)) {
            result = this.tm.getSimCountryIso().toLowerCase(Locale.getDefault());
        } else {
            final Locale locale = Locale.getDefault();
            result = locale.getCountry().toLowerCase(locale);
        }
        return CheckValidityUtil.checkValidData(
                CheckValidityUtil.handleIllegalCharacterInResult(result));
    }

    /**
     * Gets imsi.
     * <p>
     * You need to declare the below permission in the manifest file to use this properly
     * <p>
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @return the imsi
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(permission.READ_PHONE_STATE)
    public final String getIMSI() {
        String result = null;
        if ((tm != null) && PermissionUtil.hasPermission(this.context, permission.READ_PHONE_STATE)) {
            result = this.tm.getSubscriberId();
        }

        return CheckValidityUtil.checkValidData(result);
    }

    /**
     * Gets number of active sim.
     * <p>
     * You need to declare the below permission in the manifest file to use this properly
     * <p>
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @return the number of active sim
     */
    @RequiresPermission(permission.READ_PHONE_STATE)
    public final int getNumberOfActiveSim() {
        return this.getActiveMultiSimInfo().size();
    }

    /**
     * Gets SIM serial number.
     * <p>
     * You need to declare the below permission in the manifest file to use this properly
     * <p>
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @return the sim serial
     */
    @SuppressLint("HardwareIds")
    @RequiresPermission(permission.READ_PHONE_STATE)
    public final String getSIMSerial() {
        String result = null;
        if ((tm != null) && PermissionUtil.hasPermission(this.context, permission.READ_PHONE_STATE)) {
            result = this.tm.getSimSerialNumber();
        }
        return CheckValidityUtil.checkValidData(result);
    }

    /**
     * Is multi sim.
     * <p>
     * You need to declare the below permission in the manifest file to use this properly
     * <p>
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     *
     * @return the boolean
     */
    @RequiresPermission(permission.READ_PHONE_STATE)
    public final boolean isMultiSim() {
        return getActiveMultiSimInfo().size() > 1;
    }

    /**
     * Is sim network locked.
     *
     * @return the boolean
     */
    public final boolean isSimNetworkLocked() {
        return (tm != null) && (tm.getSimState() == TelephonyManager.SIM_STATE_NETWORK_LOCKED);
    }
}
