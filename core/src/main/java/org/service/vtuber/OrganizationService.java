package org.service.vtuber;

import org.object.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> getAllOrganizations(int limit, int offset);

    Organization getOrganizationByName(String name);

    Organization getOrganizationById(String id);

    Organization addOrganization(Organization organization);
}
