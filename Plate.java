public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void addFood(int n) {
        food += n;
    }

    public void decreaseFood(int n) {
        if (n <= food) {
            food -= n;   
        }
    }

    public void info() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "food: " + food;
    }
}
