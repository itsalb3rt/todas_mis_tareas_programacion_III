import java.applet.*;
import java.net.*;
//<applet width= "600" height="600"  code="SonidoURL"></applet>
public class SonidoURL extends Applet{
	public void init(){
		URL url;
		try{
			url = new URL ("la_url");
		}catch(MalformedURLException e){
			return;
		}
		play(url);
	}
}