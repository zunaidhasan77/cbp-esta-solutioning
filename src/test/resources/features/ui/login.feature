@regression
Feature: Access and Authentication on the ESTA Application Portal

Background: User is on ESTA Create Application page
    Given the user navigate to the ESTA application
    And the user navigate to the "CREATE NEW APPLICATION" page successfully as 
      | Individual Application |
  
@smoke1
  Scenario: Validate user login to the ESTA system with a valid passport
    Given Test data is loaded from Excel row <row>
    When the user uploads the passport "good_passport.png" using "From gallery"
    And passport has been uploaded successfully
    And the user enters Enter Applicant Information Details
    And the User enters Personal Information Details
    And the User enters Travel Information Details
    And the User enters Eligibility Questions Details
    And the User enters Review Application Details
    And the User enters Pay Details
  Examples:
    | row |
    | 0   |


@smoke
  Scenario: Validate un-successful passport upload in the ESTA system
    When the user uploads the passport "bad_passport.jpg" using "From gallery"
    Then system displays the error message as "We were unable to process the image"
    
@manual
  Scenario: This is a manual test scenario which couldn't be automated
    Then the user is performing some actions
    
@pending
  Scenario: This is in-progress scenario
    Then the user is performing some actions

@ignore
  Scenario: This is a ignored test scenario for some reason 
    Then the user is performing some actions
    
@defect-2342
  Scenario: Validate user can complete this func 
    Then the user is performing some actions