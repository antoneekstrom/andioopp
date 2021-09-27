package andioopp.main;

/**
 * Entrypoint of the program.
 *
 * We need this file because maven would not run from a main class which extended another class.
 * Therefore, we just call on the {@link App#run(String[])} method.
 */
public class Main {
    public static void main(String[] args) {
        App.run(args);
    }
}
