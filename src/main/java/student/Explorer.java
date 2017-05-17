package student;

import game.EscapeState;
import game.ExplorationState;
import game.NodeStatus;

import java.util.*;

public class Explorer {

  /**
   * Explore the cavern, trying to find the orb in as few steps as possible.
   * Once you find the orb, you must return from the function in order to pick
   * it up. If you continue to move after finding the orb rather
   * than returning, it will not count.
   * If you return from this function while not standing on top of the orb,
   * it will count as a failure.
   *
   * <p>There is no limit to how many steps you can take, but you will receive
   * a score bonus multiplier for finding the orb in fewer steps.</p>
   *
   * <p>At every step, you only know your current tile's ID and the ID of all
   * open neighbor tiles, as well as the distance to the orb at each of these tiles
   * (ignoring walls and obstacles).</p>
   *
   * <p>To get information about the current state, use functions
   * getCurrentLocation(),
   * getNeighbours(), and
   * getDistanceToTarget()
   * in ExplorationState.
   * You know you are standing on the orb when getDistanceToTarget() is 0.</p>
   *
   * <p>Use function moveTo(long id) in ExplorationState to move to a neighboring
   * tile by its ID. Doing this will change state to reflect your new position.</p>
   *
   * <p>A suggested first implementation that will always find the orb, but likely won't
   * receive a large bonus multiplier, is a depth-first search.</p>
   *
   * @param state the information available at the current state
   */
  public void explore(final ExplorationState state) {
    Stack<Long> exploredPath = new Stack<>();
    PriorityQueue<NodeStatus> checkedNodes = new PriorityQueue<>();
    do {
      if (state.getDistanceToTarget() == 0) {
        break;
      }
      Collection<NodeStatus> currentNeighbours = state.getNeighbours();
      for (NodeStatus node: currentNeighbours) {
        if (!checkedNodes.contains(node)) {
          checkedNodes.add(node);
        }
      }
      NodeStatus nextLocation = checkedNodes.remove();
      while (exploredPath.contains(nextLocation.getId())) {
        nextLocation = checkedNodes.remove();
      }
      LinkedList<Long> backTrack = new LinkedList<>();
      if (!currentNeighbours.contains(nextLocation)) {
        long branch = state.getCurrentLocation();
        backTrack.add(branch);
        long backStep;
        while (!currentNeighbours.contains(nextLocation)) {
          backStep = exploredPath.pop();
          backTrack.add(backStep);
          state.moveTo(backStep);
          currentNeighbours = state.getNeighbours();
        }
      }
      long destination = nextLocation.getId();
      exploredPath.push(state.getCurrentLocation());
      if (!backTrack.isEmpty()) {
        for (int i = backTrack.size() - 1; i > 0; i--) {
          exploredPath.push(backTrack.get(i));
        }
        exploredPath.addAll(backTrack);
      }
      state.moveTo(destination);
    } while (true);
  }

  /**
   * Escape from the cavern before the ceiling collapses, trying to collect as much
   * gold as possible along the way. Your solution must ALWAYS escape before time runs
   * out, and this should be prioritized above collecting gold.
   *
   * <p>You now have access to the entire underlying graph, which can be accessed
   * through EscapeState.
   * getCurrentNode() and getExit() will return you Node objects of interest, and getVertices()
   * will return a collection of all nodes on the graph.</p>
   *
   * <p>Note that time is measured entirely in the number of steps taken, and for each step
   * the time remaining is decremented by the weight of the edge taken. You can use
   * getTimeRemaining() to get the time still remaining, pickUpGold() to pick up any gold
   * on your current tile (this will fail if no such gold exists), and moveTo() to move
   * to a destination node adjacent to your current node.</p>
   *
   * <p>You must return from this function while standing at the exit. Failing to do so before time
   * runs out or returning from the wrong location will be considered a failed run.</p>
   *
   * <p>You will always have enough time to escape using the shortest path from the starting
   * position to the exit, although this will not collect much gold.</p>
   *
   * @param state the information available at the current state
   */
  public void escape(final EscapeState state) {
    //TODO: Escape from the cavern before time runs out
  }
}
