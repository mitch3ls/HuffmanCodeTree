import java.util.*;
import java.util.stream.Stream;

public class TreeBuilder {
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
}
