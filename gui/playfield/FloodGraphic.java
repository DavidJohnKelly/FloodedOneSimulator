/*
 * Copyright 2010 Aalto University, ComNet
 * Released under GPLv3. See LICENSE.txt for details.
 */
package gui.playfield;

import java.awt.Color;
import java.awt.Graphics2D;

import movement.map.SimMap;
import core.Coord;

/**
 * PlayfieldGraphic for SimMap visualization
 *
 */
public class FloodGraphic extends PlayFieldGraphic {
    private final Color FLOOD_COLOUR = Color.BLUE;
    private final Color BG_COLOR = Color.BLUE;

    public FloodGraphic() {

    }

    // TODO: draw only once and store to buffer
    @Override
    public void draw(Graphics2D g2) {

        g2.setColor(Color.RED);
        g2.setBackground(BG_COLOR);

        // TODO: Implement Flood Node Type
        Coord c = new Coord(1550, 10);
        double radius = 50;
        g2.fillOval(scale(c.getX()), scale(c.getY()),
              scale(radius), scale(radius));

    }
}
