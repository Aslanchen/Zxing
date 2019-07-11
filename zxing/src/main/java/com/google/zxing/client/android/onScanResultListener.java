package com.google.zxing.client.android;

import com.google.zxing.Result;
import java.io.Serializable;

/**
 * 扫描结果
 *
 * @author Aslan
 * @date 2019/7/11
 */
public interface onScanResultListener extends Serializable {

  void onScanResult(Result rawResult);
}
