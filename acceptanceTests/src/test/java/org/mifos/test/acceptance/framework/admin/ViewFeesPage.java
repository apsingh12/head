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

package org.mifos.test.acceptance.framework.admin;

import org.mifos.test.acceptance.framework.MifosPage;
import org.testng.Assert;

import com.thoughtworks.selenium.Selenium;

public class ViewFeesPage extends MifosPage {
    public ViewFeesPage(Selenium selenium) {
        super(selenium);
    }

    public ViewFeesPage verifyPage() {
        verifyPage("viewFees");
        return this;
    }

    public void verifyProductFees(String[] expectedData) {

        for (int i = 0; i < expectedData.length; i++) {
            String expectedCellData = expectedData[i];
            String actualCellData = selenium.getTable("productFeeTable."+i+".1");
            Assert.assertTrue(actualCellData.contains(expectedCellData), "The product fee cell did not contain the expected data!");
        }
    }

    public void verifyClientFees(String[] expectedData) {

        for (int i = 0; i < expectedData.length; i++) {
            String expectedCellData = expectedData[i];
            String actualCellData = selenium.getTable("clientFeeTable."+i+".1");
            Assert.assertTrue(actualCellData.contains(expectedCellData), "The client cell did not contain the expected data!");
        }
    }

}
