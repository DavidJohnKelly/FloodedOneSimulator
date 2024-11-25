/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package movement;

import java.util.List;

import core.Coord;
import movement.map.MapNode;
import core.Settings;

/**
 * Map based movement model that uses Dijkstra's algorithm to find the shortest
 * path between the node's current position, and a target position
 *
 * @author David Kelly
 */
public class TargetedShortestPathMapBasedMovement extends
        ShortestPathMapBasedMovement implements SwitchableMovement {

    /** Target Coord handler */
    private Coord targetCoord;

    /**
     * Creates a new movement model based on a Settings object's settings.
     * @param settings The Settings object where the settings are read from
     */
    public TargetedShortestPathMapBasedMovement(Settings settings) {
        super(settings);
        this.targetCoord = null;
    }

    /**
     * Copy constructor.
     * @param mbm The ShortestPathMapBasedMovement prototype to base
     * the new object to
     */
    protected TargetedShortestPathMapBasedMovement(TargetedShortestPathMapBasedMovement mbm) {
        super(mbm);
        this.targetCoord = mbm.targetCoord;
    }

    @Override
    public Path getPath() {
        if(targetCoord == null) {
            return super.getPath();
        }
        else {
            MapNode to = getMap().getNearestNodeByCoord(targetCoord);

            Path p = super.getPathTo(to);

            lastMapNode = to;

            return p;
        }
    }

    @Override
    public TargetedShortestPathMapBasedMovement replicate() {
        return new TargetedShortestPathMapBasedMovement(this);
    }


    public void setTargetCoord(Coord targetCoord) {
        this.targetCoord = targetCoord;
    }

}
