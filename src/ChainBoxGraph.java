import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

public class ChainBoxGraph extends Stage {

    private Pane graphPane;
    private Integer[][] intMatrix;
    private Float[][] floatMatrix;

    private Group root;
    private Scene scene;

    private int jobNumber;
    private int machineNumber;
    private float totalMakeSpan;

    private int graphWidth;
    private int graphHeight;
    private int boxHeight;

    private int[] intMachineEndTimeOfWork;
    private float[] floatMachineEndTimeOfWork;

    private Random random = new Random();
    private int tmpJobNumber = 0;
    private Color color = Color.BLUE;

    private boolean isIntegerMatrix;

    public ChainBoxGraph(Integer[][] matrix, int width, int height, int makeSpan){
        this.isIntegerMatrix = true;
        this.intMatrix = matrix;
        this.totalMakeSpan = makeSpan;
        initializeView(this.intMatrix.length, this.intMatrix[0].length, width, height);
        this.show();
    }

    public ChainBoxGraph(Float[][] matrix, int width, int height, float makeSpan){
        this.isIntegerMatrix = false;
        this.floatMatrix = matrix;
        this.totalMakeSpan = makeSpan;
        initializeView(this.floatMatrix.length, this.floatMatrix[0].length, width, height);
        this.show();
    }

    public ChainBoxGraph(Integer[][] matrix, int width, int height, int makeSpan, String graphTitle){
        this.isIntegerMatrix = true;
        this.intMatrix = matrix;
        this.totalMakeSpan = makeSpan;
        initializeView(this.intMatrix.length, this.intMatrix[0].length, width, height, graphTitle);
        this.show();
    }

    public ChainBoxGraph(Float[][] matrix, int width, int height, float makeSpan, String graphTitle){
        this.isIntegerMatrix = false;
        this.floatMatrix = matrix;
        this.totalMakeSpan = makeSpan;
        initializeView(this.floatMatrix.length, this.floatMatrix[0].length, width, height, graphTitle);
        this.show();
    }

    private void initializeView(int rowNumber, int colNumber, int width, int height, String graphTitle) {
        initializeView(rowNumber, colNumber, width, height);
        this.setTitle(graphTitle);
    }

        /**
         *  Job Number = col
         *  Machine Number = row
         */
    private void initializeView(int rowNumber, int colNumber, int width, int height){

        this.jobNumber = colNumber;
        this.machineNumber = rowNumber;

        this.graphWidth = (int) (width * 0.98);
        this.graphHeight = (int) (height * 0.98);
        this.boxHeight = graphHeight/machineNumber;
        this.graphPane = new Pane();
        this.graphPane.setLayoutX(width*0.02);
        this.graphPane.setLayoutY(height*0.02);

        this.setTitle("Chain Box Graph");
        this.setHeight(height + 40);
        this.setWidth(width + 80);

        if (isIntegerMatrix) {
            drawGraph(intMatrix);
        }
        else {
            drawGraph(floatMatrix);
        }

        this.root = new Group();    //TODO : Ajouter tous les enfants dans le groupe.
        this.root.getChildren().addAll(graphPane);
        this.scene = new Scene(root, width, height);
        this.setScene(scene);
        this.setResizable(true);
    }

    private void drawGraph(Integer[][] matrix){
        // TODO : Gérer l'ordre de lecture de la matrice
        int tmp = 0;
        this.tmpJobNumber = -1;
        this.intMachineEndTimeOfWork = new int[matrix[0].length];
        for (int a = 1; a < matrix.length; a++){    // TODO : Vérifier la length
            tmp += matrix[a-1][0];
            this.intMachineEndTimeOfWork[a] = tmp;
        }
        for (int job = 0; job < matrix[0].length; job++){
            for (int machine = 0; machine < matrix.length; machine++){
                drawRectangle(job, machine, matrix[machine][job]);
                intMachineEndTimeOfWork[machine] += matrix[machine][job];
                if (machine < matrix[machine].length -1 && intMachineEndTimeOfWork[machine] > intMachineEndTimeOfWork[machine + 1]){
                    intMachineEndTimeOfWork[machine + 1] = intMachineEndTimeOfWork[machine];
                }
            }
        }
    }

    private void drawGraph(Float[][] matrix){
        // TODO : Gérer l'ordre de lecture de la matrice
        int tmp = 0;
        this.tmpJobNumber = -1;
        this.floatMachineEndTimeOfWork = new float[matrix[0].length];
        for (int a = 1; a < matrix.length; a++){    // TODO : Vérifier la length
            tmp += matrix[a-1][0];
            this.floatMachineEndTimeOfWork[a] = tmp;
        }
        for (int job = 0; job < matrix[0].length; job++){
            for (int machine = 0; machine < matrix.length; machine++){
                drawRectangle(job, machine, matrix[machine][job]);
                floatMachineEndTimeOfWork[machine] += matrix[machine][job];
                if (machine < matrix[machine].length -1 && floatMachineEndTimeOfWork[machine] > floatMachineEndTimeOfWork[machine + 1]){
                    floatMachineEndTimeOfWork[machine + 1] = floatMachineEndTimeOfWork[machine];
                }
            }
        }
    }

    private void drawRectangle(int jobNumber, int machineNumber, float lenght){
        float width = (lenght / (float)this.totalMakeSpan) * (float)graphWidth;
        float height = boxHeight;
        float xPos;
        if (isIntegerMatrix) {
            xPos = ((float) this.intMachineEndTimeOfWork[machineNumber] / (float) this.totalMakeSpan) * graphWidth;
        }else {
            xPos = (this.floatMachineEndTimeOfWork[machineNumber] / (float) this.totalMakeSpan) * graphWidth;
        }
        float yPos = graphHeight - (machineNumber+1)*boxHeight;
        if (jobNumber != this.tmpJobNumber) {
            this.color = Color.color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            this.tmpJobNumber = jobNumber;
        }
        drawRectangle((int)xPos,(int)yPos,(int)width,(int)height, color, lenght);
    }

    private void drawRectangle(int xPos, int yPos, int width, int height, Color color, float value){
        Rectangle rectangle = new Rectangle(width, height);
        rectangle.setLayoutX(xPos);
        rectangle.setLayoutY(yPos);
        rectangle.setFill(color);
        rectangle.setStrokeWidth(1);
        rectangle.setStroke(Color.BLACK);
        Label label = new Label();
        if (isIntegerMatrix){
            label.setText(""+(int)value);
        }
        else {
            label.setText(""+value);
        }
        label.setLayoutX(rectangle.getLayoutX() + rectangle.getWidth()/2);
        label.setLayoutY(rectangle.getLayoutY() + rectangle.getHeight()/2);
        this.graphPane.getChildren().addAll(rectangle, label);
    }

    private int getBoxWidth(int span){
        return (int)(span/graphWidth)*100;
    }

    private float getBoxWidth(float span){
        return (float)(span/graphWidth)*100;
    }

}
