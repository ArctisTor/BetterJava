package org.validator;

import org.object.Organization;
import org.object.Talent;
import org.service.vtuber.OrganizationService;

public class TalentValidator {

    protected final OrganizationService organizationService;
    protected final OrganizationValidator organizationValidator = new OrganizationValidator();

    public TalentValidator(OrganizationService organizationService) {
        if (organizationService == null) {
            throw new IllegalArgumentException("OrganizationService cannot be null");
        }
        this.organizationService = organizationService;
    }

    public boolean isTalentValid(Talent talent) {
        // Must have a name, birthday, organization, and height
        if (talent.getName() == null || talent.getName().isBlank() ||
                talent.getBirthday() == null || talent.getBirthday().isBlank() ||
                talent.getOrganization() == null || talent.getOrganization().isBlank() ||
                talent.getHeight() == null) {
            return false;
        }

        Organization talentOrg;
        try {
            talentOrg = organizationService.getOrganizationByName(talent.getOrganization());
        } catch (RuntimeException e) {
            return false;
        }
        // The organization must exist in the database first
        if (talentOrg == null) {
            return false;
        } else if (!organizationValidator.isOrganizationValid(talentOrg)) {
            // must be a valid organization
            return false;
        } else if (organizationValidator.isValidCorporationOrganization(talentOrg)) {
            // If the talent is a corporation talent, then they have to have the required
            // fields
            return isValidCorporationTalent(talent);
        }
        return true;
    }

    public boolean isValidCorporationTalent(Talent talent) {
        // Corporation talents must be in a unit and have a fan name
        return !(talent.getUnit() == null || talent.getUnit().isBlank()
                || talent.getFanName() == null || talent.getFanName().isBlank());
    }
}
