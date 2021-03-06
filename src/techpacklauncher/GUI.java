/**
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation; either version 3
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
*
*/

package truelauncher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
<<<<<<< HEAD
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
=======
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import truelauncher.gcomponents.TButton;
import truelauncher.gcomponents.TComboBox;
import truelauncher.gcomponents.TLabel;
import truelauncher.gcomponents.TProgressBar;
import truelauncher.gcomponents.TTextField;

@SuppressWarnings("serial")
public class GUI extends JPanel {

	private GUI thisclass = this;
	
	public TTextField nickfield;
<<<<<<< HEAD
	public TComboBox ramfield;
=======
	public TTextField ramfield;
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
	public TComboBox listclients; 
	public TButton launch;
	public TProgressBar pbar;
	public TButton download;
	public TComboBox listdownloads;
	public LauncherUpdateDialog lu;
	public JFrame f;
	
	int posX=0,posY=0;
 
	public GUI(JFrame frame)
	{
		try {
    		AllSettings.loadConfig();
			this.f = frame;
			this.setLayout(null);
			//border
			this.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.GRAY));
			//init drag
			JLabel drag = new JLabel();
			drag.setBounds(0, 0, AllSettings.w, 15);
			drag.setOpaque(false);
			drag.setBackground(new Color(0,0,0,0));
			drag.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					posX=e.getX();
					posY=e.getY();
				}
			});
			drag.addMouseMotionListener(new MouseAdapter() {
			     public void mouseDragged(MouseEvent evt) {
					f.setLocation(evt.getXOnScreen()-posX,evt.getYOnScreen()-posY);
			     }
			});
			this.add(drag);
			//init GUI
			initUI();
			//load fields values
			loadTextFields();
			//init GlassPane that will be used to darken main frame when launcher update is available
		    f.setGlassPane(
		    		new JComponent() {
		        public void paintComponent(Graphics g) {
		            g.setColor(new Color(0, 0, 0, 150));
		            g.fillRect(0, 0, AllSettings.w, AllSettings.h);
		            super.paintComponent(g);
		   	    }
		   	});
		}
		catch (Exception e)
		{
			LauncherUtils.logError(e);
		}
	}  
 
 
 
 
     private void initUI() {
    	 initTextInputFieldsAndLabels();
    	 initStartButton();
    	 initDownloadCenter();
    	 showLauncherUpdateWindow();
    	 showCloseMinimizeButton();
     }
     

     //block 1 (nickname chooser)
     private void initTextInputFieldsAndLabels()
     {
    	 int y = AllSettings.h - 110;
    	 int levelw = 30;
      	 int widgw = 220;
      	 
      	 
      	 JPanel tifields = new JPanel();
      	 tifields.setLayout(null);
      	 tifields.setBounds(levelw, y, widgw, 95);
      	 tifields.setOpaque(false);
      	 tifields.setBackground(new Color(0,0,0,0));
      	 
      	 //Плашка объясениния
    	 TLabel expbarset = new TLabel();
    	 expbarset.setBounds(0,0,widgw,25);
    	 expbarset.setBackgroundImage(GUI.class.getResourceAsStream(AllSettings.explainimage));
    	 expbarset.setText("Ustawienia podstawowe");
      	 expbarset.setHorizontalAlignment(TButton.CENTER);
    	 tifields.add(expbarset);
    	 
    	 
    	 //Плашка ника
    	 int lnw = 80;
    	 int lnh = 20;
    	 TLabel labelnick = new TLabel();
    	 labelnick.setBounds(0,25,lnw,lnh);
    	 labelnick.setBackgroundImage(GUI.class.getResourceAsStream(AllSettings.labelimage));
    	 labelnick.setText("Nick");
      	 labelnick.setHorizontalAlignment(TButton.CENTER);
    	 tifields.add(labelnick);

    	 //Поле ника
    	 int inw = 140;
    	 int inh = 20;
    	 nickfield = new TTextField();
    	 nickfield.setBounds(lnw,25,inw,inh);
<<<<<<< HEAD
    	 nickfield.setText("Wpisz Nick");
=======
    	 nickfield.setText("NoNickName");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
    	 nickfield.setHorizontalAlignment(TButton.CENTER);
    	 tifields.add(nickfield);

    	 //Плашка памяти
    	 int lrw = 80;
    	 int lrh = 20;
    	 TLabel labelram = new TLabel();
    	 labelram.setBounds(0,45,lrw,lrh);
    	 labelram.setText("Pamięć");
      	 labelram.setHorizontalAlignment(TButton.CENTER);
    	 labelram.setBackgroundImage(GUI.class.getResourceAsStream(AllSettings.labelimage));
    	 tifields.add(labelram);

       	 //Поле памяти
    	 int irw = 140;
    	 int irh = 20;
<<<<<<< HEAD
    	 //ramfield = new TTextField();
    	 //ramfield.setBounds(lrw,45,irw,irh);
    	 //ramfield.setText("1024");
      	 //ramfield.setHorizontalAlignment(TButton.CENTER);

         ramfield = new TComboBox();

         long maxMemory = 1024;
         String architecture = System.getProperty("sun.arch.data.model", "32");
         boolean bit64 = architecture.equals("64");

         try {
             OperatingSystemMXBean osInfo = ManagementFactory.getOperatingSystemMXBean();
             if (osInfo instanceof com.sun.management.OperatingSystemMXBean) {
                 maxMemory = ((com.sun.management.OperatingSystemMXBean) osInfo).getTotalPhysicalMemorySize() / 1024 / 1024;
             }
         } catch (Throwable t) {
         }
         maxMemory = Math.max(512, maxMemory);

         if (maxMemory >= Memory.MAX_32_BIT_MEMORY && !bit64) {
             ramfield.setToolTipText("<html>Wybierz ilość pamięci przydzielonej do Minecrafta<br/>" + "Masz więcej niż 1.5GB dostępnej pamięci, ale<br/>"
                     + "żeby ją użyć musisz mieć java 64bit.</html>");
         } else {
             ramfield.setToolTipText("<html>Wybierz ilość pamięci przydzielonej do Minecrafta<br/>" + "Więcej to nie zawsze lepiej.<br/>"
                     + "Więcej pamięci bardziej obciąża twój procesor.</html>");
         }

         if (!bit64) {
             maxMemory = Math.min(Memory.MAX_32_BIT_MEMORY, maxMemory);
         }
         System.out.println("Wykryto maksymalną dostępną pamięć: " + maxMemory + " mb");

         for (Memory mem : Memory.memoryOptions) {
             if (maxMemory >= mem.getMemoryMB()) {
                 ramfield.addItem(String.valueOf(mem.getMemoryMB()));
             }
         }

         ramfield.setBounds(lrw,45,irw,irh);
         ramfield.setAlignmentY(JComboBox.CENTER_ALIGNMENT);

=======
    	 ramfield = new TTextField();
    	 ramfield.setBounds(lrw,45,irw,irh);
    	 ramfield.setText("400");
      	 ramfield.setHorizontalAlignment(TButton.CENTER);
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
    	 tifields.add(ramfield);
    	 
    	 //Кнопка сохранить
    	 TButton save = new TButton();
    	 save.setText("Zapisz ustawienia");
    	 save.setBounds(0,65,widgw,30);
    	 save.addActionListener(
    			 new ActionListener() {
    				 @Override
    				 public void actionPerformed(ActionEvent e) {
    					 saveTextFields();
    				 }
         });
    	 tifields.add(save);
    	 
    	 this.add(tifields);
     }

     
     //block 2 (clients start)
     private void initStartButton()
     {
    	 int y = AllSettings.h - 110;
    	 int levelw = 250;
      	 int widgw = 240;
    	 
      	JPanel sb = new JPanel();
      	sb.setLayout(null);
      	sb.setBounds(levelw, y , widgw, 95);
      	sb.setOpaque(false);
      	sb.setBackground(new Color(0,0,0,0));
      	 
      	 //плашка объяснений
      	TLabel expbarset = new TLabel();
       	expbarset.setBounds(0,0,widgw,25);
<<<<<<< HEAD
      	expbarset.setText("Wybierz Paczkę");
=======
      	expbarset.setText("wybór klienta");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
      	expbarset.setHorizontalAlignment(TButton.CENTER);
    	expbarset.setBackgroundImage(GUI.class.getResourceAsStream(AllSettings.explainimage));
      	 
    	sb.add(expbarset);
    	 
    	listclients = new TComboBox();
 	    List<String> servclientslist = AllSettings.getClientsList();
  	    for (String servname : servclientslist)
 	    {
 	    	listclients.addItem(servname);
 	    }
  	    listclients.setBounds(0,25,widgw,30);
  	    listclients.setAlignmentY(JComboBox.CENTER_ALIGNMENT);
 	    listclients.addActionListener(
 	    		new ActionListener() {
 	               @Override
 	               public void actionPerformed(ActionEvent e) {
 	      			LauncherUtils.checkClientJarExist(thisclass);
 	              }
 	           });
 	   sb.add(listclients);
 	    
 	     //кнопка запуска майна
       launch = new TButton();
       launch.setBounds(0, 55, widgw, 40);
       launch.setText("Uruchom Minecraft");
       launch.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 	ClientLaunch.launchMC(thisclass);
             }
       });
	   LauncherUtils.checkClientJarExist(thisclass);
       sb.add(launch);

   
       this.add(sb);
     }

     //block 3 (clients download)
     private void initDownloadCenter()
     {
    	 int y = AllSettings.h - 110;
    	 int levelw = 490;
      	 int widgw = 220;
      	 
      	 JPanel dc = new JPanel();
      	 dc.setLayout(null);
      	 dc.setBounds(levelw, y, widgw, 95);
      	 dc.setOpaque(false);
      	 dc.setBackground(new Color(0,0,0,0));
      	 
    	 TLabel expbarset = new TLabel();
    	 expbarset.setBounds(0,0,widgw,25);
    	 expbarset.setBackgroundImage(GUI.class.getResourceAsStream(AllSettings.explainimage));
<<<<<<< HEAD
    	 expbarset.setText("Pobieranie Paczek");
=======
    	 expbarset.setText("Pobieranie klientów");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
    	 expbarset.setHorizontalAlignment(TLabel.CENTER);
    	 dc.add(expbarset);
    	 
     	listdownloads = new TComboBox();
 	    List<String> servdownloadlist = AllSettings.getClientListDownloads();
  	    for (String servname : servdownloadlist)
 	    {
  	    	listdownloads.addItem(servname);
 	    }
  	    listdownloads.setBounds(0,25,widgw,30);
  	    listdownloads.addActionListener(new ActionListener() {
    		 @Override
             public void actionPerformed(ActionEvent e) {
<<<<<<< HEAD
    			 download.setText("Pobieranie Paczki");
=======
    			 download.setText("Pobieranie Klienta");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
    			 pbar.setValue(0);
    			 download.setEnabled(true);
    		 }
    	});
  	    dc.add(listdownloads);
  	    
  	  	pbar = new TProgressBar();
  	  	pbar.setBounds(0,55,widgw, 16);
  	    dc.add(pbar);
  	  
    	download = new TButton();
    	download.setBounds(0, 70, widgw, 25);
<<<<<<< HEAD
    	download.setText("Pobierz Paczke");
=======
    	download.setText("Pobierz klienta");
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
    	download.setHorizontalAlignment(TButton.CENTER);
             	download.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 listdownloads.setEnabled(false);

            	 new ClientUpdateThread(thisclass).start();
            	 download.setEnabled(false);
             }
         });
  	    dc.add(download);
    	
    	this.add(dc);
     }
     
     //close and minimize buttonis block
     private void showCloseMinimizeButton()
     {
      	 JPanel cmb = new JPanel();
      	 cmb.setLayout(null);
      	 cmb.setBounds(AllSettings.w - 75,15,60,25);
      	 
      	 TButton minimize = new TButton();
      	 minimize.setBounds(0,0,25,25);
      	 minimize.setBackgroundImage(AllSettings.hide);
      	 minimize.addActionListener(new ActionListener()
      	 {
      		 @Override 
      		 public void actionPerformed(ActionEvent e)
      		 {
      			 f.setExtendedState(JFrame.ICONIFIED);
      		 }
      	 });
      	 cmb.add(minimize);
      	 
      	 TButton close = new TButton();
      	 close.setBounds(35, 0, 25, 25);
      	 close.setBackgroundImage(AllSettings.close);
      	 close.addActionListener(new ActionListener()
      	 {
      		 @Override 
      		 public void actionPerformed(ActionEvent e)
      		 {
      			 System.exit(0);
      		 }
      	 });
      	 cmb.add(close);
      	 cmb.setOpaque(false);
      	 cmb.setBackground(new Color(0,0,0,0));
      	 
      	 this.add(cmb);
     }

     //Init laucnher updater
     private void showLauncherUpdateWindow()
     {
    	 lu = new LauncherUpdateDialog(thisclass);
    	 new LVersionChecker(thisclass).start();
    	 
     }     
     
     //load nick and ram from file
     private void loadTextFields()
     {
         try {
             String ps = LauncherUtils.getDir();
             if ((new File(ps + File.separator + AllSettings.getLauncherConfigFolderPath()+ File.separator + "config")).exists()) {
                 Scanner inFile = new Scanner(new File(ps + File.separator + AllSettings.getLauncherConfigFolderPath()+ File.separator + "config"));
                 nickfield.setText(inFile.nextLine());
<<<<<<< HEAD
                 ramfield.setSelectedItem(inFile.nextLine());
                 //ramfield.setSelectedIndex(Memory.getMemoryIndexFromId(Integer.parseInt(inFile.nextLine())));
=======
                 ramfield.setText(inFile.nextLine());
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
                 inFile.close();
             }
         } catch (Exception e) {
         }
     }
     
     //save nick and ram to file
     private void saveTextFields()
     {
       	 String ps = LauncherUtils.getDir();
    	 new File(ps + File.separator + AllSettings.getLauncherConfigFolderPath()).mkdirs();
   
    	 try {
    		 PrintWriter wrt = new PrintWriter(new File(ps+ File.separator + AllSettings.getLauncherConfigFolderPath()+File.separator+"config"));
    		 wrt.println(nickfield.getText());
<<<<<<< HEAD
    		 wrt.println(ramfield.getSelectedItem().toString());
             //wrt.println(Memory.getIdFromMemoryIndex(ramfield.getSelectedItem().toString()));
=======
    		 wrt.println(ramfield.getText());
>>>>>>> edc5842e0a91c2ae43615ebbc95033ca0bae6fbb
    		 wrt.flush();
    		 wrt.close();
    	 } catch (Exception e) {}
     }
     

     @Override
	 public void paintComponent(Graphics g) {
    	 try {
    		 Image bg = ImageIO.read(GUI.class.getResourceAsStream(AllSettings.bgimage));
    		 bg = bg.getScaledInstance(AllSettings.w, AllSettings.h, Image.SCALE_SMOOTH);
			g.drawImage(bg, 0, 0, null);
    	 } catch (IOException e) {
			e.printStackTrace();
    	 }
     }
     
     
}     

     

     


