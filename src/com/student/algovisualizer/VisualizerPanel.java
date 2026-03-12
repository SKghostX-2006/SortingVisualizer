package com.student.algovisualizer;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class VisualizerPanel extends JPanel {
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    private final int BAR_WIDTH = 10; 
    private final int NUM_BARS = SCREEN_WIDTH / BAR_WIDTH;
    private int[] array;

    public VisualizerPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        array = new int[NUM_BARS];
        randomizeArray();
    }

    public void randomizeArray() {
        Random rng = new Random();
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = rng.nextInt(SCREEN_HEIGHT - 50) + 10;
        }
        repaint();
    }

    public int[] getArray() {
        return array;
    }

    // --- THE ANIMATION ENGINE ---
    public void updateAndSleep() {
        repaint(); // Redraw the screen
        try {
            Thread.sleep(15); // 15 millisecond delay for animation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < array.length; i++) {
            int x = i * BAR_WIDTH;
            int y = SCREEN_HEIGHT - array[i];
            g.setColor(Color.CYAN);
            // Draw rectangle (with a 1px gap for visual separation)
            g.fillRect(x, y, BAR_WIDTH - 1, array[i]); 
        }
    }
}