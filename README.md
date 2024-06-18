# Spring Boot 3 GraphQL API

This repository contains a Spring Boot 3 application that demonstrates the implementation of a GraphQL API for managing products. It includes basic CRUD operations along with the ability to query products by category.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Setup](#setup)
  - [Clone the Repository](#clone-the-repository)
  - [Build and Run Locally](#build-and-run-locally)
- [GraphQL Schema](#graphql-schema)
- [Usage](#usage)
  - [GraphiQL Interface](#graphiql-interface)
  - [Sample Queries and Mutations](#sample-queries-and-mutations)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project provides a basic setup for a GraphQL API using Spring Boot 3. It demonstrates how to create a schema, define resolvers, and perform CRUD operations on a `Product` entity.

## Features

- Create, Read, Update, Delete (CRUD) operations for products
- Query products by category
- Interactive GraphQL interface using GraphiQL

## Requirements

- Java 17 or higher
- Maven 3.6.3 or higher

## Setup

### Clone the Repository

```sh
git clone https://github.com/vinaykaushik5555/spring-boot3-gql.git
cd spring-boot3-gql

Navigate to the project directory:

sh
Copy code
cd spring-boot3-gql
Build the project using Maven:

sh
Copy code
mvn clean install
Run the application:

sh
Copy code
mvn spring-boot:run
Access the application:

The application will start and be available at http://localhost:8080.

GraphQL Schema
The GraphQL schema is defined in the schema.graphqls file located in the src/main/resources directory.

schema.graphqls
graphql

type Query {
    products: [Product]
    product(id: ID!): Product
    productsByCategory(category: String!): [Product]
}

type Mutation {
    createProduct(
        name: String!,
        description: String,
        price: Float,
        quantity: Int,
        category: String,
        manufacturer: String
    ): Product

    updateProduct(
        id: ID!,
        name: String,
        description: String,
        price: Float,
        quantity: Int,
        category: String,
        manufacturer: String
    ): Product

    deleteProduct(id: ID!): Boolean
}

type Product {
    id: ID!
    name: String!
    description: String
    price: Float
    quantity: Int
    category: String
    manufacturer: String
    createdDate: String
}
Usage
GraphiQL Interface
The GraphiQL interface is enabled and can be accessed at http://localhost:8080/graphiql. It provides an interactive UI to test GraphQL queries and mutations.

Sample Queries and Mutations
Query All Products
graphql
query {
  products {
    id
    name
    description
    price
    quantity
    category
    manufacturer
    createdDate
  }
}
Query Product by ID
graphql
Copy code
query {
  product(id: 1) {
    id
    name
    description
    price
    quantity
    category
    manufacturer
    createdDate
  }
}
Query Products by Category

query {
  productsByCategory(category: "Electronics") {
    id
    name
    description
    price
    quantity
    category
    manufacturer
    createdDate
  }
}
Create a Product

mutation {
  createProduct(
    name: "New Product"
    description: "New Product Description"
    price: 99.99
    quantity: 100
    category: "New Category"
    manufacturer: "New Manufacturer"
  ) {
    id
    name
    description
    price
    quantity
    category
    manufacturer
    createdDate
  }
}
Update a Product
graphql
mutation {
  updateProduct(
    id: 1
    name: "Updated Product"
    description: "Updated Description"
    price: 199.99
    quantity: 50
    category: "Updated Category"
    manufacturer: "Updated Manufacturer"
  ) {
    id
    name
    description
    price
    quantity
    category
    manufacturer
    createdDate
  }
}
Delete a Product
graphql
Copy code
mutation {
  deleteProduct(id: 1)
}
Project Structure
plaintext
Copy code
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── graphql/
│   │               ├── model/
│   │               │   └── Product.java
│   │               ├── repository/
│   │               │   └── ProductRepository.java
│   │               ├── resolver/
│   │               │   ├── ProductMutationResolver.java
│   │               │   └── ProductQueryResolver.java
│   │               ├── service/
│   │               │   └── ProductService.java
│   │               └── GraphqlAppApplication.java
│   └── resources/
│       ├── application.properties
│       └── schema.graphqls
└── test/
    └── java/
        └── com/
            └── example/
                └── graphql/
                    └── GraphqlAppApplicationTests.java
Contributing
Contributions are welcome! Please fork this repository and submit a pull request for any features, bug fixes, or enhancements.

License
This project is licensed under the MIT License. See the LICENSE file for details.

This `README.md` file provides a comprehensive overview of the project, including setup instructions, links to relevant parts of the repository, and examples of how to use the GraphQL API.


