import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Sorting implementation CS 1332 : Fall 2014
 *
 * @author Yuen Han Chan
 * @version 1.0
 */
public class Sorting implements SortingInterface {

    // Do not add any instance variables.
    private LinkedList<Integer>[] list;

    @Override
    public <T extends Comparable<? super T>> void bubblesort(T[] arr) {
        int length = arr.length;
        for (int b = 0; b < length; b++) {
            for (int a = 0; a < length - 1; a++) {
                if (arr[a].compareTo(arr[a + 1]) > 0) {
                    swap(arr, a, a + 1);
                }
            }
        }
    }

    private <T> void swap(T[] arr, int a, int b) {
        T newSwap = arr[a];
        arr[a] = arr[b];
        arr[b] = newSwap;
    }

    @Override
    public <T extends Comparable<? super T>> void insertionsort(T[] arr) {
        int length = arr.length;
        for (int a = 0; a < length; a++) {
            int b = a;
            while ((b > 0) && (arr[b - 1].compareTo(arr[b]) > 0)) {
                swap(arr, b, b - 1);
                b = b - 1;
            }
        }
    }

    @Override
    public <T extends Comparable<? super T>> void selectionsort(T[] arr) {
        int length = arr.length;
        for (int a = 0; a < length; a++) {
            int currentMin = a;
            for (int b = a; b < length; b++) {
                if (arr[currentMin].compareTo(arr[b]) > 0) {
                    currentMin = b;
                }
            }
            swap(arr, currentMin, a);
        }

    }

    @Override
    public <T extends Comparable<? super T>> void quicksort(T[] arr, Random r) {
        quickSort2(arr, 0, arr.length, r);
    }

    private <T extends Comparable<? super T>> void quickSort2(T[] arr, int l,
            int r, Random rand) {
        if (l >= r) {
            return;
        }
        int pivot = r - 1;
        int j = l;
        int k = r - 1;
        while (j <= k) {
            while ((j <= k) && (arr[j].compareTo(arr[pivot]) <= 0)) {
                j++;
            }
            while ((j <= k) && (arr[k].compareTo(arr[pivot]) > 0)) {
                k++;
            }
            if (j <= k) {
                swap(arr, j, k);
                j++;
                k--;
            }
        }
        quickSort2(arr, l, j - 1, new Random());
        quickSort2(arr, j, r, new Random());
    }

    @Override
    public <T extends Comparable<? super T>> void mergesort(T[] arr) {
        T[] arrTemp = (T[]) new Comparable[arr.length];
        mergeHelper(arr, arrTemp, 0, arrTemp.length - 1);
    }

    private <T extends Comparable<? super T>> void mergeHelper(T[] arr,
            T[] arrTemp, int low, int high) {
        if (low == high) {
            return;
        } else {
            int middle = (low + high) / 2;
            mergeHelper(arr, arrTemp, low, middle);
            mergeHelper(arr, arrTemp, middle + 1, high);
            merge(arr, arrTemp, low, middle, high);
        }
    }

    private <T extends Comparable<? super T>> void merge(T[] arr, T[] temp,
            int low, int middle, int end) {
        int count = low;
        int rightLow = middle + 1;

        while (low <= middle && rightLow <= end) {
            if (arr[low].compareTo(arr[rightLow]) <= 0) {
                temp[count++] = arr[low++];
            } else {
                temp[count++] = arr[rightLow++];
            }
        }

        while (low <= middle) {
            temp[count++] = arr[low++];
        }

        while (rightLow <= end) {
            temp[count++] = arr[rightLow++];
        }

        for (int i = 0; i < count; i++) {
            arr[i] = temp[i];
        }
    }

    @Override
    public int[] radixsort(int[] arr) {
        int posMax = 0;
        int negMax = 0;
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                if (arr[i] > posMax) {
                    posMax = arr[i];

                }
                pos.add(arr[i]);
            } else {
                if (arr[i] < negMax) {
                    negMax = arr[i];

                }
                neg.add(arr[i] * -1);
            }
        }

        int posSigDigit = 1;
        int negSigDigit = 1;
        while (posMax / 10 != 0) {
            posSigDigit++;
            posMax /= 10;
        }
        while ((negMax * -1) / 10 != 0) {
            negSigDigit++;
            negMax /= ((negMax * -1) / 10) * -1;
        }

        int m = 10, n = 1;
        int[] posSorted = new int[pos.size()];
        int[] negSorted = new int[neg.size()];
        posSorted = radixsort(pos, m, n, posSigDigit);
        negSorted = radixsort(neg, m, n, negSigDigit);
        int counter = 0;
        for (int b = negSorted.length; b > 0; b--) {
            arr[counter++] = (negSorted[b - 1] * -1);
        }
        for (int b = 0; b < posSorted.length; b++) {
            arr[counter++] = posSorted[b];
        }
        return arr;
    }

    private int[] radixsort(ArrayList<Integer> num, int m, int n, int sf) {
        int countSf = sf;
        int[] arr = new int[num.size()];
        while (countSf > 0) {
            list = new LinkedList[] {new LinkedList<Integer>(),
                new LinkedList<Integer>(),
                new LinkedList<Integer>(),
                new LinkedList<Integer>(), new LinkedList<Integer>(),
                new LinkedList<Integer>(), new LinkedList<Integer>(),
                new LinkedList<Integer>(), new LinkedList<Integer>(),
                new LinkedList<Integer>() };
            for (int i = 0; i < num.size(); i++) {
                int result = ((num.get(i) % m) / n);
                list[result].add(num.get(i));
            }
            num.clear();
            for (int i = 0; i < list.length; i++) {
                Integer number = null;
                while ((number = list[i].poll()) != null) {
                    num.add(number);
                }
            }
            countSf--;
            m *= 10;
            n *= 10;
        }
        int count = 0;
        for (int x : num) {
            arr[count++] = x;
        }
        return arr;
    }
}