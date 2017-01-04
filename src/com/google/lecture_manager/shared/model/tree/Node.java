package com.google.lecture_manager.shared.model.tree;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by razvanolar on 04.01.2017
 */
public class Node<T> implements IsSerializable {

  private T node;
  private List<Node<T>> children;

  public Node() {}

  public Node(T value) {
    node = value;
    children = new ArrayList<Node<T>>();
  }

  public void addChild(Node<T> child) {
    children.add(child);
  }

  public void addChildren(List<Node<T>> children) {
    this.children.addAll(children);
  }

  public T getValue() {
    return node;
  }

  public List<Node<T>> getChildren() {
    return children;
  }
}
