package toolkit;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**rappresenta una clip in grado di eseguire tutto cio' che una normale
 * clip e' in grado di fare. Inoltre, quando la clip esegue la musica
 * tramite il comando {@link #start()}, una volta che la musica e' finita
 * effettua una rewind automatico
 * 
 * @author Koldar
 * @version 1.0
 *
 */
public class AutoRewindClip implements Runnable,Clip{
	
	/**la clip che viene utilizzata*/
	private Clip clip;
	/**il thread usato per rilevare quando e' necessario riavvolgere la clip*/
	private Thread internalThread;
	

	/**costruisce una nuova clip musicale
	 * 
	 * @param usingclass la classe che richiede di utilizzare la clip
	 * @param audiofile il nome del file audio
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public AutoRewindClip(Class<?> usingclass,String audiofile) throws LineUnavailableException, IOException, UnsupportedAudioFileException{
		this.clip=AudioSystem.getClip();
		this.clip.open(AudioSystem.getAudioInputStream(usingclass.getResource(audiofile)));
	}
	
	@Override
	public void run() {
		while(this.clip.isRunning());
		this.setFramePosition(0);
	}


	@Override
	public int available() {
		return this.clip.available();
	}


	@Override
	public void drain() {
		this.clip.drain();
	}


	@Override
	public void flush() {
		this.clip.flush();
	}


	@Override
	public int getBufferSize() {
		return clip.getBufferSize();
	}


	@Override
	public AudioFormat getFormat() {
		return this.clip.getFormat();
	}


	@Override
	public int getFramePosition() {
		return this.clip.getFramePosition();
	}


	@Override
	public float getLevel() {
		return this.clip.getLevel();
	}


	@Override
	public long getLongFramePosition() {
		return this.clip.getLongFramePosition();
	}


	@Override
	public long getMicrosecondPosition() {
		return this.clip.getMicrosecondPosition();
	}


	@Override
	public boolean isActive() {
		return this.clip.isActive();
	}


	@Override
	public boolean isRunning() {
		return this.clip.isRunning();
	}


	@Override
	public void start() {
		this.clip.start();
		this.internalThread=new Thread(this);
		this.internalThread.start();
	}


	@Override
	public void stop() {
		this.clip.stop();
	}


	@Override
	public void addLineListener(LineListener arg0) {
		this.clip.addLineListener(arg0);
	}


	@Override
	public void close() {
		this.clip.close();
	}


	@Override
	public Control getControl(Type arg0) {
		return this.clip.getControl(arg0);
	}


	@Override
	public Control[] getControls() {
		return this.clip.getControls();
	}


	@Override
	public javax.sound.sampled.Line.Info getLineInfo() {
		return this.clip.getLineInfo();
	}


	@Override
	public boolean isControlSupported(Type arg0) {
		return this.clip.isControlSupported(arg0);
	}


	@Override
	public boolean isOpen() {
		return this.clip.isOpen();
	}


	@Override
	public void open() throws LineUnavailableException {
		this.clip.open();
	}


	@Override
	public void removeLineListener(LineListener arg0) {
		this.clip.removeLineListener(arg0);
	}


	@Override
	public int getFrameLength() {
		return this.clip.getFrameLength();
	}


	@Override
	public long getMicrosecondLength() {
		return this.clip.getMicrosecondLength();
	}


	@Override
	public void loop(int count) {
		this.clip.loop(count);
	}


	@Override
	public void open(AudioInputStream stream) throws LineUnavailableException,
			IOException {
		this.clip.open(stream);
	}


	@Override
	public void open(AudioFormat format, byte[] data, int offset, int bufferSize)
			throws LineUnavailableException {
		this.clip.open(format, data, offset, bufferSize);
	}


	@Override
	public void setFramePosition(int frames) {
		this.clip.setFramePosition(frames);
	}


	@Override
	public void setLoopPoints(int start, int end) {
		this.clip.setLoopPoints(start, end);
	}


	@Override
	public void setMicrosecondPosition(long microseconds) {
		this.clip.setMicrosecondPosition(microseconds);
	}
	
	

}
