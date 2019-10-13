package sim8085;

import java.util.ArrayList; 

public class ASMTranslator {

	private String OpCode = ""; 
	private ArrayList<String> OpCodeList = new ArrayList<String>(); 
	
 public String translateASMToOpcode(String ASM) {
	if(ASM.replace(" ", "").replace("\n", "").replaceAll("\t", "").length() > 1) {
		
		int oldASMStart = 0;
		for(int i = 0; i<ASM.length(); i++) {
			if(ASM.charAt(i) == '\n') {
				if(ASM.substring(oldASMStart, i).replace("\n", "").length() > 1) {
				this.OpCodeList.add(ASM.substring(oldASMStart, i));
				oldASMStart = i+1;
				}
				else {
					oldASMStart++;
				}
			}
			else if(i == (ASM.length() - 1)) {
				if(ASM.substring(oldASMStart).replace("\n", "").length() > 1) {
				this.OpCodeList.add(ASM.substring(oldASMStart).replace("\n", ""));
				}
			}
		}
		
		int i = 0;
		
		while(i < this.OpCodeList.size()) {
			
		String s = OpCodeList.get(i);
		int commentLoc = OpCodeList.get(i).indexOf(";");
		if(commentLoc > 0) {
	    s = this.OpCodeList.get(i).substring(0, commentLoc);
		}
		
		boolean found = false;
		
		s = s.trim();
		
		if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cp ") ) {
			if(s.replace(" ", "").replace("\t", "").substring(2).length() >= 1) {				
				this.OpCode = this.OpCode + "F4 " + s.replace(" ", "").substring(2).toUpperCase() + "\n";
				found = true;
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cm ")) {
			if(s.replace(" ", "").replace("\t", "").substring(2).length() >= 1) {				
				this.OpCode = this.OpCode + "FC " + s.replace(" ", "").substring(2).toUpperCase() + "\n";
				found = true;
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("jp ")) {
			if(s.replace(" ", "").replace("\t", "").substring(2).length() >= 1) {				
				this.OpCode = this.OpCode + "F2 " + s.replace(" ", "").substring(2).toUpperCase() + "\n";
				found = true;
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("jm ")) {
			if(s.replace(" ", "").replace("\t", "").substring(2).length() >= 1) {	
				this.OpCode = this.OpCode + "FA " + s.replace(" ", "").substring(2).toUpperCase() + "\n";	
				found = true;
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}	
		}
						
		s = s.toLowerCase().replace(" ", "");
		s = s.replace("\t", "");
		
		if(s.contains(":")) {
			int pointUntilLabelDecl = s.indexOf(":");
			this.OpCode = this.OpCode + s.toUpperCase().substring(0, pointUntilLabelDecl+1) + "\n";
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("aci") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {	
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {			
				this.OpCode = this.OpCode + "CE " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("adc") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "8F\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "88\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "89\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "8A\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "8B\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "8C\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "8D\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "8E\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("add") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "87\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "80\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "81\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "82\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "83\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "84\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "85\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "86\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("adi") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "C6 " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("ana") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "A7\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "A0\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "A1\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "A2\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "A3\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "A4\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "A5\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "A6\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("ani") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "E6 " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cma") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "2F\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cmc") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "3F\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cmp") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "BF\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "B8\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "B9\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "BA\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "BB\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "BC\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "BD\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "BE\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cpi") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "FE " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("daa") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "27\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("dcr") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "3D\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "05\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "0D\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "15\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "1D\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "25\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "2D\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "35\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("dad") && found == false) {
			if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "09\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "19\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "29\n";
			}
			else if(s.substring(3).equals("sp")) {
				this.OpCode = this.OpCode + "39\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("dcx") && found == false) {
			if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "0B\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "1B\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "2B\n";
			}
			else if(s.substring(3).equals("sp")) {
				this.OpCode = this.OpCode + "3B\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("di") && found == false) {
			if(s.substring(2).length() == 0) {
				this.OpCode = this.OpCode + "F3\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("ei") && found == false) {
			if(s.substring(2).length() == 0) {
				this.OpCode = this.OpCode + "FB\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("hlt") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "76\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("in") && s.substring(2).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(2).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "DB " + s.substring(2).toUpperCase().replaceAll("H", "") + "\n";		
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("out") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "D3 " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";		
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("inr") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "3C\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "04\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "0C\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "14\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "1C\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "24\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "2C\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "34\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("inx") && found == false) {
			if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "03\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "13\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "23\n";
			}
			else if(s.substring(3).equals("sp")) {
				this.OpCode = this.OpCode + "33\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("ldax") && found == false) {
			if(s.substring(4).equals("b")) {
				this.OpCode = this.OpCode + "0A\n";
			}
			else if(s.substring(4).equals("d")) {
				this.OpCode = this.OpCode + "1A\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("lda") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 4) {				
				this.OpCode = this.OpCode + "3A " + s.substring(3, 5).toUpperCase() + " " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("lhld") && s.substring(4).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(4).toUpperCase().replaceAll("H", "").length() == 4) {				
				this.OpCode = this.OpCode + "2A " + s.substring(4, 6).toUpperCase() + " " + s.substring(6).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("lxi") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {
			if(s.substring(3,5).equals("b,") && s.substring(5).toUpperCase().replace("H", "").length() == 4) {
				this.OpCode = this.OpCode + "01 " + s.substring(5, 7).toUpperCase() + " " + s.substring(7).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).equals("d,") && s.substring(5).toUpperCase().replace("H", "").length() == 4) {
				this.OpCode = this.OpCode + "11 " + s.substring(5, 7).toUpperCase() + " " + s.substring(7).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).equals("h,") && s.substring(5).toUpperCase().replace("H", "").length() == 4) {
				this.OpCode = this.OpCode + "21 " + s.substring(5, 7).toUpperCase() + " " + s.substring(7).toUpperCase().replaceAll("H", "") + "\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("lxi") && s.substring(6).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {
			if(s.substring(3,6).equals("sp,") && s.substring(6).toUpperCase().replace("H", "").length() == 4) {
				this.OpCode = this.OpCode + "31 " + s.substring(6, 8).toUpperCase() + " " + s.substring(8).toUpperCase().replaceAll("H", "") + "\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}		
		}		
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("mvi") && found == false) {
			if(s.substring(3,5).replace(" ", "").toLowerCase().equals("a,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "3E " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).replace(" ", "").toLowerCase().equals("b,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "06 " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).replace(" ", "").toLowerCase().equals("c,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "0E " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).replace(" ", "").toLowerCase().equals("d,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "16 " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).replace(" ", "").toLowerCase().equals("e,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "1E " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).replace(" ", "").toLowerCase().equals("h,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "26 " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).replace(" ", "").toLowerCase().equals("l,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "2E " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else if(s.substring(3,5).replace(" ", "").toLowerCase().equals("m,") && s.substring(5).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && s.substring(5).toUpperCase().replaceAll("H", "").length() == 2) {
				this.OpCode = this.OpCode + "36 " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("nop") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "00\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("mov") && found == false) {
			if(s.substring(3).equals("a,a")) {
				this.OpCode = this.OpCode + "7F\n";
			}
			else if(s.substring(3).equals("a,b")) {
				this.OpCode = this.OpCode + "78\n";
			}
			else if(s.substring(3).equals("a,c")) {
				this.OpCode = this.OpCode + "79\n";
			}
			else if(s.substring(3).equals("a,d")) {
				this.OpCode = this.OpCode + "7A\n";
			}
			else if(s.substring(3).equals("a,e")) {
				this.OpCode = this.OpCode + "7B\n";
			}
			else if(s.substring(3).equals("a,h")) {
				this.OpCode = this.OpCode + "7C\n";
			}
			else if(s.substring(3).equals("a,l")) {
				this.OpCode = this.OpCode + "7D\n";
			}
			else if(s.substring(3).equals("a,m")) {
				this.OpCode = this.OpCode + "7E\n";
			}
			else if(s.substring(3).equals("b,a")) {
				this.OpCode = this.OpCode + "47\n";
			}
			else if(s.substring(3).equals("b,b")) {
				this.OpCode = this.OpCode + "40\n";
			}
			else if(s.substring(3).equals("b,c")) {
				this.OpCode = this.OpCode + "41\n";
			}
			else if(s.substring(3).equals("b,d")) {
				this.OpCode = this.OpCode + "42\n";
			}
			else if(s.substring(3).equals("b,e")) {
				this.OpCode = this.OpCode + "43\n";
			}
			else if(s.substring(3).equals("b,h")) {
				this.OpCode = this.OpCode + "44\n";
			}
			else if(s.substring(3).equals("b,l")) {
				this.OpCode = this.OpCode + "45\n";
			}
			else if(s.substring(3).equals("b,m")) {
				this.OpCode = this.OpCode + "46\n";
			}
			else if(s.substring(3).equals("c,a")) {
				this.OpCode = this.OpCode + "4F\n";
			}
			else if(s.substring(3).equals("c,b")) {
				this.OpCode = this.OpCode + "48\n";
			}
			else if(s.substring(3).equals("c,c")) {
				this.OpCode = this.OpCode + "49\n";
			}
			else if(s.substring(3).equals("c,d")) {
				this.OpCode = this.OpCode + "4A\n";
			}
			else if(s.substring(3).equals("c,e")) {
				this.OpCode = this.OpCode + "4B\n";
			}
			else if(s.substring(3).equals("c,h")) {
				this.OpCode = this.OpCode + "4C\n";
			}
			else if(s.substring(3).equals("c,l")) {
				this.OpCode = this.OpCode + "4D\n";
			}
			else if(s.substring(3).equals("c,m")) {
				this.OpCode = this.OpCode + "4E\n";
			}
			else if(s.substring(3).equals("d,a")) {
				this.OpCode = this.OpCode + "57\n";
			}
			else if(s.substring(3).equals("d,b")) {
				this.OpCode = this.OpCode + "50\n";
			}
			else if(s.substring(3).equals("d,c")) {
				this.OpCode = this.OpCode + "51\n";
			}
			else if(s.substring(3).equals("d,d")) {
				this.OpCode = this.OpCode + "52\n";
			}
			else if(s.substring(3).equals("d,e")) {
				this.OpCode = this.OpCode + "53\n";
			}
			else if(s.substring(3).equals("d,h")) {
				this.OpCode = this.OpCode + "54\n";
			}
			else if(s.substring(3).equals("d,l")) {
				this.OpCode = this.OpCode + "55\n";
			}
			else if(s.substring(3).equals("d,m")) {
				this.OpCode = this.OpCode + "56\n";
			}
			else if(s.substring(3).equals("e,a")) {
				this.OpCode = this.OpCode + "5F\n";
			}
			else if(s.substring(3).equals("e,b")) {
				this.OpCode = this.OpCode + "58\n";
			}
			else if(s.substring(3).equals("e,c")) {
				this.OpCode = this.OpCode + "59\n";
			}
			else if(s.substring(3).equals("e,d")) {
				this.OpCode = this.OpCode + "5A\n";
			}
			else if(s.substring(3).equals("e,e")) {
				this.OpCode = this.OpCode + "5B\n";
			}
			else if(s.substring(3).equals("e,h")) {
				this.OpCode = this.OpCode + "5C\n";
			}
			else if(s.substring(3).equals("e,l")) {
				this.OpCode = this.OpCode + "5D\n";
			}
			else if(s.substring(3).equals("e,m")) {
				this.OpCode = this.OpCode + "5E\n";
			}
			else if(s.substring(3).equals("h,a")) {
				this.OpCode = this.OpCode + "67\n";
			}
			else if(s.substring(3).equals("h,b")) {
				this.OpCode = this.OpCode + "60\n";
			}
			else if(s.substring(3).equals("h,c")) {
				this.OpCode = this.OpCode + "61\n";
			}
			else if(s.substring(3).equals("h,d")) {
				this.OpCode = this.OpCode + "62\n";
			}
			else if(s.substring(3).equals("h,e")) {
				this.OpCode = this.OpCode + "63\n";
			}
			else if(s.substring(3).equals("h,h")) {
				this.OpCode = this.OpCode + "64\n";
			}
			else if(s.substring(3).equals("h,l")) {
				this.OpCode = this.OpCode + "65\n";
			}
			else if(s.substring(3).equals("h,m")) {
				this.OpCode = this.OpCode + "66\n";
			}
			else if(s.substring(3).equals("l,a")) {
				this.OpCode = this.OpCode + "6F\n";
			}
			else if(s.substring(3).equals("l,b")) {
				this.OpCode = this.OpCode + "68\n";
			}
			else if(s.substring(3).equals("l,c")) {
				this.OpCode = this.OpCode + "69\n";
			}
			else if(s.substring(3).equals("l,d")) {
				this.OpCode = this.OpCode + "6A\n";
			}
			else if(s.substring(3).equals("l,e")) {
				this.OpCode = this.OpCode + "6B\n";
			}
			else if(s.substring(3).equals("l,h")) {
				this.OpCode = this.OpCode + "6C\n";
			}
			else if(s.substring(3).equals("l,l")) {
				this.OpCode = this.OpCode + "6D\n";
			}
			else if(s.substring(3).equals("l,m")) {
				this.OpCode = this.OpCode + "6E\n";
			}
			else if(s.substring(3).equals("m,a")) {
				this.OpCode = this.OpCode + "77\n";
			}
			else if(s.substring(3).equals("m,b")) {
				this.OpCode = this.OpCode + "70\n";
			}
			else if(s.substring(3).equals("m,c")) {
				this.OpCode = this.OpCode + "71\n";
			}
			else if(s.substring(3).equals("m,d")) {
				this.OpCode = this.OpCode + "72\n";
			}
			else if(s.substring(3).equals("m,e")) {
				this.OpCode = this.OpCode + "73\n";
			}
			else if(s.substring(3).equals("m,h")) {
				this.OpCode = this.OpCode + "74\n";
			}
			else if(s.substring(3).equals("m,l")) {
				this.OpCode = this.OpCode + "75\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("ora") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "B7\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "B0\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "B1\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "B2\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "B3\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "B4\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "B5\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "B6\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("ori") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "F6 " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";		
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("pchl") && found == false) {			
			if(s.substring(4).toUpperCase().replaceAll("H", "").length() == 0) {				
				this.OpCode = this.OpCode + "E9\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("pop") && found == false) {
			if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "C1\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "D1\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "E1\n";
			}
			else if(s.substring(3).equals("psw")) {
				this.OpCode = this.OpCode + "F1\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("push") && found == false) {
			if(s.substring(4).equals("b")) {
				this.OpCode = this.OpCode + "C5\n";
			}
			else if(s.substring(4).equals("d")) {
				this.OpCode = this.OpCode + "D5\n";
			}
			else if(s.substring(4).equals("h")) {
				this.OpCode = this.OpCode + "E5\n";
			}
			else if(s.substring(4).equals("psw")) {
				this.OpCode = this.OpCode + "F5\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("ral") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "17\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rar") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "1F\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("rc") && found == false) {
			if(s.substring(2).length() == 0) {
				this.OpCode = this.OpCode + "D8\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("ret") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "C9\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rim") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "20\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rlc") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "07\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("rm") && found == false) {
			if(s.substring(2).length() == 0) {
				this.OpCode = this.OpCode + "F8\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rnc") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "D0\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rnz") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "C0\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rpe") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "E8\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rpo") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "E0\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("rp") && found == false) {
			if(s.substring(2).length() == 0) {
				this.OpCode = this.OpCode + "F0\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("rz") && found == false) {
			if(s.substring(2).length() == 0) {
				this.OpCode = this.OpCode + "C8\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rrc") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "0F\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("rst") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("0")) {
				this.OpCode = this.OpCode + "C7\n";
			}
			else if(s.substring(3).equals("1")) {
				this.OpCode = this.OpCode + "CF\n";
			}
			else if(s.substring(3).equals("2")) {
				this.OpCode = this.OpCode + "D7\n";
			}
			else if(s.substring(3).equals("3")) {
				this.OpCode = this.OpCode + "DF\n";
			}
			else if(s.substring(3).equals("4")) {
				this.OpCode = this.OpCode + "E7\n";
			}
			else if(s.substring(3).equals("5")) {
				this.OpCode = this.OpCode + "EF\n";
			}
			else if(s.substring(3).equals("6")) {
				this.OpCode = this.OpCode + "F7\n";
			}
			else if(s.substring(3).equals("7")) {
				this.OpCode = this.OpCode + "FF\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("sbb") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "9F\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "98\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "99\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "9A\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "9B\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "9C\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "9D\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "9E\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("sbi") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "DE " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";		
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("shld") && s.substring(4).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+")) {			
			if(s.substring(4).toUpperCase().replaceAll("H", "").length() == 4) {				
				this.OpCode = this.OpCode + "22 " + s.substring(4, 6).toUpperCase() + " " + s.substring(6).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");		
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("sim") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "30\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("sphl") && found == false) {
			if(s.substring(4).length() == 0) {
				this.OpCode = this.OpCode + "F9\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("sta") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+")) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 4) {				
				this.OpCode = this.OpCode + "32 " + s.substring(3, 5).toUpperCase() + " " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");		
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("org") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+")) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 4) {				
				this.OpCode = this.OpCode + "XX " + s.substring(3, 5).toUpperCase() + " " + s.substring(5).toUpperCase().replaceAll("H", "") + "\n";			
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");		
				return null;
			}			
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("stax") && found == false) {
			 if(s.substring(4).equals("b")) {
				this.OpCode = this.OpCode + "02\n";
			}
			else if(s.substring(4).equals("d")) {
				this.OpCode = this.OpCode + "12\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}		
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("stc") && found == false) {
			if(s.substring(3).length() == 0) {
				this.OpCode = this.OpCode + "37\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("sub") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "97\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "90\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "91\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "92\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "93\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "94\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "95\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "96\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("sui") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "D6 " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";		
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("xri") && s.substring(3).toUpperCase().replaceAll("H", "").matches("-?[0-9a-fA-F]+") && found == false) {			
			if(s.substring(3).toUpperCase().replaceAll("H", "").length() == 2) {				
				this.OpCode = this.OpCode + "EE " + s.substring(3).toUpperCase().replaceAll("H", "") + "\n";		
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}			
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("xchg") && found == false) {
			if(s.substring(4).length() == 0) {
				this.OpCode = this.OpCode + "EB\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("xthl") && found == false) {
			if(s.substring(4).length() == 0) {
				this.OpCode = this.OpCode + "E3\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("xra") && found == false) {
			if(s.substring(3).replace(" ", "").toLowerCase().equals("a")) {
				this.OpCode = this.OpCode + "AF\n";
			}
			else if(s.substring(3).equals("b")) {
				this.OpCode = this.OpCode + "A8\n";
			}
			else if(s.substring(3).equals("c")) {
				this.OpCode = this.OpCode + "A9\n";
			}
			else if(s.substring(3).equals("d")) {
				this.OpCode = this.OpCode + "AA\n";
			}
			else if(s.substring(3).equals("e")) {
				this.OpCode = this.OpCode + "AB\n";
			}
			else if(s.substring(3).equals("h")) {
				this.OpCode = this.OpCode + "AC\n";
			}
			else if(s.substring(3).equals("l")) {
				this.OpCode = this.OpCode + "AD\n";
			}
			else if(s.substring(3).equals("m")) {
				this.OpCode = this.OpCode + "AE\n";
			}
			else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}			
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("jc") && found == false) {
			if(s.substring(2).length() >= 1) {				
				this.OpCode = this.OpCode + "DA " + s.substring(2).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("jmp") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "C3 " + s.substring(3).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("jnc") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "D2 " + s.substring(3).toUpperCase() + "\n";
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("jnz") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "C2 " + s.substring(3).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("jpe") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "EA " + s.substring(3).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("jpo") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "E2 " + s.substring(3).toUpperCase() + "\n";
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("jz") && found == false) {
			if(s.substring(2).length() >= 1) {				
				this.OpCode = this.OpCode + "CA " + s.substring(2).toUpperCase() + "\n";
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 4 && s.substring(0,4).toLowerCase().equals("call") && found == false) {
			if(s.substring(4).length() >= 1) {				
				this.OpCode = this.OpCode + "CD " + s.substring(4).toUpperCase() + "\n";
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("cc") && found == false) {
			if(s.substring(2).length() >= 1) {				
				this.OpCode = this.OpCode + "DC " + s.substring(2).toUpperCase() + "\n";
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cnc") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "D4 " + s.substring(3).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cnz") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "C4 " + s.substring(3).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cpe") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "EC " + s.substring(3).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}
		else if(s.length() >= 3 && s.substring(0,3).toLowerCase().equals("cpo") && found == false) {
			if(s.substring(3).length() >= 1) {				
				this.OpCode = this.OpCode + "E4 " + s.substring(3).toUpperCase() + "\n";	
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");	
				return null;
			}	
		}		
		else if(s.length() >= 2 && s.substring(0,2).toLowerCase().equals("cz") && found == false) {
			if(s.substring(2).length() >= 1) {				
				this.OpCode = this.OpCode + "CC " + s.substring(2).toUpperCase() + "\n";
			}else {
				GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
				return null;
			}	
		}
		else {
			if(found != true) {
			GUIData.error_area.setText("Error Parsing in Line " + (i+1) + "! Kindly Check & Fix The Error!");
			return null;
			}
		}

		i++;
		}
	
	}
	else {
		
		this.OpCode = "*!!**!!**!!*"; 
		return OpCode;
		
	}
	
	return this.OpCode; 
	 
 }
	
}
