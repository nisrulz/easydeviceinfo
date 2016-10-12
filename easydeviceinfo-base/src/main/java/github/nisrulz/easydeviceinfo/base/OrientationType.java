package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    EasyDeviceMod.ORIENTATION_LANDSCAPE, EasyDeviceMod.ORIENTATION_PORTRAIT,
    EasyDeviceMod.ORIENTATION_UNKNOWN
})
@Retention(RetentionPolicy.CLASS)
public @interface OrientationType {
}
