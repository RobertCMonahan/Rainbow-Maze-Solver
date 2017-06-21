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
        List<Maze.MazeNode> nodes = maze.getNodes();
        List<Maze.MazeNode> unsettledNodes = new ArrayList<Maze.MazeNode>();
        unsettledNodes.add(nodes.get(0)); // add start node to unsettledNodes

        List<Maze.MazeNode> settledNodes = null;

        while (!unsettledNodes.isEmpty()) {
            Maze.MazeNode evaluationNode = getNodeWithLowestDistance(unsettledNodes);
            unsettledNodes.remove(evaluationNode);
            settledNodes.add(evaluationNode);
            evaluatedNeighbors(evaluationNode, settledNodes);
        }
        return false;
    }

    private static Maze.MazeNode getNodeWithLowestDistance(List<Maze.MazeNode> unsettledNodes){
            //should change for loop too a fib or binomal heap for improved preformance
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
                int distanceToNeighbor = getDistance(evaluationNode, neighborNode);
                int neighborsDistanceFromStart = evaluationNode.getDistanceFromStart() + distanceToNeighbor;
                if (neighborsDistanceFromStart <= neighborNode.getDistanceFromStart()) {
                    neighborNode.setDistanceFromStart(neighborsDistanceFromStart);
                }
            }
        }

        }

