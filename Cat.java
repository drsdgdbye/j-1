public class Cat {
    private final String name;
    private final int appetite;
    private boolean fullness;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate plate) {
        if (plate.getFood() >= appetite) {
            plate.decreaseFood(appetite);
            fullness = true;
        }
    }

    public void info() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "fullness: " + fullness;
    }
}
