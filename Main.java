import java.util.stream.Stream;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Plate fromPlate = new Plate(100);
        Cat[] cats = new Cat[10];

        Random random = new Random();
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("cat" + i, random.nextInt(i + 1) * 10);
        }

        Stream.of(cats).forEach(cat -> {cat.eat(fromPlate); cat.info();});
    }
}
