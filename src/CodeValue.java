import java.util.HashMap;
import java.util.Map;

public class CodeValue extends TreeNode{
    private char value;
    private int occurrences;

    public CodeValue(char value, String message) {
        this.value = value;
        occurrences = getOccurrences(message);
    }

    @Override
    public int getOccurrences(String message) {
        occurrences =  message.length() - message.replace(Character.toString(value), "").length();
        return getOccurrences();
    }

    @Override
    public int getOccurrences() {
        return occurrences;
    }

    @Override
    public Map createTable() {
        Map<Character, String> table =  new HashMap<>();

        table.put(value, parent.getCode(this));

        return table;
    }

    @Override
    public String toString() {
        return Character.toString(value);
    }

    @Override
    public String decode(String encodedMessage, CodeTree caller) {
        return Character.toString(value) + caller.decode(encodedMessage);
    }
}
