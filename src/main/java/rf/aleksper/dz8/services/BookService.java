package rf.aleksper.dz8.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rf.aleksper.dz8.entity.Book;
import rf.aleksper.dz8.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    public Book addBook(String name) {
        return bookRepository.save(new Book(name));
    }

    public void deleteBook(long id) {
        if (bookRepository.findById(id) == null) {
            log.info("Не удалось найти книгу с id " + id);
            throw new NoSuchElementException("Не удалось найти книгу с id " + id);
        }

        bookRepository.delete(bookRepository.findById(id).orElse(null));
    }

    public Book findById(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
}
