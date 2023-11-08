<?php

include 'db.php';

// put full path to Smarty
//require('C:\xampp\php\lib\smarty-4.3.4\libs\Smarty.class.php');
require('/usr/share/php/smarty/libs/Smarty.class.php');
$smarty = new Smarty();

$smarty->template_dir = 'templates';
$smarty->compile_dir = 'templates_c';

session_start();

if (isset($_SESSION['message'])) {
    $smarty->assign('message', $_SESSION['message']);
    unset($_SESSION['message']);
    $smarty->display('message_template.tpl');
}
else
    header("Location: index.php");
?>