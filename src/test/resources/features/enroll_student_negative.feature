Feature: Search and get the student details who are enrolled to school


  Scenario: Verify the error if the student is searched with non-existent Id
    Given user is entitled to access the school record
    When user search student record for id 10000
    Then existing record of students is fetched
      | status | message                                 |
      | 404    | no student record found with Id - 10000 |

  Scenario: Verify the error if the student is searched with non-existent class
    Given user is entitled to access the school record
    When user search student record for class "10A"
    Then existing record of students is fetched
      | status | message                                 |
      | 404    | no student record found for class - 10A |

  Scenario: enroll and verify the creation of new student
    Given user is entitled to access the school record
    When enroll a new student
      | firstName | lastName | className | nationality |
      | Brijendra | Singh    | 2A        | Indian      |
    Then record is created successfully
      | status |
      | 200    |
