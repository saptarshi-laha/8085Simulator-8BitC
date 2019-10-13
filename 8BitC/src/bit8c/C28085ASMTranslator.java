package bit8c;

import java.util.ArrayList;

public class C28085ASMTranslator {

	private String finalOutputASM = "";
	private ArrayList<String> functions = new ArrayList<String>();
	private ArrayList<String> functionReturnType = new ArrayList<String>();
	private ArrayList<String> varnames = new ArrayList<String>();
	private ArrayList<String> vartypes = new ArrayList<String>();
	private ArrayList<Integer> varlocations = new ArrayList<Integer>();
	private int varloc = 65535;
	private int globalwhilecounter = 0;	
	
	String mainDecoder(String s) {	
		
		String lines[] = s.split("\\r?\\n");
		
		for(int i = 0; i<lines.length;i++) {
		
			int curly = 0;
		
		if(lines[i].replace(" ", "").startsWith("int") && lines[i].contains("(") && lines[i].contains("{")) {
			curly++;
			
			lines[i] = lines[i].replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "");
			int bracketIndex1 = lines[i].indexOf("(");
			String func = lines[i].substring(3, bracketIndex1);
			boolean found = false;
			
			for(int j = 0; j<functions.size(); j++) {
				
				if(functions.get(j).equals(func)) {
					found = true;
				}
				
			}		
			
			if(found == false) {
			functionReturnType.add("int");
			functions.add(func);
			finalOutputASM = finalOutputASM + func + ":" + "\n";
			
			
			while(curly != 0) {
			try {
			i++;	
			if(lines[i].contains("{")) {
				curly++;
			}
			else if(lines[i].contains("}")) {
				curly--;
			}
						
			
			
			if(lines[i].contains("__asm__{")) {
				int nowcurly = curly - 1;
				while(curly != nowcurly) {
					i++;
					if(lines[i].contains("}")) {
						curly--;
					}else {	
						finalOutputASM = finalOutputASM + lines[i].trim() + "\n";
					}}}
			else {
				
				if(lines[i].trim().toLowerCase().replace(" ", "").length() >=3 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,3).equals("int")) {
					int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int oldstart = 3;
					for(int j = 0; j<lines[i].length(); j++) {	
						if(lines[i].charAt(j) == ',') {
							String name = lines[i].substring(oldstart, j);
							oldstart = j+1;
							boolean foundvar = false;
							for(int k = 0; k<varnames.size(); k++) {
								if(name.equals(varnames.get(k))) {
									foundvar = true;
								}
							}
							
							if(foundvar == false) {
								varnames.add(name);
								vartypes.add("int");
								varloc = varloc - 2;
								varlocations.add(varloc);								
								finalOutputASM = finalOutputASM + "PUSH B\n";
							}								
						}						
					}
					
					String name = lines[i].substring(oldstart, endIndex);
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
		
					if(foundvar == false) {						
						varnames.add(name);
						vartypes.add("int");
						varloc = varloc - 2;
						varlocations.add(varloc);
						finalOutputASM = finalOutputASM + "PUSH B\n";
					}					
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=4 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,4).equals("byte")) {
									
					int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int oldstart = 4;
					for(int j = 0; j<lines[i].length(); j++) {	
						if(lines[i].charAt(j) == ',') {
							String name = lines[i].substring(oldstart, j);
							oldstart = j+1;
							boolean foundvar = false;
							for(int k = 0; k<varnames.size(); k++) {
								if(name.equals(varnames.get(k))) {
									foundvar = true;
								}
							}
							
							if(foundvar == false) {
								varnames.add(name);
								vartypes.add("byte");
								varloc = varloc - 1;
								varlocations.add(varloc);	
								int low = (varloc & 0xff);
								int high = ((varloc >> 8) & 0xff);
								finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X", high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X",low & 0xff).toUpperCase() + "H\nSPHL\n";
							}								
						}						
					}
					
					String name = lines[i].substring(oldstart, endIndex);
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
		
					if(foundvar == false) {						
						varnames.add(name);
						vartypes.add("byte");
						varloc = varloc - 1;
						varlocations.add(varloc);	
						int low = (varloc & 0xff);
						int high = ((varloc >> 8) & 0xff);
						finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X",high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X",low & 0xff).toUpperCase() +"H\nSPHL\n";
					}					
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=6 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,6).equals("while(") && (lines[i].trim().toLowerCase().replace(" ", "").contains("{"))) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int localwhilecounter = globalwhilecounter;
					
					if(lines[i].trim().toLowerCase().replace(" ", "").contains(">")) {
						
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf(">");
						int varindex1 = -1, varindex2 = -1;
					
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;	
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+1, bracket2).equals(varnames.get(a))) {
								varindex2 = a;		
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {			
							if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJZ end" + (localwhilecounter) + "\n";
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
						
					}
					else if(lines[i].trim().toLowerCase().replace(" ", "").contains("<")) {
						
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf("<");
						int varindex1 = -1, varindex2 = -1;
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+1, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {	
								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
					}
					else if(lines[i].trim().toLowerCase().replace(" ", "").contains("==")) {
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf("=");
						int varindex1 = -1, varindex2 = -1;
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+2, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJNZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
					}
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=7 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,6).equals("return")) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int semiColonIndex = lines[i].indexOf(";");
					int funcindex = -1, varindex1 = -1;
					
					for(int a = 0; a<functions.size(); a++) {						
						if (func.equals(functions.get(a))) {
							funcindex = a;							
						}					
					}					
					
