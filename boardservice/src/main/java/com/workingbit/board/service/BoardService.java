package com.workingbit.board.service;

import com.workingbit.board.dao.BoardContainerDao;
import com.workingbit.board.dao.BoardDao;
import com.workingbit.board.exception.BoardServiceException;
import com.workingbit.board.model.Strings;
import com.workingbit.share.domain.impl.Board;
import com.workingbit.share.domain.impl.BoardContainer;
import com.workingbit.share.domain.impl.Draught;
import com.workingbit.share.domain.impl.Square;
import com.workingbit.share.model.CreateBoardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.workingbit.board.service.BoardUtils.*;

/**
 * Created by Aleksey Popryaduhin on 13:45 09/08/2017.
 */
@Service
public class BoardService {

  private final BoardDao boardDao;
  private final BoardContainerDao boardContainerDao;

  @Autowired
  public BoardService(BoardDao boardDao,
                      BoardContainerDao boardContainerDao) {
    this.boardDao = boardDao;
    this.boardContainerDao = boardContainerDao;
  }

  public Board createBoard(CreateBoardRequest newBoardRequest) {
    Board board = initBoard(newBoardRequest.getFillBoard(), newBoardRequest.getBlack(),
        newBoardRequest.getRules());
    saveBoard(board);
    return board;
  }

  public List<BoardContainer> findAll(Integer limit) {
    return boardContainerDao.findAll(limit);
  }

  public Optional<Board> findById(String boardId) {
    return boardDao.findById(boardId);
  }

  public void delete(String boardId) {
    boardDao.delete(boardId);
  }

  public void addDraught(BoardContainer board, Draught draught) {
//    Optional<Square> draughtOnBoard = board.getSquares()
//        .stream()
//        // find square by coords of draught
//        .filter(square -> square.getV() == draught.getV() && square.getH() == draught.getH())
//        .findFirst();
//    draughtOnBoard.ifPresent(square -> {
//      square.setDraught((Draught) draught);
//    });
  }

  /**
   * @return map of {allowed, beaten}
   * @throws BoardServiceException
   */
  public Board highlight(Board boardHighlight) throws BoardServiceException {
    String boardId = boardHighlight.getId();
    Square selectedSquare = boardHighlight.getSelectedSquare();
    if (isValidHighlight(boardHighlight, selectedSquare)) {
      return null;
    }
    return boardDao.findById(boardId)
        .map((board) -> getHighlightedBoard(board, selectedSquare))
        .orElseThrow(getBoardServiceExceptionSupplier("Unable to highlight the board"));
  }

  private boolean isValidHighlight(Board boardHighlight, Square selectedSquare) {
    return selectedSquare == null
        || !selectedSquare.isOccupied()
        || selectedSquare.getDraught().isBlack() != boardHighlight.isBlack();
  }

  /**
   * @param board map of {boardId: String, selectedSquare: Square, targetSquare: Square, allowed: List<Square>, beaten: List<Square>}
   * @return Move info:
   * {v, h, targetSquare, queen} v - distance for moving vertical (minus up),
   * h - distance for move horizontal (minus left), targetSquare is a new square with
   * moved draught, queen is a draught has become the queen
   * @throws BoardServiceException
   */
  public Optional<Board> move(Board board) throws BoardServiceException {
    Square nextSquare = board.getNextSquare();
    Square selectedSquare = board.getSelectedSquare();
    if (isValidMove(nextSquare, selectedSquare)) {
      return Optional.empty();
    }
    return boardDao.findById(board.getId()).map(boardContainer ->
        BoardUtils.moveDraught(selectedSquare, nextSquare, boardContainer));
  }

  private boolean isValidMove(Square nextSquare, Square selectedSquare) {
    return nextSquare == null || selectedSquare == null || !selectedSquare.isOccupied() || !nextSquare.isHighlighted();
  }

//  public Map<String, Object> undo(String boardId) throws BoardServiceException {
//    Map<String, Object> undoMove = boardHistoryService.undo(boardId);
//    return move(undoMove);
//  }

  public void saveBoard(Board board) {
    boardDao.save(board);
  }

  public List<Board> findByIds(Strings boardIds) {
    List<String> ids = new ArrayList<>(boardIds.size());
    ids.addAll(boardIds);
    return boardDao.findByIds(ids)
        .stream()
        .map(BoardUtils::updateBoard)
        .collect(Collectors.toList());
  }
}
