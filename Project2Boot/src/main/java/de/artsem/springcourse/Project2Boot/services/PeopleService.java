package de.artsem.springcourse.Project2Boot.services;


import de.artsem.springcourse.Project2Boot.models.Mood;
import de.artsem.springcourse.Project2Boot.models.Person;
import de.artsem.springcourse.Project2Boot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public List<Person> findAllWithPagination(int page, int personPerPage){
        return peopleRepository.findAll(PageRequest.of(page, personPerPage))
                .getContent();
    }

    public List<Person> findAll(String sortBy){
        return peopleRepository.findAll(Sort.by(sortBy));
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        person.setMood(Mood.CALM);
        person.setCreatedAt(new Date());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public Optional <Person> findByEmail(String email){
        Optional<Person> foundPerson = peopleRepository.findByEmail(email);
        return foundPerson;
    }

    public void test(){
        System.out.println("Testing here with debug. Inside Hibernate transaction");
    }

    public  Optional<Person> findBookOwner(int id){
        return peopleRepository.findBookOwner(id);
    }

    @Transactional
    public void assignPersonToBook(int id, Person assignedPerson){
        assignedPerson.setId(id);
        peopleRepository.save(assignedPerson);
    }
}
