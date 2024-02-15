import java.util.Locale;

class Formatter {
    public String formatPrice(double dividedPrice) {
        return String.format(Locale.US, "%.2f", dividedPrice);
    }

    public String formatRubles(double dividedPrice) {
        int roundedRubles = (int) Math.floor(dividedPrice);
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