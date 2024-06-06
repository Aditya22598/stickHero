package GameLayers;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static Main.GameApplication.*;

public class GamePlay
                extends parentLayerClass
{
        private static int score = 0;
        private static AnchorPane instance = null;
        private static final Random random = new Random();
        private static final Group pillars = new Group();

        private GamePlay() {}

        private static Rectangle drawPillar()
        {
                Rectangle pillar = new Rectangle();
                pillar.heightProperty().bind(getScene().heightProperty().divide(4));
                pillar.widthProperty().bind(getScene().widthProperty().divide(9 + random.nextDouble(-4.5, 4.5)));
                pillar.setLayoutY(3 * getScene().heightProperty().getValue()/4);
                return pillar;
        }



        private static void pillar__init__()
        {
                for (int i = 0; i < 5; i++)
                {
                        Rectangle pillar = drawPillar();
                        pillar.setLayoutX(i * getScene().widthProperty().getValue()/3 + random.nextDouble(-2.5, 2.5));
                        pillars.getChildren().add(pillar);
                }
        }



        private static void characterFalls()
        {
                score--;
                ImageView character = (ImageView) instance.getChildren().get(1);
                TranslateTransition transition = new TranslateTransition(Duration.seconds(1), character);
                transition.setByY(getScene().heightProperty().getValue());
                transition.play();
                transition.setOnFinished(event -> {
                        getRoot().getChildren().clear();
                        getRoot().getChildren().add(GameOver.getInstance());
                        clearInstance();
                });
        }

        private static void character__init__() {
                ImageView character = new ImageView(Path.getUrl(Path.getCharacter()));
                character.setLayoutX(0);
                character.setLayoutY(3 * getScene().heightProperty().getValue()/4 - character.getImage().getHeight());
                instance.getChildren().add(character);

        }


        public static boolean legalStickLength(AtomicReference<Integer> firstPillarIndex)
        {
                Rectangle firstPillar = (Rectangle) pillars.getChildren().get(0);
                Rectangle secondPillar = (Rectangle) pillars.getChildren().get(1);
                double distance = secondPillar.getLayoutX() - firstPillar.getLayoutX() - firstPillar.getWidth();
                return !(Stick.getHeights() < distance) && !(Stick.getHeights() > distance + secondPillar.getWidth());
        }


        private static void stickMechanics() {
                AtomicReference<Integer> firstPillarIndex = new AtomicReference<>(0);
                AtomicReference<Rectangle> firstPillar = new AtomicReference<>((Rectangle) pillars.getChildren().get(0));
                AtomicReference<Boolean> flag = new AtomicReference<>(false);

                lambdaBoolean translatePillars = getLambdaTranslatePillars(firstPillarIndex, firstPillar);


                getScene().setOnKeyPressed(e -> {
                        if (e.getCode() == KeyCode.SPACE) {
                                if (flag.get())
                                {
                                        Stick.resetStick();
                                        flag.set(false);
                                }
                                if (Stick.isNull()) {
                                        Stick.getInstance( firstPillar.get().getWidth(), firstPillar.get().getLayoutY());
                                }
                                Stick.incrementStick();
                        }
                });

                getScene().setOnKeyReleased(e -> {
                        if (e.getCode() == KeyCode.SPACE) {
                                score++;
                                if (Stick.isNull()) {
                                        Stick.getInstance(firstPillar.get().getWidth(), firstPillar.get().getLayoutY());
                                }
                                Stick.rotateStick();
                                Stick.translateStick();
                                flag.set(translatePillars.run());
                                if (!legalStickLength(firstPillarIndex))
                                        characterFalls();
                        }
                });

        }

        private static lambdaBoolean getLambdaTranslatePillars(AtomicReference<Integer> firstPillarIndex, AtomicReference<Rectangle> firstPillar) {
                lambdaBoolean generatePlatform = () -> {
                        Rectangle platform = drawPillar();
                        platform.setLayoutX(4 * getScene().widthProperty().getValue() / 3);
                        pillars.getChildren().add(platform);
                        return true;
                };

                return () -> {
                        AtomicReference<Rectangle> previousPillar = new AtomicReference<>(null);
                        for (Node node : pillars.getChildren()) {
                                TranslateTransition transition = getTransition((Rectangle) node, getScene().getWidth(), previousPillar);
                                transition.play();
                        }
                        firstPillarIndex.set(firstPillarIndex.get() + 1);
                        firstPillar.set((Rectangle) pillars.getChildren().get(firstPillarIndex.get()));
                        generatePlatform.run();
                        if (pillars.getChildren().size() > 6) {
                                pillars.getChildren().remove(0);
                                firstPillarIndex.set(firstPillarIndex.get() - 1);
                                firstPillar.set((Rectangle) pillars.getChildren().get(firstPillarIndex.get()));
                        }
                        return true;
                };
        }

        private synchronized static void updateScore()
        {
                // TODO
        }



        private static TranslateTransition getTransition(Rectangle node, double sceneWidth, AtomicReference<Rectangle> previousPillar) {
                TranslateTransition transition = new TranslateTransition(Duration.seconds(1), node);
                transition.setByX(-sceneWidth / 3);
                transition.setOnFinished(event -> {
                        if (previousPillar.get() != null && previousPillar.get().getLayoutX() < 0) {
                                transition.stop();
                        }
                        previousPillar.set(node);
                });
                return transition;
        }

        public static AnchorPane getInstance() {
                if (instance == null)
                {
                        instance = Instance(instance);
                        character__init__();
                        pillar__init__();
                        instance.getChildren().addAll(pillars);
                        stickMechanics();
                }
                return instance;
        }

        public static void clearInstance() {
                instance = null;
        }

}
