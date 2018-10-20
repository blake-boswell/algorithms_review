import java.util.*;

public class MaxSubarray {

    public static void main(String args[]) {
        testMaxCrossing();
        testMaxSubarray();
    }

    /**
     * Find the subarray with the highest sum of values that crosses a midpoint
     * 
     * @param array
     * @param low
     * @param mid
     * @param high
     * @return [ low, high, sum ]
     */
    public static int[] findMaxCrossingSubarray(int array[], int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int leftMax = mid;
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum >= leftSum) {
                leftSum = sum;
                leftMax = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        int rightMax = mid;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += array[i];
            if (sum >= rightSum) {
                rightSum = sum;
                rightMax = i;
            }
        }

        int returnArray[] = {leftMax, rightMax, rightSum + leftSum};
        return returnArray;
    }

    // Find the greatest subarray in the range [low, high], and the sum
    /**
     * Find the greatest subarray in the range [low, high]
     * @param array
     * @param low
     * @param high
     * @return [ low index, high index, subarray sum ]
     */
    public static int[] findMaxSubarray(int array[], int low, int high) {
        // Base case
        if (low == high) {
            int result[] = { low, high, array[low] };
            return result;
        }
        int mid = (int)Math.floor((high + low) / 2);
        // Find max subarray right
        int rightMaxResult[] = findMaxSubarray(array, mid + 1, high);
        int rightLow = rightMaxResult[0];
        int rightHigh = rightMaxResult[1];
        int rightSum = rightMaxResult[2];
        // Find max subarray left
        int leftMaxResult[] = findMaxSubarray(array, low, mid);
        int leftLow = leftMaxResult[0];
        int leftHigh = leftMaxResult[1];
        int leftSum = leftMaxResult[2];
        // Find max subarray crossing
        int crossingMaxResult[] = findMaxCrossingSubarray(array, low, mid, high);
        int crossingLow = crossingMaxResult[0];
        int crossingHigh = crossingMaxResult[1];
        int crossingSum = crossingMaxResult[2];
        // Return the max subarray of the 3 sections
        if (rightSum >= leftSum && rightSum >= crossingSum) {
            int maxSubarray[] = { rightLow, rightHigh, rightSum };
            return maxSubarray;
        }
        if (leftSum >= rightSum && leftSum >= crossingSum) {
            int maxSubarray[] = { leftLow, leftHigh, leftSum };
            return maxSubarray;
        }
        int maxSubarray[] = { crossingLow, crossingHigh, crossingSum };
        return maxSubarray;


    }

    public static void testMaxCrossing() {
        int array[] = {12, -2, 4, 0, -6, 4, 2, -7, 10, -4, 1};
        int low = 0;
        int high = 10;
        int mid = (int)Math.floor((high - low) / 2);
        int result[] = findMaxCrossingSubarray(array, low, mid, high);
        int lowBounds = result[0];
        int highBounds = result[1];
        int subarraySum = result[2];

        int expectedLowBounds = 0;
        int expectedHighBounds = 8;
        int expectedSubarraySum = 17;

        if (lowBounds != expectedLowBounds || highBounds != expectedHighBounds || subarraySum != expectedSubarraySum) {
            System.out.println("FindMaxCrossingSubarray test failed!\nExpected values:\tLow: " + expectedLowBounds
                + "\tHigh: " + expectedHighBounds + "\tSum: " + expectedSubarraySum);
            System.out.println("Returned values:\tLow: " + lowBounds + "\tHigh: " + highBounds
                + "\tSum: " + subarraySum);
            System.out.println("Test array:");
            for (int element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        } else {
            System.out.println("FindMaxCrossingSubarray test passed!");
        }
    }

    public static void testMaxSubarray() {
        int array[] = { -3, 10, 4, 0, -7, 4, 2, -10, 10, -4, 1 };
        int low = 0;
        int high = 10;
        int result[] = findMaxSubarray(array, low, high);
        int lowBounds = result[0];
        int highBounds = result[1];
        int subarraySum = result[2];

        int expectedLowBounds = 1;
        int expectedHighBounds = 2;
        int expectedSubarraySum = 14;

        if (lowBounds != expectedLowBounds || highBounds != expectedHighBounds || subarraySum != expectedSubarraySum) {
            System.out.println("FindMaxSubarray test failed!\nExpected values:\tLow: " + expectedLowBounds
                + "\tHigh: " + expectedHighBounds + "\tSum: " + expectedSubarraySum);
            System.out.println("Returned values:\tLow: " + lowBounds + "\tHigh: " + highBounds
                + "\tSum: " + subarraySum);
            System.out.println("Test array:");
            for (int element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        } else {
            System.out.println("FindMaxSubarray test passed!");
        }
    }
}