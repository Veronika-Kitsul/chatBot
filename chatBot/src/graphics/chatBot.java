package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class chatBot {

	//setting the size of the window
	private final int WIDTH = 700, height = 700, TEXTHEIGHT = 475;
	
	//input/output text fields
	private JTextArea displayarea, typearea;
	
	//keeping track if we have some input
	private boolean entered = false;
	
	//answers for bot
	private final String[] answers = 
	{
			"Hello HUMAN!", "How are you?", "No way! What happened then?", "You must be kidding", 
			"That's so sad", "Sorry, cannot understand that. Could you repeat, please?", "Our mom is going to buy salad, do you want some?",
			"Nooo, that's not for me. How do you feel about it?", "You're beating a dead horse", "Better eat my hat"
	};
	
	public chatBot() {
		JPanel panel = new JPanel();
		
		//giving a certain layout to the panel
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		
		//giving a border and a title
		panel.setBorder(BorderFactory.createTitledBorder("Messenger"));
		
		//creating a display area 
		displayarea = new JTextArea();
		displayarea.setEditable(false);
		
		//creating a type area
		typearea = new JTextArea();
		typearea.setEditable(true);
		
		
		//listening to when enter is pressed 
		typearea.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n')
				{
					sendReceive();
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}
			
		});
		
		
		//put the text fields into scroll panes so that we can see everything  
			JScrollPane scroll = new JScrollPane (displayarea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll2 = new JScrollPane (typearea);
		scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//preferred size of the scrolling panes
		scroll.setPreferredSize(new Dimension(WIDTH, TEXTHEIGHT));
		scroll2.setPreferredSize(new Dimension(WIDTH, height-TEXTHEIGHT));
		panel.add(scroll);
		panel.add(scroll2);
		
		//set colors for the frames, so it looks better
		panel.setBackground(new Color(185,238,187));
		displayarea.setBackground(new Color(222,255,249));
		typearea.setBackground(new Color(203,238,231));
		
		//create and add a listener to the button
		JButton sendButton = new JButton ("Send");
		sendButton.addActionListener(new ActionListener() 
		{
			//what we want to happen when clicked
			public void actionPerformed(ActionEvent e)
			{
				sendReceive();
			}
		});
		
		//create a container for the button and add it to the panel
		JPanel innerPanel = new JPanel();
		innerPanel.add(sendButton);
		panel.add(innerPanel);
	
		//the main container, usual setup
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		panel.setFocusable(true);
		
		//beginning text display
		displayarea.setText("\n Welcome to AI ChatBot. Say anything to start the conversation");

		run();
	   }
	
		
		public void sendReceive()
		{
			//if there is text, add it to the display
			if (!typearea.getText().trim().equals(""))
			{
				displayarea.setText(displayarea.getText() + "\n\n Me:  " + typearea.getText().trim());
				entered = true;
			}
			//clear the input
			typearea.setText("");
		}
		
		
		//run method
		public void run()
		{
			while (true)
			{
				//give output
				if (entered)
				{
					try {Thread.sleep(500);}
					catch (InterruptedException e) {}
					int rand = (int)(Math.random()*answers.length);
					displayarea.setText(displayarea.getText()+"\n\n ChatBot:  " + answers[rand]);
					entered = false;
				}
				try {Thread.sleep(50);}
				catch (InterruptedException e) {}
			}
		}
		
		public static void main(String[] args)
		{
			new chatBot();
		}	
	
}
