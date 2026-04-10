package com.student.algovisualizer;

public class CocktailShakerSort implements Sorter {
    @Override
    public String getName() { return "Cocktail Shaker Sort"; }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        boolean swapped = true;
        int start = 0;
        int end = array.length;

        while (swapped) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i]; array[i] = array[i + 1]; array[i + 1] = temp;
                    swapped = true; panel.updateAndSleep();
                }
            }
            if (!swapped) break;
            swapped = false;
            end = end - 1;
            for (int i = end - 1; i >= start; i--) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i]; array[i] = array[i + 1]; array[i + 1] = temp;
                    swapped = true; panel.updateAndSleep();
                }
            }
            start = start + 1;
        }
    }
}