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

package org.mifos.accounts.savings.struts.action;

import java.util.Date;
import java.util.Locale;

import junit.framework.Assert;

import org.mifos.accounts.business.AccountActionDateEntity;
import org.mifos.accounts.productdefinition.business.SavingsOfferingBO;
import org.mifos.accounts.productdefinition.util.helpers.RecommendedAmountUnit;
import org.mifos.accounts.savings.business.SavingsBO;
import org.mifos.accounts.savings.persistence.SavingsPersistence;
import org.mifos.accounts.savings.util.helpers.SavingsConstants;
import org.mifos.accounts.util.helpers.AccountConstants;
import org.mifos.accounts.util.helpers.AccountState;
import org.mifos.accounts.util.helpers.PaymentData;
import org.mifos.accounts.util.helpers.SavingsPaymentData;
import org.mifos.application.meeting.business.MeetingBO;
import org.mifos.customers.business.CustomerBO;
import org.mifos.customers.util.helpers.CustomerStatus;
import org.mifos.framework.MifosMockStrutsTestCase;
import org.mifos.framework.hibernate.helper.StaticHibernateUtil;
import org.mifos.framework.util.helpers.Constants;
import org.mifos.framework.util.helpers.Money;
import org.mifos.framework.util.helpers.SessionUtils;
import org.mifos.framework.util.helpers.TestObjectFactory;
import org.mifos.security.util.UserContext;

public class SavingsApplyAdjustmentActionStrutsTest extends MifosMockStrutsTestCase {

    public SavingsApplyAdjustmentActionStrutsTest() throws Exception {
        super();
    }

    private UserContext userContext;
    private CustomerBO group;
    private CustomerBO center;
    private SavingsBO savings;
    private SavingsOfferingBO savingsOffering;
    private String flowKey;

    @Override
    protected void setStrutsConfig() {
        super.setStrutsConfig();
        setConfigFile("/WEB-INF/struts-config.xml,/WEB-INF/accounts-struts-config.xml");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        userContext = TestObjectFactory.getContext();
        userContext.setPreferredLocale(new Locale("en", "GB"));
        addRequestParameter("recordLoanOfficerId", "1");
        addRequestParameter("recordOfficeId", "1");
        request.getSession().setAttribute(Constants.USER_CONTEXT_KEY, userContext);
        request.getSession(false).setAttribute("ActivityContext", TestObjectFactory.getActivityContext());
        flowKey = createFlow(request, SavingsApplyAdjustmentAction.class);
        request.setAttribute(Constants.CURRENTFLOWKEY, flowKey);
        addRequestParameter(Constants.CURRENTFLOWKEY, flowKey);
    }

    @Override
    public void tearDown() throws Exception {
        savings = null;
        savings = null;
        group = null;
        group = null;
        center = null;
        center = null;
        super.tearDown();
    }

