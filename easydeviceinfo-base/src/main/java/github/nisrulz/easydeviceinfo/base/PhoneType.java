package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    EasyDeviceMod.PHONE_TYPE_GSM, EasyDeviceMod.PHONE_TYPE_CDMA, EasyDeviceMod.PHONE_TYPE_NONE
})
@Retention(RetentionPolicy.CLASS)
public @interface PhoneType {
}
