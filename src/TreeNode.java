import java.util.Map;

abstract class TreeNode implements Comparable<TreeNode> {

    protected CodeTree parent;

    public abstract int getOccurrences(String message);
    public abstract int getOccurrences();
    public abstract String decode(String encodedMessage, CodeTree caller);

    public abstract Map createTable();

    public void setParent(CodeTree parent) {
        this.parent = parent;
    }

    @Override
    public abstract String toString();

    @Override
    public int compareTo(TreeNode node) {
        return Integer.compare(node.getOccurrences(), getOccurrences());
    }
}
