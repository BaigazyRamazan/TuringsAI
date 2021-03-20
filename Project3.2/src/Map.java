import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map extends Pane {
    private final int UNIT = 45;
    private int size;
    private int[][] map;
    private Position start;

    public Map(String str) throws FileNotFoundException{
        try {
            Scanner scan = new Scanner(new File(str));
            size = scan.nextInt();
            map = new int[size][size];
            for (int i = 0; i <size ; i++) {
                for (int j = 0; j <size ; j++) {
                    map[i][j] = scan.nextInt();
                    if(map[i][j]==2){
                        start = new Position(i,j);
                    }
                }
            }

            GridPane pane = new GridPane();
            for (int i = 0; i <size ; i++) {
                for (int j = 0; j <size ; j++) {
                    if(map[j][i] ==0 || map[j][i] ==2){
                        Rectangle rectangle = new Rectangle(UNIT,UNIT, Color.WHITE);
                        rectangle.setStroke(Color.BLACK);
                        pane.add(rectangle, i,j);
                    }
                    else if(map[j][i] ==1){
                        Rectangle rectangle = new Rectangle(UNIT,UNIT, Color.BLACK);
                        rectangle.setStroke(Color.BLACK);
                        pane.add(rectangle, i,j);
                    }
                }
            }
            getChildren().add(pane);
        }catch (FileNotFoundException e){
            throw e;
        }
    }

    public int getUnit() {
        return UNIT+1;
    }
    public int[][] getMap(){
        return map;
    }

    public int getSize() {
        return size;
    }

    public Position getStartPosition() {
        return start;
    }
    public int getValue(int x, int y){
        return map[x][y];
    }
}
