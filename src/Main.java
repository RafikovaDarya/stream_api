import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(x -> x.getAge() < 18).count();
        System.out.println(count);

        List<String> army = persons.stream()
                .filter(x -> x.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 17 && x.getAge() < 28)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        army.forEach(System.out::println);

        List<Person> workers = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() > 17)
                .filter(x -> (x.getSex() == Sex.MAN && x.getAge() < 66) ||
                        (x.getSex() == Sex.WOMAN && x.getAge() < 61))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        workers.forEach(System.out::println);


    }
}