package feedbackservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.domain.Page;

import java.util.List;

@JsonPropertyOrder({ "total_documents", "is_first_page", "is_last_page", "documents" })
public class PaginationResponse<T> {

    @JsonProperty("total_documents")
    private long totalDocuments;

    @JsonProperty("is_first_page")
    private boolean isFirstPage;

    @JsonProperty("is_last_page")
    private boolean isLastPage;

    private List<T> documents;

    public PaginationResponse(Page<T> page) {
        this.totalDocuments = page.getTotalElements();
        this.isFirstPage = page.isFirst();
        this.isLastPage = page.isLast();
        this.documents = page.getContent();
    }

    public long getTotalDocuments() {
        return totalDocuments;
    }

    public void setTotalDocuments(long totalDocuments) {
        this.totalDocuments = totalDocuments;
    }

    @JsonProperty("is_first_page")
    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    @JsonProperty("is_last_page")
    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    public List<T> getDocuments() {
        return documents;
    }

    public void setDocuments(List<T> documents) {
        this.documents = documents;
    }
}
