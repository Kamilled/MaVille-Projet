package com.example.prototype;

import java.util.List;

public class Entrave {
    private String travailAssocié;
    private List<String> rues;

    public Entrave(String travailAssocié, List<String> rues) {
        this.travailAssocié = travailAssocié;
        this.rues = rues;
    }

    public String getTravailAssocié() {
        return travailAssocié;
    }

    public List<String> getRues() {
        return rues;
    }

    public void setTravailAssocié(String travailAssocié) {
        this.travailAssocié = travailAssocié;
    }

    public void setRues(List<String> rues) {
        this.rues = rues;
    }
}
