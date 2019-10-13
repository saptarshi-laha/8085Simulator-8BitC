package bit8c;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;


import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIData {
	
	private JFrame f;
	private JTextArea carea, asmarea;
	private JLabel clabel, asmlabel;
	private JButton clear, convert;
	private JScrollPane cpane, apane;	
	
	GUIData(){
		
		ButtonListener listen = new ButtonListener();
		
		this.f = new JFrame("C To 8085 ASM Compiler");
		this.f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ico.png")));
		this.f.setLayout(null);
		this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.f.setBounds(0,0, 1120, 900);
		this.f.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.f.setLocation(screenSize.width/2-f.getSize().width/2, screenSize.height/2-f.getSize().height/2);
		this.f.getContentPane().setBackground(Color.BLACK);
		
		this.asmarea = new JTextArea();
		this.asmarea.setText("");
		this.asmarea.setLineWrap(true);
		this.asmarea.setVisible(true);
		
		this.apane = new JScrollPane(this.asmarea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.apane.setBounds(830 ,40, 260, 760);
		this.apane.setVisible(true);
		
		this.carea = new JTextArea();
		this.carea.setText("");
		this.carea.setLineWrap(true);
		this.carea.setVisible(true);
		
		this.cpane = new JScrollPane(this.carea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.cpane.setBounds(10,40, 800, 760);
		this.cpane.setVisible(true);
		
		this.clabel = new JLabel("C Program :");
		this.clabel.setForeground(Color.WHITE);
		this.clabel.setBounds(10,20,100,20);
		
		this.asmlabel = new JLabel("ASM Program:");
		this.asmlabel.setForeground(Color.WHITE);
		this.asmlabel.setBounds(830, 20, 100, 20);
		
		
		this.clear = new JButton("Clear");
		this.clear.setBounds(10,805,100,40);
		this.clear.setActionCommand("CLR");
		this.clear.addActionListener(listen);
		
		this.convert = new JButton("Convert");
		this.convert.setBounds(710,805,100,40);
		this.convert.setActionCommand("CON");
		this.convert.addActionListener(listen);
		
		this.f.add(this.clabel);
		this.f.add(this.asmlabel);
		this.f.add(this.cpane);
		this.f.add(this.apane);
		this.f.add(this.clear);
		this.f.add(this.convert);
		this.f.setVisible(true);
		
	}
	
	class ButtonListener implements ActionListener{
		
        public void actionPerformed(ActionEvent e){
        
        	if(e.getActionCommand().equals("CLR")) {
        		asmarea.setText("");
        		carea.setText("");
        	}
        	else if(e.getActionCommand().equals("CON")) {
        		C28085ASMTranslator con = new C28085ASMTranslator();
        		String finalProgram = con.mainDecoder(carea.getText());
        		asmarea.setText(finalProgram);
        	}
        	
        }
        
        }

}
