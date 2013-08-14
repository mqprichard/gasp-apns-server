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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/")
public class DataSyncService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSyncService.class.getName());

    @POST
    @Path("/reviews")
    @Consumes(MediaType.APPLICATION_JSON)
    public void reviewUpdateReceived(String jsonInput) {
        Review review = new Gson().fromJson(jsonInput, Review.class);
        LOGGER.info("Syncing Review Id: " + String.valueOf(review.getId()));

        try {

        } catch (Exception e) {
        }
    }

    @POST
    @Path("/restaurants")
    @Consumes(MediaType.APPLICATION_JSON)
    public void restaurantUpdateReceived(String jsonInput) {
        Restaurant restaurant = new Gson().fromJson(jsonInput, Restaurant.class);
        LOGGER.info("Syncing Restaurant Id: " + String.valueOf(restaurant.getId()));

        try {

        } catch (Exception e) {
        }
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    public void userUpdateReceived(String jsonInput) {
        User user = new Gson().fromJson(jsonInput, User.class);
        LOGGER.info("Syncing User Id: " + String.valueOf(user.getId()));

        try {
        } catch (Exception e) {
        }
    }
}