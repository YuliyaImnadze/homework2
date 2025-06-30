package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // 1
        List<Integer> numbers = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);
        int thirdMax = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("В списке меньше 3 элементов"));
        System.out.println("3-е наибольшее число: " + thirdMax);

        // 2
        int thirdUniqueMax = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("В списке меньше 3 элементов"));
        System.out.println("3-е наибольшее уникальное число: " + thirdUniqueMax);

        // 3
        List<Employee> employees = createEmployees();
        List<String> engineers = employees.stream()
                .filter(e -> e.getPosition().equals("Инженер"))
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(3)
                .map(Employee::getName)
                .toList();
        System.out.println("Список имен 3 самых старших сотрудников с должностью «Инженер» " + engineers);

        // 4
        double avgAge = employees.stream()
                .filter(e -> e.getPosition().equals("Инженер"))
                .mapToInt(Employee::getAge)
                .average()
                .orElse(0);
        System.out.println("Средний возраст сотрудников с должностью «Инженер» " + avgAge);

        // 5
        List<String> list = List.of("кредит", "квартира", "параллелепипед", "музыка");
        String longest = list.stream()
                .max(Comparator.comparingInt(String::length))
                .get();
        System.out.println("Самое длинное слово: " + longest);

        // 6
        String text = "кредит квартира параллелепипед музыка квартира";
        Map<String, Long> wordCountMap = Arrays.stream(text.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Слово и количество: " + wordCountMap);

        // 7
        List<String> sorted = list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .toList();
        System.out.println("Отсортированные строки: " + sorted);

        // 8
        String[] array = {
                "кредит квартира параллелепипед музыка лес",
                "небо кроет вихри снежные крутя",
                "звук поставим на всю и"
        };
        String longestWord = Arrays.stream(array)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElse("нет слов");
        System.out.println("Самое длинное слово в массиве строк: " + longestWord);
    }

    private static List<Employee> createEmployees() {
        return List.of(
                new Employee("Паша", 40, "Инженер"),
                new Employee("Маша", 30, "Инженер"),
                new Employee("Саша", 45, "Инженер"),
                new Employee("Даша", 60, "Аналитик"),
                new Employee("Инна", 32, "Менеджер"),
                new Employee("Леша", 38, "Тестировщик")
        );

    }
}