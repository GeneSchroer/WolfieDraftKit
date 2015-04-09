package wdk.file;

import wdk.data.Draft;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This interface provides an abstraction of what a file manager should do. Note
 * that file managers know how to read and write drafts, instructors, and subjects,
 * but now how to export sites.
 * 
 * @author Richard McKenna
 */
public interface DraftFileManager {
    public void                 saveDraft(Draft draftToSave) throws IOException;
    public void                 loadDraft(Draft draftToLoad, String draftPath) throws IOException;
}
