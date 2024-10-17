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
| u7782612                 |  Manav Singh  | Data Engineer and UI-UX Designer |
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

2. **u7787385, Yuan Shi**  I have 20% contribution, as follows: <br>
- **Code Contribution in the final App**
    - AVLTree implementation, including feature tree deletion - class AVLTree: [AVLTree.java](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/data/AVLTree.java?ref_type=heads)
    - Implement loadShowData feature. package:[entity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/tree/main/app/src/main/java/com/example/prototype/entity?ref_type=heads), class:[ReportAdapter.java](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/ReportAdapter.java?ref_type=heads) [JsonDeserializer.java](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/util/JsonDeserialiser.java?ref_type=heads)
    - Implement Data-stream feature. [MainActivity.class](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java?ref_type=heads), methods: `startStreamThread()` and `stopStreamThread()`
    - Utilize 3 design patterns in the project.

- **Code and App Design**
    - Finish design patterns parts as well as data structure parts in the report,in addition to my relevant part.
    - MainActivity integration, created MainActivity and interate the whole app together. -class MainActivity.
    - Initial design of the UI for the dashboard.
    - Write code to help facilate data exchange between activities. [ReportActivity.java](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/ReportActivity.java?ref_type=heads)line196-201.
    - Utilize 2 design patterns and the complete surprise feature task 1 and 2 by refacering Harry's code.


3. **U7782814, Amogh Agarwal**  I have 20% contribution, as follows: <br>
- **Code Contribution in the final App**
    - Report Submission Feature - class ReportActivity: [ReportActivity.java](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/411f230b8c6fff7d70fc1481b2cca36973f0a122/app/src/main/java/com/example/prototype/report/ReportActivity.java)
    - Data Analysis and Graph Generation - class ReportAnalyzer: [ReportAnalyzer.java](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/411f230b8c6fff7d70fc1481b2cca36973f0a122/app/src/main/java/com/example/prototype/chart/ReportAnalyzer.java)
    - Report Deletion UI Feedback - class ReportAdapter: [ReportAdapter.java](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/e660597bd3b42e5b9412a2b4ce5e2a7121715609/app/src/main/java/com/example/prototype/report/ReportAdapter.java)
    - Assisted other members in various features and code implementation in the app <br><br>

- **Code and App Design**
    - Proposed and implemented the ReportActivity, handling the report submission process, including GPS location retrieval and scheduling report submissions.
    - Proposed and implemented the ReportAnalyzer class for analyzing report data, generating insights for chart displays.
    - Collaborated with the team in app design, including UI feedback elements and GPS integration for the ReportActivity.* <br><br>

- **Others**: (only if significant and significantly different from an "average contribution")
    - Created simple UML diagrams for the app, since the other member was unable to initally make it due to other tasks.
    - Wrote the initial drafts of the conflict resolution plan and contributed to the final draft as well.
    - Developed the control flow graph for one of the core features.
    - Contributed significantly to writing the report, ensuring that all contributions and team efforts were documented accurately.

4. **u7556816, Harry Xia**  I have 20% contribution, as follows: <br>
     - **Code Contribution in the final App**
          - Search Feature - [Class: Parser](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Parser.java), [Class: Tokenizer](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Tokenizer.java)
          - Sort and Filter feature - [Classes inside sort folder](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/tree/main/app/src/main/java/com/example/prototype/sort)
          - Search and filter related methods in MainActivity:
           *  [Class MainActivity; methods setupSortSpinner](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L165-189)
           *  [Class MainActivity; methods refreshDisplayedList](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L219-236)
           *  [Class MainActivity; methods sortReports](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L238-241)
           *  [Class MainActivity; methods updateStreamState](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L211-217)
     - **Code and App Design**
        - Worked with Yuan to create the sort functions with multiple sorter classes NewestFirst, OldestFirst, PriorityHighFirst, PriorityLowFirst, LikesHighFirst.
        - Designed and implemented the search features with class Parser and Tokenizer.
        - Integrated sorting and filtering methods within the MainActivity to provide seamless user interactions.
        - Designed testings for search and sort with ReportSearchTest and ReportSortTest classes.
        - Designed UI components for search and sort functionalities* <br><br>

     - **Others**: (only if significant and significantly different from an "average contribution")
        - Contributed significantly to writing the report.
        - Designed and prepared the project presentation slides.


