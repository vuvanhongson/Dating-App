package com.example.tinder.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class SOProfileResponse {

        @SerializedName("results")
        @Expose
        private List<Result> results = null;

        public List<Result> getResults() {
            return results;
        }

        public void setResults(List<Result> results) {
            this.results = results;
        }

        @Override
        public String toString() {
            return "SOProfileResponse{" +
                    "results=" + results +
                    '}';
        }
    }
