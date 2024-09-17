package org.example;

public class Client extends User {
    private boolean isClubMember;
    private ShoppingCart[] shoppingCarts;

    public Client(String firstName, String lastName, String username, String password, boolean isClubMember, ShoppingCart[] shoppingCarts) {
        super(firstName, lastName, username, password);
        this.isClubMember = isClubMember;
        this.shoppingCarts = shoppingCarts;
    }

    public boolean isClubMember() {
        return isClubMember;
    }

    public void setClubMember(boolean clubMember) {
        isClubMember = clubMember;
    }

    public ShoppingCart[] getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(ShoppingCart[] shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public String toString() {
        return super.toString() + "Client{" +
                "isClubMember=" + isClubMember +
                '}';
    }
}
