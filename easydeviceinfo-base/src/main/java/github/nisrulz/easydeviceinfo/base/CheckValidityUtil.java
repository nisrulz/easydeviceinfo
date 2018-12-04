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

import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/**
 * Check Validity Util Class
 */
final class CheckValidityUtil {

    private CheckValidityUtil() {
        // private constructor for utility class
    }

    /**
     * Check valid data string.
     *
     * @param data the data
     * @return the string
     */
    static String checkValidData(final String data) {
        String tempData = data;
        if (tempData == null || tempData.length() == 0) {
            tempData = EasyDeviceInfo.notFoundVal;
        }
        return tempData;
    }

    /**
     * Check valid data string [ ].
     *
     * @param data the data
     * @return the string [ ]
     */
    static String[] checkValidData(final String[] data) {
        String[] tempData = data;
        if (tempData == null || tempData.length == 0) {
            tempData = new String[]{EasyDeviceInfo.notFoundVal};
        }
        return tempData;
    }

    /**
     * Handle illegal character in result string.
     *
     * @param result the result
     * @return the string
     */
    static String handleIllegalCharacterInResult(final String result) {
        String tempResult = result;
        if (tempResult != null && tempResult.contains(" ")) {
            tempResult = tempResult.replaceAll(" ", "_");
        }
        return tempResult;
    }
}
