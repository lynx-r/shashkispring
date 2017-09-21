/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.workingbit.board.api;

import com.workingbit.board.model.ResponseError;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-21T19:52:54.325+03:00")

@Api(value = "board", description = "the board API")
public interface BoardApi {

    @ApiOperation(value = "Create a board", notes = "", response = com.workingbit.share.domain.impl.BoardContainer.class, tags={ "board", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Board response", response = com.workingbit.share.domain.impl.BoardContainer.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/board",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.POST)
    default ResponseEntity<com.workingbit.share.domain.impl.BoardContainer> createBoard(@ApiParam(value = ""  )  @Valid @RequestBody com.workingbit.share.model.CreateBoardRequest createBoardRequest) {
        // do some magic!
        return new ResponseEntity<com.workingbit.share.domain.impl.BoardContainer>(HttpStatus.OK);
    }


    @ApiOperation(value = "Delete a specific board", notes = "", response = Void.class, tags={ "board", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Void.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/board/{boardId}",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteBoardById(@ApiParam(value = "The id of the board to delete",required=true ) @PathVariable("boardId") String boardId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @ApiOperation(value = "Info for a specific board", notes = "", response = com.workingbit.share.domain.impl.BoardContainer.class, tags={ "board", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = com.workingbit.share.domain.impl.BoardContainer.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/board/{boardId}",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.GET)
    default ResponseEntity<com.workingbit.share.domain.impl.BoardContainer> findBoardById(@ApiParam(value = "The id of the board to retrieve",required=true ) @PathVariable("boardId") String boardId) {
        // do some magic!
        return new ResponseEntity<com.workingbit.share.domain.impl.BoardContainer>(HttpStatus.OK);
    }


    @ApiOperation(value = "Info for a specific board", notes = "", response = com.workingbit.share.domain.impl.BoardContainer.class, tags={ "board", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = com.workingbit.share.domain.impl.BoardContainer.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/board/highlight",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.POST)
    default ResponseEntity<com.workingbit.share.domain.impl.BoardContainer> highlightBoard(@ApiParam(value = "A board to be highlighted" ,required=true )  @Valid @RequestBody com.workingbit.share.domain.impl.BoardContainer board) {
        // do some magic!
        return new ResponseEntity<com.workingbit.share.domain.impl.BoardContainer>(HttpStatus.OK);
    }


    @ApiOperation(value = "Info for a specific board", notes = "", response = com.workingbit.share.domain.impl.BoardContainer.class, tags={ "board", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = com.workingbit.share.domain.impl.BoardContainer.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/board/move",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.POST)
    default ResponseEntity<com.workingbit.share.domain.impl.BoardContainer> move(@ApiParam(value = "A board to be highlighted" ,required=true )  @Valid @RequestBody com.workingbit.share.domain.impl.BoardContainer board) {
        // do some magic!
        return new ResponseEntity<com.workingbit.share.domain.impl.BoardContainer>(HttpStatus.OK);
    }

}
