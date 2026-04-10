package com.student.algovisualizer;

public class HeapSort implements Sorter {
    @Override
    public String getName() { return "Heap Sort"; }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        int n = array.length;
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) { heapify(array, n, i, panel); }
        // Extract elements one by one
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            panel.updateAndSleep();
            heapify(array, i, 0, panel);
        }
    }

    private void heapify(int[] array, int n, int i, VisualizerPanel panel) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) { largest = left; }
        if (right < n && array[right] > array[largest]) { largest = right; }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            panel.updateAndSleep();
            heapify(array, n, largest, panel);
        }
    }
}