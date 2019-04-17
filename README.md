
# Wallet
Welcome to e-wallet sever. This is Server Side code for e-wallet written in java. 

## Installation
* Java8
* Maven 
* Mysql Database 
TO Create Database use following command,table creation is taken care in code.
```sql
create database `e-wallet`;
```

### Update following fields in application.properties (/wallet/wallet-api/src/main/resources)
* database.url
* database.user
* database.password
* spring.datasource.url
* spring.datasource.username
* spring.datasource.password

## To run the server 
* clone the repository using git clone https://github.com/SakhiArya/wallet.git
* Import the project into your favourite IDE if you want to run in your IDE itself.
* Build the project using "mvn clean install" command (have used maven as building tool).
* Go to WalletMain.java right click and choose "run WalletMain.main()" or debug if you want to debug .
* You can run the jar in command line using "java -jar "

## Points to be noted 
* once the server is up, using swagger(http://localhost:9921/wallet-server/swagger-ui.html#/) one can see all the APIs exposed ,refrence:[swagger](https://github.com/SakhiArya/wallet/blob/development/refrence/swagger).
* Swagger will list all the contracts, refrence: [Contracts example](https://github.com/SakhiArya/wallet/blob/development/refrence/login.png). , one will be able to substitute the values and test(use 'try it out' option in swagger).
* Once you run the server the tables would automatically be created in the schema you created, hence if you want to persist data which you created during test/trial please make sure to comment out create command in "HibernateConfig.java"-Line 90:
"properties.put(Environment.HBM2DDL_AUTO, "create");" before you run the server again
* Integration tests can be found under wallet/wallet-api/src/test/java/ApiTest folder.
* [ER Diagram](https://github.com/SakhiArya/wallet/blob/development/refrence/e-wallet-ERDiagram.png)

## Brief About Apis and sequence to be followed .

1) For login, one must be a registered user.
2) For registration, have used OTP based authentication.
 After you submit the registration request, an OTP would be sent to the number provided in the request and a token would be provided in response which would be requested for next API which is SubmitOtp API
  #### note:have used waysToSms free service for sending OTP which may be down at the time of testing so feel free to peep into logs for otp
3) Provide the OTP received on the number along with the token received from registration API response to complete registration.
4) For Login provide the mobile number and password alone which will give you a token for further request to maintain the session 
 #### (this token will be used for all requests further to authenticate).
5) Add money will load the balance to the wallet (have assumed that payment gateway is in place and the payment is received successfully ).
6) Cancel transaction API will only cancel the transactions which are in the itermediate state,transaction with: "FAILED", "SUCCESS", "CANCEL" status will not be allowed to cancel and debit & credit after successfully canceling the transaction takes place in respective wallets.
7) Fetch Payee name serves as the first request for Payment API(just like paytm) which will fetch the user name, confirm if the payee exists and start the transaction flow and will give the txnId for the payment request.
8) For payment request, txnId and token serve as Input which will debit the payer and credit the payee up to the negative balance of 50k.
9) getBalance fetches the available amount in the wallet.
10) getTransactions fetches you the history of transactions made.
11) logout api invalidates the token and logs out the user.






