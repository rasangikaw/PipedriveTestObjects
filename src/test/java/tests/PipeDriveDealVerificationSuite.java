package tests;

import base.BaseTestObject;
import controllers.ActivitiesController;
import controllers.ContactsController;
import controllers.DealsController;
import controllers.LeadsController;
import controllers.LoginController;
import controllers.ProductsController;
import enums.CurrencyEnum;

import java.util.logging.Level;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class PipeDriveDealVerificationSuite extends BaseTestObject {

    protected static JSONObject dataLogin, dealData, dataDealClosingDate, dataSchedule, dataMeetingScheduleDate, dataProducts, pipelines;
    protected static JSONObject dealData2, dataDealClosingDate2, dataSchedule2, dataMeetingScheduleDate2;
    protected static JSONObject dealData3, dataDealClosingDate3, dataSchedule3, dataMeetingScheduleDate3;

    private String username, password, pipelineName;
    private String personName, organizationName, value, pipelineId, phone, email, dealCloseMonth, dealCloseYear, dealCloseDate;
    private String activitySubject, scheduleDueMonth, scheduleDueTime, scheduleEndTime, scheduleNote, scheduleMonth, scheduleYear, scheduleDate;
    private String personName2, organizationName2, value2, pipelineId2, phone2, email2, dealCloseMonth2, dealCloseYear2, dealCloseDate2;
    private String personName3, organizationName3, value3, pipelineId3, phone3, email3, dealCloseMonth3, dealCloseYear3, dealCloseDate3;
    private String productName, productCode, productCategory, tax;

    private LoginController loginController;
    private DealsController dealsController;
    private ProductsController productsController;
    private ActivitiesController activitiesController;
    private ContactsController contactsController;
    private LeadsController leadsController;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            dataLogin = getJSONTestData("LoginUser");
            pipelines = getJSONTestData("PipelineData");
            dealData = getNestedJSONTestData("DealData", "deal1");
            dataProducts = getJSONTestData("ProductData");
            dataDealClosingDate = getNestedJSONTestData("DealData", "deal1", "DealCloseDate");
            dataSchedule = getNestedJSONTestData("DealData", "deal1", "ScheduleData");
            dataMeetingScheduleDate = getNestedJSONTestData("DealData", "deal1", "ScheduleData", "MeetingScheduleDate");
            username = dataLogin.get("username").toString();
            password = dataLogin.get("password").toString();
            personName = dealData.get("personName").toString();
            organizationName = dealData.get("organizationName").toString();
            value = dealData.get("value").toString();
            pipelineId = dealData.get("pipelineId").toString();
            pipelineName = pipelines.get("newPipelineName").toString();
            phone = dealData.get("phone").toString();
            email = dealData.get("email").toString();
            dealCloseMonth = dataDealClosingDate.get("dealCloseMonth").toString();
            dealCloseYear = dataDealClosingDate.get("dealCloseYear").toString();
            dealCloseDate = dataDealClosingDate.get("dealCloseDate").toString();
            activitySubject = dataSchedule.get("activitySubject").toString();
            scheduleDueMonth = dataMeetingScheduleDate.get("scheduleDueMonth").toString();
            scheduleDueTime = dataMeetingScheduleDate.get("scheduleDueTime").toString();
            scheduleEndTime = dataMeetingScheduleDate.get("scheduleEndTime").toString();
            scheduleNote = dataMeetingScheduleDate.get("scheduleNote").toString();
            scheduleMonth = dataMeetingScheduleDate.get("scheduleMonth").toString();
            scheduleYear = dataMeetingScheduleDate.get("scheduleYear").toString();
            scheduleDate = dataMeetingScheduleDate.get("scheduleDate").toString();

            dealData2 = getNestedJSONTestData("DealData", "deal2");
            dataDealClosingDate2 = getNestedJSONTestData("DealData", "deal2", "DealCloseDate");
            dataSchedule2 = getJSONTestData("ScheduleData");
            dataMeetingScheduleDate2 = getNestedJSONTestData("DealData", "deal2", "ScheduleData", "MeetingScheduleDate");
            personName2 = dealData2.get("personName").toString();
            organizationName2 = dealData2.get("organizationName").toString();
            value2 = dealData2.get("value").toString();
            pipelineId2 = dealData2.get("pipelineId").toString();
            phone2 = dealData2.get("phone").toString();
            email2 = dealData2.get("email").toString();
            dealCloseMonth2 = dataDealClosingDate.get("dealCloseMonth").toString();
            dealCloseYear2 = dataDealClosingDate.get("dealCloseYear").toString();
            dealCloseDate2 = dataDealClosingDate.get("dealCloseDate").toString();

            dealData3 = getNestedJSONTestData("DealData", "deal3");
            dataDealClosingDate3 = getNestedJSONTestData("DealData", "deal3", "DealCloseDate");
            dataSchedule3 = getJSONTestData("ScheduleData");
            dataMeetingScheduleDate3 = getNestedJSONTestData("DealData", "deal3", "ScheduleData", "MeetingScheduleDate");
            personName3 = dealData3.get("personName").toString();
            organizationName3 = dealData3.get("organizationName").toString();
            value3 = dealData3.get("value").toString();
            pipelineId3 = dealData3.get("pipelineId").toString();
            phone3 = dealData3.get("phone").toString();
            email3 = dealData3.get("email").toString();
            dealCloseMonth3 = dataDealClosingDate.get("dealCloseMonth").toString();
            dealCloseYear3 = dataDealClosingDate.get("dealCloseYear").toString();
            dealCloseDate3 = dataDealClosingDate.get("dealCloseDate").toString();

            productName = dataProducts.get("productName").toString();
            productCode = dataProducts.get("productCode").toString();
            productCategory = dataProducts.get("productCategory").toString();
            tax = dataProducts.get("tax").toString();

            loginController = new LoginController(driver);
            dealsController = new DealsController(driver);
            productsController = new ProductsController(driver);
            activitiesController = new ActivitiesController(driver);
            contactsController = new ContactsController(driver);
            leadsController = new LeadsController(driver);

            driver.get(pipedriveUrl);
            loginController.getLoginPageFunctions().loginToPipedrive(username, password);
        } catch (Exception e) {
            throw e;
        }
    }

    @Test(priority = 1)
    public void cleanUpData() throws Exception {
        try {
            logger.log(Level.INFO, "Start | ############# cleanUpData()");
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Deals");
            dealsController.getDealsFunctions().switchToPipeline(pipelineName);
            dealsController.getDealsFunctions().deleteAllDealsByDragAndDrop();
            Assert.assertFalse(dealsController.getDealsFunctions().getDealsPage().isDealsCardsDisplayed());
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Contacts");
            contactsController.getContactsFunctions().deleteAllOrganizations();
            Assert.assertTrue(contactsController.getContactsFunctions().getContactsPageMethods().isOrganizationsAreEmpty());
            contactsController.getContactsFunctions().deleteAllContacts();
            Assert.assertTrue(contactsController.getContactsFunctions().getContactsPageMethods().isContactsAreEmpty());
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Activities");
            activitiesController.getActivitiesFunctions().deleteAllSubjects();
            Assert.assertTrue(activitiesController.getActivitiesFunctions().getActivitiesPageMethods().isActivitiesAreEmpty());
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Products");
            productsController.getProductFunctions().deleteAllProducts();
            Assert.assertTrue(productsController.getProductFunctions().getProductsPage().isProductsAreEmpty());
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Leads");
            leadsController.getLeadsFunctions().deleteAllLeads();
            Assert.assertTrue(leadsController.getLeadsFunctions().getLeadsPage().isLeadsAreEmpty());
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Deals");
            dealsController.getDealsFunctions().deleteExistingPipeline(pipelineName);
            dealsController.getDealsFunctions().addNewPipeline(pipelineName);
            dealsController.getDealsFunctions().switchToPipeline(pipelineName);

        } catch (Exception e) {
            logger.log(Level.INFO, "Fail | ############# cleanUpData()");
            throw e;
        }
    }

    @Test(priority = 2)
    public void createAndVerifyDealDetails() throws Exception {
        try {
            logger.log(Level.INFO, "Start | Test| ############# createAndVerifyDealDetails()");
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Deals");
            dealsController.addDealFunction(personName, organizationName, value, CurrencyEnum.CAD, pipelineId, phone, email, dealCloseMonth, dealCloseYear, dealCloseDate);
            Assert.assertTrue(dealsController.getDealsFunctions().isDealConfirmationMessageDisplayedCorrectly(organizationName + " deal"));
            dealsController.getDealsFunctions().navigateToSheduleAnActivity(organizationName + " deal");
            dealsController.addASchedule(activitySubject, scheduleDueMonth, scheduleDueTime, scheduleEndTime, scheduleNote, scheduleMonth, scheduleYear, scheduleDate);
            dealsController.getDealsFunctions().isDealCardHasCorrectDetails(organizationName + " deal", organizationName, CurrencyEnum.CAD + value);
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Activities");
            Assert.assertTrue(activitiesController.getActivitiesFunctions().isActivityDisplayedInActivitiesPage(activitySubject, scheduleMonth, scheduleDate, scheduleDueTime));
        } catch (Exception e) {
            logger.log(Level.INFO, "Fail | Test | ############# createAndVerifyDealDetails()");
            throw e;
        }
    }

    @Test(priority = 3)
    public void verifyDealCreationWithExistingContact() throws Exception {
        try {
            logger.log(Level.INFO, "Start | Test | ############# verifyDealCreationWithExistingContact()");
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Deals");
            Assert.assertTrue(dealsController.verifyDuplicateContactSugestions(personName, organizationName, phone, email));
        } catch (Exception e) {
            logger.log(Level.INFO, "Fail | Test | ############# verifyDealCreationWithExistingContact()");
            throw e;
        }
    }

    @Test(priority = 4)
    public void verifyProductCreationAndAttachingToDeal() throws Exception {
        try {
            logger.log(Level.INFO, "Start | Test | ############# verifyProductCreationAndAttachingToDeal()");
            productsController.getProductFunctions().getAddProductsPage().clickOnLeftNavigationItem("Products");
            productsController.createProductAndVerify(productName, productCode, productCategory, CurrencyEnum.CAD, tax);
            productsController.getProductFunctions().getProductsPage().clickOnLeftNavigationItem("Deals");
            Assert.assertTrue(dealsController.getDealsFunctions().addProductToDeal(organizationName + " deal", productName, productCode, CurrencyEnum.CAD, "1"));
        } catch (Exception e) {
            logger.log(Level.INFO, "Fail | Test | ############# verifyProductCreationAndAttachingToDeal()");
            throw e;
        }
    }

    @Test(priority = 5)
    public void verifyDealLostWonAndConvert() throws Exception {
        try {
            logger.log(Level.INFO, "Start | Test | ############# verifyDealLostWonAndConvert()");
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Deals");
            dealsController.addDealFunction(personName2, organizationName2, value2, CurrencyEnum.CAD, pipelineId2, phone2, email2, dealCloseMonth2, dealCloseYear2, dealCloseDate2);
            dealsController.getDealsFunctions().getAddDealsPage().waitForAddDealPageToDissapear();
            dealsController.addDealFunction(personName3, organizationName3, value3, CurrencyEnum.CAD, pipelineId3, phone3, email3, dealCloseMonth3, dealCloseYear3, dealCloseDate3);
            dealsController.getDealsFunctions().getDealsPage().dragAndDropDealToStage(organizationName2 + " deal" , "Contact Made");
            Assert.assertTrue(dealsController.getDealsFunctions().getDealsPage().isDealDisplayedUnderStage(organizationName2 + " deal" , "Contact Made"));
            Assert.assertTrue(dealsController.getDealsFunctions().dragDealToLostAndCompletePopup(organizationName + " deal", "Sample Reason"));
            Assert.assertTrue(dealsController.getDealsFunctions().dragDealToWonAndVerifyFilter(organizationName2 + " deal"));
            dealsController.getDealsFunctions().convertDealToLead(organizationName3 + " deal");
            dealsController.getDealsFunctions().getDealsPage().clickOnLeftNavigationItem("Leads");
            Assert.assertTrue(leadsController.getLeadsFunctions().isLeadDisplayed(organizationName3 + " deal"));
        } catch (Exception e) {
            logger.log(Level.INFO, "Fail | Test | ############# verifyDealLostWonAndConvert()");
            throw e;
        }
    }
}
