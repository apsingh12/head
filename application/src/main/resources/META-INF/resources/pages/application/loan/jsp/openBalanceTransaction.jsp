<%--
Copyright (c) 2005-2011 Grameen Foundation USA
All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License.

See also http://www.apache.org/licenses/LICENSE-2.0.html for an
explanation of the license and how it is applied.
--%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/tags/mifos-html" prefix="mifos"%>
<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/tags/date" prefix="date"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="/sessionaccess" prefix="session"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<head>

<style>
tr.even {
  background-color: #ddd;
}
tr.odd {
  background-color: #eee;
}

div.scroll {
height: 80%;
width: 100%;
overflow: auto;
float:left;
padding: 0px;
position:relative;
}
table.pkgtable td
{
border-top: 1px dotted #6699CC;
}
table.pkgtable tr.pkgtableheaderrow
{
background-color: #BEC8D1;
text-align: center;
font-family: Verdana;
font-weight: bold;
font-size: 13px;
}

table.burlywoodborder {
border-right: solid 1px #EAEBF4;
border-left : solid 1px #EAEBF4;
border-top : solid 1px #EAEBF4;
border-bottom : solid 1px #EAEBF4;
}

</style>
</head>
<tiles:insert definition=".financialAccountingLayout">
<tiles:put name="body" type="string" >
<script src="pages/js/jquery/jquery-1.4.2.min.js"></script>
<script language="javascript">

function fnloadOffices(form) {

	form.method.value="loadOffices";
	form.action="openbalanceaction.do";
	form.submit();
}

function fnSubmit(form, buttonSubmit) {
	buttonSubmit.disabled=true;
	form.method.value="preview";
	form.action="openbalanceaction.do";
	form.submit();
}

function fnCancel(form) {
	form.method.value="cancel";
	form.action="openbalanceaction.do";
	form.submit();
}

function fnloadOpenBalance(form) {
	form.method.value="loadOpenBalance";
	form.action="openbalanceaction.do";
	form.submit();
}

</script>

<fmt:setLocale value='${sessionScope["org.apache.struts.action.LOCALE"]}'/>
<fmt:setBundle basename="org.mifos.config.localizedResources.SimpleAccountingUIResources"/>
<body>
<html-el:form action="/openbalanceaction.do">

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="350" align="left" valign="top" bgcolor="#FFFFFF">

							<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td align="center" class="heading">
											&nbsp;
										</td>
									</tr>
							</table>


					<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" class="burlywoodborder">
							<tr>
								<td align="left" valign="top" class="paddingleftCreates">

									<table width="93%" border="0" cellpadding="3" cellspacing="0">
										<tr>
											<td class="headingorange">
												<span id="generalLedger.heading" class="heading"> <mifos:mifoslabel name="simpleAccounting.head" /> - </span>
												<mifos:mifoslabel name="simpleAccounting.openingBalance" />

											</td>
										</tr>
										<tr>
											<td class="fontnormal">
												<br>
												<span class="mandatorytext"> <font color="#FF0000">*</font> </span>
												<mifos:mifoslabel name="simpleAccounting.mandatory" />
											</td>
										</tr>
									</table>

									<logic:messagesPresent>
										<font class="fontnormalRedBold"><span id="BulkEntry.error.message"> <html-el:errors bundle="simpleAccountingUIResources" /> </span> </font>
										<br>
									</logic:messagesPresent>
									<br>


				<table width="93%" border="0" cellpadding="3" cellspacing="0">
					<tr class="fontnormal">
							<td align="right">
			                   <mifos:mifoslabel name="simpleAccounting.financialYear" mandatory="yes" isColonRequired="Yes"/>
			                </td>
			                <td align="left">
			                    <html-el:text property="financialYear" readonly="true"/>
			                </td>


					</tr>
				<tr class="fontnormal">
						<td align="right"><mifos:mifoslabel name="simpleAccounting.officeHeirarchy" mandatory="yes" isColonRequired="Yes"/></td>
						<td align="left">
							<mifos:select property="officeHierarchy" onchange="fnloadOffices(this.form)">
							<html-el:option value="1"><mifos:mifoslabel name="simpleAccounting.headOffice" /></html-el:option>
							<html-el:option value="2"><mifos:mifoslabel name="simpleAccounting.regionalOffice" /></html-el:option>
							<html-el:option value="3"><mifos:mifoslabel name="simpleAccounting.divisionalOffice" /></html-el:option>
							<html-el:option value="4"><mifos:mifoslabel name="simpleAccounting.areaOffice" /></html-el:option>
							<html-el:option value="5"><mifos:mifoslabel name="simpleAccounting.branchOffice" /></html-el:option>
							<html-el:option value="6"><mifos:mifoslabel name="simpleAccounting.center" /></html-el:option>
							<html-el:option value="7"><mifos:mifoslabel name="simpleAccounting.group" /></html-el:option>
						</mifos:select>
					   </td>
					   <td align="right"><mifos:mifoslabel name="simpleAccounting.office" mandatory="yes" isColonRequired="Yes"/></td>
					   <td align="left">
						<mifos:select property="office">
						<c:forEach items="${sessionScope.OfficesOnHierarchy}" var="offices">
						<html-el:option value="${offices.globalOfficeNum}">${offices.displayName}</html-el:option>
						</c:forEach>
						</mifos:select>
					</td>
			 </tr>
				<tr class="fontnormal">
				    <td width="15%" align="right">
				         <mifos:mifoslabel name="simpleAccounting.coaName" mandatory="yes" isColonRequired="Yes"/>
				    </td>
					<td width="25%" align="left">
						<mifos:select property="coaName" onchange="fnloadOpenBalance(this.form)">
				            <c:forEach items="${sessionScope.TotalGlCodes}" var="glAccount">
				               <html-el:option value="${glAccount.glcode}">${glAccount.glname}</html-el:option>
				            </c:forEach>
						</mifos:select>
				    </td>
			        <td align="right">
			               <mifos:mifoslabel name="simpleAccounting.openBalance" mandatory="yes" isColonRequired="Yes"/>
			        </td>
			        <td align="left">
			            <html-el:text property="openBalance"/>
			            <mifos:select property="amountAction"  >
				          <html-el:option value="+"><mifos:mifoslabel name="simpleAccounting.debit" /></html-el:option>
				          <html-el:option value="-"><mifos:mifoslabel name="simpleAccounting.credit" /></html-el:option>
						</mifos:select>
			        </td>
					</tr>


				</table>


			<br>
			<table width="93%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="center" class="blueline">
												&nbsp;
											</td>
										</tr>
			</table>

	              <html-el:hidden property="financialYearId"/>
	              <html-el:hidden property="method" value="get" />
				  <html-el:hidden property="input" value="load" />
				<br>

					<table width="93%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td align="center">
												 <html-el:submit styleId="simpleaccounting.button.submit" styleClass="buttn"  onclick="fnSubmit(this.form, this)">
													<mifos:mifoslabel name="simpleAccounting.preview"/>
											    </html-el:submit>
												&nbsp;
												<html-el:button  styleId="bulkentry.button.cancel" property="cancel" styleClass="cancelbuttn"  onclick="fnCancel(this.form);">
													<mifos:mifoslabel name="simpleAccounting.cancel"/>
												</html-el:button>
											</td>
										</tr>
									</table>

<br>
								</td>
							</tr>
						</table>
						<br>
					</td>
				</tr>
			</table>
		</html-el:form>
		</body>
	</tiles:put>
</tiles:insert>
