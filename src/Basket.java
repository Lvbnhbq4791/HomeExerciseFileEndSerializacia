import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Basket {
    protected String[] products;
    protected int[] prices;
    protected String cart;
    protected static ArrayList<String> tovar;
    protected static ArrayList<Integer> quantity;
    protected static ArrayList<Integer> sum;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
        this.cart = null;
        tovar = new ArrayList<>();
        quantity = new ArrayList<>();
        sum = new ArrayList<>();
    }

    public void addToCart(int productNum, int amount) {
        Predicate<String> proverka = tovar::contains;
        if (proverka.test(products[productNum])) {
            int ind = tovar.indexOf(products[productNum]);
            quantity.set(ind, quantity.get(ind) + amount);
            sum.set(ind, quantity.get(ind) * prices[productNum]);
        } else {
            tovar.add(products[productNum]);
            quantity.add(amount);
            sum.add(amount * prices[productNum]);
        }
    }

    public String printCart() {
        StringBuilder sborka = new StringBuilder();
        int sumItog = 0;
        for (int i = 0; i < tovar.size(); i++) {
            ArrayList<String> productss = new ArrayList<>(List.of(products));
            int j = productss.indexOf(tovar.get(i));
            sborka.append(tovar.get(i)).append(" ")
                    .append(quantity.get(i)).append(" шт. ")
                    .append(prices[j]).append(" руб/шт ")
                    .append(sum.get(i)).append(" руб в сумме;")
                    .append(System.lineSeparator());
            sumItog += sum.get(i);
        }
        sborka.append("Итого: ").append(sumItog).append(" руб.");
        return cart= sborka.toString();
    }

    public void saveTxt(File textFile1) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(textFile1))) {
            out.write(cart);
        } catch (IOException exit) {
            System.out.println(exit.getMessage());
        }
    }

    public static void loadFromTxtFile(File textFile) {
        try (BufferedReader imp = new BufferedReader(new FileReader(textFile))) {
            String newString;
            while ((newString = imp.readLine()) != null) {
                String[] strings = newString.split(" ", 7);
                if (!strings[0].equals("Итого:")) {
                    tovar.add(strings[0]);
                    quantity.add(Integer.parseInt(strings[1]));
                    sum.add(Integer.parseInt(strings[5]));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
