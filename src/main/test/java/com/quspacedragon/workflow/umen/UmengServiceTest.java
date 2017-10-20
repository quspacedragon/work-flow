package com.quspacedragon.workflow.umen;

import com.quspacedragon.workflow.service.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by quspacedragon on 2017/10/13.
 */
public class UmengServiceTest extends BaseTest {
    @Resource
    UmengService umengService;

    @Test
    public void sendAndroidUnicast() throws Exception {
        umengService.sendAndroidUnicast("AgRIpXbg7NdRX2pItc6yy3yX-dlwtRtmKrRK37pP1EGY", "订单变更通知", "订单变更了");

    }
}