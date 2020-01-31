/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package composer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;

/**
 *
 * @author EYOBED
 */
public  class audio extends Composer implements ItemListener {
    
 
     @Override
        public void itemStateChanged(ItemEvent e) {
        
          if(e.getItem()==insturment_selector[insturment_panel_counter ]){
           
           if(e.getStateChange()==ItemEvent.SELECTED){
          // JOptionPane.showMessageDialog(null, "piano");
           
           }
         
       }
        }
   public void brass(){
       
   }
   public void sax (){
       
   }
 
   public void masinko(){
       
   }
   public void krara(){
       
   }
   public void kebero(){
       
   }
   public void audio(){
       
   }
   public void pad(){
       
   }
   public void bass(){
       
   }
   public void washint(){
       
   }
  
     public static  void piano(){
       
         JOptionPane.showMessageDialog(null, "piano");
      
      
       
       
   }

    

   
    
}
