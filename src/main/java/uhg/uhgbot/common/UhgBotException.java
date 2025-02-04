package uhg.uhgbot.common;

public class UhgBotException extends Exception {
    /**
     * Creates a new UhgBotException. Use this exception to throw UhgBot-related exceptions.
     * 
     * @param message The message to be displayed when the exception is thrown.
     */
    public UhgBotException(String message) {
        super(message);
    }
}
