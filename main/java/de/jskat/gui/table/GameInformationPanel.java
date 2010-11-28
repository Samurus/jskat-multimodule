/*

@ShortLicense@

Authors: @JS@
         @MJL@

Released: @ReleaseDate@

 */

package de.jskat.gui.table;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import de.jskat.data.GameAnnouncement;
import de.jskat.data.SkatGameData;
import de.jskat.data.SkatGameData.GameState;
import de.jskat.util.GameType;

/**
 * Panel for showing game informations
 */
class GameInformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel label;

	private GameState gameState;
	private GameType gameType;
	private boolean handGame;
	private boolean ouvertGame;
	private boolean schneiderAnnounced;
	private boolean schwarzAnnounced;
	private int trick;
	private boolean gameWon;
	private int declarerPoints;
	private int opponentPoints;

	/**
	 * Constructor
	 */
	GameInformationPanel() {

		super();
		initPanel();
	}

	private void initPanel() {

		setLayout(new MigLayout("fill"));

		setOpaque(true);

		label = new JLabel();
		label.setFont(new Font(Font.DIALOG, Font.BOLD, 16));

		setGameState(GameState.GAME_START);

		add(this.label);
	}

	void clear() {

		this.label.setText(" "); //$NON-NLS-1$
	}

	void setGameState(GameState newGameState) {

		gameState = newGameState;

		if (gameState.equals(GameState.GAME_START)) {

			resetGameData();
		}

		refreshText();
	}

	void setGameAnnouncement(GameAnnouncement announcement) {
		gameType = announcement.getGameType();
		handGame = announcement.isHand();
		ouvertGame = announcement.isOuvert();
		schneiderAnnounced = announcement.isSchneider();
		schwarzAnnounced = announcement.isSchwarz();
	}

	private void resetGameData() {
		gameType = null;
		handGame = false;
		ouvertGame = false;
		schneiderAnnounced = false;
		schwarzAnnounced = false;
		trick = 0;
		gameWon = false;
		declarerPoints = 0;
		opponentPoints = 0;
	}

	private void refreshText() {

		String text = gameState.toString();

		if (gameType != null) {
			text += " " + gameType.toString();
		}

		if (handGame) {
			text += " hand";
		}

		if (ouvertGame) {
			text += " ouvert";
		}

		if (schneiderAnnounced) {
			text += " schneider";
		}

		if (schwarzAnnounced) {
			text += " schwarz";
		}

		if (gameState.equals(GameState.TRICK_PLAYING)) {
			text += " Trick " + (trick + 1);
		}

		if (gameState.equals(GameState.GAME_OVER)) {

			if (gameWon) {
				text += " WON!!!";
			} else {
				text += " LOST!!!";
			}

			text += " Declarer " + declarerPoints;
			text += " Opponents " + opponentPoints;
		}

		label.setText(text);
	}

	void setGameResult(SkatGameData data) {
		gameWon = data.isGameWon();
		declarerPoints = data.getDeclarerScore();
		opponentPoints = data.getOpponentScore();

		refreshText();
	}
}
