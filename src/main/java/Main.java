import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        Calculator calculator = new Calculator(scanner);
        Formatter formatter = new Formatter();
        System.out.println("Это калькулятор счёта.");
        while (true) {
            System.out.println("На сколько человек необходимо разделить счёт?");
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: введите число.");
                scanner.nextLine();
                continue;
            }
            int numberOfPeople = scanner.nextInt();
            scanner.nextLine();
            if (numberOfPeople > 1) {
                calculator.addProduct();
                double total = calculator.totalPrice();
                calculator.pricePerPerson(total, numberOfPeople, formatter);
                break;
            } else if (numberOfPeople == 1) {
                System.out.println("Введите значение больше 1.");

            } else {
                System.out.println("Ошибка: введено некорректное значение.");
            }
        }

    }
}

