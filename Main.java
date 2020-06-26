
class Main {
    int[] replaceArrayValues(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] > 0) ? 0 : 1;
        }
        return arr;
    }

    int[] fillArray() {
        final int[] source = {0, 3, 6, 9, 12, 15, 18, 21};
        int[] target = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
        return target;
    }

    int[] multiplyArrayValues() {
        int[] source = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < source.length; i++) {
            if (source[i] < 6) {
                source[i] *= 2;
            }
        }
        return source;
    }

    int[][] fillDiagonals(int size) {
        int[][] m = new int[size][size];

        for (int i = 0; i < size; i++) {
            m[i][i] = 1;
            m[i][size - 1 - i] = 1;
        }
        return m;
    }

    int[] findMinAndMax(int[] arr) {
        int[] result = new int[2];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int value : arr) {
            if (value < min) {
                min = value;
            }
            if (value > max) {
                max = value;
            }
        }
        result[0] = min;
        result[1] = max;
        return result;
    }

    boolean checkBalance(int[] arr) {
        if (arr.length < 4) {
            return false;
        }
        boolean equals = false;
        int limit = arr.length - 2;
        while (limit > 2) {
            int leftSum = 0;
            int rightSum = 0;

            for (int i = 0; i < limit; i++) {
                leftSum += arr[i];
            }
            for (int i = limit; i < arr.length; i++) {
                rightSum += arr[i];
            }
            if (leftSum == rightSum) {
                equals = true;
                break;
            } else limit -= 1;
        }
        return equals;
    }

    int[] shiftArrValues(int[] arr, int n) {
        int shift = Math.abs(n);
        if (shift > arr.length) {
            shift = shift % arr.length;
        }
        int shiftPoint = arr.length - shift;
        int[] arr1 = new int[shiftPoint];
        int[] arr2 = new int[shift];

        if (n > 0) {
            System.arraycopy(arr, 0, arr1, 0, shiftPoint);
            System.arraycopy(arr, shiftPoint, arr2, 0, shift);
            System.arraycopy(arr2, 0, arr, 0, arr2.length);
            System.arraycopy(arr1, 0, arr, arr2.length, arr1.length);
        } else {
            System.arraycopy(arr, 0, arr2, 0, shift);
            System.arraycopy(arr, shift, arr1, 0, shiftPoint);
            System.arraycopy(arr1, 0, arr, 0, arr1.length);
            System.arraycopy(arr2, 0, arr, arr1.length, arr2.length);
        }

        return arr;
    }
}

