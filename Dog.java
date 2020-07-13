import java.util.Random;

public class Dog extends Animal {
    public Dog() {
        MIN_JUMP = 0.5f;
        MIN_RUN = 400;
        MIN_SWIM = 10;
        MAX_JUMP = MIN_JUMP + RANDOM.nextFloat() * (1 - MIN_JUMP);
        MAX_RUN = MIN_RUN + RANDOM.nextInt() * (600 - MIN_RUN);
        MAX_SWIM = MIN_SWIM + RANDOM.nextInt() * (20 - MIN_SWIM);
    }
}
