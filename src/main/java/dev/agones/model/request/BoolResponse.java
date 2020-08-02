package dev.agones.model.request;

public final class BoolResponse {

    private boolean bool;

    public BoolResponse(boolean bool) {
        this.bool = bool;
    }

    public boolean value() {
        return bool;
    }
}
