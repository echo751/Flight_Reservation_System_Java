# General overview

We need to develop a new system for an airline that enables passengers to make reservations online.

<p align="center">
  <img src="https://github.com/echo751/Flight_Reservation_System_Java/blob/main/UML.png" width="350" title="UML of system">
  
</p>

The passenger has 1 week to validate his reservation, the validation requires the payment of 30% of the total amount. The rest must be paid no later than 1 week before departure. Reservations for which the deposit was not paid on time are deleted.

In case of flight cancellation, the passengers  are informed so that they can approach agency to change their flights. It is necessary to display the names and coordinates of the persons to be contacted.


The functions required to realise are listed below:
For client:
Log in & Sign up
Change password
Search for a flight 
Create a reservation
Add / Remove a flight to a reservation
Delete a reservation
Pay a reservation (deposit or full)


For agency:
Create flights for this company
Modify the price of a flight
Consult the number of reservations for a flight per class
Cancel a flight
Follow up with customers who have not paid for their entire reservation 15 days before departure
Follow up with customers who have not yet paid their deposit
Register the different passengers of a reservation after receipt of photocopies of passports
Delete reservations for which the deposit has not yet been paid and whose deadline has passed
Number of reservations for a given flight
Number of reservations for a given destination


# Packages:

Based on MVC pattern, notre program includes 4 packages: Model that contains all classes and their features, GestionGlobal that contains all methodes for different group( agent/client), GestionDomaine is responsible of the realisation of functions,and VueControlleur renders graphical user interface using SWING components.


# Schema for GUI:

<p align="center">
  <img src="https://github.com/echo751/Flight_Reservation_System_Java/blob/main/Schéma%20Graphique.jpg" width="350" title="UML of system">
  
</p>
