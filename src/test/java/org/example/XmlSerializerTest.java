package org.example;

import org.example.exceptions.XmlSerializableException;
import org.example.serializers.XmlSerializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class XmlSerializerTest {

    @Test
    public void serializeTest() throws XmlSerializableException, IllegalAccessException {
        XmlSerializer<Person> personXmlSerializer = new XmlSerializer<>();

        Person person = new Person("Ivan", "Ivanov", "London");
        Person person2 = new Person("Oleg", "Kostolom", "Vienna");

        List<String> stringList = personXmlSerializer.serialize(List.of(person, person2));

        Assertions.assertEquals(stringList,
                List.of(
                        "<Person>\n" +
                                "<firstName>Ivan<firstName/>\n" +
                                "<lastName>Ivanov<lastName/>\n" +
                                "<address>London<address/>\n" +
                                "<Person/>",
                        "<Person>\n" +
                                "<firstName>Oleg<firstName/>\n" +
                                "<lastName>Kostolom<lastName/>\n" +
                                "<address>Vienna<address/>\n" +
                                "<Person/>")
        );
    }

}
