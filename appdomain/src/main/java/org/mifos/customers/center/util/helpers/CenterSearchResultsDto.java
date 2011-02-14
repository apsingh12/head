/*
 * Copyright (c) 2005-2010 Grameen Foundation USA
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 *
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an
 * explanation of the license and how it is applied.
 */

package org.mifos.customers.center.util.helpers;

import org.mifos.customers.api.DataTransferObject;

public class CenterSearchResultsDto implements DataTransferObject {

    private short parentOfficeId;

    private String parentOfficeName;

    private String centerSystemId;

    private String centerName;

    public String getCenterSystemId() {
        return centerSystemId;
    }

    public void setCenterSystemId(String centerId) {
        this.centerSystemId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public short getParentOfficeId() {
        return parentOfficeId;
    }

    public void setParentOfficeId(short parentOfficeId) {
        this.parentOfficeId = parentOfficeId;
    }

    public String getParentOfficeName() {
        return parentOfficeName;
    }

    public void setParentOfficeName(String parentOfficeName) {
        this.parentOfficeName = parentOfficeName;
    }
}
