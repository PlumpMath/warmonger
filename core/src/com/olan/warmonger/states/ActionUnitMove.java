package com.olan.warmonger;

public class ActionUnitMove implements GameDriven.Action {
  private Map map;
  private Unit unit;
  private Tile tile;

  public ActionUnitMove (Map map, Unit unit, Tile tile) {
    this.map = map;
    this.unit = unit;
    this.tile = tile;
  }

  public void enter () {

  }

  public void exit () {

  }

  public void run () {
    if (!unit.isMovingTo(tile)) {
      map.setState(new ActionEndTurn(map, map.getCurrentTeam()));
    }
  }
}
