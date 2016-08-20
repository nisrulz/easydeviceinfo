package github.nisrulz.easydeviceinfo;

import android.content.Context;
import android.content.pm.PackageManager;

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
    return context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
  }
}
