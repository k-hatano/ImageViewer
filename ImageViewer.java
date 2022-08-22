import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.*;

import javax.swing.*;

public class ImageViewer extends JFrame implements ActionListener {
	JPanel pHeaderPanel, pCanvasPanel;
	JMenuBar mbMenuBar;
	JMenu mFile;
	JMenuItem miOpen,miQuit;
	ImageViewerImport imageViewerImport;
	ImageViewerCanvas imageViewerCanvas;
	
	ImageViewer() {
		super();

		setSize(800,812);
		setLayout(new BorderLayout());
		setTitle("ImageViewer");

		mbMenuBar = new JMenuBar();

		mFile = new JMenu("File");
		miOpen = new JMenuItem("Open...");
		miOpen.addActionListener(this);
		miOpen.setAccelerator(KeyStroke.getKeyStroke('O',KeyEvent.CTRL_MASK));
		mFile.add(miOpen);
		mFile.addSeparator();
		miQuit = new JMenuItem("Quit");
		miQuit.addActionListener(this);
		miQuit.setAccelerator(KeyStroke.getKeyStroke('Q',KeyEvent.CTRL_MASK));
		mFile.add(miQuit);
		mbMenuBar.add(mFile);

		setJMenuBar(mbMenuBar);

		pCanvasPanel = new JPanel();
		pCanvasPanel.setLayout(new BorderLayout());

		imageViewerCanvas = new ImageViewerCanvas(this);
		pCanvasPanel.add("Center", imageViewerCanvas);

		imageViewerImport = new ImageViewerImport(this);

		add("Center", pCanvasPanel);

		pHeaderPanel = new JPanel();
		pHeaderPanel.setLayout(new BorderLayout());

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				closeWindow();
			}
		});

		new DropTarget(this,new Dropper(this));
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == miQuit) {
			closeWindow();
		} else if (arg0.getSource() == miOpen) {
			imageViewerImport.showImportFileDialog();
		}
	}

	public static void main(String[] argv){
		ImageViewer ImageViewer = new ImageViewer();
		ImageViewer.show();
	}

	public void closeWindow(){
		System.exit(0);
	}

	class Dropper extends DropTargetAdapter{
		ImageViewer parent;

		Dropper(ImageViewer imageViewer){
			super();
			parent = imageViewer;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void drop(DropTargetDropEvent arg0) {
			try {
				Transferable t = arg0.getTransferable();
				if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
					arg0.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

					File file = ((java.util.List<File>)t.getTransferData(DataFlavor.javaFileListFlavor)).get(0);
					parent.imageViewerImport.importImage(file.getAbsolutePath());
				}
			}
			catch (Exception ex){
				ex.printStackTrace(System.err);
			}
		}
	}
}
