import java.util.Random;

public class Cat extends Animal{
    private final int LIMIT_RUN;
    private final int LIMIT_JUMP;
    private static final Random RANDOM = new Random();

    public Cat() {
        LIMIT_JUMP = RANDOM.nextInt(2) + 1;
        LIMIT_RUN = RANDOM.nextInt(200) + 100;
    }

    @Override
    public void jump(int height) {
        System.out.println(height <= LIMIT_JUMP);
    }

    @Override
    public void run(int distance) {
        System.out.println(distance <= LIMIT_RUN);
    }

    @Override
    public void swim(int length) {
        System.out.println(false);
    }
}
