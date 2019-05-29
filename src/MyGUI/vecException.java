package MyGUI;

public class vecException extends Exception  {
    String line;
    public vecException(String line) {
        this.line = line;
    }

    public String toString() {
        return "vecException[" + line + "]";
    }
}
