package wdk.file;

import wdk.data.Draft;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import wdk.data.Player;

/**
 * This interface provides an abstraction of what a file manager should do. Note
 * that file managers know how to read and write drafts, instructors, and subjects,
 * but now how to export sites.
 * 
 * @author Richard McKenna
 */
public interface DraftFileManager {
    public void             saveDraft(Draft draftToSave) throws IOException;
    public void             loadDraft(Draft draftToLoad, String draftPath) throws IOException;
    public Draft            loadStartingDraft(String filePathHitters, String filePathPitchers) throws IOException;

}
