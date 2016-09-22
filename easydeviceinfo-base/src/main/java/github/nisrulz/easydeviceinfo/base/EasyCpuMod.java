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

import android.os.Build;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/**
 * The type Easy  cpu mod.
 */
public class EasyCpuMod {
  /**
   * Get supported abis string [ ].
   *
   * @return the string [ ]
   */
  public final String[] getSupportedABIS() {
    String[] result = new String[] { EasyDeviceInfo.notFoundVal };
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      result = Build.SUPPORTED_ABIS;
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets string supported abis.
   *
   * @return the string supported abis
   */
  public final String getStringSupportedABIS() {
    String result = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      String[] supportedABIS = Build.SUPPORTED_ABIS;
      StringBuilder supportedABIString = new StringBuilder();
      if (supportedABIS.length > 0) {
        for (String abis : supportedABIS) {
          supportedABIString.append(abis).append("_");
        }
        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"));
      }
      else {
        supportedABIString.append("");
      }
      result = supportedABIString.toString();
    }
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(result));
  }

  /**
   * Gets string supported 32 bit abis.
   *
   * @return the string supported 32 bit abis
   */
  public final String getStringSupported32bitABIS() {
    String result = null;

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      String[] supportedABIS = Build.SUPPORTED_32_BIT_ABIS;

      StringBuilder supportedABIString = new StringBuilder();
      if (supportedABIS.length > 0) {
        for (String abis : supportedABIS) {
          supportedABIString.append(abis).append("_");
        }
        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"));
      }
      else {
        supportedABIString.append("");
      }

      result = supportedABIString.toString();
    }

    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(result));
  }

  /**
   * Gets string supported 64 bit abis.
   *
   * @return the string supported 64 bit abis
   */
  public final String getStringSupported64bitABIS() {
    String result = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      String[] supportedABIS = Build.SUPPORTED_64_BIT_ABIS;

      StringBuilder supportedABIString = new StringBuilder();
      if (supportedABIS.length > 0) {
        for (String abis : supportedABIS) {
          supportedABIString.append(abis).append("_");
        }
        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"));
      }
      else {
        supportedABIString.append("");
      }
      result = supportedABIString.toString();
    }
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(result));
  }

  /**
   * Get supported 32 bit abis string [ ].
   *
   * @return the string [ ]
   */
  public final String[] getSupported32bitABIS() {
    String[] result = new String[] { EasyDeviceInfo.notFoundVal };
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      result = Build.SUPPORTED_32_BIT_ABIS;
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Get supported 64 bit abis string [ ].
   *
   * @return the string [ ]
   */
  public final String[] getSupported64bitABIS() {
    String[] result = new String[] { EasyDeviceInfo.notFoundVal };
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      result = Build.SUPPORTED_64_BIT_ABIS;
    }
    return CheckValidityUtil.checkValidData(result);
  }
}
