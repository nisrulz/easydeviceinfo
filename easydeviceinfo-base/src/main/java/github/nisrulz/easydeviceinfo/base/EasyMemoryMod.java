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

package github.nisrulz.easydeviceinfo.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

import static android.os.Build.VERSION_CODES;
import static android.os.Environment.MEDIA_MOUNTED;
import static android.os.Environment.getDataDirectory;
import static android.os.Environment.getExternalStorageDirectory;
import static android.os.Environment.getExternalStorageState;

/**
 * EasyMemory Mod Class
 * <p>
 * Deprecation warning suppressed since it is handled in the code
 */
@SuppressWarnings("deprecation")
public class EasyMemoryMod {

    private static final String IO_EXCEPTION = "IO Exception";

    private static final int BYTEFACTOR = 1024;

    private final Context context;

    /**
     * Instantiates a new Easy memory mod.
     *
     * @param context the context
     */
    public EasyMemoryMod(Context context) {
        this.context = context;
    }

    /**
     * Convert to gb float.
     *
     * @param valInBytes the val in bytes
     * @return the float
     */
    public float convertToGb(final long valInBytes) {
        return (float) valInBytes / (EasyMemoryMod.BYTEFACTOR * EasyMemoryMod.BYTEFACTOR * EasyMemoryMod.BYTEFACTOR);
    }

    /**
     * Convert to kb float.
     *
     * @param valInBytes the val in bytes
     * @return the float
     */
    public float convertToKb(final long valInBytes) {
        return (float) valInBytes / EasyMemoryMod.BYTEFACTOR;
    }

    /**
     * Convert to mb float.
     *
     * @param valInBytes the val in bytes
     * @return the float
     */
    public float convertToMb(final long valInBytes) {
        return (float) valInBytes / (EasyMemoryMod.BYTEFACTOR * EasyMemoryMod.BYTEFACTOR);
    }

    /**
     * Convert to tb float.
     *
     * @param valInBytes the val in bytes
     * @return the float
     */
    @SuppressWarnings("NumericOverflow")
    public float convertToTb(final long valInBytes) {
        return (float) valInBytes / (EasyMemoryMod.BYTEFACTOR * EasyMemoryMod.BYTEFACTOR * EasyMemoryMod.BYTEFACTOR
                * EasyMemoryMod.BYTEFACTOR);
    }

    /**
     * Gets available external memory size.
     *
     * @return the available external memory size
     */
    public final long getAvailableExternalMemorySize() {
        if (this.externalMemoryAvailable()) {
            final File path = getExternalStorageDirectory();
            final StatFs stat = new StatFs(path.getPath());
            final long blockSize;
            final long availableBlocks;
            if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = stat.getBlockSizeLong();
                availableBlocks = stat.getAvailableBlocksLong();
            } else {
                blockSize = stat.getBlockSize();
                availableBlocks = stat.getAvailableBlocks();
            }
            return availableBlocks * blockSize;
        } else {
            return 0;
        }
    }

    /**
     * Gets available internal memory size.
     *
     * @return the available internal memory size
     */
    public final long getAvailableInternalMemorySize() {
        final File path = getDataDirectory();
        final StatFs stat = new StatFs(path.getPath());
        final long blockSize;
        final long availableBlocks;
        if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
            availableBlocks = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            availableBlocks = stat.getAvailableBlocks();
        }
        return availableBlocks * blockSize;
    }

    /**
     * Gets total external memory size.
     *
     * @return the total external memory size
     */
    public final long getTotalExternalMemorySize() {
        if (this.externalMemoryAvailable()) {
            final File path = getExternalStorageDirectory();
            final StatFs stat = new StatFs(path.getPath());
            final long blockSize;
            final long totalBlocks;
            if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
                blockSize = stat.getBlockSizeLong();
                totalBlocks = stat.getBlockCountLong();
            } else {
                blockSize = stat.getBlockSize();
                totalBlocks = stat.getBlockCount();
            }
            return totalBlocks * blockSize;
        } else {
            return 0;
        }
    }

    /**
     * Gets total internal memory size.
     *
     * @return the total internal memory size
     */
    public final long getTotalInternalMemorySize() {
        final File path = getDataDirectory();
        final StatFs stat = new StatFs(path.getPath());
        final long blockSize;
        final long totalBlocks;
        if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2) {
            blockSize = stat.getBlockSizeLong();
            totalBlocks = stat.getBlockCountLong();
        } else {
            blockSize = stat.getBlockSize();
            totalBlocks = stat.getBlockCount();
        }
        return totalBlocks * blockSize;
    }

    /**
     * Gets total ram.
     *
     * @return the total ram
     */
    public final long getTotalRAM() {
        long totalMemory = 0;
        if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
            final ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
            final ActivityManager activityManager =
                    (ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE);
            if (activityManager != null) {
                activityManager.getMemoryInfo(mi);
                totalMemory = mi.totalMem;
            }
        } else {
            RandomAccessFile reader = null;
            final String load;
            try {
                reader = new RandomAccessFile("/proc/meminfo", "r");
                load = reader.readLine().replaceAll("\\D+", "");
                totalMemory = Integer.parseInt(load);
            } catch (final IOException e) {
                if (EasyDeviceInfo.debuggable) {
                    Log.e(EasyDeviceInfo.nameOfLib, EasyMemoryMod.IO_EXCEPTION, e);
                }
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        if (EasyDeviceInfo.debuggable) {
                            Log.e(EasyDeviceInfo.nameOfLib, EasyMemoryMod.IO_EXCEPTION, e);
                        }
                    }
                }
            }
        }
        return totalMemory;
    }

    private boolean externalMemoryAvailable() {
        return getExternalStorageState().equals(MEDIA_MOUNTED);
    }
}