## Application Description

*[What is your application, what does it do? Include photos or diagrams if necessary]*

*Here is a pet specific application example*

*PetBook is a social media application specifically targetting pet owners... it provides... certified practitioners, such as veterians are indicated by a label next to their profile...*

### Problem Statement

*[Problem statement that defines the purpose of your App]*


### Application Use Cases and/or Examples

Citizen Reporting
Target Users: The target users for this app are the citizens of a city. Below are a few example cases for the use of the application.

    Scenario 1: Reporting a Pothole
    User: City Resident (e.g., John)
    Precondition: John is driving and encounters a pothole on the road.
    Action:John opens the app on his mobile device. He selects "Add Report" and chooses the "Infrastructure" category. The GPS feature auto-captures his current location. He adds additional details about the pothole and submits the report.
    UI Feedback: A toast message displays: "Report Submitted Successfully!" confirming the action.
    Outcome: The pothole is logged, and the city’s infrastructure department is notified.

    Scenario 2: Deleting a Report
    User: City Resident (e.g., Sarah)
    Precondition: Sarah has previously submitted a report that she now wants to remove because the issue has been resolved.
    Action: Sarah opens the app and navigates to her submitted reports. She selects the report that she wants to delete. Sarah taps the "Delete Report" button. A confirmation dialog appears, asking if she is sure she wants to delete the report. Sarah confirms the deletion.
    UI Feedback: A toast message displays: "Report deleted successfully."
    Outcome: The selected report is removed from the app's database and no longer appears in any report listings.
    Postcondition: The report is permanently deleted from the system, and any linked data (e.g., charts, statistics) is updated to reflect the removal.

    Scenario 3: Scheduling a Report Deletion
    User: City Resident (e.g., John)
    Precondition: John has submitted a report but knows the issue will be resolved in the future and prefers to schedule the deletion in advance.
    Action: John opens the app and navigates to his list of submitted reports. He selects the report that he wants to delete at a future date. John chooses the "Schedule Delete" option and selects a future date and time when the report should be deleted. The app confirms that the deletion is scheduled.
    UI Feedback: A toast message displays: "Report deleted successfully" when the report is deleted.
    Outcome: The app will automatically delete the report at the specified time.
    Postcondition: The report will be deleted at the scheduled time, and the app will update all related data and statistics to reflect the deletion.

![A basic User Diagram to explain the process is provided in the items also. Here is the Image](UserDiagram.png)

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
    - Objective: Used for storing and managing citizen reports efficiently.

    - Code Locations:**  Defined in the [AVLTree](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/data/AVLTree.java?ref_type=heads) class within the `com.example.prototype` package. The AVLTree is kept in the `DataHolder` class.

    - Reasons:
        - Efficiency:  
          AVLTree ensures that insertion, deletion, and search operations are performed in O(log n) time complexity, which is more efficient than using linear data structures like `ArrayList` for large datasets.
        - Sorted Data:
          sorted based on their `reportId`, facilitating quick retrieval of the report based on the reportId.
        - Easy implementation:
          AVLTree data structure is more easy to implement in terms of their operations, especially deletion, than red-black tree and B-tree.

2. **ArrayList**
    - **Objective:**  
      Used for for loading data from json data file and for passing data to customized array adapters for UI visual representation.

    - **Code Locations:**  Defined in the [MainActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java?ref_type=heads) class, `reportList`. [Parser](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Parser.java?ref_type=heads) class, all the methods. [Parser](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Parser.java?ref_type=heads) class, all the methods. [CategoryChartActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/chart/CategoryChartActivity.java?ref_type=heads) methods `onCreate()`, `retrieveCategoryCountsFromIntent()`, `loadPieChartData()`. [AVLTree](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/data/AVLTree.java?ref_type=heads), methods`fromLargeToSmall` or `fromSmallToLarge`.
    - **Reasons:**
        - Easy to use:
          Compared to the complexity of implementing an AVL tree, an ArrayList is simpler to use while still offering reasonable efficiency.
        - Efficient Access:  
          Provides quick access to elements by index, which is more effcient for retrieving report instance, with o(1) time complexity.
        - Integration with Adapters:
          Compatible with Android’s ListView() method, our customized adapter simplifies the display of reports in the app’s user interface by efficiently managing data binding and rendering. It allows dynamic updates to the list, ensuring that any changes, such as adding and deleting reports, are immediately reflected in the UI. This integration provides a smooth user experience and flexibility. Originally, I directly pass in the tree for the adapter, however, later it seems like it is hard to render data, especially it comes to sorting and filering, compared to arraylist.
        - Easy to work with search and filter feature: The ArrayList offers straightforward integration with search and filter features. It allows efficient querying, filtering adn sorting based on criteria such as keywords, categories, or dates, while to achieve the same result, we need to generate multiple avlTrees sorted by different attributes, which is complicated. This makes ArrayList a practical choice for scenarios where flexibility and ease of use are preferred.
        - Limitedness of `AVLTree`: it requires using recursion to traverse the tree, however, what if we want to do something to the whole reports stored in the tree? We have to traverse the tree and transform it into an `List`.  
          <br>
