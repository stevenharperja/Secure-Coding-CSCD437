import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
import java.lang.ProcessBuilder; //running stuff
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
public class FakeWorm3 {
    public static void write(String filename,String content) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write(content);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void printLines(String cmd, InputStream ins) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(cmd+ " "+ line);
        }
    }

    private static void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command+ " stdout:", pro.getInputStream());
        printLines(command+ " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command+ " exitValue() "+ pro.exitValue());
    }

//basically everything from here down is NOT helper functions ripped from the internet
    public static void compile(int number){
        try {
            //Process p = Runtime.getRuntime().exec("javac FakeWorm"+ (number+ 1)+ ".java");
            //Process p = new ProcessBuilder("javac","FakeWorm"+(number+1)+".java").start();
            System.out.println("Compiling File number:"+ (number+1));
            //p.waitFor();
            runProcess("javac FakeWorm"+ (number+ 1)+ ".java");
        }
        catch(Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void run(int number){
        try {
            //Runtime.getRuntime().exec("java FakeWorm"+number+1);
            Process p = new ProcessBuilder("java","FakeWorm"+(number+1)).start();
            //System.out.println(p.getInputStream());
            System.out.println("Running the file.");
        }
        catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //worm stuff
        int number = 3;
        //my catch statement that i pray works
        if(number>=3) {
            System.out.println("WORM STOPPING!");
            System.exit(0);
        }

        String output = "";
        //print stuff
        for (int i = 0; i < text.length * 2; i++) {
            if (i >= text.length)
                output = output+ "          \"";
            char[] cur_string = text[i%text.length].toCharArray();
            for(int j = 0; j< cur_string.length;j++){
                char letter = cur_string[j];
                char next_letter = '~'; //i need a null, char doesnt have it. this is bad code tho
                if(j+1<text[i % text.length].length())
                    next_letter = cur_string[j+1];
                if ((letter == '\\' | letter == '\"') && i>=text.length ){
                    output = output+ '\\';
                }
                //if we got a grave mark, substitute number in there, but only if its not the string portion
                if ((letter == '`') && i<text.length && next_letter != '~' && cur_string[j+1] != '\''){
                    output = output+ (number+1);
                    j++;
                    letter = cur_string[j];
                }
                output = output+ letter;
            }
            if (i >= text.length) {
                output = output+ "\"";
                if (i < text.length*2 - 1) {
                    output = output+ ",";
                }
            }
            output = output+ "\n";
        }
        output = output+ "    };\n";
        output = output+ "}\n";
        //more worm stuff
        System.out.print(output);
        write("FakeWorm"+(number+1)+".java",output);

        compile(number);
        run(number);
    }

    public static String[] text =
    {
          "import java.io.File;  // Import the File class",
          "import java.io.IOException;  // Import the IOException class to handle errors",
          "import java.io.FileWriter;   // Import the FileWriter class",
          "import java.lang.ProcessBuilder; //running stuff",
          "import java.io.BufferedReader;",
          "import java.io.InputStream;",
          "import java.io.InputStreamReader;",
          "public class FakeWorm` {",
          "    public static void write(String filename,String content) {",
          "        try {",
          "            FileWriter myWriter = new FileWriter(filename);",
          "            myWriter.write(content);",
          "            myWriter.close();",
          "            System.out.println(\"Successfully wrote to the file.\");",
          "        } catch (IOException e) {",
          "            System.out.println(\"An error occurred.\");",
          "            e.printStackTrace();",
          "        }",
          "    }",
          "",
          "    private static void printLines(String cmd, InputStream ins) throws Exception {",
          "        String line = null;",
          "        BufferedReader in = new BufferedReader(",
          "                new InputStreamReader(ins));",
          "        while ((line = in.readLine()) != null) {",
          "            System.out.println(cmd+ \" \"+ line);",
          "        }",
          "    }",
          "",
          "    private static void runProcess(String command) throws Exception {",
          "        Process pro = Runtime.getRuntime().exec(command);",
          "        printLines(command+ \" stdout:\", pro.getInputStream());",
          "        printLines(command+ \" stderr:\", pro.getErrorStream());",
          "        pro.waitFor();",
          "        System.out.println(command+ \" exitValue() \"+ pro.exitValue());",
          "    }",
          "",
          "//basically everything from here down is NOT helper functions ripped from the internet",
          "    public static void compile(int number){",
          "        try {",
          "            //Process p = Runtime.getRuntime().exec(\"javac FakeWorm\"+ (number+ 1)+ \".java\");",
          "            //Process p = new ProcessBuilder(\"javac\",\"FakeWorm\"+(number+1)+\".java\").start();",
          "            System.out.println(\"Compiling File number:\"+ (number+1));",
          "            //p.waitFor();",
          "            runProcess(\"javac FakeWorm\"+ (number+ 1)+ \".java\");",
          "        }",
          "        catch(Exception e){",
          "            System.out.println(\"An error occurred.\");",
          "            e.printStackTrace();",
          "        }",
          "    }",
          "    public static void run(int number){",
          "        try {",
          "            //Runtime.getRuntime().exec(\"java FakeWorm\"+number+1);",
          "            Process p = new ProcessBuilder(\"java\",\"FakeWorm\"+(number+1)).start();",
          "            //System.out.println(p.getInputStream());",
          "            System.out.println(\"Running the file.\");",
          "        }",
          "        catch(IOException e){",
          "            System.out.println(\"An error occurred.\");",
          "            e.printStackTrace();",
          "        }",
          "    }",
          "",
          "    public static void main(String[] args) {",
          "        //worm stuff",
          "        int number = `;",
          "        //my catch statement that i pray works",
          "        if(number>=3) {",
          "            System.out.println(\"WORM STOPPING!\");",
          "            System.exit(0);",
          "        }",
          "",
          "        String output = \"\";",
          "        //print stuff",
          "        for (int i = 0; i < text.length * 2; i++) {",
          "            if (i >= text.length)",
          "                output = output+ \"          \\\"\";",
          "            char[] cur_string = text[i%text.length].toCharArray();",
          "            for(int j = 0; j< cur_string.length;j++){",
          "                char letter = cur_string[j];",
          "                char next_letter = '~'; //i need a null, char doesnt have it. this is bad code tho",
          "                if(j+1<text[i % text.length].length())",
          "                    next_letter = cur_string[j+1];",
          "                if ((letter == '\\\\' | letter == '\\\"') && i>=text.length ){",
          "                    output = output+ '\\\\';",
          "                }",
          "                //if we got a grave mark, substitute number in there, but only if its not the string portion",
          "                if ((letter == '`') && i<text.length && next_letter != '~' && cur_string[j+1] != '\\''){",
          "                    output = output+ (number+1);",
          "                    j++;",
          "                    letter = cur_string[j];",
          "                }",
          "                output = output+ letter;",
          "            }",
          "            if (i >= text.length) {",
          "                output = output+ \"\\\"\";",
          "                if (i < text.length*2 - 1) {",
          "                    output = output+ \",\";",
          "                }",
          "            }",
          "            output = output+ \"\\n\";",
          "        }",
          "        output = output+ \"    };\\n\";",
          "        output = output+ \"}\\n\";",
          "        //more worm stuff",
          "        System.out.print(output);",
          "        write(\"FakeWorm\"+(number+1)+\".java\",output);",
          "",
          "        compile(number);",
          "        run(number);",
          "    }",
          "",
          "    public static String[] text =",
          "    {"
    };
}
