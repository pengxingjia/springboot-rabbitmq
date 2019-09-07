package im.qiaofeng.rabbitmq.rabbitmqconfig.util;

import java.util.UUID;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return !(str != null && !"".equals(str.trim()));
    }
    /**
     * 判断null和空字符串
     */
    public static boolean isNotEmpty(String str) {
        return !(str == null || "".equals(str));
    }

    /**
     * 获取32位随机字符
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().toLowerCase();
        return uuid.substring(0, 32);
    }
}
