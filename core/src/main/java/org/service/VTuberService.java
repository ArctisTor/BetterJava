package org.service;

import org.object.Talent;

import java.util.List;

public interface VTuberService {

    public List<Talent> getAllVTubers();
    public List<Talent> getByOrganization(String orgName);
}
