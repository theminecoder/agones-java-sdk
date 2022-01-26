package dev.agones.endpoints;

import dev.agones.model.GameServer;
import dev.agones.model.request.Empty;
import dev.agones.model.request.LabelOrAnnotation;
import dev.agones.model.request.Reservation;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AgonesEndpoints extends Endpoints {

    @POST("/ready")
    public Void ready();

    @POST("/health")
    public Void health(@Body Empty empty);

    @POST("/reserve")
    public Void reserve(@Body Reservation request);

    @POST("/allocate")
    public Void allocate();

    @POST("/shutdown")
    public Void shutdown();

    @GET("/gameserver")
    public GameServer gameServer();

    @POST("/metadata/label")
    public Void label(@Body LabelOrAnnotation label);

    @POST("/metadata/annotation")
    public Void annotation(@Body LabelOrAnnotation annotation);

}
