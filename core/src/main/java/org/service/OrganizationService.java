package org.service;

import org.object.Organization;

import java.util.List;

public interface OrganizationService {

    List<Organization> getAllOrganizations();
    Organization getOrganizationByName(String name);
    Organization getOrganizationById(String id);
}
