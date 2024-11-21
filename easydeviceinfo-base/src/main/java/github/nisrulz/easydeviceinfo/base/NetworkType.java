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

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static github.nisrulz.easydeviceinfo.base.NetworkType.CELLULAR_2G;
import static github.nisrulz.easydeviceinfo.base.NetworkType.CELLULAR_3G;
import static github.nisrulz.easydeviceinfo.base.NetworkType.CELLULAR_4G_OR_5G_NSA;
import static github.nisrulz.easydeviceinfo.base.NetworkType.CELLULAR_5G_SA;
import static github.nisrulz.easydeviceinfo.base.NetworkType.CELLULAR_UNIDENTIFIED_GEN;
import static github.nisrulz.easydeviceinfo.base.NetworkType.CELLULAR_UNKNOWN;
import static github.nisrulz.easydeviceinfo.base.NetworkType.UNKNOWN;
import static github.nisrulz.easydeviceinfo.base.NetworkType.WIFI_WIFIMAX;

@IntDef({
        WIFI_WIFIMAX, CELLULAR_4G_OR_5G_NSA,
        CELLULAR_3G, CELLULAR_5G_SA,
        CELLULAR_2G, CELLULAR_UNIDENTIFIED_GEN,
        CELLULAR_UNKNOWN, UNKNOWN
})
@Retention(RetentionPolicy.CLASS)
public @interface NetworkType {

    int UNKNOWN = 0;
    int CELLULAR_UNIDENTIFIED_GEN = 1;
    int WIFI_WIFIMAX = 2;
    int CELLULAR_UNKNOWN = 3;
    int CELLULAR_2G = 4;
    int CELLULAR_3G = 5;
    int CELLULAR_4G_OR_5G_NSA = 6;
    int CELLULAR_5G_SA = 7;
    int CELLULAR_6G = 8;
}
