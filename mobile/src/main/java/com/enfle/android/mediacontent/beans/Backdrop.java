package com.enfle.android.mediacontent.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(value = Parcel.Serialization.FIELD,
        analyze = {Backdrop.class})
public class Backdrop {

    @SerializedName("aspect_ratio")
    @Expose
    Double aspectRatio;
    @SerializedName("file_path")
    @Expose
    String filePath;
    @SerializedName("height")
    @Expose
    Integer height;
    @SerializedName("iso_639_1")
    @Expose
    String iso6391;
    @SerializedName("vote_average")
    @Expose
    Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    Integer voteCount;
    @SerializedName("width")
    @Expose
    Integer width;

    /**
     * @return The aspectRatio
     */
    public Double getAspectRatio() {
        return aspectRatio;
    }

    /**
     * @param aspectRatio The aspect_ratio
     */
    public void setAspectRatio(Double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    /**
     * @return The filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath The file_path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return The iso6391
     */
    public String getIso6391() {
        return iso6391;
    }

    /**
     * @param iso6391 The iso_639_1
     */
    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    /**
     * @return The voteAverage
     */
    public Double getVoteAverage() {
        return voteAverage;
    }

    /**
     * @param voteAverage The vote_average
     */
    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    /**
     * @return The voteCount
     */
    public Integer getVoteCount() {
        return voteCount;
    }

    /**
     * @param voteCount The vote_count
     */
    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * @return The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

}
