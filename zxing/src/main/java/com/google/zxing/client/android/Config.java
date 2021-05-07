package com.google.zxing.client.android;

import com.google.zxing.client.android.camera.FrontLightMode;

/**
 * @author chenhengfei(Aslanchen)
 * @date 2021
 */
public class Config {

  public static final String KEY_PLAY_BEEP = "preferences_play_beep";
  public static final String KEY_VIBRATE = "preferences_vibrate";
  public static final String KEY_AUTO_FOCUS = "preferences_auto_focus";
  public static final String KEY_INVERT_SCAN = "preferences_invert_scan";
  public static final String KEY_FRONT_LIGHT_MODE = "preferences_front_light_mode";
  public static final String KEY_DISABLE_CONTINUOUS_FOCUS = "preferences_disable_continuous_focus";
  public static final String KEY_DISABLE_EXPOSURE = "preferences_disable_exposure";
  public static final String KEY_DISABLE_METERING = "preferences_disable_metering";
  public static final String KEY_DISABLE_BARCODE_SCENE_MODE = "preferences_disable_barcode_scene_mode";

  public static boolean playBeep = false;
  public static boolean vibrate = false;
  public static boolean useAutoFocus = true;
  public static boolean disable_continuous_focus = false;
  public static boolean invert_scan = false;
  public static boolean disable_exposure = true;
  public static boolean disable_metering = true;
  public static boolean disable_barcode_scene_mode = true;
  public static String ront_light_mode = FrontLightMode.OFF.toString();
}
