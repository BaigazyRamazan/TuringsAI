import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Stack;

public class MyBotPlayer implements BotPlayer{
    private ImageView ball ;
    private ImageView ballR ;
    private ImageView ballL ;
    private Map map;
    private Position position ;
    private Food food;

    public MyBotPlayer(Map map) {
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
    public void feed(Food f) {
        food = f;
    }

    @Override
    public void eat() {
        Thread thread = new Thread(()->{
            while(!position.equals(food.getPosition())) {
                while (true) {
                    if(position.getX() > food.getPosition().getX()) moveLeft();
                    else if(position.getX() < food.getPosition().getX()) moveRight();
                    else if(position.getY() < food.getPosition().getY()) moveDown();
                    else if(position.getY() > food.getPosition().getY()) moveUp();

                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void find() {
        Thread thread = new Thread(()->{
            GFG gfg = new GFG(map.getSize(), map.getSize());
            Stack<Position> positions = new Stack<>();
            boolean nul = false;
                while(!position.equals(food.getPosition())) {
                    while (true) {
                        try {
                            if(positions.isEmpty() ){
                                positions = gfg.getRoad(map, position, food.getPosition());
                                if(!positions.isEmpty()){
                                    nul = false;
                                }
                            }
                            if (position.getX() > positions.peek().getX()) moveLeft();
                            else if (position.getX() < positions.peek().getX()) moveRight();
                            else if (position.getY() < positions.peek().getY()) moveDown();
                            else if (position.getY() > positions.peek().getY()) moveUp();

                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            positions.pop();
                        }catch (NullPointerException ex){
                            if(!nul){
                            System.out.println("He could eat food in this position!");
                            nul = true;
                            }
                        }
                    }
                }
        });

        thread.start();

    }

    @Override
    public void moveRight() {
        if(position.getX()+1<map.getSize() && map.getValue(position.getY(), position.getX()+1) != 1) {
            Platform.runLater(() ->{
                ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
                ball = ballR;
                ((GridPane) map.getChildren().get(0)).add(ball, position.getX() + 1, position.getY());
                position.setX(position.getX() + 1);
            });

        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public void moveLeft() {
        if(position.getX()>0 && map.getValue(position.getY(), position.getX()-1) != 1) {
            Platform.runLater(() ->{
                ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
                ball = ballL;
                ((GridPane) map.getChildren().get(0)).add(ball, position.getX() - 1, position.getY());
                position.setX(position.getX() - 1);

            });

        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public void moveUp() {
        if(position.getY()> 0 && map.getValue(position.getY()-1, position.getX()) != 1) {
            Platform.runLater(() ->{
                ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
                ((GridPane) map.getChildren().get(0)).add(ball, position.getX(), position.getY()-1);
                position.setY(position.getY()-1);

            });

        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public void moveDown() {
        if(position.getY()+1<map.getSize() && map.getValue(position.getY()+1, position.getX()) != 1) {
            Platform.runLater(() ->{
                ((GridPane) map.getChildren().get(0)).getChildren().remove(ball);
                ((GridPane) map.getChildren().get(0)).add(ball, position.getX(), position.getY()+1);
                position.setY(position.getY()+1);


            });

        }else{
            System.out.println("Invalid position!");
        }
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
