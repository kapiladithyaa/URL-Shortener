package com.kapiladithyaa;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        UrlStore store = new UrlStore();
        Scanner scanner = new Scanner(System.in);

        System.out.println(
            "Commands: shorten <url> | open <code> | exit"
        );

        while (true) {

            System.out.print("> ");

            String line = scanner.nextLine().trim();

            if (line.equals("exit")) {
                break;
            }

            String[] parts = line.split(" ", 2);

            if (parts.length < 2) {
                System.out.println(
                    "Usage: shorten <url> | open <code> | exit"
                );
                continue;
            }

            try {

                if (parts[0].equals("shorten")) {

                    System.out.println(
                        "Short code: " + store.shorten(parts[1])
                    );

                } else if (parts[0].equals("open")) {

                    System.out.println(
                        "Original URL: " + store.resolve(parts[1])
                    );

                } else {

                    System.out.println("Unknown command");

                }

            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

            }
        }

        scanner.close();
    }
}