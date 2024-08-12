package advancedwiring.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScopeClient1 {

    private Notepad notepad;

    @Autowired
    public void setNotepad(Notepad notepad) {
        this.notepad = notepad;
    }

    public Notepad getNotepad() {
        return notepad;
    }
}
