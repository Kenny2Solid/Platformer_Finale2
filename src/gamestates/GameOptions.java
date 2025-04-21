package gamestates;

import main.Game;
import ui.AudioOptions;
import ui.PauseButton;
import ui.UrmButton;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class GameOptions extends State implements Statemethods {

    private AudioOptions audioOptions;
    private BufferedImage backgroundImg, optionsBackgroundImg;
    private int bgX, bgY, bgW, bgH;
    private UrmButton menuB;

    public GameOptions(Game game) {
        super(game);
        loadImgs(); // Call loadImgs
        loadButton(); // Call loadButton
        audioOptions = game.getAudioOptions(); // Set audioOptions
    }

    private void loadButton() {
        int menuX = (int) (387 * Constants.Game.SCALE); // Set menuX
        int menuY = (int) (325 * Constants.Game.SCALE); // Set menuY
        menuB = new UrmButton(menuX, menuY, URM_SIZE, URM_SIZE, 2); // Initialize menuB
    }

    private void loadImgs() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG); // Set backgroundImg
        optionsBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_MENU); // Set optionsBackgroundImg
        bgW = (int) (optionsBackgroundImg.getWidth() * Constants.Game.SCALE); // Set bgW
        bgH = (int) (optionsBackgroundImg.getHeight() * Constants.Game.SCALE); // Set bgH
        bgX = Constants.Game.GAME_WIDTH / 2 - bgW / 2; // Set bgX
        bgY = (int) (33 * Constants.Game.SCALE); // Set bgY
    }

    @Override
    public void update() {
        menuB.update(); // Update menuB
        audioOptions.update(); // Update audioOptions
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Constants.Game.GAME_WIDTH, Constants.Game.GAME_HEIGHT, null); // Draw backgroundImg
        g.drawImage(optionsBackgroundImg, bgX, bgY, bgW, bgH, null); // Draw optionsBackgroundImg
        menuB.draw(g); // Draw menuB
        audioOptions.draw(g); // Draw audioOptions
    }

    public void mouseDragged(MouseEvent e) {
        audioOptions.mouseDragged(e); // Call audioOptions.mouseDragged
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, menuB)) { // Check if mouse is in menuB
            menuB.setMousePressed(true); // Set menuB as pressed
        } else {
            audioOptions.mousePressed(e); // Call audioOptions.mousePressed
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, menuB)) { // Check if mouse is in menuB
            if (menuB.isMousePressed()) { // Check if menuB is pressed
                Gamestate.state = Gamestate.MENU; // Set state to MENU
            }
        } else {
            audioOptions.mouseReleased(e); // Call audioOptions.mouseReleased
        }
        menuB.resetBools(); // Reset menuB booleans
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        menuB.setMouseOver(false); // Set menuB mouse over to false
        if (isIn(e, menuB)) { // Check if mouse is in menuB
            menuB.setMouseOver(true); // Set menuB mouse over to true
        } else {
            audioOptions.mouseMoved(e); // Call audioOptions.mouseMoved
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // Check if ESC key is pressed
            Gamestate.state = Gamestate.MENU; // Set state to MENU
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No implementation needed here
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // No implementation needed here
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY()); // Check if mouse is in the bounds of the button
    }
}