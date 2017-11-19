package com.leonemsolis.screens.TestScreen;

/**
 * Created by Leonemsolis on 31/10/2017.
 */

public class TestRenderer {
    private ObjectHandler handler;
    public TestRenderer(ObjectHandler handler) {
        this.handler = handler;
    }


    public void render() {
        handler.testButton.render(null);
    }
}
