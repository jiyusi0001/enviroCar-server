/*
 * Copyright (C) 2013 The enviroCar project
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.car.server.rest.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

import io.car.server.core.activities.Activities;
import io.car.server.core.activities.ActivityType;
import io.car.server.core.entities.User;
import io.car.server.core.filter.ActivityFilter;
import io.car.server.core.util.Pagination;
import io.car.server.rest.MediaTypes;
import io.car.server.rest.RESTConstants;
import io.car.server.rest.Schemas;
import io.car.server.rest.validation.Schema;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <autermann@uni-muenster.de>
 */
public class FriendsActivitiesResource extends AbstractResource {
    private User user;

    @AssistedInject
    public FriendsActivitiesResource(@Assisted User user) {
        this.user = user;
    }

    @GET
    @Schema(response = Schemas.ACTIVITIES)
    @Produces({ MediaTypes.ACTIVITIES,
                MediaTypes.XML_RDF,
                MediaTypes.TURTLE,
                MediaTypes.TURTLE_ALT })
    public Activities statistics(
            @QueryParam(RESTConstants.TYPE) ActivityType type,
            @QueryParam(RESTConstants.LIMIT) @DefaultValue("0") int limit,
            @QueryParam(RESTConstants.PAGE) @DefaultValue("0") int page) {
        return getUserService()
                .getActivities(new ActivityFilter(user, new Pagination(limit, page)));
    }
}
