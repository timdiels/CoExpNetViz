package be.ugent.psb.util.javafx.controller;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import be.ugent.psb.util.Strings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/*
 * #%L
 * CoExpNetViz
 * %%
 * Copyright (C) 2015 - 2016 PSB/UGent
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

/**
 * Browse button handler
 */
public class BrowseButtonHandler implements EventHandler<ActionEvent> {

	private String browseDialogTitle;
	private StringProperty pathProperty;
	private Window window;
	private ObjectProperty<Path> lastBrowsedPathProperty;
	
    public BrowseButtonHandler(String browseDialogTitle, StringProperty pathProperty, Window window, ObjectProperty<Path> lastBrowsedPathProperty) {
		this(browseDialogTitle, window, lastBrowsedPathProperty);
		this.pathProperty = pathProperty;
	}
    
    public BrowseButtonHandler(String browseDialogTitle, Window window, ObjectProperty<Path> lastBrowsedPathProperty) {
		this.browseDialogTitle = browseDialogTitle;
		this.window = window;
		this.lastBrowsedPathProperty = lastBrowsedPathProperty;
	}

	@Override
	public void handle(ActionEvent arg0) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(browseDialogTitle);
		
		// Get path of pathProperty
		Path path = null;
		if (!Strings.isNullOrEmpty(pathProperty.get())) {
			try {
				path = Paths.get(pathProperty.get().trim());
			}
			catch (InvalidPathException e) {
			}
		}
		
		// Set initial path
		if (path != null) {
			if (path.getParent() != null) {
				fileChooser.setInitialDirectory(path.getParent().toFile());
			}
			fileChooser.setInitialFileName(path.getFileName().toString());
		}
		else {
			fileChooser.setInitialDirectory(lastBrowsedPathProperty.get().toFile());
		}
		
		//
		File file = fileChooser.showOpenDialog(window);
		if (file != null) {
			path = file.toPath().toAbsolutePath();
			lastBrowsedPathProperty.set(path.getParent());
			pathProperty.set(path.toString());
		}
	}
	
	public void setPathProperty(StringProperty pathProperty) {
		this.pathProperty = pathProperty;
	}
    
}