    public void testSuccessfullLoad_WithoutValidLastPayment() throws Exception {
        createInitialObjects();
        savingsOffering = createSavingsOffering();
        savings = createSavingsAccount("000X00000000017", savingsOffering, group, AccountState.SAVINGS_ACTIVE);
        StaticHibernateUtil.flushSession();
        SessionUtils.setAttribute(Constants.BUSINESS_KEY, savings, request);
        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "load");
        actionPerform();
        verifyForward("load_success");
        verifyNoActionMessages();
        verifyNoActionErrors();
        savings = (SavingsBO) SessionUtils.getAttribute(Constants.BUSINESS_KEY, request);
        Assert.assertNull(savings.findMostRecentPaymentByPaymentDate());
        Assert.assertNull(SessionUtils.getAttribute(SavingsConstants.ACCOUNT_ACTION, request));
        Assert.assertNull(SessionUtils.getAttribute(SavingsConstants.CLIENT_NAME, request));
       Assert.assertEquals(Short.valueOf("0"), SessionUtils.getAttribute(SavingsConstants.IS_LAST_PAYMENT_VALID, request));
    }

    public void testSuccessfullPreviewSuccess() throws Exception {
        createInitialObjects();
        savingsOffering = createSavingsOffering();
        savings = createSavingsAccountWithPayment("000X00000000017", savingsOffering, group,
                AccountState.SAVINGS_ACTIVE);

        SessionUtils.setAttribute(Constants.BUSINESS_KEY, savings, request);
        StaticHibernateUtil.flushSession();
        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "load");
        actionPerform();
        verifyForward("load_success");

        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "preview");
        addRequestParameter("note", "adjustmentComment");
        addRequestParameter("lastPaymentAmount", "100");
        actionPerform();
        verifyForward("preview_success");
        verifyNoActionMessages();
        verifyNoActionErrors();
        StaticHibernateUtil.flushSession();
        // savings = new SavingsPersistence().findById(savings.getAccountId());
    }

    public void testSuccessfullPreviewFailure_NoLastPayment() throws Exception {
        createInitialObjects();
        savingsOffering = createSavingsOffering();
        savings = createSavingsAccount("000X00000000017", savingsOffering, group, AccountState.SAVINGS_ACTIVE);
        StaticHibernateUtil.flushSession();
        SessionUtils.setAttribute(Constants.BUSINESS_KEY, savings, request);
        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "preview");
        actionPerform();
        verifyActionErrors(new String[] { SavingsConstants.INVALID_LAST_PAYMENT });
    }

    public void testSuccessfullPreviewFailure_LongNotes() throws Exception {
        createInitialObjects();
        savingsOffering = createSavingsOffering();
        savings = createSavingsAccountWithPayment("000X00000000017", savingsOffering, group,
                AccountState.SAVINGS_ACTIVE);

        SessionUtils.setAttribute(Constants.BUSINESS_KEY, savings, request);
        StaticHibernateUtil.flushSession();
        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "load");
        actionPerform();
        verifyForward("load_success");

        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "preview");
        addRequestParameter("lastPaymentAmount", "");
        addRequestParameter(
                "note",
                "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
                        + "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
                        + "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
                        + "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
                        + "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
                        + "abcdefghijklmnopqrstuvwxyz");
        actionPerform();
       Assert.assertEquals(2, getErrorSize());
       Assert.assertEquals(1, getErrorSize(AccountConstants.MAX_NOTE_LENGTH));
       Assert.assertEquals(1, getErrorSize(SavingsConstants.INVALID_ADJUSTMENT_AMOUNT));
        StaticHibernateUtil.flushSession();
        // savings = new SavingsPersistence().findById(savings.getAccountId());
    }

    public void testSuccessfullPrevious() {
        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "previous");
        actionPerform();
        verifyForward("previous_success");
    }

    public void testSuccessfullCancel() throws Exception {
        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "cancel");
        actionPerform();
        verifyForward("account_detail_page");
        Assert.assertNull(request.getAttribute(Constants.CURRENTFLOWKEY));
    }

    public void testSuccessfullPreviewSuccessWithZeroAmountAdjustment() throws Exception {
        //Introduced for [MIFOS-2958]
        createInitialObjects();
        savingsOffering = createSavingsOffering();
        savings = createSavingsAccountWithPayment("000X00000000017", savingsOffering, group,
                AccountState.SAVINGS_ACTIVE);

        SessionUtils.setAttribute(Constants.BUSINESS_KEY, savings, request);
        StaticHibernateUtil.flushSession();
        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "load");
        actionPerform();
        verifyForward("load_success");

        setRequestPathInfo("/savingsApplyAdjustmentAction.do");
        addRequestParameter("method", "preview");
        addRequestParameter("note", "adjustmentComment");
        addRequestParameter("lastPaymentAmount", "0.0");
        actionPerform();
        verifyForward("preview_success");
        verifyNoActionMessages();
        verifyNoActionErrors();
        StaticHibernateUtil.flushSession();
        // savings = new SavingsPersistence().findById(savings.getAccountId());
    }

    private void createInitialObjects() {
        MeetingBO meeting = TestObjectFactory.createMeeting(TestObjectFactory.getTypicalMeeting());
        center = TestObjectFactory.createWeeklyFeeCenter("Center_Active_test", meeting);
        group = TestObjectFactory.createWeeklyFeeGroupUnderCenter("Group_Active_test", CustomerStatus.GROUP_ACTIVE, center);
    }

    private SavingsOfferingBO createSavingsOffering() {
        Date currentDate = new Date(System.currentTimeMillis());
        return TestObjectFactory.createSavingsProduct("SavingPrd1", "S", currentDate, RecommendedAmountUnit.COMPLETE_GROUP);
    }

    private SavingsBO createSavingsAccountWithPayment(String globalAccountNum, SavingsOfferingBO savingsOffering,
            CustomerBO group, AccountState state) throws Exception {
        SavingsBO savings = TestObjectFactory.createSavingsAccount(globalAccountNum, group, state, new Date(),
                savingsOffering, userContext);
        PaymentData paymentData = PaymentData.createPaymentData(new Money(getCurrency(), "100"), savings.getPersonnel(), Short
                .valueOf("1"), new Date(System.currentTimeMillis()));
        paymentData.setCustomer(group);
        paymentData.setReceiptDate(new Date(System.currentTimeMillis()));
        paymentData.setReceiptNum("34244");
        AccountActionDateEntity accountActionDate = null;
        paymentData.addAccountPaymentData(new SavingsPaymentData(accountActionDate));
        savings.applyPaymentWithPersist(paymentData);
        StaticHibernateUtil.flushSession();
        return new SavingsPersistence().findById(savings.getAccountId());
    }

    private SavingsBO createSavingsAccount(String globalAccountNum, SavingsOfferingBO savingsOffering,
            CustomerBO group, AccountState state) throws Exception {
        return TestObjectFactory.createSavingsAccount(globalAccountNum, group, state, new Date(), savingsOffering,
                userContext);
    }

}
