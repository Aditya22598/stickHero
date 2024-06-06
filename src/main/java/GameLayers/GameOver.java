package GameLayers;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static Main.GameApplication.getRoot;
import static Main.GameApplication.getScene;

public class GameOver
        extends parentLayerClass
{
        private static AnchorPane instance = null;
        private GameOver() {}
        public static AnchorPane getInstance() {
                if (instance == null){
                        instance = Instance(instance);
                        Button restartButton = button__init__("Restart");
                        restartButton.setOnAction(event -> {
                                getScene().setRoot(GamePlay.getInstance());
                        });
                        Button settingButton = button__init__("Setting");
                        settingButton.setOnAction(event -> {
                                getRoot().getChildren().clear();
                                getRoot().getChildren().add(GameSetting.getInstance());
                        });
                        Button exitButton = button__init__("Main Menu");
                        VBox vBox = vBox__init__();
                        vBox.getChildren().addAll(restartButton, settingButton, exitButton);
                        instance.getChildren().add(vBox);
                }
                return instance;
        }

        public static void clearInstance() {
                instance = null;
}
}
