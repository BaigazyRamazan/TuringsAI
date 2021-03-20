import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;

public class MyPlayer implements Player {
    private ImageView ball ;
    private ImageView ballR ;
    private ImageView ballL ;
    private Map map;
    private Position position ;
    public MyPlayer(Map map) {
        this.map = map;
        position = map.getStartPosition();
        ballR = new ImageView("PR.jpg");
        ballL = new ImageView("PL.jpg");
        ballR.setFitHeight(map.getUnit()-3);
        ballR.setFitWidth(map.getUnit()-3);
        ballL.setFitWidth(map.getUnit()-3);
        ballL.setFitHeight(map.getUnit()-3);
        ball = ballR;
        ((GridPane)map.getChildren().get(0)).add(ball, position.getX(), position.getY());
    }

    @Override
    public void moveRight() {
        if(position.getX()+1<map.getSize() && map.getValue(position.getY(), position.getX()+1) != 1) {
            ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
            ball = ballR;
            ((GridPane) map.getChildren().get(0)).add(ball, position.getX() + 1, position.getY());
            position.setX(position.getX() + 1);
        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public void moveLeft() {
        if(position.getX()>0 && map.getValue(position.getY(), position.getX()-1) != 1) {
            ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
            ball = ballL;
            ((GridPane) map.getChildren().get(0)).add(ball, position.getX() - 1, position.getY());
            position.setX(position.getX() - 1);

        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public void moveUp() {
        if(position.getY()> 0 && map.getValue(position.getY()-1, position.getX()) != 1) {
            ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
            ((GridPane) map.getChildren().get(0)).add(ball, position.getX(), position.getY()-1);
            position.setY(position.getY()-1);

        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public void moveDown() {
        if(position.getY()+1<map.getSize() && map.getValue(position.getY()+1, position.getX()) != 1) {
            ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
            ((GridPane) map.getChildren().get(0)).add(ball, position.getX(), position.getY()+1);
            position.setY(position.getY()+1);

        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }
}
