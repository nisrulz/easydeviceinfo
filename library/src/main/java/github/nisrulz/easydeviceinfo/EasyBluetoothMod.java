package github.nisrulz.easydeviceinfo;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * The type Easy di bluetooth mod.
 */
public class EasyBluetoothMod {
  private Context context;

  /**
   * Instantiates a new Easy di bluetooth mod.
   *
   * @param context the context
   */
  public EasyBluetoothMod(Context context) {
    this.context = context;
  }

  /**
   * Gets bluetooth mac.
   *
   * @return the bluetooth mac
   */
  @SuppressWarnings("MissingPermission") public String getBluetoothMAC() {
    String result = null;
    if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH)
        == PackageManager.PERMISSION_GRANTED) {
      BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
      result = bta.getAddress();
    }
    return CheckValidityUtil.checkValidData(result);
  }
}
