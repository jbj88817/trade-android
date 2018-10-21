package us.bojie.tradebo.bean;

import com.google.gson.annotations.SerializedName;

public class CommonResponse {
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private String results;
    @SerializedName("next")
    private String next;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
