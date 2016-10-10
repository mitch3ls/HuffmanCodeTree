
public class Main {
    public static void main(String[] args) {
        CodeTree tree = (CodeTree) CodeTree.generateTree("MISSISSIPPI");

        String code = tree.encode("MISSISSIPPI");

        System.out.println(code);
        System.out.println(tree.decode(code));
    }
}
