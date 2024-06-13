package ai;

import java.util.ArrayList;

public class ActionProba {
	ArrayList<Action> a;
	ArrayList<Integer> proba;

	public ActionProba(ArrayList<Action> a) {
		this.a = a;
		proba = new ArrayList<Integer>();
		for (int i = 0; i < a.size(); i++) {
			proba.add(100 / a.size());
		}

	}

	public ActionProba(ArrayList<Action> a, ArrayList<Integer> proba) {
		this.a = a;
		this.proba = proba;
	}

}