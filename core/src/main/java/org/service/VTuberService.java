package org.service;

import org.object.Talent;

import java.util.List;

public interface VTuberService {

    List<Talent> getAllVTubers();
    List<Talent> getByOrganization(String orgName);
    Talent getVTuberTalentById(String id);
    List<Talent> getVTuberTalentByName(String name);
    Talent getShortestTalent();
    Talent debutVTuberTalent(Talent newTalent);
    Talent updateVTuberTalent(Talent updateTalent);
}
