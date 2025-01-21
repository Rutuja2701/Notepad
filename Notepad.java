package Notepad_clone;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Notepad {
	JTextArea area;//multi line text we can add
	JFrame frame; 
	JMenuBar menuBar;//used to contains one or more menu
	JMenu fileMenu, formatMenu, LangMenu, commandPromptMenu ;//used to contains different menu items 
	JMenuItem itemNew, itemNewWindow, itemOpen, itemSave, itemSaveAs, itemExit;// for fileMenu items
	JMenuItem itemWordWrap, itemFont, itemFontSize;// for format menu item
	JMenuItem itemArial, itemCalibri;
	JMenuItem itemJava, itemHtml, itemC, itemCpp;// for programming language
	JMenuItem itemCMD;// for command prompt
	String Openpath;
	String Openfile;
	Boolean wrap = false;
	Font f1, f2, f3, f4;
	String fontStyle = "Arial";

	public static void main(String[] args) {

		Notepad np = new Notepad();
	}

	public Notepad() {
		//calling methods through constructor  
		createFrame();
		createTextArea();
		createScrollBars();
		createMenuBar();
		createfileMenuItems();
		createFormatItems();
		createCommandPromotItems();
		createLanguageItems();
	}

	// JFrame provides basic window features like close window, minimize window , maximize window
	// it contains components like JLabel, JButton, JTextField etc
	public void createFrame() {
		frame = new JFrame("Notepad");
		frame.setSize(500, 300);
		Image icon = Toolkit.getDefaultToolkit().getImage("R:\\swingss\\noteapd.png");
		frame.setIconImage(icon);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// JTextArea is used to write the multiline texts
	public void createTextArea() {
		area = new JTextArea();
		frame.add(area);
		area.setFont(new Font("Arial", Font.PLAIN, 32));

	}

	// it is used to add scroll functionality as vertical & horizontal
	public void createScrollBars() {
		JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(pane);
	}

	
	//BufferedWriter -> it is a class used to write the large chunks of data 
	public void writeData(String path, String file) {
		BufferedWriter br = null;
		try {
			br = new BufferedWriter(new FileWriter(path + file));
			String text = area.getText();
			br.write(text);
			br.close();
		} catch (IOException e1) {
			System.out.println("Data can't be written");
		}

		finally {
			try {
				br.close();
			} catch (IOException e1) {

				System.out.println("File cannot be closed");
			} catch (NullPointerException npe) {

				System.out.println("File not found to closed");
			}
		}
	}
	
	// Bufferedreader->it is a class used to read large chunks of data
	public void ReadData(String fileName, String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path + fileName));
			String sentence = br.readLine();

			while (sentence != null) {
				area.append(sentence);
				sentence = br.readLine();
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		} catch (IOException e1) {
			System.out.println("Data could not be read");
		} catch (NullPointerException npe) {
			System.out.println("null");
		} finally {
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NullPointerException npe) {
			}
		}
	}
	
	public void setFont(String fontName) {
		fontStyle = fontName;
		switch (fontName) {
		case "Arial":
			area.setFont(f1);
			break;

		case "TimesNewRoman":
			area.setFont(f2);
			break;

		case "Calibri":
			area.setFont(f3);
			break;

		case "Algerian":
			area.setFont(f4);
		}
	}
	
	public void setFontSize(int size) {
		f1 = new Font("Arial", Font.BOLD, size);
		f2 = new Font("TimesNewRoman", Font.PLAIN, size);
		f3 = new Font("Calibri", Font.PLAIN, size);
		f4 = new Font("Algerian", Font.PLAIN, size);
		setFont(fontStyle);
	}
	
	public void setLanguage(String language) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader("R:\\fileHandle\\" + language + "Format.txt"));
			String sentence = br.readLine();

			area.setText("");
			while (sentence != null) {
				area.append(sentence+"\n");
				sentence = br.readLine();
			}
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
		} catch (IOException e1) {
			System.out.println("Data could not be read");
		} catch (NullPointerException npe) {
			System.out.println("null");
		} finally {
			try {
				br.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NullPointerException npe) {
			}
		}
	}

	public void createMenuBar() {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		LangMenu = new JMenu("Languages");
		menuBar.add(LangMenu);

		formatMenu = new JMenu("Format");
		menuBar.add(formatMenu);

		commandPromptMenu = new JMenu("Command Prompt");
		menuBar.add(commandPromptMenu);
		
	}

	public void createfileMenuItems() {

		itemNew = new JMenuItem("New");
		fileMenu.add(itemNew);
		itemNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				area.setText("");
				frame.setTitle("Untitled-Notepad");
				Openfile = null;
				Openpath = null;

			}
		});

		itemNewWindow = new JMenuItem("New Window");
		fileMenu.add(itemNewWindow);
		itemNewWindow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Notepad np2 = new Notepad();
				np2.frame.setTitle("Untitled");
			}
		});

		itemOpen = new JMenuItem("Open");
		fileMenu.add(itemOpen);
		itemOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileDialog fd = new FileDialog(frame, "Open");
				fd.setVisible(true);
				String path = fd.getDirectory();
				String file = fd.getFile();
				frame.setTitle(file);
				System.out.println(path + file);
				if (file != null) {
					frame.setTitle(path + file);
					Openfile = file;
					Openpath = path;
				}

				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(path + file));
					String sentence = br.readLine();

					area.setText("");
					while (sentence != null) {
						area.append(sentence);
						sentence = br.readLine();
					}
				} catch (FileNotFoundException e1) {
					System.out.println("File not found");
				} catch (IOException e1) {
					System.out.println("Data could not be read");
				} catch (NullPointerException npe) {
					System.out.println("null");
				} finally {
					try {
						br.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NullPointerException npe) {
					}
				}
			}
		});

		itemSave = new JMenuItem("Save");
		fileMenu.add(itemSave);
		itemSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Openfile != null && Openpath != null) {
					ReadData(Openfile, Openpath);
				}

				else {
					FileDialog fd = new FileDialog(frame, "SaveAs", FileDialog.SAVE);
					fd.setVisible(true);
					String path = fd.getDirectory();
					String file = fd.getFile();

					if (path != null & file != null) {
						writeData(path, file);
						Openfile = file;
						Openpath = path;
						frame.setTitle(Openpath);
					}
				}

			}
		});

		itemSaveAs = new JMenuItem("SaveAs");
		fileMenu.add(itemSaveAs);
		// ActionListener -> used to handle the action events
		itemSaveAs.addActionListener(new ActionListener() {

			@Override
			// actionPerformed-> is used to perform the action when user click on the button
			public void actionPerformed(ActionEvent e) {

				FileDialog fd = new FileDialog(frame, "SaveAs", FileDialog.SAVE);
				fd.setVisible(true);
				String path = fd.getDirectory();
				String fileName = fd.getFile();

				if (fileName != null & path != null) {
					BufferedWriter br = null;
					try {
						br = new BufferedWriter(new FileWriter(path + fileName));
						String text = area.getText();
						// br.newLine();
						br.write(text);
						br.close();
					} catch (IOException e1) {
						System.out.println("Data can't be written");
					}

					finally {
						try {
							br.close();
						} catch (IOException e1) {

							System.out.println("File canot be closed");
						} catch (NullPointerException npe) {

							System.out.println("File not found to closed");
						}
					}
				}
			}
		});

		itemExit = new JMenuItem("Exit");
		fileMenu.add(itemExit);
		itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
	}

	public void createLanguageItems() {
		itemJava = new JMenuItem("Java");
		LangMenu.add(itemJava);

		itemJava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setLanguage("Java");
			}
		});

		itemC = new JMenuItem("C");
		LangMenu.add(itemC);

		itemC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setLanguage("C");
			}
		});

		itemCpp = new JMenuItem("C++");
		LangMenu.add(itemCpp);
		itemCpp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setLanguage("C++");
			}
		});

		itemHtml = new JMenuItem("Html");
		LangMenu.add(itemHtml);
		itemHtml.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setLanguage("HTML");
			}
		});
	}

	public void createFormatItems() {
		itemWordWrap = new JMenuItem("Word Wrap");
		formatMenu.add(itemWordWrap);
		itemWordWrap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (wrap == false) {
					area.setLineWrap(true);
					area.setWrapStyleWord(true);
					wrap = true;
					itemWordWrap.setText("Word Wrap On");
				} else {
					area.setLineWrap(false);
					area.setWrapStyleWord(false);
					wrap = false;
					itemWordWrap.setText("Word Wrap Off");
				}

			}
		});

		itemFont = new JMenu("Font");
		formatMenu.add(itemFont);

		JMenuItem itemArial = new JMenuItem("Arial");
		itemFont.add(itemArial);

		itemArial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFont("Arial");
			}
		});

		JMenuItem itemTimesNewRoman = new JMenuItem("TimesNewRoman");
		itemFont.add(itemTimesNewRoman);

		itemTimesNewRoman.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFont("TimesNewRoman");
			}
		});

		JMenuItem itemCalibri = new JMenuItem("Calibri");
		itemFont.add(itemCalibri);

		itemCalibri.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFont("Calibri");
			}
		});

		JMenuItem itemAlgerian = new JMenuItem("Algerian");
		itemFont.add(itemAlgerian);

		itemAlgerian.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setFont("Algerian");
			}
		});

		itemFontSize = new JMenu("Font Size");
		formatMenu.add(itemFontSize);

		JMenuItem sizee = new JMenuItem("8");
		itemFontSize.add(sizee);
		sizee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(8);
			}
		});

		JMenuItem size = new JMenuItem("12");
		itemFontSize.add(size);
		size.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(12);
			}
		});

		JMenuItem size1 = new JMenuItem("16");
		itemFontSize.add(size1);
		size1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(16);
			}
		});

		JMenuItem size2 = new JMenuItem("20");
		itemFontSize.add(size2);
		size2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(20);
			}
		});

		JMenuItem size3 = new JMenuItem("24");
		itemFontSize.add(size3);
		size3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(24);
			}
		});

		JMenuItem size4 = new JMenuItem("28");
		itemFontSize.add(size4);
		size4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(28);
			}
		});

		JMenuItem size8 = new JMenuItem("32");
		itemFontSize.add(size8);
		size8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(32);
			}
		});

		JMenuItem size5 = new JMenuItem("36");
		itemFontSize.add(size5);
		size5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(36);
			}
		});

		JMenuItem size6 = new JMenuItem("40");
		itemFontSize.add(size6);
		size6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(40);
			}
		});

		JMenuItem size7 = new JMenuItem("44");
		itemFontSize.add(size7);
		size7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setFontSize(44);
			}
		});

	}
	
	
	public void createCommandPromotItems() {
		itemCMD = new JMenuItem("  Open CMD      ");
		commandPromptMenu.add(itemCMD);

		itemCMD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (Openpath != null) {
						Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start" }, null, new File(Openpath));
						System.out.println("Launched CMD");
					}

					else {
						Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start" }, null, null);

						System.out.println("null");
					}

				} catch (IOException e2) {
					System.out.println("Could not launch CMD");

				}
			}

		});
	}
}
