package graphs3;

import javax.swing.*;
import java.awt.*;

public class Main 
{
    static JTextField initialNodeField;
    static JTextField finalNodeField;
    private static GraphPanel graphPanel;
    private static Dijkstra dijkstra;

    public static void main(String[] args) 
    {
        String[] vertexData = new String[30]; // declaring array for 30 vertices
        for (int i = 0; i < 30; i++) 
        {
            vertexData[i] = String.valueOf(i); // Labeling nodes as numbers 0 to 29
        }
        int[][] adjMatrix = new int[30][30]; // Adjacency matrix for 30 vertices
        
        // Adding edges for predefined graph
        addEdges(adjMatrix, 0, 2, 200);
        addEdges(adjMatrix, 0, 1, 112);
        addEdges(adjMatrix, 0, 4, 100);
        addEdges(adjMatrix, 1, 0, 112);
        addEdges(adjMatrix, 1, 2, 112);
        addEdges(adjMatrix, 1, 5, 150);
        addEdges(adjMatrix, 1, 4, 112);
        addEdges(adjMatrix, 2, 6, 110);
        addEdges(adjMatrix, 2, 0, 200);
        addEdges(adjMatrix, 2, 1, 112);
        addEdges(adjMatrix, 2, 5, 224);
        addEdges(adjMatrix, 2, 19, 78);
        addEdges(adjMatrix, 3, 19, 72);
        addEdges(adjMatrix, 3, 14, 85);
        addEdges(adjMatrix, 3, 18, 107);
        addEdges(adjMatrix, 3, 26, 127);
        addEdges(adjMatrix, 4, 0, 100);
        addEdges(adjMatrix, 4, 22, 71);
        addEdges(adjMatrix, 4, 7, 56);
        addEdges(adjMatrix, 4, 1, 112);
        addEdges(adjMatrix, 5, 1, 150);
        addEdges(adjMatrix, 5, 2, 224);
        addEdges(adjMatrix, 5, 8, 90);
        addEdges(adjMatrix, 6, 21, 100);
        addEdges(adjMatrix, 6, 20, 175);
        addEdges(adjMatrix, 6, 2, 110);
        addEdges(adjMatrix, 7, 4, 56);
        addEdges(adjMatrix, 7, 23, 79);
        addEdges(adjMatrix, 7, 8, 100);
        addEdges(adjMatrix, 8, 7, 100);
        addEdges(adjMatrix, 8, 23, 106);
        addEdges(adjMatrix, 8, 24, 79);
        addEdges(adjMatrix, 8, 5, 90);
        addEdges(adjMatrix, 9, 25, 87);
        addEdges(adjMatrix, 9, 11, 148);
        addEdges(adjMatrix, 9, 10, 73);
        addEdges(adjMatrix, 9, 29, 96);
        addEdges(adjMatrix, 10, 9, 73);
        addEdges(adjMatrix, 10, 15, 50);
        addEdges(adjMatrix, 10, 29, 56);
        addEdges(adjMatrix, 11, 13, 48);
        addEdges(adjMatrix, 11, 9, 148);
        addEdges(adjMatrix, 12, 19, 129);
        addEdges(adjMatrix, 12, 13, 65);
        addEdges(adjMatrix, 13, 12, 65);
        addEdges(adjMatrix, 13, 11, 48);
        addEdges(adjMatrix, 13, 14, 54);
        addEdges(adjMatrix, 13, 15, 130);
        addEdges(adjMatrix, 13, 16, 144);
        addEdges(adjMatrix, 14, 3, 85);
        addEdges(adjMatrix, 14, 13, 54);
        addEdges(adjMatrix, 14, 17, 103);
        addEdges(adjMatrix, 15, 13, 130);
        addEdges(adjMatrix, 15, 16, 130);
        addEdges(adjMatrix, 15, 10, 50);
        addEdges(adjMatrix, 16, 13, 144);
        addEdges(adjMatrix, 16, 27, 81);
        addEdges(adjMatrix, 16, 29, 198);
        addEdges(adjMatrix, 16, 15, 130);
        addEdges(adjMatrix, 17, 14, 103);
        addEdges(adjMatrix, 17, 27, 97);
        addEdges(adjMatrix, 17, 18, 81);
        addEdges(adjMatrix, 18, 3, 107);
        addEdges(adjMatrix, 18, 26, 221);
        addEdges(adjMatrix, 18, 17, 81);
        addEdges(adjMatrix, 19, 2, 78);
        addEdges(adjMatrix, 19, 3, 72);
        addEdges(adjMatrix, 19, 12, 129);
        addEdges(adjMatrix, 20, 6, 175);
        addEdges(adjMatrix, 20, 26, 54);
        addEdges(adjMatrix, 21, 6, 100);
        addEdges(adjMatrix, 21, 22, 112);
        addEdges(adjMatrix, 22, 21, 112);
        addEdges(adjMatrix, 22, 23, 125);
        addEdges(adjMatrix, 22, 4, 71);
        addEdges(adjMatrix, 23, 22, 125);
        addEdges(adjMatrix, 23, 7, 79);
        addEdges(adjMatrix, 23, 8, 106);
        addEdges(adjMatrix, 24, 8, 79);
        addEdges(adjMatrix, 24, 28, 125);
        addEdges(adjMatrix, 25, 9, 87);
        addEdges(adjMatrix, 25, 28, 72);
        addEdges(adjMatrix, 26, 20, 54);
        addEdges(adjMatrix, 26, 3, 127);
        addEdges(adjMatrix, 26, 18, 221);
        addEdges(adjMatrix, 27, 17, 97);
        addEdges(adjMatrix, 27, 16, 81);
        addEdges(adjMatrix, 28, 24, 125);
        addEdges(adjMatrix, 28, 25, 72);
        addEdges(adjMatrix, 29, 9, 96);
        addEdges(adjMatrix, 29, 10, 56);
        addEdges(adjMatrix, 29, 16, 198);
        
        dijkstra = new Dijkstra(vertexData.length);
        
        for (int i = 0; i < adjMatrix.length; i++) 
        {
            for (int j = 0; j < adjMatrix[0].length; j++) 
            {
                if (adjMatrix[i][j] != 0) 
                {
                    dijkstra.addEdge(i, j, adjMatrix[i][j]);
                }
            }
        }
        for (int i = 0; i < vertexData.length; i++) 
        {
            dijkstra.addVertexData(i, vertexData[i]);
        }

        // Defining vertex positions on the graph display
        Point[] vertexPositions = new Point[30];
        vertexPositions[0] = new Point(100, 100);
        vertexPositions[1] = new Point(200, 150);
        vertexPositions[2] = new Point(300, 100);
        vertexPositions[3] = new Point(400, 200);
        vertexPositions[4] = new Point(100, 200);
        vertexPositions[5] = new Point(200, 300);
        vertexPositions[6] = new Point(200, 55);
        vertexPositions[7] = new Point(125, 250);
        vertexPositions[8] = new Point(125, 350);
        vertexPositions[9] = new Point(150, 400);
        vertexPositions[10] = new Point(220, 420);
        vertexPositions[11] = new Point(275, 320);
        vertexPositions[12] = new Point(295, 240);
        vertexPositions[13] = new Point(320, 300);
        vertexPositions[14] = new Point(370, 280);
        vertexPositions[15] = new Point(270, 420);
        vertexPositions[16] = new Point(400, 420);
        vertexPositions[17] = new Point(420, 370);
        vertexPositions[18] = new Point(450, 295);
        vertexPositions[19] = new Point(370, 135);
        vertexPositions[20] = new Point(375, 55);
        vertexPositions[21] = new Point(100, 50);
        vertexPositions[22] = new Point(50, 150);
        vertexPositions[23] = new Point(50, 275);
        vertexPositions[24] = new Point(50, 325);
        vertexPositions[25] = new Point(65, 380);
        vertexPositions[26] = new Point(425, 75);
        vertexPositions[27] = new Point(475, 450);
        vertexPositions[28] = new Point(50, 450);
        vertexPositions[29] = new Point(210, 475);

        // Create JFrame to display graph
        JFrame frame = new JFrame("Graph Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        graphPanel = new GraphPanel(vertexPositions, adjMatrix);

        // Set the layout for the frame
        frame.setLayout(new BorderLayout());

        // Panel for input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        // Text fields for node names
        initialNodeField = new JTextField(5);
        finalNodeField = new JTextField(5);
        inputPanel.add(new JLabel("Initial Node: "));
        inputPanel.add(initialNodeField);
        inputPanel.add(new JLabel("Final Node: "));
        inputPanel.add(finalNodeField);

        // Button to compute shortest path
        JButton computeButton = new JButton("Find Shortest Path");
        computeButton.addActionListener(e -> computeShortestPath());
        inputPanel.add(computeButton);

        // Button to reset graph
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGraph());
        inputPanel.add(resetButton);

        // Adding components to the frame and the position
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(graphPanel, BorderLayout.CENTER);

        // Pack and display the frame
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on screen
        frame.setVisible(true);
    }

