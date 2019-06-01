package MyGUI;

public class InvalidCommand extends Exception  {
    private String line;
    public InvalidCommand(String line) {
        this.line = line;
    }

    public String toString() {
        return "InvalidCommand [" + line + "]";
    }
}