3. **HashMap**
    - **Objective:**  
      The ReportAnalyzer class aggregates and summarizes data from Report objects by generating key-value maps for priorities, categories, and locations, enabling efficient drawing.

    - **Code Locations:**  Defined in the [ReportAnalyzer](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/chart/ReportAnalyzer.java?ref_type=heads), all methods.
    - **Reasons:**
        - Efficient Data Grouping:
          The key-value maps allow for quick access to information based on keys, such as the number of high-priority reports or reports per location, which could allowed the data grouped based on whatever label quickly.

        - Flexibility for showing:
          The generated maps can be used directly in all kind of charts that require grouped data, integrating well with the code regarding graph drawing process.
          <br>
<hr>

### Design Patterns
*[What design patterns did your team utilise? Where and why?]*

1. Singleton Pattern
    * *Objective: Assures that a class is produced just once and offers a global point of access to that one instance.*
    * Code Locations: Class [Authenticator](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/login/Authenticator.java?ref_type=heads) method:`getInstance()`:
    * *Reasons:When we desire a single source for the authentication logic used throughout the application, such as with Authenticator, we use singletons to govern the creation of objects. Throughout the app's lifetime, just one instance of Authenticator is produced thanks to the getInstance() method.*

    * *Code Locations: Class [DataHolder](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/data/DataHolder.java?ref_type=heads): all the class.
    * *Reasons: Here,The Singleton pattern ensures that report instances are loaded into the AVL tree only once, maintaining consistent access and avoiding duplication throughout the application. If instance is null, a new instance is created; otherwise, the existing instance is returned. The DataHolder class provides a globally accessible container for storing and managing Report objects through the avlTree. Since it follows the Singleton pattern, all parts of the program that need access to the avlTree can do so via the single instance of DataHolder.

2. Observer Pattern
    * *Objective: Enables an activity to communicate any changes in state to another activity (observers).*
    * *Code Locations: Interface: [Observer](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/Observer.java?ref_type=heads), class[MainActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java?ref_type=heads), method`onClickPassData`, lines322-335, is an observer, and it listens to class[ReportAdapter](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/ReportAdapter.java?ref_type=heads), method`deleteReport()`, lines 178, is being observerd.
    * *Reasons: implements Observer interface: This pattern allows MainActivity to observe and respond to data changes or events from another activity or class, in this case, ReportAdapter class, such as when a report is added or removed, or when sorting is updated.
    - Real-Time Updates: The UI is instantly updated as soon as a change occurs, improving user experience by keeping the UI responsive and current.
    - Decoupling of Components: Just inform the observer and the observer will do the respective changes, reducing tight coupling between components and improving code maintainability.
    - Simplifies Synchronization: By automatically receiving notifications about data changes, the pattern ensures the UI state remains synchronized with the underlying data. It replaces the advanced techniques used for passing or notifying.
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
6. Example: user input -> `"location:Main St#$@.. priority:high pothole."`  
   tokens -> `"location:main", "st", "priority:high", "pothole"`  
   Key-Value Tokens : `"location:main", "priority:high"`  
   General Tokens: `"st", "pothole"`  
   result: reports in which location is main and priority is high, and also contain "st' and "pothole".
<hr>

### Others

