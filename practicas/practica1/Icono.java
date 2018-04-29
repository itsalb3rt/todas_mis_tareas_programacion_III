import java.awt.*;
import java.applet.*;
import javax.swing.*;
//<applet width= "400" height="400"  code="Icono"></applet>
public class Icono extends JApplet{
	ImageIcon miIcono;
	public void init(){
		miIcono = new ImageIcon("foto.gif");
	}
		public void paint(Graphics g){
			miIcono.paintIcon(this,g,150,150);
		}
	}
