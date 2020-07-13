import java.util.Random;

public abstract class Animal implements CanRun, CanJump, CanSwim {
    protected static final Random RANDOM = new Random();
    protected int MAX_RUN;
    protected float MAX_JUMP;
    protected int MAX_SWIM;
    protected int MIN_RUN;
    protected int MIN_SWIM;
    protected float MIN_JUMP;

    @Override
    public void jump(float height) {
        System.out.println(height <= MAX_JUMP);
    }

    @Override
    public void run(int distance) {
        System.out.println(distance <= MAX_RUN);
    }

    @Override
    public void swim(int length) {
        System.out.println(length <= MAX_SWIM);
    }
}
