package github.nisrulz.projecteasydeviceinfo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

  private final static String[] requestBasicPermissions = {
      Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE,
      Manifest.permission.GET_ACCOUNTS
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    requestPermission();

    final Button btn_req = (Button) findViewById(R.id.btn_req);
    btn_req.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        requestPermission();
      }
    });
  }

  private void requestPermission() {
    RuntimePermissionUtil.requestPermission(this, requestBasicPermissions, 100);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    switch (requestCode) {
      case 100: {

        RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
          @Override
          public void onPermissionGranted() {
            // Reload Activity
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
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
}
