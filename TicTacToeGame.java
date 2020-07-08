import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TicTacToeGame {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final List<List<Point>> pathsToWin = new ArrayList<>();
    private static final List<Point> humanPathToWin = new ArrayList<>();
    private static final Random random = new Random();
    private static final int SIZE = 5;
    private static final int DOTS_TO_WIN = 4;
    private static final char EMPTY = '•';
    private static final char X = 'X';
    private static final char O = 'O';
    private static char sideChar = X;
    private static char[][] map;
    private static int countMapSize;
    private static boolean humanFlag;
    private static int x;
    private static int y;
    private static boolean end;

    //this constants show way to shift. checkLine and other use it as counters
    private static final Point[] shiftX = {new Point(0, 1), new Point(0, -1)}; //from left to right
    private static final Point[] shiftY = {new Point(1, 0), new Point(-1, 0)}; //from up to down
    private static final Point[] shiftRightDiagonal = {new Point(-1, -1), new Point(1, 1)}; //right diagonal
    private static final Point[] shiftLeftDiagonal = {new Point(-1, 1), new Point(1, -1)}; //left diagonal

    private static void initMap() {
        map = new char[SIZE][SIZE];
        Arrays.stream(map).forEach(chars -> Arrays.fill(chars, EMPTY));
    }

    private static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String readLine() {
        String s = null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    private static void readerClose() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void chooseSide() {
        String input = null;
        do {
            System.out.println("choose X or O");
            input = readLine();
        } while (!isValidChar(input));
        if (input.equals("X")) {
            humanFlag = true;
        }
    }

    private static boolean isValidChar(String input) {
        return input.equals("X") || input.equals("O");
    }

    private static void humanTurn() {
        String input = null;
        int[] coordinates = null;
        System.out.println("input coordinates: x y");
        do {
            do {
                do {
                    input = readLine();
                } while (!isValidInput(input));
                coordinates = convertInputToCoordinates(input);
                x = coordinates[0];
                y = coordinates[1];
            } while (!isOnLimitCoordinates(x, y, true));
        } while (isBusyPoint());
        humanPathToWin.add(new Point(x, y));
        checkWinner();
        endTurn();
    }

    private static boolean isValidInput(String input) {
        if (input.isEmpty()) {
            System.out.println("input can't be empty. retry:");
            return false;
        }

        String[] inputArray = input.strip().split(" ");
        if (inputArray.length != 2) {
            System.out.println("input must be 2 numbers. retry:");
            return false;
        }

        try {
            Integer.parseInt(inputArray[0]);
            Integer.parseInt(inputArray[1]);
        } catch (NumberFormatException ignored) {
            System.out.printf("input must be numbers from 1 to %d. retry:%n", SIZE);
            return false;
        }
        return true;
    }

    private static int[] convertInputToCoordinates(String input) {
        String[] inputArray = input.strip().split(" ");
        int[] coordinates = new int[2];
        //for convenience human input 1..SIZE, we translate it to machine sizes.
        coordinates[0] = Integer.parseInt(inputArray[0]) - 1;
        coordinates[1] = Integer.parseInt(inputArray[1]) - 1;
        return coordinates;
    }

    private static boolean isOnLimitCoordinates(int x, int y, boolean msg) {
        if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
            return true;
        }
        //workaround for allow messages only if human makes a mistake in input
        if (msg) {
            System.out.println("coordinates is not limit. retry:");
        }
        return false;
    }

    private static boolean isBusyPoint() {
        if (map[x][y] == EMPTY) {
            return false;
        } else if (humanFlag) {
            System.out.println("coordinates is busy. retry:");
        }
        return true;
    }

    private static void aiTurn() {
        System.out.println("AI turn...");
        //to make it seem that the ai is thinking hard
        sleep();
        do {
            if (pathsToWin.isEmpty()) {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);

                calculatePathsToWin(shiftX, shiftY);
                calculatePathsToWin(shiftLeftDiagonal, shiftRightDiagonal);
                Collections.shuffle(pathsToWin);
            } else if (humanPathToWin.size() == 2) {
                List<Point> humanLineToWin = calculateHumanLineToWin();
                if (!humanLineToWin.isEmpty()) {
                    Point blockingPoint = findCrossBlockingPoint(humanLineToWin);
                    if (blockingPoint != null) {
                        x = blockingPoint.x;
                        y = blockingPoint.y;
                    } else {
                        Point randomPoint = humanLineToWin.get(random.nextInt(humanLineToWin.size()));
                        x = randomPoint.x;
                        y = randomPoint.y;
                    }
                }
            } else {
                int last = pathsToWin.size() - 1;
                List<Point> lastLineToWin = pathsToWin.get(last);
                if (isPotentialWin(lastLineToWin)) {
                    Point randomPoint = lastLineToWin.get(random.nextInt(lastLineToWin.size()));
                    x = randomPoint.x;
                    y = randomPoint.y;
                } else {
                    pathsToWin.remove(lastLineToWin);
                }
            }
        } while (isBusyPoint());

        checkWinner();
        endTurn();
    }

    //create two-point vector and calculate potential human turns
    private static List<Point> calculateHumanLineToWin() {
        List<Point> humanLineToWin = new ArrayList<>();

        int x0 = humanPathToWin.get(0).x;
        int x1 = humanPathToWin.get(1).x;
        int shiftX = Integer.compare(x1, x0);

        int y0 = humanPathToWin.get(0).y;
        int y1 = humanPathToWin.get(1).y;
        int shiftY = Integer.compare(y1, y0);

        int localX = x1 + shiftX;
        int localY = y1 + shiftY;
        while (isOnLimitCoordinates(localX, localY, false)) {
            humanLineToWin.add(new Point(localX, localY));
            localX += shiftX;
            localY += shiftY;
        }

        localX = x1 - shiftX;
        localY = y1 - shiftY;
        while (isOnLimitCoordinates(localX, localY, false)) {
            //we could add points from (x0, y0), but if distance between them >0 we lose this points
            //or find between them... and to write one more while()
            if (localX != x0 && localY != y0) {
                humanLineToWin.add(new Point(localX, localY));
            }
            localX -= shiftX;
            localY -= shiftY;
        }
        humanPathToWin.clear();

        return humanLineToWin;
    }

    private static Point findCrossBlockingPoint(List<Point> humanLineToWin) {
        Point crossPoint = null;
        List<Point> pathToWin = null;
        Point pathToWinPoint = null;
        Point humanLineToWinPoint = null;

        for (List<Point> points : pathsToWin) {
            pathToWin = points;
            for (Point point : pathToWin) {
                pathToWinPoint = point;
                for (Point humanPoint : humanLineToWin) {
                    humanLineToWinPoint = humanPoint;
                    if (pathToWinPoint.equals(humanLineToWinPoint)) {
                        crossPoint = humanLineToWinPoint;
                        break;
                    }
                }
            }
        }
        return crossPoint;
    }

    private static boolean isPotentialWin(List<Point> line) {
        if (line.size() < DOTS_TO_WIN) {
            return false;
        }
        //(••••X)
        int countFirst = 0;
        int x = 0, y = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            x = line.get(i).x;
            y = line.get(i).y;
            if (map[x][y] == EMPTY || map[x][y] == sideChar) {
                countFirst++;
            } else {
                break;
            }
        }
        //(X••••)
        int countLast = 0;
        for (int i = line.size() - DOTS_TO_WIN; i < line.size(); i++) {
            x = line.get(i).x;
            y = line.get(i).y;
            if (map[x][y] == EMPTY || map[x][y] == sideChar) {
                countLast++;
            } else {
                break;
            }
        }

        return countFirst >= DOTS_TO_WIN || countLast >= DOTS_TO_WIN;
    }

    private static void checkWinner() {
        map[x][y] = sideChar;
        countMapSize++;
        if (isWin() || isMapFull()) {
            printMap();
            String endGame = isMapFull() ? "draw!" : sideChar + " wins!";
            System.out.println("==================");
            System.out.println(endGame);
            System.out.println("==================");
            end = true;
        }
    }

    private static void endTurn() {
        x = 0;
        y = 0;
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void changeSide() {
        boolean change = humanFlag;
        humanFlag = !change;

        sideChar = (sideChar == X) ? O : X;
    }

    private static boolean isMapFull() {
        return countMapSize == SIZE * SIZE;
    }

    private static boolean isWin() {
        //no sense to check winner while one of rival will not accumulate DOT_TO_WIN sequence
        if (countMapSize < 2 * DOTS_TO_WIN - 1) {
            return false;
        }
        return checkXandYLines() || checkDiagonals();
    }

    private static boolean checkXandYLines() {
        return checkLines(shiftX, shiftY);
    }

    private static boolean checkDiagonals() {
        return checkLines(shiftLeftDiagonal, shiftRightDiagonal);
    }

    private static boolean checkLines(Point[] shiftLeft, Point[] shiftRight) {
        //1 for init point
        int countLeft = 1;
        countLeft += countRow(shiftLeft[0].x, shiftLeft[0].y);
        countLeft += countRow(shiftLeft[1].x, shiftLeft[1].y);

        int countRight = 1;
        countRight += countRow(shiftRight[0].x, shiftRight[0].y);
        countRight += countRow(shiftRight[1].x, shiftRight[1].y);

        return countLeft >= DOTS_TO_WIN || countRight >= DOTS_TO_WIN;
    }

    private static int countRow(int shiftX, int shiftY) {
        int localX = x + shiftX;
        int localY = y + shiftY;
        int count = 0;

        while (isOnLimitCoordinates(localX, localY, false)) {
            if (map[localX][localY] == sideChar) {
                count++;
            } else {
                break;
            }
            localX += shiftX;
            localY += shiftY;
        }
        return count;
    }

    private static void calculatePathsToWin(Point[] shiftLeft, Point[] shiftRight) {
        //paths for ai which will use for his turn
        List<Point> pathLeft = null;
        List<Point> pathRight = null;

        pathLeft = new ArrayList<>();
        pathRight = new ArrayList<>();
        //add initial coordinates
        pathLeft.add(new Point(x, y));
        pathRight.add(new Point(x, y));

        fillPathToWin(pathLeft, shiftLeft[0].x, shiftLeft[0].y);
        fillPathToWin(pathLeft, shiftLeft[1].x, shiftLeft[1].y);
        fillPathToWin(pathRight, shiftRight[0].x, shiftRight[0].y);
        fillPathToWin(pathRight, shiftRight[1].x, shiftRight[1].y);

        addPathToWin(pathLeft);
        addPathToWin(pathRight);
    }

    private static void fillPathToWin(List<Point> pathToWin, int shiftX, int shiftY) {
        int localX = x + shiftX;
        int localY = y + shiftY;
        while (isOnLimitCoordinates(localX, localY, false)) {
            pathToWin.add(new Point(localX, localY));

            localX += shiftX;
            localY += shiftY;
        }
    }

    private static void addPathToWin(List<Point> pathToWin) {
        if (isPotentialWin(pathToWin)) {
            pathsToWin.add(pathToWin);
        }
    }

    public static void main(String[] args) {
        initMap();
        chooseSide();
        while (true) {
            if (humanFlag) {
                printMap();
                humanTurn();
            } else {
                aiTurn();
            }
            if (end) {
                break;
            }
            changeSide();
        }
        readerClose();
    }
}
