import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;

import javax.imageio.*;

import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageViewerImport {
	ImageViewer parent;
	File lastFile = null;

	public ImageViewerImport(ImageViewer ImageViewer) {
		parent = ImageViewer;
	}

	public void showImportFileDialog() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG file (.jpg)", "jpg", "jpeg"));
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Multi-Picture Object (.mpo)", "mpo"));
		if (lastFile != null) {
			chooser.setSelectedFile(lastFile);
		}
		int res = chooser.showOpenDialog(this.parent);
		if (res == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			lastFile = file;
			importImage(file.getAbsolutePath());
		}
	}

	public boolean importImage(String path) {
		File file = new File(path);
		this.parent.setTitle(file.getName());

		try {
            Image image = ImageIO.read(new File(path));
			this.parent.imageViewerCanvas.iFileImage = image;
			this.parent.imageViewerCanvas.repaint();
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }

		return true;
	}
}
