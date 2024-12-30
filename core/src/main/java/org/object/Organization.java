package org.object;

import java.util.HashMap;

public class Organization extends HashMap<String, Object> {

    public String getId() {
        return (String) this.get("id");
    }

    public void setId(String id) {
        this.put("id", id);
    }

    public String getName() {
        return (String) this.get("name");
    }

    public void setName(String name) {
        this.put("name", name);
    }

    public String getOwner() {
        return (String) this.get("owner");
    }

    public void setOwner(String owner) {
        this.put("owner", owner);
    }

    public String getFounder() {
        return (String) this.get("founder");
    }

    public void setFounder(String founder) {
        this.put("founder", founder);
    }

    public String getWebsite() {
        return (String) this.get("website");
    }

    public void setWebsite(String website) {
        this.put("website", website);
    }

    public String getFoundedDate() {
        return (String) this.get("founded");
    }

    public void setFoundedDate(String foundedDate) {
        this.put("founded", foundedDate);
    }

    public String getIndustry() {
        return (String) this.get("industry");
    }

    public void setIndustry(String industry) {
        this.put("industry", industry);
    }

    public String[] getBrands() {
        return (String[]) this.get("brands");
    }

    public void setBrands(String[] brands) {
        this.put("brands", brands);
    }


}
