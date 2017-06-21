import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Dijkstra {
    protected static boolean search(Maze maze) {
        System.out.println("Dijkstra");

        // set all node distance to max (completed when nodes are created) done
        // settled and unsettled nodes, create unsettled arrayList and add all nodes to it done
        // settled = visited == true
        // unsettled = visited == false
        // parents node needs to be tracked (use set parent)

        //put all nodes into unsettled nodes
        List<Maze.MazeNode> unsettledNodes = maze.getNodes();
        List<Maze.MazeNode> settledNodes = null;

        while (!unsettledNodes.isEmpty()) {
            Maze.MazeNode evaluationNode = getNodeWithLowestDistance(unsettledNodes);
            //remove evaluationNode from UnSettledNodes
            //mark evaluationNode as visited
            unsettledNodes.remove(evaluationNode);
            //mark evaluationNode as visited
            settledNodes.add(evaluationNode);
            evaluatedNeighbors(evaluationNode, settledNodes);
            settledNodes.add(evaluationNode);
        }
        return false;
    }

    private static Maze.MazeNode getNodeWithLowestDistance(List<Maze.MazeNode> unsettledNodes){
            //find the node with the lowest distance in UnSettledNodes and return it
            //this would be the place to have a heap in place of the for loop (which is horribly ineffecent)
            //fibinacci heap most likly thats what was used for the computerphile video on maze solvers
            //although I should check to make sure binomial wont work (maybe I'll implemnt both and see which is faster)
            Maze.MazeNode shortestNode = null;
            int shortestDistance = Integer.MAX_VALUE;
            for(Maze.MazeNode node:unsettledNodes){
                if (node.getDistanceFromStart() <= shortestDistance){
                    shortestDistance = node.getDistanceFromStart();
                    shortestNode = node;
                }
            }
            if (shortestNode == null){
                System.out.print("ERROR: shortestNode == null, Dijkstra.java, getNodeWithLowestDistance should never have been evaluated.");
                //!! remove above if statment after testing.
            }
            return shortestNode;
        }

        private static evaluatedNeighbors(Maze.MazeNode evaluationNode, List<Maze.MazeNode> settledNodes){
//            Foreach destinationNode which can be reached via an edge from evaluationNode AND which is not in SettledNodes {
//                edgeDistance = getDistance(edge(evaluationNode, destinationNode))
//                newDistance = distance[evaluationNode] + edgeDistance
//                if (distance[destinationNode]  > newDistance ) {
//                    distance[destinationNode]  = newDistance
//                    add destinationNode to UnSettledNodes
//                }
//            }
        for (Maze.MazeNode neighborNode : evaluationNode.getArrayOfNeighborNodes()){
            if (settledNodes.indexOf(neighborNode) == -1){ //if neigborNode is unsettled
                edgeDistance
            }
        }

        }

