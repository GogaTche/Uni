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

    // creates query in a string
    $query = "SELECT microposts.content, users.name, microposts.created_at, microposts.updated_at FROM microposts INNER JOIN users ON users.id = microposts.user_id ORDER BY microposts.updated_at DESC";

    // executes a query
    $result = @ mysqli_query($db, $query);
    if (!$result) {
        showerror($db);
    }

    // query result
    $nrows = mysqli_num_rows($result);
        for ($i = 0; $i < $nrows; $i++)
            $posts[$i] = mysqli_fetch_assoc($result);

    // faz a atribuição das variáveis do template smarty
    $smarty->assign('microposts', $posts);

    // Mostra a tabela
    $smarty->display('index_template.tpl');

    // fechar a ligação à base de dados
    mysqli_close($db);
}
?>