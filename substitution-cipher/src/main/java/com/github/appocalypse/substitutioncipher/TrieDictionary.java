package com.github.appocalypse.substitutioncipher;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.Consumer;

public class TrieDictionary {

    private final Root root;

    private TrieDictionary(Root root) {
        this.root = root;
    }

    public void printOut() {
        transverse(root.next, new StringBuilder(100), System.out::println);
    }

    private static void transverse(Node[] nodes, StringBuilder builder, Consumer<String> consumer) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                final int currentIndex = builder.length();

                final char ch = (char)('a' + i);
                builder.append(ch);
                if (nodes[i].terminal) {
                    consumer.accept(builder.toString());
                }

                transverse(nodes[i].next, builder, consumer);

                // delete new char in the buffer to reuse
                builder.deleteCharAt(currentIndex);
            }
        }
    }


    public static TrieDictionary initialize(Scanner scanner) {
        final Root root = new Root();
        while (scanner.hasNextLine()) {
            final String word = scanner.nextLine().toLowerCase();
            //System.out.println(word);
            insert(root, word);
        }

        return new TrieDictionary(root);
    }

    private static void insert(Root root, String word) {
        if (word.length() == 0) return;

        final int zeroIndex = word.charAt(0) - 'a';
        if (root.next[zeroIndex] == null)  {
            root.next[zeroIndex] = new Node(word.length() == 1);
        } else {
            root.next[zeroIndex].terminal = root.next[zeroIndex].terminal || word.length() == 1;
        }

        // node always store last node
        Node node = root.next[zeroIndex];

        for (int i = 1; i < word.length(); i++) {
            final char ch = word.charAt(i);
            final int index = ch - 'a';

            if (node.next[index] == null) {
                node.next[index] = new Node(word.length() == i+1);
            } else {
                node.next[index].terminal = node.next[index].terminal || word.length() == i+1;
            }

            node = node.next[index];
        }
    }

    protected static class Root {
        final Node[] next = new Node['z' - 'a' + 1];
    }

    protected static class Node {
        final Node[] next = new Node['z' - 'a' + 1];
        boolean terminal;

        public Node() {
            this(false);
        }

        public Node(boolean terminal) {
            this.terminal = terminal;
        }
    }
}
