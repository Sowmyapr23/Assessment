Feature: Register and Purchase on AutomationTestStore.com


  @ValidateUserDetailsAfterRegistration
  Scenario Outline: Register new user and validate the user details
    Given launch application site_url in browser
    When click on "login_Register_link"
    When click on "continueBtn"
    Then verify "reg_personalDetailsText" text is present on "reg_personalDetailsHeader"
    Then enter "<firstname>" in "reg_firstname"
    Then enter "<lastname>" in "reg_lastname"
    Then enter "<email>" in "reg_email"
    Then enter "<address>" in "reg_address"
    Then enter "<city>" in "reg_city"
    Then select "<country>" in "reg_country"
    Then select "<region>" in "reg_region"
    Then enter "<zipcode>" in "reg_zipcode"
    Then enter "<loginName>" in "reg_login"
    Then enter "<password>" in "reg_password"
    Then enter "<password>" in "reg_confirmPassword"
    Then click on "reg_iAgreeCheckbox"
    Then click on "continueBtn"
    Then verify "accountCreatedText" text is present on "accountCreatedHeader"
    Then validate "<firstname>" is displayed on "welcomeUser" page
    Then click on "accountBtn"
    Then click on "logoutBtn"
    And close application


    Examples:
      | firstname     | lastname    | email                       | address | city   | region | zipcode | country      | loginName         | password     |
      | TestStore     | Automation  | testStoreAuto3@yopmail.com  |Alaska   | Alaska | Alaska | 73301   | United States| testStoreAuto3    | Password@123 |


  @LoginAndAddProductToCart
  Scenario Outline: Login to the application and Add Product to Cart
    Given launch application site_url in browser
    When click on "login_Register_link"
    Then enter "<loginName>" in "loginTextbox"
    Then enter "<password>" in "passwordTextBox"
    Then click on "loginBtn"
    Then click on "<productMainCategory>"
    Then verify "productHeaderText" text is present on "pageHeader"
    Then click on "<productSubCategory>"
    Then click on "<productName>"
    Then enter "<productQty>" in "productQty"
    Then click on "addToCartBtn"
    Then click on "accountBtn"
    Then click on "logoutBtn"
    And close application

    Examples:
        | loginName       | password     |productMainCategory  |productSubCategory       |productName |productQty|
        | testStoreAuto3  | Password@123 |productMainCategoryHC|productSubCategoryShampoo|bvl         |2         |


  @CheckOutAndValidateProductInPaymentsPage
  Scenario Outline: Proceed to Checkout
    Given launch application site_url in browser
    When click on "login_Register_link"
    Then enter "<loginName>" in "loginTextbox"
    Then enter "<password>" in "passwordTextBox"
    Then click on "loginBtn"
    Then click on "cartBtn"
    Then verify "shoppingCartHeaderText" text is present on "pageHeader"
    Then click on "checktOutBtn"
    Then verify "checkoutHeaderText" text is present on "pageHeader"
    Then verify "<productName>" text is present on "checkedOutProductName"
    Then verify "<productQty>" text is present on "checkedOutProductQty"
    Then click on "accountBtn"
    Then click on "logoutBtn"
    And close application

    Examples:
      | loginName       | password     |productName                      |productQty|
      | testStoreAuto3  | Password@123 |eau parfumee au the vert shampoo |2         |

