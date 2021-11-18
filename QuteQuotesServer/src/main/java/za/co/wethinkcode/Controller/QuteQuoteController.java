package za.co.wethinkcode.Controller;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;
import za.co.wethinkcode.Model.DataBaseCommunication;
import za.co.wethinkcode.DTO.QuteQuoteDTO;
import za.co.wethinkcode.Interfaces.QuteQuote;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.UUID;

public class QuteQuoteController {
    static QuteQuote dataBase;
    public QuteQuoteController(String dataBaseUrl) throws SQLException, IOException, ParseException{
        dataBase = new DataBaseCommunication(dataBaseUrl);
    }
   
    public static  void getAllData(Context context){
        dataBase.getAllQuotes().forEach(n-> System.out.println("This is a qoute >>> " + n.getQuote()));
        context.json(dataBase.getAllQuotes());
    }

    public static  void getQuote(Context context){
        String id = context.pathParamAsClass("id",String.class).get();
        QuteQuoteDTO  quote = dataBase.getQuote(id);
        if(quote.equals(null)){
            throw new NotFoundResponse("Quote not found: " + id);
        }
        context.json("lwanele:all qoute");
    }

    public static  void removeQuote(Context context) throws SQLException {
        String id = context.pathParamAsClass("id",String.class).get();
        String quote = context.req.getParameter("quote");
        String authorName = context.req.getParameter("authorName");
        QuteQuoteDTO createQuote = new QuteQuoteDTO();
        createQuote.setId(id);
        createQuote.setAuthorName(authorName);
        createQuote.setQuote(quote);
        dataBase.updateQuote(createQuote);
        boolean result = dataBase.DeleteQuote(createQuote);
        if (result == false) {
            throw new NotFoundResponse("Quote not deleted Id not found: " + id);
        }
        context.json("Deleted");
    }

    public static void addQuote(Context context){
        String quote = context.req.getParameter("quote");
        String authorName = context.req.getParameter("authorName");
        QuteQuoteDTO requestQuote = new QuteQuoteDTO();
        requestQuote.setId(UUID.randomUUID().toString());
        requestQuote.setQuote(quote);
        requestQuote.setAuthorName(authorName);
        dataBase.AddQuote(requestQuote);
        context.json("Quote Added!");
    }

    public static void updateQuote(Context context){
        String id = context.pathParamAsClass("id",String.class).get();
        String quote = context.req.getParameter("quote");
        String authorName = context.req.getParameter("authorName");
        QuteQuoteDTO createQuote = new QuteQuoteDTO();
        createQuote.setId(id);
        createQuote.setAuthorName(authorName);
        createQuote.setQuote(quote);
        dataBase.updateQuote(createQuote);
        context.json("updated!...");
    }

    public static void create(Context context){
        QuteQuoteDTO quote = context.bodyAsClass(QuteQuoteDTO.class);
        dataBase.AddQuote(quote);
        context.status(HttpCode.CREATED);
        context.json("Created new data");
    }

}