					if(isNumeric(lines[i].substring(6, semiColonIndex))) {
						
						int totalPart = Integer.parseInt(lines[i].substring(6, semiColonIndex));
						int topPart = ((totalPart >> 8) & 0xff);
						int bottomPart = (totalPart & 0xff);
						
						if(functionReturnType.get(funcindex).equals("int")) {
							
							finalOutputASM = finalOutputASM + "MVI C, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI B, " + String.format("%02X",topPart).toUpperCase() + "H\n"
									+ "RET\n";
							
						}
						else if(functionReturnType.get(funcindex).equals("byte")) {
							
							finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X",bottomPart).toUpperCase() + "H\nRET\n";
							
						}
						
					}
					else {
						
						String varname = lines[i].substring(6, semiColonIndex);
						
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex1 = a;							
							}					
						}
						
						if(vartypes.get(varindex1).equals("int")) {
							
							finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nMOV C, L\nMOV B, H\n"
									+ "RET\n";							
							
						}
						else if(vartypes.get(varindex1).equals("byte")) {
						
							finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
							
						}						
					}
					
				}
				else if(lines[i].contains("=") && !(lines[i].contains("+") || lines[i].contains("-") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("%") || lines[i].contains("&") || lines[i].contains("|") || lines[i].contains("^")) && lines[i].contains(";")) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ","");
					
					int equalIndex = lines[i].indexOf("=");
					int semiColonIndex = lines[i].indexOf(";");
					String varname = lines[i].substring(0, equalIndex);
					int varindex1 = -1, varindex2 = -1;
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex1 = a;							
						}					
					}
					String value = lines[i].substring(equalIndex+1, semiColonIndex);
			
					if(isNumeric(value)) {
						
						int valueToBeSet = Integer.parseInt(value);
						int topPart = ((valueToBeSet >> 8) & 0xff);
						int bottomPart = (valueToBeSet & 0xff);

						if(vartypes.get(varindex1).equals("int")) {
							
							finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + "H\n"
									+ "SHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						else if(vartypes.get(varindex1).equals("byte")) {
							
							finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X",bottomPart).toUpperCase() + "H\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						
					}
					else {
						
						varname = value;
						
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex2 = a;							
							}					
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && (vartypes.get(varindex1).equals(vartypes.get(varindex2)))) {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSTA " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							
						}
						
					}
					
				}
				else if(lines[i].contains("=") && !(lines[i].contains("+") || lines[i].contains("-") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("%") || lines[i].contains("&") || lines[i].contains("|") || lines[i].contains("^")) && lines[i].contains(";")) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ","");
					
					int equalIndex = lines[i].indexOf("=");
					int semiColonIndex = lines[i].indexOf(";");
					String varname = lines[i].substring(0, equalIndex);
					int varindex1 = -1, varindex2 = -1;
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex1 = a;							
						}					
					}
					String value = lines[i].substring(equalIndex+1, semiColonIndex);
					if(isNumeric(value)) {
						
						int valueToBeSet = Integer.parseInt(value);
						int topPart = ((valueToBeSet >> 8) & 0xff);
						int bottomPart = (valueToBeSet & 0xff);
						
						if(vartypes.get(varindex1).equals("int")) {
							
							finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + "H\n"
									+ "SHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						else if(vartypes.get(varindex1).equals("byte")) {
							
							finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X",bottomPart).toUpperCase() + "H\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						
					}
					else {
						
						varname = value;
						
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex2 = a;							
							}					
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && (vartypes.get(varindex1).equals(vartypes.get(varindex2)))) {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSTA " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							
						}
						
					}
					
				}
				else if(lines[i].contains("=") && (lines[i].contains("+") || lines[i].contains("-") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("%") || lines[i].contains("&") || lines[i].contains("|") || lines[i].contains("^")) && lines[i].contains(";")) {
					lines[i] = lines[i].trim().toLowerCase().replace(" ","");
					
					int equalIndex = lines[i].indexOf("=");
					String varname = lines[i].substring(0, equalIndex);
					int operationLoc = -1;
					char operation = 0;
					int varindex1 = -1, varindex2 = -1, varindex3 = -1, funcindex = -1;
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex1 = a;							
						}					
					}
					
					for(int a = 0; a<lines[i].length(); a++) {
						if(lines[i].charAt(a) == '+' || lines[i].charAt(a) == '-' || lines[i].charAt(a) == '*' || lines[i].charAt(a) == '/' || lines[i].charAt(a) == '%' || lines[i].charAt(a) == '&' || lines[i].charAt(a) == '|' || lines[i].charAt(a) == '^') {
							operation = lines[i].charAt(a);
							operationLoc = a+1;
							varname = lines[i].substring(equalIndex+1, a);
						}
					}			
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex2 = a;							
						}					
					}
					if(lines[i].substring(operationLoc, lines[i].length() - 1).matches("[0-9]+") && varindex1 >= 0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
						
						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase()
										+"H\nCALL addi\nSHLD "
										+ String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {	
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nMVI A, " + String.format("%02X", b&0xff).toUpperCase() 
										+"H\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {	
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X", ((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + 
										"H\nMOV C, L\nMOV B, H\nMVI L, " +  String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " +  String.format("%02X",topPart).toUpperCase()
										+"H\nCALL subi\nSHLD "
										+  String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nMVI A, " + String.format("%02X", b&0xff).toUpperCase()  
										+"H\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
					
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 								
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
			
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
						
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '|') {
								
								if(vartypes.get(varindex1).equals("int")) {
									int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
									int topPart = ((totalPart >> 8) & 0xff);
									int bottomPart = (totalPart & 0xff);
									finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
											"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
									
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
									finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
											"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						
					}
					
					else if(lines[i].substring(operationLoc, lines[i].length() - 1).contains("(") && lines[i].substring(operationLoc, lines[i].length() - 1).contains(")")) {
						
						int indexOfStart = lines[i].indexOf("(");
						varname = lines[i].substring(operationLoc, indexOfStart);
						for(int a = 0; a<functions.size(); a++) {						
							if (varname.equals(functions.get(a))) {
								funcindex = a;							
							}					
						}	
						
						if(varindex1 >= 0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2)) && vartypes.get(varindex2).equals(functionReturnType.get(funcindex))){					

						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nCALL addi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nCALL " + functions.get(funcindex)
										+"\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nMOV L, C\nMOV H, B\nMOV B, D\nMOV C, E\nCALL subi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nMOV B, A\nLDA " +  String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
							
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) +
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '|') {
								if(vartypes.get(varindex1).equals("int")) {
									
									finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
											"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									
									finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
											"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
						}
						
						
						}
						
					}
					else {
						
						varname = lines[i].substring(operationLoc, lines[i].length() - 1);
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex3 = a;							
							}					
						}						
					}
					
					if(varindex1 >= 0 && varindex2 >= 0 && varindex3 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2)) && vartypes.get(varindex2).equals(vartypes.get(varindex3))) {
						
						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nLHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase() 
										+"H\nMOV B, D\nMOV C, E\nCALL addi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() 
										+"H\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nLHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  
										+"H\nMOV B, D\nMOV C, E\nCALL subi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X", ((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() 
										+"H\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nMOV C, E\nMOV B, D\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
						else if(operation == '|') {
							
							if(vartypes.get(varindex1).equals("int")) {
									
									finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
											"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nMOV B, D\nMOV C, E\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
									
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									
									finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
											"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nMOV C, E\nMOV B, D\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
					
					}
					
				}
				
			}				
			
			}
			catch(Exception e) {
				
		}
			}}}
		
		else if(lines[i].replace(" ", "").startsWith("byte") && lines[i].contains("(") && lines[i].contains("{")) {
			curly++;
			
			lines[i] = lines[i].replaceAll(" ", "").replaceAll("\t", "").replaceAll("\n", "");
			int bracketIndex1 = lines[i].indexOf("(");
			String func = lines[i].substring(4, bracketIndex1);
			boolean found = false;
			
			for(int j = 0; j<functions.size(); j++) {
				
				if(functions.get(j).equals(func)) {
					found = true;
				}
				
			}		
			
			if(found == false) {
			functionReturnType.add("byte");
			functions.add(func);
			finalOutputASM = finalOutputASM + func + ":" + "\n";
			
			
			while(curly != 0) {
			try {
			i++;	
			if(lines[i].contains("{")) {
				curly++;
			}
			else if(lines[i].contains("}")) {
				curly--;
			}
						
			
			if(lines[i].contains("__asm__{")) {
				int nowcurly = curly - 1;
				while(curly != nowcurly) {
					i++;
					if(lines[i].contains("}")) {
						curly--;
					}else {	
						finalOutputASM = finalOutputASM + lines[i].trim() + "\n";
					}}}
			else {
				
				if(lines[i].trim().toLowerCase().replace(" ", "").length() >=3 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,3).equals("int")) {
					int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int oldstart = 3;
					for(int j = 0; j<lines[i].length(); j++) {	
						if(lines[i].charAt(j) == ',') {
							String name = lines[i].substring(oldstart, j);
							oldstart = j+1;
							boolean foundvar = false;
							for(int k = 0; k<varnames.size(); k++) {
								if(name.equals(varnames.get(k))) {
									foundvar = true;
								}
							}
							
							if(foundvar == false) {
								varnames.add(name);
								vartypes.add("int");
								varloc = varloc - 2;
								varlocations.add(varloc);								
								finalOutputASM = finalOutputASM + "PUSH B\n";
							}								
						}						
					}
					
					String name = lines[i].substring(oldstart, endIndex);
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
		
					if(foundvar == false) {						
						varnames.add(name);
						vartypes.add("int");
						varloc = varloc - 2;
						varlocations.add(varloc);
						finalOutputASM = finalOutputASM + "PUSH B\n";
					}					
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=4 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,4).equals("byte")) {
									
					int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int oldstart = 4;
					for(int j = 0; j<lines[i].length(); j++) {	
						if(lines[i].charAt(j) == ',') {
							String name = lines[i].substring(oldstart, j);
							oldstart = j+1;
							boolean foundvar = false;
							for(int k = 0; k<varnames.size(); k++) {
								if(name.equals(varnames.get(k))) {
									foundvar = true;
								}
							}
							
							if(foundvar == false) {
								varnames.add(name);
								vartypes.add("byte");
								varloc = varloc - 1;
								varlocations.add(varloc);	
								int low = (varloc+1 & 0xff);
								int high = ((varloc+1 >> 8) & 0xff);
								finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X", high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X",low & 0xff).toUpperCase() + "H\nSPHL\n";
							}								
						}						
					}
					
					String name = lines[i].substring(oldstart, endIndex);
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
		
					if(foundvar == false) {						
						varnames.add(name);
						vartypes.add("byte");
						varloc = varloc - 1;
						varlocations.add(varloc);	
						int low = (varloc+1 & 0xff);
						int high = ((varloc+1 >> 8) & 0xff);
						finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X",high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X",low & 0xff).toUpperCase() +"H\nSPHL\n";
					}					
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=6 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,6).equals("while(") && (lines[i].trim().toLowerCase().replace(" ", "").contains("{"))) {
					
					int localwhilecounter = globalwhilecounter;
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					
					if(lines[i].trim().toLowerCase().replace(" ", "").contains(">")) {
						
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf(">");
						int varindex1 = -1, varindex2 = -1;
		
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+1, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
						
					}
					else if(lines[i].trim().toLowerCase().replace(" ", "").contains("<")) {
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf("<");
						int varindex1 = -1, varindex2 = -1;
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+1, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
					}
					else if(lines[i].trim().toLowerCase().replace(" ", "").contains("==")) {
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf("=");
						int varindex1 = -1, varindex2 = -1;

						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+2, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJNZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
					}
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=7 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,6).equals("return")) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int semiColonIndex = lines[i].indexOf(";");
					int funcindex = -1, varindex1 = -1;
					
					for(int a = 0; a<functions.size(); a++) {						
						if (func.equals(functions.get(a))) {
							funcindex = a;							
						}					
					}					
					
					if(isNumeric(lines[i].substring(6, semiColonIndex))) {
						
						int totalPart = Integer.parseInt(lines[i].substring(6, semiColonIndex));
						int topPart = ((totalPart >> 8) & 0xff);
						int bottomPart = (totalPart & 0xff);
						
						if(functionReturnType.get(funcindex).equals("int")) {
							
							finalOutputASM = finalOutputASM + "MVI C, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI B, " + String.format("%02X",topPart).toUpperCase() + "H\n"
									+ "RET\n";
							
						}
						else if(functionReturnType.get(funcindex).equals("byte")) {
							
							finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X",bottomPart).toUpperCase() + "H\nRET\n";
							
						}
						
					}
					else {
						
						String varname = lines[i].substring(6, semiColonIndex);
						
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex1 = a;							
							}					
						}
						
						if(vartypes.get(varindex1).equals("int")) {
							
							finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nMOV C, L\nMOV B, H\n"
									+ "RET\n";							
							
						}
						else if(vartypes.get(varindex1).equals("byte")) {
						
							finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nRET\n";
							
						}						
					}
					
				}
				else if(lines[i].contains("=") && !(lines[i].contains("+") || lines[i].contains("-") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("%") || lines[i].contains("&") || lines[i].contains("|") || lines[i].contains("^")) && lines[i].contains(";")) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ","");
					
					int equalIndex = lines[i].indexOf("=");
					int semiColonIndex = lines[i].indexOf(";");
					String varname = lines[i].substring(0, equalIndex);
					int varindex1 = -1, varindex2 = -1;
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex1 = a;							
						}					
					}
					String value = lines[i].substring(equalIndex+1, semiColonIndex);
					if(isNumeric(value)) {
						
						int valueToBeSet = Integer.parseInt(value);
						int topPart = ((valueToBeSet >> 8) & 0xff);
						int bottomPart = (valueToBeSet & 0xff);
						
						if(vartypes.get(varindex1).equals("int")) {
							
							finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + "H\n"
									+ "SHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						else if(vartypes.get(varindex1).equals("byte")) {
							
							finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X",bottomPart).toUpperCase() + "H\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						
					}
					else {
						
						varname = value;
						
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex2 = a;							
							}					
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && (vartypes.get(varindex1).equals(vartypes.get(varindex2)))) {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSTA " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							
						}
						
					}
					
				}
				else if(lines[i].contains("=") && (lines[i].contains("+") || lines[i].contains("-") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("%") || lines[i].contains("&") || lines[i].contains("|") || lines[i].contains("^")) && lines[i].contains(";")) {
					lines[i] = lines[i].trim().toLowerCase().replace(" ","");
					
					int equalIndex = lines[i].indexOf("=");
					String varname = lines[i].substring(0, equalIndex);
					int operationLoc = -1;
					char operation = 0;
					int varindex1 = -1, varindex2 = -1, varindex3 = -1, funcindex = -1;
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex1 = a;							
						}					
					}
					
					for(int a = 0; a<lines[i].length(); a++) {
						if(lines[i].charAt(a) == '+' || lines[i].charAt(a) == '-' || lines[i].charAt(a) == '*' || lines[i].charAt(a) == '/' || lines[i].charAt(a) == '%' || lines[i].charAt(a) == '&' || lines[i].charAt(a) == '|' || lines[i].charAt(a) == '^') {
							operation = lines[i].charAt(a);
							operationLoc = a+1;
							varname = lines[i].substring(equalIndex+1, a);
						}
					}			
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex2 = a;							
						}					
					}
					if(lines[i].substring(operationLoc, lines[i].length() - 1).matches("[0-9]+") && varindex1 >= 0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
						
						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase()
										+"H\nCALL addi\nSHLD "
										+ String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {	
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nMVI A, " + String.format("%02X", b&0xff).toUpperCase() 
										+"H\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {	
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X", ((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + 
										"H\nMOV C, L\nMOV B, H\nMVI L, " +  String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " +  String.format("%02X",topPart).toUpperCase()
										+"H\nCALL subi\nSHLD "
										+  String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nMVI A, " + String.format("%02X", b&0xff).toUpperCase()  
										+"H\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
					
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
			
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
						
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '|') {
								
								if(vartypes.get(varindex1).equals("int")) {
									int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
									int topPart = ((totalPart >> 8) & 0xff);
									int bottomPart = (totalPart & 0xff);
									finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
											"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
									
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
									finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
											"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						
					}
					
					else if(lines[i].substring(operationLoc, lines[i].length() - 1).contains("(") && lines[i].substring(operationLoc, lines[i].length() - 1).contains(")")) {
						
						int indexOfStart = lines[i].indexOf("(");
						varname = lines[i].substring(operationLoc, indexOfStart);
						for(int a = 0; a<functions.size(); a++) {						
							if (varname.equals(functions.get(a))) {
								funcindex = a;							
							}					
						}	
						
						if(varindex1 >= 0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2)) && vartypes.get(varindex2).equals(functionReturnType.get(funcindex))){					

						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nCALL addi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nCALL " + functions.get(funcindex)
										+"\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nMOV L, C\nMOV H, B\nMOV B, D\nMOV C, E\nCALL subi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nMOV B, A\nLDA " +  String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
							
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) +
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '|') {
								if(vartypes.get(varindex1).equals("int")) {
									
									finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
											"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									
									finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
											"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
						}
						
						
						}
						
					}
					else {
						
						varname = lines[i].substring(operationLoc, lines[i].length() - 1);
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex3 = a;							
							}					
						}						
					}
					
					if(varindex1 >= 0 && varindex2 >= 0 && varindex3 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2)) && vartypes.get(varindex2).equals(vartypes.get(varindex3))) {
						
						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nLHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase() 
										+"H\nMOV B, D\nMOV C, E\nCALL addi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() 
										+"H\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nLHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  
										+"H\nMOV B, D\nMOV C, E\nCALL subi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X", ((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() 
										+"H\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nMOV C, E\nMOV B, D\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
						else if(operation == '|') {
							
							if(vartypes.get(varindex1).equals("int")) {
									
									finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
											"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nMOV B, D\nMOV C, E\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
									
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									
									finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
											"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nMOV C, E\nMOV B, D\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
					
					}
					
				}
				
			}				
			
			}
			catch(Exception e) {
				
		}
			}}}
		
		else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=3 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,3).equals("int")) {
			int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
			lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
			int oldstart = 3;
			for(int j = 0; j<lines[i].length(); j++) {	
				if(lines[i].charAt(j) == ',') {
					String name = lines[i].substring(oldstart, j);
					oldstart = j+1;
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
					
					if(foundvar == false) {
						varnames.add(name);
						vartypes.add("int");
						varloc = varloc - 2;
						varlocations.add(varloc);								
						finalOutputASM = finalOutputASM + "PUSH B\n";
					}								
				}						
			}
		
			String name = lines[i].substring(oldstart, endIndex);
			boolean foundvar = false;
			for(int k = 0; k<varnames.size(); k++) {
				if(name.equals(varnames.get(k))) {
					foundvar = true;
				}
			}

			if(foundvar == false) {						
				varnames.add(name);
				vartypes.add("int");
				varloc = varloc - 2;
				varlocations.add(varloc);
				finalOutputASM = finalOutputASM + "PUSH B\n";
			}					
			
		}
		else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=4 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,4).equals("byte")) {
							
			int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
			lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
			int oldstart = 4;
			for(int j = 0; j<lines[i].length(); j++) {	
				if(lines[i].charAt(j) == ',') {
					String name = lines[i].substring(oldstart, j);
					oldstart = j+1;
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
					
					if(foundvar == false) {
						varnames.add(name);
						vartypes.add("byte");
						varloc = varloc - 1;
						varlocations.add(varloc);	
						int low = (varloc+1 & 0xff);
						int high = ((varloc+1 >> 8) & 0xff);
						finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X", high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X", low & 0xff).toUpperCase() + "H\nSPHL\n";
					}								
				}						
			}
			
			String name = lines[i].substring(oldstart, endIndex);
			boolean foundvar = false;
			for(int k = 0; k<varnames.size(); k++) {
				if(name.equals(varnames.get(k))) {
					foundvar = true;
				}
			}

			if(foundvar == false) {						
				varnames.add(name);
				vartypes.add("byte");
				varloc = varloc - 1;
				varlocations.add(varloc);	
				int low = (varloc+1 & 0xff);
				int high = ((varloc+1 >> 8) & 0xff);
				finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X", high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X", low & 0xff).toUpperCase() +"H\nSPHL\n";
			}					
			
		}
		
		}
		
		finalOutputASM = finalOutputASM + "HLT\n";
		
		ModByte();
		ModInt();
		MultiplyByte();
		MultiplyInt();
		DivideByte();
		DivideInt();
		AddByte();
		AddInt();
		SubByte();
		SubInt();
		AndByte();
		AndInt();
		OrByte();
		OrInt();
		XorByte();
		XorInt();
		
		
		
		return finalOutputASM;
		
	}
	
	
	int whileLoop(String s, int i, String func) {
		
		int curly = 1;
		
		String lines[] = s.split("\\r?\\n");
		
		i=i-1;
		
		while(curly != 0) {
			try {
			i++;	
			
			if(lines[i].contains("{")) {
				curly++;
			}
			else if(lines[i].contains("}")) {
				curly--;
			}
						
			
			if(lines[i].contains("__asm__{")) {
				int nowcurly = curly - 1;
				while(curly != nowcurly) {
					i++;
					if(lines[i].contains("}")) {
						curly--;
					}else {	
						finalOutputASM = finalOutputASM + lines[i].trim() + "\n";
					}}}
			else {
				
				if(lines[i].trim().toLowerCase().replace(" ", "").length() >=3 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,3).equals("int")) {
					int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int oldstart = 3;
					for(int j = 0; j<lines[i].length(); j++) {	
						if(lines[i].charAt(j) == ',') {
							String name = lines[i].substring(oldstart, j);
							oldstart = j+1;
							boolean foundvar = false;
							for(int k = 0; k<varnames.size(); k++) {
								if(name.equals(varnames.get(k))) {
									foundvar = true;
								}
							}
							
							if(foundvar == false) {
								varnames.add(name);
								vartypes.add("int");
								varloc = varloc - 2;
								varlocations.add(varloc);								
								finalOutputASM = finalOutputASM + "PUSH B\n";
							}								
						}						
					}
					
					String name = lines[i].substring(oldstart, endIndex);
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
		
					if(foundvar == false) {						
						varnames.add(name);
						vartypes.add("int");
						varloc = varloc - 2;
						varlocations.add(varloc);
						finalOutputASM = finalOutputASM + "PUSH B\n";
					}					
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=4 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,4).equals("byte")) {
									
					int endIndex = lines[i].trim().toLowerCase().replace(" ", "").indexOf(";");
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int oldstart = 4;
					for(int j = 0; j<lines[i].length(); j++) {	
						if(lines[i].charAt(j) == ',') {
							String name = lines[i].substring(oldstart, j);
							oldstart = j+1;
							boolean foundvar = false;
							for(int k = 0; k<varnames.size(); k++) {
								if(name.equals(varnames.get(k))) {
									foundvar = true;
								}
							}
							
							if(foundvar == false) {
								varnames.add(name);
								vartypes.add("byte");
								varloc = varloc - 1;
								varlocations.add(varloc);	
								int low = (varloc & 0xff);
								int high = ((varloc >> 8) & 0xff);
								finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X", high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X",low & 0xff).toUpperCase() + "H\nSPHL\n";
							}								
						}						
					}
					
					String name = lines[i].substring(oldstart, endIndex);
					boolean foundvar = false;
					for(int k = 0; k<varnames.size(); k++) {
						if(name.equals(varnames.get(k))) {
							foundvar = true;
						}
					}
		
					if(foundvar == false) {						
						varnames.add(name);
						vartypes.add("byte");
						varloc = varloc - 1;
						varlocations.add(varloc);	
						int low = (varloc+1 & 0xff);
						int high = ((varloc+1 >> 8) & 0xff);
						finalOutputASM = finalOutputASM + "PUSH B\nMVI H," + String.format("%02X",high & 0xff).toUpperCase() + "H\nMVI L," + String.format("%02X",low & 0xff).toUpperCase() +"H\nSPHL\n";
					}					
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=6 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,6).equals("while(") && (lines[i].trim().toLowerCase().replace(" ", "").contains("{"))) {
					
					int localwhilecounter = globalwhilecounter;
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					
					if(lines[i].trim().toLowerCase().replace(" ", "").contains(">")) {
						
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf(">");
						int varindex1 = -1, varindex2 = -1;
		
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+1, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
						
					}
					else if(lines[i].trim().toLowerCase().replace(" ", "").contains("<")) {
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf("<");
						int varindex1 = -1, varindex2 = -1;
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+1, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
					}
					else if(lines[i].trim().toLowerCase().replace(" ", "").contains("==")) {
						int bracket1 = lines[i].indexOf("(");
						int bracket2 = lines[i].indexOf(")");
						int operator = lines[i].indexOf(">");
						int varindex1 = -1, varindex2 = -1;

						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(bracket1+1, operator).equals(varnames.get(a))) {
								varindex1 = a;							
							}	
						}
						
						for(int a = 0; a<varnames.size(); a++) {
							if (lines[i].substring(operator+2, bracket2).equals(varnames.get(a))) {
								varindex2 = a;							
							}	
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
														
							if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM +  "MOV C, A\nPUSH B\nwhile" + localwhilecounter + ":\n";
								globalwhilecounter++;
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nMOV B, A\nLDA " +
										String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nCMP B\nJNZ end" + (localwhilecounter) + "\n";
								
								i = whileLoop(s, i+1, func);
								curly--;
								finalOutputASM = finalOutputASM + "JMP while" + (localwhilecounter) + "\nend" + (localwhilecounter) + ":\nPOP B\nMOV A, C\n";
							}
							
							
						}
					}
					
				}
				else if(lines[i].trim().toLowerCase().replace(" ", "").length() >=7 && lines[i].trim().toLowerCase().replace(" ", "").substring(0,6).equals("return")) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ", "");
					int semiColonIndex = lines[i].indexOf(";");
					int funcindex = -1, varindex1 = -1;
					
					for(int a = 0; a<functions.size(); a++) {						
						if (func.equals(functions.get(a))) {
							funcindex = a;							
						}					
					}					
					
					if(isNumeric(lines[i].substring(6, semiColonIndex))) {
						
						int totalPart = Integer.parseInt(lines[i].substring(6, semiColonIndex));
						int topPart = ((totalPart >> 8) & 0xff);
						int bottomPart = (totalPart & 0xff);
						
						if(functionReturnType.get(funcindex).equals("int")) {
							
							finalOutputASM = finalOutputASM + "MVI C, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI B, " + String.format("%02X",topPart).toUpperCase() + "H\n"
									+ "RET\n";
							
						}
						else if(functionReturnType.get(funcindex).equals("byte")) {
							
							finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X",bottomPart).toUpperCase() + "H\nRET\n";
							
						}
						
					}
					else {
						
						String varname = lines[i].substring(6, semiColonIndex);
						
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex1 = a;							
							}					
						}
						
						if(vartypes.get(varindex1).equals("int")) {
							
							finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nMOV C, L\nMOV B, H\n"
									+ "RET\n";							
							
						}
						else if(vartypes.get(varindex1).equals("byte")) {
						
							finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\nRET\n";
							
						}						
					}
					
				}
				else if(lines[i].contains("=") && !(lines[i].contains("+") || lines[i].contains("-") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("%") || lines[i].contains("&") || lines[i].contains("|") || lines[i].contains("^")) && lines[i].contains(";")) {
					
					lines[i] = lines[i].trim().toLowerCase().replace(" ","");
					
					int equalIndex = lines[i].indexOf("=");
					int semiColonIndex = lines[i].indexOf(";");
					String varname = lines[i].substring(0, equalIndex);
					int varindex1 = -1, varindex2 = -1;
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex1 = a;							
						}					
					}
					String value = lines[i].substring(equalIndex+1, semiColonIndex);
					if(isNumeric(value)) {
						
						int valueToBeSet = Integer.parseInt(value);
						int topPart = ((valueToBeSet >> 8) & 0xff);
						int bottomPart = (valueToBeSet & 0xff);
						
						if(vartypes.get(varindex1).equals("int")) {
							
							finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + "H\n"
									+ "SHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						else if(vartypes.get(varindex1).equals("byte")) {
							
							finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X",bottomPart).toUpperCase() + "H\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
						}
						
					}
					else {
						
						varname = value;
						
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex2 = a;							
							}					
						}
						
						if(varindex1 >=0 && varindex2 >= 0 && (vartypes.get(varindex1).equals(vartypes.get(varindex2)))) {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSHLD " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + "H\nSTA " + String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";								
								
							}
							
						}
						
					}
					
				}
				else if(lines[i].contains("=") && (lines[i].contains("+") || lines[i].contains("-") || lines[i].contains("*") || lines[i].contains("/") || lines[i].contains("%") || lines[i].contains("&") || lines[i].contains("|") || lines[i].contains("^")) && lines[i].contains(";")) {
					lines[i] = lines[i].trim().toLowerCase().replace(" ","");
					
					int equalIndex = lines[i].indexOf("=");
					String varname = lines[i].substring(0, equalIndex);
					int operationLoc = -1;
					char operation = 0;
					int varindex1 = -1, varindex2 = -1, varindex3 = -1, funcindex = -1;
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex1 = a;							
						}					
					}
					
					for(int a = 0; a<lines[i].length(); a++) {
						if(lines[i].charAt(a) == '+' || lines[i].charAt(a) == '-' || lines[i].charAt(a) == '*' || lines[i].charAt(a) == '/' || lines[i].charAt(a) == '%' || lines[i].charAt(a) == '&' || lines[i].charAt(a) == '|' || lines[i].charAt(a) == '^') {
							operation = lines[i].charAt(a);
							operationLoc = a+1;
							varname = lines[i].substring(equalIndex+1, a);
						}
					}			
					for(int a = 0; a<varnames.size(); a++) {						
						if (varname.equals(varnames.get(a))) {
							varindex2 = a;							
						}					
					}
					if(lines[i].substring(operationLoc, lines[i].length() - 1).matches("[0-9]+") && varindex1 >= 0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2))) {
						
						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase()
										+"H\nCALL addi\nSHLD "
										+ String.format("%04X",((varlocations.get(varindex1) + 1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {	
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nMVI A, " + String.format("%02X", b&0xff).toUpperCase() 
										+"H\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {	
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X", ((varlocations.get(varindex2) + 1) & 0xffff)).toUpperCase() + 
										"H\nMOV C, L\nMOV B, H\nMVI L, " +  String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " +  String.format("%02X",topPart).toUpperCase()
										+"H\nCALL subi\nSHLD "
										+  String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nMVI A, " + String.format("%02X", b&0xff).toUpperCase()  
										+"H\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
					
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() +
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
			
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
						
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '|') {
								
								if(vartypes.get(varindex1).equals("int")) {
									int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
									int topPart = ((totalPart >> 8) & 0xff);
									int bottomPart = (totalPart & 0xff);
									finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
											"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
									
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
									finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
											"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								int totalPart = Integer.parseInt(lines[i].substring(operationLoc, lines[i].length() - 1));
								int topPart = ((totalPart >> 8) & 0xff);
								int bottomPart = (totalPart & 0xff);
								finalOutputASM = finalOutputASM + "MVI L, " + String.format("%02X",bottomPart).toUpperCase() + "H\nMVI H, " + String.format("%02X",topPart).toUpperCase() + 
										"H\nMOV B, H\nMOV C, L\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								byte b = Byte.parseByte(lines[i].substring(operationLoc, lines[i].length() - 1));
								finalOutputASM = finalOutputASM + "MVI A, " + String.format("%02X", b&0xff).toUpperCase()   + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						
					}
					
					else if(lines[i].substring(operationLoc, lines[i].length() - 1).contains("(") && lines[i].substring(operationLoc, lines[i].length() - 1).contains(")")) {
						
						int indexOfStart = lines[i].indexOf("(");
						varname = lines[i].substring(operationLoc, indexOfStart);
						for(int a = 0; a<functions.size(); a++) {						
							if (varname.equals(functions.get(a))) {
								funcindex = a;							
							}					
						}	
						
						if(varindex1 >= 0 && varindex2 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2)) && vartypes.get(varindex2).equals(functionReturnType.get(funcindex))){					

						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nCALL addi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";//HERE
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nCALL " + functions.get(funcindex)
										+"\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nMOV L, C\nMOV H, B\nMOV B, D\nMOV C, E\nCALL subi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + "\nMOV B, A\nLDA " +  String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
							
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) +
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV D, B\nMOV E, C\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '|') {
								if(vartypes.get(varindex1).equals("int")) {
									
									finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
											"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									
									finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
											"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "CALL " + functions.get(funcindex) + 
										"\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
						}
						
						
						}
						
					}
					else {
						
						varname = lines[i].substring(operationLoc, lines[i].length() - 1);
						for(int a = 0; a<varnames.size(); a++) {						
							if (varname.equals(varnames.get(a))) {
								varindex3 = a;							
							}					
						}						
					}
					
					if(varindex1 >= 0 && varindex2 >= 0 && varindex3 >= 0 && vartypes.get(varindex1).equals(vartypes.get(varindex2)) && vartypes.get(varindex2).equals(vartypes.get(varindex3))) {
						
						if(operation == '+') {
							if(vartypes.get(varindex1).equals("int")) {
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nLHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase() 
										+"H\nMOV B, D\nMOV C, E\nCALL addi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() 
										+"H\nCALL addb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
						}
						else if(operation == '-') {
							
							if(vartypes.get(varindex1).equals("int")) {								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()+ 
										"H\nXCHG\nLHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  
										+"H\nMOV B, D\nMOV C, E\nCALL subi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X",((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X", ((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() 
										+"H\nCALL subb\nSTA " + String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n"; 
								
							}
							
						}
						else if(operation == '/') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL divi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL divb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '*') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL muli\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL mulb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
								
							}
							
						}
						else if(operation == '%') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nCALL modi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL modb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
						else if(operation == '&') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nMOV C, E\nMOV B, D\nCALL andi\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
								
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL andb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
						else if(operation == '|') {
							
							if(vartypes.get(varindex1).equals("int")) {
									
									finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
											"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
											+"H\nMOV B, D\nMOV C, E\nCALL ori\nSHLD " 
											+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
									
									
								}
								else if(vartypes.get(varindex1).equals("byte")) {
									
									finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
											"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
											"H\nCALL orb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
									
								}
							
						}
						else if(operation == '^') {
							
							if(vartypes.get(varindex1).equals("int")) {
								
								finalOutputASM = finalOutputASM + "LHLD " + String.format("%04X" , ((varlocations.get(varindex3) + 1) & 0xffff)).toUpperCase()  + 
										"H\nXCHG\nLHLD " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase()
										+"H\nMOV C, E\nMOV B, D\nCALL xori\nSHLD " 
										+ String.format("%04X",((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n";
							
								
							}
							else if(vartypes.get(varindex1).equals("byte")) {
								
								finalOutputASM = finalOutputASM + "LDA " + String.format("%04X", ((varlocations.get(varindex3)+1) & 0xffff)).toUpperCase() + 
										"H\nMOV B, A\nLDA " + String.format("%04X",((varlocations.get(varindex2)+1) & 0xffff)).toUpperCase() +
										"H\nCALL xorb\nSTA "+ String.format("%04X", ((varlocations.get(varindex1)+1) & 0xffff)).toUpperCase() + "H\n" ;										
							
							}
							
						}
					
					}
					
				}
				
			}				
			
			}
			catch(Exception e) {
				
		}
			}
		
		return i;
		
		
	}
	
	/**In case of byte A+B or A-B or A/B or A*B -> result in A
	 * In case of int BC+HL or BC-HL or BC/HL or BC*HL -> result in HL
	 */
	
	void ModByte() {
		
		finalOutputASM = finalOutputASM + "modb:\nMVI C, 00H\nCMP B\nJM modbend\nmodbstart:\nINR C\nSUB B\nCMP B\nJP modbstart\nJZ modbstart\nmodbend:\nRET\n";
		
	}
	
	void ModInt() {
		
		finalOutputASM = finalOutputASM + "modi:\nLXI B, 0000H\nmodistart:\nMOV A, L\nSUB E\nMOV L, A\nMOV A, H\nSBB D\nMOV H, A\nJC modiend\nINX B\nJMP modistart\nmodiend:\nDAD D\nRET\n";
		
	}
	
	void MultiplyByte(){		
		
		finalOutputASM = finalOutputASM + "mulb:\nMOV D, A\nMVI A, 00H\nCMP D\nJZ endmulb\nmulb1:\nADD B\nDCR D\nJNZ mulb1\nendmulb:\nRET\n";
		
	}
	
	void MultiplyInt() {
		
		finalOutputASM = finalOutputASM + "muli:\nMOV B, H\nMOV C, L\nMVI H, 00H\nMVI L, 00H\nMOV A, C\nCMP L\nJZ muliend1\nmulistart1:\nDAD D\nDCR C\nJNZ mulistart1\nmuliend1:\nMVI A, 00H\nCMP B\nJZ muliend2\nDAD D\nDCR B\nDCR C\nJMP mulistart1\nmuliend2:\nRET\n";
		
	}
	
	void DivideByte(){		
		
		finalOutputASM = finalOutputASM + "divb:\nMVI C, 00H\nCMP B\nJM divbend\ndivbstart:\nINR C\nSUB B\nCMP B\nJP divbstart\nJZ divbstart\ndivbend:\nMOV A, C\nRET\n";
		
	}
	
	void DivideInt() {
		
		finalOutputASM = finalOutputASM + "divi:\nLXI B, 0000H\ndivistart:\nMOV A, L\nSUB E\nMOV L, A\nMOV A, H\nSBB D\nMOV H, A\nJC diviend\nINX B\nJMP divistart\ndiviend:\nDAD D\nMOV L, C\nMOV H, B\nRET\n";
		
	}
	
	void AddByte(){		
		
		finalOutputASM = finalOutputASM + "addb:\nADD B\nRET\n";
		
	}
	
	void AddInt() {
		
		finalOutputASM = finalOutputASM + "addi:\nDAD B\nRET\n";
		
	}
	
	void SubByte(){		
		
		finalOutputASM = finalOutputASM + "subb:\nSUB B\nRET\n";
		
	}
	
	void SubInt() {
		
		finalOutputASM = finalOutputASM + "subi:\nMOV A, C\nSUB L\nMOV L, A\nMOV A, B\nSBB H\nMOV H, A\nRET\n";
		
	}
	
	void AndByte() {
		
		finalOutputASM = finalOutputASM + "andb:\nANA B\nRET\n";
		
	}
	
	void AndInt() {
		
		finalOutputASM = finalOutputASM + "andi:\nMOV A, C\nANA L\nMOV L, A\nMOV A, B\nANA H\nMOV H, A\nRET\n";
		
	}
	
	void OrByte() {
		
		finalOutputASM = finalOutputASM + "orb:\nORA B\nRET\n";
		
	}
	
	void OrInt() {
		
		finalOutputASM = finalOutputASM + "ori:\nMOV A, C\nORA L\nMOV L, A\nMOV A, B\nORA H\nMOV H, A\nRET\n";
		
	}
	
	void XorByte() {
		
		finalOutputASM = finalOutputASM + "xorb:\nXRA B\nRET\n";
		
	}
	
	void XorInt() {
		
		finalOutputASM = finalOutputASM + "xori:\nMOV A, C\nXRA L\nMOV L, A\nMOV A, B\nXRA H\nMOV H, A\nRET\n";
		
	}
	
	String ifStatement(String s) {
		
		//WILL BE IMPLEMENTED IN THE FUTURE!!!
		return null;
		
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
}
