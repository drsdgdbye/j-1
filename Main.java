public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        Animal dog = new Dog();

        cat.swim(10);
        dog.swim(10);

        cat.jump(10);
        dog.jump(1);

        cat.run(100);
        dog.run(600);
    }
}
