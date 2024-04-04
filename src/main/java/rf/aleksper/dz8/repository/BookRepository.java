package rf.aleksper.dz8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rf.aleksper.dz8.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
