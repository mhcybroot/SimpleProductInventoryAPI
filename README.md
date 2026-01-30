Module 14 assignment
Title: Build a Simple Product Inventory API

Problem Overview
Your task is to build a simple Spring Boot REST API that allows a user to manage a list of products. You will combine the validation, exception handling, and logging techniques with your existing knowledge of Spring Data JPA and H2 databases.

Instead of just mocking the service, you will build a fully functional API that can create, read, and validate products, persisting them in an H2 in-memory database.

Task 1: Data Model & Validation
Set up the data model and its validation rules.

1. Create an Enum: Create a Java enum named ProductStatus with the values:

- ACTIVE

- INACTIVE

- DISCONTINUED

2. Product Entity: 

- id (Long, Primary Key, auto-generated)

- name (String)

- description (String)

- sku (String - Stock Keeping Unit)

- price (Double)

- quantity (Integer)

- status (Use the ProductStatus enum. Hint: Use @Enumerated(EnumType.STRING) to store the enum name as a string in the DB.)

3. Apply Bean Validation: All validations must have a custom message.

- name: Must not be blank.

- description: Optional, but if present, must be no longer than 500 characters (@Size).

- sku: Must not be blank.

- price: Must not be null and must be a positive number (@Positive).

- quantity: Must not be null and must be zero or more (@Min(0)).

- status: Must not be null.

Task 2: Service Layer & Custom Exceptions
1. Create Custom Exceptions: Create the following three custom exception classes:

- ProductNotFoundException

- InvalidSkuFormatException

- SkuAlreadyExistsException

2. Create ProductRepository and ProductService: 

- Validate the sku . It must start with SKU- followed by 8 alphanumeric characters (e.g., SKU-A1B2C3D4).

- The sku must be unique.

- Handle case of product not found.

- The sku of an existing product cannot be changed. 

Task 3: Controller & Endpoints
1. Implement All 5 Endpoints:

- POST /: Creates a new product.

- GET /: Returns a list of all products.

- GET /{id}: Returns a single product by its ID.

- PUT /{id}: Updates an existing product.

- DELETE /{id}: Deletes a product by its ID.

Task 4: Exception Handling & Logging (15 Marks)
Implement a global, application-wide strategy for handling errors and logging activity.

1. Exceptions:

- Handle MethodArgumentNotValidException (for bean validation failures). Return a 400 Bad Request.

- Handle ProductNotFoundException. Return a 404 Not Found.

- Handle InvalidSkuFormatException. Return a 400 Bad Request.

- Handle SkuAlreadyExistsException. Return a 409 Conflict.

Each handler must return a clear, user-friendly JSON error message.

2. Implement Logging:

- Use @Slf4j (from Lombok) in your Controller, Service, and ExceptionHandler classes.

- INFO: Log all successful operations (e.g., log.info("Product created with ID: {} and SKU: {}", newProduct.getId(), newProduct.getSku());).

- DEBUG: Log all incoming request data (e.g., log.debug("Received request to create product: {}", product);).

- WARN: Log "client error" scenarios, like in the ProductNotFoundException handler (e.g., log.warn("Failed to find product with ID: {}", id);).

- ERROR: Log all exceptions in your GlobalExceptionHandler before returning the response (e.g., log.error("Validation failed: {}", exception.getMessage());).

Submission:

Submit the URL of your GitHub repository (ONE PROJECT IN ONE REPOSITORY)

or submit an archived zip file. 

Important Notes:

- 10 marks will be deducted for not following the submission instructions.

- 5 marks will be deducted for late submission.
