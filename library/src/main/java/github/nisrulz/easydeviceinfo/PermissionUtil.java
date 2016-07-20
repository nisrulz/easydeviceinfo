package github.nisrulz.easydeviceinfo;

import android.content.Context;
import android.content.pm.PackageManager;

final class PermissionUtil {
  private PermissionUtil() {
  }

  static final boolean hasPermission(Context context, String permission) {
    return context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
  }
}
