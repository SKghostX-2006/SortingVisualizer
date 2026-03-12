package com.student.algovisualizer;

public class BubbleSort implements Sorter {
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap the numbers
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    
                    // Trigger the animation
                    panel.updateAndSleep();
                }
            }
        }
    }
}