package org.object;

import com.google.gson.JsonObject;

import java.util.HashMap;

public class Talent extends HashMap<String, Object> {

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

    public String getDebut() {
        return (String) this.get("debut");
    }

    public void setDebut(String debut) {
        this.put("debut", debut);
    }

    public String getBirthday() {
        return (String) this.get("birthday");
    }

    public void setBirthday(String birthday) {
        this.put("birthday", birthday);
    }

    public String getOrganization() {
        return (String) this.get("organization");
    }

    public void setOrganization(String organization) {
        this.put("organization", organization);
    }

    public String getUnit() {
        return (String) this.get("unit");
    }

    public void setUnit(String unit) {
        this.put("unit", unit);
    }

    public Double getHeight() {
        return (Double) this.get("height");
    }

    public void setHeight(Double height) {
        this.put("height", height);
    }

    public String getFanName() {
        return (String) this.get("fanName");
    }

    public void setFanName(String fanName) {
        this.put("fanName", fanName);
    }

    public JsonObject toJsonObject() {
        JsonObject json = new JsonObject();
        for (Entry<String, Object> entry : this.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                json.addProperty(entry.getKey(), (String) value);
            } else if (value instanceof Number) {
                json.addProperty(entry.getKey(), (Number) value);
            } else if (value instanceof Boolean) {
                json.addProperty(entry.getKey(), (Boolean) value);
            } else {
                json.add(entry.getKey(), null); // Handle other types as needed
            }
        }
        return json;
    }
}
