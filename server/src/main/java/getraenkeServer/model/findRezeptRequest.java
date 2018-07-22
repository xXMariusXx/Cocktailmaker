package getraenkeServer.model;

public class findRezeptRequest {
    private Zutat[] zutaten;
    private SearchType searchType;

    public findRezeptRequest(Zutat[] zutaten, SearchType searchType) {
        this.zutaten = zutaten;
        this.searchType = searchType;
    }

    public SearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchType searchType) {
        this.searchType = searchType;
    }

    public Zutat[] getZutaten() {
        return zutaten;
    }

    public void setZutaten(Zutat[] zutaten) {
        this.zutaten = zutaten;
    }
}
