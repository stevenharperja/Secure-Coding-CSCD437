public class Quine {
    public static void main(String[] args) {
        //print stuff
        for (int i = 0; i < text.length * 2; i++) {
            if (i >= text.length)
                System.out.print("          \"");
            for(char letter : text[i % text.length].toCharArray()) {
                if ((letter == '\\' | letter == '\"') && i>=text.length ){
                    System.out.print('\\');
                }
                System.out.print(letter);
            }
            if (i >= text.length) {
                System.out.print("\"");
                if (i < text.length*2 - 1) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        System.out.println("    };");
        System.out.println("}");
    }

    public static String[] text =
    {
        "public class Quine {",
        "    public static void main(String[] args) {",
        "        //print stuff",
        "        for (int i = 0; i < text.length * 2; i++) {",
        "            if (i >= text.length)",
        "                System.out.print(\"          \\\"\");",
        "            for(char letter : text[i % text.length].toCharArray()) {",
        "                if ((letter == '\\\\' | letter == '\\\"') && i>=text.length ){",
        "                    System.out.print('\\\\');",
        "                }",
        "                System.out.print(letter);",
        "            }",
        "            if (i >= text.length) {",
        "                System.out.print(\"\\\"\");",
        "                if (i < text.length*2 - 1) {",
        "                    System.out.print(\",\");",
        "                }",
        "            }",
        "            System.out.println();",
        "        }",
        "        System.out.println(\"    };\");",
        "        System.out.println(\"}\");",
        "    }",
        "",
        "    public static String[] text =",
        "    {"
    };
}
