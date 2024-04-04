package rf.aleksper.dz8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rf.aleksper.dz8.entity.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByReaderId(long readerId);

    Issue findByBookId(long bookId);
}
