import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

/*
 * THIS IS NOT A THOROUGH JUNIT TESTER. YOU SHOULD ADD YOUR OWN
 * TESTS FOR OTHER SITUATIONS, INCLUDING "EDGE" CASES:
 *  - DOES YOUR METHOD WORK CORRECTLY WHEN THE SET IS EMPTY?
 *  - DOES YOUR BOOLEAN METHOD RETURN TRUE WHEN IT SHOULD, AND FALSE WHEN IT SHOULD
 *  - DOES YOUR MAP HANDLE DUPLICATE KEYS CORRECTLY?
 *  AND SO ON
 */

class TreeMapJUnit {

	@Test
	void testFirstKey() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		map.put("dog", 1);
		map.put("cat", 2);
		assertEquals("cat", map.firstKey());
	}

	@Test
	void testFirstKeyThrowsCorrectException() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		assertThrows(NoSuchElementException.class, () -> map.firstKey());
	}

	@Test
	void testLastKey() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		map.put("dog", 1);
		map.put("cat", 2);
		assertEquals("dog", map.lastKey());
	}
	
	@Test
	void testLastKeyThrowsCorrectException() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		assertThrows(NoSuchElementException.class, () -> map.lastKey());
	}

	@Test
	void testPut() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		assertNull(map.put("dog", 1));
		assertEquals(1, map.put("dog", 2));
		assertTrue(map.containsKey("dog"));
	}

	@Test
	void testGet() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		assertNull(map.put("dog", 1));
		assertEquals(1, map.put("dog", 2));
		assertEquals(2, map.get("dog"));
	}

	@Test
	void testContainsKey() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		assertFalse(map.containsKey("dog"));
		map.put("dog", 2);
		assertTrue(map.containsKey("dog"));
	}

	@Test
	void testIsEmpty() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		assertTrue(map.isEmpty());
		map.put("dog", 1);
		assertFalse(map.isEmpty());
	}

	@Test
	void testClear() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		map.put("a", 1);
		map.put("b", 2);
		map.clear();
		assertTrue(map.isEmpty());
		assertEquals(0, map.size());
		assertFalse(map.containsKey("a"));
		assertFalse(map.containsKey("b"));
	}

	@Test
	void testSize() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		assertEquals(0, map.size());
		map.put("a", 1);
		assertEquals(1, map.size());
	}

	@Test
	void testKeySet() {
		TreeMap274<String, Integer> map = new TreeMap274<>();
		map.put("a", 1);
		map.put("b", 2);

		TreeSet<String> expected = new TreeSet<>();
		expected.add("a");
		expected.add("b");

		assertEquals(expected, map.keySet());
	}
	

}

