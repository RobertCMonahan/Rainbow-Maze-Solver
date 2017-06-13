import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Dijkstra {
    protected static boolean search(Maze maze) {
        System.out.println("Dijkstra");

        // set all node distance to max (completed when nodes are created)
        // settled and unsettled nodes, create unsettled arrayList and add all nodes to it
            // settled = visited == true
            // unsettled = visited == false
        // parents node needs to be tracked

        //put all nodes into unsettled nodes
        List<Maze.MazeNode> unsettledNodes = maze.getNodes();

//        while (unsettlednodes != 0){
//            evaluationNode = getNodeWithLowestDistance(UnSettledNodes)
//            remove evaluationNode from UnSettledNodes
//            mark evaluationNode as visited
//            evaluatedNeighbors(evaluationNode)
//        }
//
//
//        getNodeWithLowestDistance(UnSettledNodes){
//            find the node with the lowest distance in UnSettledNodes and return it
//        }

//        evaluatedNeighbors(evaluationNode){
//            Foreach destinationNode which can be reached via an edge from evaluationNode AND which is not in SettledNodes {
//                edgeDistance = getDistance(edge(evaluationNode, destinationNode))
//                newDistance = distance[evaluationNode] + edgeDistance
//                if (distance[destinationNode]  > newDistance ) {
//                    distance[destinationNode]  = newDistance
//                    add destinationNode to UnSettledNodes
//                }
//            }
//        }



        return false;
    }



}
