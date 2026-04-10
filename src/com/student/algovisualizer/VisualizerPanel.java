package com.student.algovisualizer;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.util.Random;

public class VisualizerPanel extends JPanel {
    
    // The size of our graph paper (the screen)
    private final int SCREEN_WIDTH = 800;
    private final int SCREEN_HEIGHT = 600;
    
    // We made the bars a bit fatter (20) so the numbers can actually fit on them!
    private final int BAR_WIDTH = 20; 
    private final int NUM_BARS = SCREEN_WIDTH / BAR_WIDTH;
    
    private int[] array;
    
    public VisualizerPanel() {
        // Set up the canvas size and background color
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.array = new int[NUM_BARS];
        randomizeArray();
    }
    
    public void randomizeArray() {
        Random random = new Random();
        for (int i = 0; i < NUM_BARS; i++) {
            // Give the bars a random height. 
            // We subtract 50 from the max height to leave "headroom" at the top of the screen so our numbers don't get cut off!
            array[i] = random.nextInt(SCREEN_HEIGHT - 50) + 20; 
        }
        repaint(); // Tell the screen to redraw immediately
    }
    
    // This is the magic method your algorithms call to create the animation
    public void updateAndSleep() {
        repaint(); 
        try {
            // Pause for 20 milliseconds. You can change this number to make it faster or slower!
            Thread.sleep(20); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // We need these so your algorithms can grab the array and modify it
    public int[] getArray() {
        return array;
    }
    
    public void setArray(int[] array) {
        this.array = array;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // This wipes the screen clean like a whiteboard eraser every frame
        
        if (array == null) return;
        
        // 1. THE GLOW UP: Upgrade our standard 'Graphics' to 'Graphics2D' (The Pro Version)
        Graphics2D g2d = (Graphics2D) g;
        
        // Turn on Anti-aliasing to make everything look HD and smooth
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        for (int i = 0; i < array.length; i++) {
            int x = i * BAR_WIDTH;
            int height = array[i];
            int y = SCREEN_HEIGHT - height;
            
            // 2. DYNAMIC COLORS (The Heatmap Effect)
            // We calculate a "Hue" based on how tall the bar is compared to the screen.
            // It starts at 0.5 (Ice Blue) and goes up to 0.9 (Neon Pink) for the tallest bars.
            float hue = 0.5f + ((float) height / SCREEN_HEIGHT) * 0.4f;
            g2d.setColor(Color.getHSBColor(hue, 0.8f, 1.0f));
            
            // 3. ROUNDED CORNERS
            // Instead of fillRect, we use fillRoundRect. The '10, 10' at the end makes the corners curve nicely.
            // We add extra height at the bottom so only the TOP corners look rounded!
            g2d.fillRoundRect(x, y, BAR_WIDTH - 2, height + 20, 10, 10); 
            
            // 4. THE NUMBERS
            g2d.setColor(Color.WHITE);
            if (BAR_WIDTH >= 15) {
                // We float the text 5 pixels above the bar's Y coordinate
                g2d.drawString(String.valueOf(height), x, y - 5);
            }
        }
    }
}