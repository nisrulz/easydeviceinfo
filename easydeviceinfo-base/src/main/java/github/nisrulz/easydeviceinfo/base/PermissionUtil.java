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
import android.content.pm.PackageManager;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/**
 * Permission Util Class
 */
final class PermissionUtil {

    private PermissionUtil() {
        // private constructor for utility class
    }

    /**
     * Has permission method.
     *
     * @param context    the context
     * @param permission the permission
     * @return the boolean
     */
    static boolean hasPermission(final Context context, final String permission) {
        boolean permGranted =
                context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        if (EasyDeviceInfo.debuggable && !permGranted) {
            Log.e(EasyDeviceInfo.nameOfLib, ">\t" + permission);
            Log.w(EasyDeviceInfo.nameOfLib,
                    "\nPermission not granted/missing!\nMake sure you have declared the permission in your manifest file (and granted it on API 23+).\n");
        }
        return permGranted;
    }
}
