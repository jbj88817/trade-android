package us.bojie.tradebo.bean;

import com.google.gson.annotations.SerializedName;

public class ResultResponse<T> {
    @SerializedName("results")
    private T results;

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
