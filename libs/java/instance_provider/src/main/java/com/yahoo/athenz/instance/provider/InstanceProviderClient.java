//
// This file generated by rdl 1.4.14. Do not modify!
//
package com.yahoo.athenz.instance.provider;

import com.yahoo.rdl.*;
import javax.ws.rs.client.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.net.ssl.HostnameVerifier;

public class InstanceProviderClient {
    Client client;
    WebTarget base;
    String credsHeader;
    String credsToken;

    public InstanceProviderClient(String url) {
        client = ClientBuilder.newClient();
        base = client.target(url);
    }

    public InstanceProviderClient(String url, HostnameVerifier hostnameVerifier) {
        client = ClientBuilder.newBuilder()
            .hostnameVerifier(hostnameVerifier)
            .build();
        base = client.target(url);
    }

    public InstanceProviderClient(String url, Client rsClient) {
        client = rsClient;
        base = client.target(url);
    }
    
    public void close() {
        client.close();
    }

    public InstanceProviderClient setProperty(String name, Object value) {
        client = client.property(name, value);
        return this;
    }

    public InstanceProviderClient addCredentials(String header, String token) {
        credsHeader = header;
        credsToken = token;
        return this;
    }

    public InstanceConfirmation postInstanceConfirmation(InstanceConfirmation confirmation) {
        WebTarget target = base.path("/instance");
        Invocation.Builder invocationBuilder = target.request("application/json");
        if (credsHeader != null) {
            invocationBuilder = invocationBuilder.header(credsHeader, credsToken);
        }
        Response response = invocationBuilder.post(javax.ws.rs.client.Entity.entity(confirmation, "application/json"));
        int code = response.getStatus();
        switch (code) {
        case 200:
            return response.readEntity(InstanceConfirmation.class);
        default:
            throw new ResourceException(code, response.readEntity(ResourceError.class));
        }

    }

}
