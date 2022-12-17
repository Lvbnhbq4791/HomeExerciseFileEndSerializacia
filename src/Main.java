import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] tovar = new int[3];
        int[] sums = new int[3];
        boolean[] proVerka = new boolean[3];
        String[] products = new String[]{"Сыр", "Колбаса", "Макароны"};
        int[] prices = new int[]{65, 152, 78};
        System.out.println("Список возможных товаров для покупки");
        int n = 0;
        for (int i = 0; i < products.length; i++) {
            n = 1 + n;
            System.out.println(n + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
        while (true) {
            int productNumber = 0;
            int productCount = 0;
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();
            if ("end".equals(input)) {
                System.out.println("Ваша корзина:");
                int ss = 0;
                for (int chs = 0; chs < products.length; chs++) {
                    if (proVerka[chs]) {
                        String nn = products[chs];
                        int k = tovar[chs];
                        int c = prices[chs];
                        int s = sums[chs];
                        System.out.println(nn + " " + k + " шт. " + c + " руб/шт " + s + " руб в сумме");
                        ss += s;
                    }
                }
                System.out.println("Итого " + ss + " руб.");
                break;
            }
            String[] vibor = input.split(" ");
            productNumber = Integer.parseInt(vibor[0]) - 1;
            productCount = Integer.parseInt(vibor[1]);
            if (productCount < 0) {
                System.out.println("Не коректное колличество введите снова");
                continue;
            }
            int count = tovar[productNumber];
            int cena = prices[productNumber];
            tovar[productNumber] = productCount + count;
            sums[productNumber] = (productCount + count) * cena;
            proVerka[productNumber] = true;
        }
    }
}