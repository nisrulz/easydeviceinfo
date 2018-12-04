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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/**
 * EasyApp Mod Class
 */
public class EasyAppMod {

    private static final String NAME_NOT_FOUND_EXCEPTION = "Name Not Found Exception";

    private final Context context;

    /**
     * Instantiates a new Easy app mod.
     *
     * @param context the context
     */
    public EasyAppMod(Context context) {
        this.context = context;
    }

    /**
     * Gets activity name.
     *
     * @return the activity name
     */
    public final String getActivityName() {
        return CheckValidityUtil.checkValidData(this.context.getClass().getSimpleName());
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public final String getAppName() {
        final String result;
        PackageManager pm = this.context.getPackageManager();
        ApplicationInfo ai = null;
        try {
            ai = pm.getApplicationInfo(this.context.getPackageName(), 0);
        } catch (final NameNotFoundException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.d(EasyDeviceInfo.nameOfLib, EasyAppMod.NAME_NOT_FOUND_EXCEPTION, e);
            }
        }
        result = (ai != null) ? (String) pm.getApplicationLabel(ai) : null;
        return CheckValidityUtil.checkValidData(result);
    }

    /**
     * Gets app version.
     *
     * @return the app version
     */
    public final String getAppVersion() {
        String result = null;
        try {
            result = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (final NameNotFoundException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.e(EasyDeviceInfo.nameOfLib, EasyAppMod.NAME_NOT_FOUND_EXCEPTION, e);
            }
        }
        return CheckValidityUtil.checkValidData(result);
    }

    /**
     * Gets app version code.
     *
     * @return the app version code
     */
    public final String getAppVersionCode() {
        String result = null;
        try {
            result = String.valueOf(
                    this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode);
        } catch (final NameNotFoundException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.e(EasyDeviceInfo.nameOfLib, EasyAppMod.NAME_NOT_FOUND_EXCEPTION, e);
            }
        }
        return CheckValidityUtil.checkValidData(result);
    }

    /**
     * Gets package name.
     *
     * @return the package name
     */
    public final String getPackageName() {
        return CheckValidityUtil.checkValidData(this.context.getPackageName());
    }

    /**
     * Gets store.
     *
     * @return the store
     */
    public final String getStore() {
        final String result = this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName());
        return CheckValidityUtil.checkValidData(result);
    }

    /**
     * Check if the app with the specified packagename is installed or not
     *
     * @param packageName the package name
     * @return the boolean
     */
    public final boolean isAppInstalled(final String packageName) {
        return context.getPackageManager().getLaunchIntentForPackage(packageName) != null;
    }

    /**
     * Is permission granted boolean.
     *
     * @param permission the permission
     * @return the boolean
     */
    public final boolean isPermissionGranted(String permission) {
        return context.checkCallingPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}
