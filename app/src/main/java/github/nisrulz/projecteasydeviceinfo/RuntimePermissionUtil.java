/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package github.nisrulz.projecteasydeviceinfo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class RuntimePermissionUtil {

  private RuntimePermissionUtil() {

  }

  public static void onRequestPermissionsResult(int[] grantResults,
      RPResultListener RPResultListener) {
    if (grantResults.length > 0) {
      for (int i = 0; i < grantResults.length; i++) {
        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
          RPResultListener.onPermissionGranted();
        } else {
          RPResultListener.onPermissionDenied();
        }
      }
    }
  }

  public static void requestPermission(final Activity activity, final String[] permissions,
      final int REQUEST_CODE) {
    // No explanation needed, we can request the permission.
    ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE);
  }

  public static void requestPermission(final Activity activity, final String permission,
      final int REQUEST_CODE) {
    // No explanation needed, we can request the permission.
    ActivityCompat.requestPermissions(activity, new String[] { permission }, REQUEST_CODE);
  }

  public static boolean checkPermissonGranted(Context context, String permission) {
    return (ActivityCompat.checkSelfPermission(context, permission)
        == PackageManager.PERMISSION_GRANTED);
  }
}