A feature we would like to highlight that we implemented was forming a
foundational activity - [BaseActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/util/BaseActivity.java) which acts as an Interface for all
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
    * Code: [Class MainActivity, method loadData()](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java?ref_type=heads)
    * Code: [Class ReportAdapter, method getView()](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/ReportAdapter.java?ref_type=heads)
    * Code: [Class AVLTree, the entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/data/AVLTree.java?ref_type=heads)
    * Code: [Class JsonDeserialiser, the entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/util/JsonDeserialiser.java?ref_type=heads)
    * Description of feature: The AVLTree loads data from json file, converting them into Reports class and store them on the AVLTree, the dashboard displays reports instances onto the dashboard in scrolling order, with each report instance containg all the information about the report.
    * Description of your implementation: The loadData method reads data from json file in the assets folder and convert into a list of reports. The AVLTree add the reports onto itself, with reportId as its key, and Report class instance as the value. To show the data onto the dashboard, a customized array adapter is used to show the reports onto the dashboard, with relevant information dsiplayed nicely.<br>
4. [DataStream]. Description of the feature ... (medium)
    * Code: [Class MainActivity, methods startStreamThread, stopStreamThread, onStart, onStop](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java?ref_type=heads)
    * Description of feature: This feature allows the app to automatically add new report instances to the dashboard and store them in the AVL tree at specified time intervals, simulating the behavior of multiple users continuously submitting reports when encountering issues. The data stream process halts whenever the user navigates to another activity or performs a search.<br>
    * Description of your implementation: A single thread controls the data stream without relying on advanced Android threading techniques. The thread reads data from a JSON file, deserializes it into a list of report instances, and iterates through the list to add each report to the AVL tree for storage. The runOnUiThread method is used to reflect the changes on the dashboard in real time. After adding a report, the thread pauses for a specified interval using Thread.sleep(). If the user switches to another activity or performs a search, the streaming process halts. Upon returning to the main dashboard, the stream resumes from where it left off. The implementation properly handles Android lifecycle events, ensuring the stream starts or resumes when needed and stops appropriately when leaving the MainActivity or interacting with the search bar. <br>

5. [Search] (medium)
    * Code:[Class: Parser](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Parser.java), [Class: Tokenizer](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Tokenizer.java),
      [Method: setupSearchView()](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L190-208), [Method: refreshDisplayedList()](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L218-235)
    * Description of feature: The search feature allows users to perform complex searches on the reports. Users can enter queries using key-value pairs to filter specific attributes (e.g., location:Main St, priority:high) and general keywords to search across all attributes (e.g., pothole). The app processes these queries and displays reports that match the search criteria.<br>
    * Description of your implementation: The implementation uses a two-step process involving tokenization and parsing.
    1. Tokenizer: The Tokenizer class handles the preprocessing of the user's input query. It removes special characters (except the colon : used in key-value pairs), converts the input to lower case for case-insensitive matching, and splits the input into tokens based on whitespace. It distinguishes between key-value tokens (containing a colon) and general tokens.
    2. Parser:  The Parser class interprets the tokens and applies appropriate filters to the list of reports. It separates the tokens into key-value pairs and general tokens.  
       For key-value pairs, the parser matches the key to specific report attributes (such as description, location, priority, category, user, and likes). It filters the reports by comparing the attribute's value to the provided value, supporting partial matches for strings and exact matches for numerical fields like likes.  
       Edge cases are handled gracefully. For example, if an invalid key is provided, the parser ignores it and continues processing the rest of the query. If a non-numeric value is provided for a numeric field like likes, the parser catches the exception and continues without filtering by that criterion.  
       For general tokens, the parser searches across multiple report attributes, including description, location, category, priority, user, and likes. It includes reports where any of these attributes contain the general token.<br>
       <br>

    * Description of feature: Our dataset is stored in a json file in the folder report_dataset_generator. This feature is intended to have a dataset consisting of 2500 instances of our primary data which is reports. A comprehensive dataset of this size and variety allows for robust and accurate testing of the core functionalities in our application (Such as Searching, Sorting and Data Visualisation). It simulates a real-world scenario wherin an app will have a diverse variety of user-inputs and should be able to handle large volues of data efficiently. <br>
    * Description of our implementation: We used a python script to create 2500 instances of report which are our main data files. It uses the random function to generate reports from pre-defined data pools for fields - location, category, priority, users, date, time and likes to ensure a large diversity in the dataset. For each report, a unique integer id is attached. Each report is saved as a dictionary and added to a list, which is ultimately serialised into a formatted JSON file reports_dataset.json. <br>

