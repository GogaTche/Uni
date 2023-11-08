<?php

include 'db.php';

const EMPTY_FIELDS_ERROR = "All fields must be filled";
const INVALID_EMAIL_OR_PASSWORD_ERROR = "Invalid email or password";


$db = dbconnect($hostname, $db_name, $db_user, $db_passwd);

if ($db) 
{
    session_start();

    $_SESSION["email"] = $_POST["email"];
    $password = $_POST["password"];

    if ($_SESSION["email"] == "" || $password == "")
        error(EMPTY_FIELDS_ERROR);
    elseif (!filter_var($_SESSION["email"], FILTER_VALIDATE_EMAIL))
        error(INVALID_EMAIL_ERROR);
    elseif (!is_registered($db, $_SESSION["email"]))
    {
        error(INVALID_EMAIL_OR_PASSWORD_ERROR);
        unset($_SESSION["email"]);
    }
    elseif(!is_password($db, $_SESSION["email"], $password))
        error(INVALID_EMAIL_OR_PASSWORD_ERROR);
    else
    {
        login($db, $_SESSION["email"]);
        header("Location: message.php");
    } 
}


function error($error_message)
{
    $_SESSION["message"] = $error_message;
    header("Location: login.php");
}

function login($db, $email)
{
    $query0 = "SELECT users.id, users.email, users.name FROM users WHERE users.email = '" . $email ."'";

    if (!($result = @ mysqli_query($db, $query0)))
        showerror($db);

    $user = @ mysqli_fetch_assoc($result);

    if (isset($_POST['autologin']) && $_POST['autologin'] == 1)
        set_cookie($db, $email);

    $_SESSION["id"] = $user["id"];
    $_SESSION["name"] = $user["name"];
    $_SESSION["email"] = $$user["email"];
    $_SESSION["message"] = "Welcome back!";
}

function is_registered($db, $email)
{
    $query = "SELECT users.email FROM users WHERE users.email = '" . $email ."'";

    if (!($result = @ mysqli_query($db, $query)))
        show_error($db);

    return mysqli_num_rows($result) > 0;
}

function is_password($db, $email, $password)
{
    $password_digest = substr(md5($password), 0, 32);   
    $query = "SELECT users.password_digest FROM users WHERE users.email = '" . $email ."'";

    if (! @ mysqli_query($db, $query))
        showerror($db);

    $result = mysqli_query($db, $query);

    return $password_digest == mysqli_fetch_assoc($result)['password_digest'];
}

function set_cookie($db, $email) {
    $cookie = substr(md5(time()), 0, 32);
    setcookie("rememberMe", $cookie, time() + (3600 * 24 * 30));

    $query = "UPDATE users SET users.remember_digest='" . $cookie . "' WHERE users.email='" . $email . "'";
    $result = @ mysqli_query($db, $query);
    if (!$result)
        showerror($db);
}

?>