
package composer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.PopupMenu;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
//import jm.JMC;
//import jm.music.data.Note;
//import jm.util.Play;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Composer extends JFrame implements KeyListener,ActionListener,ItemListener{

        public JPanel leftPanel;
        public JPanel topPanel,music_control_panel;
        public JLabel masinko_lb,krar_lb,washint_lb,band_lb;
        public JMenuBar menu;
        public JMenu file,help,setting;
        public JMenuItem save,copy,cut,about_us,about_apk,add_inst_menu;
        public JLabel add_inst_lb;
        public JLabel merchaLabel= new JLabel("piano");
        public JPopupMenu pop;
        public JTextArea pop_txt;
        public JPanel inst_panel1,inst_panel2,inst_panel3;
        public static  JPanel[] insturment_panel = new JPanel[6];
        public static JCheckBox[] insturment_selector = new JCheckBox[6];
        
        public static int insturment_panel_counter=0;
        public static int insturment_panel_coordinate=0;
         
        public String[] instruments={"piano","Kirar","washint","Masinko","kebero","pad","brass","saxphone","bass","Audio"};
        public  JComboBox music_staff = new JComboBox(instruments);
        
        
        public TimerPanel timerPanel;        

        String Memrecha="piano";
       // Note[] note = new Note[23];
        
        Timer timer ;
        public static int x=0;
        public JProgressBar progress;
        
      JButton play_btn,pause_btn,remove_btn,copy_btn,metro_btn,add_inst_btn;  
      JButton remove_panel_insturment;  
      ImageIcon play_img,pause_img,remove_img,copy_img,metro_img,add_inst_img;
    JButton[] keys = new JButton[28];
    JButton[] upperKeys = new JButton[21];
    JLayeredPane panel = getLayeredPane();
    JLayeredPane comboLayer = getLayeredPane();
       
    public Composer(){
        
        setLayout(null);
        
         pause_img = new ImageIcon("src/image/Pause.png"); 
        remove_img = new ImageIcon("src/image/Remove.png");
        copy_img =  new ImageIcon("src/image/Copy.png");
        metro_img = new ImageIcon("src/image/metro.png");
        play_btn = new JButton(play_img);
       
        
        menu = new JMenuBar();
        setJMenuBar(menu);
        file = new JMenu("File");
        save = new JMenuItem("save");
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        add_inst_menu = new JMenuItem("Add instrunmnet");
        file.add(save);
        file.add(cut);
           file.add(copy);
        file.addSeparator();
        file.add(add_inst_menu);
        pop = new JPopupMenu();
        pop.add(copy);
        pop.add(cut);
        pop.add(add_inst_menu);
        pop_txt = new JTextArea(10,50);
        pop_txt.setComponentPopupMenu(pop);
        
     
        menu.add(file);
        help = new JMenu("Help");
        about_us = new JMenuItem("About us");
        about_apk = new JMenuItem("About APK");
        help.add(about_us);
        help.add(about_apk);
        menu.add(help);
        setting = new JMenu("Setting");
        menu.add(setting);
        
        add_inst_lb = new JLabel("Add instrument");
        add_inst_btn = new JButton(new ImageIcon("src/image/plus.png"));
        remove_panel_insturment = new JButton("remove",remove_img);
        
        add_inst_btn.setBounds(260, 600, 40, 40);
        remove_panel_insturment.setBounds(260, 650,150, 40);
        add_inst_lb.setBounds(308, 600, 100, 30);
        music_staff.setBounds(410,600,100,30);
        
       add(add_inst_btn);
       add(add_inst_lb);
       add(music_staff);
       add(remove_panel_insturment);
       
       add_inst_btn.addActionListener(this);
       remove_panel_insturment.addActionListener(this);
       inst_panel1 = new JPanel();
        inst_panel2 = new JPanel();
       inst_panel3 = new JPanel();
    
        
       leftPanel = new JPanel();
       topPanel = new JPanel();
       music_control_panel = new JPanel(new GridLayout(2,5) );
       
       
       progress = new JProgressBar(0,1320);
      // progress.setIndeterminate(true);
       topPanel.setBackground(new Color(80,96,116));//new Color(80,96,116)
       leftPanel.setBackground(new Color(61,87,105));
       music_control_panel.setBackground(Color.white);
        play_img = new ImageIcon( "src/image/Play.png");
         
       
        
       pause_btn = new JButton(pause_img );
       remove_btn = new JButton ( remove_img);
       copy_btn = new JButton(copy_img);
          metro_btn = new JButton(metro_img);;
  
       
       //leftPanel.setSize(600,800);
       timerPanel =new TimerPanel();
       
        JScrollPane scroll = new JScrollPane(timerPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(510, 180, 846, 311);
        leftPanel.setBounds(0, 0, 250, 750);
        topPanel.setBounds(250, 0, 1260, 180);
        merchaLabel.setBounds(920, 500, 200, 50);
        music_control_panel.setBounds(700, 120, 300, 60);
        
         music_control_panel.add(new JLabel("Play"));
          music_control_panel.add(new JLabel("Pause"));
           music_control_panel.add(new JLabel("Remove"));
            music_control_panel.add(new JLabel("Copy"));
             music_control_panel.add(new JLabel("Metro"));
        music_control_panel.add(play_btn);
          music_control_panel.add( pause_btn );
           music_control_panel.add( remove_btn  );
             music_control_panel.add( copy_btn );
              music_control_panel.add(  metro_btn );
              
           masinko_lb = new JLabel();
           krar_lb = new JLabel();
           washint_lb = new JLabel();
           band_lb = new JLabel();
           ImageIcon masinko = new ImageIcon("src/image/masinko.jpg");
           Image mas = masinko.getImage().getScaledInstance(250, 240, Image.SCALE_SMOOTH);
           masinko_lb.setIcon(new ImageIcon(mas));
           masinko_lb.setBounds(0,50, 260, 190);
        
           
             ImageIcon  krar= new ImageIcon("src/image/krar.jpg");
           Image kra = krar.getImage().getScaledInstance(250, 240, Image.SCALE_SMOOTH);
            krar_lb .setIcon(new ImageIcon(kra));
            krar_lb .setBounds(0,265, 260, 190);
            
             ImageIcon  washint= new ImageIcon("src/image/washint.jpg");
           Image washi = washint.getImage().getScaledInstance(250, 240, Image.SCALE_SMOOTH);
            washint_lb.setIcon(new ImageIcon(washi));
           washint_lb.setBounds(0,480, 260, 190);
           
           
             
           
           
        add(masinko_lb);
         add(krar_lb);
         add( washint_lb);
        add(music_control_panel);
        add(scroll);
        add(leftPanel);
        add(topPanel);
        add(merchaLabel);
    
     //  merchaLabel.setBackground(Color.green);
       merchaLabel.setForeground(Color.red);
       merchaLabel.setFont(new Font("serif",Font.BOLD,24));
        
        int j=510;
       
         panel.setLayout(null);
         for(int i=0;i<28;i++){
            
            keys[i]=new JButton();
            keys[i].setBackground(Color.WHITE);
            keys[i].setBounds(j, 570, 30,150);
            panel.add(keys[i],new Integer(1));
            j+=30;
        }
     
         

 int k=1,index=0;
j=510;
          for(int i=1;i<9;i++){
            if(k%2==0){
                for(int eve=0;eve<3;eve++){
            upperKeys[index]=new JButton();
            upperKeys[index].setBackground(Color.BLACK);
            upperKeys[index].setBounds(j+20, 570, 20,100);
            panel.add(upperKeys[index],new Integer(2));
            index++;
            
            j+=30;
                
                }
                j+=30;
            }
         else{
                
           for(int eve=0;eve<2;eve++){
                     upperKeys[index]=new JButton();
                     upperKeys[index].setBackground(Color.BLACK);
                     upperKeys[index].setBounds(j+20, 570, 20,100);
                  panel.add(upperKeys[index],new Integer(2));
                   index++;
                 j+=30;
              }  
               j+=30; 
            }
           k++; 
            
        }   

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
       for(int i=0;i<28;i++){
           keys[i].addKeyListener(this);
       }
       
     for(int i=0;i<20;i++){
          upperKeys[i].addKeyListener(this);
   }
       
       

  
        
       
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       setSize(1500,750);
       // pack();
        
                   }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int i=e.getKeyCode();
                     
      if(e.getKeyCode()==KeyEvent.VK_Q){
                       AudioStream music;
                       InputStream path=null;
          try {
              if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\c4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\c3.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\c3.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\c3.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\c3.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\c3.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\c3.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\c3.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\c3.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                       
                  keys[14].doClick();
         }
               else if(e.getKeyCode()==KeyEvent.VK_W){
                         
                                    AudioStream music;
                                    InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\d3.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\d3.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\d3.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\d3.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\d3.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\d3.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\d3.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\d3.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
             
                         keys[15].doClick();
                  }
               else if(e.getKeyCode()==KeyEvent.VK_E){
                              AudioStream music;      
                              InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\e4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\d3.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\e3.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\e3.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\e3.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\e3.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\e3.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\e3.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\e3.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
             
                        
            keys[16].doClick();
                  }
              
               else if(e.getKeyCode()==KeyEvent.VK_R){
      
                          keys[17].doClick();
                                      AudioStream music;
               InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\f4..wav"));
  ///   f3 is the right but
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\f3.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\f3.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\f3.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\f3.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\f3.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\f3.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\f3.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\f3.wav"));

              music = new AudioStream(path);
              
              AudioPlayer.player.start(music);
            
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
             
                  }
               else if(e.getKeyCode()==KeyEvent.VK_T){
                            keys[18].doClick();
                                                       AudioStream music;
                 InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\g3.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\g3.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\g3.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\g3.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\g3.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\g3.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\g3.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\g3.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_Y){
                              keys[19].doClick();
                                                         AudioStream music;
               InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\a3.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\a3.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\a3.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\a3.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\a3.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\a3.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\a3.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\a3.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
             
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_U){
                              keys[20].doClick();
                                                         AudioStream music;
                InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\b3.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\b3.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\b3.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\b3.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\b3.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\b3.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\b3.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\b3.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_I){
                              keys[21].doClick();
                                                         AudioStream music;
                InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\c4.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\c4.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\c4.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\c4.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\c4.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\c4.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\c4.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\c4.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                              
                  } 
               else if(e.getKeyCode()==KeyEvent.VK_O){
                                keys[22].doClick();
                                                           AudioStream music;
                InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\d4.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\d4.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\d4.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\d4.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\d4.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\d4.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\d4.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\d4.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                                
                  }
               else if(e.getKeyCode()==KeyEvent.VK_P){
                              keys[23].doClick();
                                                         AudioStream music;
                InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\e4.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\e4.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\e4.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\e4.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\e4.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\e4.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\e4.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\e4.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               
                 else if(e.getKeyCode()==KeyEvent.VK_OPEN_BRACKET){
                               keys[24].doClick();
                                                            AudioStream music;
           InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\f4.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\f4.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\f4.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\f4.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\f4.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\f4.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\f4.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\f4.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
                   else if(e.getKeyCode()==KeyEvent.VK_CLOSE_BRACKET){
                                 keys[25].doClick();
                                                            AudioStream music;
           InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\g4.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\g4.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\g4.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\g4.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\g4.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\g4.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\g4.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\g4.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
                 
                     else if(e.getKeyCode()==KeyEvent.VK_BACK_SLASH){
                                 keys[26].doClick();
                                                            AudioStream music;
           InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\a4.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\a4.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\a4.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\a4.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\a4.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\a4.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\a4.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\a4.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
                   
                   
               else if(e.getKeyCode()==KeyEvent.VK_Z){
                               keys[0].doClick();
                                                          AudioStream music;
                InputStream path=null;
          try {
              
                if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\c1.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\c1.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\c1.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\c1.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\c1.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\c1.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\c1.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\c1.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_X){
                          keys[1].doClick();
                                                          AudioStream music;
              InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\d1.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\d1.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\d1.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\d1.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\d1.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\d1.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\d1.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\d1.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_C){
                            keys[2].doClick();
                                                          AudioStream music;
               InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\e1.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\e1.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\e1.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\e1.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\e1.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\e1.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\e1.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\e1.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_V){
                               keys[3].doClick();
                                                          AudioStream music;
                InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\f1.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\f1.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\f1.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\f1.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\f1.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\f1.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\f1.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\f1.wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_B){
                              keys[4].doClick();
                                                          AudioStream music;
                         InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\g1.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\g1.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\g1.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\g1.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\g1.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\g1.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\g1.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\g1.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_N){
                               keys[5].doClick();
                                                          AudioStream music;
                                 InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\a1.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\a1.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\a1.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\a1.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\a1.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\a1.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\a1.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\a1.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_M){
                                   keys[6].doClick();
                                                          AudioStream music;
                         InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\b1.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\b1.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\b1.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\b1.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\b1.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\b1.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\b1.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\b1.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               
     else if(e.getKeyCode()==KeyEvent.VK_COMMA){
                                 keys[7].doClick();
                                                            AudioStream music;
                                InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\c2.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\c2.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\c2.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\c2.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\c2.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\c2.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\c2.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\c2.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
     else if(e.getKeyCode()==KeyEvent.VK_PERIOD){
                                 keys[8].doClick();
                                                            AudioStream music;
                                  InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\d2.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\d2.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\d2.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\d2.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\d2.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\d2.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\d2.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\d2.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
     else if(e.getKeyCode()==KeyEvent.VK_SLASH){
                                keys[9].doClick();
                                                            AudioStream music;
                                 InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\e2.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\e2.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\e2.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\e2.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\e2.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\e2.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\e2.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\e2.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
     else if(e.getKeyCode()==KeyEvent.VK_SHIFT){
                                keys[10].doClick();
                                                            AudioStream music;
                                 InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\f2.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\f2.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\f2.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\f2.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\f2.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\f2.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\f2.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\f2.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
     else if(e.getKeyCode()==KeyEvent.VK_NUMPAD1){
                                keys[11].doClick();
                                                            AudioStream music;
                                 InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\g2.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\g2.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\g2.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\g2.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\g2.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\g2.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\g2.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\g2.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
     else if(e.getKeyCode()==KeyEvent.VK_NUMPAD2){
                                keys[12].doClick();
                                                            AudioStream music;
                                 InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\a2.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\a2.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\a2.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\a2.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\a2.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\a2.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\a2.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\a2.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
     else if(e.getKeyCode()==KeyEvent.VK_NUMPAD3){
                                keys[13].doClick();
                                                            AudioStream music;
                                 InputStream path=null;
          try {
              
  if(Memrecha.equalsIgnoreCase("piano"))
              path = new FileInputStream(new File("src\\composer\\piano\\d4..wav"));
           //   else if(Memrecha.equalsIgnoreCase("kirar"))
             //       path = new FileInputStream(new File("src\\composer\\kirar\\b2.wav"));
              else if(Memrecha.equalsIgnoreCase("kebero"))
                    path = new FileInputStream(new File("src\\composer\\kebero\\b2.wav"));
              else if(Memrecha.equalsIgnoreCase("saxphone"))
                    path = new FileInputStream(new File("src\\composer\\sax\\b2.wav"));
              else if(Memrecha.equalsIgnoreCase("washint"))
                    path = new FileInputStream(new File("src\\composer\\washint\\b2.wav"));
           //   else if(Memrecha.equalsIgnoreCase("masinko"))
          //          path = new FileInputStream(new File("src\\composer\\masinko\\b2.wav"));
              else if(Memrecha.equalsIgnoreCase("bass"))
                    path = new FileInputStream(new File("src\\composer\\bass\\b2.wav"));
              else if(Memrecha.equalsIgnoreCase("brass"))
                    path = new FileInputStream(new File("src\\composer\\brass\\b2.wav"));
              else if(Memrecha.equalsIgnoreCase("pad"))
                    path = new FileInputStream(new File("src\\composer\\pad\\b2.wav"));
              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               
               
               
               //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
               //////////UPPER KEYS EVENT
               
               else if(e.getKeyCode()==KeyEvent.VK_2){
                                 upperKeys[5].doClick();
                                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\4c#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_3){
                                 upperKeys[6].doClick();
                                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\4d#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_3){
                                 upperKeys[7].doClick();
                                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\4d#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_5){
                                 upperKeys[7].doClick();
                                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\4f#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_6){
                                    upperKeys[8].doClick();
                                                               AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\4g#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_7){
                                    upperKeys[9].doClick();
                                           AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\4a#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                                    
                  }
               else if(e.getKeyCode()==KeyEvent.VK_9){
                                    upperKeys[10].doClick();
                                           AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\3c#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  }
               else if(e.getKeyCode()==KeyEvent.VK_0){
                                     upperKeys[11].doClick();
                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\3d#..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  } 
              
             
               else if(e.getKeyCode()==KeyEvent.VK_S){
                                     upperKeys[0].doClick();
                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\c5s..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  } 
               else if(e.getKeyCode()==KeyEvent.VK_D){
                                     upperKeys[1].doClick();
                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\d5s..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  } 
               else if(e.getKeyCode()==KeyEvent.VK_H){
                                     upperKeys[2].doClick();
                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\f5s..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  } 
               else if(e.getKeyCode()==KeyEvent.VK_J){
                                     upperKeys[3].doClick();
                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\g5s..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  } 
               else if(e.getKeyCode()==KeyEvent.VK_K){
                                     upperKeys[4].doClick();
                                            AudioStream music;
          try {
              
          InputStream path = new FileInputStream(new File("src\\composer\\piano\\a5s..wav"));

              music = new AudioStream(path);
              AudioPlayer.player.start(music);
          } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
          }
                  } 
              
            
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
  int y;
    @Override
  
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== add_inst_btn)
        {     
         
            if(insturment_panel_counter<6){
                insturment_selector[insturment_panel_counter] = new JCheckBox(music_staff.getSelectedItem().toString());
            insturment_panel[insturment_panel_counter] = new JPanel();
           
            insturment_panel[insturment_panel_counter].setBounds(250,180+insturment_panel_coordinate,250,60);
            insturment_selector[insturment_panel_counter] .setBounds(410,180+insturment_panel_coordinate,85,30);
            
            insturment_panel[insturment_panel_counter].setBackground(Color.BLUE);
            
            ItemHandler handle = new ItemHandler();
            insturment_selector[insturment_panel_counter].addItemListener(handle);
         
            comboLayer.add( insturment_selector[insturment_panel_counter],1);
               comboLayer.add(insturment_panel[insturment_panel_counter],2);
              //   insturment_selector[insturment_panel_counter ].addItemListener(new audio());
               insturment_panel_counter++;
            repaint();
            insturment_panel_coordinate+=62;}
            else
            {
                JOptionPane.showMessageDialog(this,"you can not add more than 6 insturment");
            }
        }
        else if(e.getSource()==remove_panel_insturment){
      
                
            
               if(insturment_panel_counter>=0){
                   if(insturment_panel_counter>0)
                   {          insturment_panel_counter--;
                  comboLayer. remove(insturment_selector[insturment_panel_counter]);
                  comboLayer. remove(insturment_panel[insturment_panel_counter]);
                
            
                repaint();
                insturment_panel_coordinate -= 62;
                   }
                   else {
                       JOptionPane.showMessageDialog(this,"no insturment added"); 
                   }
               }
           
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
                                       ////////////////////////////////////          timer class
    public class TimerPanel extends JPanel  {
           TimerPanel(){
               timer = new Timer(100,new TimerListener());
          //  timer.start();
           }
        public void paintComponent(Graphics g2){
            
            Graphics2D g =(Graphics2D) g2;
         g.clearRect(0, 0, getSize().width, getSize().height);
          // g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
             g.setPaint(Color.LIGHT_GRAY);
         for(int i=0;i<getSize().width;i+=40){
             
             g.draw(new Line2D.Float(i,0,i,getSize().height));
         }
         
         for(int i=0;i<1320;i+=40){ 
             g.drawString(""+i, i, 10);
         }
           g.setPaint(Color.ORANGE);
            g.setStroke(new BasicStroke(4));
            g.draw(new Line2D.Double(x, 0, x,getSize().height));
        }
        
   class TimerListener implements ActionListener {
      int y=0;
      public void actionPerformed(ActionEvent e) {
          y++;
          x+=10;
          progress.setValue(x);
          progress.setStringPainted(true);
          progress.setString(getSize().height+"");
          if(x>getSize().width){
             
              x=0;
          }
          if(y==1000){
            timer.stop();
        }
        repaint();
        
      }
    }
        
    }
    
    public  class ItemHandler implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
        
             JCheckBox box =(JCheckBox)e.getItem();
       if(box.getText().equalsIgnoreCase("piano")){
           {    
           if(box.isSelected())
           {
               Memrecha = "piano";
           }
           }
         
        }
      else if(box.getText().equalsIgnoreCase("kebero")){
           {    
           if(box.isSelected())
           {
               Memrecha = "kebero";
           }
           }
         
        }
     else if(box.getText().equalsIgnoreCase("pad")){
           {    
           if(box.isSelected())
           {
                Memrecha = "pad";
           }
           }
         
        }
      else if(box.getText().equalsIgnoreCase("Audio")){
           {    
           if(box.isSelected())
           {
               Memrecha = "Audio";
           }
           }
         
        }
      else if(box.getText().equalsIgnoreCase("brass")){
           {    
           if(box.isSelected())
           {
              Memrecha = "brass";
           }
           }
         
        }
      else if(box.getText().equalsIgnoreCase("bass")){
           {    
           if(box.isSelected())
           {
                Memrecha = "bass";
           }
           }
         
        }
      else if(box.getText().equalsIgnoreCase("saxphone")){
           {    
           if(box.isSelected())
           {
               Memrecha = "saxphone";
           }
           }
         
        }
       else if(box.getText().equalsIgnoreCase("kirar")){
           {    
           if(box.isSelected())
           {
               Memrecha = "kirar";
           }
           }
         
        }
      else if(box.getText().equalsIgnoreCase("washint")){
           {    
           if(box.isSelected())
           {
                Memrecha = "washint";
           }
           }
         
        }
      else if(box.getText().equalsIgnoreCase("masinko")){
           {    
           if(box.isSelected())
           {
               Memrecha = "masinko";
           }
           }
         
        }
       merchaLabel.setText(Memrecha);
    
}}
   
    public static void main(String[] args) {
   new Composer();
    }
    
}
