package mx.unam.poo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mx.unam.poo.CavemanGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title = "Caveman Game";
                config.width = 800;
                config.height = 480;
		new LwjglApplication(new CavemanGame(), config);
	}
}
