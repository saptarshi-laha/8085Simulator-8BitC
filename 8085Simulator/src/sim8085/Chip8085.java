package sim8085;

public class Chip8085 {
	
	private byte a; 
	private byte b; 
	private byte c; 
	private byte d; 
	private byte e; 
	private byte h; 
	private byte l; 
	
	private char sp; 
	private char pc; 
	
	private boolean sign; 
	private boolean zero; 
	private boolean auxcarry; 
	private boolean parity; 
	private boolean carry; 
	private boolean interrupt;
	
	private byte[] memory = new byte[65536];

	Chip8085(){
		
		resetData();
		
	}
	
	public void resetData() {
		

		for(int i = 0; i < 65536; i++) {
			memory[i] = 0x76;
		}
		
		this.a = 0; 
		this.b = 0; 
		this.c = 0; 
		this.d = 0; 
		this.e = 0; 
		this.h = 0; 
		this.l = 0; 
		
		this.sp = 0xFFFF; 
		this.pc = 0; 
		
		this.sign = false; 
		this.zero = false; 
		this.auxcarry = false; 
		this.parity = false; 
		this.carry = false; 
		this.interrupt = false;
		
	}
	
	public void setInterrupt(boolean x) {
		this.interrupt = x;
	}
	
	public boolean getInterrupt() {
		return this.interrupt;
	}
	
	public void setRegA(byte x) {
		this.a = x; 
	}
	
	public void setRegB(byte x) {
		this.b = x; 
	}

	public void setRegC(byte x) {
		this.c = x; 
	}
	
	public void setRegD(byte x) {
		this.d = x;
	}
	
	public void setRegE(byte x) {
		this.e = x; 
	}
	
	public void setRegH(byte x) {
		this.h = x; 
	}
	
	public void setRegL(byte x) {
		this.l = x; 
	}
	
	public void setSignFlag(boolean x) {
		this.sign = x; 
	}
	
	public void setZeroFlag(boolean x) {
		this.zero = x; 
	}
	
	public void setAuxCarryFlag(boolean x) {
		this.auxcarry = x;
	}
	
	public void setParityFlag(boolean x) {
		this.parity = x; 
	}
	
	public void setCarryFlag(boolean x) {
		this.carry = x; 
	}
	
	public void setStackPointer(char x) {
		if(((int)x) >= 0 && ((int)x) < 65536) {
		this.sp = x;
		}
		else {
			GUIData.error_area.setText("Stack Pointer Out Of Bounds!");
		}
	}
	
	public void setProgramCounter(char x) {
		if(((int)x) >= 0 && ((int)x) < 65536) {
		this.pc = x;
		}
		else {
			GUIData.error_area.setText("Program Counter Out Of Bounds!");
		}
	}
	
	public byte getRegA() {
		return this.a;
	}
	
	public byte getRegB() {
		return this.b;
	}
	
	public byte getRegC() {
		return this.c;
	}
	
	public byte getRegD() {
		return this.d;
	}
	
	public byte getRegE() {
		return this.e;
	}
	
	public byte getRegH() {
		return this.h;
	}
	
	public byte getRegL() {
		return this.l;
	}
	
	public boolean getSignFlag() {
		return this.sign;
	}
	
	public boolean getZeroFlag() {
		return this.zero;
	}
	
	public boolean getAuxCarryFlag() {
		return this.auxcarry;
	}
	
	public boolean getParityFlag() {
		return this.parity;
	}
	
	public boolean getCarryFlag() {
		return this.carry;
	}
	
	public char getStackPointer() {
		return this.sp;
	}
	
	public char getProgramCounter() {
		return this.pc;
	}
	
	public byte getMemory(int i) {
		return this.memory[i];
	}
	
	public void setMemory(int i, byte b) {
		this.memory[i] = b;
	}
	
	public byte formatHexToByte(String x) {
		return (byte) (Integer.parseInt(x, 16) & 0xFF);
	}
	
	public String formatByteToHex(byte b) {
		return String.format("%02X",b).toUpperCase();
	}
	
	public String formatCharToHex(char b) {
		return String.format("%04X",(int)b).toUpperCase();
	}
	
	public String formatBooleanToHex(boolean b) {
		if(b == true) {
			return Integer.toString(1);
		}
		else {
			return Integer.toString(0);
		}
	}
	
}
