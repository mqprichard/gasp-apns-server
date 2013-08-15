/*
 * Copyright (c) 2013 Mark Prichard, CloudBees
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloudbees.gasp.services;

import com.cloudbees.gasp.model.Restaurant;
import com.cloudbees.gasp.model.Review;
import com.cloudbees.gasp.model.User;
import com.google.gson.Gson;
import javapns.Push;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("/")
public class DataSyncService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSyncService.class.getName());
    private final InputStream p12KeyStore = this.getClass()
                                                .getClassLoader()
                                                .getResourceAsStream("GaspApns.p12");
    private static final Config config = new Config();

    @POST
    @Path("/reviews")
    @Consumes(MediaType.APPLICATION_JSON)
    public void reviewUpdateReceived(String jsonInput) {
        try {
            Review review = new Gson().fromJson(jsonInput, Review.class);
            LOGGER.info("Syncing Review Id: " + String.valueOf(review.getId()));

            String token = Datastore.getTokens().get(0);
            LOGGER.debug("APNS Device Token: " + token);

            //TODO: Replace simple javapns call
            Push.alert("Gasp! Review Update",
                    p12KeyStore,
                    config.getP12Pwd(),
                    false,
                    config.getToken());
        } catch (Exception e) {
            return;
        }
    }

    @POST
    @Path("/restaurants")
    @Consumes(MediaType.APPLICATION_JSON)
    public void restaurantUpdateReceived(String jsonInput) {
        Restaurant restaurant = new Gson().fromJson(jsonInput, Restaurant.class);
        LOGGER.info("Syncing Restaurant Id: " + String.valueOf(restaurant.getId()));

        String token = Datastore.getTokens().get(0);
        LOGGER.debug("APNS Device Token: " + token);

        try {
            //TODO: Replace simple javapns call
            Push.alert("Gasp! Restaurants Update",
                    p12KeyStore,
                    new Config().getP12Pwd(),
                    false,
                    token);
        } catch (Exception e) {
            return;
        }
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    public void userUpdateReceived(String jsonInput) {
        User user = new Gson().fromJson(jsonInput, User.class);
        LOGGER.info("Syncing User Id: " + String.valueOf(user.getId()));

        String token = Datastore.getTokens().get(0);
        LOGGER.debug("APNS Device Token: " + token);

        try {
            //TODO: Replace simple javapns call
            Push.alert("Gasp! Users Update",
                    p12KeyStore,
                    new Config().getP12Pwd(),
                    false,
                    token);
        } catch (Exception e) {
            return;
        }
    }
}
