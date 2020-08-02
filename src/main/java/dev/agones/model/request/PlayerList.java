package dev.agones.model.request;

import java.util.List;

public final class PlayerList {

    private List<String> list;

    public PlayerList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }
}
