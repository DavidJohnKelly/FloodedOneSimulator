/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package custom;

import java.awt.Color;
import java.awt.Graphics2D;

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
    private final Color BG_COLOR = Color.BLUE;
    private final Coord location;
    private final double radius;

    public FloodGraphic(FloodEvent event) {
        this.location = event.getLocation();
        this.radius = event.getRadius();
    }

    @Override
    public void draw(Graphics2D g2) {

        g2.setColor(FLOOD_COLOUR);
        g2.setBackground(BG_COLOR);

        // Calculate centre coordinates as otherwise will be drawn as if location is top left not center
        int centre_x = scale(this.location.getX()) - scale(this.radius) / 2;
        int centre_y = scale(this.location.getY()) - scale(this.radius) / 2;

        g2.fillOval(centre_x, centre_y, scale(this.radius), scale(this.radius));
    }
}
