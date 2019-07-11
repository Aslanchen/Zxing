package com.vivalnk.app;

import android.os.Bundle;
import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureFragment;

/**
 * 扫码界面
 */
public class ScanCodeFragment extends CaptureFragment {

  public static ScanCodeFragment newInstance() {
    Bundle args = new Bundle();
    ScanCodeFragment fragment = new ScanCodeFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public int getLayoutId() {
    return R.layout.capture;
  }

  @Override
  public void onScanResult(Result rawResult) {

  }
}
