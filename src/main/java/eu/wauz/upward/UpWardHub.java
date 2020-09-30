package eu.wauz.upward;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.JComponent;

import eu.wauz.upward.demo.DoomDemo;
import eu.wauz.upward.demo.IsaacDemo;
import eu.wauz.upward.demo.IsaacRoomLayoutDemo;
import eu.wauz.upward.demo.PathDemo;
import eu.wauz.upward.demo.TerrariaCellularAutomataDemo;
import eu.wauz.upward.demo.TerrariaDemo;
import eu.wauz.upwardutils.UpWardUtils;
import eu.wauz.uwt.TextAreaOutputStream;
import eu.wauz.uwt.UButton;
import eu.wauz.uwt.UFlow;
import eu.wauz.uwt.UFrame;
import eu.wauz.uwt.UImage;
import eu.wauz.uwt.ULabel;

/**
 * The main window of the engine.
 * 
 * @author Wauzmons
 */
public class UpWardHub extends UFrame {

	/**
	 * This element's UUID.
	 */
	private static final long serialVersionUID = 2802789905709400569L;
	
	private static final TextAreaOutputStream consoleStream = new TextAreaOutputStream(true);

	/**
	 * Starts the hub application.
	 * 
	 * @param args The java arguments.
	 */
	public static void main(String[] args) {
		UpWardHub hubWindow = new UpWardHub();
		hubWindow.setTitle("UpWard Hub");
		hubWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		hubWindow.setSize(640, 480);
		hubWindow.setResizable(false);
		
		UFlow flow = new UFlow(true);
		hubWindow.createTitleLine(flow);
		hubWindow.createButtonLine(flow);
		hubWindow.add(flow, BorderLayout.NORTH);
		hubWindow.add(consoleStream.getScrollPane(), BorderLayout.CENTER);
		hubWindow.open();
		
		System.out.println("Welcome to the UpWard Engine, " + System.getProperty("user.name") + "!");
		System.out.println("Running on " + System.getProperty("os.name"));
	}
	
	/**
	 * Creates a panel with logo and title.
	 * 
	 * @param parent The parent element of the panel.
	 */
	public void createTitleLine(JComponent parent) {
		UFlow flow = new UFlow(false);
		File imageFile = UpWardUtils.getResource(getClass(), "images/icon-big.png");
		UImage image = new UImage(imageFile, 64, 64);
		flow.add(image);
		flow.add(new ULabel("UpWard Engine").inTitleFormat());
		parent.add(flow);
	}
	
	/**
	 * Creates a panel with demo buttons.
	 * 
	 * @param parent The parent element of the panel.
	 */
	public void createButtonLine(JComponent parent) {
		UFlow flow = new UFlow(false);
		flow.addTitle("Demos");
		
		Color red = Color.RED.darker().darker();
		Color orange = Color.ORANGE.darker().darker();
		Color green = Color.GREEN.darker().darker();
		
		flow.add(new UButton("Doom", new DoomDemo()).withBackground(red));
		flow.add(new UButton("Isaac", new IsaacDemo()).withBackground(orange));
		flow.add(new UButton("Isaac Rooms", new IsaacRoomLayoutDemo()).withBackground(orange));
		flow.add(new UButton("Paths", new PathDemo()).withBackground(orange));
		flow.add(new UButton("Terraria", new TerrariaDemo()).withBackground(green));
		flow.add(new UButton("Terraria Map", new TerrariaCellularAutomataDemo()).withBackground(green));
		parent.add(flow);
	}

}
