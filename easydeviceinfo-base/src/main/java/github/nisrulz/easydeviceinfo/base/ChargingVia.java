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
