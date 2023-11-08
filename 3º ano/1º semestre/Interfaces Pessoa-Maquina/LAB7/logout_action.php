<?php

include 'db.php';

session_start();

session_destroy();

session_start();

if (isset($_COOKIE['rememberMe']))
    setcookie("rememberMe", "", time() - 1);

$_SESSION["message"] = "See you back soon!";

header("Location: message.php");

?>