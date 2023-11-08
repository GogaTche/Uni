<?php

include 'db.php';

// put full path to Smarty
//require('C:\xampp\php\lib\smarty-4.3.4\libs\Smarty.class.php');
require('/usr/share/php/smarty/libs/Smarty.class.php');
$smarty = new Smarty();

$smarty->template_dir = 'templates';
$smarty->compile_dir = 'templates_c';

// connection to the database
$db = dbconnect($hostname, $db_name, $db_user, $db_passwd);

if ($db) {
    session_start();

    if (!isset($_SESSION['id'])) {
        header("Location: index.php");
        die();
    }


    $content = "";
    if(isset($_POST["content"])){       
        $content = $_POST["content"];
    }

    $values = "('" . $content . "', '" . $_SESSION['id'] . "', NOW(), NOW(), '" . -12345 . "')";
    $query = "INSERT INTO microposts (content, user_id, created_at, updated_at, likes) values" . $values;

    if (! @ mysqli_query($db, $query))
        showerror($db);

    $_SESSION['message'] = "Successfully posted";
    header("Location: message.php");

}


?>