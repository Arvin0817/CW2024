package com.example.demo.utils;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

/**
 * Utility class for handling background music and sound effects in the game.
 * <p>
 * This class provides functionality to play and loop audio files using the Java Sound API.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Supports looping background music.</li>
 *     <li>Allows playback of single audio files.</li>
 *     <li>Controls audio volume.</li>
 * </ul>
 * </p>
 *
 * @author [XIA SI ZHE]
 */
public class MusicPlayer extends Thread {
    // Path to the audio file
    private String filename;
    // Clip object for controlling audio playback
    public Clip clip;

    /**
     * Constructs a MusicPlayer object for the specified audio file.
     *
     * @param filename The path to the audio file to play
     */
    public MusicPlayer(String filename) {
        this.filename = filename;
    }

    /**
     * Plays the specified audio file in a continuous loop.
     * <p>
     * This method runs in a separate thread to avoid blocking the main game thread.
     * </p>
     */
    public void loop() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    File musicPath = new File(filename);

                    if(musicPath.exists()) {
                        // Load the audio file
                        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                        clip = AudioSystem.getClip();
                        clip.open(audioInput);
                        // Adjust the audio volume
                        FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(6);
                        // Start playback and loop continuously

                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();// Log the exception
                }
            }
        }.start();
    }

    /**
     * Static method to loop an audio file.
     * <p>
     * Creates a MusicPlayer instance and starts looping the specified audio file.
     * </p>
     *
     * @param filename The path to the audio file to loop
     */
    public static void loop(String filename) {
        MusicPlayer musicPlayer = new MusicPlayer(filename);
        musicPlayer.loop();
    }

    public static void play(String filename) {
        try {
            File musicPath = new File(filename);

            if(musicPath.exists()) {
                // Load the audio file
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                // Adjust the audio volume
                FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(6);
                // Start playback
                clip.start();
            }
        } catch(Exception ex) {
            ex.printStackTrace();// Log the exception
        }
    }
}
