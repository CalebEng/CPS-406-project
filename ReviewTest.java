/*
 * Rahaf Hussain
 * 02/04/2023
 * CPS406
 * Testing Review class
*/

import java.util.ArrayList;

public class ReviewTest {
    public static void main(String[]args){
        System.out.println("--- A result of 'true' means the test passed. --- ");
        System.out.println("Review Test: " + testReview(5.0, "Ever since I bought these shoes, my life has improved. My skin is clear and my crops are watered."));
        System.out.println("Shoe Review Test: " + testShoeReview(0.5, "These are the worst shoes I've ever bought. Save your money."));
        System.out.println("Shoe Review Deletion Test: " + testDeleteShoeReview(3.0, "They're alright I guess."));
        System.out.println("Shopper Review Test: " + testShopperReview(1.5, "The owner of this store ran over grandma, but the shoes are decent."));
        System.out.println("Shopper Review Deletion Test: " + testDeleteShopperReview(4.0, "Pretty good."));
    }

    /**
     * Review Test 1:
     * Creates and tests the output of a review
     * @param rating
     * @param comment
     * @return true if the toString output is correct, false otherwise
    */
    public static boolean testReview(double rating, String comment){
        Review reviewTest = new Review(rating, comment);
        return (reviewTest.toString().equals("(Rating=" + rating + "&Comment=" + comment + ")"));
    }

    /**
     * Review Test 2:
     * Creates an instance of Shoe, then adds a review to it
     * @param rating
     * @param comment
     * @return true if the shoe's list of reviews contains the review, false otherwise
    */
    public static boolean testShoeReview(double rating, String comment){
        Shoe shoe = new Shoe(1001, "Air Force 1s", 150.0, 50, "What are thoooose", "Sneakers", "7", "Black", "Nike", "Bruce Wayne");
        Review shoeReviewTest = new Review(rating, comment);
        shoe.addReview(shoeReviewTest);
        ArrayList<Review> shoeReviews = shoe.getReviews();
        return(shoeReviews.contains(shoeReviewTest));
    }

    /**
     * Review Test 3:
     * Testing the deletion of a review from an instance of Shoe
     * @param rating
     * @param comment
     * @return true if the shoe's list of reviews does not contain the review, false otherwise
    */
    public static boolean testDeleteShoeReview(double rating, String comment){
        Shoe shoe = new Shoe(2002, "Red Bottoms", 1131.30, 15, "You can't afford this lol", "High Heels", "6", "Black/Red", "Christian Louboutin", "Selina Kyle");
        Review deleteShoeReviewTest = new Review(rating, comment);
        shoe.addReview(deleteShoeReviewTest);
        shoe.deleteReview(deleteShoeReviewTest);
        ArrayList<Review> shoeReviews = shoe.getReviews();
        return(!shoeReviews.contains(deleteShoeReviewTest));
    }

    /**
     * Review Test 4:
     * Creates an instance of Shopper and an instance of Shoe, then has the shopper leave a review on the shoe
     * @param rating
     * @param comment
     * @return true if the review can be found in both the shopper's review list and the shoe's review list
    */
    public static boolean testShopperReview(double rating, String comment){
        Shopper shopper = new Shopper("Clark Kent", "Metropolis", "notsuperman", "clark.kent@dailyplanet.com");
        Shoe shoe = new Shoe(3003, "Flip Flops", 790.0, 29, "Definitely not overpriced", "Sandals", "9", "Black/Green/Red", "Gucci", "Lois Lane");
        shopper.writeReview(shoe, rating, comment);
        ArrayList<Review> shoeReviews = shoe.getReviews();
        Review review = shopper.getReviews().get(0);
        return(shoeReviews.contains(review));
    }

    /**
     * Review Test 5:
     * Testing the deletion of a review that a shopper has left on a shoe
     * @param rating
     * @param comment
    */
    public static boolean testDeleteShopperReview(double rating, String comment){
        Shopper shopper = new Shopper("Tim Drake", "Gotham City", "bestrobin", "tim.drake@wayneenterprises.com");
        Shoe shoe = new Shoe(4004, "Canvas Old-Skool", 145.45, 52, "Damn Daniel...", "Sneakers", "11", "White", "Vans", "Damian Wayne");
        shopper.writeReview(shoe, rating, comment);
        Review review = shopper.getReviews().get(0);
        shopper.deleteReview(shoe, review);
        return(!shopper.getReviews().contains(review) & !shoe.getReviews().contains(review));
    }
}