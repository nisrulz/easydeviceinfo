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
   * @param context
   *     the context
   * @param permission
   *     the permission
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
