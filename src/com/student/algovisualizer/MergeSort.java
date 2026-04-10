package com.student.algovisualizer;

public class MergeSort implements Sorter {
    @Override
    public String getName() { return "Merge Sort"; }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        mergeSortHelper(array, 0, array.length - 1, panel);
    }

    private void mergeSortHelper(int[] array, int left, int right, VisualizerPanel panel) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(array, left, mid, panel);
            mergeSortHelper(array, mid + 1, right, panel);
            merge(array, left, mid, right, panel);
        }
    }

    private void merge(int[] array, int left, int mid, int right, VisualizerPanel panel) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) { temp[k++] = array[i++]; } 
            else { temp[k++] = array[j++]; }
        }
        while (i <= mid) { temp[k++] = array[i++]; }
        while (j <= right) { temp[k++] = array[j++]; }

        for (i = left; i <= right; i++) {
            array[i] = temp[i - left];
            panel.updateAndSleep(); // Animate the merging!
        }
    }
}