package com.fahmiamaru.uas.model;

public class Like {
    String user, title , release, overview , poster;

    public Like(String user, String title, String release, String overview, String poster) {
        this.user = user;
        this.title = title;
        this.release = release;
        this.overview = overview;
        this.poster = poster;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
