package Main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;
import java.util.List;

public class MediaControl {
        private final MediaView mediaView;
        private final List<String> musicFiles;
        private int currentFileIndex;

        public MediaControl(List<String> musicFiles) {
                this.musicFiles = musicFiles;
                this.currentFileIndex = 0;
                MediaPlayer mediaPlayer = createMediaPlayer(musicFiles.get(currentFileIndex));
                mediaView = new MediaView(mediaPlayer);
        }

        private MediaPlayer createMediaPlayer(String musicFile) {
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.setOnEndOfMedia(() -> {
                        currentFileIndex = (currentFileIndex + 1) % musicFiles.size();
                        MediaPlayer nextPlayer = createMediaPlayer(musicFiles.get(currentFileIndex));
                        mediaView.setMediaPlayer(nextPlayer);
                        nextPlayer.play();
                });
                return mediaPlayer;
        }

        public MediaView getMediaView() {
                return mediaView;
        }
}
