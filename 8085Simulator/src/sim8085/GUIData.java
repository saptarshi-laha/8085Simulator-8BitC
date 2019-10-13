package sim8085;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class GUIData {
	
	

	public class JTextFieldLimit extends PlainDocument {
	private static final long serialVersionUID = -3731012990946177985L;
	private int limit;

	  JTextFieldLimit(int limit) {
	   super();
	   this.limit = limit;
	   }

	  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
	    if (str == null) return;

	    if ((getLength() + str.length()) <= limit) {
	      super.insertString(offset, str, attr);
	    }
	  }
	}
	
	private JFrame frame;
	private JTextArea opcode_area, asm_area;
	public static JTextArea error_area;
	public static JTextField port_valuetf;
	private JTextField regAtf, regBtf, regCtf, regDtf, regEtf, regHtf, regLtf, regSPtf, regPCtf, 
	regSFtf, regAFtf, regCFtf, regPFtf, regZFtf, start_pointtf, ori_tf;
	public static JTextField port_numberintf, port_numberouttf;
	private JTextField loc0tf, loc1tf, loc2tf, loc3tf, loc4tf, loc5tf, loc6tf, loc7tf, loc8tf, loc9tf, loc10tf, loc11tf, loc12tf, loc13tf, loc14tf, loc15tf;
	private JLabel opcode_area_label, asm_area_label, output_area_label, regAlb, regBlb, regClb, regDlb, ori_label,
	regElb, regHlb, regLlb, regSPlb, regPClb, regSFlb, regAFlb, regCFlb, regPFlb, regZFlb, regPSWlb, regPSWvallb, port_lb, port_numlb, port_vallb, start_point;
	private JLabel loc0, loc1, loc2, loc3, loc4, loc5, loc6, loc7, loc8, loc9, loc10, loc11, loc12, loc13, loc14, loc15;
	private JButton compile, step_fwd, run, save, open, translate, bind_to_port, populate, set_ori;
	private JScrollPane opcode_pane, asm_pane, error_pane;
	public static JComboBox<String> port_box;
	public static Chip8085 chip;
	private int orgpoint = -1, start_p = 0;
	public static byte port_data[];
	public static char psw = 0;
	public static int system_port[];
	private boolean accessed = false;
	public static int writeTo = -1;
	
	GUIData(){
		GUIData.chip = new Chip8085();
		
		GUIData.port_data = new byte[256];
		
		for(int i = 0; i<256; i++) {
			GUIData.port_data[i] = 0;
		}
		
		GUIData.system_port = new int[2];
		
		for(int i = 0; i<2; i++) {
			GUIData.system_port[i] = -1;
		}
		
		ButtonListener listen = new ButtonListener();
		Main bind = new Main();
		
		GUIData.error_area = new JTextArea();
		GUIData.error_area.setText("");
		GUIData.error_area.setEnabled(false);
		GUIData.error_area.setDisabledTextColor(Color.RED);
		GUIData.error_area.setLineWrap(true);
		GUIData.error_area.setVisible(true);
		
		this.frame = new JFrame("8085 Simulator");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("ico.png")));
		this.frame.setLayout(null);
		this.frame.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.frame.setBounds(0,0, 1120, 900);
		this.frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2);
		this.frame.getContentPane().setBackground(Color.BLACK);
		
		this.opcode_area = new JTextArea();
		this.opcode_area.setText("");
		this.opcode_area.setEnabled(false);
		this.opcode_area.setDisabledTextColor(Color.BLACK);
		this.opcode_area.setLineWrap(true);
		this.opcode_area.setVisible(true);
		
		this.asm_area = new JTextArea();
		this.asm_area.setText("");
		this.asm_area.setLineWrap(true);
		this.asm_area.setVisible(true);
		
		this.opcode_pane = new JScrollPane(this.opcode_area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.opcode_pane.setBounds(500,20,150,675);
		this.opcode_pane.setVisible(true);
		
		this.asm_pane = new JScrollPane(this.asm_area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.asm_pane.setBounds(6,20,480,675);
		this.asm_pane.setVisible(true);
		
		this.error_pane = new JScrollPane(GUIData.error_area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.error_pane.setBounds(6,760,645,100);
		this.error_pane.setVisible(true);
		
		this.opcode_area_label = new JLabel("OPCODE");
		this.opcode_area_label.setForeground(Color.WHITE);
		this.opcode_area_label.setBounds(553, 0, 60, 20);
		
		this.asm_area_label = new JLabel("ASM 8085");
		this.asm_area_label.setForeground(Color.WHITE);
		this.asm_area_label.setBounds(230, 0, 60, 20);
		
		this.output_area_label = new JLabel("OUTPUT");
		this.output_area_label.setForeground(Color.WHITE);
		this.output_area_label.setBounds(6, 741, 60, 20);
		
		this.translate = new JButton("Translate");
		this.translate.addActionListener(listen);
		this.translate.setActionCommand("Translate");
		this.translate.setBounds(6,698,100,30);
		
		this.compile = new JButton("Compile");
		this.compile.setEnabled(false);
		this.compile.addActionListener(listen);
		this.compile.setActionCommand("Compile");
		this.compile.setBounds(110,698,100,30);
		
		this.save = new JButton("Save");
		this.save.addActionListener(listen);
		this.save.setActionCommand("Save");
		this.save.setBounds(215,698,100,30);
		
		this.open = new JButton("Open");
		this.open.addActionListener(listen);
		this.open.setActionCommand("Load");
		this.open.setBounds(320,698,100,30);
		
		this.run = new JButton("Run");
		this.run.setEnabled(false);
		this.run.addActionListener(listen);
		this.run.setActionCommand("Run");
		this.run.setBounds(425, 698,100,30);
		
		this.step_fwd = new JButton("Step Forward");
		this.step_fwd.setEnabled(false);
		this.step_fwd.addActionListener(listen);
		this.step_fwd.setActionCommand("Step");
		this.step_fwd.setBounds(530, 698,120,30);
		
		this.regAlb = new JLabel("REGISTER A / ACCUMULATOR");
		this.regAlb.setForeground(Color.WHITE);
		this.regAlb.setBounds(792,0, 170, 20);
		
		this.regAtf = new JTextField();
		this.regAtf.setDocument(new JTextFieldLimit(2));
		this.regAtf.setHorizontalAlignment(JTextField.CENTER);
		this.regAtf.setBounds(790,20, 170, 20);
		this.regAtf.setActionCommand("RA");
		this.regAtf.addActionListener(listen);
		
		this.regBlb = new JLabel("REGISTER B");
		this.regBlb.setForeground(Color.WHITE);
		this.regBlb.setBounds(690,40, 170, 20);
		
		
		this.regClb = new JLabel("REGISTER C");
		this.regClb.setForeground(Color.WHITE);
		this.regClb.setBounds(1000,40, 170, 20);
		
		this.regBtf = new JTextField();
		this.regBtf.setDocument(new JTextFieldLimit(2));
		this.regBtf.setHorizontalAlignment(JTextField.CENTER);
		this.regBtf.setBounds(660,60,130,20);
		this.regBtf.setActionCommand("RB");
		this.regBtf.addActionListener(listen);
		
		this.regCtf = new JTextField();
		this.regCtf.setDocument(new JTextFieldLimit(2));
		this.regCtf.setHorizontalAlignment(JTextField.CENTER);
		this.regCtf.setBounds(965,60,130,20);
		this.regCtf.setActionCommand("RC");
		this.regCtf.addActionListener(listen);
		
		this.regDlb = new JLabel("REGISTER D");
		this.regDlb.setForeground(Color.WHITE);
		this.regDlb.setBounds(690,80, 170, 20);
		
		this.regDtf = new JTextField();
		this.regDtf.setDocument(new JTextFieldLimit(2));
		this.regDtf.setHorizontalAlignment(JTextField.CENTER);
		this.regDtf.setBounds(660,100,130,20);
		this.regDtf.setActionCommand("RD");
		this.regDtf.addActionListener(listen);
		
		this.regElb = new JLabel("REGISTER E");
		this.regElb.setForeground(Color.WHITE);
		this.regElb.setBounds(1000,80, 170, 20);
		
		this.regEtf = new JTextField();
		this.regEtf.setDocument(new JTextFieldLimit(2));
		this.regEtf.setHorizontalAlignment(JTextField.CENTER);
		this.regEtf.setBounds(965,100,130,20);
		this.regEtf.setActionCommand("RE");
		this.regEtf.addActionListener(listen);
		
		this.regHlb = new JLabel("REGISTER H");
		this.regHlb.setForeground(Color.WHITE);
		this.regHlb.setBounds(690,120, 170, 20);
		
		this.regHtf = new JTextField();
		this.regHtf.setDocument(new JTextFieldLimit(2));
		this.regHtf.setHorizontalAlignment(JTextField.CENTER);
		this.regHtf.setBounds(660,140,130,20);
		this.regHtf.setActionCommand("RH");
		this.regHtf.addActionListener(listen);
		
		this.regLlb = new JLabel("REGISTER L");
		this.regLlb.setForeground(Color.WHITE);
		this.regLlb.setBounds(1000,120, 170, 20);
		
		this.regLtf = new JTextField();
		this.regLtf.setDocument(new JTextFieldLimit(2));
		this.regLtf.setHorizontalAlignment(JTextField.CENTER);
		this.regLtf.setBounds(965,140,130,20);
		this.regLtf.setActionCommand("RL");
		this.regLtf.addActionListener(listen);
		
		this.regSPlb = new JLabel("REGISTER SP");
		this.regSPlb.setForeground(Color.WHITE);
		this.regSPlb.setBounds(687,160, 170, 20);
				
		this.regSPtf = new JTextField();
		this.regSPtf.setDocument(new JTextFieldLimit(4));
		this.regSPtf.setHorizontalAlignment(JTextField.CENTER);
		this.regSPtf.setBounds(660,180,130,20);
		this.regSPtf.setActionCommand("RSP");
		this.regSPtf.addActionListener(listen);
		
		this.regPClb = new JLabel("REGISTER PC");
		this.regPClb.setForeground(Color.WHITE);
		this.regPClb.setBounds(997,160, 170, 20);
		
		this.regPCtf = new JTextField();
		this.regPCtf.setDocument(new JTextFieldLimit(4));
		this.regPCtf.setHorizontalAlignment(JTextField.CENTER);
		this.regPCtf.setBounds(965,180,130,20);
		this.regPCtf.setActionCommand("RPC");
		this.regPCtf.addActionListener(listen);
		
		this.regPSWlb = new JLabel("PROGRAM STATUS WORD");
		this.regPSWlb.setForeground(Color.WHITE);
		this.regPSWlb.setBounds(805,210, 170, 20);
		
		this.regPSWvallb = new JLabel();
		this.regPSWvallb.setForeground(Color.WHITE);
		this.regPSWvallb.setBounds(823,230, 170, 20);
		
		this.regAFlb = new JLabel("AUXILIARY CARRY FLAG");
		this.regAFlb.setForeground(Color.WHITE);
		this.regAFlb.setBounds(658,240, 170, 20);
		
		this.regAFtf = new JTextField();
		this.regAFtf.setDocument(new JTextFieldLimit(1));
		this.regAFtf.setHorizontalAlignment(JTextField.CENTER);
		this.regAFtf.setBounds(660,260,130,20);
		this.regAFtf.setActionCommand("AF");
		this.regAFtf.addActionListener(listen);
		
		this.regCFlb = new JLabel("CARRY FLAG");
		this.regCFlb.setForeground(Color.WHITE);
		this.regCFlb.setBounds(1000,240, 170, 20);
		
		this.regCFtf = new JTextField();
		this.regCFtf.setDocument(new JTextFieldLimit(1));
		this.regCFtf.setHorizontalAlignment(JTextField.CENTER);
		this.regCFtf.setBounds(965,260,130,20);
		this.regCFtf.setActionCommand("CF");
		this.regCFtf.addActionListener(listen);
		
		this.regPFlb = new JLabel("PARITY FLAG");
		this.regPFlb.setForeground(Color.WHITE);
		this.regPFlb.setBounds(690,280, 170, 20);
		
		this.regPFtf = new JTextField();
		this.regPFtf.setDocument(new JTextFieldLimit(1));
		this.regPFtf.setHorizontalAlignment(JTextField.CENTER);
		this.regPFtf.setBounds(660,300,130,20);		
		this.regPFtf.setActionCommand("PF");
		this.regPFtf.addActionListener(listen);
		
		this.regZFlb = new JLabel("ZERO FLAG");
		this.regZFlb.setForeground(Color.WHITE);
		this.regZFlb.setBounds(1005,280, 170, 20);
		
		this.regZFtf = new JTextField();
		this.regZFtf.setDocument(new JTextFieldLimit(1));
		this.regZFtf.setHorizontalAlignment(JTextField.CENTER);
		this.regZFtf.setBounds(965,300,130,20);
		this.regZFtf.setActionCommand("ZF");
		this.regZFtf.addActionListener(listen);
		
		this.regSFlb = new JLabel("SIGN FLAG");
		this.regSFlb.setForeground(Color.WHITE);
		this.regSFlb.setBounds(850,280, 170, 20);
		
		this.regSFtf = new JTextField();
		this.regSFtf.setDocument(new JTextFieldLimit(1));
		this.regSFtf.setHorizontalAlignment(JTextField.CENTER);
		this.regSFtf.setBounds(815,300,130,20);
		this.regSFtf.setActionCommand("SF");
		this.regSFtf.addActionListener(listen);
		
		this.port_lb = new JLabel("PORT");
		this.port_lb.setForeground(Color.WHITE);
		this.port_lb.setBounds(690,820,60,20);
		
		GUIData.port_box = new JComboBox<String>();
		GUIData.port_box.setBounds(660, 840, 100, 20);
		for(int i = 0; i < 256; i++) {
			GUIData.port_box.addItem(Integer.toString(i));
		}
		GUIData.port_box.setSelectedIndex(0);
		GUIData.port_box.setActionCommand("CHANGED_PORT");
		GUIData.port_box.addActionListener(bind);
		
		this.port_vallb = new JLabel("VALUE");
		this.port_vallb.setForeground(Color.WHITE);
		this.port_vallb.setBounds(800, 820, 60, 20);
		
		GUIData.port_valuetf = new JTextField();
		GUIData.port_valuetf.setDocument(new JTextFieldLimit(2));
		GUIData.port_valuetf.setHorizontalAlignment(JTextField.CENTER);
		GUIData.port_valuetf.setText(String.format("%02X", GUIData.port_data[GUIData.port_box.getSelectedIndex()]).toUpperCase());
		GUIData.port_valuetf.setBounds(770,840, 100, 20);
		
		this.port_numlb = new JLabel("SYSTEM PORT NUMBER");
		this.port_numlb.setForeground(Color.WHITE);
		this.port_numlb.setBounds(890, 820, 150, 20);
		
		GUIData.port_numberintf = new JTextField();
		GUIData.port_numberintf.setDocument(new JTextFieldLimit(5));
		GUIData.port_numberintf.setHorizontalAlignment(JTextField.CENTER);
		GUIData.port_numberintf.setText(Integer.toString(GUIData.system_port[0]));
		GUIData.port_numberintf.setBounds(884,840, 70, 20);
		GUIData.port_numberintf.setToolTipText("Listen Port");
		
		GUIData.port_numberouttf = new JTextField();
		GUIData.port_numberouttf.setDocument(new JTextFieldLimit(5));
		GUIData.port_numberouttf.setHorizontalAlignment(JTextField.CENTER);
		GUIData.port_numberouttf.setText(Integer.toString(GUIData.system_port[1]));
		GUIData.port_numberouttf.setBounds(959,840, 70, 20);
		GUIData.port_numberouttf.setToolTipText("Send Port");
		
		this.bind_to_port = new JButton("Bind");
		this.bind_to_port.addActionListener(bind);
		this.bind_to_port.setActionCommand("Bind");
		this.bind_to_port.setBounds(1040,820,60,40);
		
		this.start_point = new JLabel("STARTING POINT (16 VALUES WILL BE POPULATED)");
		this.start_point.setForeground(Color.WHITE);
		this.start_point.setBounds(740,360, 300, 20);
		
		this.start_pointtf = new JTextField();
		this.start_pointtf.setDocument(new JTextFieldLimit(4));
		this.start_pointtf.setHorizontalAlignment(JTextField.CENTER);
		this.start_pointtf.setText("0000");
		this.start_pointtf.setBounds(740,380, 150, 20);
		
		this.populate = new JButton("Populate");
		this.populate.setEnabled(true);
		this.populate.addActionListener(listen);
		this.populate.setActionCommand("Populate");
		this.populate.setBounds(895,380,137,20);
		
		this.loc0 = new JLabel();
		this.loc0.setForeground(Color.WHITE);
		this.loc0.setBounds(693,440, 150, 20);
		
		this.loc0tf = new JTextField();
		this.loc0tf.setDocument(new JTextFieldLimit(2));
		this.loc0tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc0tf.setBounds(668,460, 80, 20);
		this.loc0tf.setActionCommand("L0");
		this.loc0tf.addActionListener(listen);
		
		this.loc1 = new JLabel();
		this.loc1.setForeground(Color.WHITE);
		this.loc1.setBounds(808,440, 150, 20);
		
		this.loc1tf = new JTextField();
		this.loc1tf.setDocument(new JTextFieldLimit(2));
		this.loc1tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc1tf.setBounds(780,460, 80, 20);
		this.loc1tf.setActionCommand("L1");
		this.loc1tf.addActionListener(listen);
		
		this.loc2 = new JLabel();
		this.loc2.setForeground(Color.WHITE);
		this.loc2.setBounds(919,440, 150, 20);
		
		this.loc2tf = new JTextField();
		this.loc2tf.setDocument(new JTextFieldLimit(2));
		this.loc2tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc2tf.setBounds(892,460, 80, 20);
		this.loc2tf.setActionCommand("L2");
		this.loc2tf.addActionListener(listen);
		
		this.loc3 = new JLabel();
		this.loc3.setForeground(Color.WHITE);
		this.loc3.setBounds(1030,440, 150, 20);
		
		this.loc3tf = new JTextField();
		this.loc3tf.setDocument(new JTextFieldLimit(2));
		this.loc3tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc3tf.setBounds(1004,460, 80, 20);
		this.loc3tf.setActionCommand("L3");
		this.loc3tf.addActionListener(listen);
		
		this.loc4 = new JLabel();
		this.loc4.setForeground(Color.WHITE);
		this.loc4.setBounds(693,500, 150, 20);
		
		this.loc4tf = new JTextField();
		this.loc4tf.setDocument(new JTextFieldLimit(2));
		this.loc4tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc4tf.setBounds(668,520, 80, 20);
		this.loc4tf.setActionCommand("L4");
		this.loc4tf.addActionListener(listen);
		
		this.loc5 = new JLabel();
		this.loc5.setForeground(Color.WHITE);
		this.loc5.setBounds(808,500, 150, 20);
		
		this.loc5tf = new JTextField();
		this.loc5tf.setDocument(new JTextFieldLimit(2));
		this.loc5tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc5tf.setBounds(780,520, 80, 20);
		this.loc5tf.setActionCommand("L5");
		this.loc5tf.addActionListener(listen);
		
		this.loc6 = new JLabel();
		this.loc6.setForeground(Color.WHITE);
		this.loc6.setBounds(919,500, 150, 20);
		
		this.loc6tf = new JTextField();
		this.loc6tf.setDocument(new JTextFieldLimit(2));
		this.loc6tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc6tf.setBounds(892,520, 80, 20);
		this.loc6tf.setActionCommand("L6");
		this.loc6tf.addActionListener(listen);
		
		this.loc7 = new JLabel();
		this.loc7.setForeground(Color.WHITE);
		this.loc7.setBounds(1030,500, 150, 20);
		
		this.loc7tf = new JTextField();
		this.loc7tf.setDocument(new JTextFieldLimit(2));
		this.loc7tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc7tf.setBounds(1004,520, 80, 20);
		this.loc7tf.setActionCommand("L7");
		this.loc7tf.addActionListener(listen);
		
		this.loc8 = new JLabel();
		this.loc8.setForeground(Color.WHITE);
		this.loc8.setBounds(693,560, 150, 20);
		
		this.loc8tf = new JTextField();
		this.loc8tf.setDocument(new JTextFieldLimit(2));
		this.loc8tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc8tf.setBounds(668,580, 80, 20);
		this.loc8tf.setActionCommand("L8");
		this.loc8tf.addActionListener(listen);
		
		this.loc9 = new JLabel();
		this.loc9.setForeground(Color.WHITE);
		this.loc9.setBounds(808,560, 150, 20);
		
		this.loc9tf = new JTextField();
		this.loc9tf.setDocument(new JTextFieldLimit(2));
		this.loc9tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc9tf.setBounds(780,580, 80, 20);
		this.loc9tf.setActionCommand("L9");
		this.loc9tf.addActionListener(listen);
		
		this.loc10 = new JLabel();
		this.loc10.setForeground(Color.WHITE);
		this.loc10.setBounds(919,560, 150, 20);
		
		this.loc10tf = new JTextField();
		this.loc10tf.setDocument(new JTextFieldLimit(2));
		this.loc10tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc10tf.setBounds(892,580, 80, 20);
		this.loc10tf.setActionCommand("L10");
		this.loc10tf.addActionListener(listen);
		
		this.loc11 = new JLabel();
		this.loc11.setForeground(Color.WHITE);
		this.loc11.setBounds(1030,560, 150, 20);
		
		this.loc11tf = new JTextField();
		this.loc11tf.setDocument(new JTextFieldLimit(2));
		this.loc11tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc11tf.setBounds(1004,580, 80, 20);
		this.loc11tf.setActionCommand("L11");
		this.loc11tf.addActionListener(listen);
		
		this.loc12 = new JLabel();
		this.loc12.setForeground(Color.WHITE);
		this.loc12.setBounds(693,620, 150, 20);
		
		this.loc12tf = new JTextField();
		this.loc12tf.setDocument(new JTextFieldLimit(2));
		this.loc12tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc12tf.setBounds(668,640, 80, 20);
		this.loc12tf.setActionCommand("L12");
		this.loc12tf.addActionListener(listen);
		
		this.loc13 = new JLabel();
		this.loc13.setForeground(Color.WHITE);
		this.loc13.setBounds(808,620, 150, 20);
		
		this.loc13tf = new JTextField();
		this.loc13tf.setDocument(new JTextFieldLimit(2));
		this.loc13tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc13tf.setBounds(780,640, 80, 20);
		this.loc13tf.setActionCommand("L13");
		this.loc13tf.addActionListener(listen);
		
		this.loc14 = new JLabel();
		this.loc14.setForeground(Color.WHITE);
		this.loc14.setBounds(919,620, 150, 20);
		
		this.loc14tf = new JTextField();
		this.loc14tf.setDocument(new JTextFieldLimit(2));
		this.loc14tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc14tf.setBounds(892,640, 80, 20);
		this.loc14tf.setActionCommand("L14");
		this.loc14tf.addActionListener(listen);
		
		this.loc15 = new JLabel();
		this.loc15.setForeground(Color.WHITE);
		this.loc15.setBounds(1030,620, 150, 20);
		
		this.loc15tf = new JTextField();
		this.loc15tf.setDocument(new JTextFieldLimit(2));
		this.loc15tf.setHorizontalAlignment(JTextField.CENTER);
		this.loc15tf.setBounds(1004,640, 80, 20);
		this.loc15tf.setActionCommand("L15");
		this.loc15tf.addActionListener(listen);
		
		this.ori_label = new JLabel("ORIGIN");
		this.ori_label.setForeground(Color.WHITE);
		this.ori_label.setBounds(740,730, 50, 20);
		
		this.ori_tf = new JTextField();
		this.ori_tf.setDocument(new JTextFieldLimit(4));
		this.ori_tf.setText("0000");
		this.ori_tf.setHorizontalAlignment(JTextField.CENTER);
		this.ori_tf.setBounds(790,730, 120, 20);
		
		this.set_ori = new JButton("Set Origin");
		this.set_ori.setEnabled(true);
		this.set_ori.addActionListener(listen);
		this.set_ori.setActionCommand("ORI");
		this.set_ori.setBounds(920,730,100,20);
		
		this.regAtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegA()));
		this.regBtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegB()));
		this.regCtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegC()));
		this.regDtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegD()));
		this.regEtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegE()));
		this.regHtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()));
		this.regLtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegL()));
		this.regPCtf.setText(GUIData.chip.formatCharToHex(GUIData.chip.getProgramCounter()));
		this.regSPtf.setText(GUIData.chip.formatCharToHex(GUIData.chip.getStackPointer()));
		this.regSFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getSignFlag()));
		this.regCFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getCarryFlag()));
		this.regAFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getAuxCarryFlag()));
		this.regPFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getParityFlag()));
		this.regZFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getZeroFlag()));
		this.regPSWvallb.setText(String.format("%16s", Integer.toBinaryString(GUIData.psw & 0xFFFF)).replace(' ', '0'));
		
		byte memdata[] = new byte[16];
		start_p = Integer.parseInt(start_pointtf.getText(), 16);
		
		for(int i = 0; i < 16; i++) {        		
			memdata[i] = GUIData.chip.getMemory(start_p);
			start_p++;
		}        		
		
		loc0tf.setText(GUIData.chip.formatByteToHex(memdata[0]));
		loc1tf.setText(GUIData.chip.formatByteToHex(memdata[1]));
		loc2tf.setText(GUIData.chip.formatByteToHex(memdata[2]));
		loc3tf.setText(GUIData.chip.formatByteToHex(memdata[3]));
		loc4tf.setText(GUIData.chip.formatByteToHex(memdata[4]));
		loc5tf.setText(GUIData.chip.formatByteToHex(memdata[5]));
		loc6tf.setText(GUIData.chip.formatByteToHex(memdata[6]));
		loc7tf.setText(GUIData.chip.formatByteToHex(memdata[7]));
		loc8tf.setText(GUIData.chip.formatByteToHex(memdata[8]));
		loc9tf.setText(GUIData.chip.formatByteToHex(memdata[9]));
		loc10tf.setText(GUIData.chip.formatByteToHex(memdata[10]));
		loc11tf.setText(GUIData.chip.formatByteToHex(memdata[11]));
		loc12tf.setText(GUIData.chip.formatByteToHex(memdata[12]));
		loc13tf.setText(GUIData.chip.formatByteToHex(memdata[13]));
		loc14tf.setText(GUIData.chip.formatByteToHex(memdata[14]));
		loc15tf.setText(GUIData.chip.formatByteToHex(memdata[15]));
		
		start_p = start_p - 16;
		
		loc0.setText(String.format("%04X", start_p+0).toUpperCase());
		loc1.setText(String.format("%04X", start_p+1).toUpperCase());
		loc2.setText(String.format("%04X", start_p+2).toUpperCase());
		loc3.setText(String.format("%04X", start_p+3).toUpperCase());
		loc4.setText(String.format("%04X", start_p+4).toUpperCase());
		loc5.setText(String.format("%04X", start_p+5).toUpperCase());
		loc6.setText(String.format("%04X", start_p+6).toUpperCase());
		loc7.setText(String.format("%04X", start_p+7).toUpperCase());
		loc8.setText(String.format("%04X", start_p+8).toUpperCase());
		loc9.setText(String.format("%04X", start_p+9).toUpperCase());
		loc10.setText(String.format("%04X", start_p+10).toUpperCase());
		loc11.setText(String.format("%04X", start_p+11).toUpperCase());
		loc12.setText(String.format("%04X", start_p+12).toUpperCase());
		loc13.setText(String.format("%04X", start_p+13).toUpperCase());
		loc14.setText(String.format("%04X", start_p+14).toUpperCase());
		loc15.setText(String.format("%04X", start_p+15).toUpperCase());
		
		this.frame.add(this.set_ori);
		this.frame.add(this.ori_tf);
		this.frame.add(this.ori_label);
		this.frame.add(GUIData.port_numberouttf);
		this.frame.add(this.loc15tf);
		this.frame.add(this.loc15);
		this.frame.add(this.loc14tf);
		this.frame.add(this.loc14);
		this.frame.add(this.loc13tf);
		this.frame.add(this.loc13);
		this.frame.add(this.loc12tf);
		this.frame.add(this.loc12);
		this.frame.add(this.loc11tf);
		this.frame.add(this.loc11);
		this.frame.add(this.loc10tf);
		this.frame.add(this.loc10);
		this.frame.add(this.loc9tf);
		this.frame.add(this.loc9);
		this.frame.add(this.loc8tf);
		this.frame.add(this.loc8);
		this.frame.add(this.loc7tf);
		this.frame.add(this.loc7);
		this.frame.add(this.loc6tf);
		this.frame.add(this.loc6);
		this.frame.add(this.loc5tf);
		this.frame.add(this.loc5);
		this.frame.add(this.loc4tf);
		this.frame.add(this.loc4);
		this.frame.add(this.loc3tf);
		this.frame.add(this.loc3);
		this.frame.add(this.loc2tf);
		this.frame.add(this.loc2);
		this.frame.add(this.loc1tf);
		this.frame.add(this.loc1);
		this.frame.add(this.loc0tf);
		this.frame.add(this.loc0);
		this.frame.add(this.populate);
		this.frame.add(this.start_pointtf);
		this.frame.add(this.start_point);
		this.frame.add(this.regSFtf);
		this.frame.add(this.regPFtf);
		this.frame.add(this.regZFtf);
		this.frame.add(this.regSFlb);
		this.frame.add(this.regZFlb);
		this.frame.add(this.regPFlb);
		this.frame.add(this.regCFtf);
		this.frame.add(this.regCFlb);
		this.frame.add(this.regAFtf);
		this.frame.add(this.regAFlb);
		this.frame.add(this.regPSWvallb);
		this.frame.add(this.regPSWlb);
		this.frame.add(this.regSPtf);
		this.frame.add(this.regPCtf);
		this.frame.add(this.regPClb);
		this.frame.add(this.regSPlb);
		this.frame.add(this.regHtf);
		this.frame.add(this.regLtf);
		this.frame.add(this.regDtf);
		this.frame.add(this.regEtf);
		this.frame.add(this.regHlb);
		this.frame.add(this.regLlb);
		this.frame.add(this.regDlb);
		this.frame.add(this.regElb);
		this.frame.add(this.regCtf);
		this.frame.add(this.regBtf);
		this.frame.add(this.regClb);
		this.frame.add(this.regBlb);
		this.frame.add(this.regAtf);
		this.frame.add(this.regAlb);
		this.frame.add(this.bind_to_port);
		this.frame.add(GUIData.port_numberintf);
		this.frame.add(this.port_numlb);
		this.frame.add(GUIData.port_valuetf);
		this.frame.add(this.port_vallb);
		this.frame.add(this.port_lb);
		this.frame.add(GUIData.port_box);
		this.frame.add(this.step_fwd);
		this.frame.add(this.run);
		this.frame.add(this.open);
		this.frame.add(this.save);
		this.frame.add(this.compile);
		this.frame.add(this.translate);
		this.frame.add(this.output_area_label);
		this.frame.add(this.asm_area_label);
		this.frame.add(this.opcode_area_label);
	    this.frame.add(this.error_pane);
		this.frame.add(this.asm_pane);
		this.frame.add(this.opcode_pane);
		this.frame.setVisible(true);
	}
	
	public void setTFs() {

		while(accessed == true) {
			
		}
		
		accessed = true;
		
		regAtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegA()));
		regBtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegB()));
		regCtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegC()));
		regDtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegD()));
		regEtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegE()));
		regHtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()));
		regLtf.setText(GUIData.chip.formatByteToHex(GUIData.chip.getRegL()));
		regPCtf.setText(GUIData.chip.formatCharToHex(GUIData.chip.getProgramCounter()));
		regSPtf.setText(GUIData.chip.formatCharToHex(GUIData.chip.getStackPointer()));
		regSFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getSignFlag()));
		regCFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getCarryFlag()));
		regAFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getAuxCarryFlag()));
		regPFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getParityFlag()));
		regZFtf.setText(GUIData.chip.formatBooleanToHex(GUIData.chip.getZeroFlag()));
		
		GUIData.psw = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) * 0x100);
		
		if(GUIData.chip.getSignFlag() == true) {
			
			GUIData.psw = (char) (GUIData.psw | 0b0000000010000000);
			
		}
		else {
			
			GUIData.psw = (char) (GUIData.psw & 0b1111111101111111);
			
		}
		
		if(GUIData.chip.getZeroFlag() == true) {
			
			GUIData.psw = (char) (GUIData.psw | 0b0000000001000000);
			
		}
		else {
			
			GUIData.psw = (char) (GUIData.psw & 0b1111111110111111);
			
		}
		
		if(GUIData.chip.getAuxCarryFlag() == true) {
			
			GUIData.psw = (char) (GUIData.psw | 0b0000000000010000);
			
		}
		else {
			
			GUIData.psw = (char) (GUIData.psw & 0b1111111111101111);
			
		}
		
		if(GUIData.chip.getParityFlag() == true) {
			
			GUIData.psw = (char) (GUIData.psw | 0b0000000000000100);
			
		}
		else {
			
			GUIData.psw = (char) (GUIData.psw & 0b1111111111111011);
			
		}
		
		if(GUIData.chip.getCarryFlag() == true) {
			
			GUIData.psw = (char) (GUIData.psw | 0b0000000000000001);
			
		}
		else {
			
			GUIData.psw = (char) (GUIData.psw & 0b1111111111111110);
			
		}
		
		byte memdata[] = new byte[16];
		
		for(int i = 0; i < 16; i++) {        		
			memdata[i] = GUIData.chip.getMemory(start_p);
			start_p++;
		}        		
		
		loc0tf.setText(GUIData.chip.formatByteToHex(memdata[0]));
		loc1tf.setText(GUIData.chip.formatByteToHex(memdata[1]));
		loc2tf.setText(GUIData.chip.formatByteToHex(memdata[2]));
		loc3tf.setText(GUIData.chip.formatByteToHex(memdata[3]));
		loc4tf.setText(GUIData.chip.formatByteToHex(memdata[4]));
		loc5tf.setText(GUIData.chip.formatByteToHex(memdata[5]));
		loc6tf.setText(GUIData.chip.formatByteToHex(memdata[6]));
		loc7tf.setText(GUIData.chip.formatByteToHex(memdata[7]));
		loc8tf.setText(GUIData.chip.formatByteToHex(memdata[8]));
		loc9tf.setText(GUIData.chip.formatByteToHex(memdata[9]));
		loc10tf.setText(GUIData.chip.formatByteToHex(memdata[10]));
		loc11tf.setText(GUIData.chip.formatByteToHex(memdata[11]));
		loc12tf.setText(GUIData.chip.formatByteToHex(memdata[12]));
		loc13tf.setText(GUIData.chip.formatByteToHex(memdata[13]));
		loc14tf.setText(GUIData.chip.formatByteToHex(memdata[14]));
		loc15tf.setText(GUIData.chip.formatByteToHex(memdata[15]));
		
		start_p = start_p - 16;
		
		loc0.setText(String.format("%04X", start_p+0).toUpperCase());
		loc1.setText(String.format("%04X", start_p+1).toUpperCase());
		loc2.setText(String.format("%04X", start_p+2).toUpperCase());
		loc3.setText(String.format("%04X", start_p+3).toUpperCase());
		loc4.setText(String.format("%04X", start_p+4).toUpperCase());
		loc5.setText(String.format("%04X", start_p+5).toUpperCase());
		loc6.setText(String.format("%04X", start_p+6).toUpperCase());
		loc7.setText(String.format("%04X", start_p+7).toUpperCase());
		loc8.setText(String.format("%04X", start_p+8).toUpperCase());
		loc9.setText(String.format("%04X", start_p+9).toUpperCase());
		loc10.setText(String.format("%04X", start_p+10).toUpperCase());
		loc11.setText(String.format("%04X", start_p+11).toUpperCase());
		loc12.setText(String.format("%04X", start_p+12).toUpperCase());
		loc13.setText(String.format("%04X", start_p+13).toUpperCase());
		loc14.setText(String.format("%04X", start_p+14).toUpperCase());
		loc15.setText(String.format("%04X", start_p+15).toUpperCase());
		
		regPSWvallb.setText(String.format("%16s", Integer.toBinaryString(GUIData.psw & 0xFFFF)).replace(' ', '0'));
		
		accessed = false;
        
	}
	
	public String getPort_Data(int loc) {
		return Integer.toHexString(GUIData.port_data[loc] & 0xFF).toUpperCase();
	}
	
	public void setPort_Data(int loc, String hex) {
		GUIData.port_data[loc] = (byte) (Integer.parseInt(hex, 16) & 0xFF);
	}
	
	class ButtonListener implements ActionListener{
		
        public void actionPerformed(ActionEvent e){
        	
        	if(e.getActionCommand().equals("Translate")) {
        		String s = asm_area.getText();
        		ASMTranslator at = new ASMTranslator();
        		String ret = at.translateASMToOpcode(s);
        		
        		
        		if(ret == null) {
        			String f = GUIData.error_area.getText();
        			f = f + "\nOperation Failed";
        			GUIData.error_area.setText(f);
        			opcode_area.setText("");
        			compile.setEnabled(false);
        			run.setEnabled(false);
        			step_fwd.setEnabled(false);
        		}
        		else if(ret.equals("*!!**!!**!!*")) {
        			GUIData.error_area.setText("No Input Detected! Kindly Enter Something!");
        			opcode_area.setText("");
        			compile.setEnabled(false);
        			run.setEnabled(false);
        			step_fwd.setEnabled(false);
        		}
        		else {
        			opcode_area.setText(ret);
        			GUIData.error_area.setText("Completed Successfully!");
        			run.setEnabled(false);
        			step_fwd.setEnabled(false);
        			compile.setEnabled(true);
        		}
        		
        	}
        	else if(e.getActionCommand().equals("Compile")) {
        		
        		ArrayList<Integer> origin = new ArrayList<Integer>();
        		
        		GUIData.chip.resetData();
        		
        		String origincheck = opcode_area.getText();
        		String[] lines = origincheck.split("\\r?\\n");
        		
        		for(int i = 0; i < lines.length; i++) {
        		
        		if(lines[i].contains("XX")) {
        			String s = lines[i].replaceAll(" ", "").replaceAll("XX", "");
        			origin.add(Integer.parseInt(s, 16));
        		}        		
        		}
        		
        		OpCodeTranslator o = new OpCodeTranslator(); 
        		String nolabels = o.processLabels(opcode_area.getText(), origin);
        		
        		if(nolabels != null) {
        		opcode_area.setText(nolabels);
        		boolean b = o.fixMemory(opcode_area.getText(), origin);        		
        		if(b == true) {
        			GUIData.error_area.setText("Compiled & Replaced Labels Successfully!");
        			compile.setEnabled(false);
        			run.setEnabled(true);
        			step_fwd.setEnabled(true);
        		}
        		else {
        			GUIData.error_area.setText(GUIData.error_area.getText() + "\nFailed Compiling Program!");
        			run.setEnabled(false);
        			step_fwd.setEnabled(false);
        		}
        		}
        		
        		setTFs();
        		
        	}
        	else if(e.getActionCommand().equals("Save")) {
        		
        		 JFileChooser chooser = new JFileChooser();
        		 
        		 FileNameExtensionFilter filter = new FileNameExtensionFilter("8085 Simulation File","x85");
        		 chooser.setFileFilter(filter);
        		 chooser.setAcceptAllFileFilterUsed(false);
        		 
        		    int retrival = chooser.showSaveDialog(null);
        		    if (retrival == JFileChooser.APPROVE_OPTION) {
        		        try {	        		        	       		        	
        		            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".x85");
        		            fw.write(asm_area.getText());
        		            fw.flush();
        		            fw.close();
        		        } catch (Exception ex) {
        		            GUIData.error_area.setText("Error Saving File! Kindly Retry!");
        		        }
        		    }
        		
        	}
        	else if(e.getActionCommand().equals("Load")) {
        		
        		 JFileChooser chooser= new JFileChooser();
        		 chooser.setAcceptAllFileFilterUsed(false);
                 chooser.setFileFilter(new FileNameExtensionFilter("8085 Simulation File","x85"));
                 
                 int retrival = chooser.showOpenDialog(null);
                 if(retrival == JFileChooser.APPROVE_OPTION) {
                 try{
                	 File f = chooser.getSelectedFile();
                     String filename= f.getAbsolutePath();
                     FileReader reader = new FileReader(filename);
                     BufferedReader br = new BufferedReader(reader);
                     asm_area.read(br,null);
                     br.close();
                 }catch(Exception ex){
                	 GUIData.error_area.setText("Error Loading File! Kindly Retry!");
                 }
                 }
        		
        	}
        	else if(e.getActionCommand().equals("Run")) {
        		
        	
        	if(orgpoint >= 0 && orgpoint <= 65535) {
        		        		
        			new Thread( new Runnable() {
        	            @Override
        	            public void run()
        	            {
        	            	boolean running = true;
        	            	
        	            	while(running == true) {
        	        			
        	        			int org = GUIData.chip.getProgramCounter();
        	        			OpCodeTranslator o = new OpCodeTranslator();        			
        	        			int next = o.processOpCode(org);
        	        			
        	        			if(next != -1 && GUIData.chip.getMemory(next) != 0x76) {
        	        			GUIData.chip.setProgramCounter((char)next);
        	        			setTFs();
        	        			}
        	        			else if(GUIData.chip.getMemory(next) == 0x76) {
        	        				Main.running1 = false;
        	        				GUIData.chip.setProgramCounter((char)(next+1));
        	        				setTFs();
        	        				running = false;
        	        				GUIData.error_area.setText("Program Has Ended!");
        	        				step_fwd.setEnabled(false);
        	        				run.setEnabled(false);
        	        			}
        	        			else {
        	        				Main.running1 = false;
        	        				running = false;
        	        				setTFs();
        	        				GUIData.error_area.setText("Program Has Ended!");
        	        				step_fwd.setEnabled(false);
        	        				run.setEnabled(false);
        	        			}
        	                
        	            }}}).start();
        		
        	}
        		else {
        			GUIData.error_area.setText("Kindly Specify The Origin Of The Program Between 0000 & FFFF!");
        		}
        		
        		
        	}
        	else if(e.getActionCommand().equals("Step")) {
        		
        		if(orgpoint >= 0 && orgpoint <= 65535) {
        		
        			int org = GUIData.chip.getProgramCounter();
        			OpCodeTranslator o = new OpCodeTranslator();        			
        			int next = o.processOpCode(org);
        			
        			if(next != -1 && GUIData.chip.getMemory(next) != 0x76) {
        			GUIData.chip.setProgramCounter((char)next);
        			setTFs();
        			}
        			else if(GUIData.chip.getMemory(next) == 0x76) {
        				Main.running1 = false;
        				GUIData.chip.setProgramCounter((char)(next+1));
        				setTFs();
        				GUIData.error_area.setText("Program Has Ended!");
        				step_fwd.setEnabled(false);
        				run.setEnabled(false);
        			}
        			else {
        				Main.running1 = false;
        				setTFs();
        				GUIData.error_area.setText("Program Has Ended!");
        				step_fwd.setEnabled(false);
        				run.setEnabled(false);
        			}
        			
        		}
        		else {
        			GUIData.error_area.setText("Kindly Specify The Origin Of The Program Between 0000 & FFFF!");
        		}
        		
        	}
        	else if(e.getActionCommand().equals("ORI")) {
        		if(ori_tf.getText().matches("-?[0-9a-fA-F]+")) {
        			orgpoint = Integer.parseInt(ori_tf.getText(), 16);
        			GUIData.chip.setProgramCounter((char)orgpoint);
        			GUIData.error_area.setText("Origin Set Successfully At " + String.format("%04XH", orgpoint) + "!");
        			regPCtf.setText(GUIData.chip.formatCharToHex(GUIData.chip.getProgramCounter()));
        		}
        		else {
        			GUIData.error_area.setText("Origin Could Not Be Set! Kindly Check & Fix The Value!");
        		}
        	}
        	else if(e.getActionCommand().equals("Populate")) {
        		
        		while(accessed == true) {
        			
        		}
        		
        		accessed = true;
        		
        		if(start_pointtf.getText().matches("-?[0-9a-fA-F]+") && (Integer.parseInt(start_pointtf.getText(), 16) >= 0) && (Integer.parseInt(start_pointtf.getText(), 16)+16) <= 65536) {
        			
        		byte memdata[] = new byte[16];
        		start_p = Integer.parseInt(start_pointtf.getText(), 16);
        		
        		for(int i = 0; i < 16; i++) {        		
        			memdata[i] = GUIData.chip.getMemory(start_p);
        			start_p++;
        		}        		
        		
        		loc0tf.setText(GUIData.chip.formatByteToHex(memdata[0]));
        		loc1tf.setText(GUIData.chip.formatByteToHex(memdata[1]));
        		loc2tf.setText(GUIData.chip.formatByteToHex(memdata[2]));
        		loc3tf.setText(GUIData.chip.formatByteToHex(memdata[3]));
        		loc4tf.setText(GUIData.chip.formatByteToHex(memdata[4]));
        		loc5tf.setText(GUIData.chip.formatByteToHex(memdata[5]));
        		loc6tf.setText(GUIData.chip.formatByteToHex(memdata[6]));
        		loc7tf.setText(GUIData.chip.formatByteToHex(memdata[7]));
        		loc8tf.setText(GUIData.chip.formatByteToHex(memdata[8]));
        		loc9tf.setText(GUIData.chip.formatByteToHex(memdata[9]));
        		loc10tf.setText(GUIData.chip.formatByteToHex(memdata[10]));
        		loc11tf.setText(GUIData.chip.formatByteToHex(memdata[11]));
        		loc12tf.setText(GUIData.chip.formatByteToHex(memdata[12]));
        		loc13tf.setText(GUIData.chip.formatByteToHex(memdata[13]));
        		loc14tf.setText(GUIData.chip.formatByteToHex(memdata[14]));
        		loc15tf.setText(GUIData.chip.formatByteToHex(memdata[15]));
        		
        		start_p = start_p - 16;
        		
        		loc0.setText(String.format("%04X", start_p+0).toUpperCase());
        		loc1.setText(String.format("%04X", start_p+1).toUpperCase());
        		loc2.setText(String.format("%04X", start_p+2).toUpperCase());
        		loc3.setText(String.format("%04X", start_p+3).toUpperCase());
        		loc4.setText(String.format("%04X", start_p+4).toUpperCase());
        		loc5.setText(String.format("%04X", start_p+5).toUpperCase());
        		loc6.setText(String.format("%04X", start_p+6).toUpperCase());
        		loc7.setText(String.format("%04X", start_p+7).toUpperCase());
        		loc8.setText(String.format("%04X", start_p+8).toUpperCase());
        		loc9.setText(String.format("%04X", start_p+9).toUpperCase());
        		loc10.setText(String.format("%04X", start_p+10).toUpperCase());
        		loc11.setText(String.format("%04X", start_p+11).toUpperCase());
        		loc12.setText(String.format("%04X", start_p+12).toUpperCase());
        		loc13.setText(String.format("%04X", start_p+13).toUpperCase());
        		loc14.setText(String.format("%04X", start_p+14).toUpperCase());
        		loc15.setText(String.format("%04X", start_p+15).toUpperCase());
        		
        		GUIData.error_area.setText("Populated Successfully!");
        		
        		accessed = false;
        		
        		}
        		else {
        			
        			GUIData.error_area.setText("Error Populating Memory Addresses! Kindly Check The Range Is between 0000 & FFFF!");
        			accessed = false;
        		}
        		
        	}
        	else if(e.getActionCommand().equals("RA")) {
        		
        		if(regAtf.getText().matches("-?[0-9a-fA-F]+")) {
        			GUIData.chip.setRegA((byte) (Integer.parseInt(regAtf.getText(), 16) & 0xff));
        			setTFs();
        		}
        		
        	}
			else if(e.getActionCommand().equals("RB")) {
			        		
			if(regBtf.getText().matches("-?[0-9a-fA-F]+")) {
			      GUIData.chip.setRegB((byte) (Integer.parseInt(regBtf.getText(), 16) & 0xff));
			     setTFs();
			    }
			        		
			}
			else if(e.getActionCommand().equals("RC")) {
				
				if(regCtf.getText().matches("-?[0-9a-fA-F]+")) {
					GUIData.chip.setRegC((byte) (Integer.parseInt(regCtf.getText(), 16) & 0xff));
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("RD")) {
				
				if(regDtf.getText().matches("-?[0-9a-fA-F]+")) {
					GUIData.chip.setRegD((byte) (Integer.parseInt(regDtf.getText(), 16) & 0xff));
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("RE")) {
				
				if(regEtf.getText().matches("-?[0-9a-fA-F]+")) {
					GUIData.chip.setRegE((byte) (Integer.parseInt(regEtf.getText(), 16) & 0xff));
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("RH")) {
				
				if(regHtf.getText().matches("-?[0-9a-fA-F]+")) {
					GUIData.chip.setRegH((byte) (Integer.parseInt(regHtf.getText(), 16) & 0xff));
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("RL")) {
        		
        		if(regLtf.getText().matches("-?[0-9a-fA-F]+")) {
        			GUIData.chip.setRegL((byte) (Integer.parseInt(regLtf.getText(), 16) & 0xff));
        			setTFs();
        		}
        		
        	}
			else if(e.getActionCommand().equals("RSP")) {
				
				if(regSPtf.getText().matches("-?[0-9a-fA-F]+")) {
        			GUIData.chip.setStackPointer((char) (Integer.parseInt(regSPtf.getText(), 16) & 0xffff));
        			setTFs();
        		}
				
			}        	
			else if(e.getActionCommand().equals("RPC")) {
				
				if(regPCtf.getText().matches("-?[0-9a-fA-F]+")) {
        			GUIData.chip.setProgramCounter((char) (Integer.parseInt(regPCtf.getText(), 16) & 0xffff));
        			setTFs();
        		}
				
			}
			else if(e.getActionCommand().equals("AF")) {
				
				if(regAFtf.getText().equals("1")) {
				GUIData.chip.setAuxCarryFlag(true);
				setTFs();
				}
				else if(regAFtf.getText().equals("0")) {
					GUIData.chip.setAuxCarryFlag(false);
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("CF")) {
				
				if(regCFtf.getText().equals("1")) {
				GUIData.chip.setCarryFlag(true);
				setTFs();
				}
				else if(regCFtf.getText().equals("0")) {
					GUIData.chip.setCarryFlag(false);
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("PF")) {
				
				if(regPFtf.getText().equals("1")) {
				GUIData.chip.setParityFlag(true);
				setTFs();
				}
				else if(regPFtf.getText().equals("0")) {
					GUIData.chip.setParityFlag(false);
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("ZF")) {
				
				if(regZFtf.getText().equals("1")) {
				GUIData.chip.setZeroFlag(true);
				setTFs();
				}
				else if(regZFtf.getText().equals("0")) {
					GUIData.chip.setZeroFlag(false);
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("SF")) {
				
				if(regSFtf.getText().equals("1")) {
				GUIData.chip.setSignFlag(true);
				setTFs();
				}
				else if(regSFtf.getText().equals("0")) {
					GUIData.chip.setSignFlag(false);
					setTFs();
				}

			}
			else if(e.getActionCommand().equals("L0")) {
        	
				if(loc0tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc0.getText(), 16), (byte)(Integer.parseInt(loc0tf.getText(), 16) & 0xff));		
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L1")) {
	        	
				if(loc1tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc1.getText(), 16), (byte)(Integer.parseInt(loc1tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L2")) {
	        	
				if(loc2tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc2.getText(), 16), (byte)(Integer.parseInt(loc2tf.getText(), 16) & 0xff));
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L3")) {
	        	
				if(loc3tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc3.getText(), 16), (byte)(Integer.parseInt(loc3tf.getText(), 16) & 0xff));
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L4")) {
	        	
				if(loc4tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc4.getText(), 16), (byte)(Integer.parseInt(loc4tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L5")) {
	        	
				if(loc5tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc5.getText(), 16), (byte)(Integer.parseInt(loc5tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L6")) {
	        	
				if(loc6tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc6.getText(), 16), (byte)(Integer.parseInt(loc6tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L7")) {
	        	
				if(loc7tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc7.getText(), 16), (byte)(Integer.parseInt(loc7tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L8")) {
	        	
				if(loc8tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc8.getText(), 16), (byte)(Integer.parseInt(loc8tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L9")) {
	        	
				if(loc9tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc9.getText(), 16), (byte)(Integer.parseInt(loc9tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L10")) {
	        	
				if(loc10tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc10.getText(), 16), (byte)(Integer.parseInt(loc10tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L11")) {
	        	
				if(loc11tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc11.getText(), 16), (byte)(Integer.parseInt(loc11tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L12")) {
	        	
				if(loc12tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc12.getText(), 16), (byte)(Integer.parseInt(loc12tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L13")) {
	        	
				if(loc13tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc13.getText(), 16), (byte)(Integer.parseInt(loc13tf.getText(), 16) & 0xff));					
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L14")) {
	        	
				if(loc14tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc14.getText(), 16), (byte)(Integer.parseInt(loc14tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
			else if(e.getActionCommand().equals("L15")) {
	        	
				if(loc15tf.getText().matches("-?[0-9a-fA-F]+")) {					
					GUIData.chip.setMemory(Integer.parseInt(loc15.getText(), 16), (byte)(Integer.parseInt(loc15tf.getText(), 16) & 0xff));	
					setTFs();
				}
				
			}
    }
        
}	
	
}
