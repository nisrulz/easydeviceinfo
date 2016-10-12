package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    EasyBatteryMod.HEALTH_GOOD, EasyBatteryMod.HEALTH_HAVING_ISSUES
})
@Retention(RetentionPolicy.CLASS)
public @interface BatteryHealth {
}
