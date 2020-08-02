package dev.agones.endpoints;

import dev.agones.model.GameServer;
import dev.agones.model.request.LabelOrAnnotation;
import dev.agones.model.request.Reservation;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AgonesEndpoints extends Endpoints {

    @POST("/ready")
    public void ready();

    @POST("/health")
    public void health();

    @POST("/reserve")
    public void reserve(@Body Reservation request);

    @POST("/allocate")
    public void allocate();

    @POST("/shutdown")
    public void shutdown();

    @GET("/gameserver")
    public GameServer gameServer();

    @POST("/metadata/label")
    public void label(@Body LabelOrAnnotation label);

    @POST("/metadata/annotation")
    public void annotation(@Body LabelOrAnnotation annotation);

}
