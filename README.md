
# Wallet
Welcome to e-wallet sever. This is Server Side code for e-wallet written in java. 
Feel Free to contribute or suggest.

## Installation
### Install Java8
### Install Mysql Database and run following script

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


##NOTES:

#### once the server is up, using swagger(http://localhost:9921/wallet-server/swagger-ui.html#/) one can see all the APIs exposed ,refrence:swagger file under refrence folder 
#### which will list all the contracts, example: login.png under refrence folder one will be able to test by substituting the values and test.
#### once you run the server the DB would automatically be created in the schema you created, hence if you want to persist data which you created during test/trial please make sure to comment out create command in "HibernateConfig.java" before you run the server again 

#### Integration tests can be found under wallet/wallet-api/src/test/java/ApiTest folder

#### adding ER -Diagram under refrence folder as e-wallet-ERDiagram.png.


1) For login, one must be a registered user.
2) For registration, have used OTP based authentication.
 After you submit the registration request, an OTP would be sent to the number provided in the request and a token would be provided in response which would be requested for next API which is SubmitOtp API
  note:have used waysToSms free service for sending OTP which may be down at the time of testing so feel free to peep into logs for otp
3) Provide the OTP received on the number along with the token received from registration API response to complete registration.
4) For Login provide the mobile number and password alone which will give you a token for further request to maintain the session(this token will be used for all requests further to authenticate).
5) Add money will load the balance to the wallet (have assumed that payment gateway is in place and the payment is received successfully ).
6) Cancel transaction API will only cancel the transactions which are in the itermediate state,transaction with: "FAILED", "SUCCESS", "CANCEL" status will not be allowed to cancel and debit & credit after successfully canceling the transaction takes place in respective wallets.
7) Fetch Payee name serves as the first request for Payment API(just like paytm) which will fetch the user name, confirm if the payee exists and start the transaction flow and will give the txnId for the payment request.
8) For payment request, txnId and token serve as Input which will debit the payer and credit the payee up to the negative balance of 50k.
9) getBalance fetches the available amount in the wallet.
10) getTransactions fetches you the history of transactions made.
11) logout api invalidates the token and logs out the user.






