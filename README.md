# Cerebro Frontend

This is the frontend of the Cerebro application, which is the client side application that retreives encrypted credentials from the database and attempts to automatically log in to the users web accounts. The client is based in Java with the use of additional libraries that are packaged using Maven. The client encrypts/decrypts all sensitive data using AES and sends/receives it to/from the backend. Each request also generates a RSA token (signature) based on request parameters, which is checked on the server side and vice versa.

The list of used libraries:
-Java Swing - cross-platform UI
-Selenium - for browser scripts
-Jackson FasterXML - for JSON parsing
-OkHTTP3 - HTTP requests

Full documentation can be found on the main branch of the repository.
