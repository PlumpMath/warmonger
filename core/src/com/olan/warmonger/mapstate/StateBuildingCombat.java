package com.olan.warmonger;

public class StateBuildingCombat implements MapState.State {
  private Map map;
  private Unit actor;
  private Building target;
  private Tile targetTile;

  public StateBuildingCombat (Map map, Unit actor, Building target) {
    this.map = map;
    this.actor = actor;
    this.target = target;
  }

  public void enter () {
    map.selectUnit(actor);

    if (actor.getTeam() == Team.BLUE) {
      targetTile = map.getTile(target.getRow() - 1, target.getColumn());
    } else {
      targetTile = map.getTile(target.getRow() + 1, target.getColumn());
    }
  }

  public void exit () {
    int remainHealth = target.getHealthPoint() - actor.getAttackPoint();
    if (remainHealth <= 0) {
      map.getBuildings().remove(target);
      target.remove();
    } else {
      target.setHealthPoint(remainHealth);
    }
    map.selectUnit(null);
  }

  public void run () {
    if (!actor.isMovingTo(targetTile)) {
      map.setState(new StateIdle(map));
    }
  }
}