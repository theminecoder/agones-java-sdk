package dev.agones.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GameServer {

    private Meta objectMeta;
    private Spec spec;
    private Status status;

    public Meta getObjectMeta() {
        return objectMeta;
    }

    public Spec getSpec() {
        return spec;
    }

    public Status getStatus() {
        return status;
    }

    public static class Meta {

        private String name;
        private String namespace;
        private String uid;
        private String resourceVersion;
        private int generation;
        private Instant creationTimestamp;
        private Instant deletionTimestamp;
        private Map<String, String> annotations = new HashMap<>();
        private Map<String, String> labels = new HashMap<>();

        public String getName() {
            return name;
        }

        public String getNamespace() {
            return namespace;
        }

        public String getUid() {
            return uid;
        }

        public String getResourceVersion() {
            return resourceVersion;
        }

        public int getGeneration() {
            return generation;
        }

        public Instant getCreationTimestamp() {
            return creationTimestamp;
        }

        public Instant getDeletionTimestamp() {
            return deletionTimestamp;
        }

        public Map<String, String> getAnnotations() {
            return annotations;
        }

        public Map<String, String> getLabels() {
            return labels;
        }
    }

    public static class Spec {

        private Health health;

        public Health getHealth() {
            return health;
        }

        public static class Health {

            private boolean disabled;
            private int periodSeconds;
            private int failureThreshold;
            private int initialDelaySeconds;

            public boolean isDisabled() {
                return disabled;
            }

            public int getPeriodSeconds() {
                return periodSeconds;
            }

            public int getFailureThreshold() {
                return failureThreshold;
            }

            public int getInitialDelaySeconds() {
                return initialDelaySeconds;
            }
        }

    }

    public static class Status {

        private String state;
        private String address;
        private List<Port> ports;

        public String getState() {
            return state;
        }

        public String getAddress() {
            return address;
        }

        public List<Port> getPorts() {
            return ports;
        }

        public static class Port {

            private String name;
            private int port;

            public String getName() {
                return name;
            }

            public int getPort() {
                return port;
            }
        }

    }

}
