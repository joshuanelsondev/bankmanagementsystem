package joshuanelsondev.bankmanagementsystem;
import java.util.Scanner;

public class InputUtils {
    private final Scanner scanner;

    public InputUtils() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner createScannerFromString(String input) {
        return new Scanner(input);
    }

    public String readString() {
        String text = scanner.nextLine();
        System.out.println(text);
        return text;
    }

    public int readInt() {
       return scanner.nextInt();
    }

    public double readDouble() {
        return scanner.nextDouble();
    }
}
