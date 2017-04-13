package GUI.complaint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class JPanelWithBachground extends JFrame
{
JButton b1;
JLabel l1;
	public JPanelWithBachground()
	{
	setTitle("Background Color for JFrame");
	setSize(400,400);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
/*
	One way
	-----------------
	setLayout(new BorderLayout());
	JLabel background=new JLabel(new ImageIcon("C:\\Users\\Computer\\Downloads\\colorful design.png"));
	add(background);
	background.setLayout(new FlowLayout());
	l1=new JLabel("Here is a button");
	b1=new JButton("I am a button");
	background.add(l1);
	background.add(b1);
*/
// Another way
	setLayout(new BorderLayout());
	setContentPane(new JLabel(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg")));
	setLayout(new FlowLayout());
	l1=new JLabel("Here is a button");
	b1=new JButton("I am a button");
	add(l1);
	add(b1);
	// Just for refresh :) Not optional!
	setSize(399,399);
	setSize(400,400);
	}
	
}
