package za.co.wethinkcode.Domain;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataBaseCreator {
    
    private String dbUrl;

    public DataBaseCreator(String dbUrl) throws SQLException, IOException, ParseException{
        this.dbUrl = dbUrl;
        createNewDataBase();
        addExistingQuotes();
       
    }

    private void createNewDataBase() throws SQLException {
       
        try( final Connection connection = DriverManager.getConnection( this.dbUrl ) ){
            String createTable = "CREATE TABLE IF NOT EXISTS Quotes (" +
                    "quoteId text PRIMARY KEY," +
                    "authorName CHAR(225)," +
                    "quote text);";

            try( final Statement stmt = connection.createStatement() ){
                stmt.executeUpdate(createTable);
                System.out.println( "Success creating DataBase" );
            }catch( SQLException e ){
                System.out.println("Could not create DateBase");
                System.err.println( e.getMessage() );
            }
        }
    
    }

    public void addExistingQuotes() throws SQLException, IOException, ParseException {
        try (final Connection connection = DriverManager.getConnection(this.dbUrl)) {
            JSONArray jsonArray = getQuotes();
            jsonArray.forEach(jsonData->{
                try {
                    Statement statement = connection.createStatement();
                    String  authorname = (new JSONObject((Map)jsonData).get("author")).toString();
                    String quote = (new JSONObject((Map)jsonData).get("quote")).toString();
                    statement.execute("INSERT INTO Quotes(quoteId,authorName,quote) VALUES (\""+ UUID.randomUUID().toString() +"\",\""+authorname+"\",\""+quote+"\");");
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            });
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private JSONArray getQuotes() throws IOException,org.json.simple.parser.ParseException{
        JSONArray quotesJson;
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("/home/lwanele/problems/TestingCode/QuteQuote/QuteQuotesServer/src/main/java/QuotesJson.json");
        Object json = ((JSONObject) jsonParser.parse(reader)).get("quotes");
        quotesJson = (JSONArray) json;

        return quotesJson;
    }

    
}
