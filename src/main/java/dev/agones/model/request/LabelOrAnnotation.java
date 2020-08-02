package dev.agones.model.request;

public final class LabelOrAnnotation {

    private String key;
    private String value;

    public LabelOrAnnotation(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
