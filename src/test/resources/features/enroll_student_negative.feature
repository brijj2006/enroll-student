Feature: Negative Scenarios - Search and get the student details who are enrolled to school

  @regression
  @negative
  @error_search_by_id
  Scenario: Verify the error if the student is searched with non-existent Id
    Given user is entitled to access the school record
    When user search student record for id 10000
    Then existing record of students is fetched
      | status | message                                 |
      | 404    | no student record found with Id : 10000 |

  @regression
  @negative
  @error_search_by_class
  Scenario: Verify the error if the student is searched with non-existent class
    Given user is entitled to access the school record
    When user search student record for class "10A"
    Then existing record of students is fetched
      | status | message                                 |
      | 404    | no student record found for class : 10A |

  @regression
  @negative
  @update_student
  Scenario: Verify the error if the student is updated for the non-existing record
    Given user is entitled to access the school record
    When update an existing student record
      | id   | firstName | lastName | className | nationality |
      | 9999 | Glen      | MacGrath | 10B       | Australian  |
    Then record is executed successfully
      | status | message                               |
      | 404    | student does not exist with Id : 9999 |

#  @regression
#  @negative
#  @delete_student
#  Scenario: delete and verify the same record cannot be deleted again
#    Given user is entitled to access the school record
#    When delete an existing student record
#      | id   |
#      | 1003 |
#    Then record is executed successfully
#      | status |
#      | 204    |
#    When delete an existing student record
#      | id   |
#      | 1003 |
#    Then record is executed successfully
#      | status | message                               |
#      | 404    | student does not exist with Id : 1003 |