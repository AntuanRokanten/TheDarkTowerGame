package com.anton4j.darktower.component.stage;

import com.anton4j.darktower.component.scene.Scene;

/**
 * @author anton
 */
public abstract class Stage {

    private final Stage next;
//    private final List<Option> options;
    private final Scene scene;

    public Stage(Stage next, Scene scene) {
        this.next = next;
//        this.options = options;
        this.scene = scene;
    }

//    public final List<Option> options() {
//        return options;
//    }

//    public void handleSelection(int index) {
//        Optional<Option> optionalOption = options
//              .stream()
//              .filter(option -> option.index() == index)
//              .findFirst();
//
//        if (optionalOption.isPresent()) {
////            optionalOption.get().;
//        } else {
//            // todo
//        }
//    }


    public Scene getScene() {
        return scene;
    }

    public Stage nextStage() {
        return next;
    }

    public abstract boolean stageCompleted();

}
