package github.nisrulz.easydeviceinfo.base;

import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    OrientationType.PORTRAIT, OrientationType.LANDSCAPE, OrientationType.UNKNOWN
})
@Retention(RetentionPolicy.CLASS)
public @interface OrientationType {
  int PORTRAIT = 0;
  int LANDSCAPE = 1;
  int UNKNOWN = 2;
}
