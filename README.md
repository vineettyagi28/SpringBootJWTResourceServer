A resource server based on Spring boot 2.0.0M4, OAuth2 and JWT token.

#Steps to run
1. Generate a file "public.cert" from same keystore which was used in authorixzation server, using the steps below:-
	
	keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey
	
	**Copy the public key along with BEGIN and END comment lines and put this in a file named "public.cert" and keep that file in application src/main/resources folder** 
	
2. Compile and deploy the application

3. Test your application by hitting 
	
	1. Save the token value from auth server in a variable named TOKEN and execute
		curl -H "Authorization: Bearer $TOKEN" "localhost:9000/foo"
		-It should return the generated token.
	2. curl -XPOST -H "Authorization: Bearer $TOKEN" "localhost:9000/foo"
		- Access Denied as it needs a permission which is not included in the given token
	3. In authorization server mongo database, add "FOO_WRITE" in authorities of "admin" user, and then again execute
		curl -XPOST -H "Authorization: Bearer $TOKEN" "localhost:9000/foo"
		- It will excute successfully and return response
