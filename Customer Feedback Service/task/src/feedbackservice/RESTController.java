package feedbackservice;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Controller
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
    public ResponseEntity<Iterable<Feedback>> getAllFeedback() {
        return ResponseEntity.ok().body(feedbackRepository.findAll(Sort.by("id").descending()));
    }
}
