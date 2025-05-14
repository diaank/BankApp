# Bank Application

A simple banking application built with Java and Maven. This application allows users to perform basic banking operations such as deposits, withdrawals, and generating account statements.
No currency is implemented in this demo. For the account statement generation demonstration, a simple bank operations flow is created in the main method of the BankApp.java file.

## Features

- **Deposit Transactions**: Add funds to an account.
- **Withdrawal Transactions**: Deduct funds from an account with balance validation.
- **Account Statement**: Generate a comprehensive list of all transactions, displayed in reverse chronological order with the most recent transactions at the top.

## Prerequisites

- Java 21 or higher
- Maven 3.8 or higher

## How to Run

1. To build the project using Maven:
   ```bash
    mvn clean install
    ```
2. To test the application: 
   ```bash
   mvn test
   ```
3. A demo account statement should be displayed on the screen just for demonstration purposes. 
For further implementation, remove the code in the main method of BankApp.java and remove BankAppTest.java test file.
