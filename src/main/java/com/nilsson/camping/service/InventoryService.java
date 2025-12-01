package com.nilsson.camping.service;

import com.nilsson.camping.model.items.Gear;
import com.nilsson.camping.model.items.RecreationalVehicle;
import com.nilsson.camping.model.registries.Inventory;
import com.nilsson.camping.ui.UIUtil;

public class InventoryService {

    // ──────────────────────────────────────────────────────
    // Vehicle Operations
    // ──────────────────────────────────────────────────────
    public RecreationalVehicle handleAddRecreationalVehicle() {
        // Step 1: Display the input form and collect data (simulated result for brevity)
        String make = "Volvo";
        String model = "L70";
        String type = "Wheel-loader";
        double dailyPrice = 1000;
        String year = "2025";
        String capacity = "12 tons";

        // Check if the user cancelled the dialog (e.g., if make is null)
        if (make == null) {
            // User cancelled the operation
            return null; // Must return null if the operation is cancelled
        }

        // Create a new RecreationalVehicle object
        Inventory inventory = Inventory.getInstance();
        RecreationalVehicle newRecreationalVehicle = new RecreationalVehicle(make, model, type, dailyPrice, year, capacity);

        // Add the new vehicle to the registry
        inventory.addRecreationalVehicle(newRecreationalVehicle);

        // Show success confirmation
        UIUtil.showInfoAlert("Recreational Vehicle Added", "Success", newRecreationalVehicle.getMake() + " " + newRecreationalVehicle.getModel() + " has been successfully added.");

        // Return the newly created object for the UI view to display
        return newRecreationalVehicle;
    }


    // Accept the Vehicle object from the View
    public void handleEditRecreationalVehicle(RecreationalVehicle selectedRecreationalVehicle) {
        if (selectedRecreationalVehicle != null) {
            UIUtil.showInfoAlert("Edit Recreational Vehicle", "Functionality Pending", "A dialog/form for editing recreational vehicle " + selectedRecreationalVehicle.getMake() + " will be implemented here.");
        } else {
            // Error handling remains in the service, but ideally, the view handles null
        }
    }

    public boolean handleRemoveRecreationalVehicle(RecreationalVehicle selectedRecreationalVehicle) {
        if (selectedRecreationalVehicle == null) {
            return false;
        }

        // This method removes the item from the in-memory list AND calls DataHandler.saveRecreationalVehicle().
        Inventory.getInstance().removeRecreationalVehicle(selectedRecreationalVehicle);

        // The UIUtil alert should ideally be called based on the result of the removal.
        UIUtil.showInfoAlert("Recreational Vehicle Removed", "Success", selectedRecreationalVehicle.getMake() + " " + selectedRecreationalVehicle.getModel() + " has been removed.");
        return true;
    }

    // ──────────────────────────────────────────────────────
    // Gear Operations
    // ──────────────────────────────────────────────────────

    public Gear handleAddGear() {
        // Step 1: Simulate user input for new gear
        String model = "Trekking Tent";
        String type = "Shelter";
        String capacity = "4 Person";
        double dailyPrice = 150.00;

        // Check for cancellation (placeholder for a real dialog)
        if (model == null) {
            return null;
        }

        // Create a new Gear object
        Inventory inventory = Inventory.getInstance();
        Gear newGear = new Gear(model, type, capacity, dailyPrice);

        // Add to registry
        inventory.addGear(newGear);

        // Show success confirmation
        UIUtil.showInfoAlert("Gear Added", "Success", newGear.getModel() + " has been successfully added.");
        // Return the newly created object for the UI view to display
        return newGear;
    }

    public boolean handleRemoveGear(Gear selectedGear) {
        if (selectedGear == null) {
            return false;
        }

        // Call the Inventory registry's removal method.
        boolean wasRemoved = Inventory.getInstance().removeGear(selectedGear);

        if (wasRemoved) {
            // Show success confirmation only if the removal was successful
            UIUtil.showInfoAlert("Gear Removed", "Success", selectedGear.getModel() + " has been successfully removed.");
        } else {
            // Error handling if registry failed to remove
            UIUtil.showErrorAlert("Removal Error", "Registry Mismatch", "Could not find the selected gear in the inventory registry.");
        }
        return wasRemoved;
    }
}