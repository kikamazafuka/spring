package de.semart.sprigcourse.dao;

import de.semart.sprigcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT,"artur", 10, "uncl@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"angela", 10, "uncl@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT,"natallia", 10, "uncl@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "nadia", 10, "uncl@gmail.com"));

    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person ->person.getId()==id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatedPerson){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());

    }

    public void delete (int id){
        people.removeIf(person -> person.getId()==id);
    }
}
