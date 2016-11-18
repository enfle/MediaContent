
package com.km2labs.mediacontent.common.movie.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel(value = Parcel.Serialization.FIELD,
        analyze = {Reviews.class})
public class Reviews {

    @SerializedName("page")
    @Expose
     Integer page;
    @SerializedName("results")
    @Expose
     List<Review> results = new ArrayList<>();
    @SerializedName("total_pages")
    @Expose
     Integer totalPages;
    @SerializedName("total_results")
    @Expose
     Integer totalResults;

    /**
     * @return The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return The results
     */
    public List<Review> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Review> results) {
        this.results = results;
    }

    /**
     * @return The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * @return The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

}
