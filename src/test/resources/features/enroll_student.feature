Feature: Positive Scenarios - Search and get the student details who are enrolled to school

  @regression
  @positive
  @search_all
  Scenario: Get the details of all the students enrolled to school
    Given user is entitled to access the school record
    When user search for all student records
    Then existing record of students is fetched
      | status |
      | 200    |

  @regression
  @positive
  @search_by_id
  Scenario: Get the details of the student based on the Id
    Given user is entitled to access the school record
    When user search student record for id 1000
    Then existing record of students is fetched
      | status | firstName | lastName  | className | nationality |
      | 200    | Sachin    | Tendulkar | 1A        | Indian      |

  @regression
  @positive
  @enroll_student
  @search_by_class
  Scenario: enroll and verify the creation of new student
    Given user is entitled to access the school record
    When enroll a new student
      | firstName | lastName | className | nationality |
      | Brijendra | Singh    | 2A        | Indian      |
    Then record is executed successfully
      | status |
      | 204    |
    When user search student record for class "2A"
    Then existing record of students is fetched
      | status | firstName | lastName | className |
      | 200    | Brijendra | Singh    | 2A        |


  @regression
    @positive
    @enroll_student
  Scenario Outline: enroll and verify the creation of new student
    Given user is entitled to access the school record
    When enroll a new student
      | firstName   | lastName   | className   | nationality   |
      | <firstName> | <lastName> | <className> | <nationality> |
    Then record is executed successfully
      | status |
      | 204    |
    When user search student record for class "<className>"
    Then existing record of students is fetched
      | status | firstName   | lastName   | className   |
      | 200    | <firstName> | <lastName> | <className> |
    Examples:
      | firstName | lastName | className | nationality   |
      | Jack      | Kallis   | 4A        | South African |
      | Micheal   | Holding  | 4B        | West Indian   |
      | Gary      | Kirsten  | 5A        | South African |
      | James     | Anderson | 6B        | British       |


  @regression
  @positive
  @update_student
  @search_by_id
  Scenario: update and verify the record of an existing student
    Given user is entitled to access the school record
    When update an existing student record
      | id   | firstName | lastName | className | nationality |
      | 1001 | Stephan   | Flemming | 6C        | New Zealand |
    Then record is executed successfully
      | status |
      | 204    |
    When user search student record for id 1001
    Then existing record of students is fetched
      | status | firstName | lastName | className | nationality |
      | 200    | Stephan   | Flemming | 6C        | New Zealand |


  @regression
  @positive
  @update_student
  @search_by_id
  Scenario: update and verify the partial record details of an existing student
    Given user is entitled to access the school record
    When update an existing student record
      | id   | firstName | lastName |
      | 1002 | David     | Warner   |
    Then record is executed successfully
      | status |
      | 204    |
    When user search student record for id 1002
    Then existing record of students is fetched
      | status | firstName | lastName | className | nationality |
      | 200    | David     | Warner   | 1A        | Australian  |


#  @test
#  @regression
#  @positive
#  @delete_student
#  Scenario: delete and verify the record details of an existing student
#    Given user is entitled to access the school record
#    When delete an existing student record
#      | id   |
#      | 1002 |
#    Then record is executed successfully
#      | status |
#      | 204    |
#    When user search student record for id 1002
#    Then existing record of students is fetched
#      | status | message                                |
#      | 404    | no student record found with Id : 1002 |

