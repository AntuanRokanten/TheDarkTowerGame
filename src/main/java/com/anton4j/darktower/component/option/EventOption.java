package com.anton4j.darktower.component.option;

/**
 * @author anton
 */
public abstract class EventOption extends Option {

    protected EventOption(String displayText, int index) {
        super(displayText, index);
    }

//    @Override
//    public OptionResult processOption() {
//        EventResult process = optionEvent().process();
//
//        return new OptionResult<>(process.isSuccess(), process.resultObj());
//    }

//    public abstract Event optionEvent();

}