7. [UI Feedback]. Description of the feature ... (easy)
    * Code: [Class BaseActivity, method onCreate](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/b98c45c8d17d8fefb6b2c1463ec23ecb1082342f/app/src/main/java/com/example/prototype/util/BaseActivity.java)
    * Code: [Class ReportActivity, method onCreate](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/b98c45c8d17d8fefb6b2c1463ec23ecb1082342f/app/src/main/java/com/example/prototype/report/ReportActivity.java)
    * Code: [Class ReportAdapter, method getView](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/b98c45c8d17d8fefb6b2c1463ec23ecb1082342f/app/src/main/java/com/example/prototype/report/ReportAdapter.java)
    * Code: [Class MainActivity, method onCreate](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/b98c45c8d17d8fefb6b2c1463ec23ecb1082342f/app/src/main/java/com/example/prototype/report/MainActivity.java)
    * Description of feature: This feature provides instant feedback to the user while interacting within the app—whether clicking various activities, and options, liking/unliking reports, adding reports, or deleting them. The BaseActivity has toast notifications that provide feedback confirming the user's interaction with navigation buttons such as a dashboard or charts has been received. The ReportActivity displays toast messages from dropdowns about the user's selection of categories and priorities. Also, when they submit a report, a toast notification appears upon successfully submitting a report. ReportAdapter has a changing heart icon as soon as the user likes/dislikes a report. A confirmation dialog also appears when deleting a report, giving users the option to delete it immediately or schedule the deletion. Whenever a new report is added, the report list is immediately updated, and a toast from the MainActivity notifies the user.
      <br>

