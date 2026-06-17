@regression
Feature: Lighthouse Audit for ESTA 

@audit
  Scenario: Run Lighthouse audit against ESTA Landing page
    Then run lighthouse audit for "https://esta.cbp.dhs.gov/"
    