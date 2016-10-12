package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    EasyBatteryMod.CHARGING_VIA_AC, EasyBatteryMod.CHARGING_VIA_USB,
    EasyBatteryMod.CHARGING_VIA_WIRELESS, EasyBatteryMod.CHARGING_VIA_UNKNOWN_SOURCE
})
@Retention(RetentionPolicy.CLASS)
public @interface ChargingVia {
}
