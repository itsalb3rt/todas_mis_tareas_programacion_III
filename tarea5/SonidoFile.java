import java.applet.*;
//<applet width= "600" height="600"  code="SonidoFile"></applet>
public class SonidoFile extends Applet{
	public void init(){
		play(getDocumentBase(),"po.wav");
	}
}