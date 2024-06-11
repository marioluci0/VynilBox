package com.vynilbox.review;

/**
 * Used for facilitate and make a clean code.
 * Contains all the attributes of the Review
 * in just one record.
 * @param stars quantify of stars (0-5)
 * @param text review text
 * @param songId songs reviewd
 * @param userId owner of the review
 */
public record ReviewRegisterData (int stars, String text, int songId, int userId) {
}
