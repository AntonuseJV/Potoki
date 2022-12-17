import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Молоко", "Крупа", "Чай", "Сахар"};
        int[] prices = {100, 50, 80, 60};
        Basket b01 = new Basket(prices, products);
        File file = new File("basket.bin");


        if (file.exists()) {  // проверяем наличие файла с данными
            System.out.println("Восстанавливаю корзину (данными из файла)");
            Basket.loadFromBinFile(file);
        } else {
            System.out.println("Файл с данными не найден, заполните корзину в ручную.");
            System.out.println("Список товаров, доступных к покупке:");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + "\t" + products[i] + " - " +
                        prices[i] + " руб.");
            }
            while (true) {
                System.out.println("Веди номер товара и его количество (через пробел); " +
                        "для подсчета результатов и выхода набери end.");
                String input = scanner.nextLine();
                if ("end".equals(input)) {
                    break;
                }
                String[] choice = input.split(" ");
                int cellNum = Integer.parseInt(choice[0]) - 1;
                int quantity = Integer.parseInt(choice[1]);
                b01.addToCart(cellNum, quantity);
            }

            System.out.println("В вашей корзине: ");
            b01.printCart();
        }

        b01.saveBin(file, b01);
        System.out.println("Данные вашей корзины сохранены в файл: basket.bin");
    }
}