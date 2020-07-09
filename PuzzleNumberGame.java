import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class PuzzleNumberGame {
    private PuzzleNumberGame(){ }
    
    public static void main(String[] args) {
        PuzzleNumberGame.begin();
    }

   private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
   private static final Random random = new Random();

    public static void begin() {
        int puzzled = getRandom();
        int count = 0;
        int input = 0;

        while (true) {
            System.out.println("input number 0..9");
            input = readLine();
            if (input == puzzled) {
                System.out.println("that's right, buddy!");
                if (again()) {
                    puzzled = getRandom();
                } else break;
            } else if (input > puzzled) {
                System.out.println("less");
            } else System.out.println("more");
            count++;
            if (count == 3) {
                System.out.println("no more try");
                if (again()) {
                    puzzled = getRandom();
                } else break;
            }
        }
        close();
    }

    private static boolean again() {
        System.out.println("do it again?");
        return readLine() == 1;
    }

    private static int readLine() {
        int n = 0;
        try {
            n = Integer.parseInt(READER.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return n;
    }

    private static void close() {
        try {
            READER.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getRandom() {
        return random.nextInt(10);
    }
}