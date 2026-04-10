package com.student.algovisualizer;

public class CombSort implements Sorter {
    @Override
    public String getName() { return "Comb Sort"; }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        int n = array.length;
        int gap = n;
        boolean swapped = true;

        while (gap != 1 || swapped) {
            gap = (gap * 10) / 13; // Shrink factor
            if (gap < 1) { gap = 1; }

            swapped = false;
            for (int i = 0; i < n - gap; i++) {
                if (array[i] > array[i + gap]) {
                    int temp = array[i];
                    array[i] = array[i + gap];
                    array[i + gap] = temp;
                    swapped = true;
                    panel.updateAndSleep();
                }
            }
        }
    }
}