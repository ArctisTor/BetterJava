package org.service.heartbeat;

import com.google.gson.JsonObject;

public interface DatabaseTestConnectionService {

    JsonObject testVtubersConnection();

    JsonObject testMeadConnection();
}
