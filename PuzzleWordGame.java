import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class PuzzleWordGame {
    private PuzzleWordGame() { }
    private static final String[] WORDS = {"apple", "apricot", "avocado", "banana", "broccoli", "carrot", "cherry",
            "garlic", "grape", "kiwi", "leak", "lemon", "mango", "melon", "mushroom", "nut", "olive", "orange", "pea",
            "peanut", "pear", "pepper", "pineapple", "potato", "pumpkin"};
    private static String getRandomWord() {
        return WORDS[new Random().nextInt(WORDS.length)];
    }

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static String readLine() {
        String s = null;
        try {
            s = READER.readLine().toLowerCase().strip();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    private static void close() {
        try {
            READER.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final StringBuilder BUILDER = new StringBuilder("###############");

    public static void begin() {
        String puzzled = getRandomWord();
        String input = null;

        while (true) {
            System.out.println("input word...");
            input = readLine();
            if (input.equals(puzzled)) {
                System.out.println("that's right, buddy!");
                if (again()) {
                    puzzled = getRandomWord();
                } else break;
            } else printMatch(puzzled, input);
        }
        close();
    }

    private static boolean again() {
        System.out.println("do it again? y/n");
        return readLine().equals("y");
    }

    private static void printMatch(String puzzled, String input) {
        char[] puzzledCharArray = puzzled.toCharArray();
        char[] inputCharArray = input.toCharArray();
        for (int i = 0; i < BUILDER.length(); i++) {
            if (i < puzzledCharArray.length && i < inputCharArray.length && puzzledCharArray[i] == inputCharArray[i]) {
                BUILDER.setCharAt(i, puzzledCharArray[i]);
            }
        }
        System.out.println("not exactly\n" + BUILDER.toString());
    }
}
