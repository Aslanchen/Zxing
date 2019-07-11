# Zxing修改版
1. 只保留扫码部分，删除其他功能
2. 修改界面扫描部分绘制，支持横竖屏。
3. 修改默认扫描长宽
4. DecodeThread中decodeFormats设置支持类型
5. 增加android6.0权限检查和提示

# 横屏修改文件
1. CameraManager.getFramingRect()
2. CameraManager.getFramingRectInPreview()
3. CameraManager.buildLuminanceSource()
4. CameraConfigurationUtils.findBestPreviewSizeValue()中screenResolution

# 长宽修改
1. CameraManager.getFramingRect()，修改成屏幕3/5，往上移动一段距离
2. CameraManager.setManualFramingRect()，往上移动一段距离

# 说明
## AmbientLightManager
处理闪光灯

## BeepManager
处理声音以及震动

# 问题反馈
邮件(122560007@163.com)
