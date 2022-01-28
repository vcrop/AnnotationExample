package org.example.tags;

import org.example.types.Type;

public class RootTag extends Tag<Type.RootType>{

    public RootTag(String tagName, String body) {
        super(tagName, body, new Type.RootType(){});
    }

}
