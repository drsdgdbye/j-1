import java.util.Random;

public class Dog extends Animal{
    private final int LIMIT_RUN;
    private final int LIMIT_JUMP;
    private final int LIMIT_SWIM;
    private static final Random RANDOM = new Random();

    public Dog() {
        this.LIMIT_JUMP = RANDOM.nextInt(2) + 1;
        this.LIMIT_RUN = RANDOM.nextInt(400) + 200;
        this.LIMIT_SWIM = RANDOM.nextInt(10) + 10;
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
        System.out.println(length <= LIMIT_SWIM);
    }
}
