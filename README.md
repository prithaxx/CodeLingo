# Group 15: Code Linguists

<details open>
<summary>About us</summary>

| Name | Email |
| ------ | ------ |
|   Sa'ad Ahmed     |     ahmeds17@myumanitoba.ca   |
|     Fengfan Bian   |     bianf@myumanitoba.ca   |
|   Josh Chartrand    |    chartr52@myumanitoba.ca   |
|    Pritha Das   |    dasp4@myumanitoba.ca  |
|   Duc Nghia Do   |     dodn@myumanitoba.ca   |

</details>

# Iteration 0 Notes
- All features are issues with the label "Feature", all user stories are issues with the label "UserStory"
- The 5 User stories for iteration 1 are all marked as "high" with a label. We included future user stories to ensure the time estimates were consistent for grading.
- Developer tasks omitted from this iteration

# Iteration 1 Notes
- Time reporting is done on Issues labeled "DevTask"
- The focus of this iteration was to create a horizontal prototype, so we implemented a few features to a demonstrable state.

# Iteration 2 Notes
- Time reporting is done on Issues labeled "DevTask".
- This iteration had 2 sprints:
    + Sprint 1 focused on implementing the Demo Quiz and Course Navigation features.
    + Sprint 2 focused on integration testing and HSQLDB setup.
- In this iteration, we started 2 features: Demo Quiz and Course Navigation. These 2 features are mostly finished:
    + Demo Quiz : Still need to implement code block type quiz slide (low priority).
    + Course navigation: Still need to work on the design of the Chapter Summary popup modal (medium priority).
    + [These issues](https://code.cs.umanitoba.ca/comp3350-winter2024/codelinguists-ao1-15/-/issues/?sort=created_date&state=opened&milestone_title=Iteration%203%20-%20CodeLingo&or%5Blabel_name%5D%5B%5D=Sprint%201%20-%20Iteration%202&or%5Blabel_name%5D%5B%5D=Sprint%202%20-%20Iteration2&first_page_size=100) have been moved into the next iteration.

# Config
- To reset the DB, disable persistence by setting 
  - [DbHelper.java](app%2Fsrc%2Fmain%2Fjava%2FCodeLinguists%2Fcodelingo%2Fpersistence%2Futils%2FDbHelper.java).doReset to true
- To switch DB implementations, set 
  - [Services.java](app%2Fsrc%2Fmain%2Fjava%2FCodeLinguists%2Fcodelingo%2Fapplication%2FServices.java).DbType to DbType.STUB or DbType.HSQLDB

# Other Docs
 - [ARCHITECTURE.md](Docs/ARCHITECTURE.md)
 - [VisionStatement.md](Docs/VisionStatement.md)
 - [CodingStyle.md](Docs/CodingStyle.md)
 - [DatabaseDiagram.md](Docs/DatabaseDiagram.md)
- - -
- - -
<br>
