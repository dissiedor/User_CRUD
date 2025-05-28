package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDao dao = new UserDao();
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
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setAge(age);
                    dao.createUser(user);
                }
                case 2 -> dao.getAllUsers().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID: ");
                    Long id = scanner.nextLong();
                    System.out.println(dao.getUser(id));
                }
                case 4 -> {
                    System.out.print("ID: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    User u = dao.getUser(id);
                    if (u != null) {
                        System.out.print("Новое имя: ");
                        u.setName(scanner.nextLine());
                        System.out.print("Новый email: ");
                        u.setEmail(scanner.nextLine());
                        System.out.print("Новый возраст: ");
                        u.setAge(scanner.nextInt());
                        dao.updateUser(u);
                    }
                }
                case 5 -> {
                    System.out.print("ID: ");
                    dao.deleteUser(scanner.nextLong());
                }
                case 6 -> {
                    HibernateUtil.shutdown();
                    System.exit(0);
                }
            }
        }
    }
}