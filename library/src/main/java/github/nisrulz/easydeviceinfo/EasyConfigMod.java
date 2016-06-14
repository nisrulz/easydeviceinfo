package github.nisrulz.easydeviceinfo;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.SystemClock;
import java.util.Locale;

/**
 * The type Easy di config mod.
 */
public class EasyConfigMod {
  /**
   * The constant RINGER_MODE_SILENT.
   */
  public static final int RINGER_MODE_SILENT = 0;
  /**
   * The constant RINGER_MODE_NORMAL.
   */
  public static final int RINGER_MODE_NORMAL = 1;
  /**
   * The constant RINGER_MODE_VIBRATE.
   */
  public static final int RINGER_MODE_VIBRATE = 2;
  private final Context context;

  /**
   * Instantiates a new Easy di config mod.
   *
   * @param context the context
   */
  public EasyConfigMod(Context context) {
    this.context = context;
  }

  /**
   * Is running on emulator boolean.
   *
   * @return the boolean
   */
  public boolean isRunningOnEmulator() {
    return Build.BRAND.contains("generic")
        || Build.DEVICE.contains("generic")
        || Build.PRODUCT.contains("sdk")
        || Build.HARDWARE.contains("goldfish")
        || Build.MANUFACTURER.contains("Genymotion")
        || Build.PRODUCT.contains("vbox86p")
        || Build.DEVICE.contains("vbox86p")
        || Build.HARDWARE.contains("vbox86");
  }

  /**
   * Gets Device Ringer Mode
   *
   * @return Device Ringer Mode
   */
  public int getDeviceRingerMode() {
    int ringerMode = RINGER_MODE_NORMAL;
    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    switch (audioManager.getRingerMode()) {
      case AudioManager.RINGER_MODE_NORMAL:
        ringerMode = RINGER_MODE_NORMAL;
        break;
      case AudioManager.RINGER_MODE_SILENT:
        ringerMode = RINGER_MODE_SILENT;
        break;
      case AudioManager.RINGER_MODE_VIBRATE:
        ringerMode = RINGER_MODE_VIBRATE;
        break;
      default:
        //do nothing
        break;
    }
    return ringerMode;
  }

  /**
   * Gets time.
   *
   * @return the time
   */
  public long getTime() {
    return System.currentTimeMillis();
  }

  /**
   * Gets formatted time.
   *
   * @return the formatted time
   */
  public String getFormattedTime() {

    long millis = System.currentTimeMillis();
    int sec = (int) (millis / 1000) % 60;
    int min = (int) ((millis / (1000 * 60)) % 60);
    int hr = (int) ((millis / (1000 * 60 * 60)) % 24);

    return String.format(Locale.US, "%02d:%02d:%02d", hr, min, sec);
  }

  /**
   * Gets up time.
   *
   * @return the up time
   */
  public long getUpTime() {
    return SystemClock.uptimeMillis();
  }

  /**
   * Gets formatted up time.
   *
   * @return the formatted up time
   */
  public String getFormattedUpTime() {

    long millis = SystemClock.uptimeMillis();
    int sec = (int) (millis / 1000) % 60;
    int min = (int) ((millis / (1000 * 60)) % 60);
    int hr = (int) ((millis / (1000 * 60 * 60)) % 24);

    return String.format(Locale.US, "%02d:%02d:%02d", hr, min, sec);
  }
}
