package com.enfle.android.mediacontent.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel(value = Parcel.Serialization.FIELD,
        analyze = {Credits.class})
public class Credits {

    @SerializedName("cast")
    @Expose
    List<Cast> cast = new ArrayList<>();
    @SerializedName("crew")
    @Expose
    List<Crew> crew = new ArrayList<>();

    /**
     * @return The cast
     */
    public List<Cast> getCast() {
        return cast;
    }

    /**
     * @param cast The cast
     */
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    /**
     * @return The crew
     */
    public List<Crew> getCrew() {
        return crew;
    }

    /**
     * @param crew The crew
     */
    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

}
