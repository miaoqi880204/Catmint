package org.catmint.core.tools.common;

/**
 * 消息格式化 工具类
 *
 * @author Shuo Xiang
 */
public final class MessageFormatUtils {

    private static final char[] DEFAULT_PLACEHOLDER = {'{', '}'};

    private MessageFormatUtils() {
    }

    /**
     * 格式化消息，占位符：<b>{}</b>
     *
     * @param message
     * @param params
     * @return
     */
    public static String format(String message, Object... params) {
        StringBuilder result = new StringBuilder();

        int msgLen = message.length();
        int pramsLen = params.length;

        int from = 0, to = 0, paramIdx = 0;
        for (; to < msgLen; to++) {
            if (paramIdx >= pramsLen) {
                result.append(message, from = to, msgLen);
                break;
            }

            char c = message.charAt(to);
            if (c == DEFAULT_PLACEHOLDER[0] && to + 1 < msgLen && message.charAt(to + 1) == DEFAULT_PLACEHOLDER[1]) {
                result.append(message, from, to);
                result.append(params[paramIdx++]);
                from = ++to + 1;
            }
        }

        if (from < to) {
            result.append(message, from, to);
        }

        return result.toString();
    }

}
