/**
 * Creator: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314)
 * Testers: Ian Nathaniel Chew (99140), Abang Muhamad Fikrul Amin Abang Nazaruddin (97907), Shamrin bin Al Idrus (100973)
 */

// Interface: ContentDisplayable
// Description: Interface for content modules that support page navigation (e.g., learning tips).

import javax.swing.JPanel;

// Interface for content modules with multiple pages (e.g. Learn Tips)
public interface ContentDisplayable {
    
    // Go to next page
    void nextPage();

    // Go to previous page
    void prevPage();

    // Get the current visible page panel
    JPanel getCurrentPagePanel();
}
