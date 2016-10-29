import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.Exception;
import java.lang.String;
import java.lang.StringBuffer;

public class UpdateElementTypes {

  public static String FLAG = "{new}";

  public static void main(String[] args) {
    UpdateElementTypes ob = new UpdateElementTypes();
    if (args.length  < 2) {
      System.out.println("[java] Incorrect provided arguments.");
      ob.displayInfoMessage();
      return;
    }

    File file = new File(args[0]);
    if (!file.exists()) {
      System.out.println("[java] Unbla to find the specified file.");
      System.out.println("[java]   " + args[0]);
      ob.displayInfoMessage();
      return;
    }

    BufferedReader reader;
    StringBuffer buffer = new StringBuffer();
    try {
      reader = new BufferedReader(new FileReader(file));
      String line;
      while ((line = reader.readLine()) != null) {
        boolean addEnum = false;
        if (line.contains(UpdateElementTypes.FLAG)) {
          line = line.replace("//" + UpdateElementTypes.FLAG, ",");
          addEnum = true;
        }
        buffer.append(line).append("\n");
        if (addEnum) {
          buffer.append("  ").append(args[1]).append("//").append(UpdateElementTypes.FLAG).append("\n");
        }
      }
      reader.close();
    } catch (Exception e) {
      System.out.println("[java] Error: " + e.getMessage());
    }

    System.out.println(buffer.toString());
  }

  public void displayInfoMessage() {
    System.out.println("[java] Info:");
    System.out.println("[java] To run the program -> java UpdateElementTypes elementTypesPath newEnum");
    System.out.println("[java] e.g. ");
  }
}