package com.example.tinder.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("name")
        @Expose
        private Name name;
        @SerializedName("location")
        @Expose
        private Location location;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("salt")
        @Expose
        private String salt;
        @SerializedName("md5")
        @Expose
        private String md5;
        @SerializedName("sha1")
        @Expose
        private String sha1;
        @SerializedName("sha256")
        @Expose
        private String sha256;
        @SerializedName("registered")
        @Expose
        private String registered;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("cell")
        @Expose
        private String cell;
        @SerializedName("SSN")
        @Expose
        private String ssn;
        @SerializedName("picture")
        @Expose
        private String picture;

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public String getSha256() {
            return sha256;
        }

        public void setSha256(String sha256) {
            this.sha256 = sha256;
        }

        public String getRegistered() {
            return registered;
        }

        public void setRegistered(String registered) {
            this.registered = registered;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getCell() {
            return cell;
        }

        public void setCell(String cell) {
            this.cell = cell;
        }

        public String getSsn() {
            return ssn;
        }

        public void setSsn(String ssn) {
            this.ssn = ssn;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

    @Override
    public String toString() {
        return "User{" +
                "gender='" + gender + '\'' +
                ", name=" + name +
                ", location=" + location +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", md5='" + md5 + '\'' +
                ", sha1='" + sha1 + '\'' +
                ", sha256='" + sha256 + '\'' +
                ", registered='" + registered + '\'' +
                ", dob='" + dob + '\'' +
                ", phone='" + phone + '\'' +
                ", cell='" + cell + '\'' +
                ", ssn='" + ssn + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
