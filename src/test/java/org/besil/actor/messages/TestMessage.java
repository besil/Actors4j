package org.besil.actor.messages;

import java.util.List;

/**
 * Created by besil on 9/22/15.
 */
public class TestMessage {
    private String name, surname;
    private int age;
    private List<String> hobbies;

    public TestMessage(String name, String surname, int age, List<String> hobbies) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.hobbies = hobbies;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<String> getHobbies() {
        return hobbies;
    }
}
