package com.quspacedragon.workflow.umen;

import com.quspacedragon.workflow.umen.android.AndroidUnicast;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Title: UmengService
 * <p>
 * Description:
 *
 * @author 瞿麦 <a href="mailto:qumai@2dfire.com">;
 * @version V1.0
 * @since 2017/10/10
 */
@Service
public class UmengService {
    private PushClient client = new PushClient();
    @Value("${umen.appkey}")
    private String appkey;
    @Value("${umen.message-secret}")
    private String messageSecret;
    @Value("${umen.app-master-secret}")
    private String appMasterSecret;

    public void sendAndroidUnicast(String deviceToken, String title, String text) throws Exception {
        AndroidUnicast unicast = new AndroidUnicast(appkey, appMasterSecret);
        // TODO Set your device token
        unicast.setDeviceToken(deviceToken);
        unicast.setTicker("Android unicast ticker");
        unicast.setTitle(title);
        unicast.setText(text);
        unicast.goAppAfterOpen();
        unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
        // TODO Set 'production_mode' to 'false' if it's a test device.
        // For how to register a test device, please see the developer doc.
        unicast.setProductionMode();
        // Set customized fields
//        unicast.setExtraField("test", "helloworld");
        client.send(unicast);
    }

}
