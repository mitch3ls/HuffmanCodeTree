import java.util.*;

public class CodeTree extends TreeNode {
    private TreeNode zero;
    private TreeNode one;

    public CodeTree(TreeNode zero, TreeNode one) {
        this.zero = zero;
        this.zero.setParent(this);
        this.one = one;
        this.one.setParent(this);
    }

    public static TreeNode generateTree(String message) {
        final Set<Character> chars = new LinkedHashSet<>();
        for (char character : message.toCharArray()) {
            chars.add(character);
        }

        ArrayList<TreeNode> nodes = new ArrayList<>();
        chars.forEach(character -> nodes.add(new CodeValue(character, message)));

        while(nodes.size() > 1) {
            Collections.sort(nodes);

            TreeNode last = nodes.get(nodes.size() - 1);
            TreeNode secondLast = nodes.get(nodes.size() - 2);

            nodes.remove(last);
            nodes.remove(secondLast);

            nodes.add(new CodeTree(last, secondLast));
        }

        return nodes.get(0);
    }

    public int getOccurrences(String message) {
        return zero.getOccurrences(message) + one.getOccurrences(message);
    }

    public int getOccurrences() {
        return zero.getOccurrences() + one.getOccurrences();
    }

    @Override
    public Map createTable() {
        Map<Character, String> table = new HashMap<>();

        table.putAll(zero.createTable());
        table.putAll(one.createTable());

        return table;
    }

    public String getCode(TreeNode node) {
        if (parent == null) return ((node == zero) ? "0" : "1");
        return parent.getCode(this) + ((node == zero) ? "0" : "1");
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

        if (firstDigit == '0') return zero.decode(encodedMessage.substring(1), caller);
        else return one.decode(encodedMessage.substring(1), caller);
    }
}
