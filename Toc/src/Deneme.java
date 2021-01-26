import java.util.HashMap;
import java.util.Iterator;

public class Deneme extends Helper {
	HashMap<String, HashMap<String, String>> paths = new HashMap<String, HashMap<String, String>>();
	public String state = "";

	public void fill_hashmap() {
		readFile();
		for (int i = 0; i < path.size(); i++) {
			String[] p = path.get(i).split("=");
			p = p[0].split(",");
			String p1 = p[0];
			HashMap<String, String> value = new HashMap<String, String>();
			if (!paths.containsKey(p1)) {
				for (int j = i; j < path.size(); j++) {
					p = path.get(j).split("=");
					String p2_2 = p[1];
					p = p[0].split(",");
					String p1_1 = p[0];
					String letter = p[1];
					if (p1_1.equals(p1)) {
						if (value.get(p2_2) == null) {
							value.put(p2_2, letter);
						} else {
							value.put(p2_2, "(" + value.get(p2_2) + "U" + letter + ")");
							System.out.println(p2_2 + "-" + p2_2 + "-->" + value.get(p2_2) + "*");
						}
					}
				}
				paths.put(p1, value);
			}
		}
	}

	public void operation() {
		fill_hashmap();
		String RE = "";
		HashMap<String, String> path = paths.get(S_start_state);
		this.state = S_start_state;
		RE = "(" + sss(path, RE) + ")";
		path = paths.get(A_final_state);
		this.state = A_final_state;
		RE = afs(path, RE);
		System.out.println(RE);
	}

	public String afs(HashMap<String, String> path, String RE) {
		for (String key : path.keySet()) {
			if (key.equals(A_final_state)) {
				RE += path.get(key) + "*";
				System.out.println(this.state + "-" + key + "-->" + path.get(key) + "*");
			} else if (key.equals(S_start_state)) {
				System.out.println(this.state + "-" + key + ";" + key + "-" + this.state + "--->" + "(" + path.get(key)
						+ RE + ")*");
				RE = RE + "(" + path.get(key) + RE + ")*";
			} else {
				String sstate = this.state;
				String st = find_path(path.get(key), key);
				System.out.println(sstate + "-" + key + ";" + key + "-" + sstate + "--->" + "(" + st + ")*");
				RE += "(" + st + ")*";
			}
		}
		return RE;
	}

	public String sss(HashMap<String, String> path, String RE) {
		for (String key : path.keySet()) {
			if (key.equals(S_start_state)) {
				if (!RE.equals("")) {
					if (RE.endsWith(")")) {
						RE += "U" + path.get(key) + "*";
						System.out.println(this.state + "-" + key + "-->" + path.get(key) + "*");
					}
				} else {
					RE += path.get(key) + "*";
					System.out.println(this.state + "-" + key + "-->" + path.get(key) + "*");
				}
			} else if (key.equals(A_final_state)) {
				if (!RE.equals("")) {
					if (RE.endsWith(")")) {
						RE += "U" + path.get(key);
						System.out.println(this.state + "-" + key + "-->" + path.get(key));
					}
				} else {
					RE += path.get(key);
					System.out.println(this.state + "-" + key + "-->" + path.get(key));
				}
			} else {
				System.out.println(this.state + "-" + key + "-->" + path.get(key));
				if (!RE.equals("")) {
					RE += "U(" + find_path(path.get(key), key) + ")";
				} else
					RE += "(" + find_path(path.get(key), key) + ")";
			}
		}
		return RE;
	}

	public String find_path(String re, String start_state) {
		HashMap<String, String> path = paths.get(start_state);
		this.state = start_state;
		for (String key : path.keySet()) {
			if (!(key).equals(A_final_state)) {
				if (!start_state.equals(key))
					return find_path(re + path.get(key), key);
				else {
					if (re.length() > 1) {
						char[] chars=re.toCharArray();
						re="";
						for (int i = 0; i < chars.length-1; i++) {
							re+=chars[i];
						}
						re+=path.get(key) + "*" + chars[chars.length-1];
					} else {
						re += path.get(key) + "*";
						System.out.println(this.state + "-" + key + "-->" + re);
					}
				}
			} else {
				re += path.get(key);
				System.out.println(this.state + "-" + key + "-->" + re);
			}
		}
		return re;
	}

}
