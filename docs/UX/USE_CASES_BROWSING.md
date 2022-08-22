# User Cases

**Background:**
Alex wants to find a particular photo they're thinking of. They know that the photo was taken in 2002 on their trip to Ireland. They want to browse their photos to find the photo they're thinking of.

### Navigation

#### Use Case: Navigating Forward
Alex has signed into the app already. They are greeted with the root directory of the FTP server. The **Screen** ***refreshes*** to show the contents of the **Directory**[[1]](#Details). They view the **Directories** in the **Grid View**, and see the  **Directory** with the *name* "Europe". Alex ***selects*** the **Directory** by tapping it and is navigated to the subdirectory[[2]](#Details).

#### Use Case: Navigating Backward
After the **Screen** ***refreshes*** Alex notices that they're in the wrong **Directory**, and wants to ***navigate backward*** to continue searching. They see the **Navigation Bar**, and see the **Directory** they want to navigate to. They click on the *Subdirectory* they want to ***navigate*** to (if the directory is the root, clicks on the house icon), and is navigated to the subdirectory[[2]](#Details).

### Searching

#### Use Case: Searching
Alex decides it's easier to search for the **Directory** *named* "Ireland". They tap on the **Search Bar** and the keyboard opens. As alex types the **Search Bar** *text* is updated and the **Grid View** ***updates*** to show **Directories**, **Files** and **Libraries** with the **Search Bar** *text*[[3]](#Details). Alex sees the **Directory** *named* "Ireland", and taps on it. The screen ***refreshes*** to show the **Directory** *named* "Ireland" and the **Search Bar** is cleared[[1]](#Details).

### Sorting

#### Use Case: Sorting
Alex is in a **Directory** that has a lot of **Photos** and **Directories**. Alex wants to find the oldest photo they have in that directory. They click on the **Sort Button** that opens a dropdown, and Alex selects the **Oldest to Newest** option. The **Grid View** is updated to order the **Photos** from oldest to newest while the **Directories** remain the same[[4]](#Details).

### Viewing Photo

#### Use Case: Viewing Photo
Alex found the photo they were looking for. They see the **Photo** in the **Grid View**, and want to view it larger. They tap on the **Photo** and it opens to be fullscreen.

### Starting Slideshow

#### Use Case: Starting Slideshow
Alex wants to start a slideshow from their Ireland photos. they see the **Directory** *named* "Ireland" and clicks on the **Directory** *option* button. The **popup menu** appears and the option for *Start Slideshow* appears, along with *cancel*. Alex taps that option and a slideshow is started[[5]](#Details).

### Viewing Library

#### Use Case: Navigating to Libraries

Alex decides now that they want to see what **Libraries** they have set up. They tap on the **Hamburger** button to open the **Side Bar**. There they can see the *Root Directories*, the *Settings* button, and the *Libraries* button. They click on the *Libraries* button and are navigated to a **Grid View** of all of Alexes **Libraries**[[6]](#Details).

#### Use Case: Selecting Library

Alex wants to select a **Library** to browse from the **Grid View**. They look at the **Libraries** *names* and *photo counts* to decide on what library they want to view. They decide on one **Library** and tap on it to ***open*** to a **Grid View** of the **Photos** in the **Library**[[7]](#Details).



### Details

This is a section for more detailed background Cases, including actions that occur that the user might not see or notice.

1. The screen ***refreshes*** to show the contents of the *path* of the **Current Directory**:
* The **Navigation Bar** updates its *path* to reflect the **Current Directory** that Alex is in.
* The **Grid View** ***refreshes*** to show the **Directories** and **photos** of the **Current Directory**.

As the user is browsing, the app is ***downloading photos*** so that they are visible to Alex. They are taking each **File**, and ***fetching*** an *Image* based on its *FTP Location*.

2. brought to the subdirectory:
After the user has tapped on the directory they want to navigate to, the app updates the **Current Directory** based on the selected **Files** *path* , and the screen ***refreshes***

3. As Alex types the **Search Bar** *text* is updated and the **Grid View** updates with the search results.
* the **Search Bar** *text* is updated to reflect what is typed
* the **Grid View** ***updates*** with the search results(see below)

\*When the **Search Bar** *text* is updated the app ***searches*** for
**Directories**, **Files** and **Libraries** to show to Alex. The app ***locates*** and ***filters*** all the **Files** and **Directories** in the **FTP Site** with that *search text* in their *name*. The app also ***locates*** and ***filters*** the **Libraries** that the user has made locally by their *name*.

4. Sorting: When Alex chooses to sort by date, the app ***sorts*** their **Current Directories** *files*, based on their *date*, and ***updates*** the **Grid View** with those *files*.

5. Slideshow: When Alex starts the slideshow, the app shows the **Photo** that Alex has selected. In the background, the app ***locates*** the next **Photo** in the **Current Directory**, and ***fetches*** its *Image* based on its *FTP Location*.

6. Navigated to a **Grid View** of all of Alexes **Libraries**:
The **Screen** ***retrieves*** all of the **Libraries** from the **Database**, and updates the **Grid View** with them, showing each **Libraries** *name*, and first *Files* *Image*

7. Alex ***opens*** a  **Library**:
All of the **Files** in the **Library** ***fetch*** their *Images*, and are shown on the **Grid View**
