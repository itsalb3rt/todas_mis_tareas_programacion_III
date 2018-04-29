import java.awt.*;
import java.applet.*;
//<applet width= "600" height="600"  code="DespliegaImagen"></applet>
public class DespliegaImagen extends Applet{
	Image miImagen;
	public void ver_imagen(){
	
	miImagen = getImage(getDocumentBase(),"mi_imagen.jpg");
	}
	public void paint(Graphics g){
	g.drawImage(miImagen,0,0,this);
	//Para hacer que una imagen se ajuste a la ventana se le agregan los parametros getWith() y getHeight()
	}

}