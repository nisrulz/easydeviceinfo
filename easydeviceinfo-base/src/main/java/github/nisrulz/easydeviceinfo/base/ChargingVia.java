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
    ChargingVia.USB, ChargingVia.AC, ChargingVia.WIRELESS, ChargingVia.UNKNOWN_SOURCE
})
@Retention(RetentionPolicy.CLASS)
public @interface ChargingVia {
  int USB = 0;
  int AC = 1;
  int WIRELESS = 2;
  int UNKNOWN_SOURCE = 3;
}
