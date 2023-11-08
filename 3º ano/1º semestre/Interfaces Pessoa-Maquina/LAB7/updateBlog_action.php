<?php

include 'db.php';

// put full path to Smarty
//require('C:\xampp\php\lib\smarty-4.3.4\libs\Smarty.class.php');
require('/usr/share/php/smarty/libs/Smarty.class.php');
$smarty = new Smarty();

$smarty->template_dir = 'templates';
$smarty->compile_dir = 'templates_c';

$db = dbconnect($hostname, $db_name, $db_user, $db_passwd);

if ($db) {
    session_start();

    if (!isset($_SESSION['id']) || $_SESSION['id'] != $_GET['microposts_user_id']) {
        $_SESSION['message'] = "You naughty naughty";
        header("Location: message.php");
        die();
    }

    $values = "content='" . $_POST['content'] . "', updated_at=NOW()";
    $query = "UPDATE microposts SET " . $values . "WHERE microposts.id=" . $_GET['microposts_id'];
    $result = @ mysqli_query($db, $query);
    if (!$result)
        showerror($db);
    
    $_SESSION['message'] = "Successfully updated";
    header("Location: message.php");

    mysqli_close($db);
}
?>