package ws.model;

import org.glassfish.jersey.internal.util.Base64;
import ws.repository.DBCalls;

import java.util.StringTokenizer;

/**
 * A class that contains method to authenticates user credentials
 * against the database.
 *
 * @author Zhasmina Stoyanova
 * @version 1.0 August 2018
 */
public class Authentication {
    private static final String AUTORIZATION_HEADER_PREFIX = "Basic ";

    /**
     * Authenticates user's credentials
     * against the database
     */
    public boolean authenticate(String authToken) {
        //checks if authentication token is null
        if (authToken == null) {
            return false;
        }
        //the word Basic is not needed
        String authTokenWithoutBasicWord = authToken.replace(AUTORIZATION_HEADER_PREFIX, "");
        //decode the authentication token
        String decodedString = Base64.decodeAsString(authTokenWithoutBasicWord);
        StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
        //gets the username and password as next tokens from the tokenizer
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        //validates the user against the database
        if (DBCalls.authenticateUser(username, password)) {
            return true;
        }
        return false;
    }
}
