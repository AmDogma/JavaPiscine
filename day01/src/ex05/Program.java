package ex05;

public class Program {
    public static void main(String[] args) {
        Menu menu;
        if (args.length == 1 && args[0].equals("--profile=dev"))
            menu = new Menu(7);
        else
            menu = new Menu(5);
        menu.start();
    }
}
