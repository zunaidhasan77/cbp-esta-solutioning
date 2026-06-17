@508 @regression
Feature: Accessibility test for ESTA application

Background: User is on ESTA Create Application page
    Given the user navigate to the ESTA application

@pending
  Scenario: Verify esta meets accessibility AA WCAG standards
    When axe has been injected
    And an axe scan is executed for AA WCAG level
    Then verify there is no violations should occur