import java.util.*;

public class GraphTraversals {
	public static ArrayList<String> depthFirstTraversal(Map<String, TreeSet<String>> graph,
														String startingVertex) {
		ArrayList<String> result = new ArrayList<>();
		Stack<String> toDo = new Stack<String>();
		Set<String> visited = new HashSet<String>();
		
		toDo.push(startingVertex);
		visited.add(startingVertex);
		result.add(startingVertex);
		
		while (!toDo.isEmpty()) {
			String vertex = toDo.peek();
			boolean unVisitedNeighbor = false;
			
			for (String connectedVertex : graph.get(vertex)) {
				if (!visited.contains(connectedVertex) && !unVisitedNeighbor) {
					unVisitedNeighbor = true;
					toDo.push(connectedVertex);
					visited.add(connectedVertex);
					result.add(connectedVertex);
				}
			}
			
			if (!unVisitedNeighbor) {
				toDo.pop();
			}
		}
		
		return result;
	}

	public static ArrayList<String> breadthFirstTraversal(Map<String, TreeSet<String>> graph, String startingVertex) {
		ArrayList<String> result = new ArrayList<>();
		Queue<String> toDo = new PriorityQueue<String>();
		Set<String> visited = new HashSet<String>();
		
		toDo.add(startingVertex);
		visited.add(startingVertex);
		result.add(startingVertex);
		
		while (!toDo.isEmpty()) {
			String vertex = toDo.remove();
			for (String connectedVertex : graph.get(vertex)) {
				if (!visited.contains(connectedVertex)) {
					toDo.add(connectedVertex);
					visited.add(connectedVertex);
					result.add(connectedVertex);
				}
			}
		}
		
		return result;
	}
	
	public static TreeSet<String> getSet(String ... items) {
		TreeSet<String> result = new TreeSet<>();
		for (String item : items) {
			result.add(item);
		}
		return result;
	}
	
	public static void main(String [] args) {
		Map<String, TreeSet<String>> myGraph = new TreeMap<>();
		myGraph.put("A", getSet("B","D","E"));
		myGraph.put("B", getSet("E"));
		myGraph.put("C", getSet("B"));
		myGraph.put("D", getSet("G"));
		myGraph.put("E", getSet("H","F"));
		myGraph.put("F", getSet("C", "H"));
		myGraph.put("G", getSet("H"));
		myGraph.put("H", getSet("I"));
		myGraph.put("I", getSet("F"));

		System.out.println(depthFirstTraversal(myGraph, "A"));
		System.out.println(breadthFirstTraversal(myGraph, "A"));
	}

}

