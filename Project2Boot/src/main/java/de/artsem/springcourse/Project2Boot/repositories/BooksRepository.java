package de.artsem.springcourse.Project2Boot.repositories;


import de.artsem.springcourse.Project2Boot.models.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Integer> {
//    List<Book> findByOwner(Person owner);


//    Optional<Book> findByBookOwner();
    List<Book> findAllByBookOwnerId(int id);

    List<Book> findAll(Sort var1);

    List<Book> findAllByNameStartingWith(String startingChar);





}
