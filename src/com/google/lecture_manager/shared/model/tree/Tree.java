package com.google.lecture_manager.shared.model.tree;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 04.01.2017
 */
public class Tree<T> implements IsSerializable {

  private List<Node<T>> roots;

  public Tree() {
    roots = new ArrayList<Node<T>>();
  }

  public void addRoots(List<Node<T>> roots) {
    this.roots.addAll(roots);
  }

  public void addRoot(Node<T> root) {
    this.roots.add(root);
  }

  public List<Node<T>> getRoots() {
    return roots;
  }
}
