package de.artsem.springcourse.Project2Boot.util;



import de.artsem.springcourse.Project2Boot.models.Book;
import de.artsem.springcourse.Project2Boot.services.BooksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BooksService booksService;

    public BookValidator(BooksService booksService ) {
        this.booksService = booksService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Book book = (Book) target;
    }
}
