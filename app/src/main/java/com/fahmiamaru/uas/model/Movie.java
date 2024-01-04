package com.fahmiamaru.uas.model;

public class Movie {

    String title , poster , overview, release;

    public Movie(String title, String poster, String overview, String release){
        this.title = title;
        this.poster = poster;
        this.overview = overview;
        this.release = release;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease() {
        return release;
    }

}