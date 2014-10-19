import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class TestDisplay {
	
	// --------------- Public fields ----------------
	public static final boolean VSYNC 				= false;
	public static final boolean FULLSCREEN 	= false;
	
	public static final int 		DISPLAY_WIDTH 	= 800;
	public static final int 		DISPLAY_HEIGHT 	= 600;
	public static final int		FPS_CAP				= 120;
	
	// -------------- Protected fields --------------
	protected boolean running = false;

	public void Start() {
		try {
			Display.setDisplayMode(
					new DisplayMode(DISPLAY_WIDTH, DISPLAY_HEIGHT)
					);
			
			Display.setTitle("The Almighty Engine v0.1");
			Display.setFullscreen(FULLSCREEN);
			Display.setVSyncEnabled(VSYNC);
			Display.setResizable(true);
			
			Display.create();
		} catch (LWJGLException e) {
			System.out.println("Failed to create display.");
			return;
		}
		
		// Initialize opengl resources
		create();
		
		resize();
		
		// "Main loop"
		while(!Display.isCloseRequested()) {
			if(Display.wasResized()) {
				resize();
			}
			
			render();
			
			
			Display.update();
			Display.sync(FPS_CAP);
		}
		
		Display.destroy();
	}
	
	public static void main(String[] args) {
		TestDisplay test = new TestDisplay();
		test.Start();
	}
	
    protected void create() {
        // Enable blending
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Clear screen becomes black
        glClearColor(0f, 0f, 0f, 1.0f);
    }
	
	protected void render() {
        // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT);
        /*for(GameObject go : sceneTree) {
        	go.render();
        }*/
    }
	
	protected void resize() {
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}

}
