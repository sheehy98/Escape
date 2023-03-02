package escape.observer;

import escape.required.GameObserver;

public class GameObserverImpl implements GameObserver{
    String message;

    @Override
    public void notify(String message) {
        this.message = message;
    }

    @Override
    public void notify(String message, Throwable cause) {
        this.message = (message + ": " + cause.toString());
    }

    public String getMessage() {
        return message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
