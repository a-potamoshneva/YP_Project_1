import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        Calculator calculator= new Calculator(scanner);
        Formatter formatter=new Formatter();
        System.out.println("Это калькулятор счёта.");
        while (true) {
            System.out.println("На сколько человек необходимо разделить счёт?");
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: введите число.");
                scanner.nextLine();
                continue;
            }
            int numberOfPeople=scanner.nextInt();
            scanner.nextLine();
            if (numberOfPeople>1) {
                calculator.addProduct();
                double total= calculator.totalPrice();
                calculator. pricePerPerson (total, numberOfPeople, formatter);
                break;
            }
            else if (numberOfPeople==1) {
                System.out.println("Введите значение больше 1.");

            }
            else {
                System.out.println("Ошибка: введено некорректное значение.");
            }
        }

    }
}
class Calculator {
    ArrayList<Product> productsList = new ArrayList<>();
    double total=0;
    Scanner scanner;
    public Calculator (Scanner scanner) {
        this.scanner=scanner;
    }

    void addProduct () {
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
            if (price>=0) {
                Product product = new Product(name, price);
                productsList.add(product);
                System.out.println("Товар " + product.name + " успешно добавлен.");
            } else if (price<0) {
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
    double totalPrice () {
        for (Product product : productsList) {
            total += product.price;
        }
        System.out.println("Добавленные товары:\n");
        for (Product product : productsList) {
            System.out.println(product.name+"\n");
        }
        return total;
    }
    void pricePerPerson (double total, int numberOfPeople, Formatter formatter) {
        double dividedPrice=total/numberOfPeople;
        String formattedPrice = formatter.formatPrice(dividedPrice);
        String formattedRubles = formatter.formatRubles(dividedPrice);
        System.out.println("Каждый человек должен заплатить: " + formattedPrice + " " + formattedRubles);
    }
}

class Formatter {
    public String formatPrice(double dividedPrice) {
        return String.format(Locale.US, "%.2f", dividedPrice);
    }

    public String formatRubles(double dividedPrice) {
        int roundedRubles= (int)Math.floor(dividedPrice);
        String formattedRubles;
        if (roundedRubles % 10 == 1 && roundedRubles % 100 != 11) {
            formattedRubles = "рубль";
        } else if ((roundedRubles % 10 >= 2 && roundedRubles % 10 <= 4) &&
                (roundedRubles % 100 < 10 || roundedRubles % 100 >= 20)) {
            formattedRubles = "рубля";
        } else {
            formattedRubles = "рублей";
        }
        return formattedRubles;
    }
}

class Product {
    String name;
    double price;
    public Product(String name, double price) {
        this.name=name;
        this.price=price;
    }
}
