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
public class TargetedShortestPathMapBasedMovement extends ShortestPathMapBasedMovement implements
        SwitchableMovement {

    /** Points Of Interest handler */
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
            System.out.println("Routing to: " + targetCoord);
            Path p = new Path(generateSpeed());
            MapNode to = getMap().getNearestNodeByCoord(targetCoord);
            System.out.println("Routing to: " + to);

            assert to != null: "Error target node is null";

            List<MapNode> nodePath = this.pathFinder.getShortestPath(lastMapNode, to);

            // this assertion should never fire if the map is checked in read phase
            assert !nodePath.isEmpty() : "No path from " + lastMapNode + " to " +
                    to + ". The simulation map isn't fully connected";

            for (MapNode node : nodePath) { // create a Path from the shortest path
                p.addWaypoint(node.getLocation());
            }

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
