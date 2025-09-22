/**
 * Creator: Ian Nathaniel Chew (99140)
 * Testers: Wan Fathanulhanif Edruce Bin Wan Abdillah Edruce (101314), Abang Muhamad Fikrul Amin Abang Nazaruddin (97907), Shamrin bin Al Idrus (100973)
 */

// Interface: GUIManager
// Description: Defines the interface for GUI control including initialization and panel switching.

// Interface for GUI control methods
public interface GUIManager {

    // Set up the GUI components
    void initUI();

    // Switch between panels (e.g. Dashboard, Quiz, Scoreboard)
    void switchPanel(String name);
}
