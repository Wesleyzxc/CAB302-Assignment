package MyGUI;

public class InvalidCommand extends Exception  {
    String line;
    public InvalidCommand(String line) {
        this.line = line;
    }

    public String toString() {
        return "InvalidCommand [" + line + "]";
    }
}
