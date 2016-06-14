package github.nisrulz.easydeviceinfo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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

  /**
   * Gets total ram.
   *
   * @return the total ram
   */
  public long getTotalRAM() {
    long total_memory_in_Mbs = 0;
    if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
      MemoryInfo mi = new MemoryInfo();
      ActivityManager activityManager =
          (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);
      activityManager.getMemoryInfo(mi);
      total_memory_in_Mbs = mi.totalMem / 1048576L;
    } else {
      RandomAccessFile reader;
      String load;
      try {
        reader = new RandomAccessFile("/proc/meminfo", "r");
        load = reader.readLine().replaceAll("\\D+", "");
        total_memory_in_Mbs = Integer.parseInt(load) / 1024;
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return total_memory_in_Mbs;
  }

  private boolean externalMemoryAvailable() {
    return android.os.Environment.getExternalStorageState()
        .equals(android.os.Environment.MEDIA_MOUNTED);
  }

  /**
   * Gets available internal memory size.
   *
   * @return the available internal memory size
   */
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

  /**
   * Gets total internal memory size.
   *
   * @return the total internal memory size
   */
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

  /**
   * Gets available external memory size.
   *
   * @return the available external memory size
   */
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

  /**
   * Gets total external memory size.
   *
   * @return the total external memory size
   */
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
