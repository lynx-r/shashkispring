package com.workingbit.board.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workingbit.board.exception.BoardServiceException;
import com.workingbit.share.model.EnumRules;
import com.workingbit.share.domain.impl.BoardContainer;
import com.workingbit.share.domain.impl.Draught;
import com.workingbit.share.domain.impl.Square;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by Aleksey Popryaduhin on 20:56 11/08/2017.
 */
public class BoardUtils {

  /**
   * Fill board with draughts
   *
   * @param fillBoard
   * @param black      is player plays black?
   * @param rules
   * @param squareSize size of one square
   * @return
   */
  static BoardContainer initBoard(boolean fillBoard, boolean black, EnumRules rules, Integer squareSize) {
    BoardContainer boardContainer = new BoardContainer();
    boardContainer.setRules(rules);

    List<Draught> whiteDraughts = new ArrayList<>();
    List<Draught> blackDraughts = new ArrayList<>();
    List<Square> boardSquares = getAssignSquares(rules.getDimension(), squareSize);
    for (Square square : boardSquares) {
      int v = square.getV(), h = square.getH();
      if (fillBoard) {
        if (v < rules.getRowsForDraughts()) {
          placeDraught(!black, rules, blackDraughts, square, v, h);
        } else if (v >= rules.getDimension() - rules.getRowsForDraughts() && v < rules.getDimension()) {
          placeDraught(black, rules, whiteDraughts, square, v, h);
        }
      }
    }
//    boardContainer.setBlackDraughts(blackDraughts);
//    boardContainer.setWhiteDraughts(whiteDraughts);
    boardContainer.setAssignedSquares(boardSquares);
    List<Square> board = getSquares(boardSquares, rules.getDimension());
    boardContainer.setSquares(board);
    return boardContainer;
  }

  private static void placeDraught(boolean black, EnumRules rules, List<Draught> draughts, Square square, int v, int h) {
    Draught draught = new Draught(v, h, rules.getDimension());
    draught.setBlack(black);
    draughts.add(draught);
    square.setDraught(draught);
  }

  static List<Square> getSquareArray(int offset, int dim, int squareSize, boolean prime) {
    List<Square> squares = new ArrayList<>();
    for (int v = 0; v < dim; v++) {
      for (int h = 0; h < dim; h++) {
        if (((v + h + 1) % 2 == 0)
            && (prime && (v - h + offset) == 0
            || !prime && (v + h - offset) == dim - 1)) {
          Square square = new Square(v, h, dim, prime, squareSize);
          squares.add(square);
        }
      }
    }
    return squares;
  }

  static List<List<Square>> getDiagonals(int dim, int squareSize, boolean main) {
    List<List<Square>> diagonals = new ArrayList<>(dim - 2);
    for (int i = -dim; i < dim - 1; i++) {
      if ((i == 1 - dim) && main) {
        continue;
      }
      List<Square> diagonal = BoardUtils.getSquareArray(i, dim, squareSize, main);
      if (!diagonal.isEmpty()) {
        diagonals.add(diagonal);
      }
    }
    return diagonals;
  }

  public static boolean isSubDiagonal(List<Square> diagonal, List<Square> subDiagonal) {
    return subDiagonal.stream().allMatch(diagonal::contains);
  }

  /**
   * Assign square subdiagonal and main diagonal. Assign diagonal's squares link to squares
   * @param dim
   * @param squareSize
   * @return
   */
  private static List<Square> getAssignSquares(int dim, int squareSize) {
    List<List<Square>> mainDiagonals = getDiagonals(dim, squareSize, true);
    List<List<Square>> subDiagonals = getDiagonals(dim, squareSize, false);

    List<Square> squares = new ArrayList<>();
    for (List<Square> subDiagonal : subDiagonals) {
      for (Square subSquare : subDiagonal) {
        subSquare.addDiagonal(subDiagonal);
        squares.add(subSquare);
        subDiagonal.set(subDiagonal.indexOf(subSquare), subSquare);
        for (List<Square> mainDiagonal : mainDiagonals) {
          for (Square mainSquare : mainDiagonal) {
            if (subSquare.equals(mainSquare)) {
              subSquare.addDiagonal(mainDiagonal);
              mainDiagonal.set(mainDiagonal.indexOf(subSquare), subSquare);
            }
          }
        }
      }
    }

    return squares;
  }

