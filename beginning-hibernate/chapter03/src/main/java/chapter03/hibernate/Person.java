package chapter03.hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public Person() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof chapter03.simple.Person)) return false;
        Person person = (Person) obj;
        return Objects.equals(getId(), person.getId())
                && Objects.equals(getName(), person.getName());
    }

    @Override
    public String toString() {
        return "ID: " + getId() + ", Name: " + getName();
    }
}

