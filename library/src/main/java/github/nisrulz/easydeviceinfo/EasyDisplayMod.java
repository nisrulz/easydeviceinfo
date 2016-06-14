package github.nisrulz.easydeviceinfo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * The type Easy di display mod.
 */
public class EasyDisplayMod {
  private final Context context;

  /**
   * Instantiates a new Easy di display mod.
   *
   * @param context the context
   */
  public EasyDisplayMod(Context context) {
    this.context = context;
  }

  /**
   * Gets density.
   *
   * @return the density
   */
  public String getDensity() {
    String densityStr = null;
    final int density = context.getResources().getDisplayMetrics().densityDpi;
    switch (density) {
      case DisplayMetrics.DENSITY_LOW:
        densityStr = "LDPI";
        break;
      case DisplayMetrics.DENSITY_MEDIUM:
        densityStr = "MDPI";
        break;
      case DisplayMetrics.DENSITY_TV:
        densityStr = "TVDPI";
        break;
      case DisplayMetrics.DENSITY_HIGH:
        densityStr = "HDPI";
        break;
      case DisplayMetrics.DENSITY_XHIGH:
        densityStr = "XHDPI";
        break;
      case DisplayMetrics.DENSITY_400:
        densityStr = "XMHDPI";
        break;
      case DisplayMetrics.DENSITY_XXHIGH:
        densityStr = "XXHDPI";
        break;
      case DisplayMetrics.DENSITY_XXXHIGH:
        densityStr = "XXXHDPI";
        break;
      default:
        //do nothing
        break;
    }
    return CheckValidityUtil.checkValidData(densityStr);
  }

  /**
   * Get display xy coordinates int [ ].
   *
   * @param event the event
   * @return the int [ ]
   */
  public int[] getDisplayXYCoordinates(MotionEvent event) {
    int[] coordinates = new int[2];
    coordinates[0] = 0;
    coordinates[1] = 0;
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      coordinates[0] = (int) event.getX();     // X Coordinates
      coordinates[1] = (int) event.getY();     // Y Coordinates
    }
    return coordinates;
  }

  /**
   * Gets resolution.
   *
   * @return the resolution
   */
  public String getResolution() {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    DisplayMetrics metrics = new DisplayMetrics();
    display.getMetrics(metrics);
    return CheckValidityUtil.checkValidData(metrics.heightPixels + "x" + metrics.widthPixels);
  }
}
