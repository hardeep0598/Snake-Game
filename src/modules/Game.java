package modules;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener,ActionListener{
    
	private int[] snakeXlength=new int[750];
	private int[] snakeYlength=new int[750];
	
private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
		450,475,500,525,575,600,625,650,675,700,725,750,775,800,825,850};

private int[] enemyypos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,
		450,475,500,525,575,600,625};

    private Random r=new Random();
    private int xpos=r.nextInt(34);
    private int ypos=r.nextInt(23);    
	
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	private ImageIcon title;
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon downmouth;
	private ImageIcon upmouth;
	private ImageIcon snakebody;
	private ImageIcon enemyimage;
	
	private Timer timer;
	private int delay=100;
	private int lengthofsnake=3;
	private int moves=0;
	private int score=0;
	
	public Game(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		 timer=new Timer(delay, this);
		 timer.start();
	}
	
	public void paint(Graphics g) {
		
		if(moves==0) {
			snakeXlength[2]=50;
			snakeXlength[1]=75;
			snakeXlength[0]=100;
			snakeYlength[2]=100;
			snakeYlength[1]=100;
			snakeYlength[0]=100;
		}
		
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		title=new ImageIcon("snaketitle.jpg");
		title.paintIcon(this, g, 25, 11);
		
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length : "+lengthofsnake,780,30);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score : "+score,780,50);
		
		rightmouth=new ImageIcon("rightmouth.png"); 
		rightmouth.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int i=0;i<lengthofsnake;i++) {
			if(i==0 && right) {
				rightmouth=new ImageIcon("rightmouth.png"); 
				rightmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && left) {
				leftmouth=new ImageIcon("leftmouth.png"); 
				leftmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && down) {
				downmouth=new ImageIcon("downmouth.png"); 
				downmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && up) {
				upmouth=new ImageIcon("upmouth.png"); 
				upmouth.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i!=0) {
					snakebody=new ImageIcon("snakeimage.png"); 
					snakebody.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
		}
		
		enemyimage=new ImageIcon("enemy.png");
		
		if((enemyxpos[xpos]==snakeXlength[0]) && (enemyypos[ypos]==snakeYlength[0])) {
			lengthofsnake++;
			score++;
			xpos=r.nextInt(34);
			ypos=r.nextInt(23);
		}
		
		enemyimage.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		for(int i=1;i<lengthofsnake;i++) {
			if(snakeXlength[i]==snakeXlength[0] && snakeYlength[i]==snakeYlength[0]) {
				right=false;
				down=false;
				up=false;
				left=false;
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("GAME OVER", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("Space to Restart", 350, 340);
				
			}
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		
		if(right) {
			
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeYlength[i+1]=snakeYlength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakeXlength[i]+=25;
				}
				else {
					snakeXlength[i]=snakeXlength[i-1];
				}
				if(snakeXlength[i]>850) {
					snakeXlength[i]=25;
				}
			}
			repaint();
		}
		if(left) {
			
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeYlength[i+1]=snakeYlength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakeXlength[i]-=25;
				}
				else {
					snakeXlength[i]=snakeXlength[i-1];
				}
				if(snakeXlength[i]<25) {
					snakeXlength[i]=850;
				}
			}
			repaint();
		}
		if(up) {
			
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeXlength[i+1]=snakeXlength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakeYlength[i]-=25;
				}
				else {
					snakeYlength[i]=snakeYlength[i-1];
				}
				if(snakeYlength[i]<75) {
					snakeYlength[i]=625;
				}
			}
			repaint();
		}
		if(down) {
			
			for(int i=lengthofsnake-1;i>=0;i--) {
				snakeXlength[i+1]=snakeXlength[i];
			}
			for(int i=lengthofsnake;i>=0;i--) {
				if(i==0) {
					snakeYlength[i]+=25;
				}
				else {
					snakeYlength[i]=snakeYlength[i-1];
				}
				if(snakeYlength[i]>625) {
					snakeYlength[i]=75;
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			moves=0;
			score=0;
			lengthofsnake=3;
			repaint();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			moves++;
			right=true;
			if(!left) {
				right=true;
			}
			else {
				right=false;
				left=true;
			}
			 up=false;
			 down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			moves++;
			left=true;
			if(!right) {
				left=true;
			}
			else {
				left=false;
				right=true;
			}
			 up=false;
			 down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			moves++;
			up=true;
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
			 right=false;
			 left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			moves++;
			down=true;
			if(!up) {
				down=true;
			}
			else {
				down=false;
				up=true;
			}
			 right=false;
			 left=false;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
