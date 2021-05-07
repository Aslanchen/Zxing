package com.aslan.zxing;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.encode.QRCodeEncoder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  public static final int REQUEST_PERMISSION = 1000;
  public static final int REQUEST_SCAN = 1001;

  private TextView tvScan;
  private Button btScan;
  private Button btCreat;
  private ImageView ivImage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tvScan = findViewById(R.id.tvScan);
    btScan = findViewById(R.id.btScan);
    btCreat = findViewById(R.id.btCreat);
    ivImage = findViewById(R.id.ivImage);

    btScan.setOnClickListener(this);
    btCreat.setOnClickListener(this);

    checkPermission();
  }

  private boolean checkPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
        showPermissionErrorDialog();
      } else {
        ActivityCompat
            .requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION);
      }
      return false;
    } else {
      return true;
    }
  }

  /**
   * 权限异常提示
   */
  protected void showPermissionErrorDialog() {
    new AlertDialog.Builder(this)
        .setTitle(R.string.error_permission_title)
        .setCancelable(false)
        .setMessage(R.string.error_permission_message)
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
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (REQUEST_SCAN == requestCode && resultCode == Activity.RESULT_OK && data != null) {
      tvScan.setText(data.getStringExtra(CaptureActivity.TAG_RESULT_Text));
    }
  }

  @Override
  public void onClick(View v) {
    if (v == btScan) {
      doScan();
    } else if (v == btCreat) {
      doCreat();
    }
  }

  private void doScan() {
    if (!checkPermission()) {
      return;
    }

    Intent intent = new Intent(this, CaptureActivity.class);
    startActivityForResult(intent, REQUEST_SCAN);
  }

  private void doCreat() {
    QRCodeEncoder mQRCodeEncoder = new QRCodeEncoder(BarcodeFormat.QR_CODE, ivImage.getWidth());
    try {
      Bitmap bitmap = mQRCodeEncoder.encodeAsBitmap("test");
      ivImage.setImageBitmap(bitmap);
    } catch (WriterException e) {
      e.printStackTrace();
    }
  }
}
