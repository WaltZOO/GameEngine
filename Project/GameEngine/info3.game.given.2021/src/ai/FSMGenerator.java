package ai;

import java.util.ArrayList;
import java.util.List;

import gal.ast.AST;
import gal.ast.Actions;
import gal.ast.Automaton;
import gal.ast.Behaviour;
import gal.ast.BinaryOp;
import gal.ast.Category;
import gal.ast.Condition;
import gal.ast.Direction;
import gal.ast.FunCall;
import gal.ast.IVisitor;
import gal.ast.Key;
import gal.ast.Mode;
import gal.ast.State;
import gal.ast.Transition;
import gal.ast.UnaryOp;
import gal.ast.Underscore;
import gal.ast.Value;

public class FSMGenerator implements IVisitor {
	ArrayList<FSM> output;

	public FSMGenerator() {
		output = new ArrayList<FSM>();
	}

	public ArrayList<FSM> getOutput() {
		return output;
	}

	@Override
	public Object visit(Category cat) {
		// TODO Auto-generated method stub
		return cat;
	}

	@Override
	public Object visit(Direction dir) {
		// TODO Auto-generated method stub
		return dir;
	}

	@Override
	public Object visit(Key key) {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public Object visit(Value v) {
		// TODO Auto-generated method stub
		return v;
	}

	@Override
	public Object visit(Underscore u) {
		// TODO Auto-generated method stub
		return u;
	}

	// FUNCALL --------------------------------------------------------------

	@Override
	public void enter(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(FunCall funcall, List<Object> parameters) {
		String direction = null;
		String category = null;
		String key = null;
		for (Object p : parameters) {
			if (p instanceof Category) {
				category = ((Category) p).terminal.content;
			} else if (p instanceof Direction) {
				direction = ((Direction) p).terminal.content;
			} else if (p instanceof Key) {
				key = ((Key) p).terminal.content;
			}
		}
		Object a;
		switch (funcall.name) {
			// ACTIONS ----------------------------
			case "Move": {
				if (direction != null) {
					a = new Move(direction);
				} else {
					a = new Move();
				}
				break;
			}
			case "Pick": {
				if (direction != null) {
					a = new Pick(direction);
				} else {
					a = new Pick();
				}
				break;
			}
			case "Hit": {
				if (direction != null) {
					a = new Hit(direction);
				} else {
					a = new Hit();
				}
				break;
			}
			case "Turn": {
				if (direction != null) {
					a = new Turn(direction);
				} else {
					a = new Turn();
				}
				break;
			}
			case "Get": {
				a = new Get();
				break;
			}
			case "Egg": {
				if (direction != null) {
					a = new Egg(direction, true);
				} else {
					a = new Egg();
				}
				break;
			}
			case "Wait": {
				a = new Wait();
				break;

			}

			// CONDITIONS ----------------------------

			case "Cell": {
				if (direction != null) {
					if (category != null) {
						a = new Cell(direction, category);
					} else {
						a = new Cell(direction, true);
					}
				} else {
					if (category != null) {
						a = new Cell(category, false);
					} else {
						a = new Cell();
					}
				}
				break;
			}
			case "Closest": {
				if (direction != null) {
					if (category != null) {
						a = new Closest(direction, category);
					} else {
						a = new Closest(direction, true);
					}
				} else {
					if (category != null) {
						a = new Closest(category, false);
					} else {
						a = new Closest();
					}
				}

				break;
			}
			case "Key": {
				if (key != null) {
					a = new ai.Key(key);
				} else {
					a = new ai.Key();
				}
				break;

			}
			case "MyDir": {
				if (direction != null) {
					a = new MyDir(direction);
				} else {
					a = new MyDir();
				}
				break;
			}
			case "True": {
				a = new True();
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + funcall.name);

		}

		return new Object[] { funcall.percent, a };
	}

	// BINOP --------------------------------------------------------------

	@Override
	public void enter(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(BinaryOp binop) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(BinaryOp binop, Object left, Object right) {
		if (!(left instanceof ai.Conjonction) && !(left instanceof ai.Disjonction)) {
			Object[] temp = (Object[]) left;
			left = temp[1];
		}
		if (!(right instanceof ai.Conjonction) && !(right instanceof ai.Disjonction)) {
			Object[] temp = (Object[]) right;
			right = temp[1];
		}
		if (binop.operator.equals("&")) {
			return new Conjonction((ai.Condition) left, (ai.Condition) right);
		} else if (binop.operator.equals("|")) {
			return new Disjonction((ai.Condition) left, (ai.Condition) right);
		} else {
			throw new IllegalArgumentException("Unexpected value: " + binop.operator);
		}
	}

	// UNOP --------------------------------------------------------------

	@Override
	public void enter(UnaryOp unop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(UnaryOp unop) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(UnaryOp unop, Object expression) {
		// TODO Auto-generated method stub
		return expression;
	}

	// STATE --------------------------------------------------------------

	@Override
	public Object visit(State state) {
		return new ai.State(state.name);
	}

	// MODE --------------------------------------------------------------

	@Override
	public void enter(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Mode mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Mode mode, Object source_state, Object behaviour) {
		for (Object o : (List<Object>) behaviour) {
			((ai.Transition) o).setSource((ai.State) source_state);
		}
		return behaviour;
	}

	// BEHAVIOUR --------------------------------------------------------------

	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		List<ai.Transition> t = new ArrayList<ai.Transition>();
		for (Object o : transitions) {
			t.add((ai.Transition) o);
		}
		return t;
	}

	// CONDITION --------------------------------------------------------------

	@Override
	public void enter(Condition condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Condition condition) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Condition condition, Object expression) {
		if (expression instanceof ai.Conjonction || expression instanceof ai.Disjonction) {
			return expression;
		}
		Object[] temp = (Object[]) expression;
		return temp[1];
	}

	// ACTION --------------------------------------------------------------

	@Override
	public void enter(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Actions action) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Actions action, String operator, List<Object> funcalls) {
		ArrayList<ai.ActionProba> ap = new ArrayList<ai.ActionProba>();

		ArrayList<Integer> prob = new ArrayList<Integer>();
		ArrayList<Action> acts = new ArrayList<Action>();

		for (Object o : funcalls) {
			Object[] temp = (Object[]) o;
			if (operator == ";") {
				// si ";" il n'a pas de proba dans funcalls
				// on ajoute seulement les actions
				acts = new ArrayList<Action>();
				acts.add((ai.Action) temp[1]);
				ap.add(new ActionProba(acts));
			} else {
				// si "/" il une proba dans funcalls
				// on ajoute donc les actions et les probas
				if ((int) temp[0] == -1) {
					int somme_prob = 0;
					int no_prob = 0;
					for (Object o2 : funcalls) {
						Object[] temp2 = (Object[]) o2;
						if ((int) temp2[0] != -1) {
							somme_prob += (int) temp2[0];
						} else {
							no_prob++;
						}
					}
					prob.add((100 - somme_prob) / no_prob);

				} else
					prob.add((Integer) temp[0]);
				acts.add((ai.Action) temp[1]);

			}
		}
		if (operator == "/")
			ap.add(new ActionProba(acts, prob));

		return ap;
	}

	// TRANSITION --------------------------------------------------------------

	@Override
	public void enter(Transition transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Transition transition) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Transition transition, Object condition, Object action, Object target_state) {
		ArrayList<ai.ActionProba> aa = (ArrayList<ai.ActionProba>) action;
		return new ai.Transition(null, (ai.State) target_state, (ai.Condition) condition, aa);
	}

	// AUTOMATON --------------------------------------------------------------

	@Override
	public void enter(Automaton automaton) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(Automaton automaton) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(Automaton automaton, Object initial_state, List<Object> modes) {
		FSM f = new FSM((ai.State) initial_state);

		for (Object o : modes) {
			for (Object o2 : (ArrayList<Object>) o) {
				f.add_transition((ai.Transition) o2);
			}
		}
		return f;
	}

	// AST --------------------------------------------------------------

	@Override
	public void enter(AST ast) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit(AST ast) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object build(AST ast, List<Object> automata) {

		for (Object o : automata) {
			output.add((FSM) o);
		}

		return output;
	}

}
