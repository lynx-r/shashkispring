/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.workingbit.board.api;

import com.workingbit.board.model.Boards;
import com.workingbit.board.model.ResponseError;
import com.workingbit.board.model.Strings;

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

@Api(value = "boards", description = "the boards API")
public interface BoardsApi {

    @ApiOperation(value = "Get boards by ids", notes = "", response = Boards.class, tags={ "boards", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Boards loaded", response = Boards.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/boards",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.POST)
    default ResponseEntity<Boards> listBoardsByIds(@ApiParam(value = ""  )  @Valid @RequestBody Strings boardIds) {
        // do some magic!
        return new ResponseEntity<Boards>(HttpStatus.OK);
    }

}