package za.co.wethinkcode.DTO;

public class QuteQuoteDTO {
    private String id;
    private String quote;
    private String authorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String text) {
        this.quote = text;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String name) {
        this.authorName = name;
    }

    /**
     * Use this convenient factory method to create a new quote.
     * Warning the ID will be null!
     * @param quote text of the quote
     * @param authorName  the name of the person that said the text
     * @return a Quote object
     */
    public static QuteQuoteDTO create(String quote, String authorName) {
        QuteQuoteDTO quteQuote = new QuteQuoteDTO();
        quteQuote.setQuote(quote);
        quteQuote.setAuthorName(authorName);
        return quteQuote;
    }
}
