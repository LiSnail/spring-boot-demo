package com.wulang.test;

import com.wulang.api.IPayService;
import com.wulang.constant.ShopCode;
import com.wulang.shop.PayServiceApplication;
import com.wulang.shop.pojo.TradePay;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author wulang
 * @create 2019/12/27/17:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PayServiceApplication.class)
public class PayServiceTest {

    @Autowired
    private IPayService payService;

    @Test
    public void createPayment(){
        long orderId = 351526299216515072L;
        TradePay tradePay = new TradePay();
        tradePay.setOrderId(orderId);
        tradePay.setPayAmount(new BigDecimal(880));
        payService.createPayment(tradePay);
    }

    @Test
    public void callbackPayment() throws InterruptedException, RemotingException, MQClientException, MQBrokerException, IOException {

        long payId = 352516176372441088L;
        long orderId = 351526299216515072L;

        TradePay tradePay = new TradePay();
        tradePay.setPayId(payId);
        tradePay.setOrderId(orderId);
        tradePay.setIsPaid(ShopCode.SHOP_ORDER_PAY_STATUS_IS_PAY.getCode());
        payService.callbackPayment(tradePay);

        System.in.read();

    }

}

