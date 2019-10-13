package sim8085;

import java.util.ArrayList; 

public class OpCodeTranslator {
	
	
	
	public int processOpCode(int org) {
		
			if(GUIData.chip.getMemory(org) == 0x3E) {
				GUIData.chip.setRegA(GUIData.chip.getMemory(org+1));
				org = org + 2;							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x06) {
				GUIData.chip.setRegB(GUIData.chip.getMemory(org+1));
				org = org + 2;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x0E) {
				GUIData.chip.setRegC(GUIData.chip.getMemory(org+1));
				org = org + 2;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x16) {
				GUIData.chip.setRegD(GUIData.chip.getMemory(org+1));
				org = org + 2;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x1E) {
				GUIData.chip.setRegE(GUIData.chip.getMemory(org+1));
				org = org + 2;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x26) {
				GUIData.chip.setRegH(GUIData.chip.getMemory(org+1));
				org = org + 2;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x2E) {
				GUIData.chip.setRegL(GUIData.chip.getMemory(org+1));
				org = org + 2;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x36) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getMemory(org+1));
				org = org + 2;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x00) {
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x7F) {
				GUIData.chip.setRegA(GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x78) {
				GUIData.chip.setRegA(GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x79) {
				GUIData.chip.setRegA(GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x7A) {
				GUIData.chip.setRegA(GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x7B) {
				GUIData.chip.setRegA(GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x7C) {
				GUIData.chip.setRegA(GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x7D) {
				GUIData.chip.setRegA(GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x7E) {
				GUIData.chip.setRegA(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16)));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x47) {
				GUIData.chip.setRegB(GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x40) {
				GUIData.chip.setRegB(GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x41) {
				GUIData.chip.setRegB(GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x42) {
				GUIData.chip.setRegB(GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x43) {
				GUIData.chip.setRegB(GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x44) {
				GUIData.chip.setRegB(GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x45) {
				GUIData.chip.setRegB(GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x46) {
				GUIData.chip.setRegB(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16)));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x4F) {
				GUIData.chip.setRegC(GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x48) {
				GUIData.chip.setRegC(GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x49) {
				GUIData.chip.setRegC(GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x4A) {
				GUIData.chip.setRegC(GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x4B) {
				GUIData.chip.setRegC(GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x4C) {
				GUIData.chip.setRegC(GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x4D) {
				GUIData.chip.setRegC(GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x4E) {
				GUIData.chip.setRegC(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16)));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x57) {
				GUIData.chip.setRegD(GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x50) {
				GUIData.chip.setRegD(GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x51) {
				GUIData.chip.setRegD(GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x52) {
				GUIData.chip.setRegD(GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x53) {
				GUIData.chip.setRegD(GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x54) {
				GUIData.chip.setRegD(GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x55) {
				GUIData.chip.setRegD(GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x56) {
				GUIData.chip.setRegD(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16)));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x5F) {
				GUIData.chip.setRegE(GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x58) {
				GUIData.chip.setRegE(GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x59) {
				GUIData.chip.setRegE(GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x5A) {
				GUIData.chip.setRegE(GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x5B) {
				GUIData.chip.setRegE(GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x5C) {
				GUIData.chip.setRegE(GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x5D) {
				GUIData.chip.setRegE(GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x5E) {
				GUIData.chip.setRegE(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16)));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x67) {
				GUIData.chip.setRegH(GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x60) {
				GUIData.chip.setRegH(GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x61) {
				GUIData.chip.setRegH(GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x62) {
				GUIData.chip.setRegH(GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x63) {
				GUIData.chip.setRegH(GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x64) {
				GUIData.chip.setRegH(GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x65) {
				GUIData.chip.setRegH(GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x66) {
				GUIData.chip.setRegH(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16)));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x6F) {
				GUIData.chip.setRegL(GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x68) {
				GUIData.chip.setRegL(GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x69) {
				GUIData.chip.setRegL(GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x6A) {
				GUIData.chip.setRegL(GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x6B) {
				GUIData.chip.setRegL(GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x6C) {
				GUIData.chip.setRegL(GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x6D) {
				GUIData.chip.setRegL(GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x6E) {
				GUIData.chip.setRegL(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16)));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x77) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x70) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getRegB());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x71) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getRegC());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x72) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getRegD());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x73) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getRegE());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x74) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getRegH());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x75) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegH()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegL()), 16), GUIData.chip.getRegL());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x01) {
				GUIData.chip.setRegB(GUIData.chip.getMemory(org + 1));
				GUIData.chip.setRegC(GUIData.chip.getMemory(org + 2));
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x11) {
				GUIData.chip.setRegD(GUIData.chip.getMemory(org + 1));
				GUIData.chip.setRegE(GUIData.chip.getMemory(org + 2));
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x21) {
				GUIData.chip.setRegH(GUIData.chip.getMemory(org + 1));
				GUIData.chip.setRegL(GUIData.chip.getMemory(org + 2));
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x31) {
				GUIData.chip.setStackPointer((char)Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16));
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x3A) {
				GUIData.chip.setRegA(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16)));
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x32) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16), GUIData.chip.getRegA());
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x2A) {
				GUIData.chip.setRegL(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16)));
				GUIData.chip.setRegH(GUIData.chip.getMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16) + 1));		
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x22) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16), GUIData.chip.getRegL());
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16) + 1, GUIData.chip.getRegH());
				org = org + 3;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xEB) {
				byte x = GUIData.chip.getRegH();
				GUIData.chip.setRegH(GUIData.chip.getRegD());
				GUIData.chip.setRegD(x);
				x = GUIData.chip.getRegL();
				GUIData.chip.setRegL(GUIData.chip.getRegE());
				GUIData.chip.setRegE(x);
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x0A) {
				GUIData.chip.setRegA(GUIData.chip.getMemory((Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegB()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegC()), 16))));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x1A) {
				GUIData.chip.setRegA(GUIData.chip.getMemory((Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegD()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegE()), 16))));
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x02) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegB()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegC()), 16), GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x12) {
				GUIData.chip.setMemory(Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getRegD()) + GUIData.chip.formatByteToHex(GUIData.chip.getRegE()), 16), GUIData.chip.getRegA());
				org = org + 1;
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xDA) {
				if(GUIData.chip.getCarryFlag() == true) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xD2) {
				if(GUIData.chip.getCarryFlag() == false) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xF2) {
				if(GUIData.chip.getSignFlag() == false) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xFA) {
				if(GUIData.chip.getSignFlag() == true) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xCA) {
				if(GUIData.chip.getZeroFlag() == true) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xC2) {
				if(GUIData.chip.getZeroFlag() == false) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xEA) {
				if(GUIData.chip.getParityFlag() == true) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xE2) {
				if(GUIData.chip.getParityFlag() == false) {
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xC3) {
				org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xDC) {
				if(GUIData.chip.getCarryFlag() == true) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}		
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xD4) {
				if(GUIData.chip.getCarryFlag() == false) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xCC) {
				if(GUIData.chip.getZeroFlag() == true) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}	
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xC4) {
				if(GUIData.chip.getZeroFlag() == false) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xEC) {
				if(GUIData.chip.getParityFlag() == true) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xE4) {
				if(GUIData.chip.getParityFlag() == false) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xFC) {
				if(GUIData.chip.getSignFlag() == true) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xF4) {
				if(GUIData.chip.getSignFlag() == false) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xCD) {
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
					org = Integer.parseInt(GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 1)) + GUIData.chip.formatByteToHex(GUIData.chip.getMemory(org + 2)), 16);
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xD8) {
				if(GUIData.chip.getCarryFlag() == true) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xD0) {
				if(GUIData.chip.getCarryFlag() == false) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xC8) {
				if(GUIData.chip.getZeroFlag() == true) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xC0) {
				if(GUIData.chip.getZeroFlag() == false) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xE8) {
				if(GUIData.chip.getParityFlag() == true) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xE0) {
				if(GUIData.chip.getParityFlag() == false) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xF8) {
				if(GUIData.chip.getSignFlag() == true) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xF0) {
				if(GUIData.chip.getSignFlag() == false) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));
				}
				else {
					org = org + 3;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xC9) {
					String s = Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 1)) & 0xFF) + "" + Integer.toHexString((GUIData.chip.getMemory((int) GUIData.chip.getStackPointer() + 2)) & 0xFF);
					org = Integer.parseInt(s, 16);
					GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() + 2));				
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xC7) {				
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0000", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xCF) {
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0008", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xD7) {
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0010", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xDF) {
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0018", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xE7) {
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0020", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xEF) {
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0028", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xF7) {
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0030", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xFF) {
				if(GUIData.chip.getInterrupt() == true) {
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)((org+3) & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				GUIData.chip.setMemory((int)GUIData.chip.getStackPointer(), (byte)(((org+3) >> 8)  & 0xFF));
				GUIData.chip.setStackPointer((char)(GUIData.chip.getStackPointer() - 1));
				org = Integer.parseInt("0038", 16);
				}
				else {
					org = org + 1;
				}
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x87) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegA()));
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegA()));	
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x80) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegB()));
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegB()) & 0xf) > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegB()));	
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x81) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegC()));
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegC()) & 0xf) > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegC()));	
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x82) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegD()));
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegD()) & 0xf) > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegD()));	
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x83) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegE()));
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegE()) & 0xf) > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegE()));	
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x84) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegH()));
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegH()) & 0xf) > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegH()));	
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x85) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegL()));
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegL()) & 0xf) > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegL()));	
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x86) {
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(memorydata));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(memorydata) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + memorydata));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x8F) {
				
				if(GUIData.chip.getCarryFlag() == true) {
					
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegA()) + 1);
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + 1 > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegA() + 1));	
				
				}
				else {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegA()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegA()));
					
				}
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
				
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x88) {
				
				if(GUIData.chip.getCarryFlag() == true) {
					
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegB()) + 1);
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegB()) & 0xf) + 1 > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegB() + 1));	
				
				}
				else {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegB()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegB()) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegB()));
					
				}
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
				
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x89) {
				
				if(GUIData.chip.getCarryFlag() == true) {
					
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegC()) + 1);
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegC()) & 0xf) + 1 > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegC() + 1));	
				
				}
				else {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegC()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegC()) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegC()));
					
				}
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
				
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x8A) {
				
				if(GUIData.chip.getCarryFlag() == true) {
					
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegD()) + 1);
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegD()) & 0xf) + 1 > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegD() + 1));	
				
				}
				else {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegD()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegD()) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegD()));
					
				}
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
				
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x8B) {
				
				if(GUIData.chip.getCarryFlag() == true) {
					
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegE()) + 1);
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegE()) & 0xf) + 1 > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegE() + 1));	
				
				}
				else {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegE()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegE()) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegE()));
					
				}
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
				
			}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x8C) {
				
				if(GUIData.chip.getCarryFlag() == true) {
					
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegH()) + 1);
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegH()) & 0xf) + 1 > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegH() + 1));	
				
				}
				else {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegH()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegH()) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegH()));
					
				}
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
				
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0x8D) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegL()) + 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegL()) & 0xf) + 1 > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegL() + 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegL()));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegL()) & 0xf) > 0x0f) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegL()));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x8E) {
				
				String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
				
				byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
				
				if(GUIData.chip.getCarryFlag() == true) {
				
				char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(memorydata) + 1);
				
				if(carrycheck > 0xff) {
					GUIData.chip.setCarryFlag(true);
				}
				else {
					GUIData.chip.setCarryFlag(false);
				}
				
				if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(memorydata) & 0xf) + 1 > 0x0f) {
					GUIData.chip.setAuxCarryFlag(true);
				}	
				else {
					GUIData.chip.setAuxCarryFlag(false);
				}
				
				
				GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + memorydata + 1));	
				
				}
				else {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(memorydata));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(memorydata) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + memorydata));
					
				}
				
				if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
					GUIData.chip.setSignFlag(true);
				}
				else {
					GUIData.chip.setSignFlag(false);
				}
				
				if(GUIData.chip.getRegA() == 0x0) {
					GUIData.chip.setZeroFlag(true);
				}
				else {
					GUIData.chip.setZeroFlag(false);
				}
				
				String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
				int count = 0;
				
				for(int checki = 0; checki<paritycheck.length(); checki++) {
					if(paritycheck.charAt(checki) == '1') {
						++count;
					}
				}
				
				if(count % 2 == 0) {
					GUIData.chip.setParityFlag(true);
				}
				else {
					GUIData.chip.setParityFlag(false);
				}
				
				org = org + 1;
							
			}
			else if((GUIData.chip.getMemory(org) & 0xff) == 0xCE) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)) + 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)) & 0xf) + 1 > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getMemory(org+1) + 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)) & 0xf) > 0x0f) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getMemory(org+1)));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 2;
					
				}
			    else if((GUIData.chip.getMemory(org) & 0xff) == 0xC6) {
									
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getMemory(org+1)));
				
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
				
				org = org + 2;
				
			    }
			    else if((GUIData.chip.getMemory(org) & 0xff) == 0x09) {
			    
			    	int pair1 = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegH())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegL())));
			    	int pair2 = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegB())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegC())));
				
			    	int carrycheck = pair1 + pair2;
			    	
			    	if(carrycheck > 0xffff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
			    	
			    	GUIData.chip.setRegL((byte)(carrycheck & 0xff));
			    	GUIData.chip.setRegH((byte)((carrycheck >> 8) & 0xff));
			    	
				org = org + 1;
			    }
			    else if((GUIData.chip.getMemory(org) & 0xff) == 0x19) {
			    	
			    	int pair1 = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegH())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegL())));
			    	int pair2 = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegD())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegE())));
				
			    	int carrycheck = pair1 + pair2;
			    	
			    	if(carrycheck > 0xffff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
			    	
			    	GUIData.chip.setRegL((byte)(carrycheck & 0xff));
			    	GUIData.chip.setRegH((byte)((carrycheck >> 8) & 0xff));
					
				org = org + 1;	
			    }
			    else if((GUIData.chip.getMemory(org) & 0xff) == 0x29) {
			    	
			    	int pair1 = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegH())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegL())));
			    	int pair2 = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegH())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegL())));
				
			    	int carrycheck = pair1 + pair2;
			    	
			    	if(carrycheck > 0xffff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
			    	
			    	GUIData.chip.setRegL((byte)(carrycheck & 0xff));
			    	GUIData.chip.setRegH((byte)((carrycheck >> 8) & 0xff));
			    	
				org = org + 1;	
			    }
			    else if((GUIData.chip.getMemory(org) & 0xff) == 0x39) {
			    
			    	int pair1 = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegH())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegL())));
			    	int pair2 = (int) GUIData.chip.getStackPointer();
				
			    	int carrycheck = pair1 + pair2;
			    	
			    	if(carrycheck > 0xffff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
			    	
			    	GUIData.chip.setRegL((byte)(carrycheck & 0xff));
			    	GUIData.chip.setRegH((byte)((carrycheck >> 8) & 0xff));	
					
				org = org + 1;	
			    }
			    else if((GUIData.chip.getMemory(org) & 0xff) == 0x97) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegA()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegA()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegA()));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x90) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegB()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegB()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegB()));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x91) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegC()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegC()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegC()));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x92) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegD()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegD()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegD()));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x93) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegE()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegE()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegE()));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x94) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) + Byte.toUnsignedInt(GUIData.chip.getRegH()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt(GUIData.chip.getRegH()) & 0xf) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + GUIData.chip.getRegH()));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x95) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegL()));
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegL()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegL()));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x96) {
						
						String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
						
						byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(memorydata));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~memorydata) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - memorydata));	
						
						if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
							GUIData.chip.setSignFlag(true);
						}
						else {
							GUIData.chip.setSignFlag(false);
						}
						
						if(GUIData.chip.getRegA() == 0x0) {
							GUIData.chip.setZeroFlag(true);
						}
						else {
							GUIData.chip.setZeroFlag(false);
						}
						
						String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
						
						int count = 0;
						
						for(int checki = 0; checki<paritycheck.length(); checki++) {
							if(paritycheck.charAt(checki) == '1') {
								++count;
							}
						}
						
						if(count % 2 == 0) {
							GUIData.chip.setParityFlag(true);
						}
						else {
							GUIData.chip.setParityFlag(false);
						}
						
						org = org + 1;
									
					}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x9F) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegA()) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegA()) & 0xf) > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegA() - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegA()));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegA()) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegA()));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x98) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegB()) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegB()) & 0xf) > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegB() - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegB()));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegB()) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegB()));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x99) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegC()) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegC()) & 0xf)> 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegC() - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegC()));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegC()) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegC()));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x9A) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegD()) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegD()) & 0xf) > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegD() - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegD()));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegD()) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegD()));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x9B) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegE()) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegE()) & 0xf)> 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegE() - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegE()));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegE()) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegE()));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
					else if((GUIData.chip.getMemory(org) & 0xff) == 0x9C) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegH()) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegH()) & 0xf) > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegH() - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegH()));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegH()) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegH()));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x9D) {
						
						if(GUIData.chip.getCarryFlag() == true) {
							
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegL()) - 1);
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegL()) & 0xf) > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegL() - 1));	
						
						}
						else {
							
							char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegL()));
							
							if(carrycheck > 0xff) {
								GUIData.chip.setCarryFlag(true);
							}
							else {
								GUIData.chip.setCarryFlag(false);
							}
							
							if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegL()) & 0xf) + 1 > 0x0f)) {
								GUIData.chip.setAuxCarryFlag(true);
							}	
							else {
								GUIData.chip.setAuxCarryFlag(false);
							}
							
							
							GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getRegL()));
							
						}
						
						if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
							GUIData.chip.setSignFlag(true);
						}
						else {
							GUIData.chip.setSignFlag(false);
						}
						
						if(GUIData.chip.getRegA() == 0x0) {
							GUIData.chip.setZeroFlag(true);
						}
						else {
							GUIData.chip.setZeroFlag(false);
						}
						
						String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
						
						int count = 0;
						
						for(int checki = 0; checki<paritycheck.length(); checki++) {
							if(paritycheck.charAt(checki) == '1') {
								++count;
							}
						}
						
						if(count % 2 == 0) {
							GUIData.chip.setParityFlag(true);
						}
						else {
							GUIData.chip.setParityFlag(false);
						}
						
						org = org + 1;
						
					}
					else if((GUIData.chip.getMemory(org) & 0xff) == 0x9E) {
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					if(GUIData.chip.getCarryFlag() == true) {
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(memorydata) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~memorydata) & 0xf) > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - memorydata - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(memorydata));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~memorydata) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - memorydata));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xD6) {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getMemory(org+1)) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getMemory(org+1)));
					
						if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
							GUIData.chip.setSignFlag(true);
						}
						else {
							GUIData.chip.setSignFlag(false);
						}
					
						if(GUIData.chip.getRegA() == 0x0) {
							GUIData.chip.setZeroFlag(true);
						}
						else {
							GUIData.chip.setZeroFlag(false);
						}
					
						String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
						int count = 0;
					
						for(int checki = 0; checki<paritycheck.length(); checki++) {
							if(paritycheck.charAt(checki) == '1') {
								++count;
							}
						}
					
						if(count % 2 == 0) {
							GUIData.chip.setParityFlag(true);
						}
						else {
							GUIData.chip.setParityFlag(false);
						}
					
					org = org + 2;
					
				    }
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xDE) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)) - 1);
					
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getMemory(org+1)) & 0xf) > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getMemory(org+1) - 1));	
					
					}
					else {
						
						char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)));
						
						if(carrycheck > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getMemory(org+1)) & 0xf) + 1 > 0x0f)) {
							GUIData.chip.setAuxCarryFlag(true);
						}	
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						
						GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - GUIData.chip.getMemory(org+1)));
						
					}
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 2;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x3C) {
					
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() + 1));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x04) {
					
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegB()) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegB((byte)(GUIData.chip.getRegB() + 1));	
					
					if((GUIData.chip.getRegB() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegB() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegB() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x0C) {
					
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegC()) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegC((byte)(GUIData.chip.getRegC() + 1));	
					
					if((GUIData.chip.getRegC() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegC() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegC() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x14) {
					
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegD()) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegD((byte)(GUIData.chip.getRegD() + 1));	
					
					if((GUIData.chip.getRegD() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegD() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegD() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x1C) {
					
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegE()) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegE((byte)(GUIData.chip.getRegE() + 1));	
					
					if((GUIData.chip.getRegE() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegE() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegE() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x24) {
					
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegH()) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegH((byte)(GUIData.chip.getRegH() + 1));	
					
					if((GUIData.chip.getRegH() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegH() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegH() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x2C) {
					
					
					if(((Byte.toUnsignedInt(GUIData.chip.getRegL()) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegL((byte)(GUIData.chip.getRegL() + 1));	
					
					if((GUIData.chip.getRegL() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegL() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegL() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x34) {
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					if(((Byte.toUnsignedInt(memorydata) & 0xf) + 1) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					GUIData.chip.setMemory(Integer.parseInt(memorylocation,16), (byte)(memorydata + 1));
					
					if((GUIData.chip.getMemory(Integer.parseInt(memorylocation,16)) & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getMemory(Integer.parseInt(memorylocation,16)) == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getMemory(Integer.parseInt(memorylocation,16)) & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if(GUIData.chip.getMemory(org) == 0x76) {
					org = org+1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x3D) {
					
					
					if(((Byte.toUnsignedInt((byte)(~GUIData.chip.getRegA())) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() - 1));	
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x05) {
					
					
					if(((Byte.toUnsignedInt((byte)(~GUIData.chip.getRegB())) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegB((byte)(GUIData.chip.getRegB() - 1));	
					
					if((GUIData.chip.getRegB() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegB() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegB() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x0D) {
					
					
					if(((Byte.toUnsignedInt((byte)(~GUIData.chip.getRegC())) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegC((byte)(GUIData.chip.getRegC() - 1));	
					
					if((GUIData.chip.getRegC() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegC() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegC() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x15) {
					
					
					if(((Byte.toUnsignedInt((byte)(~GUIData.chip.getRegD())) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegD((byte)(GUIData.chip.getRegD() - 1));	
					
					if((GUIData.chip.getRegD() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegD() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegD() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x1D) {
					
					
					if(((Byte.toUnsignedInt((byte)(~GUIData.chip.getRegE())) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegE((byte)(GUIData.chip.getRegE() - 1));	
					
					if((GUIData.chip.getRegE() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegE() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegE() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x25) {
					
					
					if(((Byte.toUnsignedInt((byte)(~GUIData.chip.getRegH())) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegH((byte)(GUIData.chip.getRegH() - 1));	
					
					if((GUIData.chip.getRegH() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegH() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegH() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x2D) {
					
					
					if(((Byte.toUnsignedInt((byte)(~GUIData.chip.getRegL())) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					
					GUIData.chip.setRegL((byte)(GUIData.chip.getRegL() - 1));	
					
					if((GUIData.chip.getRegL() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getRegL() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegL() & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x35) {
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					if(((Byte.toUnsignedInt((byte)(~memorydata)) & 0xf)) > 0x0f) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}
					
					GUIData.chip.setMemory(Integer.parseInt(memorylocation,16), (byte)(memorydata - 1));
					
					if((GUIData.chip.getMemory(Integer.parseInt(memorylocation,16)) & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(GUIData.chip.getMemory(Integer.parseInt(memorylocation,16)) == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getMemory(Integer.parseInt(memorylocation,16)) & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
								
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x03) {
					
					int pair = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegB())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegC())));
			    	pair = pair + 1;
					
			    	GUIData.chip.setRegC((byte)(pair & 0xff));
			    	GUIData.chip.setRegB((byte)((pair >> 8) & 0xff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x13) {
					
					int pair = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegD())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegE())));
			    	pair = pair + 1;
					
			    	GUIData.chip.setRegE((byte)(pair & 0xff));
			    	GUIData.chip.setRegD((byte)((pair >> 8) & 0xff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x23) {
					
					int pair = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegH())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegL())));
			    	pair = pair + 1;
					
			    	GUIData.chip.setRegL((byte)(pair & 0xff));
			    	GUIData.chip.setRegH((byte)((pair >> 8) & 0xff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x33) {
					
					int pair = (int) GUIData.chip.getStackPointer();
			    	pair = pair + 1;
					
			    	GUIData.chip.setStackPointer((char) (pair & 0xffff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x0B) {
					
					int pair = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegB())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegC())));
			    	pair = pair - 1;
					
			    	GUIData.chip.setRegC((byte)(pair & 0xff));
			    	GUIData.chip.setRegB((byte)((pair >> 8) & 0xff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x1B) {
					
					int pair = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegD())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegE())));
			    	pair = pair - 1;
					
			    	GUIData.chip.setRegE((byte)(pair & 0xff));
			    	GUIData.chip.setRegD((byte)((pair >> 8) & 0xff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x2B) {
					
					int pair = (int) ((Byte.toUnsignedInt(GUIData.chip.getRegH())*0x100) + (Byte.toUnsignedInt(GUIData.chip.getRegL())));
			    	pair = pair - 1;
					
			    	GUIData.chip.setRegL((byte)(pair & 0xff));
			    	GUIData.chip.setRegH((byte)((pair >> 8) & 0xff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x3B) {
					
					int pair = (int) GUIData.chip.getStackPointer();
			    	pair = pair - 1;
					
			    	GUIData.chip.setStackPointer((char) (pair & 0xffff));
			    	
			    	org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x27) {
					
					char bcd = (char) Byte.toUnsignedInt(GUIData.chip.getRegA());
					
					if(GUIData.chip.getAuxCarryFlag() == true || (GUIData.chip.getRegA() & 0xf) > 0x9) {
						
						if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + 6 > 0xf) {
							GUIData.chip.setAuxCarryFlag(true);
						}
						else {
							GUIData.chip.setAuxCarryFlag(false);
						}
						
						bcd = (char) (bcd + 0x6);
						
					}
					
					if(GUIData.chip.getCarryFlag() == true || (GUIData.chip.getRegA() >> 4 & 0xf) > 0x9) {
						
						if((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xff) + 0x60 > 0xff) {
							GUIData.chip.setCarryFlag(true);
						}
						else {
							GUIData.chip.setCarryFlag(false);
						}
						
						bcd = (char) (bcd + 0x60);
												
					}
					
					GUIData.chip.setRegA((byte) (bcd & 0xff));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA7) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getRegA()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA0) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getRegB()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA1) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getRegC()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA2) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getRegD()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA3) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getRegE()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA4) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getRegH()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA5) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getRegL()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA6) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & memorydata));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xE6) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(true);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() & GUIData.chip.getMemory(org+1)));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 2;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB7) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getRegA()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB0) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getRegB()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB1) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getRegC()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB2) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getRegD()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB3) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getRegE()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB4) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getRegH()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB5) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getRegL()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB6) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | memorydata));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xF6) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() | GUIData.chip.getMemory(org+1)));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 2;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xAF) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getRegA()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA8) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getRegB()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xA9) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getRegC()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xAA) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getRegD()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xAB) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getRegE()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xAC) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getRegH()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xAD) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getRegL()));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xAE) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ memorydata));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xEE) {
					
					GUIData.chip.setCarryFlag(false);
					GUIData.chip.setAuxCarryFlag(false);
					
					GUIData.chip.setRegA((byte)(GUIData.chip.getRegA() ^ GUIData.chip.getMemory(org+1)));
					
					if((GUIData.chip.getRegA() & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
				
					if(GUIData.chip.getRegA() == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
				
					String paritycheck = String.format("%8s", Integer.toBinaryString(GUIData.chip.getRegA() & 0xFF)).replace(' ', '0');
				
					int count = 0;
				
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
				
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}					
					
					org = org + 2;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x2F) {
					
					GUIData.chip.setRegA((byte) (~GUIData.chip.getRegA()));
					
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x3F) {
					
					if(GUIData.chip.getCarryFlag() == true) {
						GUIData.chip.setCarryFlag(false);
					}
					else if(GUIData.chip.getCarryFlag() == false) {
						GUIData.chip.setCarryFlag(true);
					}
					
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x37) {
					
					GUIData.chip.setCarryFlag(true);
					
					org = org + 1;					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xBF) {
								
						
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegA()));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegA()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB8) {
					
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegB()));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegB()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xB9) {
					
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegC()));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegC()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xBA) {
					
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegD()));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegD()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xBB) {
					
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegE()));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegE()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xBC) {
					
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegH()));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegH()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xBD) {
					
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getRegL()));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getRegL()) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xBE) {
					
					String memorylocation = Integer.toHexString(GUIData.chip.getRegH() & 0xff) + Integer.toHexString(GUIData.chip.getRegL() & 0xff);
					
					byte memorydata = GUIData.chip.getMemory(Integer.parseInt(memorylocation,16));
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(memorydata));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~memorydata) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xFE) {
					
					
					char carrycheck = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) - Byte.toUnsignedInt(GUIData.chip.getMemory(org+1)));
					
					byte compare = (byte) carrycheck;
						
					if(carrycheck > 0xff) {
						GUIData.chip.setCarryFlag(true);
					}
					else {
						GUIData.chip.setCarryFlag(false);
					}
						
					if(((Byte.toUnsignedInt(GUIData.chip.getRegA()) & 0xf) + (Byte.toUnsignedInt((byte) ~GUIData.chip.getMemory(org+1)) & 0xf) + 1 > 0x0f)) {
						GUIData.chip.setAuxCarryFlag(true);
					}	
					else {
						GUIData.chip.setAuxCarryFlag(false);
					}					
					
					if((compare & 0b10000000) == 0b10000000) {
						GUIData.chip.setSignFlag(true);
					}
					else {
						GUIData.chip.setSignFlag(false);
					}
					
					if(compare == 0x0) {
						GUIData.chip.setZeroFlag(true);
					}
					else {
						GUIData.chip.setZeroFlag(false);
					}
					
					String paritycheck = String.format("%8s", Integer.toBinaryString(compare & 0xFF)).replace(' ', '0');
					
					int count = 0;
					
					for(int checki = 0; checki<paritycheck.length(); checki++) {
						if(paritycheck.charAt(checki) == '1') {
							++count;
						}
					}
					
					if(count % 2 == 0) {
						GUIData.chip.setParityFlag(true);
					}
					else {
						GUIData.chip.setParityFlag(false);
					}
					
					org = org + 2;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x07) {
					
					char rotate = (char) Byte.toUnsignedInt(GUIData.chip.getRegA());
					
					char test = (char) (rotate << 1);
					
					if(test >> 8 == 0x1) {
						
						GUIData.chip.setCarryFlag(true);
						test = (char) (test | 0b0000000000000001);
						GUIData.chip.setRegA((byte) (test & 0xff));
						
					}
					else if (test >> 8 == 0x0){
						
						GUIData.chip.setCarryFlag(false);
						test = (char) (test & 0b1111111111111110);
						GUIData.chip.setRegA((byte) (test & 0xff));
						
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x0F) {
					
					char rotate = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) * 0x10);
					
					char test = (char) (rotate >> 1);
					
					if((test & 0x000f) == 0x8) {
						
						GUIData.chip.setCarryFlag(true);
						rotate = (char) (rotate >> 5);
						rotate = (char) (rotate | 0b0000000010000000);
						GUIData.chip.setRegA((byte) (rotate & 0xff));
						
					}
					else if((test & 0x000f) == 0x0) {
						
						GUIData.chip.setCarryFlag(false);
						rotate = (char) (rotate >> 5);
						GUIData.chip.setRegA((byte) (rotate & 0xff));
						
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x17) {
					
					char rotate = (char) Byte.toUnsignedInt(GUIData.chip.getRegA());
					
					char test = (char) (rotate << 1);
					
					if(GUIData.chip.getCarryFlag() == true) {
						
						if(test >> 8 == 0x1) {
							
							GUIData.chip.setCarryFlag(true);
							test = (char) (test | 0b0000000000000001); 
							GUIData.chip.setRegA((byte) (test & 0xff));
							
						}
						else if (test >> 8 == 0x0) {
							
							GUIData.chip.setCarryFlag(false);
							test = (char) (test | 0b0000000000000001); 
							GUIData.chip.setRegA((byte) (test & 0xff));
						}
						
					}
					else {
						
						if(test >> 8 == 0x1) {
							
							GUIData.chip.setCarryFlag(true);
							test = (char) (test & 0b1111111111111110); 
							GUIData.chip.setRegA((byte) (test & 0xff));
							
						}
						else if (test >> 8 == 0x0) {
							
							GUIData.chip.setCarryFlag(false);
							test = (char) (test & 0b1111111111111110); 
							GUIData.chip.setRegA((byte) (test & 0xff));
						}
						
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0x1F) {
					
					char rotate = (char) (Byte.toUnsignedInt(GUIData.chip.getRegA()) * 0x10);
					
					char test = (char) (rotate >> 1);
					
					if(GUIData.chip.getCarryFlag() == true) {
						
						if((test & 0x000f) == 0x8) {
							
							GUIData.chip.setCarryFlag(true);
							rotate = (char) (rotate >> 5);
							rotate = (char) (test | 0b0000000010000000); 
							GUIData.chip.setRegA((byte) (rotate & 0xff));
							
						}
						else if ((test & 0x000f) == 0x0) {
							
							GUIData.chip.setCarryFlag(false);
							rotate = (char) (rotate >> 5);
							rotate = (char) (test | 0b0000000010000000); 
							GUIData.chip.setRegA((byte) (rotate & 0xff));
						}
						
					}
					else {
						
						if((test & 0x000f) == 0x8) {
							
							GUIData.chip.setCarryFlag(true);
							rotate = (char) (rotate >> 5); 
							GUIData.chip.setRegA((byte) (rotate & 0xff));
							
						}
						else if (test >> 8 == 0x0) {
							
							GUIData.chip.setCarryFlag(false);
							rotate = (char) (rotate >> 5); 
							GUIData.chip.setRegA((byte) (rotate & 0xff));
						}
						
					}
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xFB) {
					
					GUIData.chip.setInterrupt(true);
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xF3) {
					
					GUIData.chip.setInterrupt(false);
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xE9) {
					
					org = ((Byte.toUnsignedInt(GUIData.chip.getRegH()) * 0x100) + Byte.toUnsignedInt(GUIData.chip.getRegL()));
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xF9) {
					
					GUIData.chip.setStackPointer((char) ((Byte.toUnsignedInt(GUIData.chip.getRegH()) * 0x100) + Byte.toUnsignedInt(GUIData.chip.getRegL())));
					
					org = org + 1;
					
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xE3) {
					
					try {
					byte hdata = GUIData.chip.getRegH();
					byte ldata = GUIData.chip.getRegL();
					
					GUIData.chip.setRegH(GUIData.chip.getMemory(GUIData.chip.getStackPointer() + 1));
					GUIData.chip.setRegL(GUIData.chip.getMemory(GUIData.chip.getStackPointer()));
					
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), ldata);
					GUIData.chip.setMemory(GUIData.chip.getStackPointer() + 1, hdata);
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xC5) {
					
					try {
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), GUIData.chip.getRegB());
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), GUIData.chip.getRegC());
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xD5) {
					
					try {
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), GUIData.chip.getRegD());
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), GUIData.chip.getRegE());
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xE5) {
					
					try {
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), GUIData.chip.getRegH());
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), GUIData.chip.getRegL());
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xF5) {
					
					try {
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), (byte) (GUIData.psw >> 8));
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					GUIData.chip.setMemory(GUIData.chip.getStackPointer(), (byte) (GUIData.psw & 0xff));
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() - 1));
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xC1) {
					
					try {
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					GUIData.chip.setRegC(GUIData.chip.getMemory(GUIData.chip.getStackPointer()));		
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					GUIData.chip.setRegB(GUIData.chip.getMemory(GUIData.chip.getStackPointer()));	
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xD1) {
					
					try {
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					GUIData.chip.setRegE(GUIData.chip.getMemory(GUIData.chip.getStackPointer()));		
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					GUIData.chip.setRegD(GUIData.chip.getMemory(GUIData.chip.getStackPointer()));	
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xE1) {
					
					try {
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					GUIData.chip.setRegL(GUIData.chip.getMemory(GUIData.chip.getStackPointer()));		
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					GUIData.chip.setRegH(GUIData.chip.getMemory(GUIData.chip.getStackPointer()));	
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xF1) {
					
					try {
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					byte low = GUIData.chip.getMemory(GUIData.chip.getStackPointer());		
					GUIData.chip.setStackPointer((char) (GUIData.chip.getStackPointer() + 1));
					byte high = GUIData.chip.getMemory(GUIData.chip.getStackPointer());
					GUIData.psw = (char) ((Byte.toUnsignedInt(high) * 0x100) + (Byte.toUnsignedInt(low)));
					GUIData.chip.setRegA(high);
					
					if((GUIData.psw & 0b0000000010000000) == 0b0000000010000000) {
						
						GUIData.chip.setSignFlag(true);
						
					}
					else {
						
						GUIData.chip.setSignFlag(false);
						
					}
					
					if((GUIData.psw & 0b0000000001000000) == 0b0000000001000000) {
						
						GUIData.chip.setZeroFlag(true);
						
					}
					else {
						
						GUIData.chip.setZeroFlag(false);
						
					}
					
					if((GUIData.psw & 0b0000000000010000) == 0b0000000000010000) {
						
						GUIData.chip.setAuxCarryFlag(true);
						
					}
					else {
						
						GUIData.chip.setAuxCarryFlag(false);
						
					}
					
					if((GUIData.psw & 0b0000000000000100) == 0b0000000000000100) {
						
						GUIData.chip.setParityFlag(true);
						
					}
					else {
						
						GUIData.chip.setParityFlag(false);
						
					}
					
					if((GUIData.psw & 0b0000000000000001) == 0b0000000000000001) {
						
						GUIData.chip.setCarryFlag(true);
						
					}
					else {
						
						GUIData.chip.setCarryFlag(false);
						
					}
					
					
					}
					catch(Exception e) {
						GUIData.error_area.setText("Error Trying To Reference Data Out Of Bounds");
					}
					org = org + 1;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xDB) {
					GUIData.chip.setRegA(GUIData.port_data[GUIData.chip.getMemory((org+1))]);
					org = org + 2;
				}
				else if((GUIData.chip.getMemory(org) & 0xff) == 0xD3) {
					GUIData.writeTo = Byte.toUnsignedInt(GUIData.chip.getMemory(org + 1));
					Main.runClient(GUIData.system_port[1]);
					org = org + 2;
				}
			
			
			 if (org >=65536) {
				return -1;
			}	
			
			return org;		
	}
	
	public String processLabels(String program, ArrayList<Integer> origin) {
		
		String addressFixed = "";
		ArrayList <String> labels = new ArrayList<String>();
		ArrayList <String> labellocations = new ArrayList<String>();
		int address = 0;
		
		int counter = 0;
		
		int totallabels = 0;
		
		String[] lines = program.split("\\r?\\n");
		
		int i = 0;
		while(i < lines.length) {
			if(lines[i].contains("XX")) {			
				address = origin.get(counter);	
				counter++;
			}	
			else if(lines[i].contains(":")) {				
				labels.add(lines[i].replaceAll(" ", "").replaceAll("\n", "").replaceAll(":",""));
				labellocations.add(Integer.toString((address)));	
			}
			 else if(lines[i].length() > 3 && (lines[i].substring(0, 3).equals("CD ") || lines[i].substring(0, 3).equals("DC ") || lines[i].substring(0, 3).equals("FC ") 
			    		|| lines[i].substring(0, 3).equals("D4 ") || lines[i].substring(0, 3).equals("C4 ") || lines[i].substring(0, 3).equals("F4 ") 
			    		|| lines[i].substring(0, 3).equals("EC ") || lines[i].substring(0, 3).equals("E4 ") || lines[i].substring(0, 3).equals("CC ")
			    		|| lines[i].substring(0, 3).equals("DA ") || lines[i].substring(0, 3).equals("FA ") || lines[i].substring(0, 3).equals("C3 ")
			    		|| lines[i].substring(0, 3).equals("D2") || lines[i].substring(0, 3).equals("C2 ") || lines[i].substring(0, 3).equals("F2 ")
			    		|| lines[i].substring(0, 3).equals("EA ") || lines[i].substring(0, 3).equals("E2 ") || lines[i].substring(0, 3).equals("CA "))) {
				 
				 totallabels++;
				 address = address + 3;
				 
			 }
			else {	
				
				int spaces = 0;
				for(int j = 0; j < lines[i].length(); j++) {
					if(lines[i].charAt(j) == ' ') {
						spaces++;
					}
				}
				
				address = address + spaces + 1;	
				
			}
			
			i++;
		}	
		
		if(totallabels == 0) {
			GUIData.error_area.setText("No Labels Found. Program Is Ready For Compilation!");
			return program;
		}
		
		i = 0;
		address = 0;
		counter = 0;
		
		while(i < lines.length) {
			
			if(lines[i].contains(":") || lines[i].contains("XX")) {
				if(lines[i].contains("XX")) {
				address = origin.get(counter);
				counter++;
				addressFixed = addressFixed + lines[i] + "\n";
				}				
			}
		    else if(lines[i].length() > 3 && (lines[i].substring(0, 3).equals("CD ") || lines[i].substring(0, 3).equals("DC ") || lines[i].substring(0, 3).equals("FC ") 
		    		|| lines[i].substring(0, 3).equals("D4 ") || lines[i].substring(0, 3).equals("C4 ") || lines[i].substring(0, 3).equals("F4 ") 
		    		|| lines[i].substring(0, 3).equals("EC ") || lines[i].substring(0, 3).equals("E4 ") || lines[i].substring(0, 3).equals("CC ")
		    		|| lines[i].substring(0, 3).equals("DA ") || lines[i].substring(0, 3).equals("FA ") || lines[i].substring(0, 3).equals("C3 ")
		    		|| lines[i].substring(0, 3).equals("D2") || lines[i].substring(0, 3).equals("C2 ") || lines[i].substring(0, 3).equals("F2 ")
		    		|| lines[i].substring(0, 3).equals("EA ") || lines[i].substring(0, 3).equals("E2 ") || lines[i].substring(0, 3).equals("CA "))) {
				
				String s = lines[i].substring(3);
				boolean found = false;
				
				for(int j = 0; j<labels.size();j++) {
					
					if(s.equals(labels.get(j))) {
						
						String hex = String.format("%04X", Integer.parseInt(labellocations.get(j)));
						hex = hex.substring(0, 2) + " " + hex.substring(2, 4);
						addressFixed = addressFixed + lines[i].substring(0, 3) + hex + "\n";
						found = true;
					
					}
				
				}
				
				if(found == false) {
					GUIData.error_area.setText("Error Finding Label - " + lines[i].substring(3));
					return null;
					
				}
				
				address = address + 3;
				
			}
			else {	
				
				int spaces = 0;
				for(int j = 0; j < lines[i].length(); j++) {
					if(lines[i].charAt(j) == ' ') {
						spaces++;
					}
				}
				
				address = address + spaces + 1;
				addressFixed = addressFixed + lines[i] + "\n";		
				
			}
			i++;
		}
		
		GUIData.error_area.setText("Replaced Labels Successfully!");
		return addressFixed;
	}
	
	public boolean fixMemory(String opcode, ArrayList<Integer> org) {
		boolean b = false;
		
		String[] lines = opcode.split("\\r?\\n");
		
		int counter = 0;
		int point = 0;
		int i = 0;
		
		while(i<lines.length) {
			try {
				
				String s = lines[i].replaceAll(" ", "");
				
				if(s.contains("XX")) {
					point = org.get(counter);
					counter++;
				}
				else if(s.contains(":")) {
					//Do Nothing!!!
				}
				else if(s.length() == 2) {
					
					GUIData.chip.setMemory(point, GUIData.chip.formatHexToByte(s.substring(0, 2)));
					++point;
				}
				else if(s.length() == 4) {
					
					GUIData.chip.setMemory(point, GUIData.chip.formatHexToByte(s.substring(0, 2)));
					++point;
					GUIData.chip.setMemory(point, GUIData.chip.formatHexToByte(s.substring(2, 4)));
					++point;					
				}
				else if(s.length() == 6) {
					
					GUIData.chip.setMemory(point, GUIData.chip.formatHexToByte(s.substring(0, 2)));
					++point;
					GUIData.chip.setMemory(point, GUIData.chip.formatHexToByte(s.substring(2, 4)));
					++point;		
					GUIData.chip.setMemory(point, GUIData.chip.formatHexToByte(s.substring(4, 6)));
					++point;
				}
			}
			catch(Exception e) {
				GUIData.error_area.setText("Program Exceeds Available Memory OR Jump To Label Not Found! Kindly Change The Origination Point Of The Program Or Reduce The Size Of The Program");
				return b;
			}			
			i++;	
		}
		
		b = true;
		return b;
	}

}

