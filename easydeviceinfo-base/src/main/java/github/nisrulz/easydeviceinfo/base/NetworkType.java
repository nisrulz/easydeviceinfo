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

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
            NetworkType.WIFI_WIFIMAX, NetworkType.CELLULAR_4G, NetworkType.CELLULAR_3G,
            NetworkType.CELLULAR_2G, NetworkType.CELLULAR_UNIDENTIFIED_GEN,
            NetworkType.CELLULAR_UNKNOWN, NetworkType.UNKNOWN
        })
@Retention(RetentionPolicy.CLASS)
public @interface NetworkType {
  int UNKNOWN = 0;
  int WIFI_WIFIMAX = 1;
  int CELLULAR_UNKNOWN = 2;
  int CELLULAR_2G = 3;
  int CELLULAR_3G = 4;
  int CELLULAR_4G = 5;
  int CELLULAR_UNIDENTIFIED_GEN = 6;
}
