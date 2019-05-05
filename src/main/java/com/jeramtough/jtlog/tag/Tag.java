package com.jeramtough.jtlog.tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2019-05-05 14:56
 * by @author JeramTough
 */
public class Tag {

    private String name;
    private static Map<String, Tag> tagMap;


    Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Tag get(String name) {
        if (tagMap == null) {
            tagMap = new HashMap<>(16);
        }
        Tag tag = tagMap.get(name);
        if (tag == null) {
            tag = new Tag(name);
            tagMap.put(name, tag);
        }
        return tag;
    }
}
