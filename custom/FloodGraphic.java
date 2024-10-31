/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package custom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import core.Coord;
import gui.playfield.PlayFieldGraphic;

/**
 * FloodGraphic for flood visualisation
 *
 * @author David Kelly
 *
 */
public class FloodGraphic extends PlayFieldGraphic {
    private final Color FLOOD_COLOUR = Color.RED;
    private final Color BG_COLOR = new Color(255, 0, 0, 100);
    private final Color RANGE_COLOR = new Color(0, 255, 0, 50);
    private final Coord location;
    private final double radius;

    public FloodGraphic(FloodEvent event) {
        this.location = event.getLocation();
        this.radius = event.getRadius();
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.setColor(RANGE_COLOR);
        Ellipse2D viewableRangeGraphic = new Ellipse2D.Double(
                scale(this.location.getX() - FloodEvent.VIEWABLE_RANGE_EXTENDER * radius),
                scale(this.location.getY() - FloodEvent.VIEWABLE_RANGE_EXTENDER * radius),
                scale(FloodEvent.VIEWABLE_RANGE_EXTENDER * radius * 2),
                scale(FloodEvent.VIEWABLE_RANGE_EXTENDER * radius * 2)
        );
        g2.fill(viewableRangeGraphic);


        // Create the flood graphic by drawing solid outline and filling with transparent colour
        g2.setColor(FLOOD_COLOUR);
        Ellipse2D floodGraphic = new Ellipse2D.Double(
                scale(this.location.getX() - radius),
                scale(this.location.getY() - radius),
                scale(radius * 2),
                scale(radius * 2)
        );
        // Draw the outline of the flood
        g2.draw(floodGraphic);
        // Fill the background of the flood with transparency
        g2.setColor(BG_COLOR);
        g2.fill(floodGraphic);

    }
}
