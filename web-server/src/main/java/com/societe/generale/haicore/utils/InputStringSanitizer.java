package com.societe.generale.haicore.utils;

import org.springframework.web.util.HtmlUtils;

public class InputStringSanitizer {
    public static String sanitizeInputString(String input) {
        input = input.replaceAll("\r", "").replaceAll("\n", "");
        return HtmlUtils.htmlEscape(input);
    }
}
