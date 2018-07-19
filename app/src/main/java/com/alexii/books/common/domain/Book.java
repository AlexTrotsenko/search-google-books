package com.alexii.books.common.domain;

public class Book {
    private String id;
    private String title;
    private String thumbnailLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    /**
     * @return null as base info do not have description data.
     */
    public String getDescription() {
        return null;
    }
}
