/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.workingbit.article.api;

import com.workingbit.article.model.ResponseError;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-25T23:04:03.743+03:00")

@Api(value = "article", description = "the article API")
public interface ArticleApi {

    @ApiOperation(value = "Create a article", notes = "", response = com.workingbit.share.model.CreateArticleResponse.class, tags={ "article", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Article response", response = com.workingbit.share.model.CreateArticleResponse.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/article",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.POST)
    default ResponseEntity<com.workingbit.share.model.CreateArticleResponse> createArticle(@ApiParam(value = ""  )  @Valid @RequestBody com.workingbit.share.model.CreateArticleRequest createArticleRequest) {
        // do some magic!
        return new ResponseEntity<com.workingbit.share.model.CreateArticleResponse>(HttpStatus.OK);
    }


    @ApiOperation(value = "Delete a specific article", notes = "", response = Void.class, tags={ "article", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Void.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/article/{articleId}",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> deleteArticleById(@ApiParam(value = "The id of the article to delete",required=true ) @PathVariable("articleId") String articleId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @ApiOperation(value = "Info for a specific article", notes = "", response = com.workingbit.share.domain.impl.Article.class, tags={ "article", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = com.workingbit.share.domain.impl.Article.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/article/{articleId}",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.GET)
    default ResponseEntity<com.workingbit.share.domain.impl.Article> findArticleById(@ApiParam(value = "The id of the article to retrieve",required=true ) @PathVariable("articleId") String articleId) {
        // do some magic!
        return new ResponseEntity<com.workingbit.share.domain.impl.Article>(HttpStatus.OK);
    }


    @ApiOperation(value = "Info for a specific board", notes = "", response = com.workingbit.share.domain.impl.BoardBox.class, tags={ "board", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Expected response to a valid request", response = com.workingbit.share.domain.impl.BoardBox.class),
        @ApiResponse(code = 200, message = "unexpected ResponseError", response = ResponseError.class) })
    
    @RequestMapping(value = "/article/{articleId}/board",
        produces = { "application/json;charset=UTF-8" }, 
        method = RequestMethod.GET)
    default ResponseEntity<com.workingbit.share.domain.impl.BoardBox> findBoardByArticleId(@ApiParam(value = "The id of the board to retrieve",required=true ) @PathVariable("articleId") String articleId) {
        // do some magic!
        return new ResponseEntity<com.workingbit.share.domain.impl.BoardBox>(HttpStatus.OK);
    }

}
