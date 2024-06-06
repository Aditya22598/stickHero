package GameLayers;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static Main.GameApplication.getScene;

abstract public class parentLayerClass {
        protected static ImageView backGroundImage = new ImageView(Path.getUrl(Path.getBackground(0)));


        // In parentLayerClass.java
        protected static Button button__init__(String option) {
                Button button = new Button(option);
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
                button.setFont(Font.font(option, FontWeight.BOLD, 20));
                button.prefHeightProperty().bind(Bindings.divide(getScene().heightProperty(), 20));
                button.prefWidthProperty().bind(getScene().widthProperty());
                button.setMaxWidth(Double.MAX_VALUE);
                button.addEventHandler(MouseEvent.MOUSE_ENTERED, e ->  button.setStyle("-fx-background-color: rgba(245,59,33,0.5); -fx-text-fill: white;"));
                button.addEventHandler(MouseEvent.MOUSE_EXITED, e -> button.setStyle("-fx-background-color: transparent; -fx-text-fill: white;"));
                return button;
        }

        protected static void layer__init__(AnchorPane pane) {
                backGroundImage.setPreserveRatio(false);
                backGroundImage.fitWidthProperty().bind(getScene().widthProperty());
                backGroundImage.fitHeightProperty().bind(getScene().heightProperty());
                pane.getChildren().add(backGroundImage);
        }

        protected static AnchorPane Instance(AnchorPane instance) {
                instance = new AnchorPane();
                instance.prefHeightProperty().bind(getScene().heightProperty());
                instance.prefWidthProperty().bind(getScene().widthProperty());
                layer__init__(instance);
                return instance;
        }


        static VBox vBox__init__() {
                VBox vbox = new VBox(50);
                double padding_height = (getScene().getHeight() - vbox.getHeight())/2;
                vbox.setPadding(new Insets(padding_height, 0,0,0));
                return vbox;
        }

        static void setBackGroundImage(int i)
        {
                backGroundImage = new ImageView(Path.getUrl(Path.getBackground(i)));
        }
}
