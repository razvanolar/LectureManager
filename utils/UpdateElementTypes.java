import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.Exception;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.System;

public class UpdateElementTypes {

  public static String ENUM_FLAG = "{new}";
  public static String CONTROLLER_FLAG = "{controller}";
  public static String VIEW_FLAG = "{view}";
  public static String CASE_FLAG = "{new_case}";

  public static void main(String[] args) {
    UpdateElementTypes ob = new UpdateElementTypes();
    if (args.length  != 6) {
      System.out.println("[java] Incorrect provided arguments.");
      ob.displayInfoMessage();
      System.exit(1);
    }
    ob.showArguments(args);

    File elementTypes = new File(args[0]);
    if (!elementTypes.exists()) {
      ob.fileNotFound(args[0]);
    }

    File controllerFactory = new File(args[1]);
    if (!controllerFactory.exists()) {
      ob.fileNotFound(args[1]);
    }

    File viewFactory = new File(args[2]);
    if (!viewFactory.exists()) {
      ob.fileNotFound(args[2]);
    }

    String typesFile = ob.updateElementTypes(elementTypes, args[5]);
    String controllerFile = ob.updateControllerFactory(controllerFactory, args[3], args[5]);
    String viewFile = ob.updateViewFactory(viewFactory, args[4], args[5]);
    System.out.println("[java] Write file " + elementTypes.getName());
    ob.writeToFile(typesFile, elementTypes);
    System.out.println("[java] Write file " + controllerFactory.getName());
    ob.writeToFile(controllerFile, controllerFactory);
    System.out.println("[java] Write file " + viewFactory.getName());
    ob.writeToFile(viewFile, viewFactory);
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
        if (line.contains(UpdateElementTypes.ENUM_FLAG)) {
          System.out.println("[java] Update tagged line");
          line = line.replace("//" + UpdateElementTypes.ENUM_FLAG, ",");
          addEnum = true;
        }
        buffer.append(line).append("\n");
        if (addEnum) {
          System.out.println("[java] Add " + newType + " enum");
          buffer.append("  ").append(newType).append("//").append(UpdateElementTypes.ENUM_FLAG).append("\n");
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

  public String updateControllerFactory(File file, String packageString, String enumType) {
    BufferedReader reader;
    StringBuffer buffer = new StringBuffer();
    try {
      reader = new BufferedReader(new FileReader(file));
      String line;
      System.out.println("[java] Reading " + file.getName());
      while ((line = reader.readLine()) != null) {
        if (line.contains(UpdateElementTypes.CONTROLLER_FLAG)) {
          System.out.println("[java] Add import controller line");
          buffer.append("import ").append(packageString).append(";\n");
        } else if (line.contains(UpdateElementTypes.CASE_FLAG)) {
          System.out.println("[java] Add new case branch");
          buffer.append("      case ").append(enumType).append(":\n");
          buffer.append("        return ").append(getClassNameFromPackage(packageString));
          buffer.append(".getInstance();\n");
        }
        buffer.append(line).append("\n");
      }
      buffer.replace(buffer.length() - 1, buffer.length(), "");
      reader.close();
    } catch (Exception e) {
      System.out.println("[java] Error: " + e.getMessage());
      System.exit(1);
    }
    return buffer.toString();
  }

  public String updateViewFactory(File file, String packageString, String enumType) {
    BufferedReader reader;
    StringBuffer buffer = new StringBuffer();
    try {
      reader = new BufferedReader(new FileReader(file));
      String line;
      System.out.println("[java] Reading " + file.getName());
      while ((line = reader.readLine()) != null) {
        if (line.contains(UpdateElementTypes.VIEW_FLAG)) {
          System.out.println("[java] Add import view line");
          buffer.append("import ").append(packageString).append(";\n");
        } else if (line.contains(UpdateElementTypes.CASE_FLAG)) {
          System.out.println("[java] Add new case branch");
          buffer.append("      case ").append(enumType).append(":\n");
          buffer.append("        return new ").append(getClassNameFromPackage(packageString)).append("();\n");
        }
        buffer.append(line).append("\n");
      }
      buffer.replace(buffer.length() - 1, buffer.length(), "");
      reader.close();
    } catch (Exception e) {
      System.out.println("[java] Error: " + e.getMessage());
      System.exit(1);
    }
    return buffer.toString();
  }

  public String getClassNameFromPackage(String packageString) {
    int index = packageString.lastIndexOf('.');
    return packageString.substring(index + 1);
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
    System.out.println("[java] To run the program -> java UpdateElementTypes elementTypesPath controllerFactoryPath " +
            "viewFactoryPath controllerPackage viewPackage newEnum");
    System.out.println("[java] e.g. ");
  }

  public void showArguments(String[] args) {
    System.out.println("[java] Specified arguments:");
    System.out.println("[java]   ElementTypes path: " + args[0]);
    System.out.println("[java]   ControllerFactory path: " + args[1]);
    System.out.println("[java]   ViewFactory path: " + args[2]);
    System.out.println("[java]   Controller package: " + args[3]);
    System.out.println("[java]   View package: " + args[4]);
    System.out.println("[java]   ElementTypes instace: " + args[5]);
  }
}