package graphs3;

import static graphs3.Main.finalNodeField;
import static graphs3.Main.initialNodeField;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel 
  {
    private final Point[] vertexPositions;
    private final int[][] adjMatrix;
    private int[] shortestPath;
    private boolean showShortestPath;

    public GraphPanel(Point[] vertexPositions, int[][] adjMatrix) 
    {
        this.vertexPositions = vertexPositions;
        this.adjMatrix = adjMatrix;
        this.shortestPath = new int[0]; // Initializing with an empty array to represent initial no shortest path
        this.showShortestPath = false; // Initially false since there is no shortest path at this point
        this.setPreferredSize(new Dimension(550, 525)); // panel size
    }

    public void setShortestPath(int[] path) 
    {
        this.shortestPath = path;
        this.showShortestPath = true;
        repaint(); // displays updated shortest path on the graph
    }

  

    public void resetGraph() 
    {
        this.shortestPath = new int[0]; // clearing the shortest path
        this.showShortestPath = false; // setting to initial condition of no shortest path
        initialNodeField.setText("");//setting the initial node field to null
        finalNodeField.setText("");//setting the final node field to null
        repaint(); // to update the display
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // drawing edges
         for (int i = 0; i < adjMatrix.length; i++) 
         {Point pos1 = vertexPositions[i];//setting pos1 as the first edge
            for (int j = i + 1; j < adjMatrix.length; j++) 
            {Point pos2 = vertexPositions[j];//setting pos2 as the adjacent edge
                if (adjMatrix[i][j] != 0) 
                {g2d.setColor(Color.BLACK);//setting the colur to black
                 g2d.drawLine(vertexPositions[i].x, vertexPositions[i].y, vertexPositions[j].x, vertexPositions[j].y);//drawing the line between edges
                 int weight = (int) Math.round(Math.sqrt(Math.pow(pos1.x - pos2.x, 2) + Math.pow(pos1.y - pos2.y, 2)));//calculating weight between the edges
                 
                 int centerX = ((pos1.x + pos2.x) / 2)-10;
                 int centerY = ((pos1.y + pos2.y) / 2)+10;
                 g2d.setColor(Color.BLUE);
                 g2d.drawString(String.valueOf(weight), centerX, centerY);// writing the weight near the middle of the edge in blue
                }
            }
        }
        
        
        // Highlight shortest path (if available and showShortestPath is true)
        if (showShortestPath && shortestPath.length > 1) 
        {
            g2d.setColor(Color.RED);//setting the colour to red
            g2d.setStroke(new BasicStroke(3));//setting the width of the basic stroke
            for (int i = 0; i < shortestPath.length - 1; i++) 
            {
                int u = shortestPath[i];
                int v = shortestPath[i + 1];
                g2d.drawLine(vertexPositions[u].x, vertexPositions[u].y, vertexPositions[v].x, vertexPositions[v].y);//drawing the red line
            }
        }

        // Drawing the vertices
        for (int i = 0; i < vertexPositions.length; i++) 
        {
            Point vertexPos = vertexPositions[i];
            drawVertex(g2d, vertexPos, i);
        }
    }

    private void drawVertex(Graphics2D g2d, Point vertexPos, int vertexIndex) 
    {
        // Drawing white circle with black border
        g2d.setColor(Color.WHITE);
        g2d.fillOval(vertexPos.x - 10, vertexPos.y - 10, 25, 25);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(vertexPos.x - 10, vertexPos.y - 10, 25, 25);
        // Writing vertex number in black
        g2d.drawString(String.valueOf(vertexIndex), vertexPos.x-3, vertexPos.y+5);
    }
}
