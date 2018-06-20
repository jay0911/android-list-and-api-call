package com.homecredit.ph.recyclerviewactivity;

import com.google.gson.annotations.SerializedName;

public class SuperHero {

        public SuperHero() {}

        @SerializedName("name")
        private String name;
        @SerializedName("about")
        private String about;
        @SerializedName("image")
        private String image;

        public SuperHero(String name, String about, String image) {
            this.setName(name);
            this.setAbout(about);
            this.setImage(image);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAbout() {
            return about;
        }

        public void setAbout(String about) {
            this.about = about;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

}
