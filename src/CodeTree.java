import java.util.HashMap;
import java.util.Map;

public class CodeTree extends TreeNode {
    private TreeNode left;
    private TreeNode right;

    public CodeTree(TreeNode left, TreeNode right) {
        this.left = left;
        this.left.setParent(this);
        this.right = right;
        this.right.setParent(this);
    }

    public int getOccurrences(String message) {
        return left.getOccurrences(message) + right.getOccurrences(message);
    }

    public int getOccurrences() {
        return left.getOccurrences() + right.getOccurrences();
    }

    @Override
    public String toString() {
        return "<0>\n" + left + "\n</0>\n" +
                "<1>\n" + right + "\n</1>";
    }

    @Override
    public Map createTable() {
        Map<Character, String> table = new HashMap<>();

        table.putAll(left.createTable());
        table.putAll(right.createTable());

        return table;
    }

    public String getCode(TreeNode node) {
        if (parent == null) return ((node == left) ? "0" : "1");
        return parent.getCode(this) + ((node == left) ? "0" : "1");
    }

    public String encode(String message) {
        Map cipher = createTable();

        String encodedMessage = "";
        for (char character : message.toCharArray()) {
            encodedMessage += cipher.get(character);
        }
        return encodedMessage;
    }

    public String decode(String encodedMessage) {
        return decode(encodedMessage, this);
    }

    @Override
    public String decode(String encodedMessage, CodeTree caller) {
        if (encodedMessage.length() < 1) return "";

        char firstDigit = encodedMessage.toCharArray()[0];

        if (firstDigit == '0') return left.decode(encodedMessage.substring(1), caller);
        else return right.decode(encodedMessage.substring(1), caller);
    }
}