### Custom Features
Feature Category: Privacy <br>
Feature Category: Data <br>
1. [Data-Graphical]. Description of the feature  (hard)
    * Code: [Class ReportAnalyzer](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/0dfa6e401f0e1e40998f356b148f66c09a1be5fb/app/src/main/java/com/example/prototype/chart/ReportAnalyzer.java), [method getPriorityCounts](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/chart/ReportAnalyzer.java#L23-38), [method getCategoryCounts] (https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/chart/ReportAnalyzer.java#L40-53), [method getLocationCounts](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/chart/ReportAnalyzer.java#L55-78) [Class ChartActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/chart/ChartActivity.java), [Class LocationChartActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/chart/LocationChartActivity.java), [Class CategoryChartActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/chart/CategoryChartActivity.java) and [Class PriorityChartActivity](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/app/src/main/java/com/example/prototype/chart/PriorityChartActivity.java).
    * Description of your implementation: The MPAndroid Chart library is used to display meaningful data graphically in our application. The graphical representation makes analyzing data more intuitive for users. It also makes it easier to deduce observations by the governing authority of this application and these observations can play a key role in forming future decisions that determine the operation of the intended smart city. To view these graphs, the user is able to navigate to the graphs section of the app from any page in the app using the dashboard. The graphs button on the dashboard routes the user to ChartActivity where the user has options of reports to choose from. The implementation consists of 3 different types of graphs displaying important information that portray the trends in report filing based on Priority, Location and Category.


      - Priority Bar Graph - Pressing the Priority Graph button routes the user to the PriorityChartActivity which implements a standard bar graph to display the amount of reports categorised by their priority - (High, Medium, Low). The activity page retrieves the data in the form of extra intent passed on by ChartActivity which utilises the method getPriorityCounts from ReportAnalyzer. This information is passed on as a HashMap which iterates through the input(reports) and counts the number of reports in each priority. This HashMap is then used to display the Bar Graph using the MPAndroidChart library with standard implementation. 
      - Category Pie Graph - Pressing the Category Graph button routes the user to the CategoryChartActivity which implements a standard pie graph to display the amount of reports categorised by their category - (Maintenance, Safety, Public, Utilities, Environmental, Community, Infrastructure). The activity page retrieves the data in the form of extra intent passed on by ChartActivity which utilises the method getCategoryCounts from ReportAnalyzer. This information is passed on as a HashMap which iterates through the input(reports) and counts the percentage of reports in each category. This HashMap is then used to display the Pie Chart using the MPAndroidChart library with standard implementation.
      - Location Radar Graph - Pressing the Location Graph button routes the user to the LocationChartActivity which implements a standard radar graph to display the amount of reports categorised by their location - (form sample data and newly added report locations). The activity page retrieves the data in the form of extra intent passed on by ChartActivity which utilises the method getLocationCounts from ReportAnalyzer. This information is passed on as a HashSet which iterates through the input(reports) and counts the number of reports in each unique location. This HashSet is then used to display the Radar Graph using the MPAndroidChart library with standard implementation.
     <br>
3. [Search-Filter] (easy)
    * Code: Class MainActivity; 
   [methods setupSearchView](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L191-209),
   [method setupSortSpinner](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L165-189), 
   [method refreshDisplayedList](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L219-236), 
   [method sortReports](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L238-241), 
   [method updateStreamState](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/MainActivity.java#L211-217).
    * Code: [Class LikesHighFirst](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/sort/LikesHighFirst.java),
   [Class NewestFirst](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/sort/NewestFirst.java),
   [Class OldestFirst](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/sort/OldestFirst.java),
   [Class PriorityHighFirst](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/sort/PriorityHighFirst.java),
   [Class PriorityLowFirst](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/sort/PriorityLowFirst.java),
   [Class ReportSorter](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/sort/ReportSorter.java),
   [Class ReportSorterFactory](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/sort/ReportSorterFactory.java).
    * Description of feature: The Search-Filter feature allows users to search and filter reports using appropriate UI components. Users can enter search queries to filter reports based on specific criteria and sort the resulting list using various sorting options.
    * Description of your implementation:
        1. The implementation integrates both search and sort functionalities within the MainActivity class, utilizing UI components such as `SearchView` and `Spinner` to provide an interactive interface.
        2. The `setupSearchView` method initializes the `SearchView` and sets up a listener for query text changes. When the query text changes, the app updates the current search query, adjusts the data stream accordingly, and refreshes the displayed list.
        3. The `setupSortSpinner` method initializes the Spinner with sorting options and sets up a listener for item selection. The `sortReports` method uses the `ReportSorterFactory` to create an appropriate `ReportSorter` instance based on the selected sort option.
           Available sorting options include:Default (no sorting), Date (Newest First), Date (Oldest First), Priority (High to Low), Priority (Low to High), Likes (Most Liked First).
        4. The `refreshDisplayedList` method integrates both search and sort functionalities to update the list of reports displayed to the user. This method ensures that any changes in search queries or sort options are immediately reflected in the UI.
        5. The `updateStreamState` method controls the data streaming thread based on the current search and sort states. If the user is not searching or sorting, the data stream continues to add new reports periodically.

4. [Data-deletion] Description of the feature (medium)
    * Code: [Class AVLTree, method remove](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/data/AVLTree.java?ref_type=heads)
    * [Class ReportAdapter](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/report/ReportAdapter.java?ref_type=heads): methods deleteButton.setOnClickListener, deleteReport()
    * Description of your implementation: I firstly wrote the code of removing an element from the AVLTree. Recursion is used here, with the use of helper recur method `removeRec()`. In this method, it first considers the extreme case where the tree is empty. Then if then key to delete is less than the current node's value, it travers to left, same case for greater than. If the two values are equal, it is furtherly divided into 3 cases. If the node to dele have no child, have one child, and have 2 childs. The most tricky part is the 2 childs case. You need to find the ascending child using while loop and manages the childs of the ascending node, and then replace it back to the deleted node. We also need to consider height update and tree rebalancing. Then in the ReportAdapter class, If you click on the deletion button, the report get removed from the list of array adapter used for showing the report items, using method called `deleteReport()`, and then pass the `reportId` to the MainActivity to delete the report from the tree using `Observer` design pattern.  <br>

5. [Data-GPS]. Description of the feature  (easy)
    * Code: [Class ReportActivity, methods checkLocationPermissionAndSubmit, getLocationAnSubmitReport, onRequestPermissionResult](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/2d09ad6910cb2804f4cf5dc737e07f647ba9898c/app/src/main/java/com/example/prototype/report/ReportActivity.java)
    * Description of your implementation: This feature automatically gets the user's location using the GPS when submitting the report, removing the need for manual entry. After getting the GPS coordinates, I then implemented a reverse geocoding process to convert the coordinates into a street address, which is included in the report submission. This feature improves the accuracy and ease of reporting, while also providing detailed location to the relevant authorities.
      <br>

### Surprise Feature

<br> <hr>

1. Identifying a Code Component for Refactoring with a Design Pattern
   Before releasing the surprise feature, our group had already implemented two design patterns in the codebase. While reviewing my teammate's code, I found a code snippet using a `switch` statement to determine different sorting actions based on the position of the input. This component could be  refactored using the `Factory Design Pattern`.

   The original code involved multiple conditional branches with a `switch` statement, which made the code less flexible and harder to maintain as new sorting requirements emerged, which led to several issues:

    * Lack of Extensibility: Whenever new sorting logic was introduced, we had to modify the existing `switch` statement, which violates the Open-Closed Principle.

    * Complexity: The `switch` statement made the code less readable and harder to maintain as the number of conditional branches increased.

   Relevant Git Commits, Files, and Line Numbers (Before 10 October):

   CommitSHA:e13cd0f77d1d93ea7d4e362b1e6807bf449930ee
   File: src/com/example/prototype/sorting/MainActivity.java
   Lines: 238-284<br>
   Link to Commit: https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/064ef98de1ce8743521e2d2a3943707b1c227132/app/src/main/java/com/example/prototype/MainActivity.java

2. Correcting the Implementation Issues Identified

To resolve these issues, I refactored the code to use a `Factory Design Pattern`:

1. I defined a Functional Interface called `ReportSorter` with a `sort()` method.

2. I implemented Different Sorters: Created different classes that implemented the `ReportSorter` interface, each providing a specific sorting method.

3. I then Built a Sorter Factory called `ReportSorterfactory` to generate Sorter instances based on the input position.

This new approach improved the robustness and maintainability of the sorting logic. Now, adding new sorting algorithms does not require modifying the existing factory but simply adding a new implementation of the Sorter interface.

Relevant Git Commits, Files, and Line Numbers (On or After 10 October):

Commit SHA: bb36739f0c9ce9fdcc165370f1e10af5030364c9
File: src/com/example/prototype/report/MainActivity.java
Lines: 237-240
File: all the files under package src/com/example/prototype/sort <br>
Link to Commit: https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/commit/bb36739f0c9ce9fdcc165370f1e10af5030364c9<br>
(Note: The sortReports method in MainActivity was refactored by my teammate - the original author after October 10 to enhance its readability. The version I worked on differs slightly from the original one I provided due to these improvements. However, due to project requirement, I am not permitted to provide any commits or code changes made after October 10.)

3. Licence chosen - Apache Licence - 2.0

   We selected Apache 2.0 due to its:
    - Permissive licence, suitable for both commercial and open-source applications.
    - Offers greater freedom for software development and dissemination in the future.
    - Contains explicit patent grants that have the following advantages:
        - offer further legal protection that is beneficial to users and contributors alike
        - facilitatea simple collaboration using third-party library integration, like MPAndroidChart.

Overall, this licence strikes a decent compromise between encouraging the development of open-source software and safeguarding intellectual property.

## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide details (see rubrics) including some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
   - Code: [ReportSearchTest](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/test/java/com/example/prototype/ReportSearchTest.java) for the [Parser](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Parser.java)
   and [Tokenizer](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/main/java/com/example/prototype/search/Tokenizer.java).
   - *Number of test cases: 16*
   - *Code coverage: 90%*
   - *Types of tests created and descriptions: testSearchByDescription: Verifies that searching by the description attribute returns the correct report.
     - testSearchByLocation: Ensures that searching by location filters reports accurately.
     - testSearchByPriority: Confirms that searching by priority retrieves reports with the specified priority level.
     - testSearchByCategory: Checks that searching by category returns the appropriate report.
     - testSearchByUser: Validates that searching by user filters reports submitted by the specified user.
     - testSearchByLikes: Tests that searching by likes returns reports with the exact number of likes.
     - testSearchGeneralToken: Ensures that general search terms filter reports across multiple attributes.
     - testSearchMultipleTokens: Verifies that combining key-value pairs with general tokens filters reports based on all provided criteria.
     - testPartialMatches: Confirms that partial keyword matches successfully retrieve relevant reports.
     - Edge Tests: testEmptyQuery: Checks that an empty search query returns all available reports.
     - testInvalidKey: Ensures that using an invalid key in the search query does not filter out any reports.
     - testNoResults: Confirms that a search query with no matching reports returns an empty list.
     - testSpecialCharacters: Validates that search queries containing special characters are handled gracefully.
     - testCaseInsensitivity: Tests that the search functionality is case-insensitive.
     - testInvalidLikesValue: Ensures that providing a non-numeric value for likes does not cause the search to fail.
     - testMultipleKeyValuePairs: Verifies that multiple key-value pairs in a search query correctly filter reports based on all specified criteria.

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
6. Tests for Report Graphs
    - Code: [ReportAnalyzerTest , entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/816e13112c10e701a0fbe44e41dbabcba3ed5ee6/app/src/test/java/com/example/prototype/ReportAnalyzerTest.java) for the [ReportAnalyzer, entire file](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/816e13112c10e701a0fbe44e41dbabcba3ed5ee6/app/src/main/java/com/example/prototype/chart/ReportAnalyzer.java)
    - *Number of test cases: 5*
    - *Code coverage: 100%*
    - * Types of tests created and descriptions: The test cases verify the methods for retrieving report counts by priority, category, and location. They include checks for correct counts, handling of null values, and edge cases like empty report lists. Specifically:
    * testGetPriorityCounts, testGetCategoryCounts, and testGetLocationCounts make sure that the method getPriorityCounts(), getCategoryCounts and getLocationCounts calculate the correct counts for each priority level, Category, and Location respectively.
    * testEmptyReports: Tests all methods with empty reports, ensuring that zero counts are returned for all priority, category, and location queries. 3: Checks that reports with null values for priority, category, or location are handled properly, ensuring null values are ignored and valid fields are still processed correctly.
      <br> <hr>
7. Tests for filter and sort
    - Code: [ReportSortTest](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/main/app/src/test/java/com/example/prototype/ReportSortTest.java) for the [All classes under sort folder](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/tree/main/app/src/main/java/com/example/prototype/sort) 
    - *Number of test cases: 12*
    - *Code coverage: 90%*
    - *Types of tests created and descriptions: 
    - testSortNewestFirst: Verifies that sorting by newest first correctly orders reports based on the most recent dates.
    - testSortOldestFirst: Ensures that sorting by oldest first accurately orders reports from the earliest to the latest dates.
    - testSortPriorityHighFirst: Confirms that sorting by high priority first correctly places high-priority reports at the top.
    - testSortPriorityLowFirst: Checks that sorting by low priority first accurately positions low-priority reports at the beginning.
    - testSortLikesHighFirst: Validates that sorting by the number of likes in descending order correctly places the most-liked reports first.testReportSorterFactory: Ensures that the ReportSorterFactory correctly instantiates the appropriate sorter based on the provided position identifier.*
    - *Edge case Test: testSortEmptyList: Tests that sorting an empty list does not cause errors and returns an empty list.
    - testSortSingleItemList: Verifies that sorting a list with a single report maintains the list without changes.
    - testSortWithEqualValues: Confirms that sorting handles reports with identical values in the sort field correctly, maintaining stability.
    - testSortAfterAddingReport: Ensures that sorting after dynamically adding a new report incorporates the new report appropriately into the sorted list.
    - testSortAllSameValues: Checks that sorting a list where all reports have the same value in the sort field preserves the original order.

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
- *[Team Meeting 1](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/items/MoM/24_09_2024.md)*
- [Team Meeting 2](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/items/MoM/8_10_2024.md)
- [Team Meeting 3](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/items/MoM/10_10_2024.md)
- [Team Meeting 4](https://gitlab.cecs.anu.edu.au/u7782612/gp-24s2/-/blob/f6a52c64a740d49233e09fb4476b1a96d191f5c8/items/MoM/16_10_2024.md)
- The first 2 meetings have additional commits after the 48 Hour deadline, but they only consist of formatting changes.
  The changes only include removing whitespace and informational text added by course staff and refactoring changes.
  The minutes themselves have not undergone any changes.

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