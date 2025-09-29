/*
 * Created by Lee Hao Ming (99451)
 * Tested by Isaac Shagal Anak Tinggal (99176)
 * Description:
 * Interface for managing and navigating a learning module consisting of content pages.
 */

import javax.swing.JPanel;

public interface ILearningModule {

    /**
     * Load content items (e.g., text pages, images) into memory.
     * This method initializes the module's internal list of content pages.
     */
    public void loadContent();

    /** 
     * @return a Swing panel that renders the current content page. 
     */
    JPanel getContentPanel();

    /** Advance to the next content page. */
    void nextPage();

    /** Go back to the previous content page. */
    void prevPage();
}
