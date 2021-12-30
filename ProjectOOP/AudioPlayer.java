package ProjectOOP;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
public class AudioPlayer {
    //Declare audio files location 
    private String SoundsFolder = "Sounds" + File.separator;
    private String clearLinepath = SoundsFolder + "line.wav";
    private String gameoverPath = SoundsFolder + "gameover.wav";
    //Instantiate variables to get sounds from the audio files
    private Clip clearlineSound, gameoverSound;
    public AudioPlayer(){
            try {
                clearlineSound = AudioSystem.getClip();
                gameoverSound = AudioSystem.getClip();
                clearlineSound.open(AudioSystem.getAudioInputStream(new File(clearLinepath).getAbsoluteFile()));
                gameoverSound.open(AudioSystem.getAudioInputStream(new File(gameoverPath).getAbsoluteFile()));
            }
            //Exceptions handling for audio file types 
            catch (LineUnavailableException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void PlayClearLine(){
        clearlineSound.setFramePosition(0);//Implement to play the audio again when the method is called
        clearlineSound.start();//Play audio when the method is called
    }
    public void PlayGameOver(){
        gameoverSound.setFramePosition(0);//Implement to play the audio again when the method is called
        gameoverSound.start();//Play audio when the method is called
    }
}