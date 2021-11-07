Feature: Search and get the student details who are enrolled to school

  Scenario: Get the details of all the students enrolled to school
    Given user is entitled to access the school record
    When user search for all student records
    Then existing record of students is fetched
      | status |
      | 200    |

  Scenario: Get the details of all the students enrolled to school
    Given user is entitled to access the school record
    When user search student record for id 1000
    Then existing record of students is fetched
      | status | firstName | lastName  | className | nationality |
      | 200    | Sachin    | Tendulkar | 1 A       | Indian      |

  Scenario: enroll and verify the creation of new student
    Given user is entitled to access the school record
    When enroll a new student
      | firstName | lastName | className |
      | Brijendra | Singh    | 2 A       |
    Then record is created successfully
      | status |
      | 200    |
    When user search student record for class "2 A"
    Then existing record of students is fetched
      | status | firstName | lastName | className |
      | 200    | Brijendra | Singh    | 2 A       |


