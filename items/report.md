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

*Here is a pet training application example*

*Molly wants to inquiry about her cat, McPurr's recent troublesome behaviour*
1. *Molly notices that McPurr has been hostile since...*
2. *She makes a post about... with the tag...*
3. *Lachlan, a vet, writes a reply to Molly's post...*
4. ...
5. *Molly gives Lachlan's reply a 'tick' response*

*Here is a map navigation application example*

*Targets Users: Drivers*

* *Users can use it to navigate in order to reach the destinations.*
* *Users can learn the traffic conditions*
* ...

*Target Users: Those who want to find some good restaurants*

* *Users can find nearby restaurants and the application can give recommendations*
* ...

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

<hr> 

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
          Maintains a balanced tree automatically after each operation, preventing skewed trees and ensuring consistent performance.
        - **Sorted Data:**  
          Keeps the reports sorted based on their `reportId`, facilitating quick retrieval and organized data management.
        - **Dynamic Size:**  
          The tree can dynamically adjust its size as reports are added or removed, providing flexibility in handling varying amounts of data.

2. **Tokenizer**
    - **Objective:**  
      Used for parsing and processing user input in the `SearchView` to facilitate effective search functionality.

    - **Code Locations:**  
      Defined in the `Tokenizer` class within the `com.example.prototype` package. It is utilized in the `Parser` class to tokenize and normalize search queries before processing.

    - **Reasons:**
        - **Efficient Parsing:**  
          Breaks down complex search queries into manageable tokens, enabling more precise and relevant search results.
        - **Normalization:**  
          Converts input to lowercase and removes non-word characters to ensure consistency in search processing, reducing the likelihood of mismatches due to case sensitivity or unwanted characters.
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
   * *Code Locations: Class Authenticator: Throughout the app's lifetime, just one instance of Authenticator is produced thanks to the getInstance() method.
      Class DataHolder: Here, the same method is used to keep the AVL tree single-handed throughout the program.*
   * *Reasons:When we desire a single source of truth for the authentication logic used throughout the application, such as with Authenticator, we use singletons to govern the creation of objects. It is employed in DataHolder to guarantee data consistency (the AVL tree) between various components.*

2. Observer Pattern
    * *Objective: Enables an object (subject) to communicate any changes in state to its dependents (observers), usually in a decoupled fashion.*
    * *Code Locations: MainActivity implements Observer: This pattern allows MainActivity to observe and respond to data changes or events, such as when a report is added or removed, or when sorting is updated.*
    * *Reasons:The observer design helps to maintain the user interface current with modifications to the data (e.g., when report data changes in MainActivity).*

<hr>

### Parser

### <u>Grammar(s)</u>
*Grammar design defines how the input queries are structured and interpreted by the parser. 
The key advantage of this design is that it separates key-value pairs from general tokens, 
llowing efficient filtering based on user queries. The grammar allows for flexible and 
human-readable search queries by supporting a wide range of input structures.*
*In this case, there are two main types of grammars:*
**Key-Value Grammar: This grammar identifies tokens in the form of key-value pairs, where the key represents an attribute (e.g., description, location) and the value is the filter criterion.**
***<KeyValueToken> ::= <key> ":" <value>***
**General Token Grammar: These tokens represent general search terms that apply to any attribute of the reports (e.g., description, category). Example production rule:**
***<GeneralToken> ::= <word>***

### <u>Tokenizers and Parsers</u>

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*
*Tokenizers:*
**The tokenizer is used to break the user input into discrete tokens. It identifies both key-value pairs
(such as location: 5th Avenue) and general tokens (e.g., middle, maintenance). The tokenizer ensures input
is standardized by removing special characters and splitting the input based on spaces.**

**Design:
The tokenizer cleans the input by removing non-word characters and splitting the cleaned string by 
spaces. Key-value pairs are easily identified because they contain a colon (:).**

*Parsers:*
**The parser is responsible for interpreting the tokens and applying them to the search. The parser first
processes key-value pairs, filtering reports based on attributes like location, description, priority, 
and so on. Then, it processes general tokens, which apply to any field in the reports.**
**Design:
The parser breaks the tokens into two categories: key-value pairs and general tokens. It uses production 
rules to filter the reports based on the token type. For key-value pairs, it applies filters to specific
fields (e.g., location or priority). For general tokens, it checks all fields to find matches.**
<hr>

### Others

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

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

2. [DataFiles]. Description  ... ... (...)
   * Code to the Data File [users_interaction.json](link-to-file), [search-queries.xml](link-to-file), ...
   * Link to the Firebase repo: ...

3. ...
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
3. [FB-Auth] Description of the feature (easy)
   * Code: [Class X, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43) and Class Y, ...
   * [Class B](../src/path/to/class/file.java#L30-85): methods A, B, C, lines of code: 30 to 85
   * Description of your implementation: ... <br>

<hr>

### Surprise Feature

*Instructions:*
- If implemented, explain how your solution addresses the task (any detail requirements will be released with the surprise feature specifications).
- State that "Surprise feature is not implemented" otherwise.

<br> <hr>


## Testing Summary

*[What features have you tested? What is your testing coverage?]*
*Please provide details (see rubrics) including some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

*Here is an example:*

1. Tests for Search
   - Code: [TokenizerTest Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java) for the [Tokenizer Class, entire file](https://gitlab.cecs.anu.edu.au/comp2100/group-project/ga-23s2/-/blob/main/items/media/_examples/Dummy.java#L22-43)
   - *Number of test cases: ...*
   - *Code coverage: ...*
   - *Types of tests created and descriptions: ...*

2. xxx

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


