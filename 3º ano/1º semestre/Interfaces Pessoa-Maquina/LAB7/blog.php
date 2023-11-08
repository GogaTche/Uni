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
    
    assign_posts($db, $smarty);
    $smarty->display('blog_template.tpl');
}

function assign_posts($db, $smarty) {
    $update = isset($_GET['post_id']);
    if ($update) {
        $query = "SELECT microposts.content, microposts.user_id FROM microposts WHERE microposts.id=" . $_GET['post_id'];
        $result = @ mysqli_query($db, $query);
        if (!$result)
            showerror($db);
        
        $row = mysqli_fetch_assoc($result);

        $content = $row['content'];
        $user_id = $row['user_id'];
        $smarty->assign('post_id', $_GET['post_id']);
    }
    else {
        $content = "";
        $user_id = -1;
    }

    $smarty->assign('update', $update);
    $smarty->assign('user_id', $user_id);
    $smarty->assign('content', $content);
}

?>
