package org.example;

import java.util.Scanner;

public class Main {
    public static int workerOrClient = 0;

    public static void main(String[] args) {
        while (true) {
            switch (menu()) {
                case 1:
                    creatNewAccount();
                    break;
                case 2:
                    connectToAccount();
                case 3:
                    break;
            }
        }
    }

    public static Client clientToSave = null;
    public static Worker workerToSave = null;
    public static Client[] clients = new Client[0];
    public static Worker[] workers = new Worker[0];
    public static Product[] products = new Product[0];
    public static ShoppingCart[] shoppingCarts = new ShoppingCart[0];
    public static ShoppingCart[] userShoppingCarts = new ShoppingCart[0];

    public static int menu() {
        Scanner scanner = new Scanner(System.in);
        int userChoose = 0;
        while ((userChoose < 1 || userChoose > 3)) {
            System.out.println("Welcome to the virtual store \n " +
                    "Enter your choose: \n" +
                    "1 - Creat new account \n" +
                    "2 - Connect to exist account \n" +
                    "3 - Exit");
            userChoose = scanner.nextInt();
        }
        return userChoose;
    }

    public static int menu2() {
        Scanner scanner = new Scanner(System.in);
        int userChoose = 0;
        while ((userChoose < 1 || userChoose > 8)) {
            System.out.println(
                    "Enter your choose: \n" +
                            "1 - Print all clients \n" +
                            "2 - Print all club members \n" +
                            "3 - Print clients with at list one purchase \n" +
                            "4 - Print the client with the most purchases \n" +
                            "5 - Add new product \n" +
                            "6 - Change product status \n" +
                            "7 - Start purchase \n" +
                            "8 - Exit");
            userChoose = scanner.nextInt();
        }
        switch (userChoose) {
            case 1:
                allClients();
                break;
            case 2:
                allClubMembers();
                break;
            case 3:
                atListOnePurchase();
                break;
            case 4:
                mostPurchases();
                break;
            case 5:
                addNewProduct();
                break;
            case 6:
                changeStatus();
                break;
            case 7:
                startShopping();
                break;
            case 8:
                menu();
                break;
        }
        return userChoose;
    }

