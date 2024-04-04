package rf.aleksper.dz8.controllers;
/*
 * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)
 * Реализовать контроллер по управлению читателями с ручками:
 * GET    /reader/{id} - получить описание читателя,
 * DELETE /reader/{id} - удалить читателя,
 * POST   /reader - создать читателя
 */

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rf.aleksper.dz8.entity.Issue;
import rf.aleksper.dz8.entity.Reader;
import rf.aleksper.dz8.services.IssueService;
import rf.aleksper.dz8.services.ReaderService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("reader")
@RequiredArgsConstructor
public class ReadreController {
    private final ReaderService readerService;
    private final IssueService issueService;

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable long id) {
        log.info("Поступил запрос на удаление читателя: readerId={}", id);
        try {
            readerService.deleteReader(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Reader> createReader(@RequestBody ReaderRequest readerRequest) {
        log.info("Поступил запрос на добавление читателя: readerName={}", readerRequest.getReaderName());
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(readerService.addReader(readerRequest.getLogin(), readerRequest.getPassword(), readerRequest.getRole(), readerRequest.getReaderName()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Reader> infoReader(@PathVariable long id) {
        log.info("Поступил запрос на информацию о читателе: readerId={}", id);
        if (readerService.findById(id) != null) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(readerService.findById(id));
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    /*
     * 2.2 В сервис читателя добавить ручку
     * GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
     */

    @GetMapping("{id}/issue")
    public ResponseEntity<Iterable<Issue>> findBookIssueReader(@PathVariable long id) {
        log.info("Поступил запрос на информацию о читателе: readerId={}", id);
        if (readerService.findById(id) != null) {
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(issueService.findIssueForReader(id));
            } catch (NoSuchElementException e) {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
