package GameLayers;

import java.util.Objects;

public class Path {

        private static final String icon = "/Images/icon.jpg";
        private static final String character = "/Images/angeline.gif";
        private static final String[] background = {
                "/Images/background5.jpg",
                "/Images/background8.jpg",
        };

        private static final String[] audio = {
                "src/main/resources/Audio/Cartoon & Andromedik - Whatever (ft. Jüri Pootsmann) [NCS Release].mp3",
                "src/main/resources/Audio/Cartoon - On & On (Feat. Daniel Levi) (nuumi Remix) [NCS Release].mp3",
        };

        private static final String[] fxml = {
                "/FXML/GameMenu.fxml",
                "/FXML/Setting.fxml",
                "/FXML/Play.fxml",
                "/FXML/GameOver.fxml"
        };


        public static String getUrl(String resourcePath) {
                return Objects.requireNonNull(Path.class.getResource(resourcePath)).toExternalForm();
        }

        public static String getIcon() {
                return icon;
        }


        public static String getCharacter() {
                return character;
        }

        public static String getBackground(int i) {
                return background[i];
        }

        public static String getAudio(int i) {
                return audio[i];
        }

}
