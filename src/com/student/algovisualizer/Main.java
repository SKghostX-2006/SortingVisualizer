package com.student.algovisualizer;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // 1. Setup the Main Window
        JFrame frame = new JFrame("Algorithm Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // 2. Setup the Canvas
        VisualizerPanel panel = new VisualizerPanel();
        
        // 3. Register our Algorithms
        Sorter[] algorithms = { new BubbleSort(), new SelectionSort() };
        String[] algoNames = { algorithms[0].getName(), algorithms[1].getName() };

        // 4. Create the Dashboard (Bottom Menu)
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.DARK_GRAY);
        
        JComboBox<String> algoSelector = new JComboBox<>(algoNames);
        JButton startBtn = new JButton("Start Sorting");
        JButton resetBtn = new JButton("Reset Array");

        // Styling the labels
        JLabel selectLabel = new JLabel("Choose Algorithm: ");
        selectLabel.setForeground(Color.WHITE);

        controlPanel.add(selectLabel);
        controlPanel.add(algoSelector);
        controlPanel.add(startBtn);
        controlPanel.add(resetBtn);

        // 5. Button Actions
        resetBtn.addActionListener(e -> panel.randomizeArray());

        startBtn.addActionListener(e -> {
            // We run the algorithm in a new Thread. 
            // If we didn't do this, the GUI buttons would freeze while sorting!
            new Thread(() -> {
                int selectedIndex = algoSelector.getSelectedIndex();
                Sorter selectedAlgo = algorithms[selectedIndex];
                selectedAlgo.sort(panel.getArray(), panel);
            }).start();
        });

        // 6. Assemble the Window
        frame.add(panel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }
}