/*

@ShortLicense@

Authors: @JS@
         @MJL@

Released: @ReleaseDate@

 */

package de.jskat.gui;

import java.util.List;
import java.util.Set;

import de.jskat.control.SkatTable;
import de.jskat.control.iss.ChatMessageType;
import de.jskat.data.GameAnnouncement;
import de.jskat.data.SkatGameData;
import de.jskat.data.SkatGameData.GameState;
import de.jskat.data.iss.ISSChatMessage;
import de.jskat.data.iss.ISSGameStatus;
import de.jskat.data.iss.ISSMoveInformation;
import de.jskat.data.iss.ISSTablePanelStatus;
import de.jskat.util.Card;
import de.jskat.util.CardList;
import de.jskat.util.Player;

/**
 * Doesn't do anything
 */
public class NullView implements JSkatView {

	/**
	 * @see JSkatView#addCard(String, Player, Card)
	 */
	@Override
	public void addCard(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player player,
			@SuppressWarnings("unused") Card card) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#clearHand(String, Player)
	 */
	@Override
	public void clearHand(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player player) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#clearTrickCards(String)
	 */
	@Override
	public void clearTrickCards(@SuppressWarnings("unused") String tableName) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#createISSTable(String)
	 */
	@Override
	public void createISSTable(@SuppressWarnings("unused") String name) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#createSkatTablePanel(String)
	 */
	@Override
	public void createSkatTablePanel(@SuppressWarnings("unused") String name) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#removeCard(String, Player, Card)
	 */
	@Override
	public void removeCard(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player player,
			@SuppressWarnings("unused") Card card) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setPositions(String, Player, Player, Player)
	 */
	@Override
	public void setPositions(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player leftPosition,
			@SuppressWarnings("unused") Player rightPosition,
			@SuppressWarnings("unused") Player playerPosition) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setTrickCard(String, Player, Card)
	 */
	@Override
	public void setTrickCard(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player position,
			@SuppressWarnings("unused") Card card) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showAboutMessage()
	 */
	@Override
	public void showAboutMessage() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showExitDialog()
	 */
	@Override
	public int showExitDialog() {
		return 0;
	}

