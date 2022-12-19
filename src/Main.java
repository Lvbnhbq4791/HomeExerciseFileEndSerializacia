import java.io.File;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var products = new String[]{"Сыр", "Колбаса", "Макароны"};
        int[] prices = new int[]{65, 152, 78};
        File textFile = new File("basket.bin");
        Basket basket = new Basket(products, prices);
        if (textFile.exists()) {
//            Basket.loadFromTxtFile(textFile);
            Basket.loadFromBinFile(textFile);
        }
        System.out.println("Список возможных товаров для покупки");
        int n = 1;
        for (int i = 0; i < products.length; i++) {
            System.out.println(n + ". " + products[i] + " " + prices[i] + " руб/шт");
            n++;
        }
        while (true) {
            int productNumber;
            int amount;
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Ваша корзина:");
                System.out.println(basket.printCart());
//                basket.saveTxt(textFile);
                basket.saveBin(textFile);
                break;
            }
            String[] vibor = input.split(" ");
            productNumber = Integer.parseInt(vibor[0]) - 1;
            amount = Integer.parseInt(vibor[1]);
            if (amount < 0) {
                System.out.println("Не коректное колличество введите снова");
                break;
            }
            basket.addToCart(productNumber, amount);
        }
    }
}

