# MouseLab-DB


A cloud-based solution for storing and sorting lab animal information
Creators
Jessie Chen (jchen18@luc.edu) - Bioinformatics Consultant
Andrew Himebaugh (ahimebaugh1@luc.edu) - Interface Engineer
Sam Jaros (sjaros@luc.edu) - Design and Concept
Kenzo Scheerlinck (kscheerlinck@luc.edu) - Database Manager

Meetings every Tuesday at 5:30pm in the IC
Shared Folder
Abstract
The goal of this project is to create a database for the management and tracking of lab animals, their genotypes, their family history, and other relevant identifiers. Much time and effort is taken to track lab animals of all types as most laboratories simply use a notebook or a shared excel sheet. MouseLab DB aims to allow lab technicians to quickly add, lookup, and track a colony just as they would with legacy options while adding in data validation, user authentication, remote access, and the possibility to integrate with more complex colony-planning algorithms.

Users of MouseLab DB should expect to see a great decrease in missing or incomplete data and mistakes due to incorrect information. Because the information is stored in a purpose-built database rather than written on paper or tracked in a general data-processing program like excel, MouseLab DB can prompt users to input missing information, remind users of important dates, and point out a situation which requires human attention. User authentication is a large part of MouseLab DB because nearly all science is a collaborative effort. A self-checking, cloud-hosted database stops possible conflicts of illegible writing or shorthand which may not be comprehended by other lab members.

Database Design
Our database will have one table that will contain all of the mouses.
We will also use phpMyAdmin to create the table (whether through localhost [xampp] or hosted).
Fields:
ID (A_I)
Alphanumerical ID (String)
Sex (probably Int)
DOB (maybe Date, but probably String)
DOD (maybe Date, but probably String)
Status (probably Enum)
Mother (a.n. ID)
Father (a.n. ID)
Strain/Genotype (String)
Milestones
Create classes for storing information
Write a text-based interface for creating and interacting with a local database
Create MySQL database
Write a text-based interface for interacting with the database
Create a text-based web interface for interacting with the database
Add user authentication to the web interface
Create a GUI for interacting with the database
Related Projects
Employees Sample Database (freely available, open source)
This database, provided by MySQL, can serve as a guide for downloading, installing, and setting up our MySQL database. There is extensive documentation available about this relatively large dataset, which may be useful in the future.

GenotypeBuilder (freely available, open source)
This class and its corresponding “Genotype” and “Allele” classes provide a comprehensive way of storing genetic information. Though these classes have many more methods than we would need, we may use their code structure while writing our own classes.

Firebase Authentication (freely available, closed source)
This is a service provided by Google which allows websites to use Google’s authentication services to verify user identity. This requires all users using MouseLab DB to have a Google account, but we believe this is a reasonable expectation.
