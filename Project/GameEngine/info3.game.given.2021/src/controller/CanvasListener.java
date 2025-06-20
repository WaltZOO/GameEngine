/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.Game;
import info3.game.graphics.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
	Game m_game;

	public CanvasListener(Game game) {
		m_game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("Mouse clicked: ("+e.getX()+","+e.getY()+")");
		// System.out.println(" modifiers="+e.getModifiersEx());
		// System.out.println(" buttons="+e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("Mouse pressed: ("+e.getX()+","+e.getY()+")");
		// System.out.println(" modifiers="+e.getModifiersEx());
		// System.out.println(" buttons="+e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("Mouse released: ("+e.getX()+","+e.getY()+")");
		// System.out.println(" modifiers="+e.getModifiersEx());
		// System.out.println(" buttons="+e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// System.out.println("Mouse entered: ("+e.getX()+","+e.getY()+")");
		// System.out.println(" modifiers="+e.getModifiersEx());
		// System.out.println(" buttons="+e.getButton());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// System.out.println("Mouse exited: ("+e.getX()+","+e.getY()+")");
		// System.out.println(" modifiers="+e.getModifiersEx());
		// System.out.println(" buttons="+e.getButton());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// System.out.println("Mouse dragged: ("+e.getX()+","+e.getY()+")");
		// System.out.println(" modifiers="+e.getModifiersEx());
		// System.out.println(" buttons="+e.getButton());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println("Mouse moved: ("+e.getX()+","+e.getY()+")");
		// System.out.println(" modifiers="+e.getModifiersEx());
		// System.out.println(" buttons="+e.getButton());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("Key typed: "+e.getKeyChar()+" code="+e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println("Key pressed: "+e.getKeyChar()+" code="+e.getKeyCode());

		if (e.getKeyCode() == 38) {
			if (!this.m_game.model.p1.keys.contains("FU")) {
				this.m_game.model.p1.keys.add("FU");
			}
		}
		if (e.getKeyCode() == 37) {
			if (!this.m_game.model.p1.keys.contains("FL")) {
				this.m_game.model.p1.keys.add("FL");
			}

		} else if (e.getKeyCode() == 40) {
			if (!this.m_game.model.p1.keys.contains("FD")) {
				this.m_game.model.p1.keys.add("FD");
			}

		} else if (e.getKeyCode() == 39) {
			if (!this.m_game.model.p1.keys.contains("FR")) {
				this.m_game.model.p1.keys.add("FR");
			}
		} else {
			if (!this.m_game.model.p1.keys.contains(e.getKeyChar() + "")) {
				this.m_game.model.p1.keys.add(e.getKeyChar() + "");
			}

		}

		if (e.getKeyCode() == 38) {
			if (!this.m_game.model.p2.keys.contains("FU")) {
				this.m_game.model.p2.keys.add("FU");
			}
		} else if (e.getKeyCode() == 37) {
			if (!this.m_game.model.p2.keys.contains("FL")) {
				this.m_game.model.p2.keys.add("FL");
			}
		} else if (e.getKeyCode() == 40) {
			if (!this.m_game.model.p2.keys.contains("FD")) {
				this.m_game.model.p2.keys.add("FD");
			}
		} else if (e.getKeyCode() == 39) {
			if (!this.m_game.model.p2.keys.contains("FR")) {
				this.m_game.model.p2.keys.add("FR");
			}
		} else {
			if (!this.m_game.model.p2.keys.contains(e.getKeyChar() + "")) {
				this.m_game.model.p2.keys.add(e.getKeyChar() + "");
			}
		}

		// ATTENTION a tester la conversion string to char ne vas peut etre pas
		// fonctionner
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("Key released: "+e.getKeyChar()+" code="+e.getKeyCode());
		if (this.m_game.model.p1.keys.contains(e.getKeyChar() + "")) {
			this.m_game.model.p1.keys.remove(e.getKeyChar() + "");
		}

		if (this.m_game.model.p2.keys.contains(e.getKeyChar() + "")) {
			this.m_game.model.p2.keys.remove(e.getKeyChar() + "");
		}

		if (e.getKeyCode() == 38) {
			this.m_game.model.p1.keys.remove("FU");
			this.m_game.model.p2.keys.remove("FU");
			return;
		}
		if (e.getKeyCode() == 37) {
			this.m_game.model.p1.keys.remove("FL");
			this.m_game.model.p2.keys.remove("FL");
			return;
		}
		if (e.getKeyCode() == 40) {
			this.m_game.model.p1.keys.remove("FD");
			this.m_game.model.p2.keys.remove("FD");
			return;
		}
		if (e.getKeyCode() == 39) {
			this.m_game.model.p1.keys.remove("FR");
			this.m_game.model.p2.keys.remove("FR");
			return;
		}

		if (this.m_game.model.p1.keys.isEmpty()) {
			this.m_game.model.p1.isRunning = false;
		}

		if (this.m_game.model.p2.keys.isEmpty()) {
			this.m_game.model.p2.isRunning = false;
		}

	}

	@Override
	public void tick(long elapsed) {
		m_game.tick(elapsed);
	}

	@Override
	public void paint(Graphics g) {
		m_game.paint(g);
	}

	@Override
	public void windowOpened() {
		// m_game.loadMusic();
		// m_game.m_canvas.setTimer(6000);
	}

	@Override
	public void exit() {
	}

	// boolean m_expired;
	@Override
	public void endOfPlay(String name) {
		// if (!m_expired) // only reload if it was a forced reload by timer
		// m_game.loadMusic();
		// m_expired = false;
	}

	@Override
	public void expired() {
		// will force a change of music, after 6s of play
		// //System.out.println("Forcing an ealy change of music");
		// m_expired = true;
		// m_game.loadMusic();
	}

}
