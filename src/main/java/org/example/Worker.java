package org.example;

public class Worker extends Client {
    private int rank;

    public Worker(String firstName, String lastName, String username, String password, boolean isClubMember, ShoppingCart[] shoppingCarts, int rank) {
        super(firstName, lastName, username, password, isClubMember, shoppingCarts);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return super.toString() + "Worker{" +
                "rank=" + rank +
                '}';
    }
}
