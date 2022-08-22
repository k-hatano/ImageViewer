# Makefile
ImageViewer: ImageViewer.java
	javac -encoding UTF-8 -source 1.8 -target 1.8 ImageViewer.java
	jar cvfm ImageViewer.jar mani.mf *.class
clean:
	rm *.class
	rm *.jar