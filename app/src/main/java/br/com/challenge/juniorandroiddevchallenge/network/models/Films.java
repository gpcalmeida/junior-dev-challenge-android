package br.com.radixeng.juniorandroiddevchallenge.network.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Films {

    @SerializedName("title")
    private String title;

    @SerializedName("episodeId")
    private int episodeId;

    @SerializedName("director")
    private String director;

    public Films(String title, int episodeId, String director) {
        this.title = title;
        this.episodeId = episodeId;
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public String getDirector() {
        return director;
    }

    @Override
    public String toString() {
        return "Star Wars - Episode " + episodeId + ": " + title;
    }
}
