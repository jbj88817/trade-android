package us.bojie.tradebo.bean;

import com.google.gson.annotations.SerializedName;

public class CommonResponse<T> {
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private T results;
    @SerializedName("next")
    private String next;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
