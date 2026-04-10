package com.student.algovisualizer;

public class ShellSort implements Sorter {
    @Override
    public String getName() { return "Shell Sort"; }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        int n = array.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                    panel.updateAndSleep();
                }
                array[j] = temp;
                panel.updateAndSleep();
            }
        }
    }
}