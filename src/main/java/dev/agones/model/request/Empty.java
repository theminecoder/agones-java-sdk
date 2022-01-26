package dev.agones.model.request;

public class Empty {

    private static final Empty INSTANCE = new Empty();

    public static Empty empty() {
        return INSTANCE;
    }

    private Empty() {}
}
