package Notepad_clone;

import java.awt.FileDialog;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Notepad {

	JTextArea area;
	JFrame frame;
	JMenuBar menuBar;
	JMenu fileMenu, formatMenu, editMenu, commandPromptMenu;
	JMenuItem itemNew, itemNewWindow, itemOpen, itemSave, itemSaveAs, itemExit;// for fileMenu items
	JMenuItem itemWordWrap, itemFont, itemFontSize;// for format menu item
	JMenuItem itemCMD;// for command prompt
	String Openpath ;
	String Openfile ;

	public static void main(String[] args) {

		Notepad np = new Notepad();
	}

	public Notepad() {
		createFrame();
		createArea();
		createScrollBars();
		createMenuBar();
		createfileMenuItems();
		createFormatItems();
		createCommandPromotItems();
	}

	public void createFrame() {
		frame = new JFrame("Notepad");
		// frame.setLayout(null);
		frame.setSize(500, 300);
		Image icon = Toolkit.getDefaultToolkit().getImage("R:\\swingss\\noteapd.png");
		frame.setIconImage(icon);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void createArea() {
		area = new JTextArea();
		frame.add(area);
	}

	public void createScrollBars() {
		JScrollPane pane = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(pane);
	}
	
	public void writeDataToFile(String fileName,String path) {
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
			}
			catch (NullPointerException npe) {
			}
		}
	}
	
	public void writeData(String path,String file) {
		BufferedWriter br = null ;
		try {
			 br = new BufferedWriter(new FileWriter(path+file)) ;
		     String text = area.getText();
		     br.write(text) ;
		     br.close() ;
		} catch (IOException e1) {
		  System.out.println("Data can't be written");
		}
		
		finally {
			try {
				br.close();
			} catch (IOException e1) {

					System.out.println("File canot be closed");
			}
			catch (NullPointerException npe) {

				System.out.println("File not found to closed");
			}
		}
	}

	public void createMenuBar() {
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		editMenu = new JMenu("Edit");
		menuBar.add(editMenu);

		formatMenu = new JMenu("Format");
		menuBar.add(formatMenu);

		commandPromptMenu = new JMenu("Command Prompt");
		menuBar.add(commandPromptMenu);
	}

	public void createfileMenuItems() {
		
		itemNew = new JMenuItem("New");
		fileMenu.add(itemNew);

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
				if(file != null)
				{
					frame.setTitle(path+file);
					
					Openfile = file ;
					Openpath = path ;
				}

				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(path + file));
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
					}
					catch (NullPointerException npe) {
					}
				}
			}
		});
		

		itemSave = new JMenuItem("Save");
		fileMenu.add(itemSave);
		itemSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			  if(Openfile != null && Openpath != null)
			  {
				  writeDataToFile(Openfile, Openpath) ;
			  }
			  
			  else
			  {
				  FileDialog fd = new FileDialog(frame,"SaveAs",FileDialog.SAVE);
					fd.setVisible(true);
					String path = fd.getDirectory();
					String file =fd.getFile();
					
					if(path != null & file != null) {
						
						writeData(path,file) ;
					}
			  }
				
			}
		});

		itemSaveAs = new JMenuItem("SaveAs");
		fileMenu.add(itemSaveAs);
		itemSaveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				FileDialog fd = new FileDialog(frame,"SaveAs",FileDialog.SAVE);
				fd.setVisible(true);
				String path = fd.getDirectory();
				String fileName =fd.getFile();
				
				if(fileName != null & path != null)
				{
					BufferedWriter br = null ;
					try {
						 br = new BufferedWriter(new FileWriter(path+fileName)) ;
					     String text = area.getText();
					     //br.newLine();
					     br.write(text) ;
					     br.close() ;
					} catch (IOException e1) {
					  System.out.println("Data can't be written");
					}
					
					finally {
						try {
							br.close();
						} catch (IOException e1) {

								System.out.println("File canot be closed");
						}
						catch (NullPointerException npe) {

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
	

	public void createFormatItems() {
		itemWordWrap = new JMenuItem("Word Wrap");
		formatMenu.add(itemWordWrap);

		itemFont = new JMenuItem("Font");
		formatMenu.add(itemFont);

		itemFontSize = new JMenuItem("Font Size");
		formatMenu.add(itemFontSize);
	}

	public void createCommandPromotItems() {
		itemCMD = new JMenuItem("  Open CMD      ");
		commandPromptMenu.add(itemCMD);
	}
}
