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

package github.nisrulz.easydeviceinfo.common;

/**
 * Easy device info class.
 */
public class EasyDeviceInfo {

  /**
   * The Name.
   */
  public static final String nameOfLib = "EasyDeviceInfo";
  /**
   * The constant debuggable.
   */
  public static boolean debuggable = false;
  /**
   * The Not found val.
   */
  public static String notFoundVal = "unknown";

  private EasyDeviceInfo() {

  }

  /**
   * Instantiates a new Easy device info.
   *
   * @param notFoundVal
   *     the not found val
   */
  public static void setNotFoundVal(String notFoundVal) {
    EasyDeviceInfo.notFoundVal = notFoundVal;
  }

  /**
   * Instantiates a new Easy device info.
   *
   * @param notFoundVal
   *     the not found val
   * @param debugFlag
   *     the debug flag
   */
  public static void setConfigs(String notFoundVal, boolean debugFlag) {
    EasyDeviceInfo.notFoundVal = notFoundVal;
    EasyDeviceInfo.debuggable = debugFlag;
  }

  /**
   * Gets library version.
   *
   * @return the library version
   */
  public static String getLibraryVersion() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(nameOfLib)
        .append(" : v")
        .append(BuildConfig.VERSION_NAME)
        .append(" [build-v")
        .append(String.valueOf(BuildConfig.VERSION_CODE))
        .append("]");

    return stringBuilder.toString();
  }

  /**
   * Debug.
   */
  public static void debug() {
    EasyDeviceInfo.debuggable = true;
  }
}
