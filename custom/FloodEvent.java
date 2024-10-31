package custom;

import core.Coord;
import core.DTNHost;

/**
 * Class defining a flood event. Flood events have parameters:
 * location (x, y), radius growth rate (m/tick), maximum area (m^2).
 * Flood events will update per clock tick, and nodes are unable to enter their area
 * Flood events are represented by a red oval on the GUI map
 */
public class FloodEvent {
    private final Coord location;
    private final double radiusGrowthRate;
    private final double maxArea;
    private double currentRadius;

    public static final double VIEWABLE_RANGE_EXTENDER = 1.2;

    /**
     * Default constructor that defines the location, growth rate and maximum area of the flood
     *
     * @param location Defines the location of the centre point of the flood
     * @param radiusGrowthRate Defines how many meters the flood radius grows by per tick
     * @param maxArea Defines the maximum area the flood will be able to cover, use -1 for unconstrained growth
     */
    public FloodEvent(Coord location, double radiusGrowthRate, double maxArea) {
        this.location = location;
        this.radiusGrowthRate = radiusGrowthRate;
        this.maxArea = maxArea;
        this.currentRadius = 0.0;
    }

    /**
     * Get the distance of a given coordinate from the centre of the flood
     * @param coord The coordinate to check the distance of
     * @return The distance of the coordinate from the flood
     */
    private double getDistanceFromCentre(Coord coord)
    {
        double x = coord.getX();
        double y = coord.getY();

       return Math.sqrt(Math.pow(x - location.getX(), 2) + Math.pow(y - location.getY(), 2));
    }

    /**
     * Checks if a given node is within the flood's active area
     *
     * @param node The node to check
     * @return True if node in area, False if not
     */
    public boolean nodeInFlood(DTNHost node) {
        if (node == null)
        {
            return false;
        }
        double nodeDistance = getDistanceFromCentre(node.getLocation());

        return nodeDistance <= this.currentRadius;
    }

    /**
     * Checks if a given node can view a flood.
     * The viewable range is 20% bigger than the radius of the flooded area
     *
     * @param node The node to check
     * @return True if node can view, False if cannot
     */
    public boolean nodeCanViewFlood(DTNHost node) {
        if (node == null)
        {
            return false;
        }
        double nodeDistance = getDistanceFromCentre(node.getLocation());

        return nodeDistance <= VIEWABLE_RANGE_EXTENDER * this.currentRadius;
    }

    public Coord getLocation() { return this.location; }

    public double getRadius() { return this.currentRadius; }

    /**
     * Update the size of the flood based on the growth rate
     */
    public void update()
    {
        if (reachedMaxArea())
        {
            return;
        }
        currentRadius += radiusGrowthRate;
    }

    /**
     * Check if the current area of the flood has reached the maximum allowed area
     *
     * @return True if flood has reached the defined max area, false if not
     */
    private boolean reachedMaxArea()
    {
        if (maxArea < 0)
        {
            return false;
        }
        double currentArea = Math.PI * Math.pow(currentRadius, 2);
        return currentArea >= maxArea;
    }

}
