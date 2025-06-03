package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Добавить пользователя\n2. Показать всех пользователей\n3. Найти по ID\n4. Обновить\n5. Удалить\n6. Выход");
            switch (scanner.nextInt()) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("Имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Возраст: ");
                    int age = scanner.nextInt();
                    service.addUser(name, email, age);
                }
                case 2 -> {
                    service.listUsers().forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = scanner.nextLong();
                    User user = service.findUserById(id);
                    System.out.println(user != null ? user : "Пользователь не найден.");
                }
                case 4 -> {
                    System.out.print("ID: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    User user = service.findUserById(id);
                    if (user != null) {
                        System.out.print("Новое имя: ");
                        String name = scanner.nextLine();
                        System.out.print("Новый email: ");
                        String email = scanner.nextLine();
                        System.out.print("Новый возраст: ");
                        int age = scanner.nextInt();
                        service.updateUser(id, name, email, age);
                    } else {
                        System.out.println("Пользователь не найден.");
                    }
                }
                case 5 -> {
                    System.out.print("ID: ");
                    Long id = scanner.nextLong();
                    service.deleteUser(id);
                }
                case 6 -> {
                    HibernateUtil.shutdown();
                    System.exit(0);
                }
            }
        }
    }
}