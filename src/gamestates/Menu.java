package gamestates;

import main.Game;
import ui.MenuButton;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backgroundImg, backgroundImgPink;
    private int menuX, menuY, menuWidth, menuHeight;

    public Menu(Game game) {
        super(game);
        loadButtons(); // Call loadButtons
        loadBackground(); // Call loadBackground
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG); // Set backgroundImgPink
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND); // Set backgroundImg
        menuWidth = (int) (backgroundImg.getWidth() * Constants.Game.SCALE); // Set menuWidth
        menuHeight = (int) (backgroundImg.getHeight() * Constants.Game.SCALE); // Set menuHeight
        menuX = Constants.Game.GAME_WIDTH / 2 - menuWidth / 2; // Set menuX
        menuY = (int) (45 * Constants.Game.SCALE); // Set menuY
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Constants.Game.GAME_WIDTH / 2, (int) (150 * Constants.Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Constants.Game.GAME_WIDTH / 2, (int) (220 * Constants.Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Constants.Game.GAME_WIDTH / 2, (int) (290 * Constants.Game.SCALE), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons) { // Loop through buttons
            mb.update(); // Call update on each button
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImgPink, 0, 0, Constants.Game.GAME_WIDTH, Constants.Game.GAME_HEIGHT, null); // Draw backgroundImgPink
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null); // Draw backgroundImg
        for (MenuButton mb : buttons) { // Loop through buttons
            mb.draw(g); // Draw each button
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // No implementation needed
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) { // Loop through buttons
            if (isIn(e, mb)) { // Check if mouse is in button
                mb.setMousePressed(true); // Set button as pressed
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) { // Loop through buttons
            if (isIn(e, mb)) { // Check if mouse is in button
                if (mb.isMousePressed()) { // Check if button is pressed
                    mb.applyGamestate(); // Apply game state
                    if (mb.getState() == Gamestate.PLAYING) { // If state is PLAYING
                        game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex()); // Set level song
                    }
                    break;
                }
            }
        }
        resetButtons(); // Reset buttons
    }

    private void resetButtons() {
        for (MenuButton mb : buttons) { // Loop through buttons
            mb.resetBools(); // Reset button booleans
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons) { // Loop through buttons
            mb.setMouseOver(false); // Set mouse over to false
        }
        for (MenuButton mb : buttons) { // Loop through buttons
            if (isIn(e, mb)) { // Check if mouse is in button
                mb.setMouseOver(true); // Set mouse over to true
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // No implementation needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No implementation needed
    }
}