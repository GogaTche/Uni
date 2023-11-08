<?php

include 'db.php';

// put full path to Smarty
// require('C:\xampp\php\lib\smarty-4.3.4\libs\Smarty.class.php');
require('/usr/share/php/smarty/libs/Smarty.class.php');
$smarty = new Smarty();

$smarty->template_dir = 'templates';
$smarty->compile_dir = 'templates_c';

// connection to the database
$db = dbconnect($hostname, $db_name, $db_user, $db_passwd);

if ($db) {

    session_start();

    // creates query in a string
    $query = "SELECT microposts.content, users.name, microposts.created_at, microposts.updated_at, microposts.user_id, microposts.id FROM microposts 
    INNER JOIN users ON users.id = microposts.user_id ORDER BY microposts.updated_at DESC";

    // executes a query*
    $result = @ mysqli_query($db, $query);
    if (!$result) {
        showerror($db);
    }

    // query result
    $nrows = mysqli_num_rows($result);
        for ($i = 0; $i < $nrows; $i++)
            $posts[$i] = mysqli_fetch_assoc($result);

    if (isset($_COOKIE['rememberMe']))
        rememberMe_login($db);
    
    $smarty->assign('isLoggedIn', isset($_SESSION["id"]));
    if(isset($_SESSION["id"])){
        $smarty->assign('id', $_SESSION["id"]);
        $name = $_SESSION["name"];
    }
    else{
        $smarty->assign('id', -1);
        $name = "";
    }

    $smarty->assign('name', $name);

    // faz a atribuição das variáveis do template smarty
    $smarty->assign('microposts', $posts);

    // Mostra a tabela
    $smarty->display('index_template.tpl');

    // fechar a ligação à base de dados
    mysqli_close($db);

}

function rememberMe_login($db) {
    $query = "SELECT users.id, users.name, users.email FROM users WHERE users.remember_digest='" . $_COOKIE['rememberMe'] . "'";
    $result = @ mysqli_query($db, $query);
    if (!$result)
        showerror($db);
    
    $row = mysqli_fetch_assoc($result);

    $_SESSION['id'] = $row['id'];
    $_SESSION['name'] = $row['name'];
    $_SESSION['email'] = $row['email'];
}
?>