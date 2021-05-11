/*
 * This file is part of RadPlanBio
 *
 * Copyright (C) 2013-2019 RPB Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.dktk.dd.rpb.core.domain.edc;

/**
 * OpenClinica status transient domain entity
 *
 * @author tomas@skripcak.net
 * @since 20 Jan 2014
 */
public class Status {

    //region Members

    private int id;
    private String name;
    private String description;

    //endregion

    //region Constructors

    public Status() {
        // NOOP
    }

    //endregion

    //region Properties

    //region ID

    public int getId() {
        return id;
    }

    public void setId(int value) {
        if (this.id != value) {
            this.id = value;
        }
    }

    //endregion

    //region Name

    public String getName() {
        return this.name;
    }

    public void setName(String value) {
        this.name = value;
    }

    //endregion

    //region Description

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    //endregion

    //endregion

}