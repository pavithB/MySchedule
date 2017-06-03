# MySchedule


1. When the application starts, it presents the user with a calendar displaying the dates of the current month and year and 5 buttons labelled Create Appointment, View/Edit Appointments, Delete Appointment, Move Appointment, Search. The user should be able to select a day by clicking on the corresponding date of the calendar. The user can select a diﬀerent month or year by using the displayed calendar. 


2. After the user selects a date, clicking on the Create Appointment button displays the user with 3 textboxes and a Save button. The 3 textboxes will prompt the user to enter three separate things: The title of the appointment event (e.g. Meeting with John), the time of the event and the details of the appointment (e.g. we are going to discuss, this and that and that...). The application should allow the user to enter text of arbitrary length for the details of the appointment and scrollbars should be added to the textarea displaying them, so that the user can scroll up and down and edit all the information entered. Pressing the Save button will save the title and details of the appointment in an SQLite table. The table should contain separate columns for the date of the appointment, time, title of the appointment and details of the appointment (with any additional necessary columns left up to you to create).


3. Any number of appointments could be created for a single date in the previous subquestion. However, any appointment title should be unique for a single date (even if the details and/or time of the 2 appointments are diﬀerent). I.e. if the user has selected 2nd of March 2012 and he/she attempts to enter 2 appointments with the tile “Meeting with

                  1

                  John”, a pop up error window should be displayed “Appointment Meeting with John already exists, please choose a diﬀerent event title”. Two appointments with the same title are allowed if they occur on diﬀerent dates. 


4. After the user selects a date in the initial screen, clicking on the Delete Appointment button displays the user with 2 options as buttons: “Delete all appointments for that date” which will delete from the database all the corresponding appointments and “Select appointment to delete” button which will display all of the appointments titles on that date, sorted in ascending order by time and according to the following format:

                              1. 15:00 Meeting with John 2. 17:00 Team meeting 3. 20:00 Party at Helen’s

                              The user will be asked to enter a number e.g. 2, and then a pop up window will ask for a conﬁrmation Would you like to delete event: “Team Meeting”?, with 2 buttons labelled “Yes” and “No”. After pressing the “Yes” button, the event will be deleted from the database. 

5. After the user selects a date in the initial screen, clicking on the View/Edit Appointment button displays the user with all appointments of that date (in a similar format with the previous subquestion) and allows him/her to edit all the details of a chosen appointment (time, title, details). All the updated information is persisted in the database.

6. After the user selects a date in the initial screen, clicking on the Move Appointment button displays the user with all appointments for that date, asking him/her to select a speciﬁc appointment. Then the application should allow the user to select another date from a calendar view, in which case the appointment is transferred automatically to the newly selected date (the database is updated accordingly).

7. Extend the application so that in the same screen that the user enters the details of an appointment, there is an additional textbox and a button with the label Thesaurus. Entering a word in the textbox and clicking on the button displays the user with a list of synonyms for the word entered. The synonyms will be taken from the following web service:

                              http://thesaurus.altervista.org/service
                              You will need to create a free yahoo account and subscribe to the thesaurus service to obtain your own key by following the link below:
                              http://thesaurus.altervista.org/mykey
                              You will need to parse the returned XML after you send a similar request with the following (you need to replace the test only with your own key):

                              2
                              http://thesaurus.altervista.org/thesaurus/v1?word=peace&language=en_US& key=test_only&output=xml
                              You need to specify the above in a single line. You could test the returned XML by specifying the above in your browser. 

8. Extend the application so that the user can select (i.e. highlight a word) in the details of an appointment and click on a diﬀerent Thesaurus button (another button which is diﬀerent than the previous question). A list of synonyms for the highlighed word from the thesaurus.altervista.org web service will be displayed to the user. The user will be able to select one of the synonyms displayed in the list and the highlighted word will be replaced with the synonym selected from the list

9. The Search button of the initial screen will be used by the user in order to retrieve appointments for which he/she does not remember the date. Clicking on the button will ask the user to enter a string used for the search. The application will search all future appointments and try to match the entered string with the title and details of any appointment(s). The matched appointment(s) will be displayed to the user and he/she will be able to select one of them to see the full details of it. E.g. if the user enters “Wood” then an appointment with title “Meeting with Woodworth” would be matched and displayed. Also an appointment with details “I am going to see Woodworth to see what he thinks of the new programming language I came up with” would be matched and also displayed. Search should be case insensitive and the user is allowed to enter any string including the case which a string contains parts of 2 words separated by a space character. E.g. a search string “see Wood” would display the second appointment above. Exact search of the entered string is thus required. The search implementation must be done programmatically using the Java language and must NOT be done using SQL statements to extract appointment rows matching the entered search string. SQL statements for this subquestion can be used only to extract ALL appointment rows occurring in the future which are then manipulated further using Java code. 
