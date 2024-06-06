package GameLayers;

import javafx.scene.layout.AnchorPane;

public class GameSetting
        extends parentLayerClass
{
        private static AnchorPane instance = null;
        private GameSetting() {}
        public static AnchorPane getInstance() {
                if (instance == null){
                        instance = Instance(instance);
                }
                return instance;
        }
        public static void clearInstance() {
                instance = null;
        }
}
