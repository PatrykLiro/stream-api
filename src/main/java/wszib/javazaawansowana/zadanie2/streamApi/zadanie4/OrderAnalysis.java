package wszib.javazaawansowana.zadanie2.streamApi.zadanie4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

public class OrderAnalysis {
    static void main() {

        @AllArgsConstructor
        @Getter
        @Setter
        class OrderItem {
            private String productName;
            private String category;
            private int quantity;
            private double price;

        // konstruktor, gettery, settery
        }

        class Order {
            private String orderId;
            private String status; // "ZREALIZOWANE", "W TRAKCIE", "ANULOWANE"
            private List<OrderItem> items;

            // konstruktor, gettery, settery
            public Order(String orderId, String status, List<OrderItem> items) {
                this.orderId = orderId;
                this.status = status;
                this.items = items;
            }

            public String getOrderId() {
                return orderId;
            }
            public String getStatus() {
                return status;
            }
            public List<OrderItem> getItems() {
                return items;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }
            public void setStatus(String status) {
                this.status = status;
            }
            public void setItems(List<OrderItem> items) {
                this.items = items;
            }
        }

        List<Order> orders = Arrays.asList(
                new Order("ORD001", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Mysz Logitech", "Elektronika", 2, 150),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800)
                )),
                new Order("ORD002", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Monitor Samsung", "Elektronika", 1, 1200),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500)
                )),
                new Order("ORD003", "W TRAKCIE", Arrays.asList(
                        new OrderItem("Smartfon iPhone", "Elektronika", 1, 4500)
                )),
                new Order("ORD004", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Monitor Samsung", "Elektronika", 2, 1200),
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Lampa LED", "Oświetlenie", 3, 120)
                )),
                new Order("ORD005", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Laptop Dell", "Elektronika", 1, 3500),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800),
                        new OrderItem("Lampa LED", "Oświetlenie", 2, 120)
                )),
                new Order("ORD006", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Mysz Logitech", "Elektronika", 1, 150),
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500)
                )),
                new Order("ORD007", "ANULOWANE", Arrays.asList(
                        new OrderItem("Tablet Samsung", "Elektronika", 1, 2200)
                )),
                new Order("ORD008", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Monitor Samsung", "Elektronika", 1, 1200),
                        new OrderItem("Lampa LED", "Oświetlenie", 1, 120),
                        new OrderItem("Dywan", "Dekoracje", 1, 350)
                )),
                new Order("ORD009", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Mysz Logitech", "Elektronika", 3, 150),
                        new OrderItem("Biurko Ikea", "Meble", 1, 800)
                )),
                new Order("ORD010", "ZREALIZOWANE", Arrays.asList(
                        new OrderItem("Klawiatura Razer", "Elektronika", 1, 450),
                        new OrderItem("Krzesło Herman Miller", "Meble", 1, 2500),
                        new OrderItem("Dywan", "Dekoracje", 1, 350)
                ))
        );

        class ProductStats {
            private String productName;
            private long orderCount; // liczba zamówień zawierających ten produkt

            public ProductStats(String key, Long value) {
            }

            // konstruktor, gettery, settery
             ProductStats(String productName, long orderCount) {
                this.productName = productName;
                this.orderCount = orderCount;
            }

            public String getProductName() {
                return productName;
            }
            public long getOrderCount() {
                return orderCount;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }
            public void setOrderCount(long orderCount) {
                this.orderCount = orderCount;
            }

            @Override
            public String toString() {
                return "ProductStats{" +
                        "productName='" + productName + '\'' +
                        ", orderCount=" + orderCount +
                        '}';
            }
        }

        Map<String, List<ProductStats>> result =
                orders.stream()
                        .filter(o -> "ZREALIZOWANE".equals(o.getStatus()))
                        .flatMap(o ->
                                o.getItems().stream()
                                        .map(item -> new AbstractMap.SimpleEntry<>(
                                                o.getOrderId(),
                                                item
                                        ))
                        )
                        .distinct()
                        .collect(Collectors.groupingBy(
                                e -> e.getValue().getCategory(),
                                Collectors.collectingAndThen(
                                        Collectors.groupingBy(
                                                e -> e.getValue().getProductName(),
                                                Collectors.counting()
                                        ),
                                        productCounts -> productCounts.entrySet().stream()
                                                .map(p -> new ProductStats(p.getKey(), p.getValue()))
                                                .sorted(Comparator.comparingLong(ProductStats::getOrderCount).reversed())
                                                .limit(3)
                                                .collect(Collectors.toList())
                                )
                        ));

        System.out.println(result);
        result.forEach((category, stats) -> System.out.println(category + " = " + stats));
    }
}
