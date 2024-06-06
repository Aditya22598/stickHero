package GameLayers;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicReference;

import static Main.GameApplication.getScene;

public class Stick {
        private static final AtomicReference<Rectangle> stick = new AtomicReference<>(null);
        private static int indexInstance = 0;


        private Stick() {}

        public synchronized static void getInstance(double locX, double locY) {
                if (stick.get() == null) {
                        stick.set(new Rectangle());
                        stick.get().setLayoutX(locX - getScene().getWidth() / 200);
                        stick.get().setLayoutY(locY);
                        stick.get().setWidth(getScene().getWidth() / 200);
                        GamePlay.getInstance().getChildren().add(stick.get());
                        indexInstance = GamePlay.getInstance().getChildren().indexOf(stick.get());
                }
        }

        public synchronized static boolean isNull() {
                return (stick.get() == null);
        }

        public synchronized static void incrementStick() {
                stick.get().setHeight(stick.get().getHeight() + getScene().getHeight() / 100);
                stick.get().setLayoutY(stick.get().getLayoutY() - getScene().getHeight() / 100);
        }



        public synchronized static double getWidth() {
                return stick.get().getWidth();
        }

        public synchronized static void rotateStick() {
                Rotate rotate = new Rotate();
                rotate.setPivotX(stick.get().getX());
                rotate.setPivotY(stick.get().getY() + stick.get().getHeight());
                rotate.setAngle(90);
                stick.get().getTransforms().add(rotate);
        }

        public synchronized static void resetStick() {
                if (indexInstance >= 0 && indexInstance < GamePlay.getInstance().getChildren().size()) {
                        GamePlay.getInstance().getChildren().remove(indexInstance);
                        stick.set(null);
                }
        }

        public synchronized static double getHeights() {
                return  stick.get().getHeight();
        }



        public synchronized static void translateStick() {
                TranslateTransition stickTranslation = new TranslateTransition(Duration.seconds(1), stick.get());
                stickTranslation.setByX(-getScene().getWidth() / 3);
                stickTranslation.play();
        }

        public static double getLength() {
                return stick.get().getWidth();
        }
}
