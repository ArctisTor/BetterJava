package org.service;

import org.object.Talent;

import java.util.List;

public interface VTuberService {

    List<Talent> getAllVTubers();
    List<Talent> getByOrganization(String orgName);
    Talent getShortestTalent();
    Talent debutVTuberTalent(Talent newTalent);
}
