package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    EasyNetworkMod.WIFI_WIFIMAX, EasyNetworkMod.CELLULAR_4G, EasyNetworkMod.CELLULAR_3G,
    EasyNetworkMod.CELLULAR_2G, EasyNetworkMod.CELLULAR_UNIDENTIFIED_GEN,
    EasyNetworkMod.CELLULAR_UNKNOWN, EasyNetworkMod.UNKNOWN
})
@Retention(RetentionPolicy.CLASS)
public @interface NetworkType {
}
