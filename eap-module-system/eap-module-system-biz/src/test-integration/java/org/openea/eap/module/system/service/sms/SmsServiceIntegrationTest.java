package org.openea.eap.module.system.service.sms;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ThreadUtil;
import org.openea.eap.framework.common.enums.UserTypeEnum;
import org.openea.eap.framework.sms.config.EapSmsAutoConfiguration;
import org.openea.eap.module.system.test.BaseDbAndRedisIntegrationTest;
import org.openea.eap.module.system.mq.consumer.sms.SmsSendConsumer;
import org.openea.eap.module.system.mq.producer.sms.SmsProducer;
import org.openea.eap.module.system.service.user.AdminUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// TODO @芋艿：需要迁移
@Import({EapSmsAutoConfiguration.class,
        SmsChannelServiceImpl.class, SmsSendServiceImpl.class, SmsTemplateServiceImpl.class, SmsLogServiceImpl.class,
        SmsProducer.class, SmsSendConsumer.class})
public class SmsServiceIntegrationTest extends BaseDbAndRedisIntegrationTest {

    @Resource
    private SmsSendServiceImpl smsService;
    @Resource
    private SmsChannelServiceImpl smsChannelService;

    @MockBean
    private AdminUserService userService;

    @Test
    public void testSendSingleSms_aliyunSuccess() {
        // 参数准备
        String mobile = "15601691399";
        Long userId = 1L;
        Integer userType = UserTypeEnum.ADMIN.getValue();
        String templateCode = "test_02";
        Map<String, Object> templateParams = MapUtil.<String, Object>builder()
                .put("code", "1234").build();
        // 调用
        smsService.sendSingleSms(mobile, userId, userType, templateCode, templateParams);

        // 等待 MQ 消费
        ThreadUtil.sleep(1, TimeUnit.HOURS);
    }

//    @Test
//    public void testDoSendSms() {
//        // 等待 MQ 消费
//        ThreadUtil.sleep(1, TimeUnit.HOURS);
//    }

}
