package view;

import javax.swing.*;
import java.awt.*;

public class AboutBoxView
{
    private static final String MESSAGE = "JabberPoint is a primitive slide-show program in Java(tm). It\n" +
            "is freely copyable as long as you keep this notice and\n" +
            "the splash screen intact.\n" +
            "Copyright (c) 1995-1997 by Ian F. Darwin ian@darwinsys.com.\n" +
            "Adapted by Gert Florijn (version 1.1) and " +
            "Sylvia Stuurman (version 1.2 and higher) for the Open" +
            "University of the Netherlands 2002 -- now.\n" +
            "Author's version available from http://www.darwinsys.com/";
    private static final String TITLE = "About JabberPoint";

    public static void showDialog(Frame parent)
    {
        JOptionPane.showMessageDialog(parent, MESSAGE, TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}