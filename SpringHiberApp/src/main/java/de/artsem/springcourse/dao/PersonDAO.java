package de.artsem.springcourse.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import de.artsem.springcourse.models.Person;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

//    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
//        this.sessionFactory = sessionFactory;
        this.entityManager = entityManager;
    }



    @Transactional(readOnly = true)
    public void testNPlus1() {

        Session session = entityManager.unwrap(Session.class);

//        List<Person> people = session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//
//        for (Person person : people){
//            System.out.println("Person "+ person.getName() + " has items: " +person.getItemList());
//        }

        List<Person> people = session.createQuery("select p from Person p LEFT JOIN fetch p.itemList",
                Person.class).getResultList();


        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has items: " + person.getItemList());
        }
    }


//    @Transactional(readOnly = true)
//    public List<Person> index() {
//        Session session = sessionFactory.getCurrentSession();
//
//        List<Person> people = session.createQuery("select p from Person p", Person.class)
//                .getResultList();
//        return people;
//    }
//
//    @Transactional(readOnly = true)
//    public Person show(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(person);
//    }
//
//    @Transactional
//    public void update(int id, Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, id);
//        person.setName(updatedPerson.getName());
//        person.setAge(updatedPerson.getAge());
//        person.setEmail(updatedPerson.getEmail());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        session.remove(session.get(Person.class, id));
//
//
//    }
}
