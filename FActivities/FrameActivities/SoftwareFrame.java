/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameActivities;

import javax.swing.*;

/**
 *
 * @author Elena Ngo
 */
public class SoftwareFrame extends JFrame{
    
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 500;
    
    private MainMenuFrame _menu;
    
    private JPanel _panel;

    public SoftwareFrame()
    {
        frameComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false); //window cannot be resizable
    }
    
    /**
     * Adds the JFrame components into software frame
     */
    private void frameComponents()
    {
        //Uses MainMenuFrame first to get Menu Screen
        _panel = new JPanel();
        _panel.setLayout(null);
        _menu = new MainMenuFrame(_panel);
        
        add(_panel);
    }
}
