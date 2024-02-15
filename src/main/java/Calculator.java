import java.util.Scanner;
import java.util.ArrayList;

class Calculator {
    ArrayList<Product> productsList = new ArrayList<>();
    double total = 0;
    Scanner scanner;

    public Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    void addProduct() {
        while (true) {
            System.out.println("Введите название товара.");
            String name = scanner.nextLine();
            System.out.println("Введите цену товара.");

            if (!scanner.hasNextDouble()) {
                System.out.println("Ошибка: стоимость товара должна быть в виде числа. " +
                        "Попробуйте заново.");
                scanner.nextLine();
                continue;
            }
            double price = scanner.nextDouble();
            scanner.nextLine();
            if (price >= 0) {
                Product product = new Product(name, price);
                productsList.add(product);
                System.out.println("Товар " + product.name + " успешно добавлен.");
            } else if (price < 0) {
                System.out.println("Ошибка: цена товара не может быть отрицательной. " +
                        "Попробуйте заново.");
                continue;
            }

            System.out.println("Нажмите Enter для добавления следующего товара или " +
                    "введите \"Завершить\".");
            String finish = scanner.nextLine();
            if (finish.equalsIgnoreCase("Завершить")) {
                scanner.close();
                break;
            }
        }
    }

    double totalPrice() {
        for (Product product : productsList) {
            total += product.price;
        }
        System.out.println("Добавленные товары:\n");
        for (Product product : productsList) {
            System.out.println(product.name + "\n");
        }
        return total;
    }

    void pricePerPerson(double total, int numberOfPeople, Formatter formatter) {
        double dividedPrice = total / numberOfPeople;
        String formattedPrice = formatter.formatPrice(dividedPrice);
        String formattedRubles = formatter.formatRubles(dividedPrice);
        System.out.println("Каждый человек должен заплатить: " + formattedPrice + " " + formattedRubles);
    }
}