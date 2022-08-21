
package sk.tuke.gamestudio.logicalmazes.server.webservice.seviceScore;


public class ScoreClientException extends RuntimeException {
    public ScoreClientException(String message) {
        super(message);
    }

    public ScoreClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScoreClientException(Throwable cause) {
        super(cause);
    }
}
