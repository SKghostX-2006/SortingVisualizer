package com.student.algovisualizer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // We use invokeLater to ensure the UI builds safely
        SwingUtilities.invokeLater(() -> {
            
            // 1. Create the Main Window
            JFrame frame = new JFrame("Algorithm Visualizer - B.Tech Capstone");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Add your partner's beautiful VisualizerPanel to the center
            VisualizerPanel panel = new VisualizerPanel();
            frame.add(panel, BorderLayout.CENTER);

            // 2. Build the Control Panel at the bottom
            JPanel controlPanel = new JPanel();
            controlPanel.setBackground(Color.DARK_GRAY);

            // 3. Register ALL 10 of your Algorithms!
            Sorter[] algorithms = {
                new BubbleSort(), new SelectionSort(), new InsertionSort(),
                new QuickSort(), new MergeSort(), new HeapSort(),
                new ShellSort(), new CocktailShakerSort(), new GnomeSort(),
                new CombSort()
            };

            // Automatically grab the names for the dropdown
            String[] algoNames = new String[algorithms.length];
            for (int i = 0; i < algorithms.length; i++) {
                algoNames[i] = algorithms[i].getName();
            }

            // Create the UI Buttons
            JComboBox<String> algoDropdown = new JComboBox<>(algoNames);
            JButton startButton = new JButton("Start Sorting");
            JButton resetButton = new JButton("Reset Array");

            // 4. The "Reset" Button Logic
            resetButton.addActionListener(e -> panel.randomizeArray());

            // 5. The "Start" Button Logic (YOUR MASTERPIECE)
            startButton.addActionListener(e -> {
                
                // Disable the buttons while sorting so the user can't click them and break the app!
                startButton.setEnabled(false);
                resetButton.setEnabled(false);
                algoDropdown.setEnabled(false);

                // Find out which algorithm the user picked
                int selectedIndex = algoDropdown.getSelectedIndex();
                Sorter selectedSorter = algorithms[selectedIndex];

                // --- THE MULTI-THREADING MAGIC ---
                // We launch the sorting algorithm in a background thread so the UI doesn't freeze.
                new Thread(() -> {
                    // Run the sort!
                    selectedSorter.sort(panel.getArray(), panel);
                    
                    // Once the sort is 100% finished, turn the buttons back on safely
                    SwingUtilities.invokeLater(() -> {
                        startButton.setEnabled(true);
                        resetButton.setEnabled(true);
                        algoDropdown.setEnabled(true);
                    });
                }).start();
            });

            // 6. Add everything to the screen
            // Using HTML here just to make the text label white so we can read it on the dark background!
            controlPanel.add(new JLabel("<html><font color='white'>Select Algorithm: </font></html>"));
            controlPanel.add(algoDropdown);
            controlPanel.add(startButton);
            controlPanel.add(resetButton);

            frame.add(controlPanel, BorderLayout.SOUTH);

            // 7. Pack it up and show it!
            frame.pack();
            frame.setLocationRelativeTo(null); // This perfectly centers the window on your computer screen
            frame.setVisible(true);
        });
    }
}