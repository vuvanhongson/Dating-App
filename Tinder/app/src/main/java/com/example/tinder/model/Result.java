package com.example.tinder.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("seed")
        @Expose
        private String seed;
        @SerializedName("version")
        @Expose
        private String version;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getSeed() {
            return seed;
        }

        public void setSeed(String seed) {
            this.seed = seed;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

    @Override
    public String toString() {
        return "Result{" +
                "user=" + user +
                ", seed='" + seed + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
