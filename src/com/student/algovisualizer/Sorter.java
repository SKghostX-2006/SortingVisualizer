package com.student.algovisualizer;

public interface Sorter {
    // Every algorithm must implement this method
    void sort(int[] array, VisualizerPanel panel);
    
    // Every algorithm must return its name for the dropdown menu
    String getName();
}