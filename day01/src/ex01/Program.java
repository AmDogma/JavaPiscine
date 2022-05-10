package ex01;

public class Program {
    public static void main(String[] args) {
        UserIdsGenerator first = UserIdsGenerator.getInstance();
        UserIdsGenerator second = UserIdsGenerator.getInstance();
        System.out.println("first == second: " + (first == second));
        System.out.println("Hash codes: " + first.hashCode() + " " + second.hashCode() +
                " " + UserIdsGenerator.getInstance().hashCode());
        User Adam = new User("Adam", 400);
        User Eva = new User("Eva", 200);
        System.out.println(Adam + " " + Eva);

    }
}
