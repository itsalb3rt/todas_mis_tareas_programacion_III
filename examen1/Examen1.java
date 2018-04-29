import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;

//<applet width= "800" height="600"  code="Examen1"></applet>

public class Examen1 extends Applet implements ActionListener{
	Panel pgg,pg1,p3,p1,p2,pImagen;
	Label l1,l5;
	TextArea t;
	Button b0,b1,b2,b3,b4;

	public Examen1(){
		l1 = new Label("Sonidos de Botones");

		b0 = new Button("Icono");
		b1 = new Button("Imagen");
		b2 = new Button("Sonido");
		b3 = new Button("Video");
		b4 = new Button("Limpiar");

		l5 = new Label("Resultado de presionar los botones");
		t = new TextArea(5,5);
		setLayout(new GridLayout(1,1,0,0));
		pgg = new Panel(new GridLayout(3,1,0,0)); //Panel General
		pg1 = new Panel(new GridLayout(1,1,0,0)); //Pamel general 2
		p3 = new Panel(new GridLayout(2,1,5,5)); //Panel del area de texto
		pImagen = new Panel(new GridLayout(1,1,5,5)); //Panel de la imagen

		p1 = new Panel(new GridLayout(1,1,5,5)); //Panel de titulo
		p2 = new Panel(new GridLayout(2,2,5,5)); //Panel de botones de sonidos

		p1.add(l1);

		b0.setBackground(Color.BLUE);
		p2.add(b0);
		b1.setBackground(Color.RED);
		p2.add(b1);
		b2.setBackground(Color.BLUE);
		p2.add(b2);
		b3.setBackground(Color.BLUE);
		p2.add(b3);
		b4.setBackground(Color.BLUE);
		p2.add(b4);

		p3.add(l5);

		t.setBackground(Color.GREEN);
		p3.add(t);

		p1.setFont(new Font("Arial",Font.BOLD,35));
		pg1.add(p1);
		pgg.add(pg1);
		p2.setFont(new Font("Arial",Font.BOLD,15));

		pgg.add(p2);
		p3.setFont(new Font("Arial",Font.BOLD,15));
		
		pgg.add(p3);
		pgg.add(pImagen);
		add(pgg);

		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==b0){
			t.append("Icono " + "\n");
			ver_icono();
		}
		if(ae.getSource()==b1){
			t.append("Imagen:" + "\n");
			ver_imagen();
		}
		if(ae.getSource()==b2){
			t.append("Reproduciendo sonido: "+"\n");
			play(getDocumentBase(),"LASER.wav");			
		}
		if(ae.getSource()==b3){
			t.append("Video"+ "\n");
			play(getDocumentBase(),"RADAR.wav");
		}
		if(ae.getSource()==b4){
			pImagen.removeAll();
			t.setText("");
		}
	}
	
	public void ver_imagen(){
		pImagen.removeAll(); //Limpiar el panel antes de mostrar algo
		ImageIcon image = new ImageIcon("mi_imagen.jpg");
		JLabel label_imagen = new JLabel("", image, JLabel.CENTER);
		JPanel panel_imagen = new JPanel(new BorderLayout());
		panel_imagen.add( label_imagen, BorderLayout.CENTER );
		pImagen.add(panel_imagen);
	}
	public void ver_icono(){
		pImagen.removeAll();//Limpiar el panel antes de mostrar algo
		ImageIcon icono = new ImageIcon("icono.gif");
		JLabel label_icono = new JLabel("", icono, JLabel.CENTER);
		JPanel panel_icono = new JPanel(new BorderLayout());
		panel_icono.add( label_icono, BorderLayout.CENTER );
		pImagen.add(panel_icono);
	}

}

