package com.workingbit.board.service;

import com.workingbit.board.common.EnumSearch;
import com.workingbit.board.exception.BoardServiceException;
import com.workingbit.share.common.EnumRules;
import com.workingbit.share.domain.ICoordinates;
import com.workingbit.share.domain.impl.Board;
import com.workingbit.share.domain.impl.BoardContainer;
import com.workingbit.share.domain.impl.Draught;
import com.workingbit.share.domain.impl.Square;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.workingbit.board.common.EnumSearch.allowed;
import static com.workingbit.board.service.BoardUtils.findSquareByVH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Aleksey Popryaduhin on 20:01 10/08/2017.
 */
public class HighlightMoveUtilTest {
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void filterNotOnMainAndSelected() throws Exception, BoardServiceException {
    Board board = getBoard();
    Draught draught = getDraught(5, 2);
    Square square = getSquareByVH(board.getCurrentBoard(), 5, 2);
    square.setDraught(draught);
    HighlightMoveUtil highlight = new HighlightMoveUtil(board.getCurrentBoard(), square, board.getRules());
    CompletableFuture<Stream<Square>> x = highlight.filterNotOnMainAndSelected();
    Stream<Square> squareStream = x.get();
    String squares = squareStream.map(ICoordinates::toNotation).collect(Collectors.joining(","));
    assertEquals("h8,g7,f6,a5,e5,b4,d4,b2,d2,a1,e1", squares);
  }

  @Test
  public void shouldWhiteDraughtMoveForwardOnOnePosition() throws Exception, BoardServiceException {
    Board board = getBoard();
    Draught draught = getDraught(5, 2);
    Square square = getSquareByVH(board.getCurrentBoard(), 5, 2);
    square.setDraught(draught);
    Map<String, Object> highlight = HighlightMoveUtil.highlight(board, square);
    assertEquals("(4,1)(4,3)", resultToString(highlight, allowed));
  }

