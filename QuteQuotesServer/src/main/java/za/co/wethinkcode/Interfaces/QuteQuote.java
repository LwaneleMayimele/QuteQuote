package za.co.wethinkcode.Interfaces;
import java.sql.SQLException;
import java.util.List;

import za.co.wethinkcode.DTO.QuteQuoteDTO;

public interface QuteQuote {

    QuteQuoteDTO getQuote(String id);

    public void setDbUrl(String dbUrl);

    List<QuteQuoteDTO> getAllQuotes();

    boolean DeleteQuote(QuteQuoteDTO quote) throws SQLException;

    void AddQuote(QuteQuoteDTO quote);

    void updateQuote(QuteQuoteDTO quote);
}
