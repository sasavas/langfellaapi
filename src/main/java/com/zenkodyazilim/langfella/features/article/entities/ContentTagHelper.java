package com.zenkodyazilim.langfella.features.article.entities;

import java.util.Map;

public class ContentTagHelper {
    private static final Map<String, ContentTag> TAG_MAP = Map.ofEntries(
            Map.entry("h1", ContentTag.H1),
            Map.entry("h2", ContentTag.H2),
            Map.entry("h3", ContentTag.H3),
            Map.entry("h4", ContentTag.H4),
            Map.entry("h5", ContentTag.H5),
            Map.entry("h6", ContentTag.H6),
            Map.entry("p", ContentTag.P),
            Map.entry("span", ContentTag.P),
            Map.entry("strong", ContentTag.P),
            Map.entry("b", ContentTag.P),
            Map.entry("i", ContentTag.P),
            Map.entry("blockquote", ContentTag.P),
            Map.entry("img", ContentTag.IMG)
    );

    public static ContentTag getContentTagFromHtmlTagString(String htmlTagString) {
        ContentTag tag = TAG_MAP.get(htmlTagString.toLowerCase());
        if (tag == null) {
            throw new IllegalArgumentException("Not a valid HTML tag for h1-h6, p, and img");
        }
        return tag;
    }

    public static boolean isTitleTag(String htmlTag) {
        return isTitleTag(getContentTagFromHtmlTagString(htmlTag));
    }

    public static boolean isTitleTag(ContentTag tag) {
        return switch (tag) {
            case H1, H2, H3, H4, H5, H6 -> true;
            default -> false;
        };
    }
}
