package org.example.tags;

import org.example.types.Type;

public class FieldTag extends Tag<Type.FieldType>{
    public FieldTag(String tagName, String body) {
        super(tagName, body, new Type.FieldType(){});
    }
}
