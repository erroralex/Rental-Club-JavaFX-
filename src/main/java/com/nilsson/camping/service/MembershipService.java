package com.nilsson.camping.service;

import com.nilsson.camping.model.Member;
import com.nilsson.camping.model.registries.MemberRegistry;
import com.nilsson.camping.ui.UIUtil;

public class MembershipService {

    public Member handleAddMember() {
        // Step 1: Display the input form and collect data (simulated result for brevity)
        String firstName = "Lena";
        String lastName = "Svensson";
        String membershipLevel = "Premium";

        // Check if the user cancelled the dialog (e.g., if firstName is null)
        if (firstName == null) {
            // User cancelled the operation
            return null; // Return null if nothing was added
        }

        // Step 2: Create a new Member object
        MemberRegistry registry = MemberRegistry.getInstance();
        int newId = registry.getUniqueID(); // Get a unique ID

        Member newMember = new Member(newId, firstName, lastName, membershipLevel, null);

        // Step 3: Add the new member to the registry (handles persistence)
        registry.addMember(newMember);

        // Step 4: Show success confirmation
        UIUtil.showInfoAlert("Member Added", "Success",
                newMember.getFirstName() + " " + newMember.getLastName() + " (ID: " + newMember.getId() + ") has been successfully added.");

        // Return the newly created member object
        return newMember;
    }

    // Accept the Member object from the View
    public void handleEditMember(Member selectedMember) {
        if (selectedMember != null) {
            UIUtil.showInfoAlert("Edit Member", "Functionality Pending",
                    "A dialog/form for editing member " + selectedMember.getFirstName() + " will be implemented here.");
        } else {
            // Error handling remains in the service, but ideally, the view handles null
        }
    }

    /**
     * Attempts to remove a member from the registry and triggers persistence.
     * @param selectedMember The Member object to remove.
     * @return true if the member was successfully removed, false otherwise.
     */
    public boolean removeMemberFromRegistry(Member selectedMember) {
        if (selectedMember == null) {
            return false;
        }

        // Call the Registry's remove method.
        boolean wasRemoved = MemberRegistry.getInstance().removeMember(selectedMember);

        if (wasRemoved) {
            UIUtil.showInfoAlert("Member Removed", "Success",
                    selectedMember.getFirstName() + " " + selectedMember.getLastName() + " has been successfully removed.");
        } else {
            // This might happen if the member exists in the UI but was somehow already removed from the registry
            UIUtil.showErrorAlert("Removal Error", "Error", "Could not find member in registry.");
        }

        return wasRemoved;
    }
}