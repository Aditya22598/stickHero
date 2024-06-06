package Main;

import GameLayers.GameMenu;
import GameLayers.Path;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class GameApplication
                extends Application
{
        private static Stage stage;
        private static Scene scene;
        private static AnchorPane root;

        public static Stage getStage() {
                return stage;
        }

        public static Scene getScene() {
                return scene;
        }

        public static AnchorPane getRoot() {
                return root;
        }

        // initialising the stage
        private void stage__init__()
        {
                stage.setTitle("Stick Hero");
                stage.setMaximized(true);
                stage.setResizable(false);
                stage.getIcons().add(new Image(Path.getUrl(Path.getIcon())));
        }

        // initialising the scene
        private void scene__init__()
        {
                root = new AnchorPane();
                scene = new Scene(root);
                root.prefHeightProperty().bind(scene.heightProperty());
                root.prefWidthProperty().bind(scene.widthProperty());
                stage.setScene(scene);
        }

        @Override
        public void start(Stage primeStage)
                        throws IOException
        {
                stage = primeStage;
                stage__init__();
                scene__init__();
                stage.show();
                root.getChildren().add(GameMenu.getInstance());
        }



        public static void main(String[] args)
        {
                List<String> musicFiles = Arrays.asList(
                                Path.getAudio(0),
                                Path.getAudio(1)
                );


                MediaControl mediaControl = new MediaControl(musicFiles);
                //mediaControl.getMediaView().getMediaPlayer().play();
                launch();
        }
}