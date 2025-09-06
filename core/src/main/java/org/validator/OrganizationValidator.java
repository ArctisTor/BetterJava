package org.validator;

import org.constants.OrganizationConstants;
import org.object.Organization;

public class OrganizationValidator {

    public boolean isOrganizationValid(Organization org) {
        if (org.getId() == null || org.getName() == null ||
                org.getId().isBlank() || org.getName().isBlank()) {
            return false;
        } else if (!org.getName().equals(OrganizationConstants.INDIE_ORG)) {
            return isValidCorporationOrganization(org);
        } else {
            return true;
        }
    }

    public boolean isValidCorporationOrganization(Organization org) {
        //must have an owner, a website, a founded date, and a founder
        return org.getOwner() != null && !org.getOwner().isEmpty()
                && org.getWebsite() != null && !org.getWebsite().isEmpty()
                && org.getFoundedDate() != null && !org.getFoundedDate().isEmpty()
                && org.getFounder() != null && !org.getFounder().isEmpty();

    }
}
