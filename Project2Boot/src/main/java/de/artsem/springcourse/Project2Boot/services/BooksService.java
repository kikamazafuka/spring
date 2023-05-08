package de.artsem.springcourse.Project2Boot.services;


import de.artsem.springcourse.Project2Boot.models.Book;
import de.artsem.springcourse.Project2Boot.models.Person;
import de.artsem.springcourse.Project2Boot.repositories.BooksRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public List<Book> findAllBooksWithSorting(String sortingField){
        return booksRepository.findAll(Sort.by(Sort.Direction.ASC,
                sortingField));
    }

    public List<Book> findAllWithPagination(int page, int personPerPage){
        return booksRepository.findAll(PageRequest.of(page, personPerPage))
                .getContent();
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
    public void saveAll(List<Book> books){
        booksRepository.saveAll(books);
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

//    public List<Book> findAllByBookOwnerId(int id){
////        final long tenDaysInMs = 864000000;
////        final long currentTime = new Date().getTime();
////        //boolean outOfTime = false;
////        List<Book> foundedBooks = booksRepository.findAllByBookOwnerId(id);
////        for (Book book : foundedBooks){
////            long assignedTime = book.getAssignedAt().getTime();
////            long restrictedTime = currentTime - assignedTime;
////           // long currentTimes = new Date().setTime(restrictedTime);
////            if (restrictedTime>tenDaysInMs){
////              book.setOutOfTime(true);
////            }
////        }
//        //TODO check assigned time
//        // long assignedTime = book.getAssignedAt().getTime();
//        return booksRepository.findAllByBookOwnerId(id);
//    }

    @Transient
    public List<Book> guessOutOfTime(int id){
        final long days = 10;
        final long currentDay = LocalDateTime.now().getDayOfYear();
        List<Book> foundedBooks = booksRepository.findAllByBookOwnerId(id);
        for (Book book : foundedBooks){
            long assignedDay = book.getAssignedAt().getDayOfYear();
            long restrictedDays = currentDay - assignedDay;
            if (restrictedDays>days){
                book.setOutOfTime(true);
            }
        }
        return foundedBooks;
    }

    public List<Book> findAllByNameStartingWith(String startingChar){
        return booksRepository.findAllByNameStartingWith(startingChar);
    }

    @Transactional
    public void assignPersonToBook(int id, Person assignedPerson){
        Optional<Book> bookOptional = booksRepository.findById(id);
        if (bookOptional.isPresent()){
            boolean outOfTime = false;
            Book book = bookOptional.get();
            book.setAssignedAt(LocalDateTime.now());

            book.setBookOwner(assignedPerson);
            booksRepository.save(book);
        }
    }


    @Transactional
    public void releaseBook(int id) {
        Optional<Book> bookOptional = booksRepository.findById(id);
        if (bookOptional.isPresent()){
            Book book = bookOptional.get();
            book.setBookOwner(null);
            book.setAssignedAt(null);
            booksRepository.save(book);
        }
    }
}
