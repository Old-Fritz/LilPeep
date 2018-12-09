<form method = "POST" action="login">
    email<br>
    <input type="text" name="email" required/><br>
    password<br>
    <input type="text" name="password" required/><br>
    <input type="radio" name="kindID" value="1" required>Simple user</input><br>
    <input type="radio" name="kindID" value="2" required>Owner</input><br>
    <input type="radio" name="kindID" value="3" required>Admin</input><br>
    <button type="submit">login</button>
</form>