package com.google.lecture_manager.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.lecture_manager.client.utils.services.LectureService;
import com.google.lecture_manager.shared.FileTypes;
import com.google.lecture_manager.shared.model.FileData;
import com.google.lecture_manager.shared.model.tree.Node;
import com.google.lecture_manager.shared.model.tree.Tree;

/**
 * Created by razvanolar on 04.01.2017
 */
public class LectureServiceImpl extends RemoteServiceServlet implements LectureService {

  @Override
  public Tree<FileData> getLecturesFilesForUser(long userId) throws Exception {
    Tree<FileData> tree = new Tree<>();
    Node<FileData> node1 = new Node<>(new FileData("Lecture_1", "1", FileTypes.FOLDER, -1, null));
    Node<FileData> node2 = new Node<>(new FileData("Lecture_2", "2", FileTypes.FOLDER, -1, null));
    Node<FileData> node3 = new Node<>(new FileData("Lecture_3", "3", FileTypes.FOLDER, -1, null));
    Node<FileData> node4 = new Node<>(new FileData("Lecture_4", "4", FileTypes.FOLDER, -1, null));

    Node<FileData> node11 = new Node<>(new FileData("lecture1_file1", "lecturemanager\\app_files\\lectures\\1\\generateComponent.bat", FileTypes.REGULAR, 125, null));
    Node<FileData> node12 = new Node<>(new FileData("lecture1_file2", "lecturemanager\\app_files\\lectures\\1\\generateComponent.py", FileTypes.REGULAR, 225, null));
    Node<FileData> node13 = new Node<>(new FileData("lecture1_file3", "lecturemanager\\app_files\\lectures\\1\\generateEvent.bat", FileTypes.REGULAR, 325, null));

    Node<FileData> node21 = new Node<>(new FileData("lecture2_file1", "lecturemanager\\app_files\\lectures\\1\\mysqlDumpScript.bat", FileTypes.REGULAR, 125, null));
    Node<FileData> node22 = new Node<>(new FileData("lecture2_file2", "22", FileTypes.REGULAR, 225, null));
    Node<FileData> node23 = new Node<>(new FileData("lecture2_file3", "23", FileTypes.REGULAR, 325, null));

    node1.addChild(node11);
    node1.addChild(node12);
    node1.addChild(node13);

    node2.addChild(node21);
    node2.addChild(node22);
    node2.addChild(node23);

    tree.addRoot(node1);
    tree.addRoot(node2);
    tree.addRoot(node3);
    tree.addRoot(node4);
    return tree;
  }
}
