/*
 * Rahaf Hussain
 * 26/03/2023
 * CPS406
 * Review class which holds all the information about a product review
*/

public class Review {
    private double rating;
    private String comment;

    /**
     * Class constructor
     * @param rating
     *      the rating that the user has given a product
     * @param comment
     *      the comment that the user has left on a product
    */
    public Review(double rating, String comment){
        this.rating = rating;
        this.comment = comment;
    }

    // Getters
    public double viewRating(){
        return rating;
    }
    public String viewReview(){
        return comment;
    }

    // Setters
    public void giveRating(double rating){
        this.rating = rating;
    }
    public void writeReview(String comment){
        this.comment = comment;
    }

    /**
     * tostring for testing
     * @returns information about the review class
    */
    public String toString(){
        return "(Rating=" + this.rating + "&Comment=" + this.comment + ")";
    }
}