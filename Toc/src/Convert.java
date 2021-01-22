
public class Convert extends Helper {
	public String find_U(String p1,String p2,String re,int i,String letter,boolean flag) {
		if(flag) {
			re+=letter+"*";
		}else
			re+=letter;
		for (int j = i+1; j < path.size(); j++) {
			String[] p=path.get(j).split("=");
			String p2_2=p[1].trim();
			p=p[0].split(",");
			String p1_1=p[0];
			letter=p[1];
			if(p2.equals(p1_1)) {
				if(p2_2.equals(p1_1)) {
					return find_U(p1_1,p2_2,re,j,letter,true);
				}else if(!p2_2.equals(A_final_state)) {
					return find_U(p1_1,p2_2,re,j,letter,false);
				}else {
					return re+letter;
				}
			}else {
				return re;
			}
		}
		return re;
	}
	public void operation() {
		String RE="";
		readFile();
		for (int i = 0; i < path.size(); i++) {
			String[] p=path.get(i).split("=");
			String p2=p[1].trim();
			p=p[0].split(",");
			String p1=p[0];
			String letter=p[1];
			if(!p2.equals(A_final_state)&&!p1.equals(p2)) {
				String re="";
				if(!RE.equals("")) {
					RE+="U("+find_U(p1,p2,re,i,letter,false)+")";
				}else {
					RE="("+find_U(p1,p2,re,i,letter,false)+")";
				}
			}if(!p1.equals(p2)&&p2.equals(A_final_state)&&p1.equals(S_start_state)) {
				if(RE.endsWith(")")) {
					RE+="U"+letter;
				}else {
					RE+=letter;
				}
			}if(p1.equals(A_final_state)&&p1.equals(p2)) {
				RE=star(RE,letter);
			}if(p1.equals(S_start_state)&&p1.equals(p2)) {
				RE=star(RE,letter);
			}
			
		}
		System.out.println(RE);
	}
	public String star(String RE,String letter) {
		if(RE.endsWith(")")) {
			RE+="U"+letter+"*";
		}else {
			RE+=letter+"*";
		}
		return RE;
	}
	
}
