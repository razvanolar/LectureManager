package com.google.lecture_manager.server.utils;

import com.google.lecture_manager.shared.FileTypes;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.LectureDTO;
import com.google.lecture_manager.shared.model.tree.Node;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.*;

/**
 * Created by razvanolar on 24.01.2017
 */
public class FileUtil {

  public static final String LECTURES_PATH = "lecturemanager\\app_files\\lectures\\";
  public static String DISK_PATH;

  public static String getPathForLecture(LectureDTO lecture) {
    return getPathForLecture(lecture.getId());
  }

  public static String getPathForLecture(int lectureId) {
    return LECTURES_PATH + lectureId + "\\";
  }

  public static String getRelativeLecturePath(String path) {
    int index = path.indexOf(LECTURES_PATH);
    if (index != -1)
      return path.substring(index);
    return path;
  }

  public static Node<FileData> getHierarchyForLecture(LectureDTO lecture) {
    String lecturePath = getPathForLecture(lecture);
    File rootFile = new File(lecturePath);
    if (!rootFile.exists() && !rootFile.mkdirs())
      return null;
    Node<FileData> rootNode = new Node<>(new FileData(lecture.getLectureName(), lecturePath, FileTypes.FOLDER, rootFile.length(), new Date(rootFile.lastModified())));
    File[] files = rootFile.listFiles();
    if (files == null)
      return rootNode;

    Queue<Node<FileData>> queue = new LinkedList<>();
    List<Node<FileData>> children = createFileNodesFromFiles(files);
    queue.addAll(children);
    rootNode.addChildren(children);

    while (!queue.isEmpty()) {
      Node<FileData> node = queue.poll();
      if (node.getValue().getType() == FileTypes.FOLDER) {
        File[] fileContents = getFileContents(node.getValue().getPath());
        children = createFileNodesFromFiles(fileContents);
        node.addChildren(children);
        queue.addAll(children);
      }
    }

    return rootNode;
  }

  private static File[] getFileContents(String path) {
    File file = new File(path);
    if (!file.exists())
      return null;
    if (!file.isDirectory())
      return null;

    return file.listFiles();
  }

  public static void loadDiskPath() throws IOException {
    File file = new File(FileUtil.class.getResource("pathConfig.txt").getPath());
    BufferedReader br = new BufferedReader(new FileReader(file));
    FileUtil.DISK_PATH = br.readLine();
    br.close();
  }

  public static void deleteFile(String path) throws Exception {
    File file = new File(path);
    FileUtils.forceDelete(file);

    String fileProjPath = FileUtil.DISK_PATH + path;
    File projFile = new File(fileProjPath);
    FileUtils.forceDelete(projFile);
  }

  private static List<Node<FileData>> createFileNodesFromFiles(File[] files) {
    List<Node<FileData>> result = new ArrayList<Node<FileData>>();
    if (files == null)
      return result;
    for (File file : files) {
      FileTypes type = file.isDirectory() ? FileTypes.FOLDER : FileTypes.REGULAR;
      result.add(new Node<FileData>(
              new FileData(file.getName(), getRelativeLecturePath(file.getAbsolutePath()), type, file.length(), new Date(file.lastModified()))));
    }
    return result;
  }
}
