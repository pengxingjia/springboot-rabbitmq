package im.qiaofeng.rabbitmq.rabbitmqconfig.msg;

import java.io.Serializable;
import lombok.Data;

/**
 * 消息基类
 *
 * @author qiaofeng
 */
@Data
public class Msg implements Serializable {

    private int cmd;

    private Object msg;
}