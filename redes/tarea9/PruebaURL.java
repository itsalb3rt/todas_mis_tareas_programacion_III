import java.net.*;
public class PruebaURL{
	URL url;

	public PruebaURL(){
		try{
			url = new URL("http://x.mty.itesm.mx/java#referencia");
			despliega(url);
			url = new URL("http","x.mty.itesm.mx",80,"/java");
			despliega(url);
			url = new URL("http","x.mty.itesm.mx","/java");
			despliega(url);
			URL urlBase = new URL("http://x.mty.itesm.mx/");
			url = new URL(urlBase,"/pdiaz/java/index.html");
			despliega(url);

		}catch(MalformedURLException e){
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args){
		new PruebaURL();
	}
	void despliega(URL url){
		System.out.println(url.getProtocol() + " ");
		System.out.println(url.getHost()+" ");
		System.out.println(url.getPort()+" ");
		System.out.println(url.getFile() + " ");
		System.out.println(url.getRef());
		System.out.println(url.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
}