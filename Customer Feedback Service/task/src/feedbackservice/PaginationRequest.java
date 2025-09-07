package feedbackservice;

public class PaginationRequest {
    private Integer page;
    private Integer perPage;

    public Integer getPage() {
        if (page == null || perPage == null) {
            return 1;
        }

        return page < 1 ? 1 : page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        if (page == null || perPage == null) {
            return 10;
        }

        if (perPage < 5 || perPage > 20) {
            return 10;
        }

        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}
