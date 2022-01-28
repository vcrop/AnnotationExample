package org.example.serializers;

import org.example.annotations.XmlElement;
import org.example.annotations.XmlSerializable;
import org.example.exceptions.XmlSerializableException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

        StringBuilder serializeOutput = new StringBuilder();

        String root = object.getClass().getAnnotation(XmlSerializable.class).key();

        serializeOutput.append(String.format("<%s>%n", root.isEmpty() ? object.getClass().getSimpleName() : root));

        for (Field field: object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(XmlElement.class)) {
                String value = field.getAnnotation(XmlElement.class).key();
                serializeOutput.append(String.format("<%s>%s<%1$s/>%n", value.isEmpty() ? field.getName() : value,
                        field.get(object)));
            }
        }

        serializeOutput.append(String.format("<%s/>", root.isEmpty() ? object.getClass().getSimpleName() : root));

        return serializeOutput.toString();
    }

}
