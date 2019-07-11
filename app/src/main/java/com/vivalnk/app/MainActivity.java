package com.vivalnk.app;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureFragment;
import com.google.zxing.client.android.onScanResultListener;

public class MainActivity extends AppCompatActivity implements onScanResultListener {

  public static final int REQUEST_PERMISSION = 1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
        showPermissionErrorDialog();
      } else {
        ActivityCompat
            .requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
      }
    } else {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.flMain, CaptureFragment.newInstance(this));
      fragmentTransaction.commit();
    }
  }

  /**
   * 权限异常提示
   */
  protected void showPermissionErrorDialog() {
    new AlertDialog.Builder(this)
        .setTitle(com.google.zxing.client.android.R.string.error_permission_title)
        .setCancelable(false)
        .setMessage(com.google.zxing.client.android.R.string.error_permission_message)
        .setPositiveButton(com.google.zxing.client.android.R.string.button_ok,
            new OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                try {
                  startActivity(intent);
                } catch (Exception e) {
                  e.printStackTrace();
                }

                finish();
              }
            })
        .show();
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == REQUEST_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
      fragmentTransaction.replace(R.id.flMain, CaptureFragment.newInstance(this));
      fragmentTransaction.commit();
    }
  }

  @Override
  public void onScanResult(Result rawResult) {

  }
}
