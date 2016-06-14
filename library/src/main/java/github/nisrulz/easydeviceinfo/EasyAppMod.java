package github.nisrulz.easydeviceinfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * The type Easy di app mod.
 */
public class EasyAppMod {

  private final Context context;

  /**
   * Instantiates a new Easy di app mod.
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
  public String getActivityName() {
    return CheckValidityUtil.checkValidData(context.getClass().getSimpleName());
  }

  /**
   * Gets package name.
   *
   * @return the package name
   */
  public String getPackageName() {
    return CheckValidityUtil.checkValidData(context.getPackageName());
  }

  /**
   * Gets store.
   *
   * @return the store
   */
  public String getStore() {
    String result = null;
    if (Build.VERSION.SDK_INT >= 3) {
      result = context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets app name.
   *
   * @return the app name
   */
  public String getAppName() {
    String result;
    final PackageManager pm = context.getPackageManager();
    ApplicationInfo ai = null;
    try {
      ai = pm.getApplicationInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    result = (ai != null ? (String) pm.getApplicationLabel(ai) : null);
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets app version.
   *
   * @return the app version
   */
  public String getAppVersion() {
    String result = null;
    try {
      result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets app version code.
   *
   * @return the app version code
   */
  public String getAppVersionCode() {
    String result = null;
    try {
      result = String.valueOf(
          context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return CheckValidityUtil.checkValidData(result);
  }
}
