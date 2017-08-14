package com.workingbit.history.domain;

import com.github.rutledgepaulv.prune.Tree;
import com.workingbit.history.domain.impl.BoardTreeNode;
import com.workingbit.share.domain.impl.BoardContainer;

import java.io.Serializable;
import java.util.Optional;

/**
 * Created by Aleksey Popryaduhin on 10:27 13/08/2017.
 */
public interface IBoardHistory extends Serializable {

  Tree.Node<Optional<BoardContainer>> addBoard(BoardContainer boardContainer);

  boolean canUndo();

  boolean canRedo();

  boolean canRedo(Tree.Node<Optional<BoardContainer>> branch);

  void moveUp();

  void moveDown(Tree.Node<Optional<BoardContainer>> branch);

  void moveDown();

  Tree.Node<Optional<BoardContainer>> getLast();

  Tree<Tree.Node<BoardContainer>> getTree();

  String getJson();

  BoardTreeNode fromJson(String json);
}
