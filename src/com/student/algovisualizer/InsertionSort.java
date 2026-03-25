package com.student.algovisualizer;

public class InsertionSort implements Sorter {
    
    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public void sort(int[] array, VisualizerPanel panel) {
        int n = array.length;
        
        for (int i = 1; i < n; ++i) {
            int currentCard = array[i];
            int j = i - 1;

            // Move elements that are bigger than our current card one position ahead
            while (j >= 0 && array[j] > currentCard) {
                array[j + 1] = array[j];
                j = j - 1;
                
                // Animate the shifting process
                panel.updateAndSleep(); 
            }
            
            // Put the card in its correct empty slot
            array[j + 1] = currentCard;
            panel.updateAndSleep();
        }
    }
}