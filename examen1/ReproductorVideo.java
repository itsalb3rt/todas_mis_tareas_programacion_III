import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.*; //Para tratar la alinacion de elementos

public class ReproductorVideo extends Application
{
	public static void main(String[] args) 
	{
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) 
	{
		/*
		Buscando el archivo a repdoducir, recomendable que este en la misma 
		carpera del video o en un subdirectorio de esta carpeta por ejemplo /videos
		*/
		URL mediaUrl = getClass().getResource("viajo_sin_ver.mp4"); //Nombre del video
		String mediaStringUrl = mediaUrl.toExternalForm();
		
		// creando el medio
		Media media = new Media(mediaStringUrl);
		
		// Creando el reproductor
		final MediaPlayer player = new MediaPlayer(media);

		// Esto decide si se reproduce automaticamente al abrir el video, booleano
		player.setAutoPlay(false);
		
		// Creando el tamano de la pantalla donde se reproduce el video
		MediaView mediaView = new MediaView(player);
		mediaView.setFitWidth(1024);
		mediaView.setFitHeight(600);		
		mediaView.setSmooth(true);
		
		// Creando un efecto de sombra
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetY(5.0);
		dropshadow.setOffsetX(5.0);
		dropshadow.setColor(Color.WHITE);

		mediaView.setEffect(dropshadow);		
		
		// Creando botones
		Button playButton = new Button("Reproducir");
		Button stopButton = new Button("Detener");
		
		// Creando el evento donde se lee si se pulsa un boton
		playButton.setOnAction(new EventHandler <ActionEvent>() 
		{
            public void handle(ActionEvent event) 
            {
            	/*
					Si se pulsa el boton reproducir y el video ya esta corriendo lo que hace
					es volver al inicio la reproduccion del video, si se pulsa el boton detener volvera
					al inicio del video tambien.
            	 */
            	if (player.getStatus() == Status.PLAYING) 
            	{
            		player.stop();
            		player.play();
            	} 
            	else 
            	{
            		player.play();
            	}
            }
        });		

		stopButton.setOnAction(new EventHandler <ActionEvent>() 
		{
            public void handle(ActionEvent event) 
            {
            	player.stop();
            }
        });		
		
		// Creando un HBOX
		HBox controlBox = new HBox(5, playButton, stopButton);
		//Estableciendo el tamano de los botones
		playButton.setPrefWidth(100); //Ancho
		playButton.setPrefHeight(50); //alto
		stopButton.setPrefWidth(100);
		stopButton.setPrefHeight(50);
		//Centrando los botones
		controlBox.setAlignment(Pos.CENTER);

		HBox.setMargin(playButton,new Insets(10));
		
		// creando la caja del video
		VBox root = new VBox(5,mediaView,controlBox);
		//Alineando el video
		root.setAlignment(Pos.CENTER);
		
		// Estas son las propiedades de la caja que encierra la pantalla del video
		root.setStyle("-fx-padding: 20;" + //Expancion del contenedor
				"-fx-border-style: solid inside;" + //Tipo de bordes
				"-fx-border-width: 20;" + //Grosor del borde general
				"-fx-border-insets: 5;" + //Grosor del borde de dentro
				"-fx-border-radius: 20;" + //Bordes redondos, mientras mas alto el numero mas redondos
				"-fx-border-color: black;"); //Color de la linea del contenedor del video

		// Creando la scena
		Scene scene = new Scene(root);
		// Agregando la scena al panel principal
		stage.setScene(scene);
		// titulo de la ventana
		stage.setTitle("Examen1");
		// Mostrando todo lo creado
		stage.show();		
	}
}
