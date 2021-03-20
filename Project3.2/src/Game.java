import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Game extends Application {
    private Map map;
    private MyBotPlayer player;
    private Food food;
    private boolean started = false;
    private static String str;
    @Override
    public void start(Stage primaryStage) throws Exception{
        map = new Map("map1.txt");
        player = new MyBotPlayer(map);
        food = new Food(map,player);
        player.feed(food);
        System.out.println("Map size: "+map.getSize());
        Scene scene = new Scene(map,500,475);
        scene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.E && !started){
                started = true;
                System.out.println("E (eat) key pressed");
                player.eat();
            }
            if(event.getCode() == KeyCode.F && !started){
                started = true;
                System.out.println("F (find) key pressed");
                System.out.println("press S to stop and close");
                player.find();
            }
            if(event.getCode() == KeyCode.S && started){
                System.exit(-1);
            }

        });
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