    private static void addEdges(int[][] adjMatrix, int u, int v, int weight) 
    {
        adjMatrix[u][v] = weight;
        adjMatrix[v][u] = weight; 
    }

    private static void computeShortestPath() 
    {
        String initialNode = initialNodeField.getText().trim();
        String finalNode = finalNodeField.getText().trim();

        //condition if any of the text fields are left empty
        if (initialNode.isEmpty() || finalNode.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "Please enter both initial and final nodes.");
            return;
        }

        Result result;
        try 
        {
            result = dijkstra.dijkstra(initialNode);
        } 
        catch (IllegalArgumentException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }

        int[] distances = result.distances;
        int[] predecessors = result.predecessors;

        int startVertex = -1;
        int endVertex = -1;
        for (int i = 0; i < dijkstra.size; i++) 
        {
            if (dijkstra.vertexData[i].equals(initialNode)) 
            {
                startVertex = i;
            }
            if (dijkstra.vertexData[i].equals(finalNode)) 
            {
                endVertex = i;
            }
        }

        //if start or end nodes are not in the graph
        if (startVertex == -1) 
        {
            JOptionPane.showMessageDialog(null, "Initial node '" + initialNode + "' not found");
        } 
        else if (endVertex == -1) 
        {
            JOptionPane.showMessageDialog(null, "Final node '" + finalNode + "' not found");
        } 
        else 
        {
            LinkedList path = dijkstra.getPath(predecessors, startVertex, endVertex);

            // Highlighting shortest path on the graph
            int[] shortestPathArray = new int[path.size()];
            Node node = path.getHead();
            int index = 0;
            while (node != null) 
            {
                shortestPathArray[index++] = node.data;
                node = node.next;
            }
            graphPanel.setShortestPath(shortestPathArray);
            
        }
    }

    private static void resetGraph() 
    {
        // Resetting the display
        graphPanel.resetGraph();
    }
}
