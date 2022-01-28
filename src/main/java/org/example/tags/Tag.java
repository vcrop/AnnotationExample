package org.example.tags;

import org.example.types.Type;

public class Tag{

    Type type;
    String tagName;
    String body;

    public Tag(String tagName, String body, Type type) {
        this.tagName = tagName;
        this.body = body;
        this.type = type;
    }

    public static Tag rootTag(String tagName, String body) {
        return new Tag(tagName, body, new Type.RootType(){});
    }

    public static Tag fieldTag(String tagName, String body) {
        return new Tag(tagName, body, new Type.FieldType(){});
    }

    public String toString() {
        return String.format("<%s>%3$s%s%3$s<%1$s/>", tagName, body, type.delimiter());
    }

}
