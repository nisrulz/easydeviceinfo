package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    EasyConfigMod.RINGER_MODE_NORMAL, EasyConfigMod.RINGER_MODE_SILENT,
    EasyConfigMod.RINGER_MODE_VIBRATE
})
@Retention(RetentionPolicy.CLASS)
public @interface RingerMode {
}
