# payload-sucure
This project is to secure the Json payload. Check whether back end received payload is same as font end send one.

Sending data from the front end should be received to backend properly.
Data cannot be changed from middleware.
If it is changed, that should be identified by backend.
This project is for that.
We encrypted the payload from front end by a given private key.
Pay load encrypted string is send in header.
In the backend, Payload is again encrypt and take that encrypted string.
Then compare received encrypted payload string is equal with backend generated encrypted string.
If it is not equal then throw an exception. That means payload has been changed some one.

To handle the font end part - use payloadAuth.html file.
To handle the font back part - use spring boot application (java 11)
