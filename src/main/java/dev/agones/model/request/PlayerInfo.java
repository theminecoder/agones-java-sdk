package dev.agones.model.request;

public final class PlayerInfo {

    private String playerID;

    public PlayerInfo(String playerID) {
        this.playerID = playerID;
    }

    public String getPlayerID() {
        return playerID;
    }
}
