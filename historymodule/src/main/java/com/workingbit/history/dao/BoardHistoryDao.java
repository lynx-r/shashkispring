package com.workingbit.history.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workingbit.history.config.AWSProperties;
import com.workingbit.history.domain.impl.BoardHistory;
import com.workingbit.history.domain.IBoardHistory;
import com.workingbit.share.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Aleksey Popryaduhin on 18:16 09/08/2017.
 */
@Component
public class BoardHistoryDao extends BaseDao<BoardHistory, IBoardHistory> {

  private final ObjectMapper objectMapper;

  @Autowired
  public BoardHistoryDao(AWSProperties awsProperties,
                  ObjectMapper objectMapper) {
    super(BoardHistory.class, IBoardHistory.class, awsProperties.getRegion());
    this.objectMapper = objectMapper;
  }

//  @Override
//  public Optional<IBoard> findById(String entityId) {
//    Optional<IBoard> boardOptional = super.findById(entityId);
//    return boardOptional.map(iBoard -> {
//      BoardChanger last = iBoard.getLast();
//      if (last != null) {
//        last.mapBoard(objectMapper);
//      }
//      return iBoard;
//    });
//  }
//
//  @Override
//  public List<IBoard> findAll() {
//    return super.findAll()
//        .stream()
//        .peek(iBoard -> {
//          BoardChanger last = iBoard.getLast();
//          if (last != null) {
//            last.mapBoard(objectMapper);
//          }
//        })
//        .collect(Collectors.toList());
//  }
}