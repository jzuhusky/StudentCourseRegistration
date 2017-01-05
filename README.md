# StudentCourseRegistration
Undergrad Object-Oriented Design hw/project. MVC based Student Course Registration System w/ Swing GUIs

Model has a list of Observers, which are the controllers.

Views have 1 observer, also a type of controller. 

Controllers implement ViewObserver & Model Observer and respond accordingly to each when a 
change of state occurs, either on model side or view side.
