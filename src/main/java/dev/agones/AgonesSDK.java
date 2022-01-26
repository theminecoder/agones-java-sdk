package dev.agones;

import com.jaredsburrows.retrofit2.adapter.synchronous.SynchronousCallAdapterFactory;
import dev.agones.endpoints.AgonesEndpoints;
import dev.agones.endpoints.AlphaEndpoints;
import dev.agones.endpoints.Endpoints;
import dev.agones.model.GameServer;
import dev.agones.model.request.*;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class AgonesSDK {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final Retrofit retrofit;
    private final Map<Class<? extends Endpoints>, Endpoints> endpointsMap = new HashMap<>();

    private final AlphaSdk alphaSdk = new AlphaSdk(this);
    private final BetaSdk betaSdk = new BetaSdk(this);

    public AgonesSDK() {
        this(Integer.parseInt(System.getenv().getOrDefault("AGONES_SDK_HTTP_PORT", "9358")));
    }

    public AgonesSDK(int port) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(new HttpUrl.Builder().scheme("http").host("localhost").port(port).build())
                .addCallAdapterFactory(SynchronousCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @SuppressWarnings("unchecked")
    private <E extends Endpoints> E getEndpoints(Class<E> endpoints) {
        return (E) endpointsMap.computeIfAbsent(endpoints, __ -> retrofit.create(endpoints));
    }

    public AlphaSdk alpha() {
        return this.alphaSdk;
    }

    public BetaSdk beta() {
        return this.betaSdk;
    }

    public void ready() {
        getEndpoints(AgonesEndpoints.class).ready();
    }

    public void startHealthTask(int period, TimeUnit unit) {
        executorService.scheduleAtFixedRate(this::health, 0, period, unit);
    }

    public void health() {
        getEndpoints(AgonesEndpoints.class).health(Empty.empty());
    }

    public void reserve(int seconds) {
        getEndpoints(AgonesEndpoints.class).reserve(new Reservation(seconds));
    }

    public void allocate() {
        getEndpoints(AgonesEndpoints.class).allocate();
    }

    public void shutdown() {
        getEndpoints(AgonesEndpoints.class).shutdown();
    }

    public GameServer gameServer() {
        return getEndpoints(AgonesEndpoints.class).gameServer();
    }

    public void label(String key, String value) {
        getEndpoints(AgonesEndpoints.class).label(new LabelOrAnnotation(key, value));
    }

    public void annotation(String key, String value) {
        getEndpoints(AgonesEndpoints.class).annotation(new LabelOrAnnotation(key, value));
    }

    public static final class AlphaSdk {

        private final AgonesSDK sdk;

        private AlphaSdk(AgonesSDK sdk) {
            this.sdk = sdk;
        }

        public boolean playerConnect(String id) {
            return sdk.getEndpoints(AlphaEndpoints.class).playerConnect(new PlayerInfo(id)).value();
        }

        public boolean playerDisconnect(String id) {
            return sdk.getEndpoints(AlphaEndpoints.class).playerDisconnect(new PlayerInfo(id)).value();
        }

        public List<String> getConnectedPlayers() {
            return sdk.getEndpoints(AlphaEndpoints.class).getConnectedPlayers().getList();
        }

        public boolean isPlayerConnected(String player) {
            return sdk.getEndpoints(AlphaEndpoints.class).isPlayerConnected(player).value();
        }

        public int getPlayerCount() {
            return sdk.getEndpoints(AlphaEndpoints.class).getPlayerCount().getCount();
        }

        public int getPlayerCapacity() {
            return sdk.getEndpoints(AlphaEndpoints.class).getPlayerCapacity().getCount();
        }

        public void setCapacity(int capacity) {
            sdk.getEndpoints(AlphaEndpoints.class).setPlayerCapacity(new PlayerCount(capacity));
        }

    }

    public static final class BetaSdk {

        private final AgonesSDK sdk;

        private BetaSdk(AgonesSDK sdk) {
            this.sdk = sdk;
        }
    }

}
