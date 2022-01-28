package org.example.serializers;

import org.example.annotations.XmlElement;
import org.example.annotations.XmlSerializable;
import org.example.tags.FieldTag;
import org.example.tags.RootTag;
import org.example.tags.Tag;
import org.example.exceptions.XmlSerializableException;
import org.example.types.Type;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class XmlSerializer<T> {

    public  List<String> serialize(List<T> objects) throws XmlSerializableException, IllegalAccessException {
        List<String> serializeOutputList = new ArrayList<>();
        for (T obj: objects) serializeOutputList.add(serialize(obj));
        return serializeOutputList;
    }

    private String serialize(T object) throws XmlSerializableException, IllegalAccessException {
        if (!object.getClass().isAnnotationPresent(XmlSerializable.class))
            throw new XmlSerializableException(
                    String.format("The class with name %s is not annotated with @XmlSerializable", object.getClass())
            );

        List<Tag<Type.FieldType>> fieldTags = new ArrayList<>();

        for (Field field: object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(XmlElement.class)) {
                String value = field.getAnnotation(XmlElement.class).key();
                value = value.isEmpty() ? field.getName() : value;
                fieldTags.add(
                        new FieldTag(value, Optional.ofNullable(field.get(object)).map(Object::toString).orElse("null"))
                );
            }
        }

        String root = object.getClass().getAnnotation(XmlSerializable.class).key();
        root = root.isEmpty() ? object.getClass().getSimpleName() : root;

        return new RootTag(root, fieldTags.stream().map(Object::toString).collect(Collectors.joining("\n"))).toString();
    }

}
