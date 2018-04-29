import java.awt.*;
import java.applet.*;
//<applet width= "600" height="600"  code="animacionPelota"></applet>
public class animacionPelota extends Applet implements Runnable{
	Thread anima;
	int radio = 40; //radio de la pelota
	int x,y; // posicion del centro de la pelota
	int dx =1; //desplazamientos
	int dy = 1;
	int anchoApplet;
	int altoApplet;
	int retardo = 5; //velocidad de movimiento, mayor numero es mas lento
	//crea el doble buffer
	Image imag;
	Graphics gBuffer;

	public void init (){
	setBackground(Color.white);
	//Dimensiones del applet
	anchoApplet=getSize().width; 
	altoApplet=getSize().height;
	//posicion inicial de la pelota
	x=anchoApplet/4;
	y=altoApplet/2;

}
public void start(){
	if(anima==null){
		anima= new Thread(this);
		anima.start();
	}
}
public void stop(){
	if(anima!=null){
		anima.stop();
		anima=null;
	}
}
public void run(){
	long t=System.currentTimeMillis();
	while(true){
		mover();
		try{
			//para evitar parpadeo
			t+=retardo;
			Thread.sleep(Math.max(0,t-System.currentTimeMillis()));
		}catch(InterruptedException ex){
			break;
		}
	}
}
void mover(){
	//cambia la posicion x,y de la pelota
	x+=dx;
	y+=dy;
	//cuando la pelota topa en alguno de los extremos
	if(x >= (anchoApplet-radio) || x <=radio) dx*= -1;
	if(y>=(altoApplet-radio) || y <= radio) dy*= -1;
	repaint(); //llama al metodo update
}
public void update(Graphics g){
	if(gBuffer == null){
		imag = createImage(anchoApplet,altoApplet);
		gBuffer=imag.getGraphics();
	}
	gBuffer.setColor(getBackground());
	gBuffer.fillRect(0,0,anchoApplet,altoApplet);
	//dibujo la pelota
	gBuffer.setColor(Color.red);
	gBuffer.fillOval(x-radio,y-radio,2*radio,2*radio);
	//transfiere la imagen al contexto grafico del applet
	g.drawImage(imag,0,0,null);
}
public void paint(Graphics g){
	//se llama la primra vez que aparece el applet
}}
