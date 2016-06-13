package github.nisrulz.easydeviceinfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;

import static android.os.Build.VERSION;
import static android.os.Build.VERSION_CODES;

/**
 * The type Easy di memory mod.
 */
public class EasyMemoryMod {
  private final Context context;

  /**
   * Instantiates a new Easy di memory mod.
   *
   * @param context the context
   */
  public EasyMemoryMod(Context context) {
    this.context = context;
  }

  public long getTotalMemory() {
    MemoryInfo mi = new MemoryInfo();
    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
    activityManager.getMemoryInfo(mi);
    long memory_available_in_Mbs = 0;
    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
      memory_available_in_Mbs = mi.totalMem / 1048576L;
    }
    return memory_available_in_Mbs;
  }

  private boolean externalMemoryAvailable() {
    return android.os.Environment.getExternalStorageState()
        .equals(android.os.Environment.MEDIA_MOUNTED);
  }

  public long getAvailableInternalMemorySize() {
    File path = Environment.getDataDirectory();
    StatFs stat = new StatFs(path.getPath());
    long blockSize;
    long availableBlocks;
    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
      blockSize = stat.getBlockSizeLong();
      availableBlocks = stat.getAvailableBlocksLong();
    } else {
      blockSize = stat.getBlockSize();
      availableBlocks = stat.getAvailableBlocks();
    }
    return (availableBlocks * blockSize) / (1024 * 1024);
  }

  public long getTotalInternalMemorySize() {
    File path = Environment.getDataDirectory();
    StatFs stat = new StatFs(path.getPath());
    long blockSize;
    long totalBlocks;
    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
      blockSize = stat.getBlockSizeLong();
      totalBlocks = stat.getBlockCountLong();
    } else {
      blockSize = stat.getBlockSize();
      totalBlocks = stat.getBlockCount();
    }
    return (totalBlocks * blockSize) / (1024 * 1024);
  }

  public long getAvailableExternalMemorySize() {
    if (externalMemoryAvailable()) {
      File path = Environment.getExternalStorageDirectory();
      StatFs stat = new StatFs(path.getPath());
      long blockSize;
      long availableBlocks;
      if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
        blockSize = stat.getBlockSizeLong();
        availableBlocks = stat.getAvailableBlocksLong();
      } else {
        blockSize = stat.getBlockSize();
        availableBlocks = stat.getAvailableBlocks();
      }
      return (availableBlocks * blockSize) / (1024 * 1024);
    } else {
      return 0;
    }
  }

  public long getTotalExternalMemorySize() {
    if (externalMemoryAvailable()) {
      File path = Environment.getExternalStorageDirectory();
      StatFs stat = new StatFs(path.getPath());
      long blockSize;
      long totalBlocks;
      if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
        blockSize = stat.getBlockSizeLong();
        totalBlocks = stat.getBlockCountLong();
      } else {
        blockSize = stat.getBlockSize();
        totalBlocks = stat.getBlockCount();
      }
      return (totalBlocks * blockSize) / (1024 * 1024);
    } else {
      return 0;
    }
  }
}
