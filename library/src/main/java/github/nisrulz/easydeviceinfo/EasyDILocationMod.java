package github.nisrulz.easydeviceinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

public class EasyDILocationMod {
  private Context context;

  public EasyDILocationMod(Context context) {
    this.context = context;
  }

  /**
   * Get lat long double [ ].
   *
   * @return the double [ ]
   */
  @SuppressWarnings("MissingPermission") public double[] getLatLong() {
    boolean hasFineLocationPermission =
        context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED ? true : false;
    boolean isGPSEnabled;
    boolean isNetworkEnabled;

    double[] gps = new double[2];
    gps[0] = 0;
    gps[1] = 0;
    if (hasFineLocationPermission) {
      LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

      isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
      isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

      Location net_loc = null;
      Location gps_loc = null;
      Location final_loc;

      if (isGPSEnabled) {
        gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      }
      if (isNetworkEnabled) {
        net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
      }

      if (gps_loc != null && net_loc != null) {
        if (gps_loc.getAccuracy() >= net_loc.getAccuracy()) {
          final_loc = gps_loc;
        } else {
          final_loc = net_loc;
        }
      } else {
        if (gps_loc != null) {
          final_loc = gps_loc;
        } else if (net_loc != null) {
          final_loc = net_loc;
        } else {
          // GPS and Network both are null so try passive
          final_loc = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
      }

      if (final_loc != null) {
        gps[0] = final_loc.getLatitude();
        gps[1] = final_loc.getLongitude();
      }

      return gps;
    }
    return gps;
  }
}
