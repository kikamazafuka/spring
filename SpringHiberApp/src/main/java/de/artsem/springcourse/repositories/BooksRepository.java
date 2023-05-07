package de.artsem.springcourse.repositories;

import de.artsem.springcourse.models.Book;
import de.artsem.springcourse.models.Item;
import de.artsem.springcourse.models.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Integer> {
//    List<Book> findByOwner(Person owner);


//    Optional<Book> findByBookOwner();
    List<Book> findAllByBookOwnerId(int id);

    List<Book> findAll(Sort var1);

    List<Book> findAllByNameStartingWith(String startingChar);





}
