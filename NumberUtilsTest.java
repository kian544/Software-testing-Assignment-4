package org.example;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    @Test /** Basic addition: 8 + 9 = 17 */
    public void testBaseAddition() {
        List<Integer> left = Arrays.asList(8);
        List<Integer> right = Arrays.asList(9);
        List<Integer> expectedOutput = Arrays.asList(1, 7);
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test /** Null input: null + any number = null */
    public void testNullAddition() {
        assertNull(NumberUtils.add(null, Arrays.asList(9)));
        assertNull(NumberUtils.add(Arrays.asList(9), null));
        assertNull(NumberUtils.add(null, null));
    }

    @Test /** Empty input: [] + 4 = [4] */
    public void testEmptyList() {
        assertEquals(Arrays.asList(4), NumberUtils.add(Arrays.asList(), Arrays.asList(4)));
    }

    @Test /** Carry-over scenario: 96 + 4 = 100 */
    public void testCarryAddition() {
        List<Integer> left = Arrays.asList(9, 6);
        List<Integer> right = Arrays.asList(4);
        List<Integer> expectedOutput = Arrays.asList(1, 0, 0);
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test /** Invalid input: negative number */
    public void testInvalidNegativeDigit() {
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(Arrays.asList(-2), Arrays.asList(4, 5)));
    }

    @Test /** Invalid input: digit > 9 */
    public void testInvalidInputLarge() {
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(Arrays.asList(100), Arrays.asList(9)));
    }

    @Test /** Leading zero removal */
    public void testRemoveLeadingZeros() {
        List<Integer> left = Arrays.asList(0, 0, 1, 2, 4);
        List<Integer> right = Arrays.asList(0, 0, 1, 2, 4);
        List<Integer> expectedOutput = Arrays.asList(2, 4, 8);
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    void testBothListsEmpty() {
        List<Integer> left = Arrays.asList();
        List<Integer> right = Arrays.asList();
        List<Integer> expectedOutput = Arrays.asList();
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    @Tag("Mutations")
    void testFinalCarry() {
        List<Integer> left = Arrays.asList(9, 9, 9);
        List<Integer> right = Arrays.asList(1);
        List<Integer> result = NumberUtils.add(left, right);

        assertEquals(4, result.size(), "Carry should extend the size of the result.");
        assertEquals(1, result.get(0), "Leading carry should be 1.");
    }


    @Test
    @Tag("Mutations")
    void testLoopBoundary() {
        List<Integer> left = Arrays.asList(1);
        List<Integer> right = Arrays.asList(9, 9, 9);
        List<Integer> result = NumberUtils.add(left, right);

        assertEquals(4, result.size(), "Loop boundary should process all digits correctly.");
        assertEquals(Arrays.asList(1, 0, 0, 0), result, "Expected carry should propagate correctly.");
    }


    @Test
    @Tag("Mutations")
    void testLeadingZeroEdgeCase() {
        List<Integer> left = Arrays.asList(0, 0, 0, 5);
        List<Integer> right = Arrays.asList(0, 0, 0, 5);
        List<Integer> result = NumberUtils.add(left, right);

        assertFalse(result.get(0) == 0 && result.size() > 1, "Leading zeros should be removed.");
        assertEquals(Arrays.asList(1, 0), result, "Leading zeros should be stripped, and result should be correct.");
    }

    @Test
    @Tag("Mutations")
    void testLeadingZeroMutationFix() {
        List<Integer> left = Arrays.asList(0, 0, 0, 0, 1);
        List<Integer> right = Arrays.asList(0, 0, 0, 0, 2);
        List<Integer> result = NumberUtils.add(left, right);

        assertEquals(Arrays.asList(3), result, "Result should not contain leading zeros.");

        List<Integer> leftZero = Arrays.asList(0, 0, 0);
        List<Integer> rightZero = Arrays.asList(0, 0, 0);
        List<Integer> zeroResult = NumberUtils.add(leftZero, rightZero);

        assertEquals(Arrays.asList(0), zeroResult, "Edge case: sum of zeros should return [0] exactly.");
    }

}