    public static void mostPurchases() {
        int max = 0;
        Client clientToReturn = null;
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getShoppingCarts().length > max) {
                max = clients[i].getShoppingCarts().length;
                clientToReturn = clients[i];
            }
        }
        System.out.println(clientToReturn);
        menu2();
    }

    public static void atListOnePurchase() {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getShoppingCarts().length >= 1) {
                System.out.println(clients[i]);
            }
        }
        menu2();
    }

    public static void allClubMembers() {
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].isClubMember()) {
                System.out.println(clients[i]);
            }
        }
        menu2();
    }

    public static void allClients() {
        for (int i = 0; i < clients.length; i++) {
            System.out.println(clients[i]);
        }
        menu2();
    }

    public static void startShopping() {
        int productNum = 0;
        int amount = 0;
        Scanner scanner = new Scanner(System.in);
        Product[] clientProducts = new Product[0];
        do {
            printInStockList();
            System.out.println("Enter product name or -1 to end the purchase");
            productNum = scanner.nextInt();

            if (productNum >= 1 && productNum <= inStockProductsList().length) {
                do {
                    System.out.println("Enter valid product amount");
                    amount = scanner.nextInt();
                } while (amount < 0);
                for (int j = 0; j < amount; j++) {
                    Product[] newClientProducts = clientProducts;
                    clientProducts = new Product[clientProducts.length + 1];
                    for (int i = 0; i < newClientProducts.length; i++) {
                        clientProducts[i] = newClientProducts[i];
                    }
                    clientProducts[clientProducts.length - 1] = products[productNum - 1];
                }
                System.out.println("workerOrClient " + workerOrClient);
                if (workerOrClient == 1) {
                    double discount = ((double) (10 - workerToSave.getRank()) / 10);
                    System.out.println("Total price: " + printCart(clientProducts) * discount);
                } else {
                    System.out.println("Total price: " + printCart(clientProducts));
                }
            } else if (productNum != -1) {
                System.out.println("Enter valid product number");
            }

        } while (productNum != -1);
        printCart(clientProducts);

        ShoppingCart[] newShoppingCarts = shoppingCarts;
        shoppingCarts = new ShoppingCart[shoppingCarts.length + 1];
        for (int i = 0; i < newShoppingCarts.length; i++) {
            shoppingCarts[i] = newShoppingCarts[i];
        }
        ShoppingCart shoppingCart = new ShoppingCart(clientProducts);
        shoppingCarts[shoppingCarts.length - 1] = shoppingCart;

        ShoppingCart[] userNewShoppingCarts = userShoppingCarts;
        userShoppingCarts = new ShoppingCart[userShoppingCarts.length + 1];
        for (int i = 0; i < userNewShoppingCarts.length; i++) {
            userShoppingCarts[i] = userNewShoppingCarts[i];
        }
        userShoppingCarts[userShoppingCarts.length - 1] = shoppingCart;
        if (workerOrClient == 1) {
            workerToSave.setShoppingCarts(userShoppingCarts);

        } else {
            clientToSave.setShoppingCarts(userShoppingCarts);
        }
        menu();
    }

    public static double printCart(Product[] productsList) {
        double totalPrice = 0;
        System.out.println("Your cart: ");
        for (int i = 0; i < productsList.length; i++) {
            System.out.println(i + 1 + ") " + productsList[i].getName());
            totalPrice = totalPrice + productsList[i].getPrice();
        }
        return totalPrice;
    }

    public static void printInStockList() {
        System.out.println("Products in stock: ");
        for (int i = 0; i < inStockProductsList().length; i++) {
            System.out.println(i + 1 + ") " + inStockProductsList()[i].getName());
        }
    }

    public static Product[] inStockProductsList() {
        int counter = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].isInStock()) {
                counter++;
            }
        }
        Product[] list = new Product[counter];
        int index = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].isInStock()) {
                list[index] = products[i];
                index++;
            }
        }
        return list;
    }

    public static void productsList() {
        System.out.println("Products List:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + 1 + ") " + products[i].getName());
        }
    }

    public static void changeStatus() {
        Scanner scanner = new Scanner(System.in);
        productsList();
        String productName = "";
        while (productName == "") {
            System.out.println("Enter product name");
            productName = scanner.nextLine();
        }
        for (int i = 0; i < products.length; i++) {
            if (products[i].getName().equals(productName)) {
                products[i].setInStock(!products[i].isInStock());
                System.out.println("Product status changed");
            } else {
                System.out.println("Product status didn't changed");
            }
        }
        menu2();
    }

    public static void addNewProduct() {
        Scanner scanner = new Scanner(System.in);
        String productName = "";
        double price = 0;
        double discount = 0;
        while (productName == "") {
            System.out.println("Enter product name");
            productName = scanner.nextLine();
        }
        while (price == 0) {
            System.out.println("Enter price");
            price = scanner.nextDouble();
        }
        while (discount == 0) {
            System.out.println("Enter discount");
            discount = scanner.nextDouble();
        }
        Product[] newProducts = products;
        products = new Product[products.length + 1];
        Product product = new Product(productName, price, discount, true);
        for (int i = 0; i < newProducts.length; i++) {
            products[i] = newProducts[i];
        }
        products[products.length - 1] = product;
        menu2();
    }

    public static void workerOrClient() {
        Scanner scanner = new Scanner(System.in);
        while (workerOrClient < 1 || workerOrClient > 2) {
            System.out.println("Are you worker or client? \n" +
                    "1 - For worker \n" +
                    "2 - For client");
            workerOrClient = scanner.nextInt();
        }
    }

    public static void connectToAccount() {
        workerOrClient();
        Scanner scanner2 = new Scanner(System.in);
        String username = "";
        String password = "";
        while (username == "") {
            System.out.println("Enter username");
            username = scanner2.nextLine();
        }
        while (password == "") {
            System.out.println("Enter password");
            password = scanner2.nextLine();
        }
        if (workerOrClient == 1) {
            for (int i = 0; i < workers.length; i++) {
                if (workers[i].getUsername().equals(username) && workers[i].getPassword().equals(password)) {
                    workerToSave = workers[i];
                    printWorker(workers[i]);
                    menu2();
                } else {
                    System.out.println("Username or password are incorrect");
                    break;
                }
            }
        } else {
            for (int i = 0; i < clients.length; i++) {
                if (clients[i].getUsername().equals(username) && clients[i].getPassword().equals(password)) {
                    clientToSave = clients[i];
                    printClient(clients[i]);
                    startShopping();
                } else {
                    System.out.println("Username or password are incorrect");
                    break;
                }
            }
        }
    }


    public static void creatNewAccount() {
        workerOrClient();
        Scanner scanner2 = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        String username = "";
        String password = "";
        int rank = 0;
        int clubMember = 0;
        do {
            System.out.println("Enter your first name ");
            firstName = scanner2.nextLine();
        } while (!validName(firstName) || firstName == "");
        do {
            System.out.println("Enter your last name ");
            lastName = scanner2.nextLine();
        } while (!validName(lastName) || lastName == "");

        do {
            System.out.println("Enter username ");
            username = scanner2.nextLine();
            if (!validUsername(username)) {
                System.out.println("Username already taken");
            }
        } while (!validUsername(username) || username == "");
        do {
            System.out.println("Enter password ");
            password = scanner2.nextLine();
        } while (!validPassword(password));
        if (workerOrClient == 1) {
            do {
                System.out.println("Enter your rank: \n " +
                        "1 - For regular worker \n " +
                        "2 - For manager \n " +
                        "3 - Management member");
                rank = scanner2.nextInt();
            } while (rank < 1 || rank > 3);
            Worker worker = new Worker(firstName, lastName, username, password, false, null, rank);
            workers = new Worker[workers.length + 1];
            Worker[] newWorkers = workers;
            for (int i = 0; i < newWorkers.length; i++) {
                workers[i] = newWorkers[i];
            }
            workers[workers.length - 1] = worker;
        } else {
            while (clubMember < 1 || clubMember > 2) {
                System.out.println("Are you a club member?: \n " +
                        "1 - yes \n " +
                        "2 - no");
                clubMember = scanner2.nextInt();
            }
            Client client = new Client(firstName, lastName, username, password, isClubMember(clubMember), null);
            clients = new Client[clients.length + 1];
            Client[] newClients = clients;
            for (int i = 0; i < newClients.length; i++) {
                clients[i] = newClients[i];
            }
            clients[clients.length - 1] = client;
        }
    }

    public static boolean validName(String stringToCheck) {
        boolean isValid = true;
        String nums = "0123456789";
        for (int i = 0; i < stringToCheck.length(); i++) {
            for (int j = 0; j < nums.length(); j++) {
                if (stringToCheck.charAt(i) == nums.charAt(j)) {
                    isValid = false;
                    System.out.println("Cannot contain numbers");
                    break;
                }
            }
        }
        return isValid;
    }

    public static boolean validUsername(String stringToCheck) {
        boolean isValid = true;
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getUsername().equals(stringToCheck)) {
                isValid = false;
                break;
            }
        }
        for (int i = 0; i < workers.length; i++) {
            if (workers[i].getUsername().equals(stringToCheck)) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public static boolean validPassword(String stringToCheck) {
        boolean isValid = true;
        if (stringToCheck.length() < 6) {
            isValid = false;
            System.out.println("Password length must be at lest 6 characters");
        }
        return isValid;
    }

    public static boolean isClubMember(int num) {
        boolean isClubMember = false;
        if (num == 1) {
            isClubMember = true;
        }
        return isClubMember;
    }

    public static void printClient(Client client) {
        if (!client.isClubMember()) {
            System.out.println("Hello " + client.getFirstName() + " " + client.getLastName() + "!");
        } else {
            System.out.println("Hello " + client.getFirstName() + " " + client.getLastName() + "(VIP)!");
        }
    }

    public static void printWorker(Worker worker) {
        System.out.println("Hello " + worker.getFirstName() + " " + worker.getLastName() + " (" + worker.getRank() + ")!");
    }


}