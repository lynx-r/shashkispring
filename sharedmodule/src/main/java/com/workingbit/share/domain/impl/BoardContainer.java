package com.workingbit.share.domain.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workingbit.board.common.DBConstants;
import com.workingbit.share.model.EnumRules;
import com.workingbit.share.common.Log;
import com.workingbit.share.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Aleksey Popryaduhin on 19:54 12/08/2017.
 */
@Data
@AllArgsConstructor
@DynamoDBTable(tableName = DBConstants.BOARD_CONTAINER_TABLE)
public class BoardContainer implements BaseDomain {

  @DynamoDBAutoGeneratedKey
  @DynamoDBHashKey(attributeName = "id")
  private String id;

//  @DynamoDBTypeConvertedJson(targetType = BoardHistory.class)
  @DynamoDBAttribute(attributeName = "boardHistoryId")
  private String boardHistoryId;

  /**
   * Squares for API
   */
  @DynamoDBIgnore
  private List<Square> squares = new ArrayList<>();

  /**
   * Squares without nulls
   */
  @DynamoDBIgnore
  @JsonIgnore
  private List<Square> assignedSquares = new ArrayList<>();

  /**
   * Is player on the black side?
   */
  @DynamoDBAttribute(attributeName = "black")
  private boolean black;

  @DynamoDBTypeConvertedEnum
  @DynamoDBAttribute(attributeName = "rules")
  private EnumRules rules;

  /**
   * Size of one square
   */
  @DynamoDBAttribute(attributeName = "squareSize")
  private int squareSize;

  public BoardContainer() {
  }

  public BoardContainer(boolean black, EnumRules rules, int squareSize) {
    this.black = black;
    this.rules = rules;
    this.squareSize = squareSize;
  }

  public BoardContainer undo() {
    Log.debug(id);
    return this;
  }

  public BoardContainer redo() {
    Log.debug(id);
    return this;
  }

//   public void mapBoard(ObjectMapper objectMapper) {
//    Square[] mappedSquares = new Square[squares.length];
//    for (int i = 0; i < squares.size(); i++) {
//      Square square = objectMapper.convertValue(squares.get(i), Square.class);
//      mappedSquares.add(square);
//    }
//    squares = mappedSquares;
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
//    if (!super.equals(o)) return false;
    BoardContainer that = (BoardContainer) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), id);
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return new BoardContainer(id,
        boardHistoryId,
        squares,
        assignedSquares,
        black,
        rules,
        squareSize);
  }

  public BoardContainer init(BoardContainer initBoard) {
    setSquares(initBoard.getSquares());
    setAssignedSquares(initBoard.getAssignedSquares());
    return this;
  }
}
