package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    NetworkType.WIFI_WIFIMAX, NetworkType.CELLULAR_4G, NetworkType.CELLULAR_3G,
    NetworkType.CELLULAR_2G, NetworkType.CELLULAR_UNIDENTIFIED_GEN, NetworkType.CELLULAR_UNKNOWN,
    NetworkType.UNKNOWN
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
