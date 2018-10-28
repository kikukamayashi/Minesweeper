package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Root;

public class Main extends Application {

    private static final int TILE_SIZE = 40;
    private static final int W = 800;
    private static final int H = 600;


    private static final int X_NUM = W / TILE_SIZE;
    private static final int Y_NUM = H / TILE_SIZE;
    private static final int bombCount = X_NUM*Y_NUM *15/100;

    private Tile[][] grid = new Tile[X_NUM][Y_NUM];
    private Scene scene;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(W, H);

        for (int y = 0; y < Y_NUM; y++) {
            for (int x = 0; x < X_NUM; x++) {

                Tile tile = new Tile(x, y);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }
        Random random = new Random();
        for (int i = 0; i < bombCount; i++) {

            grid[random.nextInt(X_NUM)][random.nextInt(Y_NUM)].setBomb(true);

        }
        for (int y = 0; y < Y_NUM; y++) {
            for (int x = 0; x < X_NUM; x++) {
                Tile tile = grid[x][y];

                if (tile.hasBomb)
                    continue;

                long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                if (bombs > 0)
                    tile.text.setText(String.valueOf(bombs));
            }
        }

        return root;
    }

    private List<Tile> getNeighbors(Tile tile) {
        List<Tile> neighbors = new ArrayList<>();

        int[] points = new int[] {
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };

        for (int i = 0; i < points.length; i++) {
            int dx = points[i];
            int dy = points[++i];

            int newX = tile.x + dx;
            int newY = tile.y + dy;

            if (newX >= 0 && newX < X_NUM
                    && newY >= 0 && newY < Y_NUM) {
                neighbors.add(grid[newX][newY]);
            }
        }

        return neighbors;
    }

    private class Tile extends StackPane {
        private int x, y;
        private boolean hasBomb;
        private boolean isOpen = false;

        private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
        private Text text = new Text();

        public Tile(int x, int y) {
            this.x = x;
            this.y = y;
            this.hasBomb = false;

            border.setStroke(Color.LIGHTGRAY);

            text.setFont(Font.font(18));
            text.setText(hasBomb ? "X" : "");
            text.setVisible(false);

            getChildren().addAll(border, text);

            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

            setOnMouseClicked(e -> open());
        }

        public void setBomb(boolean bomb){
            this.hasBomb = bomb;
        }

        public void open() {
            if (isOpen)
                return;

            if (hasBomb) {
                System.out.println("Game Over");
                Pane root =  new Pane();
                scene.setRoot(root);
                Text text = new Text();
                text.setText("Game over");
                text.setX(50);
                text.setY(50);

                return;

            }

            isOpen = true;
            text.setVisible(true);
            border.setFill(null);

            if (text.getText().isEmpty()) {
                getNeighbors(this).forEach(Tile::open);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}