import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellChecker {
    public static void main(String[] args) {

        // Step 1: Demonstrate tree capabilities
        //testTree();

        // Step 2: Read the dictionary and report the tree statistics
        BinarySearchTree<String> dictionary = readDictionary();
        //reportTreeStats(dictionary);

        // Step 3: Perform spell checking
        readLetter();
    }

    /**
     * This method is used to help develop the BST, also for the grader to
     * evaluate if the BST is performing correctly.
     */
    public static void testTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        //
        // Add a bunch of values to the tree
        tree.insert("Olga");
        tree.insert("Tomeka");
        tree.insert("Benjamin");
        tree.insert("Ulysses");
        tree.insert("Tanesha");
        tree.insert("Judie");
        tree.insert("Tisa");
        tree.insert("Santiago");
        tree.insert("Chia");
        tree.insert("Arden");

        //
        // Make sure it displays in sorted order
        tree.display("--- Initial Tree State ---");
        reportTreeStats(tree);

        //
        // Try to add a duplicate
        if (tree.insert("Tomeka")) {
            System.out.println("oops, shouldn't have returned true from the insert");
        }
        tree.display("--- After Adding Duplicate ---");
        reportTreeStats(tree);

        //
        // Remove some existing values from the tree
        tree.remove("Olga");    // Root node
        tree.remove("Arden");   // a leaf node
        tree.display("--- Removing Existing Values ---");
        reportTreeStats(tree);

        //
        // Remove a value that was never in the tree, hope it doesn't crash!
        tree.remove("Karl");
        tree.display("--- Removing A Non-Existent Value ---");
        reportTreeStats(tree);
    }

    /**
     * This method is used to report on some stats about the BST
     */
    public static void reportTreeStats(BinarySearchTree<String> tree) {
        System.out.println("-- Tree Stats --");
        System.out.printf("Total Nodes : %d\n", tree.numberNodes());
        System.out.printf("Leaf Nodes  : %d\n", tree.numberLeafNodes());
        System.out.printf("Tree Height : %d\n", tree.height());
    }

    /**
     * This method reads the dictionary and constructs the BST to be
     * used for the spell checking.
     */
    public static BinarySearchTree<String> readDictionary() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        ArrayList<String> ordered = new ArrayList<>();

        File file = new File("C:\\Users\\jrsja\\Downloads\\Assign9 - Spell Checker\\Assign9 - Spell Checker\\Dictionary.txt");
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String word = input.nextLine();
                //System.out.println(word);
                ordered.add(word);
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the file: " + ex);
        }
        //at this point i have an arrayList 'ordered', i want to now shuffle it then add each item into my tree
        java.util.Collections.shuffle(ordered);
        for(int i=0; i<ordered.size(); i++) {
            tree.insert(ordered.get(i));
        }

        return tree;
    }

    public static void readLetter() {
        ArrayList<String> letterWords = new ArrayList<>();

        File file = new File("C:\\Users\\jrsja\\Downloads\\Assign9 - Spell Checker\\Assign9 - Spell Checker\\Letter.txt");
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] words = line.split(" ");
                for(String word : words) {
                    word=word.replace(",","").replace(".","").replace("(","").replace(")","").replace("!","").replace("?","").replace(":","").replace("\n","").replace("\"","");
                    word=word.toLowerCase();
                    if(!word.isEmpty()) {
                        //System.out.println(word);
                        letterWords.add(word);
                    }
                }
            }
        }
        catch (java.io.IOException ex) {
            System.out.println("An error occurred trying to read the file: " + ex);
        }

        BinarySearchTree<String> dictionary = readDictionary();

        for(String word : letterWords) {
            if(!dictionary.search(word)) { //word is misspelled
                System.out.println(word);
            }
        }

    }
}
