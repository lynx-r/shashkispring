package com.workingbit.share.domain.impl;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.workingbit.share.domain.IArticle;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aleksey Popryaduhin on 18:31 09/08/2017.
 */
@DynamoDBTable(tableName = "Article")
@Data
@NoArgsConstructor
public class Article implements IArticle {

  @DynamoDBAutoGeneratedKey
  @DynamoDBHashKey(attributeName = "Id")
  private String id;

  @DynamoDBAttribute(attributeName = "Username")
  private String author;

  @DynamoDBAttribute(attributeName = "Title")
  private String title;

  @DynamoDBAttribute(attributeName = "Content")
  private String content;

  @DynamoDBTypeConvertedJson(targetType = Set.class)
  @DynamoDBAttribute(attributeName = "BoardIds")
  private Set<String> boardIds = new HashSet<>();

  /**
   * New article not viewed by moderator
   */
  @DynamoDBAttribute(attributeName = "NewAdded")
  private boolean newAdded;

  /**
   * Article viewed by moderator and didn't pass it
   */
  @DynamoDBAttribute(attributeName = "Banned")
  private boolean banned;

  /**
   * Article published
   */
  @DynamoDBAttribute(attributeName = "Published")
  private boolean published;

  public Article(String author, String title, String content) {
    this.author = author;
    this.title = title;
    this.content = content;
  }
}