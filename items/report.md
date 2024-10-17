# G0 - 20 Report

The following is a report template to help your team successfully provide all the details necessary for your report in a structured and organised manner. Please give a straightforward and concise report that best demonstrates your project. Note that a good report will give a better impression of your project to the reviewers.

Note that you should have removed ALL TEMPLATE/INSTRUCTION textes in your submission (like the current sentence), otherwise it hampers the professionality in your documentation.

*Here are some tips to write a good report:*

* `Bullet points` are allowed and strongly encouraged for this report. Try to summarise and list the highlights of your project (rather than give long paragraphs).*

* *Try to create `diagrams` for parts that could greatly benefit from it.*

* *Try to make your report `well structured`, which is easier for the reviewers to capture the necessary information.*

*We give instructions enclosed in square brackets [...] and examples for each sections to demonstrate what are expected for your project report. Note that they only provide part of the skeleton and your description should be more content-rich. Quick references about markdown by [CommonMark](https://commonmark.org/help/)*

## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Application Description](#application-description)
4. [Application UML](#application-uml)
5. [Application Design and Decisions](#application-design-and-decisions)
6. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
7. [Testing Summary](#testing-summary)
8. [Implemented Features](#implemented-features)
9. [Team Meetings](#team-meetings)
10. [Conflict Resolution Protocol](#conflict-resolution-protocol)

## Administrative

*Instruction: please place the CORRECT link to your firebase repository here (with comp21006442@gmail.com added as an Editor)*

- Firebase Repository Link: <I do not use Firebase> // TODO
   - Confirm: [ ] I have already added comp21006442@gmail.com as a Editor to the Firebase project prior to due date.
- Two user accounts for markers' access are usable on the app's APK (do not change the username and password unless there are exceptional circumstances. Note that they are not real e-mail addresses in use):
   - Username: comp2100@anu.edu.au	Password: comp2100 [ x ] // TODO: check if done
   - Username: comp6442@anu.edu.au	Password: comp6442 [ x ] // TODO: check if done

## Team Members and Roles
The key area(s) of responsibilities for each member

| UID                      |     Name      |                    Role |
|:-------------------------|:-------------:|------------------------:|
| u7782612                 |  Manav Singh  |          UI-UX Designer |
| u7787385                 |   Yuan Shi    | Data Structure Designer |
| u7782814                 | Amogh Agarwal |        Testing Engineer |
| u7556816                 |   Harry Xia   |         Search Engineer |
| u7781798                 | Yuvraj Singh  | Design Pattern Engineer |


## Summary of Individual Contributions

Specific details of individual contribution of each member to the project.

Each team member is responsible for writing **their own subsection**.

A generic summary will not be acceptable and may result in a significant lose of marks.

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*you should ALSO provide links to the specified classes and/or functions*
Note that the core criteria of contribution is based on `code contribution` (the technical developing of the App).

*Here is an example: (Note that you should remove the entire section (e.g. "others") if it is not applicable)*

1. **UID1, Name1**  I have 30% contribution, as follows: <br>
  - **Code Contribution in the final App**
    - Feature A1, A2, A3 - class Dummy: [Dummy.java](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java)
    - XYZ Design Pattern -  class AnotherClass: [functionOne()](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43), [function2()](the-URL)
    - ... (any other contribution in the code, including UI and data files) ... [Student class](../src/path/to/class/Student.java), ..., etc.*, [LanguageTranslator class](../src/path/to/class/LanguageTranslator.java): function1(), function2(), ... <br><br>

  - **Code and App Design** 
    - [What design patterns, data structures, did the involved member propose?]*
    - [UI Design. Specify what design did the involved member propose? What tools were used for the design?]* <br><br>

  - **Others**: (only if significant and significantly different from an "average contribution") 
    - [Report Writing?] [Slides preparation?]*
    - [You are welcome to provide anything that you consider as a contribution to the project or team.] e.g., APK, setups, firebase* <br><br>

2. **UID2, Name2**  I have xx% contribution, as follows: <br>
  - ...



## Application Description

*[What is your application, what does it do? Include photos or diagrams if necessary]*

*Here is a pet specific application example*

*PetBook is a social media application specifically targetting pet owners... it provides... certified practitioners, such as veterians are indicated by a label next to their profile...*

### Problem Statement

*[Problem statement that defines the purpose of your App]*


### Application Use Cases and/or Examples

*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

Citizen Reporting
Scenario 1: Reporting a Pothole
User: City Resident (e.g., John)
Precondition: John is driving and encounters a pothole on the road.
Action:
John opens the app on his mobile device.
He selects "Add Report" and chooses the "Infrastructure" category.
The GPS feature auto-captures his current location.
He adds additional details about the pothole and submits the report.
UI Feedback: A toast message displays: "Report Submitted Successfully!" confirming the action.
Outcome: The pothole is logged, and the city’s infrastructure department is notified.


Scenario 2: Reporting an Emergency (Fire Incident)
User: City Resident (e.g., Mark)

Precondition: Mark witnesses a fire at a nearby building.

Action:

Mark opens the app in an urgent situation and selects "Add Report."
He categorizes the report under "Environmental" or "Infrastructure," depending on the situation.
Mark sets the report priority to "HIGH" to indicate the emergency.
The GPS feature automatically captures Mark's current location for accurate reporting.
Mark adds details about the fire and submits the report.
UI Feedback: A toast message displays: "High-Priority Report Submitted! Authorities have been notified."

Outcome: The report is flagged as a high-priority issue, and the relevant authorities 
(e.g., fire department) are alerted to respond based on the GPS location and details provided. 
The authorities receive the report as a high-priority case, enabling them to respond promptly to 
the emergency situation.

Scenario 3: Submitting a GPS-Based Report
User: City Resident (e.g., Alice)
Precondition: Alice notices a broken streetlight on her evening walk.
Action:
Alice opens the app and selects "Add Report."
The GPS system captures her exact location.
She chooses the "Maintenance" category and submits the report.
UI Feedback: A toast message displays: "Task added Successfully."
Outcome: The report is stored with precise location data, allowing for easy dispatch of a repair team.

Scenario 4: Deleting a Report
User: City Resident (e.g., Sarah)
Precondition: Sarah has previously submitted a report that she now wants to remove because the issue has been resolved.
Action:
Sarah opens the app and navigates to her submitted reports.
She selects the report that she wants to delete.
Sarah taps the "Delete Report" button.
A confirmation dialog appears, asking if she is sure she wants to delete the report.
Sarah confirms the deletion.
UI Feedback: A toast message displays: "Report deleted successfully."
Outcome: The selected report is removed from the app's database and no longer appears in any report listings.
Postcondition: The report is permanently deleted from the system, and any linked data (e.g., charts, statistics) is updated to reflect the removal.

Scenario 5: Scheduling a Report Deletion
User: City Resident (e.g., John)
Precondition: John has submitted a report but knows the issue will be resolved in the future and prefers to schedule the deletion in advance.
Action:
John opens the app and navigates to his list of submitted reports.
He selects the report that he wants to delete at a future date.
John chooses the "Schedule Delete" option and selects a future date and time when the report should be deleted.
The app confirms that the deletion is scheduled.
UI Feedback: A toast message displays: "Report deleted successfully" when the report is deleted.
Outcome: The app will automatically delete the report at the specified time.
Postcondition: The report will be deleted at the scheduled time, and the app will update all related data and statistics to reflect the deletion.


### Application UML

![ClassDiagramExample](media/_examples/ClassDiagramExample.png) <br>
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

<hr>

## Code Design and Decisions

This is an important section of your report and should include all technical decisions made. Well-written justifications will increase your marks for both the report as well as for the relevant parts (e.g., data structure). This includes, for example,

- Details about the parser (describe the formal grammar and language used)

- Decisions made (e.g., explain why you chose one or another data structure, why you used a specific data model, etc.)

- Details about the design patterns used (where in the code, justification of the choice, etc)


*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design.

<hr>

### Data Structures

*[What data structures did your team utilise? Where and why?]*

Here is a partial (short) example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. **AVLTree**
    - **Objective:**  
      Used for storing and managing citizen reports efficiently.

    - **Code Locations:**  
      Defined in the `AVLTree` class within the `com.example.prototype` package. The AVLTree is utilized in the `DataHolder` class to store, retrieve, and manage reports.

    - **Reasons:**
        - **Efficiency:**  
          AVLTree ensures that insertion, deletion, and search operations are performed in O(log n) time complexity, which is more efficient than using linear data structures like `ArrayList` for large datasets.
        - **Balanced Structure:**  
          Maintains a balanced tree automatically after each operation,
          more efficient than linear-structure data structure.
        - **Sorted Data:**  
          Keeps the reports sorted based on their `reportId`, facilitating quick retrieval and organized data management.
        - **Dynamic Size:**  
          The tree can dynamically adjust its size as reports are added or removed, providing flexibility in handling varying amounts of data.
        - **Easy implementation:**
          AVLTree data structure is more easy to implement in terms of their operations than red-black tree and B-tree.

2. **Tokenizer**
    - **Objective:**  
      Used for parsing and processing user input in the `SearchView` to facilitate effective search functionality.

    - **Code Locations:**  
      Defined in the `Tokenizer` class within the `com.example.prototype` package. It is utilized in the `Parser` class to tokenize and normalize search queries before processing.

    - **Reasons:**
        - **Efficient Parsing:**  
          Breaks down complex search queries into manageable tokens, enabling more precise and relevant search results.
        - **Normalization:**  
          Converts input to lowercase and removes non-word characters except ":" to ensure consistency in search processing, reducing the likelihood of mismatches due to case sensitivity or unwanted characters.
        - **Streamlined Processing:**  
          Utilizes Java Streams and regular expressions for efficient tokenization and filtering of input, enhancing performance for real-time search features.
        - **Modularity:**  
          Encapsulates tokenization logic within a separate class, improving code readability, maintainability, and reusability across different components of the app.

3. **ArrayList**
    - **Objective:**  
      Used for storing lists of reports during tree traversal and for passing data to adapters for UI components.

    - **Code Locations:**  
      Utilized in methods such as `fromLargeToSmall()` and `fromSmallToLarge()` in the `AVLTree` class to store and return ordered lists of reports.

    - **Reasons:**
        - **Dynamic Resizing:**  
          Allows for dynamic addition and removal of elements without predefined size constraints.
        - **Random Access:**  
          Provides quick access to elements by index, which is useful for displaying reports in the UI.
        - **Integration with Adapters:**  
          Compatible with Android's `ListView` adapter, making it suitable for displaying reports in the app's user interface.
<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. Singleton Pattern
   * *Objective: Assures that a class is produced just once and offers a global point of access to that one instance.*
   * Code Locations: Class Authenticator: Throughout the app's lifetime, just one instance of Authenticator is produced thanks to the getInstance() method.
   * *Reasons:When we desire a single source of truth for the authentication logic used throughout the application, such as with Authenticator, we use singletons to govern the creation of objects.*

   * *Code Locations: Class DataHolder: Here, the singleton design pattern is used to maintain a single instance of the AVL tree throughout the program.*
   * *Reasons: Here,The Singleton pattern ensures that report instances are loaded into the AVL tree only once, maintaining consistent access and avoiding duplication throughout the application.*

2. Observer Pattern
    * *Objective: Enables an object (subject) to communicate any changes in state to its dependents (observers), usually in a decoupled fashion.*
    * *Code Locations: MainActivity implements Observer interface: This pattern allows MainActivity to observe and respond to data changes or events, such as when a report is added or removed, or when sorting is updated, from other activities.*
    * *Reasons:The observer design helps to maintain the user interface current with modifications to the data (e.g., when report data changes in MainActivity).*
  3. Factory Pattern
     *is developped as a surprise feature.*

<hr>

### Parser and Grammar
#### Grammar Design
There are two main types of grammars in this implementation:
1. Key-Value Grammar: Identifies tokens in the form of key-value pairs, where the key represents an attribute (e.g., description, location, priority) and the value is the filter criterion. This allows users to specify precise filters for specific attributes.
   "KeyValueToken = key : value"  
   example: "location:Main St", "priority:high"
2. General Token Grammar: Represents general search terms that apply to any attribute of the reports. These are useful for broad searches where the user is looking for a term that could appear in multiple fields.
   "GeneralToken = word" 
   example: "Noise", "Broken"
### Tokenizer
The tokenizer breaks the user input into discrete tokens, identifying both key-value pairs and general tokens. It ensures that the input is standardized by removing special characters and splitting the input based on spaces.
1. input Cleaning: Removes non-word characters (excluding the colon : used in key-value pairs) and converts the input to lower case to ensure consistent processing.
2. Token Generation: Splits the cleaned input by spaces to create a list of tokens.
3. Identification: Distinguishes key-value pairs from general tokens based on the presence of a colon : .
4. Example `"[^\\p{L}\\p{N}:]+"` The regular expression matches one or more characters that are not letters, numbers, or colons (:).
5. Example `split("\\s+")` Split based on spaces.
### Parser
The parser interprets the tokens generated by the tokenizer and applies them to filter the list of reports. It processes key-value pairs and general tokens differently to efficiently filter reports based on the user's search criteria.
1. Divides tokens into key-value pairs and general tokens for specialized processing.
2. Key-Value Processing: Filters reports by matching the specified key with the corresponding attribute in each report.
   Compares the value in the key-value pair with the attribute's value in the report.
3. General Token Processing: Checks multiple attributes (e.g., description, location, category, priority, user) for the presence of the token.
4. Apply Key-Value Filters: For each key-value pair, the parser filters the list of reports to include only those where the report's attribute matches the specified value.
   For example, a key-value pair location:main would filter reports where the location contains "main".
5. Apply General Token Filters: For each general token, the parser further filters the reports to include those where any relevant attribute contains the token.
   This allows for a broader search, capturing reports that may not be filtered by key-value pairs alone.
6. Example: use input -> `"location:Main St#$@.. priority:high pothole."`  
   tokens -> `"location:main", "st", "priority:high", "pothole"`  
   Key-Value Tokens : `"location:main", "priority:high"`  
   General Tokens: `"st", "pothole"`  
   result: reports in which location is main and priority is high, and also contain "st' and "pothole".
<hr>

### Others

A feature we would like to highlight that we implemented was forming a 
foundational activity - BaseActivity which acts as an Interface for all
the other activities for the application. It lays out the inheriting
activity as a child view and displays the foundation as the primary
content. 
This also integrates a central navigation system which allows for 
accesibility to other activities. 

The purpose of this feature was to have a standard design for every
page of the application and increase code reusability. It prevented
us from having to add buttons in every activity for navigation and
also made the UI interface uniform in terms of Layout.

<br>
<hr>

## Implemented Features
*[What features have you implemented? where, how, and why?]* <br>
*List all features you have completed in their separate categories with their featureId. THe features must be one of the basic/custom features, or an approved feature from Voice Four Feature.*

### Basic Features
1. [LogIn]. Description of the feature ... (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of feature: ... <br>
   * Description of your implementation: ... <br>

2. [DataFiles]. Description of the feature ... (easy)
   * Code to the Data File [reports_dataset.json](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/4211a2b73d3609ae153915b5ea24e70b69bb785c/report_dataset_generator/generate_reports.py).
   * Description of feature: Our dataset is stored in a json file in the folder report_dataset_generator. This feature is intended to have a dataset consisting of 2500 instances of our primary data which is reports. A comprehensive dataset of this size and variety allows for robust and accurate testing of the core functionalities in our application (Such as Searching, Sorting and Data Visualisation). It simulates a real-world scenario wherin an app will have a diverse variety of user-inputs and should be able to handle large volues of data efficiently. <br>
   * Description of our implementation: We used a python script to create 2500 instances of report which are our main data files. It uses the random function to generate reports from pre-defined data pools for fields - location, category, priority, users, date, time and likes to ensure a large diversity in the dataset. For each report, a unique integer id is attached. Each report is saved as a dictionary and added to a list, which is ultimately serialised into a formatted JSON file reports_dataset.json. <br>
   
3. [LoadShowData]. Description of the feature ... (easy) 
    * Code: [Class MainActivity, method loadData()](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/util/JsonDeserialiser.java?ref_type=heads)
    * Code: [Class ReportAdapter, method getView()](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/util/JsonDeserialiser.java?ref_type=heads)
    * Code: [Class AVLTree, the entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/util/JsonDeserialiser.java?ref_type=heads)
    * Code: [Class JsonDeserialiser, the entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/util/JsonDeserialiser.java?ref_type=heads)
    * Description of feature: The AVLTree loads data from json file, converting them into Reports class and store them on the AVLTree, the dashboard displays reports instances onto the dashboard in scrolling order, with each report instance containg all the information about the report.
   <br>
   * Description of your implementation: The loadData method reads data from json file in the assets folder and convert into a list of reports. The AVLTree add the reports onto itself, with reportId as its key, and Report class instance as the value. To show the data onto the dashboard, a customized array adapter is used to show the reports onto the dashboard, with relevant information dsiplayed nicely.

4. [DataStream]. Description of the feature ... (medium)
   * Code: [Class MainActivity, methods startStreamThread, stopStreamThread, onStart, onStop](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java?ref_type=heads) 
   * Description of feature: This feature allows the app to automatically add new report instances to the dashboard and store them in the AVL tree at specified time intervals, simulating the behavior of multiple users continuously submitting reports when encountering issues. The data stream process halts whenever the user navigates to another activity or performs a search.<br>
   * Description of your implementation: A single thread controls the data stream without relying on advanced Android threading techniques. The thread reads data from a JSON file, deserializes it into a list of report instances, and iterates through the list to add each report to the AVL tree for storage. The runOnUiThread method is used to reflect the changes on the dashboard in real time. After adding a report, the thread pauses for a specified interval using Thread.sleep(). If the user switches to another activity or performs a search, the streaming process halts. Upon returning to the main dashboard, the stream resumes from where it left off. The implementation properly handles Android lifecycle events, ensuring the stream starts or resumes when needed and stops appropriately when leaving the MainActivity or interacting with the search bar. <br>

5. [UXUI] Description of the feature ... (easy)
    * Code: [Layout XML-file : activity_main.xml](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/0dfa6e401f0e1e40998f356b148f66c09a1be5fb/app/src/main/res/layout/activity_main.xml), [Layout XML-file : activity_base.xml](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/0dfa6e401f0e1e40998f356b148f66c09a1be5fb/app/src/main/res/layout/activity_base.xml), [Layout XML-file : activity_login.xml](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/0dfa6e401f0e1e40998f356b148f66c09a1be5fb/app/src/main/res/layout/activity_login.xml)
   * Description of feature: To maintain a uniform and consistent design language throughout the application, we ensured that the colors, fonts, and UI element styles worked with each other in harmony and made the User visual experience pleasant and enjoyable.
      * Font Used : Poppins
      * Colour Scheme : Black and White (Minimalistic)
      * UI Element Style : IOS 17 Filled Icons <br>
   * Description of your implementation: Maintaining a consistent design language and structure throughout without creating huge duplicate amounts of code was a challenge to us in the start of the project. This challenge was overcome by deciding to implement a foundational activity page that would display other activities as secondary content within the layout screen. This was implemented in BaseActivity which serves as the primary content view whose properties are inherited by every other activity. It incorporates features for navigation to different pages of the app as well as a fixed standard layout window which ensures that the size of the displayed activity is the same everytime without the need for explicit instruction. This helped us keep the design language consistent along with the usage of uniform UI features. <br>
<br>

### Custom Features
Feature Category: Privacy <br>
1. [Privacy-Request]. Description of the feature  (easy)
   * Code: [Class X, methods Z, Y](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * Description of your implementation: ... <br>
     <br>

2. [Privacy-Block]. Description ... ... (medium)
   ... ...
   <br><br>

Feature Category: Firebase Integration <br>
3. [Data-deletion] Description of the feature (medium)
   * Code: [Class AVLTree, method remove](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   * [Class ReportAdapter](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/ReportAdapter.java?ref_type=heads): methods deleteButton.setOnClickListener, deleteReport()
   * Description of your implementation: I firstly wrote the code of removing an element from the AVLTree. Recursion is used here, with the use of helper recur method removeRec(). It breaks into 3 cases of removing, with the consideration of height update and tree rebalancing. Then in the ReportAdapter, If you click on the deletion button, the report get removed from the list of array adapter used for showing the report items, and then pass the reportId to the MainActivity to delete the report from the tree.  <br>

<hr>

### Surprise Feature

*Instructions:*
- If implemented, explain how your solution addresses the task (any detail requirements will be released with the surprise feature specifications).


<br> <hr>

(i) Identifying a Code Component for Refactoring with a Design Pattern

Before releasing the surprise feature, our group had already implemented two design patterns in the codebase. While reviewing my teammate's code, I found a code snippet using a switch statement to determine different sorting actions based on the position of the input. This component could benefit from being refactored using the Factory Design Pattern.

The original code involved multiple conditional branches with a switch statement, which made the code less flexible and harder to maintain as new sorting requirements emerged, which led to several issues:

    Lack of Extensibility: Whenever new sorting logic was introduced, we had to modify the existing switch statement, which violated the Open-Closed Principle.
    Complexity: The switch statement made the code less readable and harder to maintain as the number of conditional branches increased.

    Relevant Git Commits, Files, and Line Numbers (Before 10 October):

        Commit  SHA:e13cd0f77d1d93ea7d4e362b1e6807bf449930ee
        Like: 
        File: src/com/example/prototype/sorting/MainActivity.java
        Lines: 238-284
        Link to Commit: https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/064ef98de1ce8743521e2d2a3943707b1c227132/app/src/main/java/com/example/prototype/MainActivity.java

(ii) Correcting the Implementation Issues Identified

To resolve these issues, I refactored the code to use a Factory Design Pattern:

    I defined a Functional Interface called Sorter  with a sort() method. I Implemented Different Sorters: Created different classes that implemented the Sorter interface, each providing a specific sorting method.I then Built a Sorter Factory: Developed a SorterFactory class to generate appropriate Sorter instances based on the input position.

 This new approach improved the robustness and maintainability of the sorting logic. Now, adding new sorting algorithms does not require modifying the existing factory but simply adding a new implementation of the Sorter interface.

    Relevant Git Commits, Files, and Line Numbers (On or After 10 October):

        Commit SHA: bb36739f0c9ce9fdcc165370f1e10af5030364c9
          File: src/com/example/prototype/report/MainActivity.java
            Lines: 237-240
          File: all the files under package src/com/example/prototype/sort
            Link to Commit: https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/commit/bb36739f0c9ce9fdcc165370f1e10af5030364c9
    (Note: The sortReports method in MainActivity was refactored by my teammate - the original author after October 10 to enhance its readability. The version I worked on differs slightly from the original one I provided due to these improvements. However, due to project requirement, I am not permitted to provide any commits or code changes made after October 10.)

## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide details (see rubrics) including some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
   - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java) for the [Tokenizer Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   - *Number of test cases: ...*
   - *Code coverage: ...*
   - *Types of tests created and descriptions: ...*

2. Tests for tree's behaviour.
    - Code: [AVLTreeTest Class, entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/test/java/com/example/prototype/AVLTreeTest.java?ref_type=heads) for the [AVLTree Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   - *Number of test cases: 13*
   - *Code coverage: 100%*
   - *Types of tests created and descriptions: use int as key and String as value, a bunch of unit tests including: setup test, behaviour tests on insert, remove, modify, get and sorting and traversal. Also extreme case of large data and no data is tested either. Exception are checked as well on non-exsistence keys, throw NoSuchElementException.*
3. Tests for load data.
    - Code: [JsonDeserializerTest](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/test/java/com/example/prototype/JsonDeserializerTest.java?ref_type=heads) for the [JsonDeserialiser class, entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/util/JsonDeserialiser.java?ref_type=heads)
    - *Number of test cases: 2*
    - *Code coverage: 80%*
    - *Types of tests created and descriptions: Read in both a valid json string and an invalid json string, the valid one should be converted into a valid Report class, with attributes match perfectly. The invalid one, upon deserialize on it, will throw NullPointerException.*
4. Tests for reports addition and removal.
    - Code: [ReportStorageTest](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/test/java/com/example/prototype/ReportStorageTest.java?ref_type=heads) for the [AVLTree Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) 
    - *Number of test cases: 1*
    - *Code coverage: 70%*
    - *Types of tests created and descriptions: 1 big integration test use Report as the generic type to test whether Reports are stored and removed correctly, this integration test involves error handling, boundary case of empty size, also mutation test involving changing value to fail the test.*
5. Tests for singleton behaviour
    - Code: [DataHoldertest](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/test/java/com/example/prototype/DataHolderTest.java?ref_type=heads) for the [DataHolder class, entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/data/DataHolder.java?ref_type=heads)
    - *Number of test cases: 4*
    - *Code coverage: 80%*
    - *Types of tests created and descriptions: Including setup test, test 2 instances are the same on empty tree and on non-empty tree, after the tree has added or removed some elements. *

...

<br> <hr>



## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*
*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

*Here is an example:*

1. *Bug 1:*
    - *A space bar (' ') in the sign in email will crash the application.*
    - ...

2. *Bug 2:*
3. ...

<br> <hr>


## Team Management

### Meeting Minutes
* Link to the minutes of your meetings like above. There must be at least 4 team meetings.
  (each committed within 2 days after the meeting)
* Your meetings should also have a reasonable date spanning across Week 6 to 11.*


- *[Team Meeting 1](link_to_md_file.md)*
- ...
- ...
- [Team Meeting 4](link_to_md_file.md)
- ... (Add any descriptions if needed) ...

<hr>

### Conflict Resolution Protocol
*[Write a well defined protocol your team can use to handle conflicts. That is, if your group has problems, what is the procedure for reaching consensus or solving a problem?
(If you choose to make this an external document, link to it here)]*

*If your group has issues, how will your group reach consensus or solve the problem?*
*- e.g., if a member gets sick, what is the solution? Alternatively, what is your plan to mitigate the impact of unforeseen incidents for this 6-to-8-week project?*

This shall include an agreed procedure for situations including (but not limited to):
- A member is sick in the final week of the project.
- A member didn't complete the assigned task which should've been completed before the checkpoint, and the checkpoint is approaching.
- A member is unreachable (didn't respond messages in your agreed communication channels and emails in two days).
- The team have different understandings toward the requirement of the assignment.


