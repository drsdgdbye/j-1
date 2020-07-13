import java.util.Random;

public class Cat extends Animal {
    public Cat() {
        MIN_JUMP = 1;
        MIN_RUN = 200;
        MAX_JUMP = MIN_JUMP + RANDOM.nextFloat() * (2 - MIN_JUMP);
        MAX_RUN = MIN_RUN + RANDOM.nextInt() * (300 - MIN_RUN);
        MAX_SWIM = 0;
    }
}
