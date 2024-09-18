package interfaces.colorDraw;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import javax.swing.*;
 class Panels extends JFrame {
	 int etat; Color col;
	Panels(String A) {
		setTitle(A);
		setSize(500, 400);
setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Temp\\f1.png"));
		Container c = getContentPane();
String[] T = { "red", "green", "yellow", "black", "white", "blue" };
		ppanels a = new ppanels();
		ppanels b = new ppanels();
		JButton b1=new JButton("Droite");
		JButton b2=new JButton("Rectangle");
		JButton b3=new JButton("Ecrire un texte");
		JButton b4=new JButton("Effacer");
		JComboBox cb =new JComboBox (T);
		String txt = (String) cb.getSelectedItem();
		try {
			Field field = Color.class.getField(txt);
			col = (Color) field.get(null);
		} catch (Exception m) {	col = null; }
	  
	cb.addItemListener(new ItemListener() {
	public void itemStateChanged(ItemEvent e) {
		String txt2 = (String) cb.getSelectedItem();
		try {
			Field field = Color.class.getField(txt2);
			col = (Color) field.get(null);
		} catch (Exception m) {	col = null; }
	  }
    });
    b1.addMouseListener(new MouseAdapter() {
	    public void mouseClicked(MouseEvent arg0) {etat=1; }
	});
    b2.addMouseListener(new MouseAdapter() {
	   public void mouseClicked(MouseEvent arg0) {	etat=2; 
	   }
	});
    b3.addMouseListener(new MouseAdapter() {
	   public void mouseClicked(MouseEvent e) {etat=3;
	   String x=JOptionPane.showInputDialog(this, "Enter un texte :");
	   a.msg=x;
	   a.x1=e.getX();
	   a.y1=e.getY();
	   }});
    b4.addMouseListener(new MouseAdapter() {
    	public void mouseClicked(MouseEvent arg0) {
		a.repaint();
		}
	});
    a.addMouseListener(new MouseAdapter() {
    	public void mousePressed(MouseEvent e) {
    		a.x1=e.getX();    a.y1=e.getY();
		}
    	public void mouseReleased(MouseEvent e) {
    		a.x2=e.getX();    a.y2=e.getY();
    		a.dessiner(etat,col);
		}
	});
    b.setLayout(new FlowLayout());
    b.add(cb);
    b.add(b1); 
    b.add(b2);
    b.add(b3);
    b.add(cb);
    b.add(b4);
    c.add(a);
    c.add(b, "South");
    setVisible(true);
	}
}
class ppanels extends JPanel{
	int x1, y1,x2,y2,ry;
	String  msg;
	ppanels(){ }
	 public void paintComponent (Graphics g){
		super.paintComponent(g) ;
		requestFocus() ;
	}
	void dessiner(int etat, Color c){
		int tmp;
		Graphics g = getGraphics() ; g.setColor(c);
			if(etat==1) { g.drawLine(x1, y1,x2, y2) ;}
			if(etat==2){	
				if(x2>x1 && y2>y1) {y2-=y1; x2-=x1;
						g.drawRect(x1, y1,x2, y2); }
				else if(x1>x2&&y1>y2) {tmp=x1; x1=x2; x2=tmp;tmp=y1; y1=y2; y2=tmp;
					x2=x2-x1; y2-=y1;
				g.drawRect(x1, y1,x2, y2); }
			else if(x1>x2 && y1<y2) {
					tmp=x1; x1=x2; x2=tmp; 
					x2=x2-x1; y2-=y1;
					g.drawRect(x1, y1,x2, y2); }
			else if(x2>x1 && y1>y2) {
					tmp=y1; y1=y2; y2=tmp;
					x2=x2-x1; y2-=y1;
					g.drawRect(x1, y1,x2, y2); }
			}
			if(etat==3) {
				g.drawString(msg, x1, y1);
			}
			this.requestFocus() ;
	 }
}
public class ColorDraw {
	public static void main(String[]ar) {
	Panels A = new Panels("Dessiner des objets avec différentes couleurs !!");
	}
}
