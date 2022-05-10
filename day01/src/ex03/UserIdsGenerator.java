package ex03;

public final class UserIdsGenerator {
    private static UserIdsGenerator single_instance = null;

    private int id;

    private UserIdsGenerator() {
        this.id = 0;
    }

    public int generateId() {
        if (id == Integer.MAX_VALUE) {
            System.err.println("id == Integer.MAX_VALUE");
            System.exit(-1);
        }
        return ++id;
    }

    public static UserIdsGenerator getInstance() {
        if (single_instance == null)
            single_instance = new UserIdsGenerator();
            return single_instance;
    }

}
