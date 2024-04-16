Task
Create a RESTful API that manages a simple library system.
The API should allow API user to perform the following actions:
1. Register a new borrower to the library.
2. Register a new book to the library.
3. Get a list of all books in the library.

The API should allow API user to perform the following actions on behalf of a borrower:
1. Borrow a book with a particular book id (refer Book in Data Models).
2. Return a borrowed book.

Data Models
Borrower should have a unique id, a name and an email address
Book should have a unique id, an ISBN number, a title, and an author

ISBN number uniquely identifies a book in the following way:
- 2 books with the same title and same author but different ISBN numbers are considered
as different books
- 2 books with the same ISBN numbers must have the same title and same author

Multiple copies of books with same ISBN number are allowed in the system
Requirements
1. Use a programming language and framework of your choice to create the project.
a. Use of Java 17 and Spring Boot framework is an added bonus
2. Configurable to run in multiple environments
3. Use a package manager to manage project dependencies.
4. Implement proper data validation and error handling.
5. Use a database to store borrower and book data.
a. Justify your choice of database
6. Implement REST API endpoints for each action mentioned above.
7. Multiple books with the same ISBN number should be registered as books with different
ids.
8. Ensure that no more than one member is borrowing the same book (same book id) at a
time.
9. Provide clear documentation for how to use your API
10. Provide documentation of all your assumptions for any requirements that are not
explicitly stated in this task

Nice to have
1. Include unit tests & unit test coverage
2. Demonstrate clean code
3. Demonstrate use of containerization and CI/CD tools in declarative manner.
a. Docker and Kubernetes preferred
4. Demonstrate conformance (up to certain