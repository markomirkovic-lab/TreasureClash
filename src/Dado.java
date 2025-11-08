public class Dado {
    private static final int NUMERO_FACCE = 6;

    public static int lancia() {
        return (int) (Math.random() * NUMERO_FACCE) + 1;
    }
}