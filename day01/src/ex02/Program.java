package ex02;

public class Program {
    public static void main(String[] args) {
        User Adam = new User("Adam", 400);
        User Eva = new User("Eva", 200);
        UsersList list = new UsersArrayList();

        System.out.println("list.size() = " + list.size());
        list.add(Adam);
        list.add(Eva);
        System.out.println("list.size() = " + list.size());
        try {
            System.out.println("found " + list.getById(1).getName());
            System.out.println("found " + list.getById(2).getName());
            System.out.println("found " + list.getById(3).getName());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("found " + list.getByIndex(0).getName());
            System.out.println("found " + list.getByIndex(1).getName());
            System.out.println("found " + list.getByIndex(2).getName());

            System.out.println(list.getByIndex(-2));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
}
