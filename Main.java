package ToDoList;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8.name());

        System.out.println("Добро пожаловать в приложение \"Управление списком задач\"!");
        ListManagement manager = new ListManagement(scanner);

        while (true) {
            System.out.println("\n1. Добавить задачу");
            System.out.println("2. Удалить задачу");
            System.out.println("3. Отредактировать задачу");
            System.out.println("4. Показать все задачи");
            System.out.println("5. Фильтровать задачи по статусу");
            System.out.println("6. Найти задачу по ключевому слову");
            System.out.println("7. Изменить статус задачи");
            System.out.println("8. Показать статистику");
            System.out.println("9. Выход");
            System.out.print("\nВыберите действие: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Неверный ввод. Введите число от 1 до 9.");
                scanner.nextLine();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Введите название задачи: ");
                String name = scanner.nextLine();
                while (name.trim().isEmpty()) {
                    System.out.print("Название не может быть пустым");
                    name = scanner.nextLine();
                }
                System.out.print("Введите приоритет: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Приоритет должен быть числом");
                    scanner.nextLine();
                    continue;
                }
                int priority = scanner.nextInt();
                scanner.nextLine();

                manager.addNewDailyTask(name, priority);

            } else if (choice == 2) {
                System.out.print("Введите номер задачи: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Неверный ввод");
                    scanner.nextLine();
                    continue;
                }
                int index = scanner.nextInt();
                manager.deleteTask(index);

            } else if (choice == 3) {
                System.out.print("Введите номер задачи: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Неверный ввод");
                    scanner.nextLine();
                    continue;
                }
                int index = scanner.nextInt();
                manager.editTask(index);

            } else if (choice == 4) {
                manager.printTasks();

            } else if (choice == 5) {
                manager.filterTasks();

            } else if (choice == 6) {
                manager.findByKeyword();

            } else if (choice == 7) {
                System.out.print("Введите номер задачи: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Неверный ввод");
                    scanner.nextLine();
                    continue;
                }
                int index = scanner.nextInt();
                manager.changeStatus(index);

            } else if (choice == 8) {
                manager.showStatistics();

            } else if (choice == 9) {
                manager.exitProgram();

            } else {
                System.out.println("Введите число от 1 до 9");
            }
        }
    }
}