/*

@ShortLicense@

Authors: @JS@
         @MJL@

Released: @ReleaseDate@

 */

package de.jskat.gui.action.human;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import de.jskat.control.JSkatMaster;
import de.jskat.gui.action.AbstractJSkatAction;
import de.jskat.gui.action.JSkatActions;

/**
 * Implements the action for handling card panel clicks
 */
public class DiscardAction extends AbstractJSkatAction {

	private static final long serialVersionUID = 1L;

	/**
	 * @see AbstractJSkatAction#AbstractJSkatAction(JSkatMaster)
	 */
	public DiscardAction(JSkatMaster controller) {
		
		super(controller);
		
		putValue(Action.NAME, "Discard");
		putValue(Action.SHORT_DESCRIPTION, "Discard cards");
		putValue(Action.ACTION_COMMAND_KEY, JSkatActions.DISCARD_CARDS.toString());
	}
	
	/**
	 * @see AbstractAction#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println(e.getSource() + " " + e.getActionCommand());
		
		this.jskat.triggerHuman(e);
	}
}
