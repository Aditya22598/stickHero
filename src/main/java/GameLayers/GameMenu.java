package GameLayers;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import static Main.GameApplication.getRoot;

public class GameMenu
        extends parentLayerClass
{
        private static AnchorPane instance = null;
        private GameMenu() {}

        private static final lambdaButton startButtonMapping = (button) -> {
                button.onActionProperty().set(e -> {
                        getRoot().getChildren().clear();
                        getRoot().getChildren().add(GamePlay.getInstance());
                });
        };

        private static final lambdaButton settingButtonMapping = (button) -> {
                button.onActionProperty().set(e -> {
                        getRoot().getChildren().clear();
                        getRoot().getChildren().add(GameSetting.getInstance());
                });
        };
        private static final lambdaButton exitButtonMapping = (button) -> {
                button.onActionProperty().set(e -> {
                        System.exit(0);
                });
        };

        public static AnchorPane getInstance() {
                if (instance == null){
                        instance = Instance(instance);
                        Button startButton = button__init__("Play");
                        startButtonMapping.run(startButton);
                        Button settingButton = button__init__("Setting");
                        settingButtonMapping.run(settingButton);
                        Button exitButton = button__init__("Exit");
                        exitButtonMapping.run(exitButton);
                        VBox vBox = vBox__init__();
                        vBox.getChildren().addAll(startButton, settingButton, exitButton);
                        instance.getChildren().add(vBox);
                }
                return instance;
        }

}
