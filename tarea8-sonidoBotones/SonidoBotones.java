import java.awt.*;
import java.applet.*;
import java.awt.event.*;

//<applet width= "600" height="600"  code="SonidoBotones"></applet>

public class SonidoBotones extends Applet implements ActionListener{
	Panel pgg,pg1,p3,p1,p2;
	Label l1,l5;
	TextArea t;
	Button b0,b1,b2,b3,b4;

	public SonidoBotones(){
		l1 = new Label("Sonidos de Botones");

		b0 = new Button("Aplausos");
		b1 = new Button("Laser");
		b2 = new Button("Ringout");
		b3 = new Button("Radar");
		b4 = new Button("Limpiar Pantalla");

		l5 = new Label("Resultado de presionar los botones");
		t = new TextArea(20,40);
		setLayout(new GridLayout(1,1,0,0));
		pgg = new Panel(new GridLayout(3,1,0,0)); //Panel General
		pg1 = new Panel(new GridLayout(1,1,0,0)); //Pamel general 2
		p3 = new Panel(new GridLayout(2,1,5,5)); //Panel del area de texto

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

		add(pgg);

		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==b0){
			t.append("Resultado: Aplausos " + "\n");
				play(getDocumentBase(),"aplauso.wav");
		}
		if(ae.getSource()==b1){
			t.append("Resultado: Laser:" + "\n");
			play(getDocumentBase(),"LASER.wav");
		}
		if(ae.getSource()==b2){
			t.append("Resultado: Ringout"+"\n");
			play(getDocumentBase(),"ringout.wav");			
		}
		if(ae.getSource()==b3){
			t.append("Resultado: Radar"+ "\n");
			play(getDocumentBase(),"RADAR.wav");
		}
		if(ae.getSource()==b4){
			t.setText("");
		}
	}
}