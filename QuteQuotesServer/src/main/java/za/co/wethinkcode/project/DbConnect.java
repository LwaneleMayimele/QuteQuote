package za.co.wethinkcode.project;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import za.co.wethinkcode.DTO.QuteQuoteDTO;

public class DbConnect
{
    public static final String IN_MEMORY_DB_URL = "jdbc:sqlite::memory:";

    public static final String DISK_DB_URL = "jdbc:sqlite:";

    public static void main( String[] args ) {
        final DbConnect tester = new DbConnect( args );
    }

    private String dbUrl = null;

    private DbConnect( String[] args ) {
        processCmdLineArgs( args );

        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            System.out.println( "Connected to database " );
            createNewTables(connection);
//            createQoutes(connection);
//            addExistingQuotes();
            printAllData();

        }catch(SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    private JSONArray getQuotes() throws IOException, ParseException {
        JSONArray quotesJson;
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("src/main/java/QuotesJson.json");
        Object json = ((JSONObject) jsonParser.parse(reader)).get("quotes");
        quotesJson = (JSONArray) json;
        return quotesJson;
    }
    private void addExistingQuotes() throws SQLException, IOException, ParseException {
        try (final Connection connection = DriverManager.getConnection(dbUrl)) {
            JSONArray jsonArray = getQuotes();
            jsonArray.forEach(jsonData->{
                try {
                    Statement statement = connection.createStatement();
                    String  authorname = (new JSONObject((Map)jsonData).get("author")).toString();
                    String quote = (new JSONObject((Map)jsonData).get("quote")).toString();
                    statement.execute("INSERT INTO Quotes(authorName,quote) VALUES (\""+authorname+"\",\""+quote+"\");");
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            });
        }
    }

    private void processCmdLineArgs( String[] args ){
        if( args.length == 2 && args[ 0 ].equals( "-f" )){
            dbUrl = DISK_DB_URL + args[ 1 ];
        }else if( args.length == 0 ){
            dbUrl = IN_MEMORY_DB_URL;
        }else{
            throw new RuntimeException( "Illegal command-line arguments." );
        }
    }

   private void printAllData(){
       long startTime = System.nanoTime();
       List<QuteQuoteDTO> quotes = new ArrayList<>() ;
       try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
           Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Quotes");
            while (result.next()){
                QuteQuoteDTO dto = new QuteQuoteDTO();
                dto.setAuthorName(result.getString("authorName"));
                dto.setQuote(result.getString("quote"));
                quotes.add(dto);
            }
            quotes.forEach(n->{
                System.out.println("Name {"+n.getAuthorName()+"} || { "+"Quote "+n.getQuote()+" }");
            });
           long endTime   = System.nanoTime();


       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private void createNewTables(Connection connection){
        String createTable = "CREATE TABLE IF NOT EXISTS Quotes (" +
                "quoteId CHAR(36) PRIMARY KEY," +
                "authorName text," +
                "quote text);";

        try( final Statement stmt = connection.createStatement() ){
            stmt.executeUpdate(createTable);
            System.out.println( "Success creating tables" );
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    private void createQoutes(final Connection connection)
            throws SQLException
    {
        try( final Statement stmt = connection.createStatement() ){
            boolean gotAResultSet = stmt.execute(
                    "INSERT INTO Quotes(authorName,quote) VALUES (\"malt\",\"working is good\");"
            );

            if( gotAResultSet ){
                throw new RuntimeException( "Unexpectedly got a SQL results." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                    System.out.println( updateCount+" row INSERTED into Quotes" );
            }
        }
    }

    public void addData(QuteQuoteDTO quteQuote) throws SQLException {
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Quotes(authorName,quote) VALUES (\""+quteQuote.getAuthorName()+"\",\""+quteQuote.getQuote()+"\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeleteData(QuteQuoteDTO quteQuote) throws SQLException {
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Quotes(authorName,quote) VALUES (\""+quteQuote.getAuthorName()+"\",\""+quteQuote.getQuote()+"\");");
//            "DELETE FROM products WHERE id = 2"
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //how to update get data id
    public void UpdateData(QuteQuoteDTO quteQuote) throws SQLException {
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Quotes(authorName,quote) VALUES (\""+quteQuote.getAuthorName()+"\",\""+quteQuote.getQuote()+"\");");
//            "UPDATE Quotes SET  = \"Sourflat IPA\" WHERE name = \"Lemondrop IPA\""
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void readData(QuteQuoteDTO quteQuote) throws SQLException {
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO Quotes(authorName,quote) VALUES (\""+quteQuote.getAuthorName()+"\",\""+quteQuote.getQuote()+"\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}