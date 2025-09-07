package feedbackservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
@Validated
public class RESTController {
    private final FeedbackRepository feedbackRepository;

    public RESTController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @PostMapping("/feedback")
    public ResponseEntity<String> postFeedback(@RequestBody Feedback feedback) {
        feedbackRepository.save(feedback);

        URI uri = UriComponentsBuilder.fromPath("/feedback/{id}")
                .buildAndExpand(feedback.getId())
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri.toString());

        return ResponseEntity.created(uri).headers(headers).body(null);
    }

    @GetMapping("feedback/{id}")
    public ResponseEntity<Optional<Feedback>> getFeedback(@PathVariable String id) {
        return feedbackRepository.findById(id).isPresent() ?
                ResponseEntity.ok().body(feedbackRepository.findById(id)) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("feedback")
    public ResponseEntity<PaginationResponse<Feedback>> getAllFeedback(PaginationRequest paginationRequest) {
        int page = paginationRequest.getPage(), perPage = paginationRequest.getPerPage();

        Pageable pageable = PageRequest.of(page - 1, perPage, Sort.by("id").descending());

        Page<Feedback> feedbackPage = feedbackRepository.findAll(pageable);

        PaginationResponse<Feedback> paginationResponse = new PaginationResponse<>(feedbackPage);

        System.out.println(page + " " + perPage);

        return ResponseEntity.ok(paginationResponse);
    }
}
