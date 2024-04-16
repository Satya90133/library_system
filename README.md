Library System Management API Documentation

Overview
The Library System Management API provides endpoints to manage borrowers and books in a library system. It allows users to register new borrowers, add new books to the library, borrow books on behalf of borrowers, and more.
Base URL
http://localhost:8080/ 
Authentication
Authentication is not required for accessing the endpoints.
Endpoints
1. Add Book
•	Method: POST
•	URL: /books
•	Description: Adds a new book to the library.
•	Request Body:
{
    "isbn": "978-3-16-148410-0",
    "title": "Book Title",
    "author": "Author Name"
}
Response:
•	Status: 201 Created
•	Body:
•	{
•	    "id": 1,
•	    "isbn": "978-3-16-148410-0",
•	    "title": "Book Title",
•	    "author": "Author Name"
}

2. Borrow Book
•	Method: POST
•	URL: /borrowers/{borrowerId}/borrow/{bookId}
•	Description: Allows a borrower to borrow a book from the library.
•	Path Parameters:
•	{borrowerId}: ID of the borrower.
•	{bookId}: ID of the book to borrow.
•	Response:
•	Status: 200 OK
•	Body:

•	{
•	    "message": "Book successfully borrowed"
}

Error Handling
•	If a request fails due to validation errors or any other issue, an appropriate error response with the corresponding HTTP status code and message will be returned.


3. Running the Application with Different Profiles
Default Profile
To run the application with the default profile, use the following command under /target folder
"java -jar library-0.0.1-SNAPSHOT.war"


Custom Profile
To run the application with a custom profile (e.g., local, dev, qa, prod) use the following command:
java -Dspring.profiles.active=application-local.yml -jar library-0.0.1-SNAPSHOT.war
-Dspring.profiles.active=application-local
-Dspring.profiles.active=application-qa
-Dspring.profiles.active=application-prod


4. Usage Example
1. Add Book

	curl -X POST \  http://localhost:8080/books/addBook \
  -H 'Content-Type: application/json' \
  -d '{
      "isbn": "978-3-16-148410-0",
      "title": "Book Title",
      "author": "Author Name"
  }'

Through Postman:- 
Post:  http://localhost:8080/book/addBook
Json sample: 
		{
    "isbn": "978-3-16-148411-0",
    "title": "C++ Book",
    "author": "Denis Author"
}
 


GET API http://localhost:8080/book/getAllBooks
 

 





2. Borrow Book
curl -X POST \  http://localhost:8080/borrowers/addBorrower \
  -H 'Content-Type: application/json' \
  -d '{
      "name": "satya parkash",
      "email": "satyamaurya@example.com",
  }'

Using POSTMAN: - POST  - > http://localhost:8080/borrowers/addBorrower
JSON SAMPLE:- {
    "name": "Satya maurya",
    "email": "satyamaurya@example.com"
}

 









3. Fetch Borrow Book

curl -X POST \
  http://localhost:8080/borrowers/1/borrow/1 \
  -H 'Content-Type: application/json' \

 

Postman GET URL: http://localhost:8080/borrowers/1/borrow/1


Assumptions
•	The ISBN number provided for adding a book must be unique.
•	The borrower ID and book ID provided for borrowing a book must be valid and existing in the system.
•	Proper error handling is implemented for cases such as invalid input, missing data, etc.

