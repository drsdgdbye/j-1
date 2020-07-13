public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        Animal dog = new Dog();

        cat.swim(10);
        dog.swim(11);

        cat.jump(1.2f);
        dog.jump(0.75f);

        cat.run(120);
        dog.run(550);
    }
}
