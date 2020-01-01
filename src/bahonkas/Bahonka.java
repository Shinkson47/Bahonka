package bahonkas;

import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

public class Bahonka {

	public static void main(String[] arguments) {
		Speak("So new ani-me plot:");
		Speak("This new girl comes to school except she has huge boobs");
		Speak("real big bahonkers");

		int i = 0;
		String word = "";
        while (i < 5) {     
        	word = BahonkaGenerator(i);   
        	Speak(GetIntermediate() + " " + word);
        	i++;
        }
        Speak("What happens next?");
        Speak("Introduce the new foreign exchange student with even bigger " + BahonkaGenerator(10));
        Speak("Ginormous " + BahonkaGenerator(30));
        
        try {
			Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dMBwLyyqcBk"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	private static String GetIntermediate() {
		return Intermediates[rnd.nextInt(Intermediates.length - 1)];
	}
	
	private static String Intermediates[] = {
			"I'm talking some full on",
			"Propper",
			"Wacking",
			"Hella",
			"full sized",
			"no joke they're massive",
			"we're talking huge"
	};

	private static void Speak(String string) {

        VoiceProvider tts = new VoiceProvider("8ae6dd76352846b78488e72cfd7d8fc8");
		
        VoiceParameters params = new VoiceParameters(string, Languages.English_GreatBritain);
        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(5);
		
        try {
			byte[] voice = tts.speech(params);
			
			javax.sound.sampled.AudioFormat format = new javax.sound.sampled.AudioFormat(javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 16, 2, 1, 44100.0f, false);
			//Sorry future self, this fucking import mess is disgusting. Eclipse won't import javax for some stupid reason.
			
			Clip clip = AudioSystem.getClip();
			clip.open(format, voice, 0, voice.length);
			clip.start();
			
		  	System.out.println(string);
			
			while(clip.getMicrosecondLength() > clip.getMicrosecondPosition())
			{
				//wait
			}
			
			clip.flush();
			clip.close();
			clip = null;
			
			FileOutputStream fos;
			fos = new FileOutputStream(string + ".mp3");
            fos.write(voice, 0, voice.length);
            fos.flush();
            fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private static String Syllabbles[] = {
			"honk",
			"bad",
			"onk",
			"er",
			"tat",
			"do",
			"bon",
			"bonk",
			"hoonk",
			"ab",
			"hank",
			"ton",
			"hon",
			"ger",
			"e",
			"koog",
			"hoog",
			"hun",
			"golom",
			"ghoog",
			"onolon",
			"ono",
			"lon",
			"loug",
			"hon",
			"zon",
			"ga",
			"go",
			"loog"
			
			
			
	};
		
	private static String Suffixes[] = {
			"ers",
			"roos",
			"ros",
			"loos",
			"gous",
			"gus"
	};
	
	private static Random rnd = new Random();
	private static String BahonkaGenerator(int b) {
		String buffer = "";
		
		for (int i = 0; i <= b; i++) {
			buffer += Syllabbles[rnd.nextInt(Syllabbles.length - 1)];
		}
		
		buffer += Suffixes[rnd.nextInt(Suffixes.length - 1)];
		
		return buffer;
	}
}
