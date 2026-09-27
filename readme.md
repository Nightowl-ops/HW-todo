1. GET All Categories: [http://localhost:8089/api/categories/]
2. POST Create Category: [http://localhost:8089/api/categories/]
3. GET Category By ID: [http://localhost:8089/api/categories/1]
4. PUT Update Category: [http://localhost:8089/api/categories/1]
5. DELETE Delete Category: [http://localhost:8089/api/categories/1]

for the iteams
GET All Items in Category: http://localhost:8089/api/categories/{categoryId}/items

POST Create Item in Category: http://localhost:8089/api/categories/{categoryId}/items

GET Item By ID: http://localhost:8089/api/categories/{categoryId}/items/{itemId}

PUT Update Item: http://localhost:8089/api/categories/{categoryId}/items/{itemId}

DELETE Delete Item: http://localhost:8089/api/categories/{categoryId}/items/{itemId}


GET Hello this is the health check: http://localhost:8089/hello

POST Register User: http://localhost:8089/auth/users/register

POST Login User: http://localhost:8089/auth/users/login