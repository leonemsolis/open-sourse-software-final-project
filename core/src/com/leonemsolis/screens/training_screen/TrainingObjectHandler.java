package com.leonemsolis.screens.training_screen;

import com.leonemsolis.main.MainGameClass;
import com.leonemsolis.screens.training_screen.objects.ExperienceButton;

/**
 * Created by kmusw on 2017-11-21.
 */

public class TrainingObjectHandler {

    public ExperienceButton experience;
    public MainGameClass mainGameClass;

    public TrainingObjectHandler(MainGameClass mainGameClass) {
        this.mainGameClass = mainGameClass;
        experience = new ExperienceButton(0, 0);
    }

    public void update() {

    }
}
