package com.enfle.android.mediacontent.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel(value = Parcel.Serialization.FIELD,
        analyze = {Images.class})
public class Images {

    @SerializedName("backdrops")
    @Expose
    List<Backdrop> backdrops = new ArrayList<>();
    @SerializedName("posters")
    @Expose
    List<Poster> posters = new ArrayList<>();

    /**
     * @return The backdrops
     */
    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    /**
     * @param backdrops The backdrops
     */
    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    /**
     * @return The posters
     */
    public List<Poster> getPosters() {
        return posters;
    }

    /**
     * @param posters The posters
     */
    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

}
