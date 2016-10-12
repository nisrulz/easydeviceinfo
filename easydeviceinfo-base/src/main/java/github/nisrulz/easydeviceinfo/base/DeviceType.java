package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    EasyDeviceMod.DEVICE_TYPE_WATCH, EasyDeviceMod.DEVICE_TYPE_PHONE,
    EasyDeviceMod.DEVICE_TYPE_PHABLET, EasyDeviceMod.DEVICE_TYPE_TABLET,
    EasyDeviceMod.DEVICE_TYPE_TV
})
@Retention(RetentionPolicy.CLASS)
public @interface DeviceType {
}
