/*
 * This file is part of RadPlanBio
 *
 * Copyright (C) 2013-2020 RPB Team
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

import com.google.common.base.Objects;
import de.dktk.dd.rpb.core.domain.Identifiable;
import de.dktk.dd.rpb.core.domain.IdentifiableHashBuilder;
import de.dktk.dd.rpb.core.util.Constants;
import de.dktk.dd.rpb.core.util.DicomStudyDescriptionEdcCodeUtil;
import org.apache.log4j.Logger;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * RPB DICOM Study Series domain object to encapsulate data coming from PACS
 *
 * @author tomas@skripcak.net
 * @since 07 August 2013
 */
@XmlRootElement
public class DicomSeries implements Identifiable<Integer>, Serializable {

    //region Finals

    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(DicomSeries.class);

    //endregion

    //region Members

    private Integer id; // unused for Transient entity

    private String seriesInstanceUID;
    private String frameOfReferenceUid;
    private String seriesDescription;
    private String seriesModality;
    private String seriesTime;

    private List<DicomImage> seriesImages;

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    //endregion

    //region Constructors

    public DicomSeries() {
        // NOOP
    }

    //endregion

    //region Properties

    //region Id

    @XmlTransient
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    @Transient
    public boolean isIdSet() {
        return this.id != null;
    }

    //endregion

    //region Uid

    public String getSeriesInstanceUID() {
        return seriesInstanceUID;
    }

    public void setSeriesInstanceUID(String value) {
        this.seriesInstanceUID = value;
    }

    //endregion
    // region FrameOfReferencingUid

    @Transient
    public String getFrameOfReferenceUid() {
        return frameOfReferenceUid;
    }

    @Transient
    public void setFrameOfReferenceUid(String frameOfReferenceUid) {
        this.frameOfReferenceUid = frameOfReferenceUid;
    }

    //endregion

    //region Description

    public String getSeriesDescription() {
        return seriesDescription;
    }

    public void setSeriesDescription(String value) {
        this.seriesDescription = value;
    }

    /**
     * Consolidated and enhanced Series Description for UI activities of the user.
     * An empty description strings will be replaced by information from optional fields depending on the modality
     * of the study.
     *
     * @return String modified SeriesDescription
     */
    @XmlTransient
    public String getUserViewSeriesDescription() {
        return DicomStudyDescriptionEdcCodeUtil.removeEdcCodePrefix(seriesDescription);
    }

    //endregion

    //region Modality

    public String getSeriesModality() {
        return this.seriesModality;
    }

    public void setSeriesModality(String value) {
        this.seriesModality = value.trim();
    }

    //endregion

    //region SeriesTime

    public String getSeriesTime() {
        return this.seriesTime;
    }

    public void setSeriesTime(String value) {
        this.seriesTime = value;
    }

    public Date getTimeSeries() {
        if (this.seriesTime != null && !this.seriesTime.equals("")) {
            DateFormat format = new SimpleDateFormat(Constants.DICOM_TIMEFORMAT);
            try {
                return format.parse(this.seriesTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public String getTimeSeriesString() {
        DateFormat format = new SimpleDateFormat(Constants.RPB_TIMEFORMAT);
        Date date = this.getTimeSeries();
        return date != null ? format.format(date) : null;
    }

    //endregion

    //region Images

    public List<DicomImage> getSeriesImages() {
        return this.seriesImages;
    }

    public void setSeriesImages(List<DicomImage> images) {
        this.seriesImages = images;
    }

    //endregion

    //endregion

    //region Overrides

    /**
     * equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof DicomSeries && this.hashCode() == other.hashCode());
    }

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this, this.seriesInstanceUID);
    }

    /**
     * Construct a readable string representation for this Study instance.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this) //
                .add("seriesInstanceUid", this.seriesInstanceUID) //
                .add("seriesDescription", this.seriesDescription) //
                .toString();
    }

    //endregion

}