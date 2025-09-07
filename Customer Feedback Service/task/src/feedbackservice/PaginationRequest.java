package feedbackservice;

public class PaginationRequest {
    private Integer page;
    private Integer perPage;

    public Integer getPage() {
        return page != null && page > 0 ? page : 1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage != null && perPage >= 5 && perPage <= 20 ? perPage : 10;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}
