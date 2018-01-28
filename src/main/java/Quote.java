import com.google.gson.Gson;

@SuppressWarnings("unused")
class Quote {
    private String quote;
    private String author;
    private String source;

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getQuote(){
        return this.quote;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
