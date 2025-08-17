package com.handoff.util;

import org.apache.commons.text.StringEscapeUtils;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class SanitizeUtils {
    private SanitizeUtils() {}

    // Output escaping (use at response time)
    public static String html(String s) {
        return s == null ? null : StringEscapeUtils.escapeHtml4(s);
    }

    public static Set<String> htmlSet(Set<String> set) {
        if (set == null) return null;
        return set.stream().map(SanitizeUtils::html).collect(Collectors.toSet());
    }

    // Input cleaning (use at request time)
    private static final Pattern SCRIPT_TAG = Pattern.compile("(?is)<script.*?>.*?</script>");
    private static final Pattern IFRAME_TAG = Pattern.compile("(?is)<iframe.*?>.*?</iframe>");
    private static final Pattern OBJECT_TAG = Pattern.compile("(?is)<object.*?>.*?</object>");
    private static final Pattern EMBED_TAG = Pattern.compile("(?is)<embed.*?>.*?</embed>");
    private static final Pattern EVENT_HANDLERS = Pattern.compile("(?i)on\\w+\\s*=\\s*\"[^\"]*\"|(?i)on\\w+\\s*=\\s*'[^']*'|(?i)on\\w+\\s*=\\s*[^\\s>]*");
    private static final Pattern JAVASCRIPT_PROTO = Pattern.compile("(?i)javascript:");

    public static String cleanInput(String s) {
        if (s == null) return null;
        String out = s;
        out = SCRIPT_TAG.matcher(out).replaceAll("");
        out = IFRAME_TAG.matcher(out).replaceAll("");
        out = OBJECT_TAG.matcher(out).replaceAll("");
        out = EMBED_TAG.matcher(out).replaceAll("");
        out = EVENT_HANDLERS.matcher(out).replaceAll("");
        out = JAVASCRIPT_PROTO.matcher(out).replaceAll("");
        return out.trim();
    }

    public static Set<String> cleanInputSet(Set<String> set) {
        if (set == null) return null;
        return set.stream().map(SanitizeUtils::cleanInput).collect(Collectors.toSet());
    }
}
