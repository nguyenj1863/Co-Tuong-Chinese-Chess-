//Name: John Nguyen
//Date: Nov 26, 2021
//Purpose: Grid Game

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.applet.Applet;
import java.io.*;
import java.util.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CoTuong extends Applet implements ActionListener
{
    //audio
    AudioClip soundFile;
    //date
    String time;
    JLabel display;
    //Declare the widgets
    JComboBox animal;
    String animal_s;
    //All of the conditions
    int condition = 0;
    int dragcondition = 0;
    int startcondition = 0;
    int checkcondition = 0;
    int viable = 0;
    int abc = 0;
    int red = 0;
    int blue = 0;
    //For the annoying methods
    int m = 0;
    int n = 0;
    //For the special dragon methods
    char dragontemp1 = 'z';
    char dragontemp2 = 'z';
    Panel p_card;  //to hold all of the screens
    Panel card1, card2, card3, card4, card5; //the two screens
    CardLayout cdLayout = new CardLayout ();
    //turns
    JLabel colourturn;
    JLabel turnpic;
    char turn = 'r';
    int last = -1;
    //grid
    int row = 10;
    int col = 9;
    JButton a[] = new JButton [row * col];
    //* Visable Arrays
    char piece[] [] = {{'t', 'b', 's', 'c', 'd', 'c', 's', 'b', 't'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},  //piece
	    {'x', 'p', 'x', 'x', 'x', 'x', 'x', 'p', 'x'}, {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'}, {'x', 'p', 'x', 'x', 'x', 'x', 'x', 'p', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'t', 'b', 's', 'c', 'd', 'c', 's', 'b', 't'}};
    char select[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},  //selected or not
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};
    char colour[] [] = {{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},  //colour of piece
	    {'x', 'b', 'x', 'x', 'x', 'x', 'x', 'b', 'x'}, {'b', 'x', 'b', 'x', 'b', 'x', 'b', 'x', 'b'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'}, {'x', 'r', 'x', 'x', 'x', 'x', 'x', 'r', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'}};
    char bg[] [] = {{'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'}, {'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'},  //pieces background
	    {'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'}, {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
	    {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}, {'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
	    {'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'}, {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'},
	    {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'}, {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'}};
    //* Reset Arrays (the og board)
    char ogpiece[] [] = {{'t', 'b', 's', 'c', 'd', 'c', 's', 'b', 't'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},  //piece
	    {'x', 'p', 'x', 'x', 'x', 'x', 'x', 'p', 'x'}, {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'}, {'x', 'p', 'x', 'x', 'x', 'x', 'x', 'p', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'t', 'b', 's', 'c', 'd', 'c', 's', 'b', 't'}};
    char ogselect[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},  //selected or not
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};
    char ogcolour[] [] = {{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},  //colour of piece
	    {'x', 'b', 'x', 'x', 'x', 'x', 'x', 'b', 'x'}, {'b', 'x', 'b', 'x', 'b', 'x', 'b', 'x', 'b'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'}, {'x', 'r', 'x', 'x', 'x', 'x', 'x', 'r', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'}};
    char ogbg[] [] = {{'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'}, {'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'},  //pieces background
	    {'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'}, {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
	    {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}, {'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
	    {'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'}, {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'},
	    {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'}, {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'}};
    //*Check Array (Array that runs in the background to find any checks)
    char ckpiece[] [] = {{'t', 'b', 's', 'c', 'd', 'c', 's', 'b', 't'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},  //piece
	    {'x', 'p', 'x', 'x', 'x', 'x', 'x', 'p', 'x'}, {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'}, {'x', 'p', 'x', 'x', 'x', 'x', 'x', 'p', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'t', 'b', 's', 'c', 'd', 'c', 's', 'b', 't'}};
    char ckselect[] [] = {{'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},  //selected or not
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'},
	    {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}, {'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u', 'u'}};
    char ckcolour[] [] = {{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},  //colour of piece
	    {'x', 'b', 'x', 'x', 'x', 'x', 'x', 'b', 'x'}, {'b', 'x', 'b', 'x', 'b', 'x', 'b', 'x', 'b'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
	    {'r', 'x', 'r', 'x', 'r', 'x', 'r', 'x', 'r'}, {'x', 'r', 'x', 'x', 'x', 'x', 'x', 'r', 'x'},
	    {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'}, {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r'}};
    char ckbg[] [] = {{'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'}, {'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'},  //pieces background
	    {'e', 'e', 'e', 'n', 'n', 'n', 'e', 'e', 'e'}, {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'},
	    {'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e', 'e'}, {'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'},
	    {'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n', 'n'}, {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'},
	    {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'}, {'n', 'n', 'n', 'e', 'e', 'e', 'n', 'n', 'n'}};
    //Screen layouts
    public void init ()
    {
	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	screen1 ();
	screen2 ();
	screen3 ();
	resize (530, 700);
	setLayout (new BorderLayout ());
	initMenu ();
	add ("Center", p_card);
    }


    public void initMenu ()
    { //The menu
	JMenuBar menuBar = new JMenuBar ();
	JMenu menu;
	JMenuItem menuItem;
	//File menubar
	menu = new JMenu ("File");
	menuBar.add (menu);
	//The options
	menuItem = new JMenuItem ("Open"); //opens code
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("open");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Save"); //saves code
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("save");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Close"); //closes program
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("close");
	menu.add (menuItem);
	//Sound menubar
	menu = new JMenu ("Sound");
	menuBar.add (menu);
	menuItem = new JMenuItem ("Play"); //plays sound
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("ps");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Stop"); //stops sound
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("ss");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Loop"); //loops sound
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("ls");
	menu.add (menuItem);
	//Navigation menubar
	menu = new JMenu ("Navigate");
	menuBar.add (menu);
	menuItem = new JMenuItem ("Opening"); //Goes to screen 1
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("s1");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Rules"); //Goes to screen 2
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("s2");
	menu.add (menuItem);
	menuItem = new JMenuItem ("Board"); //Goes to screen 3
	menuItem.addActionListener (this);
	menuItem.setActionCommand ("s3");
	menu.add (menuItem);
	add ("North", menuBar);
    }


    public void screen1 ()
    { //screen 1 is set up.
	card1 = new Panel ();
	card1.setBackground (new Color (213, 208, 189));
	//Title image
	JButton title = new JButton (createImageIcon ("title.jpg"));
	title.setBorder (null);
	//To go to screen 2
	title.setActionCommand ("s2");
	title.addActionListener (this);
	card1.add (title);
	p_card.add ("1", card1);
    }


    public void screen2 ()
    { //screen 2 is set up.
	card2 = new Panel ();
	card2.setBackground (new Color (237, 232, 228));
	//Instruction image
	JButton next = new JButton (createImageIcon ("insbackground.jpg"));
	next.setBorder (null);
	//To go to screen 3
	next.setActionCommand ("s3");
	next.addActionListener (this);
	card2.add (next);
	p_card.add ("2", card2);
    }


    public void screen3 ()
    { //screen 3 is set up.
	card3 = new Panel ();
	card3.setBackground (new Color (73, 92, 86));
	//Title and headings
	JLabel title = new JLabel ("                         DRAGON'S DOM                         ");
	title.setFont (new Font ("Arial", Font.BOLD, 27));
	title.setForeground (Color.white);
	colourturn = new JLabel ("Red's Turn");
	colourturn.setForeground (Color.white);
	//turn
	turnpic = new JLabel (createImageIcon ("red.jpg"));
	turnpic.setPreferredSize (new Dimension (43, 43));
	//Date
	dater ();
	display = new JLabel (time);
	display.setForeground (Color.white);
	display.setFont (new Font ("Arial", Font.BOLD, 15));
	//Create the options for the box
	animal_s = "Turing"; //the default option
	//the other options
	String[] animalStrings = {"Rat", "Crane", "Dragon", "Snake", "Turtle", "Buffalo", "Phoenix"};
	//Create the combo box for people
	JComboBox animal = new JComboBox (animalStrings);
	animal.setSelectedIndex (3);
	animal.setBackground (new Color (115, 95, 64));
	animal.setForeground (Color.white);
	animal.setActionCommand ("animal");
	animal.addActionListener (this);
	//Reset button
	JButton reset = new JButton ("Reset");
	reset.setActionCommand ("reset");
	reset.addActionListener (this);
	//Set up grid
	Panel p = new Panel (new GridLayout (row, col));
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move] = new JButton (createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".jpg"));
		a [move].setPreferredSize (new Dimension (53, 53));
		a [move].addActionListener (this);
		a [move].setActionCommand ("" + move);
		p.add (a [move]);
		move++;
	    }
	}
	//Heading Elements
	Panel g = new Panel ();
	g.add (colourturn);
	g.add (turnpic);
	g.add (display);
	g.add (animal);
	//Screen Elements
	card3.add (title);
	card3.add (g);
	card3.add (p);
	card3.add (reset);
	p_card.add ("3", card3);
    }


    protected static ImageIcon createImageIcon (String path)
    { //Gets the images from the folder
	java.net.URL imgURL = CoTuong.class.getResource (path);
	if (imgURL != null)
	{
	    return new ImageIcon (imgURL);
	}
	else
	{ //ensures that the photo is found
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    }


    public void dater ()
    { //Gets the present date
	SimpleDateFormat formatter = new SimpleDateFormat ("yyyy/MM/dd");
	Date date = new Date ();
	time = formatter.format (date);
    }


    public void ps ()
    { //plays sound
	soundFile = getAudioClip (getDocumentBase (), "TheFourSeasons.wav");
	soundFile.play ();
    }


    public void ss ()
    { //plays sound
	soundFile = getAudioClip (getDocumentBase (), "TheFourSeasons.wav");
	soundFile.stop ();
    }


    public void ls ()
    { //plays sound
	soundFile = getAudioClip (getDocumentBase (), "TheFourSeasons.wav");
	soundFile.loop ();
    }


    public void starting ()
    {
	if (startcondition == 0)
	{
	    //Show a dialog asking the user to select from a combobox:
	    String[] possibleValues = {"Red", "Blue"};
	    String selectedValue = (String) JOptionPane.showInputDialog (null,
		    "Who Starts?", "Input", JOptionPane.INFORMATION_MESSAGE, null,
		    possibleValues, possibleValues [0]);
	    if (selectedValue == "Red")
	    {
		unselect ();
		turn = 'r';
		colourturn.setText ("Red's Turn");
		turnpic.setIcon (createImageIcon ("red.jpg"));
	    }
	    else
	    {
		unselect ();
		turn = 'b';
		colourturn.setText ("Blue's Turn");
		turnpic.setIcon (createImageIcon ("blue.jpg"));
	    }
	}
	startcondition = -1;
    }


    public void redraw ()
    { //Reprints the array
	int move = 0;
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		a [move].setIcon (createImageIcon (piece [i] [j] + "" + colour [i] [j] + "" + bg [i] [j] + "" + select [i] [j] + ".jpg"));
		move++;
	    }
	}
    }


    public void save1 (String filename)
    { //Insert codes for chess layout
	PrintWriter out;
	try
	{
	    out = new PrintWriter (new FileWriter (filename));
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + piece [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + colour [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + bg [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + select [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + ckpiece [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + ckcolour [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + ckbg [i] [j]);
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    out.println ("" + ckselect [i] [j]);
		}
	    }
	    out.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("Error opening file " + e);
	}
    }


    public void open1 (String filename)
    { //Insert code for turn
	BufferedReader in;
	try
	{ //prints out chess layout
	    in = new BufferedReader (new FileReader (filename));
	    String input = in.readLine ();
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    piece [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    colour [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    bg [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    select [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    ckpiece [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    ckcolour [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    ckbg [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    for (int i = 0 ; i < row ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    ckselect [i] [j] = input.charAt (0);
		    input = in.readLine ();
		}
	    }
	    in.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("Error opening file " + e);
	}
	redraw ();
    }


    public void save2 (String filename)
    { //Insert code for turn
	PrintWriter out;
	try
	{
	    out = new PrintWriter (new FileWriter (filename));
	    out.println ("" + turn);
	    out.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("Error opening file " + e);
	}
    }


    public void open2 (String filename)
    { //Insert code for turn
	BufferedReader in;
	try
	{
	    in = new BufferedReader (new FileReader (filename));
	    String input = in.readLine ();
	    turn = input.charAt (0);
	    in.close ();
	}
	catch (IOException e)
	{
	    System.out.println ("Error opening file " + e);
	}
	if (turn == 'r')
	{
	    colourturn.setText ("Red's Turn");
	    turnpic.setIcon (createImageIcon ("red.jpg"));
	}
	else
	{
	    colourturn.setText ("Blue's Turn");
	    turnpic.setIcon (createImageIcon ("blue.jpg"));
	}
    }


    //Resets Board
    public void reset ()
    { //copy every element of r into b
	restart ();
	startcondition = 0;
	starting ();
	checkcondition = 0;
	viable = 0;
    }


    //Alternate turns
    public void switchTurn ()
    {
	if (turn == 'r')
	{
	    unselect ();
	    turn = 'b';
	    turnpic.setIcon (createImageIcon ("blue.jpg"));
	}
	else
	{
	    unselect ();
	    turn = 'r';
	    turnpic.setIcon (createImageIcon ("red.jpg"));
	}
    }


    //Makes sure there's a piece between the Dragons
    public void betweenDragon (int x, int y)
    {
	char piecetemp1 = 'z';
	char piecetemp2 = 'z';
	condition = 0;

	//up
	int capturable = 0;

	for (int i = x - 1 ; i > -1 ; i--)
	    if (colour [i] [y] != 'x' && capturable == 0)
	    {
		piecetemp1 = piece [i] [y];
		capturable = 1;
	    }


	//down
	capturable = 0;
	for (int i = x + 1 ; i < row ; i++)
	    if (colour [i] [y] != 'x' && capturable == 0)
	    {
		piecetemp2 = piece [i] [y];
		capturable = 1;
	    }


	if (piecetemp1 == 'd' && piecetemp2 == 'd')
	    condition = 1;
    }


    //Makes sure Red Dragon never files with Blue Dragon
    public void specialRedDragon (int x, int y)
    {
	dragontemp1 = 'z';
	dragontemp2 = 'z';

	//up (left)
	int capturable = 0;

	for (int i = x - 1 ; i > -1 ; i--)
	    if (colour [i] [y - 1] != 'x' && capturable == 0)
	    {
		dragontemp1 = piece [i] [y - 1];
		capturable = 1;
	    }
	//up (right)
	capturable = 0;

	for (int i = x - 1 ; i > -1 ; i--)
	    if (colour [i] [y + 1] != 'x' && capturable == 0)
	    {
		dragontemp2 = piece [i] [y + 1];
		capturable = 1;
	    }
    }


    //Makes sure Blue Dragon never files with Red Dragon
    public void specialBlueDragon (int x, int y)
    {
	dragontemp1 = 'z';
	dragontemp2 = 'z';
	//down (left)
	int capturable = 0;

	for (int i = x + 1 ; i < row ; i++)
	    if (colour [i] [y - 1] != 'x' && capturable == 0)
	    {
		dragontemp1 = piece [i] [y - 1];
		capturable = 1;
	    }
	//down (right)
	capturable = 0;
	for (int i = x + 1 ; i < row ; i++)
	    if (colour [i] [y + 1] != 'x' && capturable == 0)
	    {
		dragontemp2 = piece [i] [y + 1];
		capturable = 1;
	    }

    }



    public void selectRat (int x, int y)
    { //rat movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	if (colour [x] [y] == 'r')
	{
	    if (x > 0 && colour [x - 1] [y] != turn)
		select [x - 1] [y] = 's';
	    //when rat is on enemy territory, it can move horizontally
	    if (x < 5 && y > 0 && colour [x] [y - 1] != turn && condition == 0)
		select [x] [y - 1] = 's';
	    if (x < 5 && y < 8 && colour [x] [y + 1] != turn && condition == 0)
		select [x] [y + 1] = 's';
	}
	if (colour [x] [y] == 'b')
	{
	    if (x < 9 && colour [x + 1] [y] != turn)
		select [x + 1] [y] = 's';
	    //when rat is on enemy territory, it can move horizontally
	    if (x > 4 && y > 0 && colour [x] [y - 1] != turn && condition == 0)
		select [x] [y - 1] = 's';
	    if (x > 4 && y < 8 && colour [x] [y + 1] != turn && condition == 0)
		select [x] [y + 1] = 's';
	}

    }


    public void selectCrane (int x, int y)
    { //crane movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	if (colour [x] [y] == 'r')
	{ //Piece can only move diagonally but is contained in the palace (small boxes on each side)
	    if (colour [x - 1] [y - 1] != turn && bg [x - 1] [y - 1] == 'e' && condition == 0)
		select [x - 1] [y - 1] = 's';
	    if (colour [x - 1] [y + 1] != turn && bg [x - 1] [y + 1] == 'e' && condition == 0)
		select [x - 1] [y + 1] = 's';
	    if (x < 9 && y > 2 && colour [x + 1] [y - 1] != turn && bg [x + 1] [y - 1] == 'e' && condition == 0)
		select [x + 1] [y - 1] = 's';
	    if (x < 9 && y < 6 && colour [x + 1] [y + 1] != turn && bg [x + 1] [y + 1] == 'e' && condition == 0)
		select [x + 1] [y + 1] = 's';
	}
	else if (colour [x] [y] == 'b')
	{ //Piece can only move diagonally but is contained in the palace (small boxes on each side)
	    if (x > 0 && y > 2 && colour [x - 1] [y - 1] != turn && bg [x - 1] [y - 1] == 'n' && condition == 0)
		select [x - 1] [y - 1] = 's';
	    if (x > 0 && y < 6 && colour [x - 1] [y + 1] != turn && bg [x - 1] [y + 1] == 'n' && condition == 0)
		select [x - 1] [y + 1] = 's';
	    if (colour [x + 1] [y - 1] != turn && bg [x + 1] [y - 1] == 'n' && condition == 0)
		select [x + 1] [y - 1] = 's';
	    if (colour [x + 1] [y + 1] != turn && bg [x + 1] [y + 1] == 'n' && condition == 0)
		select [x + 1] [y + 1] = 's';
	}
    }


    public void selectDragon (int x, int y)
    { //dragon movements
	if (colour [x] [y] == 'r')
	{ //dragons are contained in their respected palace
	    specialRedDragon (x, y); //Dragon is not allowed to move if it'll be in file with the other dragon face to face
	    if (bg [x - 1] [y] == 'e' && colour [x - 1] [y] != turn)
		select [x - 1] [y] = 's';
	    if (x < 9 && colour [x + 1] [y] != turn)
		select [x + 1] [y] = 's';
	    if (bg [x] [y + 1] == 'e' && colour [x] [y + 1] != turn && dragontemp2 != 'd')
		select [x] [y + 1] = 's';
	    if (bg [x] [y - 1] == 'e' && colour [x] [y - 1] != turn && dragontemp1 != 'd')
		select [x] [y - 1] = 's';
	}
	else if (colour [x] [y] == 'b')
	{
	    specialBlueDragon (x, y); //Dragon is not allowed to move if it'll be in file with the other dragon face to face
	    if (x > 0 && colour [x - 1] [y] != turn)
		select [x - 1] [y] = 's';
	    if (bg [x + 1] [y] == 'n' && colour [x + 1] [y] != turn)
		select [x + 1] [y] = 's';
	    if (bg [x] [y + 1] == 'n' && colour [x] [y + 1] != turn && dragontemp2 != 'd')
		select [x] [y + 1] = 's';
	    if (bg [x] [y - 1] == 'n' && colour [x] [y - 1] != turn && dragontemp1 != 'd')
		select [x] [y - 1] = 's';
	}
    }




    public void selectTurtle (int x, int y)
    { //turtle movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	int cx1 = x - 1;
	while (cx1 >= 0 && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1--;
	}
	if (cx1 >= 0 && colour [cx1] [y] != turn && colour [cx1] [y] != 'x')
	    select [cx1] [y] = 's';

	cx1 = x + 1;
	while (cx1 < row && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1++;
	}
	if (cx1 < row && colour [cx1] [y] != turn && colour [cx1] [y] != 'x')
	    select [cx1] [y] = 's';

	int cy1 = y - 1;
	while (cy1 >= 0 && colour [x] [cy1] == 'x' && condition == 0)
	{
	    select [x] [cy1] = 's';
	    cy1--;
	}
	if (cy1 >= 0 && colour [x] [cy1] != turn && colour [x] [cy1] != 'x' && condition == 0)
	    select [x] [cy1] = 's';
	cy1 = y + 1;
	while (cy1 < col && colour [x] [cy1] == 'x' && condition == 0)
	{
	    select [x] [cy1] = 's';
	    cy1++;
	}
	if (cy1 < col && colour [x] [cy1] != turn && colour [x] [cy1] != 'x' && condition == 0)
	    select [x] [cy1] = 's';
    }


    public void selectBuffalo (int x, int y)
    { //buffalo movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	//up
	if (x > 1 && y > 0 && colour [x - 2] [y - 1] != turn && piece [x - 1] [y] == 'x' && condition == 0)
	    select [x - 2] [y - 1] = 's';
	if (x > 1 && y < 8 && colour [x - 2] [y + 1] != turn && piece [x - 1] [y] == 'x' && condition == 0)
	    select [x - 2] [y + 1] = 's';
	//down
	if (x < 8 && y > 0 && colour [x + 2] [y - 1] != turn && piece [x + 1] [y] == 'x' && condition == 0)
	    select [x + 2] [y - 1] = 's';
	if (x < 8 && y < 8 && colour [x + 2] [y + 1] != turn && piece [x + 1] [y] == 'x' && condition == 0)
	    select [x + 2] [y + 1] = 's';
	//right
	if (x > 1 && y < 7 && colour [x - 1] [y + 2] != turn && piece [x] [y + 1] == 'x' && condition == 0)
	    select [x - 1] [y + 2] = 's';
	if (x < 9 && y < 7 && colour [x + 1] [y + 2] != turn && piece [x] [y + 1] == 'x' && condition == 0)
	    select [x + 1] [y + 2] = 's';
	//left
	if (x > 1 && y > 1 && colour [x - 1] [y - 2] != turn && piece [x] [y - 1] == 'x' && condition == 0)
	    select [x - 1] [y - 2] = 's';
	if (x < 9 && y > 1 && colour [x + 1] [y - 2] != turn && piece [x] [y - 1] == 'x' && condition == 0)
	    select [x + 1] [y - 2] = 's';
    }


    public void selectSnake (int x, int y)
    { //snake movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	if (colour [x] [y] == 'r')
	{
	    if (x < 8 && y < 7 && colour [x + 2] [y + 2] != turn && piece [x + 1] [y + 1] == 'x' && condition == 0)
		select [x + 2] [y + 2] = 's';
	    if (x < 8 && y > 1 && colour [x + 2] [y - 2] != turn && piece [x + 1] [y - 1] == 'x' && condition == 0)
		select [x + 2] [y - 2] = 's';
	    if (x > 5 && y < 7 && colour [x - 2] [y + 2] != turn && piece [x - 1] [y + 1] == 'x' && condition == 0)
		select [x - 2] [y + 2] = 's';
	    if (x > 5 && y > 1 && colour [x - 2] [y - 2] != turn && piece [x - 1] [y - 1] == 'x' && condition == 0)
		select [x - 2] [y - 2] = 's';
	}
	else if (colour [x] [y] == 'b')
	{
	    if (x < 4 && y < 7 && colour [x + 2] [y + 2] != turn && piece [x + 1] [y + 1] == 'x' && condition == 0)
		select [x + 2] [y + 2] = 's';
	    if (x < 4 && y > 1 && colour [x + 2] [y - 2] != turn && piece [x + 1] [y - 1] == 'x' && condition == 0)
		select [x + 2] [y - 2] = 's';
	    if (x > 1 && y < 7 && colour [x - 2] [y + 2] != turn && piece [x - 1] [y + 1] == 'x' && condition == 0)
		select [x - 2] [y + 2] = 's';
	    if (x > 1 && y > 1 && colour [x - 2] [y - 2] != turn && piece [x - 1] [y - 1] == 'x' && condition == 0)
		select [x - 2] [y - 2] = 's';
	}
    }


    public void selectPhoenix (int x, int y)
    { //phoneix movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	//up
	int capturable = 0;
	int cx1 = x - 1;
	while (cx1 >= 0 && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1--;
	}
	//makes the piece behind the facing piece selectable
	for (int i = cx1 - 1 ; i > -1 ; i--)
	    if (colour [i] [y] != 'x' && colour [i] [y] != turn && capturable == 0)
	    {
		select [i] [y] = 's';
		capturable = 1;
	    }
	//down
	capturable = 0;
	cx1 = x + 1;
	while (cx1 < row && colour [cx1] [y] == 'x')
	{
	    select [cx1] [y] = 's';
	    cx1++;
	}
	//makes the piece behind the facing piece selectable
	for (int i = cx1 + 1 ; i < row ; i++)
	    if (colour [i] [y] != 'x' && colour [i] [y] != turn && capturable == 0)
	    {
		select [i] [y] = 's';
		capturable = 1;
	    }
	//left
	capturable = 0;
	int cy1 = y - 1;
	while (cy1 >= 0 && colour [x] [cy1] == 'x' && condition == 0)
	{
	    select [x] [cy1] = 's';
	    cy1--;
	}
	//makes the piece behind the facing piece selectable
	for (int i = cy1 - 1 ; i > -1 ; i--)
	{
	    if (colour [x] [i] != 'x' && colour [x] [i] != turn && capturable == 0 && condition == 0)
	    {
		select [x] [i] = 's';
		capturable = 1;
	    }
	}
	//right
	capturable = 0;
	cy1 = y + 1;
	while (cy1 < col && colour [x] [cy1] == 'x' && condition == 0)
	{
	    select [x] [cy1] = 's';
	    cy1++;
	}
	//makes the piece behind the facing piece selectable
	for (int i = cy1 + 1 ; i < col ; i++)
	{
	    if (colour [x] [i] != 'x' && colour [x] [i] != turn && capturable == 0 && condition == 0)
	    {
		select [x] [i] = 's';
		capturable = 1;
	    }
	}

    }


    public void copy ()
    { //Makes the background array the same as the printed array
	for (int i = 0 ; i < ckpiece.length ; i++)
	    for (int j = 0 ; j < ckpiece [i].length ; j++)
	    {
		ckpiece [i] [j] = piece [i] [j];
		ckbg [i] [j] = bg [i] [j];
		ckselect [i] [j] = select [i] [j];
		ckcolour [i] [j] = colour [i] [j];
	    }
    }


    public void ckselectRat (int x, int y)
    { //rate movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	if (ckcolour [x] [y] == 'r')
	{
	    if (x > 0 && ckcolour [x - 1] [y] != turn)
		ckselect [x - 1] [y] = 's';
	    //when rat is on enemy territory, it can move horizontally
	    if (x < 5 && y > 0 && ckcolour [x] [y - 1] != turn && condition == 0)
		ckselect [x] [y - 1] = 's';
	    if (x < 5 && y < 8 && ckcolour [x] [y + 1] != turn && condition == 0)
		ckselect [x] [y + 1] = 's';
	}
	if (ckcolour [x] [y] == 'b')
	{
	    if (x < 9 && ckcolour [x + 1] [y] != turn)
		ckselect [x + 1] [y] = 's';
	    //when rat is on enemy territory, it can move horizontally
	    if (x > 4 && y > 0 && ckcolour [x] [y - 1] != turn && condition == 0)
		ckselect [x] [y - 1] = 's';
	    if (x > 4 && y < 8 && ckcolour [x] [y + 1] != turn && condition == 0)
		ckselect [x] [y + 1] = 's';
	}

    }


    public void ckselectCrane (int x, int y)
    { //crane movements
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	if (ckcolour [x] [y] == 'r')
	{ //Piece can only move diagonally but is contained in the palace (small boxes on each side)
	    if (ckcolour [x - 1] [y - 1] != turn && ckbg [x - 1] [y - 1] == 'e' && condition == 0)
		ckselect [x - 1] [y - 1] = 's';
	    if (ckcolour [x - 1] [y + 1] != turn && ckbg [x - 1] [y + 1] == 'e' && condition == 0)
		ckselect [x - 1] [y + 1] = 's';
	    if (x < 9 && y > 2 && ckcolour [x + 1] [y - 1] != turn && ckbg [x + 1] [y - 1] == 'e' && condition == 0)
		ckselect [x + 1] [y - 1] = 's';
	    if (x < 9 && y < 6 && ckcolour [x + 1] [y + 1] != turn && ckbg [x + 1] [y + 1] == 'e' && condition == 0)
		ckselect [x + 1] [y + 1] = 's';
	}
	else if (ckcolour [x] [y] == 'b')
	{ //Piece can only move diagonally but is contained in the palace (small boxes on each side)
	    if (x > 0 && y > 2 && ckcolour [x - 1] [y - 1] != turn && ckbg [x - 1] [y - 1] == 'n' && condition == 0)
		ckselect [x - 1] [y - 1] = 's';
	    if (x > 0 && y < 6 && ckcolour [x - 1] [y + 1] != turn && ckbg [x - 1] [y + 1] == 'n' && condition == 0)
		ckselect [x - 1] [y + 1] = 's';
	    if (ckcolour [x + 1] [y - 1] != turn && ckbg [x + 1] [y - 1] == 'n' && condition == 0)
		ckselect [x + 1] [y - 1] = 's';
	    if (ckcolour [x + 1] [y + 1] != turn && ckbg [x + 1] [y + 1] == 'n' && condition == 0)
		ckselect [x + 1] [y + 1] = 's';
	}
    }


    public void ckselectDragon (int x, int y)
    { //dragon movements
	if (ckcolour [x] [y] == 'r')
	{ //dragons are contained in their respected palace
	    specialRedDragon (x, y); //Dragon is not allowed to move if it'll be in file with the other dragon face to face
	    if (ckbg [x - 1] [y] == 'e' && ckcolour [x - 1] [y] != turn)
		ckselect [x - 1] [y] = 's';
	    if (x < 9 && ckcolour [x + 1] [y] != turn)
		ckselect [x + 1] [y] = 's';
	    if (ckbg [x] [y + 1] == 'e' && ckcolour [x] [y + 1] != turn && dragontemp2 != 'd')
		ckselect [x] [y + 1] = 's';
	    if (ckbg [x] [y - 1] == 'e' && ckcolour [x] [y - 1] != turn && dragontemp1 != 'd')
		ckselect [x] [y - 1] = 's';
	}
	else if (ckcolour [x] [y] == 'b')
	{
	    specialBlueDragon (x, y); //Dragon is not allowed to move if it'll be in file with the other dragon face to face
	    if (x > 0 && ckcolour [x - 1] [y] != turn)
		ckselect [x - 1] [y] = 's';
	    if (ckbg [x + 1] [y] == 'n' && ckcolour [x + 1] [y] != turn)
		ckselect [x + 1] [y] = 's';
	    if (ckbg [x] [y + 1] == 'n' && ckcolour [x] [y + 1] != turn && dragontemp2 != 'd')
		ckselect [x] [y + 1] = 's';
	    if (ckbg [x] [y - 1] == 'n' && ckcolour [x] [y - 1] != turn && dragontemp1 != 'd')
		ckselect [x] [y - 1] = 's';
	}
    }


    public void ckselectTurtle (int x, int y)
    {
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	int cx1 = x - 1;
	while (cx1 >= 0 && ckcolour [cx1] [y] == 'x')
	{
	    ckselect [cx1] [y] = 's';
	    cx1--;
	}
	if (cx1 >= 0 && ckcolour [cx1] [y] != turn && ckcolour [cx1] [y] != 'x')
	    ckselect [cx1] [y] = 's';

	cx1 = x + 1;
	while (cx1 < row && ckcolour [cx1] [y] == 'x')
	{
	    ckselect [cx1] [y] = 's';
	    cx1++;
	}
	if (cx1 < row && ckcolour [cx1] [y] != turn && ckcolour [cx1] [y] != 'x')
	    ckselect [cx1] [y] = 's';

	int cy1 = y - 1;
	while (cy1 >= 0 && ckcolour [x] [cy1] == 'x' && condition == 0)
	{
	    ckselect [x] [cy1] = 's';
	    cy1--;
	}
	if (cy1 >= 0 && ckcolour [x] [cy1] != turn && ckcolour [x] [cy1] != 'x' && condition == 0)
	    ckselect [x] [cy1] = 's';
	cy1 = y + 1;
	while (cy1 < col && ckcolour [x] [cy1] == 'x' && condition == 0)
	{
	    ckselect [x] [cy1] = 's';
	    cy1++;
	}
	if (cy1 < col && ckcolour [x] [cy1] != turn && ckcolour [x] [cy1] != 'x' && condition == 0)
	    ckselect [x] [cy1] = 's';
    }


    public void ckselectBuffalo (int x, int y)
    {
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	//up
	if (x > 1 && y > 0 && ckcolour [x - 2] [y - 1] != turn && ckpiece [x - 1] [y] == 'x' && condition == 0)
	    ckselect [x - 2] [y - 1] = 's';
	if (x > 1 && y < 8 && ckcolour [x - 2] [y + 1] != turn && ckpiece [x - 1] [y] == 'x' && condition == 0)
	    ckselect [x - 2] [y + 1] = 's';
	//down
	if (x < 8 && y > 0 && ckcolour [x + 2] [y - 1] != turn && ckpiece [x + 1] [y] == 'x' && condition == 0)
	    ckselect [x + 2] [y - 1] = 's';
	if (x < 8 && y < 8 && ckcolour [x + 2] [y + 1] != turn && ckpiece [x + 1] [y] == 'x' && condition == 0)
	    ckselect [x + 2] [y + 1] = 's';
	//right
	if (x > 1 && y < 7 && ckcolour [x - 1] [y + 2] != turn && ckpiece [x] [y + 1] == 'x' && condition == 0)
	    ckselect [x - 1] [y + 2] = 's';
	if (x < 9 && y < 7 && ckcolour [x + 1] [y + 2] != turn && ckpiece [x] [y + 1] == 'x' && condition == 0)
	    ckselect [x + 1] [y + 2] = 's';
	//left
	if (x > 1 && y > 1 && ckcolour [x - 1] [y - 2] != turn && ckpiece [x] [y - 1] == 'x' && condition == 0)
	    ckselect [x - 1] [y - 2] = 's';
	if (x < 9 && y > 1 && ckcolour [x + 1] [y - 2] != turn && ckpiece [x] [y - 1] == 'x' && condition == 0)
	    ckselect [x + 1] [y - 2] = 's';
    }


    public void ckselectSnake (int x, int y)
    {
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	if (ckcolour [x] [y] == 'r')
	{
	    if (x < 8 && y < 7 && ckcolour [x + 2] [y + 2] != turn && ckpiece [x + 1] [y + 1] == 'x' && condition == 0)
		ckselect [x + 2] [y + 2] = 's';
	    if (x < 8 && y > 1 && ckcolour [x + 2] [y - 2] != turn && ckpiece [x + 1] [y - 1] == 'x' && condition == 0)
		ckselect [x + 2] [y - 2] = 's';
	    if (x > 5 && y < 7 && ckcolour [x - 2] [y + 2] != turn && ckpiece [x - 1] [y + 1] == 'x' && condition == 0)
		ckselect [x - 2] [y + 2] = 's';
	    if (x > 5 && y > 1 && ckcolour [x - 2] [y - 2] != turn && ckpiece [x - 1] [y - 1] == 'x' && condition == 0)
		ckselect [x - 2] [y - 2] = 's';
	}
	else if (ckcolour [x] [y] == 'b')
	{
	    if (x < 4 && y < 7 && ckcolour [x + 2] [y + 2] != turn && ckpiece [x + 1] [y + 1] == 'x' && condition == 0)
		ckselect [x + 2] [y + 2] = 's';
	    if (x < 4 && y > 1 && ckcolour [x + 2] [y - 2] != turn && ckpiece [x + 1] [y - 1] == 'x' && condition == 0)
		ckselect [x + 2] [y - 2] = 's';
	    if (x > 1 && y < 7 && ckcolour [x - 2] [y + 2] != turn && ckpiece [x - 1] [y + 1] == 'x' && condition == 0)
		ckselect [x - 2] [y + 2] = 's';
	    if (x > 1 && y > 1 && ckcolour [x - 2] [y - 2] != turn && ckpiece [x - 1] [y - 1] == 'x' && condition == 0)
		ckselect [x - 2] [y - 2] = 's';
	}
    }


    public void ckselectPhoenix (int x, int y)
    {
	betweenDragon (x, y); //Makes sure that the piece is the only piece between the dragons
	//up
	int capturable = 0;
	int cx1 = x - 1;
	while (cx1 >= 0 && ckcolour [cx1] [y] == 'x')
	{
	    ckselect [cx1] [y] = 's';
	    cx1--;
	}
	//makes the piece behind the facing piece selectable
	for (int m = cx1 - 1 ; m > -1 ; m--)
	    if (ckcolour [m] [y] != 'x' && ckcolour [m] [y] != turn && capturable == 0)
	    {
		ckselect [m] [y] = 's';
		capturable = 1;
	    }
	//down
	capturable = 0;
	cx1 = x + 1;
	while (cx1 < row && ckcolour [cx1] [y] == 'x')
	{
	    ckselect [cx1] [y] = 's';
	    cx1++;
	}
	//makes the piece behind the facing piece selectable
	for (int m = cx1 + 1 ; m < row ; m++)
	    if (ckcolour [m] [y] != 'x' && ckcolour [m] [y] != turn && capturable == 0)
	    {
		ckselect [m] [y] = 's';
		capturable = 1;
	    }
	//left
	capturable = 0;
	int cy1 = y - 1;
	while (cy1 >= 0 && ckcolour [x] [cy1] == 'x' && condition == 0)
	{
	    ckselect [x] [cy1] = 's';
	    cy1--;
	}
	//makes the piece behind the facing piece selectable
	for (int m = cy1 - 1 ; m > -1 ; m--)
	{
	    if (ckcolour [x] [m] != 'x' && ckcolour [x] [m] != turn && capturable == 0 && condition == 0)
	    {
		ckselect [x] [m] = 's';
		capturable = 1;
	    }
	}
	//right
	capturable = 0;
	cy1 = y + 1;
	while (cy1 < col && ckcolour [x] [cy1] == 'x' && condition == 0)
	{
	    ckselect [x] [cy1] = 's';
	    cy1++;
	}
	//makes the piece behind the facing piece selectable
	for (int m = cy1 + 1 ; m < col ; m++)
	{
	    if (ckcolour [x] [m] != 'x' && ckcolour [x] [m] != turn && capturable == 0 && condition == 0)
	    {
		ckselect [x] [m] = 's';
		capturable = 1;
	    }
	}

    }




    public void ckselect (int m, int n)
    {//Runs the future possiblies to see if a check/ checkmate is possible
	copy (); //updates ckboard
	unselect (); //unselects all pieces
	for (m = 0 ; m < row ; m++)
	{
	    for (n = 0 ; n < col ; n++)
	    {
		if (ckpiece [m] [n] == 'r' && ckcolour [m] [n] == turn)
		{
		    ckselectRat (m, n);
		    checktrack ();
		}
		else if (ckpiece [m] [n] == 'c' && ckcolour [m] [n] == turn)
		{
		    ckselectCrane (m, n);
		    checktrack ();
		}
		else if (ckpiece [m] [n] == 'd' && ckcolour [m] [n] == turn)
		{
		    ckselectDragon (m, n);
		    checktrack ();
		}
		else if (ckpiece [m] [n] == 't' && ckcolour [m] [n] == turn)
		{
		    ckselectTurtle (m, n);
		    checktrack ();
		}
		else if (ckpiece [m] [n] == 'b' && ckcolour [m] [n] == turn)
		{
		    ckselectBuffalo (m, n);
		    checktrack ();
		}
		else if (ckpiece [m] [n] == 's' && ckcolour [m] [n] == turn)
		{
		    ckselectSnake (m, n);
		    checktrack ();
		}
		else if (ckpiece [m] [n] == 'p' && ckcolour [m] [n] == turn)
		{
		    ckselectPhoenix (m, n);
		    checktrack ();
		}
	    }
	}
    }



    public void checktrack ()
    { //Checks if there are any dragons that are capable of being selected by enemy
	for (m = 0 ; m < row ; m++)
	{
	    for (n = 0 ; n < col ; n++)
	    {
		if (ckpiece [m] [n] == 'd' && ckselect [m] [n] == 's')
		{
		    ckselect [m] [n] = 'u';
		    checkcondition++;
		}
	    }
	}
    }



    public void choisir ()
    {//prints out check
	for (m = 0 ; m < row ; m++)
	{
	    for (n = 0 ; n < col ; n++)
	    {
		if (ckpiece [m] [n] == 'd' && ckcolour [m] [n] != turn && abc == 2)
		    checkDragon (m, n);
		else if (ckpiece [m] [n] == 'd' && ckcolour [m] [n] == turn && abc == 4)
		    checkDragon (m, n);
	    }
	}
	if (viable >= 0)
	    JOptionPane.showMessageDialog (null, "CHECK", "alert",
		    JOptionPane.ERROR_MESSAGE);
    }



    public void checkDragon (int x, int y)
    {
	if (ckcolour [x] [y] == 'r')
	{ //dragons are contained in their respected palace
	    specialRedDragon (x, y); //Dragon is not allowed to move if it'll be in file with the other dragon face to face
	    if (ckbg [x - 1] [y] == 'e' && ckcolour [x - 1] [y] != 'r' && ckselect [x - 1] [y] != 's')
		viable++;
	    if (x < 9 && ckcolour [x + 1] [y] != 'r' && ckselect [x + 1] [y] != 's')
		viable++;
	    if (ckbg [x] [y - 1] == 'e' && ckcolour [x] [y - 1] != 'r' && dragontemp1 != 'd' && ckselect [x] [y - 1] != 's')
		viable++;
	    if (ckbg [x] [y + 1] == 'e' && ckcolour [x] [y + 1] != 'r' && dragontemp1 != 'd' && ckselect [x] [y + 1] != 's')
		viable++;
	}
	else if (ckcolour [x] [y] == 'b')
	{
	    specialBlueDragon (x, y);
	    if (x > 0 && ckcolour [x - 1] [y] != turn && ckselect [x - 1] [y] != 's')
		viable++;
	    if (ckbg [x + 1] [y] == 'n' && ckcolour [x + 1] [y] != 'b' && ckselect [x + 1] [y] != 's')
		viable++;
	    if (ckbg [x] [y + 1] == 'n' && ckcolour [x] [y + 1] != 'b' && dragontemp2 != 'd' && ckselect [x] [y + 1] != 's')
		viable++;
	    if (ckbg [x] [y - 1] == 'n' && ckcolour [x] [y - 1] != 'b' && dragontemp1 != 'd' && ckselect [x] [y - 1] != 's')
		viable++;
	}
    }


    public void endgame (int m, int n)
    {//runs throught the board and sees if there are any missing dragons
	red = 2;
	blue = 2;
	for (m = 0 ; m < row ; m++)
	{
	    for (n = 0 ; n < col ; n++)
	    {
		if (ckpiece [m] [n] == 'd' && ckcolour [m] [n] == 'r')
		    red++;
		else if (ckpiece [m] [n] == 'd' && ckcolour [m] [n] == 'b')
		    blue++;

	    }
	}// if there are any missing dragons (int is the same), it'll print the winner
	if (red == 2)
	{
	    JOptionPane.showMessageDialog (null, "WINNER *** BLUE"
		    , "Congratulations", JOptionPane.QUESTION_MESSAGE);
	    cdLayout.show (p_card, "1");
	    startcondition = 0;
	    restart ();
	}
	else if (blue == 2)
	{
	    JOptionPane.showMessageDialog (null, "WINNER *** RED"
		    , "Congratulations", JOptionPane.QUESTION_MESSAGE);
	    cdLayout.show (p_card, "1");
	    startcondition = 0;
	    restart ();
	}
    }


    public void restart ()
    {//made specially for the endgame to restart the board because the restart method has unique coding in it
	for (int i = 0 ; i < row ; i++)
	    for (int j = 0 ; j < col ; j++)
	    {
		piece [i] [j] = ogpiece [i] [j];
		select [i] [j] = ogselect [i] [j];
		colour [i] [j] = ogcolour [i] [j];
		bg [i] [j] = ogbg [i] [j];
	    }
	//call redraw to update b on the screen
	redraw ();
    }


    //Instruction methods
    public void insRat ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("rreu.jpg"), "Rat", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("rbeu.jpg"), "Rat", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * R A T *    * * \n \n"
		+ "Rats move and capture by advancing one point forward.\n"
		+ "Once a rat has crossed the river it may\n"
		+ "also move and capture one point horizontally.\n"
		+ "A rat may never move backward, thus retreating.\n"
		, "Instructions", JOptionPane.QUESTION_MESSAGE);
    }


    public void insCrane ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("creu.jpg"), "Crane", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("cbeu.jpg"), "Crane", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * C R A N E *    * * \n \n"
		+ "These are the dragon's counselors\n"
		+ "and guard the dragon within the palace.\n"
		+ "The cranes moves one point\n"
		+ "diagonally and is confined to the palace.\n"
		, "Instructions", JOptionPane.QUESTION_MESSAGE);
    }


    public void insDragon ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("dreu.jpg"), "Dragon", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("dbeu.jpg"), "Dragon", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * D R A G O N *    * * \n \n"
		+ "Dragon may move one point\n"
		+ "either vertically or horizontally,\n"
		+ "but not diagonally and is confined to the\n"
		+ "nine points within his palace.\n"
		+ "A dragon may not also move into a file,\n"
		+ "which is occupied by the enemy dragon,\n"
		+ "unless there is at least one piece\n"
		+ "positioned between the dragons in the file.\n"
		, "Instructions", JOptionPane.QUESTION_MESSAGE);
    }


    public void insSnake ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("sreu.jpg"), "Snake", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("sbeu.jpg"), "Snake", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * S N A K E *    * * \n \n"
		+ "The elephants move exactly two points\n"
		+ "in any diagonal direction\n"
		+ "and may not jump over intervening pieces\n"
		+ "or cross the river.\n"
		, "Instructions", JOptionPane.QUESTION_MESSAGE);
    }


    public void insTurtle ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("treu.jpg"), "Turtle", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("tbeu.jpg"), "Turtle", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * T U R T L E *    * * \n \n"
		+ "The turtle moves as many points as it wishes\n"
		+ "horizontally or vertically.\n"
		+ "It cannot jump over pieces in its path.\n"
		, "Instructions", JOptionPane.QUESTION_MESSAGE);
    }


    public void insBuffalo ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("breu.jpg"), "Buffalo", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("bbeu.jpg"), "Buffalo", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * B U F F A L O *    * * \n \n"
		+ "The buffalo moves one point horizontally or vertically,\n"
		+ "and then one point diagonally.\n"
		+ "It cannot move in a direction where there is a piece\n"
		+ "blocking it along the path of movement.\n"
		, "Instructions", JOptionPane.QUESTION_MESSAGE);
    }


    public void insPhoenix ()
    {
	JOptionPane.showMessageDialog (null, createImageIcon ("preu.jpg"), "Phoenix", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, createImageIcon ("pbeu.jpg"), "Phoenix", JOptionPane.INFORMATION_MESSAGE);
	JOptionPane.showMessageDialog (null, "* * * P H O E N I X *    * * \n \n"
		+ "Phoenixes move exactly like the turtle.\n"
		+ "To capture, however, a cannon must jump over\n"
		+ "exactly one piece, friend or foe,\n"
		+ "along its line of movement.\n"
		, "Instructions", JOptionPane.QUESTION_MESSAGE);
    }


    //Instructions selection
    public void insselector (String animal_s)
    {
	if (animal_s == "Rat")
	    insRat ();
	else if (animal_s == "Crane")
	    insCrane ();
	else if (animal_s == "Dragon")
	    insDragon ();
	else if (animal_s == "Snake")
	    insSnake ();
	else if (animal_s == "Turtle")
	    insTurtle ();
	else if (animal_s == "Buffalo")
	    insBuffalo ();
	else if (animal_s == "Phoenix")
	    insPhoenix ();
    }


    //When the piece is selected
    public void pieceselector (int x, int y)
    {
	if (piece [x] [y] == 'r')
	    selectRat (x, y);
	else if (piece [x] [y] == 'c')
	    selectCrane (x, y);
	else if (piece [x] [y] == 'd')
	    selectDragon (x, y);
	else if (piece [x] [y] == 't')
	    selectTurtle (x, y);
	else if (piece [x] [y] == 'b')
	    selectBuffalo (x, y);
	else if (piece [x] [y] == 's')
	    selectSnake (x, y);
	else if (piece [x] [y] == 'p')
	    selectPhoenix (x, y);
    }


    //Makes the board unselected
    public void unselect ()
    {
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		select [i] [j] = 'u';
		ckselect [i] [j] = 'u';
	    }
	}
    }


    //Switches turn
    public void switchturn ()
    {
	if (turn == 'r')
	{
	    turn = 'b';
	    colourturn.setText ("Blue's Turn");
	    turnpic.setIcon (createImageIcon ("blue.jpg"));
	}
	else
	{
	    turn = 'r';
	    colourturn.setText ("Red's Turn");
	    turnpic.setIcon (createImageIcon ("red.jpg"));
	}
    }


    public void actionPerformed (ActionEvent e)
    { //moves between the screens
	if (e.getActionCommand ().equals ("s1"))
	{
	    cdLayout.show (p_card, "1");
	    showStatus ("Opening Room");
	}
	else if (e.getActionCommand ().equals ("s2"))
	{
	    cdLayout.show (p_card, "2");
	    showStatus ("Game Rules");
	}
	else if (e.getActionCommand ().equals ("s3"))
	{
	    cdLayout.show (p_card, "3");
	    showStatus ("Board Room");
	    //Checks if turn decision is required
	    starting ();
	}
	else if (e.getActionCommand ().equals ("ps"))
	    //Plays sound
	    ps ();
	else if (e.getActionCommand ().equals ("ss"))
	    //Stops sound
	    ss ();
	else if (e.getActionCommand ().equals ("ls"))
	    //Loops sound
	    ls ();
	else if (e.getActionCommand ().equals ("close"))
	    //Closes program
	    System.exit (0);
	else if (e.getActionCommand ().equals ("save"))
	    //Saves game
	    {
		//Saves board
		save1 ("file1.txt");
		//Saves game
		save2 ("file2.txt");
	    }
	else if (e.getActionCommand ().equals ("open"))
	    //Loads saved game
	    {
		//Loads board
		open1 ("file1.txt");
		//Loads turn
		open2 ("file2.txt");
	    }
	else if (e.getActionCommand ().equals ("reset"))
	    //Resets game (board and turn)
	    reset ();
	else if (e.getActionCommand ().equals ("animal"))
	{ //Finds the string variable for the animal selected
	    JComboBox cb = (JComboBox) e.getSource ();
	    animal_s = (String) cb.getSelectedItem ();
	    //Finds the correct instruction method
	    insselector (animal_s);
	}
	else
	{ //code to handle the game
	    int n = Integer.parseInt (e.getActionCommand ());
	    int x = n / col;
	    int y = n % col;
	    showStatus ("(" + x + ", " + y + ")");
	    //If the wrong colour is chosen, it'll notifty the player
	    if (turn != colour [x] [y] && last == -1)
	    {
		showStatus ("It's not your turn");
		JOptionPane.showMessageDialog (null, "It's not your turn.", "alert",
			JOptionPane.ERROR_MESSAGE);
	    }
	    else if (last == -1 && turn == colour [x] [y])
	    {
		pieceselector (x, y);
		last = n;
	    }
	    else
	    {
		checkcondition = 0;
		abc = 0;
		int lastx = last / col;
		int lasty = last % col;
		//move
		if (select [x] [y] == 's')
		{
		    //move
		    piece [x] [y] = piece [lastx] [lasty];
		    piece [x] [y] = piece [lastx] [lasty];
		    piece [lastx] [lasty] = 'x';
		    colour [x] [y] = colour [lastx] [lasty];
		    colour [lastx] [lasty] = 'x';
		    abc = 2;
		    ckselect (m, n);
		    //switch turn
		    switchturn ();
		    abc = 4;
		    //checks if there's any checks possible
		    ckselect (m, n);
		    //prints out the check
		    if (checkcondition > 0)
			choisir ();
		    //Checks if the dragon is elminated
		    endgame (m, n);
		} //move done

		//reset select
		unselect ();
		last = -1;
	    } //move else
	} //grid else
	//updates board
	redraw ();
    } //AP
}


