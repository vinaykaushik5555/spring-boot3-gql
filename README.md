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


cd spring-boot3-gql
Build the project using Maven:


mvn clean install
Run the application:

mvn spring-boot:run
Access the application:
```

# graphiql-interface
The application will start and be available at http://localhost:9191/graphiql?path=/graphql

# GraphQL Schema
The GraphQL schema is defined in the schema.graphqls file located in the src/main/resources/graphql directory.

    type Product {
      id: ID!
      name: String!
      description: String
      price: Float
      stock: Int
      category: String
      manufacturer: String
      createdDate: String
      }

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
         stock: Int,
         category: String,
         manufacturer: String
     ): Product
     updateStock(id: Int!, stock: Int!): Product
    } 
   
# sample-queries-and-mutations
    query Products {
    products {
        id
        name
        description
        price
        stock
        category
        manufacturer
        createdDate
    }
    productsByCategory(category: null) {
        id
        name
        description
        price
        stock
        category
        manufacturer
        createdDate
    }
    product(id: null) {
        id
        name
        description
        price
        stock
        category
        manufacturer
        createdDate
    }
}

    mutation UpdateStock {
      updateStock(id: 1, stock: 34) {
      id
      name
      description
      price
      stock
      category
      manufacturer
      createdDate
      }
    }

Contributing
Contributions are welcome! Please fork this repository and submit a pull request for any features, bug fixes, or enhancements.

License
This project is licensed under the MIT License. See the LICENSE file for details.

This `README.md` file provides a comprehensive overview of the project, including setup instructions, links to relevant parts of the repository, and examples of how to use the GraphQL API.


