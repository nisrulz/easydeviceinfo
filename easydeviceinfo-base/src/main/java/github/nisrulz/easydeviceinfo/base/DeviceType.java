package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    DeviceType.WATCH, DeviceType.PHONE, DeviceType.PHABLET, DeviceType.TABLET, DeviceType.TV
})
@Retention(RetentionPolicy.CLASS)
public @interface DeviceType {
  int WATCH = 0;
  int PHONE = 1;
  int PHABLET = 2;
  int TABLET = 3;
  int TV = 4;
}
