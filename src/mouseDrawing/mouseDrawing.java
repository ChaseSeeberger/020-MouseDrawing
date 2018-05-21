package mouseDrawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class mouseDrawing extends JFrame {

	JMenuBar mainMenuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem newMenuItem = new JMenuItem("New");
	JMenuItem exitMenuItem = new JMenuItem("Exit");
	JMenuBar secMenuBar = new JMenuBar();
	JMenu secFileMenu = new JMenu("File");
	JMenuItem penMenuItem = new JMenuItem("New");
	JMenuItem canvasMenuItem = new JMenuItem("Exit");
	JPanel drawPanel = new JPanel();
	JLabel leftColorLabel = new JLabel();
	JLabel rightColorLabel = new JLabel();
	JPanel colorPanel = new JPanel();
	JLabel[] colorLabel = new JLabel[24];
	Graphics2D g2D;

	double xPrevious, yPrevious;
	Color drawColor, leftColor, rightColor;


	
	public static void main(String[] args) {
		new mouseDrawing().setVisible(true);

	}

	public mouseDrawing() {
		//frame Constructor
		setTitle("Artistlistic Creations");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitForm(e);
			}
		});
		
		getContentPane().setLayout(new GridBagLayout());
		
		newMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMenuItemActionPerformed(e);	
			}
		});
			
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMenuItemActionPerformed(e);
			}
			
		}); 
		
		//build menu
		setJMenuBar(mainMenuBar);
		fileMenu.setMnemonic('F');
		mainMenuBar.add(fileMenu);
		fileMenu.add(newMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		//build menu
		//setJMenuBar(secMenuBar);
		//fileMenu.setMnemonic('M');
		//mainMenuBar.add(secFileMenu);
		//secFileMenu.add(penMenuItem);
		//secFileMenu.addSeparator();
		//secFileMenu.add(canvasMenuItem);
		
	
	
	drawPanel.setPreferredSize(new Dimension(1000,800));
	drawPanel.setBackground(Color.BLACK);
	GridBagConstraints gridConstraints = new GridBagConstraints();
	gridConstraints.gridx = 0;
	gridConstraints.gridy = 0;
	gridConstraints.gridheight = 2;
	gridConstraints.insets = new Insets(10,10,10,10);
	getContentPane().add(drawPanel, gridConstraints);
	
	
	drawPanel.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			drawPanelMousePresseed(e);
		}

	});
	
	drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseDragged(MouseEvent e) {
			drawPanelMouseDragged(e);
		}

	});
	drawPanel.addMouseListener(new MouseAdapter() {
		public void mouseDragged(MouseEvent e) {
			drawPanelMouseReleased(e);
		}
	});
	
	
	leftColorLabel.setPreferredSize(new Dimension(40,40));
	leftColorLabel.setOpaque(true);
	gridConstraints = new GridBagConstraints();
	gridConstraints.gridx = 2;
	gridConstraints.gridy = 0;
	gridConstraints.anchor = GridBagConstraints.NORTH;
	gridConstraints.insets = new Insets(10,5,10,10);
	getContentPane().add(leftColorLabel, gridConstraints);
	
	rightColorLabel.setPreferredSize(new Dimension(40,40));
	rightColorLabel.setOpaque(true);
	gridConstraints = new GridBagConstraints();
	gridConstraints.gridx = 3;
	gridConstraints.gridy = 0;
	gridConstraints.anchor = GridBagConstraints.NORTH;
	gridConstraints.insets = new Insets(10,5,10,10);
	getContentPane().add(rightColorLabel, gridConstraints);
	
	colorPanel.setPreferredSize(new Dimension(320,480));
	colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
	gridConstraints = new GridBagConstraints();
	gridConstraints.gridx = 1;
	gridConstraints.gridy = 1;
	gridConstraints.gridwidth = 2;
	gridConstraints.anchor = GridBagConstraints.NORTH;
	gridConstraints.insets = new Insets(10,10,10,10);
	getContentPane().add(colorPanel);
	
	colorPanel.setLayout(new GridBagLayout());
	int j = 0;
	int k = 0;
	for (int i = 0; i<24; i++ ) {
		colorLabel[i] = new JLabel();
		colorLabel[i].setPreferredSize(new Dimension(30, 30));
		colorLabel[i].setOpaque(true);
		gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = j;
		gridConstraints.gridy = i / 3;
		colorPanel.add(colorLabel[i], gridConstraints);
		j++;
			if ((1 + i) % 3 == 0) {
				j = 0;
			}
		
		colorLabel[i].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				colorMousePressed(e);
			}

		});
	}
	
	colorLabel[0].setBackground(new Color(0,0,0));
	colorLabel[1].setBackground(new Color(125,125,125));
	colorLabel[2].setBackground(new Color(255,255,255)); //white
	
	colorLabel[3].setBackground(new Color(0,0,153));
	colorLabel[4].setBackground(new Color(0,0,255)); //blue
	colorLabel[5].setBackground(new Color(51, 153, 255));
	
	colorLabel[6].setBackground(new Color(179, 0, 0));
	colorLabel[7].setBackground(new Color(255, 0, 0)); //red
	colorLabel[8].setBackground(new Color(255, 77, 77));
	
	colorLabel[9].setBackground(new Color(0, 128, 0));
	colorLabel[10].setBackground(new Color(0, 255, 0)); //green
	colorLabel[11].setBackground(new Color(102, 255, 102));
	
	colorLabel[12].setBackground(new Color(179, 71, 0));
	colorLabel[13].setBackground(new Color(255, 102, 0)); //orange
	colorLabel[14].setBackground(new Color(255, 191, 128)); 
	
	colorLabel[15].setBackground(new Color(179, 0, 179));
	colorLabel[16].setBackground(new Color(255, 0, 255)); //magenta
	colorLabel[17].setBackground(new Color(255, 153, 255)); 
	
	colorLabel[18].setBackground(new Color(179, 179, 0));
	colorLabel[19].setBackground(new Color(255, 255, 0)); //yellow
	colorLabel[20].setBackground(new Color(255, 255, 128));
	
	colorLabel[21].setBackground(new Color(0, 153, 153));
	colorLabel[22].setBackground(new Color(0, 255, 255)); //cyan
	colorLabel[23].setBackground(new Color(153, 255, 255));
	
	leftColor = colorLabel[0].getBackground();
	leftColorLabel.setBackground(leftColor);
	rightColor = colorLabel[0].getBackground();
	rightColorLabel.setBackground(rightColor);
	
	pack();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	setBounds((int) (0.5 * (screenSize.width - getWidth())),
			(int) (0.5 * (screenSize.height - getHeight())),
			getWidth(), getHeight());
		g2D = (Graphics2D) drawPanel.getGraphics();
	}
	
	private void colorMousePressed(MouseEvent e) {
		Component clickedColor = e.getComponent();
		Toolkit.getDefaultToolkit().beep();
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftColor = clickedColor.getBackground();
			leftColorLabel.setBackground(leftColor);
		} else if (e.getButton() == MouseEvent.BUTTON3){
			rightColor = clickedColor.getBackground();
			rightColorLabel.setBackground(rightColor);
		}
	}
	
	private void drawPanelMouseDragged(MouseEvent e) {
		Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
		g2D.setPaint(drawColor);
		g2D.draw(myLine);
		g2D.setStroke(new BasicStroke(3));
		xPrevious = e.getX();
		yPrevious = e.getY();
	}
	
	private void drawPanelMousePresseed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3 ) {
			xPrevious = e.getX();
			yPrevious = e.getY();
			if (e.getButton() == MouseEvent.BUTTON1) {
				drawColor = leftColor;
			} else {
				drawColor = rightColor;
			}
		}
	}
	
	private void drawPanelMouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
			Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
			g2D.setPaint(drawColor);
			g2D.draw(myLine);
		}
		
	}
	
	private void exitMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the Blackboard program?", "Exit Program",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			return;
		} else {
			exitForm(null);
		}
	}
	
	private void newMenuItemActionPerformed(ActionEvent e) {
		int response;
		response = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new drawing?", "New Drawing", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			g2D.setPaint(drawPanel.getBackground());
			g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
		}
	}
	
	private void exitForm(WindowEvent e) {
		g2D.dispose();
		System.exit(0);
	}
	

}