  private static List<Square> getSquares(List<Square> diagonals, int dim) {
    List<Square> squares = new ArrayList<>();
    List<Square> collect = diagonals
        .stream()
        .sorted(Comparator.comparingInt(Square::getV))
        .collect(Collectors.toList());
    Iterator<Square> iterator = collect.iterator();
    for (int i = 0; i < dim; i++) {
      for (int j = 0; j < dim; j++) {
        if ((i + j) % 2 == 0) {
          squares.add(null);
        } else if (iterator.hasNext()) {
          squares.add(iterator.next());
        }
      }
    }
    return squares;
  }

//  private static boolean mainDiagonal(int v, int h, int dim) {
//    return h - v;
//  }
//
//  private static boolean isSubDiagonal(int v, int h, int dim) {
//    return dim - h - v;
//  }

  /**
   * Find variable link to square from board
   *
   * @param board
   * @param square
   * @return
   */
  static Optional<Square> findSquareLink(BoardContainer board, Square square) {
    return findSquareByVH(board, square.getV(), square.getH());
  }

  static Optional<Square> findSquareByVH(BoardContainer board, int v, int h) {
    for (Square square : board.getAssignedSquares()) {
      if (square.getH() == h && square.getV() == v) {
        return Optional.of(square);
      }
    }
    return Optional.empty();
  }

  static Optional<Square> findSquareByNotation(BoardContainer board, String notation) {
    if (StringUtils.isBlank(notation)) {
      return Optional.empty();
    }
    for (Square square : board.getAssignedSquares()) {
      if (square.toNotation().equals(notation)) {
        return Optional.of(square);
      }
    }
    return Optional.empty();
  }

  /**
   * Get diff between source and target if h == -1 then we go left if h == 1 go right if v == -1 go up if v == 1 go up
   *
   * @param source
   * @param target
   * @return
   */
  static Pair<Integer, Integer> getDistanceVH(Square source, Square target) {
    int vDist = target.getV() - source.getV();
    int hDist = target.getH() - source.getH();
    return Pair.of(vDist, hDist);
  }

  static Supplier<BoardServiceException> getBoardServiceExceptionSupplier(String message) {
    return () -> new BoardServiceException(message);
  }

  static <T, I> List<I> mapList(List<I> squares, ObjectMapper objectMapper, Class<T> clazz, Class<I> iclazz) {
    if (squares == null || squares.isEmpty()) {
      return Collections.emptyList();
    }
    List<I> newSquares = new ArrayList<>(squares.size());
    // leave as is. Find by Id returns HashMap of getSquares() convert so we need to convert it to Square
    for (int i = 0; i < squares.size(); i++) {
      I square = iclazz.cast(objectMapper.convertValue(squares.get(i), clazz));
      newSquares.add(square);
    }
    return newSquares;
  }

  public static Square addDraught(BoardContainer boardContainer, String newSquare, boolean black) throws BoardServiceException {
    return addDraught(boardContainer, newSquare, black, false);
  }

  public static Square addDraught(BoardContainer currentBoard, String notation, boolean black, boolean queen) throws BoardServiceException {
    Optional<Square> squareOptional = findSquareByNotation(currentBoard, notation);
    return squareOptional.map(square -> {
      Draught draught = new Draught(square.getV(), square.getH(), square.getDim(), black, queen);
      square.setDraught(draught);
      return square;
    }).orElseThrow(getBoardServiceExceptionSupplier("Unable to add draught"));
  }
}
