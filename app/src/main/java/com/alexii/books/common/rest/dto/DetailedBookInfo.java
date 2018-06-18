package com.alexii.books.common.rest.dto;

/**
 * Detailed book info for displaying in separate screen.
 */
public class DetailedBookInfo extends BookInfo {
    private static final long serialVersionUID = 4456649274181195736L;

    String description;

    /**
     * @return book description, might be html.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return super.toString() + ", description: " + description;
    }
}
