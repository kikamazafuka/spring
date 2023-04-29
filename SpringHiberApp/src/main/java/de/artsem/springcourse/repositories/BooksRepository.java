package de.artsem.springcourse.repositories;

import de.artsem.springcourse.models.Book;
import de.artsem.springcourse.models.Item;
import de.artsem.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
//    List<Book> findByOwner(Person owner);


    List<Book> findAllByBookOwnerId(int id);
}
