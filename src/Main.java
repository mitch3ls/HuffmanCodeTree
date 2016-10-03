
public class Main {
    public static void main(String[] args) {
        CodeTree tree = (CodeTree) TreeBuilder.generateTree("MISSISSIPPI");
        System.out.println(tree.createTable());

        String code = tree.encode("MISSISSIPPI");

        System.out.println(code);
        System.out.println(tree.decode(code));
    }
}
