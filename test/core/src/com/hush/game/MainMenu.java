package com.hush.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hush.game.UI.Settings;

public class MainMenu extends ScreenAdapter {
    Settings game;
    SpriteBatch batch;
    Texture titleText;
    Texture startText;
    Texture settingsText;
    Texture quitText;
    Sound sound;

    int cursorX;
    int cursorY;
    float buttonWidth = Gdx.graphics.getWidth() / 5;
    float buttonHeight = Gdx.graphics.getHeight() / 9;
    float buttonX = Gdx.graphics.getWidth() / 2 - buttonWidth / 2;
    float titleY = buttonHeight * 7;
    float startY = buttonHeight * 5;
    float settingsY = buttonHeight * 3;
    float quitY = buttonHeight;

    public MainMenu(Settings game) {
        this.game = game;
        batch = new SpriteBatch();
        titleText = new Texture("Text/titleText.png");
        startText = new Texture("Text/startText.png");
        settingsText = new Texture("Text/settingsText.png");
        quitText = new Texture("Text/quitText.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("Menu1.wav"));
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            // Main Menu Input
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                cursorX = Gdx.input.getX();
                cursorY = Gdx.graphics.getHeight() - Gdx.input.getY();
                // Start Button
                if (cursorX > buttonX && cursorX < buttonX + buttonWidth) {
                    if (cursorY > startY && cursorY < startY + buttonHeight) {
                        if (Gdx.input.isTouched()) {
                            sound.play(0.25f);
                            game.setScreen(new LevelSelect(game));
                        }
                    }
                }
                // Settings Button
                if (cursorX > buttonX && cursorX < buttonX + buttonWidth) {
                    if (cursorY > settingsY && cursorY < settingsY + buttonHeight) {
                        if (Gdx.input.isTouched()) {
                            sound.play(0.25f);
                            game.setScreen(new SettingsScreen(game));
                        }
                    }
                }
                // Quit Button
                if (cursorX > buttonX && cursorX < buttonX + buttonWidth) {
                    if (cursorY > quitY && cursorY < quitY + buttonHeight) {
                        if (Gdx.input.isTouched()) {
                            sound.play(0.25f);
                            System.exit(0);
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Renders Menu Buttons
        batch.begin();
        batch.draw(titleText, buttonX, titleY, buttonWidth, buttonHeight);
        batch.draw(startText, buttonX, startY, buttonWidth, buttonHeight);
        batch.draw(settingsText, buttonX, settingsY, buttonWidth, buttonHeight);
        batch.draw(quitText, buttonX, quitY, buttonWidth, buttonHeight);
        batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}