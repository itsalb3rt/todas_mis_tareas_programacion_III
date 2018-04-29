import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.awt.font.*;
import java.awt.geom.*;

//<applet width= "600" height="600"  code="Icono1"></applet>

public class Icono1 extends JApplet{
	ImageIcon miicono;
	public void init(){
		miicono = new ImageIcon("foto.jpg");
	}
	public void paint(Graphics g){
		miicono.paintIcon(this,g,0,0);

		Graphics2D g2 = (Graphics2D)g;
		int w = getSize().width;
		int h = getSize().height;
		//se prepara el texto
		FontRenderContext frc = g2.getFontRenderContext();
		Font f = new Font("Arial",Font.BOLD,w/12);
		String texto = new String("Inmortalidad");
		TextLayout tl = new TextLayout(texto,f,frc);
		float sw = (float)tl.getBounds().getWidth();
		AffineTransform transform = new AffineTransform();
		transform.setToTranslation((w/2)-(sw/2),h/2);
		g2.setColor(Color.white);
		//se Deibuja el texto completo centralizado
		tl.draw(g2,(w/3)-(sw/2),h/2);
	}
}