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

import android.Manifest.permission;
import android.R.anim;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    static final String[] requestBasicPermissions = {
            permission.ACCESS_FINE_LOCATION, permission.READ_PHONE_STATE,
            permission.GET_ACCOUNTS, permission.USE_FINGERPRINT
    };

    boolean launched;

    private static void setFullScreen(final Activity activity) {
        // Call before calling setContentView();
        activity.getWindow()
                .setFlags(LayoutParams.FLAG_FULLSCREEN,
                        LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashActivity.setFullScreen(this);

        this.setContentView(R.layout.activity_splash);

        final boolean hasFineLocation =
                RuntimePermissionUtil.checkPermissonGranted(this, permission.ACCESS_FINE_LOCATION);
        final boolean hasReadPhoneState =
                RuntimePermissionUtil.checkPermissonGranted(this, permission.READ_PHONE_STATE);
        final boolean hasGetAcc =
                RuntimePermissionUtil.checkPermissonGranted(this, permission.GET_ACCOUNTS);
        boolean hasFingerprint = false;
        if (VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hasFingerprint =
                    RuntimePermissionUtil.checkPermissonGranted(this, permission.USE_FINGERPRINT);
        }

        if (hasFineLocation && hasGetAcc && hasReadPhoneState && hasFingerprint) {
            this.loadMainActivity();
        } else {
            RuntimePermissionUtil.requestPermission(this, SplashActivity.requestBasicPermissions, 100);
        }

        Button btnReqPermission = this.findViewById(R.id.btn_req);
        btnReqPermission.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                RuntimePermissionUtil.requestPermission(SplashActivity.this, SplashActivity.requestBasicPermissions, 100);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions,
                                           @NonNull final int[] grantResults) {
        if (requestCode == 100) {
            RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                @Override
                public void onPermissionDenied() {
                    // do nothing
                }

                @Override
                public void onPermissionGranted() {
                    if ((grantResults[0] == PackageManager.PERMISSION_GRANTED)
                            && (grantResults[1] == PackageManager.PERMISSION_GRANTED)
                            && (grantResults[2] == PackageManager.PERMISSION_GRANTED)
                            && (grantResults[3] == PackageManager.PERMISSION_GRANTED)
                            && !SplashActivity.this.launched) {
                        SplashActivity.this.loadMainActivity();
                    }
                }
            });
        }
    }

    void loadMainActivity() {

        // Reload Activity
        this.startActivity(new Intent(this, MainActivity.class));
        this.overridePendingTransition(anim.fade_in, anim.fade_out);
        this.launched = true;
        this.finish();
    }
}
