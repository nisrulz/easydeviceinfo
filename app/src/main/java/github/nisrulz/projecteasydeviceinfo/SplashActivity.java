/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.nisrulz.projecteasydeviceinfo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

  private final static String[] requestBasicPermissions = {
      Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE,
      Manifest.permission.GET_ACCOUNTS, Manifest.permission.USE_FINGERPRINT
  };
  private boolean launched = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setFullScreen(this);

    setContentView(R.layout.activity_splash);

    boolean hasFineLocation =
        RuntimePermissionUtil.checkPermissonGranted(this, Manifest.permission.ACCESS_FINE_LOCATION);
    boolean hasReadPhoneState =
        RuntimePermissionUtil.checkPermissonGranted(this, Manifest.permission.READ_PHONE_STATE);
    boolean hasGetAcc =
        RuntimePermissionUtil.checkPermissonGranted(this, Manifest.permission.GET_ACCOUNTS);
    boolean hasFingerprint =
        RuntimePermissionUtil.checkPermissonGranted(this, Manifest.permission.USE_FINGERPRINT);

    if (hasFineLocation && hasGetAcc && hasReadPhoneState && hasFingerprint) {
      loadMainActivity();
    }
    else {
      RuntimePermissionUtil.requestPermission(SplashActivity.this, requestBasicPermissions, 100);
    }

    final Button btn_req = (Button) findViewById(R.id.btn_req);
    btn_req.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        RuntimePermissionUtil.requestPermission(SplashActivity.this, requestBasicPermissions, 100);
      }
    });
  }

  private void loadMainActivity() {

    // Reload Activity
    startActivity(new Intent(SplashActivity.this, MainActivity.class));
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    launched = true;
    finish();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions,
      @NonNull final int[] grantResults) {
    switch (requestCode) {
      case 100: {

        RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
          @Override
          public void onPermissionGranted() {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED
                && grantResults[3] == PackageManager.PERMISSION_GRANTED
                && !launched) {
              loadMainActivity();
            }
          }

          @Override
          public void onPermissionDenied() {
            // do nothing
          }
        });
        break;
      }
    }
  }

  private static void setFullScreen(Activity activity) {
    // Call before calling setContentView();
    activity.getWindow()
        .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }
}
