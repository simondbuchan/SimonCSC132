package Draw;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 * @author Simon Buchan
 * @author River Kelly
**/
public class MousePanel extends JPanel implements MouseListener, MouseMotionListener{
	
	
	public static MousePanel inst; 
	private int startX, startY, endX, endY, shape;
	private int stroke = 10;
	private Color theColor = Color.BLUE;
	private static boolean button = false;
	BufferedImage grid;
	Graphics2D gc;
	Color bg = Color.WHITE;
	Color fill, outline;
	
	public MousePanel(){		
		 addMouseListener(this);   
	     addMouseMotionListener(this);
	     fill = Color.BLACK;
	     outline = Color.BLACK;
	     this.setVisible(true);
	     this.setBackground(bg);
	}

	public static MousePanel getInstance(){
	       if(inst == null)
	           inst = new MousePanel();
	       return inst;
	}
	
	public void paintComponent(Graphics g){ 
         super.paintComponent(g);  
         Graphics2D g2 = (Graphics2D)g;
         if(grid == null){
            int w = this.getWidth();
            int h = this.getHeight();
            grid = (BufferedImage)(this.createImage(w,h));
            gc = grid.createGraphics();
         }
         g2.drawImage(grid, null, 0, 0);
    }

    public void setShape(int s){
        shape = s;
        button = true;
    }
    
    public void drawShape(Graphics2D pen){
    	if (true == button){
    		pen.setColor(theColor);
	        gc.setStroke(new BasicStroke(stroke));
	        switch(shape){
	            case 1:
	                rectangle(pen);
	                break;
	            case 2:
	                emptyRectangle(pen);
	                break;
	            case 3:
	                oval(pen);
	                break;
	            case 4:
	                emptyOval(pen);
	                break;
	            case 5:
	                line(pen);
	                break;
	            case 6:
	            	//clear();
	            	break;
	            default:
	                     break;
	        } 
	        repaint();
    	}
    }

    public void emptyOval(Graphics2D pen){
        gc.setColor(theColor);
         if(startX <= endX){
             if(startY <= endY)
                 gc.drawOval(startX, startY, (endX - startX), (endY -startY));
             else
                 gc.drawOval(startX, endY, (endX-startX), (startY-endY));
         }
         else{
             if(startY <= endY)
                 gc.drawOval(endX, startY, (startX - endX), (endY - startY));
             else
                 gc.drawOval(endX, endY, (startX - endX), (startY- endY));
         }
        

    }
    
    public void oval(Graphics2D pen){   
         if(startX <= endX){
             if(startY <= endY)
             {
                 gc.fillOval(startX, startY, (endX - startX), (endY -startY));
                 gc.drawOval(startX, startY, (endX - startX), (endY -startY));
                 
             }
             else{
                 gc.fillOval(startX, endY, (endX-startX), (startY-endY));
                 gc.drawOval(startX, endY, (endX-startX), (startY-endY));
             }
         }
         else{
             if(startY <= endY)
             {
                 gc.fillOval(endX, startY, (startX - endX), (endY - startY));
                 gc.drawOval(endX, startY, (startX - endX), (endY - startY));
             }
             else{
                 gc.fillOval(endX, endY, (startX - endX), (startY- endY));
                 gc.drawOval(endX, endY, (startX - endX), (startY- endY));
             }
         }
        
    }
    
    public void rectangle(Graphics2D pen){  	
        if(startX <= endX){
             if(startY <= endY){
                 gc.fillRect(startX, startY, (endX - startX), (endY -startY));
                 gc.drawRect(startX, startY, (endX - startX), (endY -startY));
             }
             else{
                 gc.fillRect(startX, endY, (endX-startX), (startY-endY));
                 gc.drawRect(startX, endY, (endX-startX), (startY-endY));
             }
             
         }
         else{
             if(startY <= endY){
                 gc.fillRect(endX, startY, (startX - endX), (endY - startY));
                 gc.drawRect(endX, startY, (startX - endX), (endY - startY));
             }
             else{
                 gc.fillRect(endX, endY, (startX - endX), (startY- endY));
                 gc.drawRect(endX, endY, (startX - endX), (startY- endY));
             }
         }       
    }
    
    public void emptyRectangle(Graphics2D pen){
         if(startX <= endX){
             if(startY <= endY)
                 pen.drawRect(startX, startY, (endX - startX), (endY -startY));
             else
                 pen.drawRect(startX, endY, (endX-startX), (startY-endY));
         }
         else{
             if(startY <= endY)
                 pen.drawRect(endX, startY, (startX - endX), (endY - startY));
             else
                 gc.drawRect(endX, endY, (startX - endX), (startY- endY));
         }   
    }
    
    public void line(Graphics2D pen){
          gc.drawLine(startX, startY, endX, endY);
    }
    
    public void clear(){
        gc.setPaint(bg);
        gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());
        repaint();
    }
    
    public void setStroke(int s){
    	stroke = s;       
    }
    
    public void setColor(Color c){
    	theColor = c;       
    }

    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    
    public void mousePressed(MouseEvent e){
        startX = e.getX();
        startY = e.getY();
        if(6 == shape){
        	System.out.println("hi");
        }
    }
    
    public void mouseDragged(MouseEvent e) {
 	   if(shape == 6){
 		   int endX = e.getX();
	 	   int endY = e.getY();
	 	   gc.setColor(theColor);
	 	   gc.setStroke(new BasicStroke(stroke));
	 	   gc.drawLine(startX, startY, endX, endY);
	 	   startX = endX;
	 	   startY = endY;
	 	   repaint();
 	   }
    }

    public void mouseReleased(MouseEvent e){
        endX = e.getX();
        endY = e.getY();
        drawShape(gc);
        
    }

   public void mouseMoved(MouseEvent e){}

}
