import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        Scanner scanner = new Scanner(System.in);
        var products = new String[]{"Сыр", "Колбаса", "Макароны"};
        int[] prices = new int[]{65, 152, 78};
        Basket basket = new Basket(products, prices);
        Config config = new Config();
        config.configload();
        ClientLog clientLog = new ClientLog();
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
                config.configLog(clientLog);
                System.out.println("Ваша корзина:");
                System.out.println(basket.printCart());
                config.configsave(basket);
                break;
            }
            String[] vibor = input.split(" ");
            productNumber = Integer.parseInt(vibor[0]) - 1;
            amount = Integer.parseInt(vibor[1]);
            if (amount < 0) {
                System.out.println("Не коректное колличество введите снова");
                break;
            }
            clientLog.log(productNumber, amount);
            basket.addToCart(productNumber, amount);
        }
    }
}