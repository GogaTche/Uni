<?php

include 'db.php';

// put full path to Smarty
// require('C:\xampp\php\lib\smarty-4.3.4\libs\Smarty.class.php');
require('/usr/share/php/smarty/libs/Smarty.class.php');
$smarty = new Smarty();

$smarty->template_dir = 'templates';
$smarty->compile_dir = 'templates_c';

$db = dbconnect($hostname, $db_name, $db_user, $db_passwd);


session_start();

$message = "Login";
$email = "";

if (isset($_SESSION['id']))
    header("Location: index.php");
else{
if(isset($_SESSION['message'])){
    $message = $_SESSION['message'];
    unset($_SESSION['message']);
}

if(isset($_SESSION['email'])){
    $email = $_SESSION['email'];
    unset($_SESSION['email']);
}

$smarty->assign('message', $message);
$smarty->assign('email', $email);



$smarty->display('login_template.tpl');
}

?>