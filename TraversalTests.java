import static org.junit.Assert.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

public class TraversalTests {
	public static TreeSet<String> getSet(String ... items) {
		TreeSet<String> result = new TreeSet<>();
		for (String item : items) {
			result.add(item);
		}
		return result;
	}
	public Map<String, TreeSet<String>> createGraph1() {
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
		return myGraph;
	}
	@Test
	public void breadthFirstTest1() {
		Map<String, TreeSet<String>> graph = createGraph1();
		assertArrayEquals(new String [] {"A","B","D","E","G","F","H","C","I"},
							GraphTraversals.breadthFirstTraversal(graph, "A").toArray());
	}
	@Test
	public void depthFirstTest1() {
		Map<String, TreeSet<String>> graph = createGraph1();
		assertArrayEquals(new String [] {"A","B","E","F","C","H","I","D","G"},
							GraphTraversals.depthFirstTraversal(graph, "A").toArray());
	}
}

