package ws.model;

import org.glassfish.jersey.internal.util.Base64;
import ws.repository.DBCalls;
import java.util.StringTokenizer;

public class Authentication {
    private static final String AUTORIZATION_HEADER_PREFIX = "Basic ";

    public boolean authenticate(String authToken) {
        if (authToken == null) {
            return false;
        }
        String authTokenWithoutBasicWord = authToken.replace(AUTORIZATION_HEADER_PREFIX, "");
        String decodedString = Base64.decodeAsString(authTokenWithoutBasicWord);
        StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
        String username = tokenizer.nextToken();
        String password = tokenizer.nextToken();

        if (DBCalls.authenticateUser(username, password)) {
            return true;
        }
        return false;
    }
}
