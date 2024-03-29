package com.workingbit.share.common;

/**
 * Created by Aleksey Popryaduhin on 09:32 10/08/2017.
 */
public enum EnumRules {
  RUSSIAN(8, 3),
  RUSSIAN_GIVEAWAY(-8, 3),
  INTERNATIONAL(10, 4),
  INTERNATIONAL_GIVEAWAY(-10, 4);

  private final int dimension;
  private final int rowsForDraughts;

  EnumRules(int dimension, int rowsForDraughts) {
    this.dimension = dimension;
    this.rowsForDraughts = rowsForDraughts;
  }

  public int getDimension() {
    return dimension;
  }

  public int getRowsForDraughts() {
    return rowsForDraughts;
  }
}
