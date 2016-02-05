package be.ugent.psb.util.mvc.view;

/*
 * #%L
 * CoExpNetViz
 * %%
 * Copyright (C) 2015 PSB/UGent
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * A label, text field and a Browse button.
 */
public class FilePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
    private JLabel pathLabel;
    private JTextField pathTextField;
    private JButton browseButton;

    public FilePanel() {
        pathLabel = new JLabel("Path:");
        pathTextField = new JTextField();
        browseButton = new JButton("Browse");
        browseButton.setIcon(UIManager.getIcon("FileView.directoryIcon"));

        setMaximumSize(new Dimension(495, 64));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.insets = new Insets(0, 0, 0, 0);
        c.weightx = 0.05;
        c.gridx = 0;
        c.gridy = 0;
        add(pathLabel, c);
        c.weightx = 0.9;
        c.gridx = 1;
        c.gridy = 0;
        add(pathTextField, c);
        c.weightx = 0.05;
        c.gridx = 2;
        c.gridy = 0;
        add(browseButton, c);
    }

	public JLabel getPathLabel() {
		return pathLabel;
	}

	public JTextField getPathTextField() {
		return pathTextField;
	}

	public JButton getBrowseButton() {
		return browseButton;
	}

}