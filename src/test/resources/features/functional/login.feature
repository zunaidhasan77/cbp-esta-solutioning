@regression
Feature: Access and Authentication on the ESTA Application Portal

Background: User is on ESTA Create Application page
    Given the user navigate to the ESTA application
    And the user navigate to the "CREATE NEW APPLICATION" page successfully as 
      | Individual Application |
    # And the user navigate to the "CREATE NEW APPLICATION" page successfully with data from excel for "TC-01" 
    #   | Individual_Application |
    #   | GROUP_OF_APPLICATIONS  |
  
@smoke
  Scenario Outline: Validate user login to the ESTA system with a valid passport
    When the user uploads the passport "passport3.png" using "From gallery"
    And passport has been uploaded successfully
    And the user enters below Passport Information from excel for "<TEST_ID>":
    | Family_Name            |
    | First_Name             |
    # | Passport_Number        |
    # | Issuing_Country        |
    | Issuance_Date          |
    # | Expiration_Date        |
    # | Country_Of_Citizenship |
    # | Sex                    |
    # | Date_Of_Birth          |
    | City_Of_Birth          |
    | Country_Of_Birth       |
    And the user enters "OTHER_CITIZENSHIP_NATIONALITY_Q1" from excel for "<TEST_ID>"
    And the user enters "PREVIOUS_CITIZENSHIP_NATIONALITY_Q2" from excel for "<TEST_ID>"
    And the user enters "EMAIL_ADDRESS" from excel for "<TEST_ID>"
    And the user enters "CONFIRM_EMAIL_ADDRESS" from excel for "<TEST_ID>"
    Then the user click on "SAVE AND EXIT" button in the applicantinfo page
    Examples:
    | TEST_ID |
    | TC-01   |
    
@smoke
  Scenario: Validate un-successful passport upload in the ESTA system
    When the user uploads the passport "bad_passport.jpg" using "From gallery"
    Then system displays the error message as "We were unable to process the image"
    
@manual
  Scenario: This is a sample manual test scenario
    Then the user is performing some actions
    
@pending
  Scenario: This is sample in-progress scenario
    Then the user is performing some actions

@ignore
  Scenario: This is a sample ignored test scenario 
    Then the user is performing some actions
    
@defect-2342 @skip
  Scenario: This is a sample scenario with a defect
    Then the user is performing some actions