Id process:

<img width="713" height="438" alt="Get Client" src="https://github.com/user-attachments/assets/76b47530-6472-4e5f-b4da-bfae4415d75c" />

Buy product process:
<img width="892" height="616" alt="ClientGetProduct" src="https://github.com/user-attachments/assets/c5075855-8946-4f07-a026-90213baa347c" />

New Product process:

<img width="586" height="242" alt="SaveOrUpdate" src="https://github.com/user-attachments/assets/ec6fc4b2-c0b9-479d-9a1d-bed5de0d5f14" />

What i thought was missing:

1. In the original ERD, there was no reason to authorize the client after it was just created, I changed it so that authorization is checked first and if the client data is valid but no client yet exists, the client is created and is already authorized, I also streamlined client creation so that an existing client and a new client can be created/retrieved from the same endpoint for convenience
2. In the instructions and the ERD i did not see how a client can buy/remove a product i added this process where if a client is authorized and a valid product ID is provided then the product is added if the client did not already have that product
3. For adding a new product to the system i created 1 endpoint instead of having 2 where if a product does not exist it is created and if a product already exists with that id the product is updated with new values

