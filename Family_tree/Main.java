package Family_tree;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FamilyTree tree = testTree();
        System.out.println(tree);
    }
    static FamilyTree testTree() {
        FamilyTree tree = new FamilyTree();

        Human vasya = new Human("Василий", LocalDate.of(1963, 12, 10), Gender.Male);
        Human masha = new Human("Мария", LocalDate.of(1965, 5, 18), Gender.Female);
        tree.add(masha);
        tree.add(vasya);

        Human chris = new Human("Крис", LocalDate.of(1945, 9, 1), Gender.Female, vasya, masha);
        Human senya = new Human("Сеня", LocalDate.of(1991, 1, 25), Gender.Male, masha, vasya);
        tree.add(chris);
        tree.add(senya);

        Human anya = new Human("Аня", LocalDate.of(1945, 4, 12), Gender.Female);
        anya.addChild(vasya);
        tree.add(anya);
        return tree;
    }
}
