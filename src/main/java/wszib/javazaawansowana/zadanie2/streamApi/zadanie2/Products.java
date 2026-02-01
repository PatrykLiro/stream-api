package wszib.javazaawansowana.zadanie2.streamApi.zadanie2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Products {
    static void main() {

        @AllArgsConstructor
        @Setter
        @Getter
        class Product {
            private String name;
            private String category;
            private double price;

            // konstruktor, gettery, settery

        }

        List<Product> products = Arrays.asList(
                new Product("Laptop Dell", "Elektronika", 3500.00),
                new Product("Mysz Logitech", "Elektronika", 150.00),
                new Product("Monitor Samsung", "Elektronika", 1200.00),
                new Product("Klawiatura Razer", "Elektronika", 450.00),
                new Product("Smartfon iPhone", "Elektronika", 4500.00),
                new Product("Krzesło biurowe", "Meble", 800.00),
                new Product("Słuchawki Sony", "Elektronika", 350.00),
                new Product("Tablet Samsung", "Elektronika", 2200.00),
                new Product("Biurko", "Meble", 1500.00),
                new Product("Drukarka HP", "Elektronika", 900.00)
        );

        List<String> expensiveElectronics = products.stream()
                .filter(p -> p.getCategory().equals("Elektronika") && p.getPrice() > 1000.00)
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .map(Product::getName)
                .toList();

        System.out.println("Produkty z kategorii 'Elektronika' o cenie powyżej 1000.00 zł, posortowane malejąco według ceny: " + expensiveElectronics);
    }
}