import java.awt.*;
import java.applet.*;
//<applet width= "600" height="600"  code="animacion2"></applet>
public class animacion2 extends Applet{
	
	Image dobleBuffer;
	Graphics miCG;

	public void init(){
		//inicializa el doble buffer
		dobleBuffer = createImage(300,300);
		miCG= dobleBuffer.getGraphics();

		//construye un area grafica de trabajo
		miCG.setColor(Color.white);
		miCG.fillRect(0,0,300,300);
		resize(500,450);
	}
	public void paint(Graphics g){
		//Presenta la imagen del buffer
		g.drawImage(dobleBuffer,0,0,this);
	}
	public void titulo(){
		//Obtiene la fuente de texto actual y se guarda
		Font f = miCG.getFont();
		//Seleciona otro color y otra fuente para el titulo
		miCG.setColor(Color.blue);
		miCG.setFont(new Font("TimesRoman",Font.BOLD,20));
		miCG.drawString("Ejemplo de cuadrado",15,50);
		miCG.drawString("Con Circulos",15,70);
		//Recupera la fuente original
		miCG.setFont(f);
	}
	public void cuadrado(){
		int x,y;

		//seleciona otro color para el cuadrado
		miCG.setColor(Color.red);

		//dibuja circulos en los lados horizontales
		y=100;
		for(x = 100;x <=200; x+=10){
			miCG.drawOval(x,y,20,20);
			miCG.drawOval(x,y+100,20,20);
		}
		//dibuja circulos en los lados verticales
		x=100;
		for(y=100;y<=200;y+=10){
			miCG.drawOval(x,y,20,20);
			miCG.drawOval(x+100,y,20,20);
		}
	}
	public void start(){
		//hace el dibujo off-line
		titulo();
		cuadrado();
		//muestra la imagen de golpe
		repaint();
	}
	}