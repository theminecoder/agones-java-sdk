package dev.agones.model.request;

public final class Reservation {

    private final int seconds;

    public Reservation(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