	/**
	 * @see JSkatView#showGameResults()
	 */
	@Override
	public void showGameResults() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showISSLogin()
	 */
	@Override
	public void showISSLogin() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showSeriesResults()
	 */
	@Override
	public void showSeriesResults() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showTable(SkatTable)
	 */
	@Override
	public void showTable(@SuppressWarnings("unused") SkatTable table) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#startBidding()
	 */
	@Override
	public void startBidding() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#startDiscarding()
	 */
	@Override
	public void startDiscarding() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#startGame(String)
	 */
	@Override
	public void startGame(@SuppressWarnings("unused") String tableName) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#startPlaying()
	 */
	@Override
	public void startPlaying() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#startSeries(String)
	 */
	@Override
	public void startSeries(@SuppressWarnings("unused") String tableName) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setGameAnnouncement(String, GameAnnouncement, boolean)
	 */
	@Override
	public void setGameAnnouncement(
			@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") GameAnnouncement ann,
			@SuppressWarnings("unused") boolean hand) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setGameState(String, GameState)
	 */
	@Override
	public void setGameState(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") GameState state) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#addGameResult(String, SkatGameData)
	 */
	@Override
	public void addGameResult(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") SkatGameData data) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showHelpDialog()
	 */
	@Override
	public void showHelpDialog() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showLicenseDialog()
	 */
	@Override
	public void showLicenseDialog() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#clearTable(String)
	 */
	@Override
	public void clearTable(@SuppressWarnings("unused") String tableName) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setNextBidValue(String, int)
	 */
	@Override
	public void setNextBidValue(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") int nextBidValue) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showMessage(int, String)
	 */
	@Override
	public void showMessage(@SuppressWarnings("unused") int messageType,
			@SuppressWarnings("unused") String message) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setBid(String, Player, int)
	 */
	@Override
	public void setBid(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player player,
			@SuppressWarnings("unused") int bidValue) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setTrickForeHand(String, Player)
	 */
	@Override
	public void setTrickForeHand(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player trickForeHand) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setSkat(String, CardList)
	 */
	@Override
	public void setSkat(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") CardList skat) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#putCardIntoSkat(String, Card)
	 */
	@Override
	public void putCardIntoSkat(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Card card) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#takeCardFromSkat(String, Card)
	 */
	@Override
	public void takeCardFromSkat(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Card card) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showStartSkatSeriesDialog()
	 */
	@Override
	public void showStartSkatSeriesDialog() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#updateISSLobbyPlayerList(String, String, long, double)
	 */
	@Override
	public void updateISSLobbyPlayerList(
			@SuppressWarnings("unused") String playerName,
			@SuppressWarnings("unused") String playerLanguage1,
			@SuppressWarnings("unused") long gamesPlayed,
			@SuppressWarnings("unused") double strength) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#removeFromISSLobbyPlayerList(String)
	 */
	@Override
	public void removeFromISSLobbyPlayerList(
			@SuppressWarnings("unused") String playerName) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showISSLobby()
	 */
	@Override
	public void showISSLobby() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#removeFromISSLobbyTableList(String)
	 */
	@Override
	public void removeFromISSLobbyTableList(
			@SuppressWarnings("unused") String tableName) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#updateISSLobbyTableList(String, int, long, String, String,
	 *      String)
	 */
	@Override
	public void updateISSLobbyTableList(
			@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") int maxPlayers,
			@SuppressWarnings("unused") long gamesPlayed,
			@SuppressWarnings("unused") String player1,
			@SuppressWarnings("unused") String player2,
			@SuppressWarnings("unused") String player3) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#appendISSChatMessage(ChatMessageType, ISSChatMessage)
	 */
	@Override
	public void appendISSChatMessage(
			@SuppressWarnings("unused") ChatMessageType messageType,
			@SuppressWarnings("unused") ISSChatMessage message) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#updateISSTable(String, ISSTablePanelStatus)
	 */
	@Override
	public void updateISSTable(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") ISSTablePanelStatus status) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#updateISSTable(String, String, ISSGameStatus)
	 */
	@Override
	public void updateISSTable(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") String playerName,
			@SuppressWarnings("unused") ISSGameStatus status) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#getNewTableName()
	 */
	@Override
	public String getNewTableName() {
		// empty method by indent
		return null;
	}

	/**
	 * @see JSkatView#updateISSMove(String, ISSMoveInformation)
	 */
	@Override
	public void updateISSMove(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") ISSMoveInformation moveInformation) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#playTrickCard(String, Player, Card)
	 */
	@Override
	public void playTrickCard(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player position,
			@SuppressWarnings("unused") Card card) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#setLastTrick(String, Player, Card, Card, Card)
	 */
	@Override
	public void setLastTrick(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player trickForeHand,
			@SuppressWarnings("unused") Card foreHandCard,
			@SuppressWarnings("unused") Card middleHandCard,
			@SuppressWarnings("unused") Card hindHandCard) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#showPreferences()
	 */
	@Override
	public void showPreferences() {
		// empty method by indent
	}

	/**
	 * @see JSkatView#closeTabPanel(java.lang.String)
	 */
	@Override
	public void closeTabPanel(@SuppressWarnings("unused") String name) {
		// empty method by indent
	}

	/**
	 * @see JSkatView#getPlayerForInvitation(Set)
	 */
	@Override
	public List<String> getPlayerForInvitation(
			@SuppressWarnings("unused") Set<String> playerNames) {
		// empty method by indent
		return null;
	}

	/**
	 * @see JSkatView#addCards(String, Player, CardList)
	 */
	@Override
	public void addCards(@SuppressWarnings("unused") String tableName,
			@SuppressWarnings("unused") Player player,
			@SuppressWarnings("unused") CardList cards) {
		// empty method by indent
	}
}
