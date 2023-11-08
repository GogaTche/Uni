<?php

include 'db.php';

const EMPTY_FIELDS_ERROR = "All fields must be filled";
const INVALID_EMAIL_ERROR = "Invalid email";
const REGISTERED_EMAIL_ERROR = "Email already registered";
const MISMATCH_PASSWORDS_ERROR = "Passwords don't match";


$db = dbconnect($hostname, $db_name, $db_user, $db_passwd);

if ($db) 
{
    session_start(); 

    $_SESSION["username"] = $_POST["username"];
    $_SESSION["email"] = $_POST["email"];
    $password = $_POST["password"];
    $password_confirm = $_POST["confirm_password"];

    if ($_SESSION["username"] == "" || $_SESSION["email"] == "" || $password == "" || $password_confirm == "")
        error(EMPTY_FIELDS_ERROR);
    elseif (!filter_var($_SESSION["email"], FILTER_VALIDATE_EMAIL)){
        error(INVALID_EMAIL_ERROR);
        unset($_SESSION["email"]);
    }
    elseif ($password != $password_confirm)
        error(MISMATCH_PASSWORDS_ERROR);
    elseif (is_registered($db, $_SESSION["email"]))
    {
        error(REGISTERED_EMAIL_ERROR);
        unset($_SESSION["email"]);
    }
    else
    {
        insert_into_users_db($db, $_SESSION["username"], $_SESSION["email"], $password);
        header("Location: register_success.html");
    } 
    
    mysqli_close($db);
}



function error($error_message)
{
    $_SESSION["message"] = $error_message;
    header("Location: register.php");
}

function is_registered($db, $email)
{
    $query = "SELECT users.email FROM users WHERE users.email = '" . $email ."'";

    if (!($result = @ mysqli_query($db, $query)))
        show_error($db);

    return mysqli_num_rows($result) > 0;
}

function insert_into_users_db($db, $username, $email, $password)
{
    $password_digest = substr(md5($password), 0, 32);   
    $values = "('" . $username . "', '" . $email . "', NOW(), NOW(), '" . $password_digest . "')";
    $query = "INSERT INTO users (name, email, created_at, updated_at, password_digest) values" . $values;

    if (! @ mysqli_query($db, $query))
        showerror($db);

    unset($_SESSION["username"]);
    unset($_SESSION["email"]);
}

?>