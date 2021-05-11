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

package de.dktk.dd.rpb.core.domain.pacs;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({RtImageDicomSeries.class, Logger.class})
public class RtImageDicomSeriesTest {
    private Logger logger;
    private RtImageDicomSeries rtImageSeries;

    @Before
    public void setUp() throws Exception {
        mockStatic(Logger.class);
        logger = mock(Logger.class);
        when(Logger.getLogger(any(Class.class))).thenReturn(logger);

        rtImageSeries = new RtImageDicomSeries();
    }

    //region getUserViewSeriesDescription
    @Test
    public void getSeriesDescription_returns_unmodified_description() {
        String dummyDescription = "dummyDescription";
        rtImageSeries.setSeriesDescription(dummyDescription);

        assertEquals(dummyDescription, rtImageSeries.getUserViewSeriesDescription());
    }

    @Test
    public void getSeriesDescription_returns_rtImageName_if_description_is_empty() {
        String dummyDescription = "";
        String rtImageName = "dummy RtImageName";
        rtImageSeries.setSeriesDescription(dummyDescription);
        rtImageSeries.setRtImageName(rtImageName);

        assertEquals(rtImageName, rtImageSeries.getUserViewSeriesDescription());
    }

    @Test
    public void getSeriesDescription_returns_rtImageLabel_if_rtImageName_is_empty() {
        String emptyString = "";
        String rtImageLabel = "dummy RtImagelabel";
        rtImageSeries.setSeriesDescription(emptyString);
        rtImageSeries.setRtImageName(emptyString);
        rtImageSeries.setRtImageLabel(rtImageLabel);

        assertEquals(rtImageLabel, rtImageSeries.getUserViewSeriesDescription());
    }

    @Test
    public void getSeriesDescription_returns_rtImageDescription_if_rtImageLabel_is_empty() {
        String emptyString = "";
        String rtImageDescription = "dummy RtImageDescription";
        rtImageSeries.setSeriesDescription(emptyString);
        rtImageSeries.setRtImageName(emptyString);
        rtImageSeries.setRtImageLabel(emptyString);
        rtImageSeries.setRtImageDescription(rtImageDescription);

        assertEquals(rtImageDescription, rtImageSeries.getUserViewSeriesDescription());
    }

    @Test
    public void getSeriesDescription_returns_rtImageDescription_if_other_members_where_null() {
        String emptyString = "";
        String rtImageDescription = "dummy RtImageDescription";
        rtImageSeries.setSeriesDescription(emptyString);
        rtImageSeries.setRtImageDescription(rtImageDescription);

        assertEquals(rtImageDescription, rtImageSeries.getUserViewSeriesDescription());
    }

    //endregion
}