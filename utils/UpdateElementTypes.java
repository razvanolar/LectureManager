import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.Exception;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.System;

public class UpdateElementTypes {

  public static String FLAG = "{new}";

  public static void main(String[] args) {
    UpdateElementTypes ob = new UpdateElementTypes();
    if (args.length  < 2) {
      System.out.println("[java] Incorrect provided arguments.");
      ob.displayInfoMessage();
      System.exit(1);
    }

    File elementTypes = new File(args[0]);
    if (!elementTypes.exists()) {
      ob.fileNotFound(args[0]);
    }

    String typesFile = ob.updateElementTypes(elementTypes, args[1]);
    System.out.println("[java] Write file " + elementTypes.getName());
    ob.writeToFile(typesFile, elementTypes);
  }

  public String updateElementTypes(File elementTypes, String newType) {
    BufferedReader reader;
    StringBuffer buffer = new StringBuffer();
    try {
      reader = new BufferedReader(new FileReader(elementTypes));
      String line;
      System.out.println("[java] Reading " + elementTypes.getName());
      while ((line = reader.readLine()) != null) {
        boolean addEnum = false;
        if (line.contains(UpdateElementTypes.FLAG)) {
          System.out.println("[java] Update tagged line");
          line = line.replace("//" + UpdateElementTypes.FLAG, ",");
          addEnum = true;
        }
        buffer.append(line).append("\n");
        if (addEnum) {
          System.out.println("[java] Add " + newType + " enum");
          buffer.append("  ").append(newType).append("//").append(UpdateElementTypes.FLAG).append("\n");
        }
      }
      buffer.replace(buffer.length() - 1, buffer.length(), "");
      reader.close();
    } catch (Exception e) {
      System.out.println("[java] Error: " + e.getMessage());
      System.exit(1);
    }
    return buffer.toString();
  }

  public void writeToFile(String content, File file) {
    PrintWriter writer;
    try {
      writer = new PrintWriter(file);
      writer.print(content);
      writer.close();
    } catch (Exception e) {
      System.out.println("[java] Error: " + e.getMessage());
      System.exit(1);
    }
  }

  public void fileNotFound(String file) {
    System.out.println("[java] Unable to find the specified file.");
    System.out.println("[java]   " + file);
    displayInfoMessage();
    System.exit(1);
  }

  public void displayInfoMessage() {
    System.out.println("[java] Info:");
    System.out.println("[java] To run the program -> java UpdateElementTypes elementTypesPath newEnum");
    System.out.println("[java] e.g. ");
  }
}