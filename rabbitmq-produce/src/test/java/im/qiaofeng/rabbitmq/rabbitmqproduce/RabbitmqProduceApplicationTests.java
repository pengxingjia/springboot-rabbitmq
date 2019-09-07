package im.qiaofeng.rabbitmq.rabbitmqproduce;

import im.qiaofeng.rabbitmq.rabbitmqconfig.msg.Msg;
import im.qiaofeng.rabbitmq.rabbitmqconfig.msg.UserJoinMsg;
import im.qiaofeng.rabbitmq.rabbitmqconfig.util.StringUtil;
import im.qiaofeng.rabbitmq.rabbitmqproduce.produce.ProducerInter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqProduceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    @Qualifier(value = "produceImpl")
    ProducerInter producerInter;

    @Test
    public void produceDirectSendMsg() {
        Msg msg = new Msg();
        UserJoinMsg userJoinMsg = new UserJoinMsg();
        userJoinMsg.setAttendStatus(1);
        userJoinMsg.setLeaveReason("乔峰的消息留言");
        userJoinMsg.setMeetingId("80082082820");
        userJoinMsg.setOpenId("xxxxx");

        List<String> toUserList = new ArrayList<String>();
        toUserList.add("111111111");
        toUserList.add("aaaaaaaaaá");

        userJoinMsg.setToUserList(toUserList);
        msg.setMsg(userJoinMsg);
        for (int i=0 ; i <6; i++) {
            //消息id
            CorrelationData correlationId = new CorrelationData(StringUtil.getUuid());
            producerInter.sendDirectMsg("testExchange", "testRouting1", msg, correlationId);

           // producerInter.sendDirectMsg("testExchange", "testRouting2", msg, correlationId);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void produceFanoutSendMsg() {
        Msg msg = new Msg();
        UserJoinMsg userJoinMsg = new UserJoinMsg();
        userJoinMsg.setAttendStatus(1);
        userJoinMsg.setLeaveReason("乔峰的消息留言");
        userJoinMsg.setMeetingId("80082082820");
        userJoinMsg.setOpenId("xxxxx");

        List<String> toUserList = new ArrayList<String>();
        toUserList.add("111111111");
        toUserList.add("aaaaaaaaaá");

        userJoinMsg.setToUserList(toUserList);
        msg.setMsg(userJoinMsg);
        for (int i=0 ; i <6; i++) {
            //消息id
            producerInter.sendFanoutMsg("testFanoutExchange", msg);

            // producerInter.sendDirectMsg("testExchange", "testRouting2", msg, correlationId);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
