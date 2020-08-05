package dev.agones.endpoints;

import dev.agones.model.request.BoolResponse;
import dev.agones.model.request.PlayerCount;
import dev.agones.model.request.PlayerInfo;
import dev.agones.model.request.PlayerList;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AlphaEndpoints extends Endpoints {

    @POST("/alpha/player/connect")
    public BoolResponse playerConnect(PlayerInfo info);

    @POST("/alpha/player/disconnect")
    public BoolResponse playerDisconnect(PlayerInfo info);

    @GET("/alpha/player/connected")
    public PlayerList getConnectedPlayers();

    @GET("/alpha/player/connected/{player}")
    public BoolResponse isPlayerConnected(@Path("player") String playerId);

    @GET("/alpha/player/capacity")
    public PlayerCount getPlayerCount();

    @GET("/alpha/player/capacity")
    public PlayerCount getPlayerCapacity();

    @POST("/alpha/player/capacity")
    public Void setPlayerCapacity(PlayerCount count);

}
