package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/**
 * The type Permission util.
 */
final class PermissionUtil {
  private PermissionUtil() {
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
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(">\t").append(permission);

      Log.e(EasyDeviceInfo.nameOfLib, stringBuilder.toString());
      Log.w(EasyDeviceInfo.nameOfLib,
          "\nPermission not granted/missing!\nMake sure you have declared the permission in your manifest file.\n");
    }
    return permGranted;
  }
}
