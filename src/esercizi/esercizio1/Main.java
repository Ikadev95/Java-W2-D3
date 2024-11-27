package esercizi.esercizio1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Customer c = new Customer("marco", 2);
        Customer c1 = new Customer("mattia", 2);
        Customer c2 = new Customer("veronica", 1);

        Product p = new Product("prodotto1", "Books", 120.0);
        Product p1 = new Product("prodotto2", "Books", 180.0);
        Product p2 = new Product("prodotto3", "Baby", 60.0);
        Product p3 = new Product("prodotto4", "Boys", 10.0);
        Product p4 = new Product("prodotto5", "Baby", 12.0);
        Product p5 = new Product("prodotto6", "Boys", 18.0);

        List<Product> productList = new ArrayList<>();
        productList.add(p);
        productList.add(p2);
        Order ordine = new Order(c, productList, LocalDate.of(2021, 2, 3));

        List<Product> productList1 = new ArrayList<>();
        productList1.add(p3);
        productList1.add(p4);
        Order ordine2 = new Order(c1, productList1,  LocalDate.of(2021, 3, 3));

        List<Product> productList2 = new ArrayList<>();
        productList2.add(p5);
        productList2.add(p4);
        Order ordine3 = new Order(c2, productList2,  LocalDate.of(2023, 5, 6));

        List<Order> ordini = new ArrayList<>();
        ordini.add(ordine);
        ordini.add(ordine2);
        ordini.add(ordine3);

        // esercizio 1
        List<Product> prodotti = new ArrayList<>();
        prodotti.add(p);
        prodotti.add(p1);
        prodotti.add(p2);
        prodotti.add(p3);
        prodotti.add(p4);
        prodotti.add(p5);

        List<Product> prodottiBooks = prodotti.stream()
                .filter(pr -> pr.getCategory().equals("Books") && pr.getPrice() > 100)
                .toList();

        System.out.println("lista di prodotti della categoria books con prezzo maggiore di 100 euro");
        prodottiBooks.forEach(Product::stampa);
        System.out.println("--------");

        // esercizio 2
        List<Order> ordiniBaby = ordini.stream()
                .filter(or -> or.getProducts().stream()
                        .anyMatch(el -> el.getCategory().equals("Baby")))
                .toList();

        System.out.println("lista di ordini con prodotti che appartengono alla categoria Baby:");
        ordiniBaby.forEach(Order::stampaOrdine);

        //esercizio 3
        List<Product> productsBoys = prodotti.stream()
                .filter(pr -> pr.getCategory().equals("Boys"))
                .peek(pr -> pr.setPrice(pr.getPrice() * 0.9))
                .toList();

        System.out.println("lista prodotti categoria Boys con applicazione sconto: ");
        productsBoys.forEach(Product::stampa);
        System.out.println("--------");

        //esercizio 4

        LocalDate dataInizio = LocalDate.of(2021, 2, 1);  // 1° febbraio 2021
        LocalDate dataFine = LocalDate.of(2021, 4, 1);    // 1° aprile 2021


        List<Product> ordiniLivello2 = ordini.stream()
                .filter(or -> or.getCustomer().getTier().equals(2) && or.getOrderDate().isBefore(dataFine) && or.getOrderDate().isAfter(dataInizio))
                .flatMap(or -> or.getProducts().stream())
                .toList();

        System.out.println("prodotti filtrati clienti livello 2 tra il 1 febbraio e il 1 aprile 2021");
        ordiniLivello2.forEach(Product::stampa);
    }
}
