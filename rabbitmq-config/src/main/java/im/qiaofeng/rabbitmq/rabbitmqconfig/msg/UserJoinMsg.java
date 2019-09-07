package im.qiaofeng.rabbitmq.rabbitmqconfig.msg;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 参会人签到消息体
 *
 * @author qiaofeng
 */
@Data
public class UserJoinMsg implements Serializable {

    private String meetingId;

    private String openId;//改变状态人的openId

    private int attendStatus;//参加状态（0待参加，1确认参加、2不参加即请假

    private String leaveReason;//请假理由

    private List<String> toUserList;

}