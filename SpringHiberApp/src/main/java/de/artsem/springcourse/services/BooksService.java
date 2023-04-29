package de.artsem.springcourse.services;

import de.artsem.springcourse.models.Book;
import de.artsem.springcourse.models.Item;
import de.artsem.springcourse.models.Person;
import de.artsem.springcourse.repositories.BooksRepository;
import de.artsem.springcourse.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private BooksRepository booksRepository;


    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Book findOne(int id){
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

//    public List<Book> findByOwner(Person owner){
//        return booksRepository.findByOwner(owner);
//    }
    public List<Book> findAllByBookOwnerId(int id){
        return booksRepository.findAllByBookOwnerId(id);
    }
}
