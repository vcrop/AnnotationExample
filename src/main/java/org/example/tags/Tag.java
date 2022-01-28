package org.example.tags;

import org.example.types.Type;

public class Tag<T extends Type> {

    T type;
    String tagName;
    String body;

    public Tag(String tagName, String body, T type) {
        this.tagName = tagName;
        this.body = body;
        this.type = type;
    }

    public String toString() {
        return String.format("<%s>%3$s%s%3$s<%1$s/>", tagName, body, type.delimiter());
    }

}
