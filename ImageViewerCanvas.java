import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.awt.datatransfer.*;

import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageViewerCanvas extends Canvas implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	ImageViewer parent;
	Image iFileImage = null;

	public ImageViewerCanvas(ImageViewer ImageViewer) {
		parent = ImageViewer;

		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
	}

	public void update(Graphics g){
		paint(g);
	}

	public void paint(final Graphics g){
		int w = this.getWidth();
		int h = this.getHeight();

		Image img = createImage(w, h);
		Graphics2D grp = (Graphics2D)(img.getGraphics());

		grp.setColor(new Color(255, 255, 255));
		grp.fillRect(0, 0, w, h);

		if (iFileImage != null) {
			grp.drawImage(iFileImage, 0, 0, this);
		}

		g.drawImage(img, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent arg0){
	}

	@Override
	public void keyReleased(KeyEvent arg0){

	}

	@Override
	public void keyTyped(KeyEvent arg0){
		
	}

}
