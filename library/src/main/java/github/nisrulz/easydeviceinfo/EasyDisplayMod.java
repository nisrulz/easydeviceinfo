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

package github.nisrulz.easydeviceinfo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * The type Easy  display mod.
 */
public class EasyDisplayMod {
  private final Context context;

  /**
   * Instantiates a new Easy display mod.
   *
   * @param context
   *     the context
   */
  public EasyDisplayMod(final Context context) {
    this.context = context;
  }

  /**
   * Gets density.
   *
   * @return the density
   */
  public final String getDensity() {
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
   * @param event
   *     the event
   * @return the int [ ]
   */
  public final int[] getDisplayXYCoordinates(final MotionEvent event) {
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
  public final String getResolution() {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    DisplayMetrics metrics = new DisplayMetrics();
    display.getMetrics(metrics);
    return CheckValidityUtil.checkValidData(metrics.heightPixels + "x" + metrics.widthPixels);
  }
}
