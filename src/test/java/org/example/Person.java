package org.example;

import org.example.annotations.XmlElement;
import org.example.annotations.XmlSerializable;

@XmlSerializable(key = "Person")
public class Person {

    @XmlElement(key = "firstName")
    private String name;
    @XmlElement(key = "lastName")
    private String surname;
    @XmlElement
    private String address;

    public Person(String name, String surname, String address) {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

}
