package report;

import core.DTNHost;
import core.UpdateListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A report class to give data on how many nodes survived the flood
 * Report follows the following structure -
 * flooded nodes:
 * saved nodes:
 * safe nodes:
 * percent saved:
 * percent safe:
 *
 * @author David Kelly
 */
public class FloodedNodesReport extends Report implements UpdateListener {
    private final ArrayList<DTNHost> floodedNodes;
    private final ArrayList<DTNHost> savedNodes;
    private final ArrayList<DTNHost> safeNodes;

    public FloodedNodesReport() {
        init();
        floodedNodes = new ArrayList<>();
        savedNodes = new ArrayList<>();
        safeNodes = new ArrayList<>();
    }

    @Override
    public void done() {
        // Write the data to file
        write("Flooding stats for scenario " + getScenarioName());
        // Get percentage directly saved in scenario
        float percentSaved = ((float) savedNodes.size() /
                (floodedNodes.size() + savedNodes.size() + safeNodes.size())) * 100f;
        float percentSafe = ((float) safeNodes.size() /
                (floodedNodes.size() + savedNodes.size() + safeNodes.size())) * 100f;

        String statsText = "flooded nodes: " + this.floodedNodes.size() +
                "\nsaved nodes: " + this.savedNodes.size() +
                "\nsafe nodes: " + this.safeNodes.size() +
                "\npercent saved: " + percentSaved +
                "\npercent safe: " + percentSafe;

        write(statsText);
        super.done();
    }

    @Override
    public void updated(List<DTNHost> hosts) {
        for (DTNHost host : hosts) {
            if(host.isStuckInFlood())
            {
                // Remove it from the safe list
                // And add to the flooded list
                safeNodes.remove(host);
                if(!floodedNodes.contains(host))
                {
                    floodedNodes.add(host);
                }
            // If not in flood and at safe zone then notify
            } else if (host.hasSeenFlood() && host.getSpeed() == 0)
            {
                safeNodes.remove(host);
                if(!savedNodes.contains(host))
                {
                    savedNodes.add(host);
                }
            }
            else {
                // Otherwise add to flooded list
                // Will only happen once
                if (!safeNodes.contains(host))
                {
                    safeNodes.add(host);
                }
            }
        }
    }
}
