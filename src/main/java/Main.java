import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) throws InterruptedException {
        String baseURL = "https://www.goodreads.com/quotes/tag/books?page=";
        ArrayList<Quote> quotesArrayList = new ArrayList<Quote>();
        int numPages = 100;
        for (int i = 1; i <= numPages; i++) {
            try {
                String url = baseURL + i;
                Document doc = Jsoup.connect(url).get();
                Elements quotes = doc.getElementsByClass("quoteText");
                for (Element quoteElement : quotes) {
                    String[] quoteArray = quoteElement.text().split("―");
                    Quote quote = new Quote();
                    String quoteText = quoteArray[0].trim();
                    String quoteAuthor = quoteArray[1].trim();
                    quote.setQuote(quoteText);
                    quote.setAuthor(quoteAuthor);
                    quote.setSource(url);
                    quotesArrayList.add(quote);
                    System.out.println("Quote added: " + quote.getQuote() + " " + quote.getAuthor());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(4000);
        }


        String filename = "quotes.json";
        try {
            File file = new File(filename);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(quotesArrayList.toString());
            fileWriter.flush();
            fileWriter.close();
            System.out.println("Done: " + filename + " file created with " + quotesArrayList.size() + " quotes.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
