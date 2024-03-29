package com.workingbit.wiki.service;

import com.workingbit.wiki.config.RestProperties; /**
 * Created by Aleksey Popryaduhin on 17:59 12/08/2017.
 */
public class BaseRestService {

  private final RestProperties restProperties;

  public BaseRestService(RestProperties restProperties) {
    this.restProperties = restProperties;
  }

  protected String boardResource() {
    return restProperties.getBoardApiUrl() + restProperties.getBoardResource();
  }
}
