# APPLICATION REQUIREMENTS

## Application User

### 1. User new account (unique email address)
* Account verification
* User Profile image
* User details (name, email, **phone** interests, **location**, address, etc)
* Being able to update the user information

### 2. User reset password (without being logged in)
* Password reset link should expire in 24 hours
### 3. User login (using email and password)
* Token based authentication (JWT token)
* Refresh token seamlessly
### 4. Brute force attack mitigation (account lock mechanism)
* Lock account after 6 failed attempts
### 5. Two factor authentication (using user phone number)
* Send verification code to user's phone number
### 6. Keep track of users activities (login, account change, etc)
* Ability to report suspicious activities
* Tracking information
  * IP Address
  * Device
  * Browser
  * Date 
  * Type

## Cinema

### 1. Cinema new account (unique email address)
* Account verification
* Cinema Profile image
* Cinema details (name, email, **phone**, about, images, **location**, Country, State, City, address, etc)
* Cinema Work days and hours
* Cinema Bank account (Bank name, account name, account number)
* Being able to update the cinema information

### 2. Role and Permission based access (ACL)
* Protect cinema resources using roles and permissions

### 3. Cinema login (using email and password)
* Token based authentication (JWT token)

### 4. Brute force attack mitigation (account lock mechanism)
* Lock account after 6 failed attempts

### 5. Two factor authentication (using user phone number)
* Send verification code to user's phone number

### 6. Add Movie to cinema 
* Cinema should be able to add a movie to its catalogue by searching
* Based on the cinema's working days and hours show time should be selected
* Cinema should be able to set other movie details like (now showing, price, date and time, etc)

### 7. Food and Drinks
* Cinema should be able to select food or drink, quantity, and add price
* Cinema can mark food/drink as available or not
* Cinema can add their ow unique snacks (name, price, availability)
* Cinema should be able to list its food and drinks (filter by: search, category, etc)
* Cinema should be able to update/delete food and drink info 

### 8. Orders
* An order has a bookingID and is mapped to a single movie showing, but can have more than 1 ticket
* A order comprises of:
  * Movie
  * List of tickets
  * Payment Status
  * Total Amount
  * Print tickets for mailing
  * Print tickets 
  * Download tickets as pdf

### 9. Tickets
* A is payment for a single seat for a movie showing.
* Ticket comprises:
  * Movie
  * Movie time
  * List of food and drinks
  * Quantity and price of each food/drink
  * Total amount

### 9. Transactions
* Cinemas should be able to see a list of transactions made by clients
* Transaction should have bookingID, Client username, amount and Status(PAid, Pending, Expired)

### 10. Keep track of users activities (login, account change, etc)
* Ability to report suspicious activities
* Tracking information
    * IP Address
    * Device
    * Browser
    * Date
    * Type

## Movies
* With the help tmdb apis and our custom implements, cinemas and clients should:
  * Be able to search movies
  * View Coming Soon movies
  * Filter movies by categories
  * View Trending movies
  * View top ten movies showing around them
  * View recent releases
  * View top and recent movie searches

## Notifications
* Push Notifications
* In-app notifications

## Reviews and Ratings
* Clients should be able to drops reviews for cinemas and movies they have watched

## Privacy Policy
* Unauthenticated endpoint that servers the privacy policy