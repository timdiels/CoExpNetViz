package be.ugent.psb.coexpnetviz.gui.controller;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.google.common.collect.MapDifference.ValueDifference;

import be.ugent.psb.coexpnetviz.gui.model.JobInputModel;
import be.ugent.psb.util.JComboBoxes;
import be.ugent.psb.util.mvc.model.MapListener;
import be.ugent.psb.util.mvc.model.MapModel;
import be.ugent.psb.util.mvc.model.NamedObject;
import be.ugent.psb.util.mvc.model.ValueModel;

/**
 * Controls set of presets, including loading from and saving to current form input
 */
public class PresetsController { // TODO external controller should save to disk when presets change

    public PresetsController(final ValueModel<JobInputModel> currentInputModel, final MapModel<String, JobInputModel> presets, 
    		final JComboBox<NamedObject<JobInputModel>> presetComboBox, final JButton saveButton, final JButton saveAsButton, 
    		final JButton deleteButton) {
    	
    	// When presets change, update combo box
    	presets.addListener(new MapListener<String, JobInputModel>() {
			@Override
			public void mapChanged(Map<String, JobInputModel> source, Map<String, JobInputModel> entriesInserted,
					Map<String, ValueDifference<JobInputModel>> entriesChanged, Map<String, JobInputModel> entriesRemoved)
			{
				Object selected = presetComboBox.getSelectedItem();
				presetComboBox.removeAllItems();
				for (Entry<String, JobInputModel> entry : presets.entrySet()) {
					presetComboBox.addItem(new NamedObject<>(entry.getKey(), entry.getValue()));
				}
				presetComboBox.setSelectedItem(selected);
			}
		});
    	
    	// When user selects in combo box, load selected preset 
    	presetComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				currentInputModel.set(JComboBoxes.getSelectedItem(presetComboBox).getObject().clone_());
			}
		});
    	
    	// When combo box selection changes, update buttons
    	presetComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
			    boolean hasSelection = presetComboBox.getSelectedItem() == null;
			    saveButton.setEnabled(hasSelection);
			    deleteButton.setEnabled(hasSelection);
			}
    	});
    	
    	// When save pressed, save current model to current preset
    	saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JComboBoxes.getSelectedItem(presetComboBox).getName();
				presets.put(name, currentInputModel.get().clone_());
			}
		});
    	
    	// When save as pressed, save current model to a new preset
    	saveAsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				assert false;
				String name = ""; // TODO show dialog with editable combobox or instead use editable combobox in the whole and add a Load button (the latter is prolly better)
				presets.put(name, currentInputModel.get().clone_());
			}
		});
    	
    	// When delete pressed, delete current preset and clear selection
    	deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JComboBoxes.getSelectedItem(presetComboBox).getName();
				presets.remove(name);
			}
		});
    	
    	// old stuff  TODO rm
//	            getGuiManager().delCurrentProfile();
//	            try {        
//    	          getGuiManager().saveProfiles();
//	            } catch (IOException ex) {
//	                JOptionPane.showMessageDialog(getGuiManager().getInpPnl(),
//	                    "There was an error while saving the profile\n" + ex.getMessage(),
//	                    "Error",
//	                    JOptionPane.ERROR_MESSAGE);
//	            }
    }
    
}