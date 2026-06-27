import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("      LIVE CURRENCY CONVERTER");
        System.out.println("=================================");

        System.out.print("Base Currency (USD, EUR, GBP...): ");
        String base = input.next().toUpperCase();

        System.out.print("Target Currency: ");
        String target = input.next().toUpperCase();

        System.out.print("Amount: ");
        double amount = input.nextDouble();

        try {

            String api =
                    "https://api.frankfurter.app/latest?amount="
                            + amount
                            + "&from="
                            + base
                            + "&to="
                            + target;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(api))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            String key = "\"" + target + "\":";
            int start = json.indexOf(key);

            if (start == -1) {
                System.out.println("Invalid currency code.");
                return;
            }

            start += key.length();

            int end = json.indexOf("}", start);

            double result = Double.parseDouble(json.substring(start, end));

            System.out.println("\n========== RESULT ==========");
            System.out.printf("%.2f %s = %.2f %s%n",
                    amount, base, result, target);

        } catch (Exception e) {
            System.out.println("Unable to retrieve exchange rate.");
            System.out.println(e.getMessage());
        }

        input.close();
    }
}