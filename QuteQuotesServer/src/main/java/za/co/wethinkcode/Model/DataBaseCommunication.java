package za.co.wethinkcode.Model;
import za.co.wethinkcode.DTO.QuteQuoteDTO;
import za.co.wethinkcode.Domain.DataBaseCreator;
import za.co.wethinkcode.Interfaces.QuteQuote;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataBaseCommunication implements  QuteQuote{
    private String dbUrl = null;
    
    public DataBaseCommunication(String url) throws SQLException, IOException, ParseException{
        this.dbUrl = url;
        new DataBaseCreator(dbUrl);
    }

    @Override
    public QuteQuoteDTO getQuote(String id) {
        return null;
    }

    @Override
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
    @Override
    public List<QuteQuoteDTO> getAllQuotes() {
        List<QuteQuoteDTO> quotes = new ArrayList<>();
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Quotes");
            while (result.next()){
                QuteQuoteDTO dto = new QuteQuoteDTO();
                dto.setQuote(result.getString("quote"));
                dto.setAuthorName(result.getString("authorName"));
                dto.setId(result.getString("quoteId"));
                quotes.add(dto);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return quotes;
    }

    @Override
    public boolean DeleteQuote(QuteQuoteDTO quoteDTO) {
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            statement.execute("DELETE * FROM Quotes WHERE quoteId =\""+quoteDTO.getId());
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    //set your id in the client

    @Override
    public void AddQuote(QuteQuoteDTO quote) {
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            quote.setId(UUID.randomUUID().toString());
            statement.execute("INSERT INTO Quotes(quoteId,authorName,quote) VALUES (\""+quote.getId()+"\",\""+quote.getAuthorName()+"\",\""+quote.getQuote()+"\");");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuote(QuteQuoteDTO quote) {
        String statment = "UPDATE Quotes SET Quotes =\""+quote.getAuthorName()+",\""+quote.getQuote()+"\""+","+ "WHERE quoteId = "+"\""+quote.getId()+"\"";
        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            Statement statement = connection.createStatement();
            statement.execute(statment);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
