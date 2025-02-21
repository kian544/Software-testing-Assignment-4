package org.example;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {
    /**
     * Step 1: understand the requirement, input type and output type
     * Requirement: Add two list of integer, index by index, and returns another list
     * <p>
     * Step 2 (raw):  Perform partition and boundary analysis on input and output
     * Each input: left | right
     * Combination of input:
     * Output:
     * Step 3: Derive potential test cases
     */
    @Test /** Testing basic addition, 8 + 9 = 17*/
    public void testBaseAddition() {
        List<Integer> left = Arrays.asList(8);
        List<Integer> right = Arrays.asList(9);
        List<Integer> expectedOutput = Arrays.asList(1,7);
        List<Integer> actualOutput = NumberUtils.add(left, right);
        assert expectedOutput.equals(actualOutput);

    }
    @Test/** Testing null input, null + null = null*/
    public void testNullAdditionLeft() {
        List<Integer> left = null;
        List<Integer> right = Arrays.asList(9);
        List<Integer> expectedOutput = null;
        List<Integer> actualOutput = NumberUtils.add(left, right);
        assertNull(actualOutput);
    }
    @Test /** testing empty input, [ ], or 0 +4 = 4*/
    public void testEmptyList() {
        List<Integer> left = Arrays.asList();
        List<Integer> right = Arrays.asList(4);
        List<Integer> expectedOutput = Arrays.asList(4);
        List<Integer> actualOutput = NumberUtils.add(left, right);
        assert expectedOutput.equals(actualOutput);
    }
    @Test /** Testing addition that requires a carry, 96 + 4 = 100*/
    public void testCarryAddition() {
        List<Integer> left = Arrays.asList(9,6);
        List<Integer> right = Arrays.asList(4);
        List<Integer> expectedOutput = Arrays.asList(1,0,0);
        List<Integer> actualOutput = NumberUtils.add(left, right);
        assert expectedOutput.equals(actualOutput);
    }
    @Test /** Testing negative input, -2 + 45 = Error*/
    public void testInvalidNegativeDigit() {
        List<Integer> left = Arrays.asList(-2);
        List<Integer> right = Arrays.asList(4, 5);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }
    @Test/** Testing out of range input x>0-9, 100 + 9 = Error*/
    public void testInvalidInputLarge(){
        List<Integer> left = Arrays.asList(100);
        List<Integer> right = Arrays.asList(9);
        List<Integer> expectedOutput = null;
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test/** Covers Leading Zero Removal**/
    @Tag("Coverage")
    public void testRemoveLeadingZeros() {
        List<Integer> left = Arrays.asList(0,0,1,2,4);
        List<Integer> right = Arrays.asList(0,0,1,2,4);
        List<Integer> expectedOutput= Arrays.asList(2,4,8);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(expectedOutput,result);

    }
    @Test/** Covers Right side null input **/
    @Tag("Coverage")
    public void testNullAdditionRight() {
        List<Integer> left = Arrays.asList(9);
        List<Integer> right = null;
        List<Integer> expectedOutput = null;
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(expectedOutput,result);
    }

    @Test/** Covers Negative Right side **/
    @Tag("Coverage")
    public void testRightOutOfBoundsNegative() {
        List<Integer> left = Arrays.asList(9,6);
        List<Integer> right = Arrays.asList(-4);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test /** Covers Number Over 9 on right side**/
    @Tag("Coverage")
    public void testRightOutOfBoundsPlusTen() {
        List<Integer> left = Arrays.asList(9,6);
        List<Integer> right = Arrays.asList(10);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

}