package com.workingbit.share.domain.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workingbit.board.common.DBConstants;
import com.workingbit.share.domain.BaseDomain;
import com.workingbit.share.model.EnumRules;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Aleksey Popryaduhin on 19:54 12/08/2017.
 */
//@NoArgsConstructor
//@AllArgsConstructor
@DynamoDBTable(tableName = DBConstants.BOARD_CONTAINER_TABLE)
public class BoardContainer implements BaseDomain {

  @DynamoDBAutoGeneratedKey
  @DynamoDBHashKey(attributeName = "id")
  private String id;

  @DynamoDBAttribute(attributeName = "articleId")
  private String articleId;

  @DynamoDBAttribute(attributeName = "currentBoardId")
  private String currentBoardId;

  @JsonIgnore
  @DynamoDBIgnore
  private Board currentBoard;

  public BoardContainer() {
  }

  public BoardContainer(Board board) {
    this.currentBoard = board;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getArticleId() {
    return articleId;
  }

  public void setArticleId(String articleId) {
    this.articleId = articleId;
  }

  public String getCurrentBoardId() {
    return currentBoardId;
  }

  public void setCurrentBoardId(String currentBoardId) {
    this.currentBoardId = currentBoardId;
  }

  public Board getCurrentBoard() {
    return currentBoard;
  }

  public void setCurrentBoard(Board currentBoard) {
    this.currentBoard = currentBoard;
  }

  public Map<Square, Draught> getBlackDraughts() {
    return currentBoard.getBlackDraughts();
  }

  public void setBlackDraughts(Map<Square, Draught> blackDraughts) {
    currentBoard.setBlackDraughts(blackDraughts);
  }

  public Map<Square, Draught> getWhiteDraughts() {
    return currentBoard.getWhiteDraughts();
  }

  public void setWhiteDraughts(Map<Square, Draught> whiteDraughts) {
    currentBoard.setWhiteDraughts(whiteDraughts);
  }

  public Square getSelectedSquare() {
    return currentBoard.getSelectedSquare();
  }

  public void setSelectedSquare(Square selectedSquare) {
    currentBoard.setSelectedSquare(selectedSquare);
  }

  public Square getNextSquare() {
    return currentBoard.getNextSquare();
  }

  public void setNextSquare(Square nextSquare) {
    currentBoard.setNextSquare(nextSquare);
  }

  public List<Square> getSquares() {
    return currentBoard.getSquares();
  }

  public void setSquares(List<Square> squares) {
    currentBoard.setSquares(squares);
  }

  public List<Square> getAssignedSquares() {
    return currentBoard.getAssignedSquares();
  }

  public void setAssignedSquares(List<Square> assignedSquares) {
    currentBoard.setAssignedSquares(assignedSquares);
  }

  public boolean isBlack() {
    return currentBoard.isBlack();
  }

  public void setBlack(boolean black) {
    currentBoard.setBlack(black);
  }

  public EnumRules getRules() {
    return currentBoard.getRules();
  }

  public void setRules(EnumRules rules) {
    currentBoard.setRules(rules);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BoardContainer that = (BoardContainer) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(articleId, that.articleId) &&
        Objects.equals(currentBoardId, that.currentBoardId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, articleId, currentBoardId);
  }
}
