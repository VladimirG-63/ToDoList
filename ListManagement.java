package ToDoList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ListManagement {
    List<DailyTasks> list = new ArrayList<>();
    private final Scanner scanner;

    public ListManagement(Scanner scanner) {
        this.scanner = scanner;
    }

    public void addNewDailyTask(String name, int priority) {
        DailyTasks newTask = new DailyTasks(name, priority, "Невыполнена");
        list.add(newTask);
        System.out.println("Задача добавлена");
    }

    public void deleteTask(int number) {
        int index = number - 1;
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            System.out.println("Задача под номером " + number + " удалена");
        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void editTask(int number) {
        int index = number - 1;
        if (index >= 0 && index < list.size()) {
            DailyTasks task = list.get(index);
            System.out.println("Что изменить?");
            System.out.println("1. Название");
            System.out.println("2. Приоритет");

            if (!scanner.hasNextInt()) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
                return;
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Введите новое название: ");
                String newName = scanner.nextLine();
                task.setName(newName);
                System.out.println("Название изменено");
            } else if (choice == 2) {
                System.out.print("Введите новый приоритет: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("Неверный ввод");
                    scanner.nextLine();
                    return;
                }
                int newPriority = scanner.nextInt();
                task.setPriority(newPriority);
                System.out.println("Приоритет изменен");
            } else {
                System.out.println("Неверный выбор");
            }
        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void filterTasks() {
        System.out.println("Выберите критерий:");
        System.out.println("1. По статусу");
        System.out.println("2. По диапазону приоритетов");

        if (!scanner.hasNextInt()) {
            System.out.println("Неверный ввод");
            scanner.nextLine();
            return;
        }
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("Какой статус ищем?");
            System.out.println("1. Выполнена");
            System.out.println("2. В процессе");
            System.out.println("3. Невыполнена");

            if (!scanner.hasNextInt()) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
                return;
            }
            int statusChoice = scanner.nextInt();
            String status = "";

            if (statusChoice == 1) {
                status = "Выполнена";
            } else if (statusChoice == 2) {
                status = "В процессе";
            } else if (statusChoice == 3) {
                status = "Невыполнена";
            } else {
                System.out.println("Неверный выбор");
                return;
            }

            final String finalStatus = status;
            List<DailyTasks> filtered = list.stream()
                    .filter(t -> t.getStatus().equals(finalStatus))
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("Задачи с таким статусом не найдены");
            } else {
                int counter = 1;
                for (DailyTasks task : filtered) {
                    System.out.println(counter + ". " + task.toString());
                    counter++;
                }
            }

        } else if (choice == 2) {
            System.out.print("Минимальный приоритет: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
                return;
            }
            int min = scanner.nextInt();

            System.out.print("Максимальный приоритет: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
                return;
            }
            int max = scanner.nextInt();

            List<DailyTasks> filtered = list.stream()
                    .filter(t -> t.getPriority() >= min && t.getPriority() <= max)
                    .collect(Collectors.toList());

            if (filtered.isEmpty()) {
                System.out.println("Задачи в данном диапазоне не найдены");
            } else {
                int counter = 1;
                for (DailyTasks task : filtered) {
                    System.out.println(counter + ". " + task.toString());
                    counter++;
                }
            }
        } else {
            System.out.println("Неверный выбор");
        }
    }

    public void findByKeyword() {
        System.out.print("Введите ключевое слово: ");
        String word = scanner.nextLine();

        List<DailyTasks> foundTask = list.stream()
                .filter(s -> s.getName().contains(word))
                .collect(Collectors.toList());

        if (foundTask.isEmpty()) {
            System.out.println("Совпадений не найдено");
        } else {
            int counter = 1;
            for (DailyTasks task : foundTask) {
                System.out.println(counter + ". " + task.toString());
                counter++;
            }
        }
    }

    public void changeStatus(int number) {
        int index = number - 1;
        if (index >= 0 && index < list.size()) {
            DailyTasks task = list.get(index);

            System.out.println("Выберите новый статус:");
            System.out.println("1. Выполнена");
            System.out.println("2. В процессе");
            System.out.println("3. Невыполнена");

            if (!scanner.hasNextInt()) {
                System.out.println("Неверный ввод");
                scanner.nextLine();
                return;
            }
            int choice = scanner.nextInt();

            if (choice == 1) {
                task.setStatus("Выполнена");
                LocalDateTime now = LocalDateTime.now();
                task.setDateOfCompletion(now);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println("Статус изменен на 'Выполнена', время выполнения: " + now.format(formatter));
            } else if (choice == 2) {
                task.setStatus("В процессе");
                task.setDateOfCompletion(null);
                System.out.println("Статус изменен на 'В процессе'.");
            } else if (choice == 3) {
                task.setStatus("Невыполнена");
                task.setDateOfCompletion(null);
                System.out.println("Статус изменен на 'Невыполнена'");
            } else {
                System.out.println("Неверный выбор");
            }
        } else {
            System.out.println("Задача не найдена");
        }
    }

    public void showStatistics() {
        if (list.isEmpty()) {
            System.out.println("Список задач пуст");
            return;
        }

        double averagePriority = list.stream()
                .mapToInt(DailyTasks::getPriority)
                .average()
                .orElse(0.0);

        System.out.println("\nСтатистика задач:");
        System.out.println("Всего задач: " + list.size());
        System.out.printf("Средний приоритет: %.1f\n", averagePriority);

        Map<String, List<DailyTasks>> groupedTasks = list.stream()
                .collect(Collectors.groupingBy(DailyTasks::getStatus));

        String[] statuses = {"Выполнена", "В процессе", "Невыполнена"};
        for (String status : statuses) {
            List<DailyTasks> tasks = groupedTasks.getOrDefault(status, Collections.emptyList());
            System.out.println(status + ": " + tasks.size());
        }

        System.out.println("\nГруппировка по статусам:");
        for (String status : statuses) {
            List<DailyTasks> tasks = groupedTasks.getOrDefault(status, Collections.emptyList());
            System.out.println("\n[ " + status + " ]");
            if (tasks.isEmpty()) {
                System.out.println("  Задач нет.");
            } else {
                for (DailyTasks task : tasks) {
                    System.out.println(task.getName() + " (Приоритет: " + task.getPriority() + ")");
                }
            }
        }
    }

    public void exitProgram() {
        System.out.println("Программа завершена");
        System.exit(0);
    }

    public void printTasks() {
        if (list.isEmpty()) {
            System.out.println("Список задач пуст");
            return;
        }

        System.out.println("Как отобразить задачи?");
        System.out.println("1. Сортировка по приоритету (по убыванию)");
        System.out.println("2. Сортировка по дате создания");

        if (!scanner.hasNextInt()) {
            System.out.println("Неверный ввод");
            scanner.nextLine();
            return;
        }
        int sortChoice = scanner.nextInt();

        System.out.println("\nСписок задач:");
        List<DailyTasks> tasksToPrint = list;

        if (sortChoice == 1) {
            tasksToPrint = list.stream()
                    .sorted((t1, t2) -> Integer.compare(t2.getPriority(), t1.getPriority()))
                    .collect(Collectors.toList());
        } else if (sortChoice == 2) {
            tasksToPrint = list.stream()
                    .sorted(Comparator.comparing(DailyTasks::getDateOfCreation))
                    .collect(Collectors.toList());
        }

        int counter = 1;
        for (DailyTasks task : tasksToPrint) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
    }
}
