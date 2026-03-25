package com.student.algovisualizer;

public class QuickSort implements Sorter {

    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        // We use a helper method here because Quick Sort needs to know the start and end of the array
        quickSortHelper(array, 0, array.length - 1, panel);
    }

    private void quickSortHelper(int[] array, int low, int high, VisualizerPanel panel) {
        if (low < high) {
            // Find the pivot and split the array
            int pivotIndex = partition(array, low, high, panel);
            
            // Sort the left side, then sort the right side
            quickSortHelper(array, low, pivotIndex - 1, panel);
            quickSortHelper(array, pivotIndex + 1, high, panel);
        }
    }

    private int partition(int[] array, int low, int high, VisualizerPanel panel) {
        int pivotValue = array[high]; // Make the last bar the boss
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            // If current bar is smaller than the boss, move it to the left side
            if (array[j] < pivotValue) {
                i++;
                // Swap
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                panel.updateAndSleep(); // Animate!
            }
        }
        
        // Put the boss in their final correct spot
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        panel.updateAndSleep();
        
        return i + 1;
    }
}