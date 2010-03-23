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

package org.mifos.application.servicefacade;

import java.util.List;

import org.mifos.application.master.business.CustomFieldView;
import org.mifos.application.master.business.MasterDataEntity;
import org.mifos.application.master.business.ValueListElement;
import org.mifos.customers.personnel.business.PersonnelView;
import org.mifos.customers.util.helpers.SavingsDetailDto;

public class ClientFormCreationDto {

    private final List<ValueListElement> salutations;
    private final List<ValueListElement> genders;
    private final List<ValueListElement> maritalStatuses;
    private final List<ValueListElement> citizenship;
    private final List<ValueListElement> ethinicity;
    private final List<ValueListElement> educationLevels;
    private final List<ValueListElement> businessActivity;
    private final List<ValueListElement> poverty;
    private final List<ValueListElement> handicapped;
    private final List<MasterDataEntity> spouseFather;
    private final List<CustomFieldView> customFieldViews;
    private final Short officeId;
    private final Short formedByPersonnelId;
    private final List<PersonnelView> personnelList;
    private final CustomerApplicableFeesDto applicableFees;
    private final ClientRulesDto clientRules;
    private final List<SavingsDetailDto> savingsOfferings;
    private final List<PersonnelView> formedByPersonnelList;

    public ClientFormCreationDto(List<ValueListElement> salutations, List<ValueListElement> genders,
            List<ValueListElement> maritalStatuses, List<ValueListElement> citizenship,
            List<ValueListElement> ethinicity, List<ValueListElement> educationLevels,
            List<ValueListElement> businessActivity, List<ValueListElement> poverty,
            List<ValueListElement> handicapped, List<MasterDataEntity> spouseFather, List<CustomFieldView> customFieldViews, ClientRulesDto clientRules, Short officeId, Short formedByPersonnelId, List<PersonnelView> personnelList, CustomerApplicableFeesDto applicableFees, List<PersonnelView> formedByPersonnelList, List<SavingsDetailDto> savingsOfferings) {
        this.salutations = salutations;
        this.genders = genders;
        this.maritalStatuses = maritalStatuses;
        this.citizenship = citizenship;
        this.ethinicity = ethinicity;
        this.educationLevels = educationLevels;
        this.businessActivity = businessActivity;
        this.poverty = poverty;
        this.handicapped = handicapped;
        this.spouseFather = spouseFather;
        this.customFieldViews = customFieldViews;
        this.clientRules = clientRules;
        this.officeId = officeId;
        this.formedByPersonnelId = formedByPersonnelId;
        this.personnelList = personnelList;
        this.applicableFees = applicableFees;
        this.formedByPersonnelList = formedByPersonnelList;
        this.savingsOfferings = savingsOfferings;
    }

    public List<ValueListElement> getSalutations() {
        return this.salutations;
    }

    public List<ValueListElement> getGenders() {
        return this.genders;
    }

    public List<ValueListElement> getMaritalStatuses() {
        return this.maritalStatuses;
    }

    public List<ValueListElement> getCitizenship() {
        return this.citizenship;
    }

    public List<ValueListElement> getEthinicity() {
        return this.ethinicity;
    }

    public List<ValueListElement> getEducationLevels() {
        return this.educationLevels;
    }

    public List<ValueListElement> getBusinessActivity() {
        return this.businessActivity;
    }

    public List<ValueListElement> getPoverty() {
        return this.poverty;
    }

    public List<ValueListElement> getHandicapped() {
        return this.handicapped;
    }

    public List<MasterDataEntity> getSpouseFather() {
        return this.spouseFather;
    }

    public List<CustomFieldView> getCustomFieldViews() {
        return this.customFieldViews;
    }

    public Short getOfficeId() {
        return this.officeId;
    }

    public Short getFormedByPersonnelId() {
        return this.formedByPersonnelId;
    }

    public List<PersonnelView> getPersonnelList() {
        return this.personnelList;
    }

    public CustomerApplicableFeesDto getApplicableFees() {
        return this.applicableFees;
    }

    public ClientRulesDto getClientRules() {
        return this.clientRules;
    }

    public List<SavingsDetailDto> getSavingsOfferings() {
        return this.savingsOfferings;
    }

    public List<PersonnelView> getFormedByPersonnelList() {
        return this.formedByPersonnelList;
    }
}