  @Test
  public void shouldBlackDraughtMoveBackwardOnOnePosition() throws Exception, BoardServiceException {
    Board board = getBoard();
    Draught draught = getDraughtBlack(5, 2);
    Square square = getSquare(draught, 5, 2);
    HighlightMoveUtil highlightMoveUtil = new HighlightMoveUtil(board.getCurrentBoard(), (Square) square, getRules());
    Map<String, Object> allowedMoves = highlightMoveUtil.findAllowedMoves();
    assertTrue(allowedMoves.size() > 0);
    assertEquals("(6,1)(6,3)", resultToString(allowedMoves, allowed));
  }

//  @Test
//  public void shouldWhiteDraughtBeatForward() throws Exception, BoardServiceException {
//    Board board = getBoard();
//    BoardService boardService = getBoardService();
//    // add black draught
//    boardService.addDraught(board.getCurrentBoard(), getDraughtBlack(4, 3));
//    Draught draught = getDraught(5, 2);
//    // find square on board
//    Square square = getSquareByVH(board.getCurrentBoard(), 5, 2);
//    // set draught for square
//    square.setDraught((Draught) draught);
//    HighlightMoveUtil highlightMoveUtil = new HighlightMoveUtil(board.getCurrentBoard(), (Square) square, getRules());
//    Map<String, Object> allowedMoves = highlightMoveUtil.findAllowedMoves();
//    assertTrue(allowedMoves.size() > 0);
//    assertEquals("(3,4)", resultToString(allowedMoves, allowed));
//    assertEquals("(4,3)", resultToString(allowedMoves, beaten));
//  }

//  @Test
//  public void shouldWhiteDraughtBeatForwardTwice() throws Exception, BoardServiceException {
//    Board board = getBoard();
//    BoardService boardService = getBoardService();
//    boardService.addDraught(board.getCurrentBoard(), getDraughtBlack(4, 3));
//    boardService.addDraught(board.getCurrentBoard(), getDraughtBlack(4, 1));
//    Draught draught = getDraught(5, 2);
//    Square square = getSquareByVH(board.getCurrentBoard(), 5, 2);
//    square.setDraught((Draught) draught);
//    HighlightMoveUtil highlightMoveUtil = new HighlightMoveUtil(board.getCurrentBoard(), (Square) square, getRules());
//    Map<String, Object> allowedMoves = highlightMoveUtil.findAllowedMoves();
//    assertTrue(allowedMoves.size() > 0);
//    assertEquals("(3,0)(3,4)", resultToString(allowedMoves, allowed));
//    assertEquals("(4,1)(4,3)", resultToString(allowedMoves, beaten));
//  }

//  @Test
//  public void shouldWhiteDraughtBeatBackward() throws Exception, BoardServiceException {
//    Board board = getBoard();
//    BoardService boardService = getBoardService();
//    boardService.addDraught(board.getCurrentBoard(), getDraughtBlack(6, 1));
//    Draught draught = getDraught(5, 2);
//    Square square = getSquareByVH(board.getCurrentBoard(), 5, 2);
//    square.setDraught((Draught) draught);
//    HighlightMoveUtil highlightMoveUtil = new HighlightMoveUtil(board.getCurrentBoard(), (Square) square, getRules());
//    Map<String, Object> allowedMoves = highlightMoveUtil.findAllowedMoves();
//    assertTrue(allowedMoves.size() > 0);
//    assertEquals("(7,0)", resultToString(allowedMoves, allowed));
//    assertEquals("(6,1)", resultToString(allowedMoves, beaten));
//  }

//  @Test
//  public void shouldWhiteDraughtBeatBackwardTwice() throws Exception, BoardServiceException {
//    Board board = getBoard();
//    BoardService boardService = getBoardService();
//    boardService.addDraught(board.getCurrentBoard(), getDraughtBlack(6, 1));
//    boardService.addDraught(board.getCurrentBoard(), getDraughtBlack(6, 3));
//    Draught draught = getDraught(5, 2);
//    Square square = getSquareByVH(board.getCurrentBoard(), 5, 2);
//    square.setDraught((Draught) draught);
//    HighlightMoveUtil highlightMoveUtil = new HighlightMoveUtil(board.getCurrentBoard(), (Square) square, getRules());
//    Map<String, Object> allowedMoves = highlightMoveUtil.findAllowedMoves();
//    assertTrue(allowedMoves.size() > 0);
//    assertEquals("(7,0)(7,4)", resultToString(allowedMoves, allowed));
//    assertEquals("(6,1)(6,3)", resultToString(allowedMoves, beaten));
//  }
//
  private String resultToString(Map<String, Object> allowedMoves, EnumSearch enumSearch) {
    return ((List<ICoordinates>) allowedMoves.get(enumSearch.name()))
        .stream()
        .map(s -> String.format("(%s,%s)", s.getV(), s.getH()))
        .collect(Collectors.joining());
  }

  Board getBoard() {
    BoardContainer boardContainer = BoardUtils.initBoard(false, false, EnumRules.RUSSIAN, 60);
    Board board = new Board(boardContainer, false, EnumRules.RUSSIAN, 60);
    BoardContainer currentBoard = board.getCurrentBoard();
    Optional<Square> squareByVH = BoardUtils.findSquareByVH(currentBoard, 5, 2);
    Square selectedSquare = squareByVH.get();
    Draught draught = new Draught(5, 2, getRules().getDimension());
    selectedSquare.setDraught(draught);
    currentBoard.setSelectedSquare(selectedSquare);
    return board;
  }

  Draught getDraught(int v, int h) {
    return new Draught(v, h, getRules().getDimension());
  }

  Square getSquare(Draught draught, int v, int h) {
    return new Square(v, h, getRules().getDimension(), true, 60, draught);
  }

  Draught getDraughtBlack(int v, int h) {
    return new Draught(v, h, getRules().getDimension(), true);
  }

  Square getSquareByVH(BoardContainer board, int v, int h) {
    return findSquareByVH(board, v, h).get();
  }

  protected EnumRules getRules() {
    return EnumRules.RUSSIAN;
  }

}