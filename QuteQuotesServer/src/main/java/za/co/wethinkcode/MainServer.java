package za.co.wethinkcode;

import io.javalin.Javalin;
import za.co.wethinkcode.Controller.QuteQuoteController;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.sql.SQLException;

public class MainServer {
    
    private final Javalin server;
    public static final String IN_MEMORY_DB_URL = "jdbc:sqlite::memory:";
    public static final String DISK_DB_URL = "jdbc:sqlite:";
    private final String url;
    
    public MainServer(String[] args) throws SQLException, IOException, ParseException, java.text.ParseException {
        server = Javalin.create(config -> config.defaultContentType = "application/json");
        httpMathods(server);
        this.url = processCmdLineArgs(args);
        new QuteQuoteController(this.url);
    }

    public static void main(String[] args) throws SQLException, IOException, ParseException, java.text.ParseException {
        MainServer server = new MainServer(args);
        server.start(5000);
    }

    private String processCmdLineArgs( String[] args ){
        String dbUrl = null;
        if( args.length == 2 && args[0].equals( "-f" )){
            dbUrl = DISK_DB_URL + args[1];
            return dbUrl;
        }else if( args.length == 0 ){
            dbUrl = IN_MEMORY_DB_URL;
            return dbUrl;
        }else{
            throw new RuntimeException( "Illegal command-line arguments." );
        }
    }

    private void httpMathods(Javalin server){
        this.server.get("/quotes", QuteQuoteController::getAllData);
        this.server.get("/quote/{id}", QuteQuoteController::getQuote);
        this.server.post("/quotes", QuteQuoteController::create);
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }

} 
