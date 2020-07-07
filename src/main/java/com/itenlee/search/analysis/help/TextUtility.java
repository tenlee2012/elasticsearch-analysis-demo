package com.itenlee.search.analysis.help;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tenlee
 * @date 2020/6/3
 */
public class TextUtility {
    //链接符号
    private static final String[] STOP_WORD = ("!\n" + "\"\n" + "#\n" + "$\n" + "%\n" + "&\n"
            + "'\n" + "(\n" + ")\n" + "*\n" + "+\n" + ",\n" + "-\n" + ".\n" + "/\n" + ":\n" + ";\n" + "<\n" + "=\n"
            + ">\n" + "?\n" + "@\n" + "\\\n" + "^\n" + "_\n" + "`\n" + "{\n" + "|\n" + "}\n" + "~\n" + "＂\n" + "＃\n"
            + "＄\n" + "％\n" + "＆\n" + "＇\n" + "（\n" + "）\n" + "＊\n" + "＋\n" + "，\n" + "－\n" + "／\n" + "：\n"
            + "；\n" + "＜\n" + "＝\n" + "＞\n" + "＠\n" + "［\n" + "＼\n" + "］\n" + "＾\n" + "＿\n" + "｀\n" + "｛\n"
            + "｜\n" + "｝\n" + "～\n" + "｟\n" + "｠\n" + "｢\n" + "｣\n" + "､\n" + "　\n" + "、\n" + "〃\n" + "〈\n"
            + "〉\n" + "《\n" + "》\n" + "「\n" + "」\n" + "『\n" + "』\n" + "〔\n" + "〕\n" + "〖\n" + "〗\n" + "〘\n"
            + "〙\n" + "〚\n" + "〛\n" + "〜\n" + "〝\n" + "〞\n" + "〟\n" + "〰\n" + "〾\n" + "〿\n" + "–\n" + "—\n"
            + "‘\n" + "’\n" + "‛\n" + "“\n" + "”\n" + "„\n" + "‟\n" + "…\n" + "‧\n" + "﹏\n" + "﹑\n" + "﹔\n"
            + "·\n" + "！\n" + "？\n" + "｡\n" + "。\n").split("\n");
    // 用map，时间复杂度小一点
    private static final Map<String, Boolean> STOP_WORD_MAP = Stream.of(STOP_WORD).collect(Collectors.toMap(data -> data, data -> true));

    /**
     * 是否是不需要索引的词
     *
     * @param text
     * @return
     */
    public static boolean isStopWord(String text) {
        if (text == null || text.length() == 0 || isBlank(text)) {
            return true;
        }
        if (text.length() != 1) {
            return false;
        }
        return STOP_WORD_MAP.containsKey(text);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }
}
