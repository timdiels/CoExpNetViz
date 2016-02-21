package be.ugent.psb.util.javafx.controller;

import java.io.File;

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
	
    public BrowseButtonHandler(String browseDialogTitle, StringProperty pathProperty, Window window) {
		this(browseDialogTitle, window);
		this.pathProperty = pathProperty;
	}
    
    public BrowseButtonHandler(String browseDialogTitle, Window window) {
		this.browseDialogTitle = browseDialogTitle;
		this.window = window;
	}

	@Override
	public void handle(ActionEvent arg0) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(browseDialogTitle);
		File path = fileChooser.showOpenDialog(window);
		if (path != null) {
			pathProperty.set(path.toString());
		}
	}
	
	public void setPathProperty(StringProperty pathProperty) {
		this.pathProperty = pathProperty;
	}
    
}