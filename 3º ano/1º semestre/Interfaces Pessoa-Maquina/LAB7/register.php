<?php

include 'db.php';


require('/usr/share/php/smarty/libs/Smarty.class.php');
$smarty = new Smarty();

$smarty->template_dir = 'templates';
$smarty->compile_dir = 'templates_c';

session_start();

$message = "Register";
$username = "";
$email = "";

if(isset($_SESSION["id"]))
    header("Location: index.php");

else{
if(isset($_SESSION['message'])){
    $message = $_SESSION['message'];
    unset($_SESSION['message']);
}

if(isset($_SESSION['username'])){
    $username = $_SESSION['username'];
    unset($_SESSION['username']);
}

if(isset($_SESSION['email'])){
    $email = $_SESSION['email'];
    unset($_SESSION['email']);
}

$smarty->assign('message', $message);
$smarty->assign('username', $username);
$smarty->assign('email', $email);

// Mostra a tabela
$smarty->display('./templates/register_template.tpl');
}

?>